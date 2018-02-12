package qlcoder;

import java.util.Scanner;
import java.util.Stack;

/**
 * @auther xzl on 14:17 2018/1/18
 * 给你一个1->n的排列和一个栈，入栈顺序给定
你要在不打乱入栈顺序的情况下，对数组进行从大到小排序
当无法完全排序时，请输出字典序最大的出栈序列
 */
public class StackTest {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            boolean isFirst = true;
            Stack<Integer> stack = new Stack<>();
            int total = 0;
            int num = 0;
            while (true){
                if (isFirst){
                    total = scanner.nextInt();
                }
                num++;
                isFirst = false;
                int value = scanner.nextInt();
                if(stack.empty()){
                    stack.push(value);
                    continue;
                }
                print1(value,stack,(num==2));
                if (total==stack.size()){
                    System.out.print("结果：");
                    while (!stack.empty()){
                        System.out.print(stack.pop()+" ");
                    }
                    isFirst = true;
                }
            }
        }catch (Exception e){

        }
    }
    public static Stack print1(int v,Stack<Integer> stack,boolean twoParm){
        int temp = stack.pop();
        boolean add = true;
        if (temp>v){
            if (!stack.empty()){
                print1(v,stack,add);
                add = false;
            }
            if (add){
                stack.push(v);
            }
            stack.push(temp);
        }else {
            stack.push(temp);
            stack.push(v);
        }
        return stack;
    }
}
