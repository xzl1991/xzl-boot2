package DesignModel.ObserveModel.interfces;

import DesignModel.ObserveModel.model.People;

/**
 * Created by ${xzl} on 2017/8/29.
 */
public interface MyInterface {
    void uploadNew(People people);//上传新视频
    void notifyFans();//通知订阅者

    void addOrDel(People people);//管理粉丝
}
