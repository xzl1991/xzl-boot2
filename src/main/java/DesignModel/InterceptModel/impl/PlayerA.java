package DesignModel.InterceptModel.impl;

import DesignModel.InterceptModel.AbstractPlayer;
import DesignModel.InterceptModel.MediaAbstract;

/**
 * Created by ${xzl} on 2017/9/15.
 */
public class PlayerA extends AbstractPlayer{
    public   String title;
    @Override
    protected void updateScore(int score,MediaAbstract mediaAbstract) {
        mediaAbstract.AWin(score);
    }
    public PlayerA(int  myScore,String playerName,String title){
        super(myScore,playerName);
        this.title = title;
    }

    @Override
    protected String playerInfo() {
        return this.name+ ",当前积分:"+this.myScore+",头衔:"+this.title;
    }
}
