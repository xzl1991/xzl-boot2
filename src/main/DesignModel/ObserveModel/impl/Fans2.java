package DesignModel.ObserveModel.impl;

import DesignModel.ObserveModel.interfces.FansInterface;
import DesignModel.ObserveModel.interfces.MyInterface;
import DesignModel.ObserveModel.model.People;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by ${xzl} on 2017/8/29.
 */
public class Fans2 extends People implements FansInterface{
    @Override
    public void watchVideo(People uploader) {
        System.out.println("我要看的视频");
    }
    public void subscibeVideo(People uploader){
//        myInterface.uploadNew();
    }
}
