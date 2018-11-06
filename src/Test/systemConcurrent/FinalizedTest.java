package systemConcurrent;

/**
 * @auther xzl on 11:23 2018/6/26
 */
class C {
    static A a;
}

class A {
    B b;

    public A(B b) {
        this.b = b;
    }

    @Override
    public void finalize() {
        System.out.println("A finalize");
        C.a = this;
    }
}

class B {
    String name;
    int age;

    public B(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public void finalize() {
        System.out.println("B finalize");
    }

    @Override
    public String toString() {
        return name + " is " + age;
    }
}

public class FinalizedTest {
    public static void main(String[] args) throws Exception {
        A a = new A(new B("allen", 20));
        a = null;

        System.gc();
        Thread.sleep(1000);
//        a.finalize();
        System.out.println(C.a.b);
    }
}
