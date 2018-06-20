package DesignModel.InterceptModel;

import DesignModel.InterceptModel.impl.MdeiaPlayer;
import DesignModel.InterceptModel.impl.PlayerA;
import DesignModel.InterceptModel.impl.PlayerB;

/**
 * Created by ${xzl} on 2017/9/15.
 *
 * <p>
 * 应用实例： 1、中国加入 WTO 之前是各个国家相互贸易，结构复杂，现在是各个国家通过 WTO 来互相贸易。                       2、机场调度系统。 3、MVC 框架，其中C（控制器）就是 M（模型）和 V（视图）的中介者。
 优点：        1、降低了类的复杂度，将一对多转化成了一对一。 2、各个类之间的解耦。 3、符合迪米特原则。
 缺点：        中介者会庞大，变得复杂难以维护。
 使用场景： 1、系统中对象之间存在比较复杂的引用关系，导致它们之间的依赖关系结构混乱而且难以复用该对象。                   2、想通过一个中间类来封装多个类中的行为，而又不想生成太多的子类。
 注意事项：不应当在职责混乱的时候使用
 *
 * </p>
 */
public class TestMain {
    public static void main(String[] args) {
        AbstractPlayer playerA = new PlayerA(100,"李小龙","黑带8段");
        AbstractPlayer playerB = new PlayerB(80,"泰森","拳王");
        System.out.println(playerA.name+ ",当前积分:"+playerA.myScore+",头衔:"+((PlayerA)playerA).title);
        System.out.println(playerB.name+ ",当前积分:"+playerB.myScore+",头衔:"+((PlayerB)playerB).title);

        MediaAbstract mediaAbstract = new MdeiaPlayer(playerA,playerB);//中介者---裁判
        System.out.println("第一回合:");
        mediaAbstract.AWin(10);
        System.out.println("第一回合结束:");
        System.out.println("      "+playerA.playerInfo());
        System.out.println("      "+playerB.playerInfo());

        mediaAbstract.BWin(30);
        System.out.println("第二回合结束:");
        System.out.println("      "+playerA.playerInfo());
        System.out.println("      "+playerB.playerInfo());
    }
}
