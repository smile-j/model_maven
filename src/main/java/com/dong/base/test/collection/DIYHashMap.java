package com.dong.base.test.collection;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/12/7.
 */
 public class DIYHashMap<K,V> implements DiyMap<K,V> {

    //定义初始化默认数组长度 ；
    private int defalutLength = 16 ;

    //定义默认负载因子 ；
    private double defalutAddSizeFactor = 0.75 ;

    //使用数组位置的总数
    private int useSize ;

    //定义骨架Entry数组 ；
    private Entry<K,V>[] table ;

    public  DIYHashMap(int defalutLength, double defalutAddSizeFactor){
        if(defalutLength < 0){
            throw new IllegalAccessError("数组长度为负数") ;
        }

        if(defalutAddSizeFactor<=0 || Double.isNaN(defalutAddSizeFactor)){
            throw new IllegalAccessError("扩容长度不能为非正数字");
        }

        this.defalutAddSizeFactor = defalutAddSizeFactor ;
        this.defalutLength = tableSizeFor(defalutLength) ;
        table = new Entry[defalutLength];

    }

    public int tableSizeFor(int n ){
        int num = n-1 ;
        num |= num>>>1;
        num |= num>>>2;
        num |= num>>>4;
        num |= num>>>8;
        num |= num>>>16;

        return (num<0) ? 1:((num>= 1<<30)? 1<<30 : num+1) ;
    }

    @Override
    public V put(K k, V v) {
        if(table.length==0 || useSize > defalutLength * defalutAddSizeFactor){
            up2Size() ;
        }

        int index=getIndex(k,table.length) ;
        Entry<K,V> entry =table[index] ;
        Entry<K,V> newEntry =new Entry<K, V>(k, v, null) ;

        if(entry == null){
            table[index] = newEntry ;
            useSize++ ;
        }else{
            Entry<K,V> tmp;
            while((tmp=table[index])!=null){
                tmp=tmp.next ;
            }
            tmp.next = newEntry ;
        }
        return newEntry.getValue() ;
    }

    @Override
    public V get(K k) {
        int index=getIndex(k,table.length) ;
        Entry<K,V> entry =table[index] ;

        for (Entry<K,V> e = table[index];
             e != null;
             e = e.next) {
            Object kk;
            if (e.getKey() == k || e.getKey().equals(k))
                return e.getValue();
        }
        return null;
    }

    //扩展数组长度 ；
    public void up2Size(){
        Entry<K,V>[] newTable = new Entry[defalutLength*2] ;

        ArrayList<Entry<K,V>> entryList = new ArrayList<Entry<K,V>>() ;
        for(int i=0;i<table.length;i++){
            if(table[i] == null){
                continue ;
            }
            //查找是否形成链表
            findEntryByNext(table[i], entryList) ;
        }
        if(entryList.size() >0){
            useSize =0;
            defalutLength = defalutLength*2 ;
            table=newTable ;
            for(Entry<K,V> entry : entryList){
                if(entry.next != null){
                    entry.next = null ;
                }
                put(entry.getKey(), entry.getValue()) ;
            }
        }else{
            table = new Entry[defalutLength] ;
        }

    }

    public void findEntryByNext(Entry<K, V> entry, ArrayList<Entry<K, V>> entryList){
        if(entry!= null && entry.next != null){
            entryList.add(entry) ;
            findEntryByNext(entry.next, entryList) ;
        }else
            entryList.add(entry) ;
    }

    private int getIndex(K k,int length ){
        int m=length-1 ;
        int hashCode = k.hashCode() ;
        hashCode = hashCode^((hashCode>>>7)^(hashCode>>>12)) ;
        hashCode = hashCode^(hashCode>>>7)^(hashCode>>>4) ;

        int index = hashCode & m ;

        return index >=0 ?index :-index ;
    }

    private class Entry<K,V> implements DiyMap.Entry<K,V>{
        Entry<K,V> next = null;
        K k;
        V v;
        public Entry(K k,V v,Entry<K,V> next){
            this.k = k;
            this.v = v;
            this.next = next ;
        }
        @Override
        public K getKey() {
            // TODO Auto-generated method stub
            return k;
        }

        @Override
        public V getValue() {
            // TODO Auto-generated method stub
            return v;
        }


    }
}
