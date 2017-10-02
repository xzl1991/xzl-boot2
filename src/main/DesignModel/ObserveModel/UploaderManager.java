package DesignModel.ObserveModel;

import DesignModel.ObserveModel.impl.Uploader1;
import DesignModel.ObserveModel.model.People;

import java.util.*;

/**
 * Created by ${xzl} on 2017/8/29.
 */
public class UploaderManager {
    public static Map<String,People> uperMap = new HashMap<>();
    //添加作者
    public void add(People people){
        uperMap.put(people.getName(),people);
    }
    //根据id获取作者
    public  People getPeople(String name){
        return  uperMap.get(name);
    }

    private  UploaderManager(){}
    public static UploaderManager getInstance(){
        return  UploaderManagerInstance.manager;
    }
    private static class UploaderManagerInstance{
        private static UploaderManager manager = new UploaderManager();
    }
}
