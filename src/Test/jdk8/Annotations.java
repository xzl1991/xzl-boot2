package jdk8;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * @auther xzl on 18:00 2018/1/30
 * @Description: 新增类型注解:ElementType.TYPE_USE 和ElementType.TYPE_PARAMETER（在Target上）
 */
public class Annotations {
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.TYPE_USE,ElementType.TYPE_PARAMETER})
    public @interface NonEmpty{
    }
    public static class Holder<@NonEmpty Integer> extends @NonEmpty Object{
        public void method() throws @NonEmpty Exception{
            System.out.println("内部方法-------");
        }
    }

    public static void main(String[] args) {
        final Holder<String> holder = new @NonEmpty Holder<>();
        try {
            holder.method();
        } catch (Exception e) {
            e.printStackTrace();
        }
        @NonEmpty Collection<@NonEmpty String> strings = new ArrayList<>();
    }
}











































