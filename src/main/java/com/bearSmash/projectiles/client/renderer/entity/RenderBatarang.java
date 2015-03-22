package com.bearSmash.projectiles.client.renderer.entity;

import com.bearSmash.projectiles.entity.projectile.EntityBatarang;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Created by Bear on 3/3/2015.
 */
public class RenderBatarang extends Render {

    private static final ResourceLocation batarangTexture = new ResourceLocation("textures/items/batarang.png");
    protected final Item itemBatarang;
    private final RenderItem renderItemBatarang;

//    public RenderBatarang(RenderManager renderManager) {
//        super(renderManager);
//    }

    public RenderBatarang(RenderManager renderManager, Item item, RenderItem renderItem){
        super(renderManager);
        this.itemBatarang = item;
        this.renderItemBatarang = renderItem;
    }

    @Override
    public void doRender(Entity entity, double transX, double transY, double transZ, float param5, float dir){
        doRender((EntityBatarang) entity, transX, transY, transZ, param5, dir);
    }

    public void doRender(EntityBatarang batarang, double transX, double transY, double transZ, float param5, float dir){
//        bindEntityTexture(batarang);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.pushMatrix();

        GlStateManager.translate((float) transX, (float) transY, (float) transZ);

        //moved
        GlStateManager.enableRescaleNormal();
        float scale = 0.75F;
        GlStateManager.scale(scale, scale, scale);

        GlStateManager.rotate(batarang.prevRotationYaw + (batarang.rotationYaw - batarang.prevRotationYaw) * dir - 90.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(batarang.getRandomTilt(), 1.0F, 0.0F, 0.0F);

        //moved
        bindEntityTexture(batarang);

        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldRenderer = tessellator.getWorldRenderer();

//        GlStateManager.enableRescaleNormal();

//        float scale = 0.05F;
//        GlStateManager.scale(scale, scale, scale);

        GL11.glNormal3f(0.0F, 0.0F, scale);

        worldRenderer.startDrawingQuads();
        worldRenderer.addVertexWithUV(-2.0D, -2.0D, 0.0D, 0, 0);
        worldRenderer.addVertexWithUV(-2.0D, -2.0D, 0.0D, 1, 0);
        worldRenderer.addVertexWithUV(-2.0D, -2.0D, 0.0D, 1, 1);
        worldRenderer.addVertexWithUV(-2.0D, -2.0D, 0.0D, 0, 1);
        tessellator.draw();

        GlStateManager.rotate(100.0F, 1.0F, 0.0F, 0.0F);
        worldRenderer.startDrawingQuads();
        worldRenderer.addVertexWithUV(-2.0D, -2.0D, 0.0D, 0, 0);
        worldRenderer.addVertexWithUV(-2.0D, -2.0D, 0.0D, 1, 0);
        worldRenderer.addVertexWithUV(-2.0D, -2.0D, 0.0D, 1, 1);
        worldRenderer.addVertexWithUV(-2.0D, -2.0D, 0.0D, 0, 1);
        tessellator.draw();

        //added
        this.renderItemBatarang.renderItemModel(this.func_177082_d(batarang));

        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();

        super.doRender(batarang, transX, transY, transZ, param5, dir);

    }

//    public void doRender(Entity p_doRender_1_, double p_doRender_2_, double p_doRender_4_, double p_doRender_6_, float p_doRender_8_, float p_doRender_9_) {
//        GlStateManager.pushMatrix();
//        GlStateManager.translate((float)p_doRender_2_, (float)p_doRender_4_, (float)p_doRender_6_);
//        GlStateManager.enableRescaleNormal();
//        GlStateManager.scale(0.5F, 0.5F, 0.5F);
//        GlStateManager.rotate(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
//        GlStateManager.rotate(this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
//        this.bindTexture(TextureMap.locationBlocksTexture);
//        this.renderItemBatarang.renderItemModel(this.func_177082_d(p_doRender_1_));
//        GlStateManager.disableRescaleNormal();
//        GlStateManager.popMatrix();
//        super.doRender(p_doRender_1_, p_doRender_2_, p_doRender_4_, p_doRender_6_, p_doRender_8_, p_doRender_9_);
//    }

    protected ResourceLocation getEntityTexture(EntityBatarang batarang) {
        return batarangTexture;
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity){
        return getEntityTexture((EntityBatarang)entity);
    }

    public ItemStack func_177082_d(Entity entity) {
        return new ItemStack(this.itemBatarang, 1, 0);
    }
}
