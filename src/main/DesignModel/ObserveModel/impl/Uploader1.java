package DesignModel.ObserveModel.impl;

import DesignModel.ObserveModel.UploaderManager;
import DesignModel.ObserveModel.interfces.FansInterface;
import DesignModel.ObserveModel.interfces.MyInterface;
import DesignModel.ObserveModel.model.People;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ${xzl} on 2017/8/29.
 *
 * 可以和适配器模式组合使用
 */
public class Uploader1 extends People implements MyInterface{
    @Override
    public void addOrDel(People fans) {
        fansList.add(fans);
    }

    private List<People> fansList = new ArrayList<>();
    @Override
    public void notifyFans() {
        for( People fans : fansList){
            System.out.println("通知粉丝:"+fans.getName()+",赶快观看---主播-"+this.getName()+".当前金币："+this.getCone()+"  快来加油吧·，·");
            fans.temp(20);
            this.setCone(this.getCone()+20);
        }
    }

    @Override
    public void uploadNew(People fans) {
       // fansList.add(fans);
        System.out.println("Up 主"+this.getName()+": 上传视频-----");
        notifyFans();
    }
}
