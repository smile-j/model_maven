package com.dong.base.test.collection;



/**
 * Created by Administrator on 2017/12/7.
 */
public interface DiyMap<K,V> {

    public V put(K k, V v);

    public V get(K k);

    public interface Entry<K,V>{
        Entry<?, ?> next = null;
        public K getKey();
        public V getValue();
    }

}
