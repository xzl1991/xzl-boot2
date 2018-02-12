package jdk8;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.time.*;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * @auther xzl on 14:15 2018/1/30
 */
public class TestLambda {
    public List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    public static void main(String[] args) {
        TestLambda testLambda = new TestLambda();
        testLambda.testLambda();
    }

    /**
     * 13      * 1.Lambda表达式
     * 14
     */
    @Test
    public void testLambda() {
        list.forEach(System.out::println);
        list.forEach(e -> System.out.println("方式二：" + e));
    }

    /**
     * 2.Stream函数式操作流元素集合
     */
    @Test
    public void testStream() {
        List<Integer> list = Lists.newArrayList(1, 1, 2, null, 3, 4, null, 5);
        System.out.println("List的大小:" + list.size());
        System.out.println("求和：" +
                list.stream()//转成Stream
                        .filter(team -> team != null)//过滤
//                       .peek(System.out::println)
                        .distinct()//去重
                        .mapToInt(num -> num * 2)//map操作
                        .skip(2)//跳过前2个元素
                        .limit(4)//限制取前4个元素
                        .peek(System.out::println)//流式处理对象函数
                        .peek(e -> System.out.println("方式二：" + e))//流式处理对象函数
                        .sum());//
    }

    /**
     * 3.接口新增：默认方法与静态方法
     * default 接口默认实现方法是为了让集合类默认实现这些函数式处理，而不用修改现有代码
     * （List继承于Iterable<T>，接口默认方法不必须实现default forEach方法）
     */
    @Test
    public void testDefaultFunctionInterface() {
        //可以直接使用接口名.静态方法来访问接口中的静态方法
        JDK8Interface1.staticMethod();
        JDK8Interface1 interface1 = new InterfaceImpl();
        interface1.testDefault();
        System.out.println(interface1.testDefaultString());

        interface1 = new InterfaceImpl_1();
        interface1.testDefault();
        System.out.println(interface1.testDefaultString());
    }

    /**
     * 4.方法引用,与Lambda表达式联合使用
     */
    @Test
    public void testMethodReference() {
        //构造器引用。语法是Class::new，或者更一般的Class< T >::new，要求构造器方法是没有参数；
        final Car car = Car.create(Car::new);
        final List<Car> cars = Arrays.asList(car);
        //静态方法引用。语法是Class::static_method，要求接受一个Class类型的参数；
        cars.forEach(Car::collied);
        //任意对象的方法引用。它的语法是Class::method。无参，所有元素调用；
        cars.forEach(Car::repair);
        cars.stream().peek(e -> System.out.println("方式二：" + e.toString()));
        //特定对象的方法引用，它的语法是instance::method。有参，在某个对象上调用方法，将列表元素作为参数传入；
        Car car1 = Car.create(Car::new);
        cars.forEach(car1::follow);
        Car car2 = new Car();
        cars.forEach(car2::follow);
         car2 = new Car();
        cars.forEach(car2::follow);

    }
        /**
           * 5.引入重复注解
           * 1.@Repeatable
           * 2.可以不用以前的“注解容器”写法，直接写2次相同注解即可
           * Java 8在编译器层做了优化，相同注解会以集合的方式保存，因此底层的原理并没有变化。
           */
        @Test
        public void RepeatingAnnotations(){
            RepeatingAnnotations.main(null);
        }
        /**
         * 6.类型注解
         * 新增类型注解:ElementType.TYPE_USE 和ElementType.TYPE_PARAMETER（在Target上）
         *
         * 没明白
         * */
        @Test
        public void ElementType(){
            Annotations.main(null);
        }
        /**
         * 7.最新的Date/Time API (JSR 310)
         * */
        @Test
        public void DateTime(){
            //1.clock
            Clock clock = Clock.systemUTC();
            System.out.println(clock.getZone());
            System.out.println(clock.instant());
            System.out.println(clock.millis());
            //2. ISO-8601格式且无时区信息的日期部分
            LocalDate date = LocalDate.now();
            LocalDate dateFromClock = LocalDate.now(clock);
            System.out.println("LocalDate："+date);
            System.out.println("LocalDate："+dateFromClock);
            // 3.ISO-8601格式无时区信息的日期与时间
            LocalDateTime dateTime = LocalDateTime.now();
            LocalDateTime dateTimeFromColck = LocalDateTime.now(clock);
            System.out.println("LocalDateTime："+dateTime);
            System.out.println("LocalDateTime："+dateTimeFromColck);
            // 4.特定时区的日期/时间，
            ZonedDateTime zonedDateTime = ZonedDateTime.now();
            ZonedDateTime zonedDateTimeClock = ZonedDateTime.now(clock);
            final ZonedDateTime zonedDatetimeFromZone = ZonedDateTime.now( ZoneId.of( "America/Los_Angeles" ) );
            System.out.println("特定时区："+zonedDateTime);
            System.out.println("特定时区："+zonedDateTimeClock);
            System.out.println("特定时区："+zonedDatetimeFromZone);
            //5.在秒与纳秒级别上的一段时间
            LocalDateTime time = LocalDateTime.of(2018,4,20,10,8,55,10);
            final LocalDateTime to = LocalDateTime.of( 2018, Month.APRIL, 16, 9, 59, 59 );
            System.out.println(time);
            Duration duration = Duration.between(time,to);
            System.out.println("Duration日期:"+duration.toDays());
        }
        /**
         *  8.新增base64加解密API
         * */
        @Test
        public void testBase64(){
            final String text = "Jdk8-Base64测试";
            String enCode = Base64.getEncoder().encodeToString(text.getBytes(StandardCharsets.UTF_8));
            System.out.println("加密后:"+enCode);
            String deCode = new String(Base64.getDecoder().decode(enCode.getBytes(StandardCharsets.UTF_8)));
            System.out.println("解密后:"+deCode);

        }
        /**
         *  9.数组并行（parallel）操作
         * */
        @Test
        public void testParall(){
            long[] arrayOfLong = new long [ 20000 ];
            //1.给数组随机赋值
            Arrays.parallelSetAll(arrayOfLong,temp-> ThreadLocalRandom.current().nextInt(1000000));
            //2.打印出前10个元素
            Arrays.stream(arrayOfLong).limit(10).forEach(temp->System.out.print(temp +" "));
            List<Integer> list = Lists.newArrayList(1, 1, 2, null, 3, 4, null, 5);
//            list.stream().filter(temp->temp!=null).mapToInt(num -> num ).limit(5).peek(System.out::println).sum();
        }
        /**
         * 10.JVM的PermGen空间被移除：取代它的是Metaspace（JEP 122）元空间
         * */
        @Test
        public void testMetaspace(){

        }
    static class Car {
        public static Car create(final Supplier<Car> supplier) {
            return supplier.get();
        }

        public static void collied(Car car) {
            System.out.println("collied方法:" + car.toString());
        }


        public void repair() {
            System.out.println("任意对象的方法引用 " + this.toString());
        }

        public void follow(final Car car) {
            System.out.println("特定对象的方法引用 " + car.toString());
        }
    }
}

















































