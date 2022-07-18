package com.dong;

import org.apache.commons.lang3.StringUtils;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {


    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        String str = in.nextLine();
//        String str="3[a]2[bc]";
        String str="3[a2[c]]";//accaccacc

        Stack<String> stack = new Stack<>();
        int num =0;
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<str.length();i++) {
            String s = str.substring(i,i+1);
            stack.add(s);

        }
        while (!stack.empty()){
            String s = stack.pop();
            if(StringUtils.isNumeric(s)){
                num = Integer.parseInt(s);
                if(stack.size()!=0){
                    for(int ii=0;ii<num;ii++){
                        stack.add(sb.toString());
                    }
                }else {
                    for(int ii=0;ii<num;ii++){
                        System.out.println(sb.toString());                    }
                }
                sb = new StringBuilder();
            }else if("[".equals(s)||"]".equals(s)){

            }else {
                sb.append(s);
            }
        }



    }



    public static void getPrimer(long num ){
        long count = num/2;
        for(int i=2;i<=num;i++){
            if(num%i==0){
                System.out.print(i+" ");
                getPrimer(num/i);
                break;
            }
            if(i==num){
                System.out.print(i+ " ");
            }
        }


    }

    public static void main1(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        String ss = in.nextLine();
        String [] strs = str.split(" ");
        HashSet<String> set = new HashSet<String>();
        Boolean flag = true;
        for(String s:strs){
            if(s.contains("")){

            }
            if(s.startsWith(ss)){
                flag = false;
                set.add(s);

            }
        }
        if (flag){
            System.out.print(ss);
        }
        set.stream().sorted().forEach(e->{
            System.out.print(e+" ");
        });
    }


    public static void main2(String[] args) {
        Scanner in = new Scanner(System.in);
        String [] a = in.nextLine().split(" ");
        String [] b = in.nextLine().split(" ");
        int height = Integer.parseInt(a[0]);
        ArrayList<String> list = new ArrayList<String>();
        Collections.addAll(list,b);
        list.stream().sorted(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int num1 = Integer.parseInt(o1);
                int num2 = Integer.parseInt(o2);
                return Math.abs(num1-height)-Math.abs(num2-height);
            }
        }).forEach(e->{
            System.out.print(e+" ");
        });


    }

    public static void main3(String[] args) {
        Scanner in = new Scanner(System.in);
        LinkedList<String> list = new LinkedList<>();
        List<String> result = new LinkedList<>();
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String s = in.nextLine();
            if(!s.contains(" ")){
                break;
            }
            list.add(s);

        }
        int num = list.size();
        for(int i=0;i< num-1;i++){
            for (int j=i+1;j<num;j++){
                String [] s1 = list.get(i).split(" ");
                String [] s2 = list.get(j).split(" ");
                int a1 = Integer.parseInt(s1[0]);
                int a2 = Integer.parseInt(s1[1]);
                int a3 = Integer.parseInt(s2[0]);
                int a4 = Integer.parseInt(s2[1]);
                StringBuilder sb = new StringBuilder();
                if(a2>=a3){
                    if(a1>a3){
                        sb.append(a1);
                    }else {
                        sb.append(a3);
                    }
                    if(a2>a4){
                        sb.append(" "+a4);
                    }else {
                        sb.append(" "+a2);
                    }
                    result.add(sb.toString());
                    result = result.stream().distinct().collect(Collectors.toList());
                }else {
                    break;
                }
            }
        }
        result = result.stream().sorted().distinct().collect(Collectors.toList());
        boolean flag = true;
        while (true){
            for(int j=result.size()-1,i=0;j>=0&&i<result.size();j--,i++){
                if(j-1>=0){
                    String str1 = result.get(i);
                    String str2 = result.get(i+1);

                    String [] s1 = str1.split(" ");
                    String [] s2 = str2.split(" ");
                    int a1 = Integer.parseInt(s1[0]);
                    int a2 = Integer.parseInt(s1[1]);
                    int a3 = Integer.parseInt(s2[0]);
                    int a4 = Integer.parseInt(s2[1]);
                    if(a2>=a3){
                        StringBuilder sb = new StringBuilder();
                        sb.append(a1+" "+(a3>a4?a3:a4));
                        result.remove(str1);
                        result.remove(str2);
                        result.add(sb.toString());
                        flag = false;
                        i=-1;
                        result = result.stream().sorted().distinct().collect(Collectors.toList());

                    }
                }
            }
            if(flag){
                break;
            }
            flag = true;

        }
        result.forEach(e->{
            System.out.println(e);
        });
        if(result.size()==0){
            System.out.println("None");
        }
    }

}
