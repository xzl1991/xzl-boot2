package main.Controller;

import main.beans.Columns;
import main.beans.Panel;
import main.beans.ViewConfig;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by ${xzl} on 2017/7/5.
 */
public class TestPanel {
    public static void main(String[] args) {
        Panel panel = new Panel();
        panel.setId("testPanelId");
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
        Object[] fields =  panelClass.getDeclaredFields();
        try {
            for (Object field: fields) {
                Class type = ((Field)field).getType();
                stringBuilder.append(((Field)field).getName());
                ((Field)field).setAccessible(true);
                Object value = ((Field)field).get(panel);
                if(type.isMemberClass()||isCommon((Field) field)){//基本类型
                    appendTo((Field) field,panel,stringBuilder);
                }else {
                    //已经实例化
                    String classNname = ((Field) field).getType().getName();//Class.forName(panel.getClass().getName()).getDeclaredClasses()
                    ViewConfig viewConfig = null;//(ViewConfig) field;
                  Class<?> classname = Class.forName(classNname);//((Field) field).getType().getSimpleName();
                  Object obj = classname.newInstance();
                  if(obj instanceof Columns){
                      Class[] columnClass = new Class[]{Class.class};
                      Method method1 = obj.getClass().getDeclaredMethod("getChild",columnClass);
                      method1.setAccessible(true);
                      method1.invoke(obj,null);
                      System.out.println("----Column--"+obj);
                  }
                  Field[]  fields1 =  ((Field) field).getType().getDeclaredFields();

                  stringBuilder.append(":{\n");
                  String valueFiled = null;
                  for(Field field1:fields1){
                      field1.setAccessible(true);
                      stringBuilder.append("\t\t").append(field1.getName());
                      appendTo(field1,obj,stringBuilder);
                  }
                  stringBuilder.append("},\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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
}
