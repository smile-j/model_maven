package com.dong;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
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
