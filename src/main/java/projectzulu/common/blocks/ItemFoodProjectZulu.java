package projectzulu.common.blocks;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.core.DefaultProps;

public class ItemFoodProjectZulu extends ItemFood {
    /** Number of ticks to run while 'EnumAction'ing until result. */
    public final int itemUseDuration;

    /** The amount this food item heals the player. */
    private final int healAmount;
    private final float saturationModifier;

    /** Whether wolves like this food (true for raw and cooked porkchop). */
    private final boolean isWolfsFavoriteMeat;

    /**
     * If this field is true, the food can be consumed even if the player don't need to eat.
     */
    private boolean alwaysEdible;

    /**
     * represents the potion effect that will occurr upon eating this food. Set by setPotionEffect
     */
    private int potionId;

    /** set by setPotionEffect */
    private int potionDuration;

    /** set by setPotionEffect */
    private int potionAmplifier;

    /** probably of the set potion effect occurring */
    private float potionEffectProbability;

    public ItemFoodProjectZulu(int par2, float par3, boolean par4, String name) {
        super(par2, par3, par4);
        itemUseDuration = 32;
        healAmount = par2;
        isWolfsFavoriteMeat = par4;
        saturationModifier = par3;
        setCreativeTab(ProjectZulu_Core.projectZuluCreativeTab);
        setUnlocalizedName(name);
        setTextureName(DefaultProps.blockKey + ":" + name.toLowerCase());
    }

    @Override
    public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        --par1ItemStack.stackSize;
        par3EntityPlayer.getFoodStats().func_151686_a(this, par1ItemStack);
        par2World.playSoundAtEntity(par3EntityPlayer, "random.burp", 0.5F, par2World.rand.nextFloat() * 0.1F + 0.9F);
        this.func_77849_c(par1ItemStack, par2World, par3EntityPlayer);
        return par1ItemStack;
    }

    protected void func_77849_c(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        if (!par2World.isRemote && this.potionId > 0 && par2World.rand.nextFloat() < this.potionEffectProbability) {
            par3EntityPlayer.addPotionEffect(new PotionEffect(this.potionId, this.potionDuration * 20,
                    this.potionAmplifier));
        }
    }

    /**
     * How long it takes to use or consume an item
     */
    @Override
    public int getMaxItemUseDuration(ItemStack par1ItemStack) {
        return 32;
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    @Override
    public EnumAction getItemUseAction(ItemStack par1ItemStack) {
        return EnumAction.eat;
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        if (par3EntityPlayer.canEat(this.alwaysEdible)) {
            par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
        }

        return par1ItemStack;
    }

    @Override
    public int func_150905_g(ItemStack stack) {
        return this.healAmount;
    }

    /**
     * gets the saturationModifier of the ItemFood
     */
    @Override
    public float func_150906_h(ItemStack stack) {
        return this.saturationModifier;
    }

    /**
     * Whether wolves like this food (true for raw and cooked porkchop).
     */
    @Override
    public boolean isWolfsFavoriteMeat() {
        return this.isWolfsFavoriteMeat;
    }

    /**
     * sets a potion effect on the item. Args: int potionId, int duration (will be multiplied by 20), int amplifier,
     * float probability of effect happening
     */
    @Override
    public ItemFoodProjectZulu setPotionEffect(int par1, int par2, int par3, float par4) {
        this.potionId = par1;
        this.potionDuration = par2;
        this.potionAmplifier = par3;
        this.potionEffectProbability = par4;
        return this;
    }

    /**
     * Set the field 'alwaysEdible' to true, and make the food edible even if the player don't need to eat.
     */
    @Override
    public ItemFoodProjectZulu setAlwaysEdible() {
        this.alwaysEdible = true;
        return this;
    }
}
