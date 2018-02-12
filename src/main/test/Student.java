package test;

/**
 * @auther xzl on 11:55 2018/1/30
 */
class Person {
    public String gentle = "Father";
}

public class Student extends Person {

    public String gentle = "Son";

    public String print(){
        return super.gentle;       // 在子类中访问父类中同名成员变
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Student student = new Student();
        System.out.println("##### " + student.gentle);
        Person p = student;
        System.out.println("***** " + p.gentle);    //隐藏：编译时决定，不会发生多态

        System.out.println("----- " + student.print());
        Person p1 = new Student();
        System.out.println("----- " + ((Student)p1).print());    //Error：Person 中未定义该方法
    }
}
