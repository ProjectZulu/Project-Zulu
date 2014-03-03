package projectzulu.common.blocks.heads;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import projectzulu.common.core.DefaultProps;
import projectzulu.common.mobs.models.ModelFinch;

public class TileEntityMobHeadsRenderer extends TileEntitySpecialRenderer {
    enum HeadRender {
        Finch_Red(0, new ModelFinch(), 0.0625F, new Position[] { new Position(0.26F, 1.5F, 0.5F),
                new Position(0.5F, 1.5F, 0.5F), new Position(0.5F, 1.5F, 0.74F), new Position(0.5F, 1.5F, 0.26F),
                new Position(0.74F, 1.5F, 0.5F), new Position(0.26F, 1.5F, 0.5F) }), Crocodile(1,
                new ModelCrocodileHead(), 0.0625F, new Position[] { new Position(0.26F, 1.5F, 0.5F),
                        new Position(0.5F, 1.5F, 0.5F), new Position(0.5F, 1.75F, 0.55F),
                        new Position(0.5F, 1.75F, 0.4F), new Position(0.55F, 1.75F, 0.5F),
                        new Position(0.45F, 1.75F, 0.5F) }), Armadillo(2, new ModelArmadilloHead(), 0.0625F,
                new Position[] { new Position(0.26F, 1.5F, 0.5F), new Position(0.5F, 1.5F, 0.5F),
                        new Position(0.5F, 1.75F, 0.80F), new Position(0.5F, 1.75F, 0.20F),
                        new Position(0.80F, 1.75F, 0.5F), new Position(0.20F, 1.75F, 0.5F) }), BearBlack(3,
                new ModelBearHead(), 0.0625F, new Position[] { new Position(0.26F, 1.5F, 0.5F),
                        new Position(0.5F, 1.5F, 0.5F), new Position(0.5F, 1.75F, 0.85F),
                        new Position(0.5F, 1.75F, 0.15F), new Position(0.85F, 1.75F, 0.5F),
                        new Position(0.15F, 1.75F, 0.5F) }), BearBrown(4, new ModelBearHead(), 0.08F, new Position[] {
                new Position(0.26F, 2.0F, 0.5F), new Position(0.5F, 1.90f, 0.5F), new Position(0.5F, 2.1F, 0.85F),
                new Position(0.5F, 2.1F, 0.15F), new Position(0.85F, 2.1F, 0.5F), new Position(0.15f, 2.1F, 0.5F) }), BearPolar(
                5, new ModelBearHead(), 0.1F, new Position[] { new Position(0.26F, 1.5F, 0.5F),
                        new Position(0.5F, 2.4F, 0.5F), new Position(0.5F, 2.5F, 0.80F),
                        new Position(0.5F, 2.5F, 0.20f), new Position(0.80f, 2.5F, 0.5F),
                        new Position(0.20f, 2.5F, 0.5F) }), Beaver(6, new ModelBeaverHead(), 0.0625F, new Position[] {
                new Position(0.26F, 1.5F, 0.5F), new Position(0.5F, 1.5F, 0.5F), new Position(0.5F, 1.75F, 0.85f),
                new Position(0.5F, 1.75F, 0.15F), new Position(0.85f, 1.75F, 0.5F), new Position(0.15f, 1.75F, 0.5F) }), Boar(
                7, new ModelBoarHead(), 0.0625F, new Position[] { new Position(0.26F, 1.5F, 0.5F),
                        new Position(0.5F, 1.5F, 0.5F), new Position(0.5F, 1.75F, 0.70f),
                        new Position(0.5F, 1.75F, 0.30f), new Position(0.70f, 1.75F, 0.5F),
                        new Position(0.30f, 1.75F, 0.5F) }), Giraffe(8, new ModelGiraffeHead(), 0.035F, new Position[] {
                new Position(0.26F, 1.5F, 0.5F), new Position(0.5F, 0.85F, 0.5F), new Position(0.5F, 1.15F, 1.00f),
                new Position(0.5F, 1.15F, 0.00f), new Position(1.00F, 1.15F, 0.5F), new Position(0.00F, 1.15F, 0.5F) }), Gorilla(
                9, new ModelGorillaHead(), 0.0625F, new Position[] { new Position(0.26F, 1.5F, 0.5F),
                        new Position(0.5F, 1.5F, 0.5F), new Position(0.5F, 1.75F, 0.80f),
                        new Position(0.5F, 1.75F, 0.20f), new Position(0.80f, 1.75F, 0.5F),
                        new Position(0.20f, 1.75F, 0.5F) }), Lizard(10, new ModelLizardHead(), 0.0625F, new Position[] {
                new Position(0.26F, 1.5F, 0.5F), new Position(0.5F, 1.5F, 0.5F), new Position(0.5F, 1.75F, 0.80f),
                new Position(0.5F, 1.75F, 0.20f), new Position(0.80f, 1.75F, 0.5F), new Position(0.20f, 1.75F, 0.5F) }), Mammoth(
                11, new ModelMammothHead(), 0.0625F, new Position[] { new Position(0.26F, 1.5F, 0.5F),
                        new Position(0.5F, 1.40F, 0.5F), new Position(0.5F, 1.56F, 0.70f),
                        new Position(0.5F, 1.56F, 0.30f), new Position(0.70f, 1.56F, 0.5F),
                        new Position(0.30f, 1.56F, 0.5F) }), Ostrich(12, new ModelOstrichHead(), 0.0625F,
                new Position[] { new Position(0.26F, 1.5F, 0.5F), new Position(0.5F, 1.5F, 0.5F),
                        new Position(0.5F, 1.75F, 1.2F), new Position(0.5F, 1.75F, -0.1F),
                        new Position(1.15F, 1.75F, 0.5F), new Position(-0.15F, 1.75F, 0.5F) }), Penguin(13,
                new ModelPenguinHead(), 0.0625F, new Position[] { new Position(0.26F, 1.5F, 0.5F),
                        new Position(0.5F, 1.5F, 0.5F), new Position(0.5F, 1.85F, 0.75f),
                        new Position(0.5F, 1.85F, 0.25f), new Position(0.75f, 1.85F, 0.5F),
                        new Position(0.25f, 1.85F, 0.5F) }), Rhino(14, new ModelRhinoHead(), 0.0625F, new Position[] {
                new Position(0.26F, 1.5F, 0.5F), new Position(0.5F, 1.5F, 0.5F), new Position(0.5F, 1.75F, 0.60F),
                new Position(0.5F, 1.75F, 0.4F), new Position(0.60f, 1.75F, 0.5F), new Position(0.40f, 1.75F, 0.5F) }), TreeEnt(
                15, new ModelTreeEntHead(), 0.0625F, new Position[] { new Position(0.26F, 1.5F, 0.5F),
                        new Position(0.5F, 1.5F, 0.5F), new Position(0.5F, 1.75f, 0.55F),
                        new Position(0.5F, 1.75F, 0.4F), new Position(0.55F, 1.75F, 0.5F),
                        new Position(0.45F, 1.75F, 0.5F) }), Vulture(16, new ModelVultureHead(), 0.0625F,
                new Position[] { new Position(0.26F, 1.5F, 0.5F), new Position(0.5F, 1.55F, 0.5F),
                        new Position(0.5F, 2.10F, 0.95F), new Position(0.5F, 2.10F, 0.05F),
                        new Position(0.95F, 2.10F, 0.5F), new Position(0.05F, 2.10F, 0.5F) }), Elephant(17,
                new ModelElephantHead(), 0.0505F, new Position[] { new Position(0.26F, 1.5F, 0.5F),
                        new Position(0.5F, 0.9F, 0.5F), new Position(0.5F, 0.9F, 0.59F),
                        new Position(0.5F, 0.9F, 0.41F), new Position(0.59F, 0.9F, 0.5F),
                        new Position(0.41F, 0.9F, 0.5F) });

        private final ModelBase model;
        private final int iD;
        private final float scale;
        private final Position[] transOffset;
        public final ResourceLocation resourceLocation;

        private HeadRender(int iD, ModelBase model, float scale, Position[] offSets) {
            this.iD = iD;
            this.model = model;
            this.transOffset = offSets;
            this.scale = scale;
            resourceLocation = new ResourceLocation(DefaultProps.mobKey, this.toString().toLowerCase() + ".png");
        }

        public ModelBase getModel() {
            return model;
        }

        public int getID() {
            return iD;
        }

        public Position getOffset(int index) {
            return transOffset[index];
        }

        public static HeadRender getByID(int iD) {
            for (HeadRender head : HeadRender.values()) {
                if (head.iD == iD)
                    return head;
            }
            return null;
        }
    }

    public static class Position {
        public final float x;
        public final float y;
        public final float z;

        public Position(float x, float y, float z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    public TileEntityMobHeadsRenderer() {
    }

    public void renderAModelAt(TileEntityMobHeads tile, float par1, float par2, float par3, float f) {

        /* Get Rotation */
        float rotation = (float) (tile.getRotation() * 360) / 16f;

        /* Get Meta */
        int meta = 0;
        if (tile.getWorldObj() != null) {
            meta = (tile.getWorldObj().getBlockMetadata(tile.xCoord, tile.yCoord, tile.zCoord) & 7);
        }
        /* Get And Set Attributes Specific to Skull Type */
        int skullType = tile.getSkullType();
        HeadRender mobhead = HeadRender.getByID(skullType);
        float scale = mobhead.scale;
        String textureLocation = DefaultProps.mobDiretory + mobhead.toString().toLowerCase() + ".png";
        int skullState = 0;
        if (meta == 1) {
            switch (mobhead) {
            case Giraffe:
            case Mammoth:
            case Ostrich:
            case Vulture:
            case Elephant:
                skullState = 1;
                break;
            default:
                break;
            }
        }
        bindTexture(mobhead.resourceLocation);

        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_CULL_FACE);

        GL11.glTranslatef(par1 + (float) mobhead.getOffset(meta).x, par2 + (float) mobhead.getOffset(meta).y, par3
                + (float) mobhead.getOffset(meta).z);
        /* Override Rotation if on the Side of a Block */
        switch (meta) {
        case 1:
            break;
        case 2:
            break;
        case 3:
            rotation = 180.0F;
            break;
        case 4:
            rotation = 270.0F;
            break;
        case 5:
        default:
            rotation = 90.0F;
        }

        GL11.glRotatef(0.0f, 0.0f, 1.0F, 0.0f);
        GL11.glScalef(-1.0f, -1.0F, 1.0F);
        mobhead.model.render((Entity) null, 0.0F, 0.0F, 0.0F, rotation, skullState, scale);
        GL11.glPopMatrix();
    }

    public void renderTileEntityAt(TileEntity tileentity, double par2, double par4, double par6, float par8) {
        renderAModelAt((TileEntityMobHeads) tileentity, (float) par2, (float) par4, (float) par6, par8); // where to
                                                                                                         // render
    }
}
