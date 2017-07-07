package Test.ClassLoader;

/**
 * Created by ${xzl} on 2017/6/29.
 */
public class HelloBean {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString(){
        return "Hello [name=" + name + ", age=" + age + "]";
    }
    public  void hello(){
        System.out.println("Hello ClassLoader.....");
    }
}
