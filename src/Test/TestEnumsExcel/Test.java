//package com.gomefinance.ermas;
//
//import com.gomefinance.ermas.common.utils.ExcelUtils;
//import org.apache.commons.beanutils.PropertyUtils;
//import org.apache.poi.xssf.streaming.SXSSFWorkbook;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.lang.reflect.Field;
//import java.util.*;
//
///**
// */
////@RunWith(SpringJUnit4ClassRunner.class)
////@SpringBootTest(classes = Application.class)
//public class Test {
//
//
//
//
//    public static void main(String[] args)  {
//        Set<Class<?>> classSet =  ClassTools.getClasses("com.xx.xx.common.enums");
//        Class clazz = null;
//        Field[] fields = null;
//        String code = null;
//        Map<String,Object> objectMap = null;
//        Iterator iter = classSet.iterator();
//        Object[] enumConstants = null;
//        String methodName = null;
//        Object value = null;
//        byte[] bytes = null;
//        List<Map<String,Object>> list = new ArrayList<>();
////        List<JsonObject> list = new ArrayList<>();
//        boolean newClazz = false;
//        try {
//            while (iter.hasNext()){
//                clazz = (Class) iter.next();
//                // 1. 读取常量值
//                if(!clazz.isEnum()){
//                    continue;
//                }
//                enumConstants = clazz.getEnumConstants();
//                objectMap = new HashMap<>();
//                for (Object ec : enumConstants) { //如果枚举继承了同一个类的话不用这么麻烦
//                    fields = ec.getClass().getDeclaredFields();
////                    value = PropertyUtils.getProperty(ec, methodName);
//                    code = ec.toString();
//                    if(methodName==null){//获取声明的field
//                        for(Field field : fields){
//                            if(!field.isEnumConstant()&&field.getType().isAssignableFrom(String.class)){//非常量值---
//                                methodName = field.getName();
//                                break;
//                            }
//                        }
//                    }
//                    if(methodName!=null){
//                        value = PropertyUtils.getProperty(ec, methodName);//取值
//                        if(newClazz){
//                            methodName = null;
//                        }
//                    }
//                    if (code != null) {
//                        objectMap.put(code,value);//.addProperty(code, value.toString());
//                    }
////                    for(Field field : fields){
////                        if(!field.isEnumConstant()&&field.getType().isAssignableFrom(String.class)){//非常量值---
////                             methodName = field.getName();
////                        }else {
////                            code = field.getName();//常量值
////                        }
////                        field.setAccessible(true);
////                        if(methodName!=null){
////                            value = PropertyUtils.getProperty(ec, methodName);//取值
////                            if(newClazz){
////                                methodName = null;
////                            }
////                        }
////                        if (code != null) {
////                            jsonObject.addProperty(code, value.toString());
////                        }
////                    }
//
//                }
//                list.add(objectMap);
//                newClazz = true;
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        System.out.println(list.size());
//        createExcel(list);
//    }
//    private static  final List<String > list=new ArrayList<>();
//
//    public static void scan(String packageName){
//        String parent=	System.getProperty("user.dir");
//        parent=parent+"/src/main/java";
//        System.out.println(parent);
//        String child=packageName.replaceAll("\\.", "/");
//        File f=new File(parent ,child);
//        for(File c:f.listFiles()){
//            if(c.getName().endsWith(".java")){
//                list.add(packageName+"."+c.getName().substring(0,c.getName().lastIndexOf(".")));
//            }
//        }
//
//    };
//    public static void createExcel(List<Map<String,Object>> contents){
//        Map<String, String> titles = new LinkedHashMap<String,String>(){{
//            put("DIC_TYPE","DIC_TYPE");
//            put("DATA_TYPE","DATA_TYPE");
//            put("DIC_KEY","DIC_KEY");
//            put("DIC_VALUE","DIC_VALUE");
//            put("DIC_ORDER","DIC_ORDER");
//            put("DESCRIPTION","DESCRIPTION");
//        }};
//
//        FileOutputStream outputStream = null;
//        try {
//            outputStream = new FileOutputStream("E:/数据字典.xlsx");
//            SXSSFWorkbook wb =  ExcelUtils.mapToExcel(titles,contents);
//                 wb.write(outputStream);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally {
//            try {
//
//                if (outputStream!=null) {
//                    outputStream.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public  void testRWbyIO(String content,String path)  {
//        File file = new File(path);
//        //2.创建一个通道，（如果不存在路径中的文件，则会在这一步创建的文件）
//        //new FileOutputStream(file,true); /true表示在原来文本的基础上写入文本（反之则会先清空再写入）
//        FileOutputStream outputStream = null;
//        byte[] b = null;
//        try {
//            outputStream = new FileOutputStream(file,true);
//            //3.创键一个字节数组
//            //将字符串变为字节数组
//            b = content.getBytes();
//            //4.写入数据
//            outputStream.write(b);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally {
//            try {
//
//                if (outputStream!=null) {
//                    outputStream.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }
//
//    }
//}
