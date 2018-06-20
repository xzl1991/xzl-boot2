package Controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import beans.Columns;
import beans.Panel;
import beans.ViewConfig;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ${xzl} on 2017/7/5.
 */
public class TestPanel {
    public static void main(String[] args) {
        Panel panel = new Panel();
        List<Columns> columnsList = new ArrayList<>();
        panel.setId("testPanelId");
        Columns columns = null;
        columns = new Columns();
        columns.setHeader("测试");
        columns.setDataIndex("acctNbr");
        columnsList.add(columns);
        columns = new Columns();
        columns.setHeader("姓名");
        columns.setDataIndex("name");
        columnsList.add(columns);
        JSONArray array = new JSONArray();
        JSONObject objs = null;
        objs = new JSONObject();
        objs.put("header","测试");
        objs.put("dataIndex","acctNbr");
        array.add(objs);
        objs = new JSONObject();
        objs.put("header","姓名");
        objs.put("dataIndex","name");
        array.add(objs);
        panel.setColumns(Columns.getChildByJson(array));
//        Object[] fields =  panel.getClass().getDeclaredFields();
       StringBuilder stringBuilder = new StringBuilder(1000);
        stringBuilder.append("return new Ext.grid.Panel({\n");
        getByClass(panel,stringBuilder);
        stringBuilder.append("\n})");
        System.out.println("结果:"+stringBuilder.toString());
    }
    private static void getByClass(Object panel,StringBuilder stringBuilder){
        Class panelClass = panel.getClass();
        Class arrClass[]=panelClass.getDeclaredClasses();
        Method[] methods = panelClass.getMethods();
        String paneName = panelClass.getSimpleName();
        Field[] fields =  panelClass.getDeclaredFields();
        List<Map> columnList = new ArrayList();
        try {
            Type fc = null;
            for (Field field: fields) {
                fc = field.getGenericType(); // 关键的地方，如果是List类型，得到其Generic的类型
                Class type = field.getType();
                stringBuilder.append(field.getName());
                field.setAccessible(true);
                Object value = field.get(panel);
                if(type.isMemberClass()||isCommon( field)){//基本类型
                    appendTo( field,panel,stringBuilder);
                }else {
                    boolean isColumn = false;
                    if(isList(field)){
//                        fc = field.getGenericType(); // 关键的地方，如果是List类型，得到其Generic的类型
                        if (fc == null)
                            continue;
                        if (fc instanceof ParameterizedType) // 【3】如果是泛型参数的类型
                        {
                            ParameterizedType pt = (ParameterizedType) fc;
                            Class genericClazz = (Class) pt
                                    .getActualTypeArguments()[0]; // 【4】
//                            for(Class clzss : genericClazz){
                                if(genericClazz.newInstance() instanceof Columns){
                                    isColumn = true;
                                    getColumn(genericClazz.newInstance(),stringBuilder);
                                }
//                            }
//                            getAllFields(genericClazz);
                        }
                    }else {
                        String classNname = ((Field) field).getType().getName();//Class.forName(panel.getClass().getName()).getDeclaredClasses()
                        ViewConfig viewConfig = null;//(ViewConfig) field;
                        Class<?> classname = Class.forName(classNname);//((Field) field).getType().getSimpleName();
                        Object obj = classname.newInstance();
                        Field[]  fields1 =  ((Field) field).getType().getDeclaredFields();
                        stringBuilder.append(":{\n");
                        String valueFiled = null;
                        for(Field field1:fields1){
                            field1.setAccessible(true);
                            stringBuilder.append("\t\t").append(field1.getName());
                            appendTo(field1,obj,stringBuilder);
                        }
                        stringBuilder.append("},\n");
                        if(isColumn){
                            stringBuilder.append("],\n");
                            isColumn = false;
                        }
                    }
                    //已经实例化
//                    String classNname = ((Field) field).getType().getName();//Class.forName(panel.getClass().getName()).getDeclaredClasses()
//                    ViewConfig viewConfig = null;//(ViewConfig) field;
//                  Class<?> classname = Class.forName(classNname);//((Field) field).getType().getSimpleName();
//                  Object obj = classname.newInstance();


                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void getColumn(Object obj, StringBuilder stringBuilder){
        boolean isColumn = false;
        Field[] fields =  ((Class)obj).getDeclaredFields();
        isColumn = true;
        stringBuilder.append(":{\n");
        String valueFiled = null;
        for(Field field1:fields){
            field1.setAccessible(true);
            stringBuilder.append("\t\t").append(field1.getName());
            try {
                appendTo(field1,obj,stringBuilder);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        stringBuilder.append("},\n");
        if(isColumn){
            stringBuilder.append("],\n");
            isColumn = false;
        }
    }
    private void checChild(Field field,StringBuilder stringBuilder){
        if(((Field) field).getType()== Columns.class){//是否column
            System.out.println("进入column-----");
        }
    }
    private static void appendTo(Field field1,Object obj,StringBuilder stringBuilder) throws Exception{
        String valueFiled = null;
        if(field1.get(obj)!=null){
            valueFiled = field1.get(obj).toString();
            if((field1.getType().isAssignableFrom(boolean.class)||valueFiled.contains("function"))){
                stringBuilder.append(":").append(valueFiled).append(",\n");
            }else if(field1.getName().contains("columns")){
                stringBuilder.append(valueFiled).append(",\n");
            }else {
                stringBuilder.append(":'").append(valueFiled).append("',\n");
            }
        }else {
            stringBuilder.append(":'").append(valueFiled).append("',\n");
        }
    }
    private static String getByChildClass(Object panel,StringBuilder stringBuilder){
        Class panelClass = panel.getClass();
        String paneName = panelClass.getSimpleName();
        Object[] fields =  panelClass.getDeclaredFields();
        try {
            for (Object field: fields) {
                stringBuilder.append(((Field)field).getName());
                ((Field)field).setAccessible(true);

                Object value = ((Field)field).get(panel);
                stringBuilder.append(":'").append(value).append("',");
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return  stringBuilder.toString();
    }
    private static boolean isCommon(Field field){
        if(field.getType().isAssignableFrom(String.class)||
                field.getType().isAssignableFrom(boolean.class)||
                field.getType().isAssignableFrom(Integer.class)){
            return true;
        }
        return false;
    }
    private static boolean isList(Field field){
        return field.getType().isAssignableFrom(List.class);
    }
}
