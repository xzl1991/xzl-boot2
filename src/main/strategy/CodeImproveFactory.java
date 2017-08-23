package strategy;

import java.io.File;
import java.io.FileFilter;
import java.lang.annotation.Annotation;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


/**
 * 策略工厂  简单工厂
 * */
public class CodeImproveFactory {
	private final static String packageName = "strategy.impl";
	private ClassLoader classLoader = getClass().getClassLoader();//类加载器
	private List<Class<? extends MyInterface>> insertCodeList;//策略集合
	private CodeImproveFactory(){init();}
	
	private void init() {
		insertCodeList = new ArrayList<>();
		File[] resources = getResources();//获取到包下所有的class文件
		Class<MyInterface> myInterfaceClass = null;
		try {
			//使用相同的加载器加载策略接口
			myInterfaceClass = (Class<MyInterface>) classLoader.loadClass(MyInterface.class.getName());
		    for (int i = 0; i < resources.length; i++) {
	            try {
	                //载入包下的类
	                Class<?> clazz = classLoader.loadClass(packageName + "."+resources[i].getName().replace(".class", ""));
	                //判断是否是MyInterface的实现类并且不是MyInterface它本身，满足的话加入到策略列表
	                if (MyInterface.class.isAssignableFrom(clazz) && clazz != myInterfaceClass) {
	                	insertCodeList.add((Class<? extends MyInterface>) clazz);
	                }
	            } catch (ClassNotFoundException e) {
	                e.printStackTrace();
	            }
	        }
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("获取策略失败");
		}
		
	}

	private File[] getResources() {
		try {
			File files = new File(classLoader.getResource(packageName.replace(".", "/")).toURI());
			return files.listFiles(new FileFilter() {
				
				@Override
				public boolean accept(File pathname) {
				  if (pathname.getName().endsWith(".class")) {//我们只扫描class文件
                        return true;
                    }
                   return false;
				}
			});
		} catch (URISyntaxException e) {
			e.printStackTrace();
			throw new RuntimeException("未找到策略资源");
		}
	}
	
	public static CodeImproveFactory getInstance(){
        return CodeImproveFactoryInstance.instance;
    }
    
    private static class CodeImproveFactoryInstance{
        
        private static CodeImproveFactory instance = new CodeImproveFactory();
    }
	//根据输入产生相应的策略
	public  MyInterface createMyInterface(MyClass myClass){
		NumRegion numRegion = null;
		for(Class<? extends MyInterface> clazz:insertCodeList){
			//获取注解
			numRegion = handleAnnotation(clazz);//获取该策略的注解
			
			  //判断数字是否在注解的区间
            if (myClass.getMethodNum() > numRegion.min() && myClass.getMethodNum() <= numRegion.max()) {
                try {
                    //是的话我们返回一个当前策略的实例
                    return clazz.newInstance();
                } catch (Exception e) {
                    throw new RuntimeException("策略获得失败");
                } 
            }
		}
		 throw new RuntimeException("策略获得失败");
	}
	//获取注解值
	private NumRegion handleAnnotation(Class<? extends MyInterface> clazz) {
		 Annotation[] annotations = clazz.getDeclaredAnnotations();
		 if(annotations==null||annotations.length<1){
			 return null;
		 }
		 for (int i = 0; i < annotations.length; i++) {
            if (annotations[i] instanceof NumRegion) {
                return (NumRegion) annotations[i];
            }
         }
        return null;
	}
}	



























