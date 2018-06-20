package DesignModel.InterceptModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by ${xzl} on 2017/9/15.
 *
 * <p>玩具抽象类</p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
//@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractPlayer {
    protected int myScore ;
    protected String name;
    protected  AbstractPlayer(int  myScore,String playerName){
        this.myScore = myScore;
        this.name = playerName;
    }
    protected    abstract  void updateScore(int score,MediaAbstract mediaAbstract);
    protected    abstract  String playerInfo();


}
