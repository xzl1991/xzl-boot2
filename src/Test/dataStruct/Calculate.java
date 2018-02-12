package dataStruct;

import java.util.Stack;

/**
 * @auther xzl on 16:05 2018/1/17
 */
public class Calculate {
    public static void main(String[] args) {
        String[] par = new String[]{"4", "13", "5", "/", "+"};
        System.out.println("结果是:"+evalRPN(par));
//        par = new String[]{"2", "1", "+", "3", "*"};
        par = new String[]{"3","-4","+"};
        System.out.println("结果是:"+evalRPN(par));
    }
    public static int evalRPN(String[] tokens) {
        Stack<Integer> vStack = new Stack();
        for(int i=0;i<tokens.length;i++){
            try {
                vStack.push(Integer.parseInt(tokens[i]));
            }catch (Exception e){
                int tempValue = 0;
                switch (tokens[i]){
                    case "+": vStack.push(vStack.pop() + vStack.pop());break;
                    case "-": tempValue= vStack.pop();vStack.push(vStack.pop() - tempValue);break;
                    case "*": vStack.push(vStack.pop() * vStack.pop());break;
                    case "/":tempValue= vStack.pop();vStack.push(vStack.pop() / tempValue);break;
                    default:return 0;
                }
            }
        }
        return  vStack.peek();
    }
}
