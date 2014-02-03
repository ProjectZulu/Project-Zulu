package projectzulu.common.potion.effects;

public class PotionBubbling extends PotionZulu {

    protected PotionBubbling(int par1, boolean par2, int par3) {
        super(par1, par2, par3);
    }

    @Override
    public boolean isInstant() {
        return true;
    }
}
