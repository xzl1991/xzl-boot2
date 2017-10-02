package DesignModel.strategy;

import java.lang.annotation.*;


//这是我们的有效区间注解，可以给策略添加有效区间的设置
@Target(ElementType.TYPE)//表示只能给类添加该注解
@Retention(RetentionPolicy.RUNTIME)//这个必须要将注解保留在运行时
public @interface NumRegion {
	int max() default Integer.MAX_VALUE;
	int min() default Integer.MIN_VALUE;
}
