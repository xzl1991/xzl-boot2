package main.DesignModel.InterceptModel.impl;

import main.DesignModel.InterceptModel.AbstractPlayer;
import main.DesignModel.InterceptModel.MediaAbstract;

/**
 * Created by ${xzl} on 2017/9/15.
 */
public class PlayerB extends AbstractPlayer{
    public  String title;
    @Override
    protected void updateScore(int score, MediaAbstract mediaAbstract) {
        mediaAbstract.BWin(score);
    }
    public PlayerB(int  myScore, String playerName, String title){
        super(myScore,playerName);
        this.title = title;
    }

    @Override
    protected String playerInfo() {
        return this.name+ ",当前积分:"+this.myScore+",头衔:"+this.title;
    }
}
