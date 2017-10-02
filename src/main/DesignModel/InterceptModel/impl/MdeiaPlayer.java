package main.DesignModel.InterceptModel.impl;

import main.DesignModel.InterceptModel.AbstractPlayer;
import main.DesignModel.InterceptModel.MediaAbstract;

/**
 * Created by ${xzl} on 2017/9/15.

    <p>中介者</p>
 */
public class MdeiaPlayer extends MediaAbstract {
    public MdeiaPlayer(AbstractPlayer a, AbstractPlayer b) {
        super(a, b);
    }

    @Override
    public void AWin(int count) {
        playerA.setMyScore(playerA.getMyScore()+count);
        playerB.setMyScore(playerB.getMyScore()-count);
    }

    @Override
    public void BWin(int count) {
        playerA.setMyScore(playerA.getMyScore()-count);
        playerB.setMyScore(playerB.getMyScore()+count);
    }
}
