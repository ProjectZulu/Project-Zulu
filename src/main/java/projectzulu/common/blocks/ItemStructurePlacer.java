package projectzulu.common.blocks;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.core.terrain.TerrainFeature;
import projectzulu.common.core.terrain.TerrainFeature.FeatureDirection;
import projectzulu.common.world.terrain.CathedralFeature;
import projectzulu.common.world.terrain.CemetaryFeature;
import projectzulu.common.world.terrain.LabyrinthFeature;
import projectzulu.common.world.terrain.OasisFeature;
import projectzulu.common.world.terrain.PyramidFeature;

public class ItemStructurePlacer extends Item {

    String[] structureName = new String[] { "Oasis", "Pyramid", "Labyrinth", "Cemetary", "Cathedral" };

    public ItemStructurePlacer(String baseName) {
        super();
        setHasSubtypes(true);
        this.setCreativeTab(ProjectZulu_Core.projectZuluCreativeTab);
        setUnlocalizedName(baseName);
        setTextureName("paper");
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4) {
        if (!par2World.isRemote) {
            int buildingID = par1ItemStack.getItemDamage();
            int Xcoord = (int) par3EntityPlayer.posX;
            int Zcoord = (int) par3EntityPlayer.posZ;
            int Ycoord = ((int) par3EntityPlayer.posY) - 1;

            switch (buildingID) {
            case 0: {
                /* Oasis Generation */
                TerrainFeature terrainFeature = ProjectZulu_Core.featureGenerator
                        .getRegisteredStructure(OasisFeature.OASIS);
                if (terrainFeature != null) {
                    terrainFeature.generateFeature(par2World, Xcoord / 16, Zcoord / 16, new ChunkCoordinates(Xcoord,
                            Ycoord + 1, Zcoord), par2World.rand, calculateFeatureDirection(par3EntityPlayer));
                }
                break;
            }
            case 1: {
                /* Pyramid Generation */
                TerrainFeature terrainFeature = ProjectZulu_Core.featureGenerator
                        .getRegisteredStructure(PyramidFeature.PYRAMID);
                if (terrainFeature != null) {
                    terrainFeature.generateFeature(par2World, Xcoord / 16, Zcoord / 16, new ChunkCoordinates(Xcoord,
                            Ycoord + 1, Zcoord), par2World.rand, calculateFeatureDirection(par3EntityPlayer));
                }
                break;
            }
            case 2: {
                /* Labyrinth Generation */
                TerrainFeature terrainFeature = ProjectZulu_Core.featureGenerator
                        .getRegisteredStructure(LabyrinthFeature.LABYRINTH);
                if (terrainFeature != null) {
                    terrainFeature.generateFeature(par2World, Xcoord / 16, Zcoord / 16, new ChunkCoordinates(Xcoord,
                            Ycoord + 1, Zcoord), par2World.rand, calculateFeatureDirection(par3EntityPlayer));
                }
                break;
            }
            case 3: {
                /* Cemetary Generation */
                TerrainFeature terrainFeature = ProjectZulu_Core.featureGenerator
                        .getRegisteredStructure(CemetaryFeature.CEMETARY);
                if (terrainFeature != null) {
                    terrainFeature.generateFeature(par2World, Xcoord / 16, Zcoord / 16, new ChunkCoordinates(Xcoord,
                            Ycoord + 1, Zcoord), par2World.rand, calculateFeatureDirection(par3EntityPlayer));
                }
                break;
            }
            case 4: {
                /* Cathedral Generation */
                TerrainFeature terrainFeature = ProjectZulu_Core.featureGenerator
                        .getRegisteredStructure(CathedralFeature.CATHEDRAL);
                if (terrainFeature != null) {
                    terrainFeature.generateFeature(par2World, Xcoord / 16, Zcoord / 16, new ChunkCoordinates(Xcoord,
                            Ycoord + 1, Zcoord), par2World.rand, calculateFeatureDirection(par3EntityPlayer));
                }
                break;
            }
            default:
                break;
            }
        }

        if (!par3EntityPlayer.capabilities.isCreativeMode) {
            par1ItemStack.stackSize--;
        }

    }

    private FeatureDirection calculateFeatureDirection(EntityPlayer player) {
        if (player.isSneaking()) {
            return FeatureDirection.CENTERED;
        } else {
            int direction = MathHelper.floor_double((double) (player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
            if (direction == 0) {
                return FeatureDirection.SOUTH;
            } else if (direction == 1) {
                return FeatureDirection.WEST;
            } else if (direction == 2) {
                return FeatureDirection.NORTH;
            } else if (direction == 3) {
                return FeatureDirection.EAST;
            }
        }
        throw new IllegalArgumentException();
    }

    @Override
    public int getMaxItemUseDuration(ItemStack par1ItemStack) {
        return 16;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
        return par1ItemStack;
    }

    /**
     * Return an item rarity from EnumRarity
     */
    @Override
    public EnumRarity getRarity(ItemStack par1ItemStack) {
        return EnumRarity.rare;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack par1ItemStack) {
        return EnumAction.bow;
    }

    @Override
    public String getUnlocalizedName(ItemStack itemstack) {
        try {
            return super.getUnlocalizedName().concat(".")
                    .concat(structureName[itemstack.getItemDamage()].toLowerCase());
        } catch (Exception e) {
            return "";
        }
    }

    @Override
    public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        for (int i = 0; i < structureName.length; i++) {
            par3List.add(new ItemStack(par1, 1, i));
        }
    }
}