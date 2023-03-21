package com.dong;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {


    public static String getMaxSameString(String str1, String str2) {
        if (str1 != null && str2 != null) {
            String maxString = (str1.length() >= str2.length()) ? str1 : str2;
            String minString = (str1.length() < str2.length()) ? str1 : str2;

            int length = minString.length();
            for (int i = 0; i < length; i++) {
                for (int x = 0, y = length - i; y <= length; x++, y++) {//此处选length是因为左闭右开
                    String subStr = minString.substring(x, y);
                    if (maxString.contains(subStr)) {
                        return subStr;
                    }
                }
            }
        }
        return null;
    }

    public static String test111(String s,List<String> list){
        String ss = list.stream().filter(e-> StringUtils.isNotBlank(getMaxSameString(s,e))).findFirst().orElse(null);
        return StringUtils.isNotBlank(ss)?getMaxSameString(ss,s):null;
    }

    public static String test11(String s,List<List<String>> list,int num ){
        if(num==list.size()){
            System.out.println("=========>>"+s);
            return s;
        }
        for(int i = num;i<list.size();i++){
            for(int j=0;j<list.get(i).size();j++){
                return test111(s,list.get(i));
            }
        }
        return null;
    }
    //获取list之间的交集
    public static boolean getIntersectionStr(List<List<String>> list ){
        List<String> firtList = list.get(0);
        for(int j=0;j<firtList.size();j++){
            String fistStr = firtList.get(j);
            for(int i=1;i<list.size();i++){
                for(int ii=0;ii<list.get(i).size();ii++){
                    String sf = getMaxSameString(fistStr,list.get(i).get(ii));
                    if(StringUtils.isNotBlank(sf)){
                        String sd = test11(sf,list,i+1);
                        if(StringUtils.isNotBlank(sd)){
                            System.out.println("---->"+sd);
                            return true;
                        }else {
                            continue;
                        }
                    }else {
                        fistStr = null;
                        break;
                    }
                }
                if(StringUtils.isBlank(fistStr)){
                    break;

                }
            }
            if(StringUtils.isNotBlank(fistStr)){
                return true;
            }

        }
        return true;
    }


    public static  boolean getIntersectionStr2(List<List<String>> list){
        List<String> firtList = list.get(0);
        for (int i=0;i<firtList.size();i++) {
           String res =  getIntersectionStr2Iner(firtList.get(i),list,1);
           if(StringUtils.isNotBlank(res)){
               return true;
           }
        }
        return false;

    }
    public static String getIntersectionStr2Iner(String str ,List<List<String>> list,int num ){
        if(num == list.size()){
            return str;
        }
        for (int index = num;index<list.size();index++){
            List<String> twoList = list.get(num);
            String twoStr = new String(str);
            for (int i=0;i<twoList.size();i++) {
                String maxSameString = getMaxSameString(twoStr, twoList.get(i));
                if(StringUtils.isNotBlank(maxSameString)){
                    String res =  getIntersectionStr2Iner(maxSameString,list,num+1);
                    if(StringUtils.isNotBlank(res)){
                        return res;
                    }
                }else {
                    continue;
                }
            }
        }
        return null;

    }

    public static long getDValue2Day(Date start, Date end) {
        long dValue = 0;
        try {
            long l = end.getTime() - start.getTime();
            dValue = l / (24 * 60 * 60 * 1000);
        } catch (Exception e) {
        }
        return dValue;
    }

    @SneakyThrows
    public static void main(String[] args) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(getDValue2Day(format.parse("2022-01-02"),format.parse("2022-01-02")));

        List<String> set1 = Lists.newArrayList("ac","hc","cde","deff");
        List<String> set2 = Lists.newArrayList("ec","ae","ab","edde");
        List<String> set3 = Lists.newArrayList("gd","bb","ded","f");

        List<List<String>> tlist = Lists.newArrayList(set1,set2,set3);
        System.out.println("111------->  "+getIntersectionStr(tlist));
        System.out.println("222------->  "+getIntersectionStr2(tlist));




        int i=10,j=10;
        for(;i>0;i--){
            System.out.println("i:"+i);
            for (j=10;j>0;j--){
                System.out.println(i+"======"+j);
                if(j==3)break;
            }
        }
        System.out.println();
        System.out.println();
        System.out.println();
        String s = new String("早上");
        System.out.println(s.hashCode());
        System.out.println(s.length());

        int num =10;
        System.out.println(num+(num>>1));

        HashMap<String,String> map = new HashMap<>();
        map.put("","");
        map.get("");
        ConcurrentHashMap<String,String> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put("","");
        concurrentHashMap.size();

        //最大数
//        System.out.println(largestNumber(new int[]{9,12,13,2}));
//        System.out.println(largestNumber(new int[]{1,0,0,0}));
//        int[] ints = prisonAfterNDays(new int[]{0, 1, 0, 1, 1, 0, 0, 1}, 0);
//        System.out.println(JSONObject.toJSONString(ints));
//        //n*m矩阵
//        int matrix[][] = {{1,2,3},{4,5,6},{7,8,9}};
//        List<Integer> printjz = printjz(matrix);
//        System.out.println(JSONObject.toJSONString(printjz));
        //链表合并
//        Node mergelinked = Mergelinked(new Node().setValue(1).setNext(new Node().setValue(3).setNext(new Node().setValue(9))),
//                new Node().setValue(2).setNext(new Node().setValue(3).setNext(new Node().setValue(22))));
//        Node head = mergelinked;
//        while (head!=null){
//            System.out.print(" ->"+head.getValue());
//            head =  head.getNext();
//        }
    }

    public static Node Mergelinked(Node l,Node r){
        if(l==null||r==null){
            return l==null?r:l;
        }
        Node head = new Node();
        Node next =null;
        Node ll = l,rr=r;
        while (ll!=null||rr!=null){
            Node temp;
            if(ll!=null&&(
                    rr==null||ll.getValue()<rr.getValue())){
                temp=ll;
                ll = ll.getNext();
            }else {
                temp=rr;
                rr = rr.getNext();
            }
            if(head.getNext()==null){
                head.setNext(temp);
            }else {
                next.setNext(temp);
            }
            next = temp;
        }
        return head.getNext();
    }

    public static String largestNumber(int[] nums) {

        return String.join("",IntStream.of(nums).mapToObj(a->String.valueOf(a)).sorted((a,b)->(b+a).compareTo(a+b)).collect(Collectors.toList())).replaceAll("^0+$","0");
        /*int n = nums.length;
        String[] ss = new String[n];
        for (int i = 0; i < n; i++) ss[i] = "" + nums[i];
        Arrays.sort(ss, (a, b) -> {
            String sa = a + b, sb = b + a ;
            return sb.compareTo(sa);
        });

        StringBuilder sb = new StringBuilder();
        for (String s : ss) sb.append(s);
        int len = sb.length();
        int k = 0;
        while (k < len - 1 && sb.charAt(k) == '0') k++;
        return sb.substring(k);*/
    }

    //N*N矩阵和顺时针打印N*M
    public static List<Integer> printjz(int matrix[][]){
//        int matrix[][] = {{},{}};
        List<Integer> order = new ArrayList<Integer>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return order;
        }
        int rows = matrix.length, columns = matrix[0].length;
        int left = 0, right = columns - 1, top = 0, bottom = rows - 1;

        while (left<=right&&top<=bottom){
            for (int column=left;column<=right;column++){
                order.add(matrix[top][column]);
            }
            for(int row=top+1;row<=bottom;row++){
                order.add(matrix[row][right]);
            }
            if(left<right&&top<bottom){
                for (int column=right-1;column>=left;column--){
                    order.add(matrix[bottom][column]);
                }
                for(int row=bottom-1;row>top;row--){
                    order.add(matrix[row][left]);
                }
            }

            left++;
            right--;
            top++;
            bottom--;
        }


        return order;
    }

    /**
     *
     * @param nums
     * @param flag true 正序；false 倒叙
     */
    public static void printnums(int nums[],int start,int end,boolean flag){
        if(flag){
            for(int i=start;i<end;i++){
                System.out.print(nums[i-1]);
            }
        }else {
            for(int i=end;i>start;i--){
                System.out.print(nums[i-1]);
            }
        }
    }

    public static int[] prisonAfterNDays(int[] cells, int N) {
        // 保存状态的键值对 ：<state, 剩余天数>
        HashMap<Integer, Integer> seen = new HashMap();

        // 状态state，逐位赋初值
        int state = 0;
        for (int i = 0; i < 8; ++i) {
            if (cells[i] > 0)
                state ^= 1 << i;
        }

        // 还有剩余天数，模拟一天
        while (N > 0) {
            // 当前状态出现过，周期t = seen.get(state) - N
            if (seen.containsKey(state)) {
                //更新N, N %= t ;
                N %= seen.get(state) - N;
            }
            // 当前状态没出现过，将其存入保存状态的键值对，天数N--,更新状态
            seen.put(state, N);
            if (N >= 1) {
                N--;
                state = nextDay(state);
            }
        }

        // Convert the state back to the required answer.
        int[] ans = new int[8];
        for (int i = 0; i < 8; ++i) {
            if (((state >> i) & 1) > 0) {
                ans[i] = 1;
            }
        }

        return ans;
    }

    public static int nextDay(int state) {
        int ans = 0;

        // We only loop from 1 to 6 because 0 and 7 are impossible,
        // as those cells only have one neighbor.
        // 根据规则更新状态
        for (int i = 1; i <= 6; ++i) {
            if (((state >> (i - 1)) & 1) == ((state >> (i + 1)) & 1)) {
                ans ^= 1 << i;
            }
        }

        return ans;
    }


    public static void problem1(int[] arr) {
        int res = 0;
        for (int value : arr) {
            res ^= value;
        }
        System.out.println(res);
    }

}
