package qlcoder;

import java.util.Scanner;
import java.util.Stack;

/**
 * @auther xzl on 14:17 2018/1/18
 * 给你一个1->n的排列和一个栈，入栈顺序给定
你要在不打乱入栈顺序的情况下，对数组进行从大到小排序
当无法完全排序时，请输出字典序最大的出栈序列
 */
public class StackTest1 {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            boolean isFirst = true;
            boolean isMax = false;
            Stack<Integer> stack = new Stack<>();
            int total = 0;
            int size = 0;
            while (true){
                if (isFirst){
                    total = scanner.nextInt();
                    size = total;
                }
                isFirst = false;
                int value = scanner.nextInt();
                if(value==total){
                    isMax = true;
                }
                size--;
                if(stack.empty()){
                    stack.push(value);
                    continue;
                }
                print1(value,stack,isMax);
                if (size==0){
                    while (!stack.empty()){
                        System.out.print(stack.pop()+" ");
                    }
                    isFirst = true;
                    isMax = false;
                }
            }
        }catch (Exception e){

        }
    }
    public static Stack print1(int v,Stack<Integer> stack,boolean hasMax){
        int temp = stack.pop();
        if (temp<v){
            stack.push(temp);
            stack.push(v);
        }else {
            if (hasMax){
                System.out.print(temp+" ");
                stack.push(v);
            }else {
                stack.push(temp);
                stack.push(v);
            }
        }
        return stack;
    }
}
