package projectzulu.common.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.api.CustomEntityList;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.mobs.entity.EntityMummyPharaoh;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockJasper extends Block {
    boolean prepareToSummonBoss = false;
    int counter = 0;
    int alterIncrement = 0;

    Vec3[] alterBlockLocations = new Vec3[30];

    public BlockJasper() {
        super(Material.rock);
        setTickRandomly(true);
        setCreativeTab(ProjectZulu_Core.projectZuluCreativeTab);
        setHardness(1.0f);
        setResistance(1.0f);
        initialiseAlterBlocks();
    }

    public void initialiseAlterBlocks() {
        // All Blocks are relative to BlockJasper Location
        // Bottom Of Alter
        alterBlockLocations[0] = Vec3.createVectorHelper(2, 0, -1);
        alterBlockLocations[1] = Vec3.createVectorHelper(2, 0, 0);
        alterBlockLocations[2] = Vec3.createVectorHelper(2, 0, 1);

        alterBlockLocations[3] = Vec3.createVectorHelper(1, 0, -2);
        alterBlockLocations[4] = Vec3.createVectorHelper(1, 0, -1);
        alterBlockLocations[5] = Vec3.createVectorHelper(1, 0, 0);
        alterBlockLocations[6] = Vec3.createVectorHelper(1, 0, 1);
        alterBlockLocations[7] = Vec3.createVectorHelper(1, 0, 2);

        alterBlockLocations[8] = Vec3.createVectorHelper(0, 0, -2);
        alterBlockLocations[9] = Vec3.createVectorHelper(0, 0, -1);
        alterBlockLocations[10] = Vec3.createVectorHelper(0, 0, 1);
        alterBlockLocations[11] = Vec3.createVectorHelper(0, 0, 2);

        alterBlockLocations[12] = Vec3.createVectorHelper(-1, 0, -2);
        alterBlockLocations[13] = Vec3.createVectorHelper(-1, 0, -1);
        alterBlockLocations[14] = Vec3.createVectorHelper(-1, 0, 0);
        alterBlockLocations[15] = Vec3.createVectorHelper(-1, 0, 1);
        alterBlockLocations[16] = Vec3.createVectorHelper(-1, 0, 2);

        alterBlockLocations[17] = Vec3.createVectorHelper(-2, 0, -1);
        alterBlockLocations[18] = Vec3.createVectorHelper(-2, 0, 0);
        alterBlockLocations[19] = Vec3.createVectorHelper(-2, 0, 1);

        // FirstLevel
        alterBlockLocations[20] = Vec3.createVectorHelper(1, 1, -1);
        alterBlockLocations[21] = Vec3.createVectorHelper(1, 1, 0);
        alterBlockLocations[22] = Vec3.createVectorHelper(1, 1, 1);

        alterBlockLocations[23] = Vec3.createVectorHelper(0, 1, -1);
        alterBlockLocations[24] = Vec3.createVectorHelper(0, 1, 0);
        alterBlockLocations[25] = Vec3.createVectorHelper(0, 1, 1);

        alterBlockLocations[26] = Vec3.createVectorHelper(-1, 1, -1);
        alterBlockLocations[27] = Vec3.createVectorHelper(-1, 1, 0);
        alterBlockLocations[28] = Vec3.createVectorHelper(-1, 1, 1);
        // Second Level
        alterBlockLocations[29] = Vec3.createVectorHelper(0, 2, 0);
    }

    @Override
    public int quantityDropped(Random random) {
        return 1;
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    @Override
    public void onBlockAdded(World par1World, int par2, int par3, int par4) {

        counter = 0;
        super.onBlockAdded(par1World, par2, par3, par4);
        Vec3[] blocksToCheck;
        blocksToCheck = new Vec3[13];
        int countValidTorches = 0;

        // Check Southern Configuration
        blocksToCheck[0] = Vec3.createVectorHelper(par2 + 1, par3, par4);
        blocksToCheck[1] = Vec3.createVectorHelper(par2 + 1, par3, par4 + 1);
        blocksToCheck[2] = Vec3.createVectorHelper(par2 + 1, par3, par4 - 1);
        blocksToCheck[3] = Vec3.createVectorHelper(par2, par3, par4 - 1);
        blocksToCheck[4] = Vec3.createVectorHelper(par2, par3, par4 + 1);
        blocksToCheck[5] = Vec3.createVectorHelper(par2 - 1, par3, par4);
        blocksToCheck[6] = Vec3.createVectorHelper(par2 - 1, par3, par4 + 1);
        blocksToCheck[7] = Vec3.createVectorHelper(par2 - 1, par3, par4 - 1);

        // Check Northern Configuration
        blocksToCheck[8] = Vec3.createVectorHelper(par2 - 2, par3, par4);
        blocksToCheck[9] = Vec3.createVectorHelper(par2 - 3, par3, par4);
        blocksToCheck[10] = Vec3.createVectorHelper(par2 - 3, par3, par4 + 1);
        blocksToCheck[11] = Vec3.createVectorHelper(par2 - 3, par3, par4 - 1);
        blocksToCheck[12] = Vec3.createVectorHelper(par2 - 4, par3, par4);

        for (int i = 0; i < blocksToCheck.length; i++) {
            if (par1World.getBlock((int) blocksToCheck[i].xCoord, (int) blocksToCheck[i].yCoord,
                    (int) blocksToCheck[i].zCoord) == Blocks.torch) {
                countValidTorches += 1;
            }
        }
        if (countValidTorches < 13) {
            countValidTorches = 0;
        }

        // Check Northern Configuration
        blocksToCheck[8] = Vec3.createVectorHelper(par2 + 2, par3, par4);
        blocksToCheck[9] = Vec3.createVectorHelper(par2 + 3, par3, par4);
        blocksToCheck[10] = Vec3.createVectorHelper(par2 + 3, par3, par4 + 1);
        blocksToCheck[11] = Vec3.createVectorHelper(par2 + 3, par3, par4 - 1);
        blocksToCheck[12] = Vec3.createVectorHelper(par2 + 4, par3, par4);

        for (int i = 0; i < blocksToCheck.length; i++) {

            if (par1World.getBlock((int) blocksToCheck[i].xCoord, (int) blocksToCheck[i].yCoord,
                    (int) blocksToCheck[i].zCoord) == Blocks.torch) {
                countValidTorches += 1;
            }
        }
        if (countValidTorches < 13) {
            countValidTorches = 0;
        }

        // Check Western Configuration
        blocksToCheck[8] = Vec3.createVectorHelper(par2, par3, par4 + 2);
        blocksToCheck[9] = Vec3.createVectorHelper(par2, par3, par4 + 3);
        blocksToCheck[10] = Vec3.createVectorHelper(par2 + 1, par3, par4 + 3);
        blocksToCheck[11] = Vec3.createVectorHelper(par2 - 1, par3, par4 + 3);
        blocksToCheck[12] = Vec3.createVectorHelper(par2, par3, par4 + 4);

        for (int i = 0; i < blocksToCheck.length; i++) {

            if (par1World.getBlock((int) blocksToCheck[i].xCoord, (int) blocksToCheck[i].yCoord,
                    (int) blocksToCheck[i].zCoord) == Blocks.torch) {
                countValidTorches += 1;
            }
        }
        if (countValidTorches < 13) {
            countValidTorches = 0;
        }

        // Check Eastern Configuration
        blocksToCheck[8] = Vec3.createVectorHelper(par2, par3, par4 - 2);
        blocksToCheck[9] = Vec3.createVectorHelper(par2, par3, par4 - 3);
        blocksToCheck[10] = Vec3.createVectorHelper(par2 + 1, par3, par4 - 3);
        blocksToCheck[11] = Vec3.createVectorHelper(par2 - 1, par3, par4 - 3);
        blocksToCheck[12] = Vec3.createVectorHelper(par2, par3, par4 - 4);

        for (int i = 0; i < blocksToCheck.length; i++) {

            if (par1World.getBlock((int) blocksToCheck[i].xCoord, (int) blocksToCheck[i].yCoord,
                    (int) blocksToCheck[i].zCoord) == Blocks.torch) {
                countValidTorches += 1;
            }
        }
        if (countValidTorches < 13) {
            countValidTorches = 0;
        }

        if (countValidTorches >= 13 && par1World.canBlockSeeTheSky(par2, par3 + 1, par4)
                && CustomEntityList.MUMMYPHARAOH.modData.isPresent()) {

            if (!MinecraftServer.getServer().isDedicatedServer()) {
                Minecraft.getMinecraft().theWorld.playRecord(DefaultProps.coreDiretory + ":misc.summonwhispers", par2,
                        par3, par4);
            }

            // if( !MinecraftServer.getServer().isDedicatedServer() ){
            // //theMinecraft.theWorld.setWorldTime(13000);
            // Minecraft theMinecraft = Minecraft.getMinecraft();
            // theMinecraft.theWorld.playSound(par2, par3, par4, "sounds.summonwhispers", 10.0f, 1.0f);
            // }
            // Play Summon Sound
            // Set Time to Night
            par1World.scheduleBlockUpdate(par2, par3, par4, this, 1);
            counter++;
            prepareToSummonBoss = true;
        } else {
            this.dropBlockAsItem(par1World, par2, par3, par4, 0, 0);
            par1World.setBlock(par2, par3, par4, Blocks.air, 0, 0);
            par1World.setBlock(par2, par3, par4, Blocks.air);
        }
    }

    @SideOnly(Side.CLIENT)
    public void playWhispersSound() {

    }

    @Override
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {

        if (prepareToSummonBoss == false) {
            par1World.setBlock(par2, par3, par4, Blocks.air);
        }

        if (MathHelper.floor_double(counter / 20) >= 5 && MathHelper.floor_double(counter / 20) < 35) {
            buildAlter(par1World, par2, par3, par4, par5Random, MathHelper.floor_double(counter / 20) - 5);
        }

        if (counter < 40 * 20 && par1World != null) {
            par1World.scheduleBlockUpdate(par2, par3, par4, this, 1);
            counter++;
        } else {
            if (!MinecraftServer.getServer().isDedicatedServer()) {
                // World worldObj = ModLoader.getMinecraftInstance().theWorld;
                Minecraft.getMinecraft().theWorld.playSound(par2, par3, par4, "sounds.mummyroar", 10.0f, 1.0f, false);
            }

            EntityMummyPharaoh var17 = new EntityMummyPharaoh(par1World, par2, par3 + 3, par4);
            par1World.spawnEntityInWorld(var17);
            par1World.setBlock(par2, par3, par4, Blocks.air);
        }
    }

    public void buildAlter(World par1World, int par2, int par3, int par4, Random par5Random, int iterator) {
        int alterX = MathHelper.floor_double(alterBlockLocations[iterator].xCoord);
        int alterY = MathHelper.floor_double(alterBlockLocations[iterator].yCoord);
        int alterZ = MathHelper.floor_double(alterBlockLocations[iterator].zCoord);
        par1World.setBlock(alterX + par2, alterY + par3, alterZ + par4, Blocks.sandstone);
    }

    // /**
    // * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
    // */
    // public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
    // {
    // int var5 = par1World.getBlockId(par2, par3, par4);
    // return (var5 == 0 || Block.blocksList[var5].blockMaterial.isGroundCover()) &&
    // par1World.doesBlockHaveSolidTopSurface(par2, par3 - 1, par4);
    // }

    // /**
    // * Called when the block is placed in the world.
    // */
    // public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving par5EntityLiving)
    // {
    // int var6 = MathHelper.floor_double((double)(par5EntityLiving.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
    // par1World.setBlockMetadataWith_Notify(par2, par3, par4, var6);
    // }

}
