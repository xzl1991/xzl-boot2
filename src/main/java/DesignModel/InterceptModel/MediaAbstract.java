package DesignModel.InterceptModel;

/**
 * Created by ${xzl} on 2017/9/15.
 *
 * <p>中介者抽象类</p>
 */
public abstract class MediaAbstract {
    protected AbstractPlayer playerA;
    protected AbstractPlayer playerB;
    public MediaAbstract(AbstractPlayer a, AbstractPlayer b)
    {
        playerA = a;
        playerB = b;
    }

    public abstract void AWin(int count);
    public abstract void BWin(int count);
}
