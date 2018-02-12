package qlcoder;

import java.util.Scanner;

/**
 * @auther xzl on 13:35 2018/1/18
 * 有n个地方，编号为1->n，任意两个地方有公交车，
 * 从i到j的票价为(i+j)mod(n+1)，
 * 而且这个票可以用无限次，你要把这些地方全部走一遍，
 * 问最小花费为多少。可以在任意地方开始和结束。
 */
public class Visit {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            while (true){
                //让值优先==n+1
                int value = scanner.nextInt();
                print(value);
            }
        }catch (Exception e){

        }
    }
    public static void print(int num){
        int sum = 0;
        int start = 1;
        int total = num+1;
        sum+=((start+num)%total);
        StringBuffer sb = new StringBuffer();
        sb.append(start).append("—>").append(num).append("—>");
        for(;start+1<num;){
            sum+=((++start+num)%total);
            sb.append(start).append("—>");
            sum+=((start+--num)%total);
            sb.append(num).append("—>");
        }
//        System.out.println("结果:"+sb.toString());
        System.out.println(sum);
    }
}
