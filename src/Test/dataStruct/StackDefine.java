package dataStruct;

import java.util.Stack;

/**
 * Created by xzl on 2017/12/11.
 *
 * @author xzl
 * @date 2017/12/11  15:02.
 */
public class StackDefine<E> {
    //存储数据
    protected Object[] elementData = new Object[10];
    protected int elementCount;
    public StackDefine(){
    }
    public void push(Object obj){
        elementData[elementCount++] = obj;
    }
    public E pop(){
        if(elementCount==0){
            return null;  //如果栈长为0，返回空
        }
        E e = peek();
      //删除元素
      int len = elementCount;
      removeElementAt(len - 1);
      return  e;
    }
    public E peek(){
        return (E) elementData[elementCount-1];
    }
    public int size(){
        return  elementCount;
    }
    private void removeElementAt(int index){
        if (index >= elementCount) {
            throw new ArrayIndexOutOfBoundsException(index + " >= " +
                    elementCount);
        }
        else if (index < 0) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        int j = elementCount - index - 1;
        if (j > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, j);
        }
        elementCount--;
        elementData[elementCount] = null; /* to let gc do its work */
    }

    public static void main(String[] args) {
        //使用字符类Character声明泛型
        StackDefine<Character> ms=new StackDefine<Character>();
        String s="{{}{}}";//{{}}()(){({){}}}
        char[] a=s.toCharArray();
        for(int i=0;i<a.length;i++){
            Character t=ms.pop();
            //每次会先出栈当前的栈顶元素
            if(t==null){
                ms.push(a[i]);
            }
            else if(t=='{'&&a[i]=='}'){
                //如果匹配，就不进行动作处理，栈顶元素已经出栈
            }else if(t=='('&&a[i]==')'){

            }else if(t=='['&&a[i]==']'){

            }
            else{
                //不匹配的话，将栈顶元素和新元素一起出栈
                ms.push(t);
                ms.push(a[i]);
            }
        }
        if(ms.size()==0){
            System.out.println("YES");
        }else{
            System.out.println("NO");
        }
    }
}
