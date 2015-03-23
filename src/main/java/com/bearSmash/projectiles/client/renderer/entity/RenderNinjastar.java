package com.bearSmash.projectiles.client.renderer.entity;

import com.bearSmash.projectiles.entity.projectile.EntityNinjastar;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Created by Bear on 3/3/2015.
 */
public class RenderNinjastar extends Render {

    private static final ResourceLocation starTexture = new ResourceLocation("/textures/items/ninjastar.png");
    protected final Item itemNinjastar;
    private final RenderItem renderItemNinjastar;

//    public RenderNinjastar(RenderManager renderManager) {
//
//        super(renderManager);
//    }

    public RenderNinjastar(RenderManager renderManager, Item item, RenderItem renderItem){
        super(renderManager);
        this.itemNinjastar = item;
        this.renderItemNinjastar = renderItem;
    }

    protected ResourceLocation getEntityTexture(EntityNinjastar star) {
        return starTexture;
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity){
        return getEntityTexture((EntityNinjastar)entity);
    }

    @Override
    public void doRender(Entity entity, double transX, double transY, double transZ, float param5, float dir){
        doRender((EntityNinjastar) entity, transX, transY, transZ, param5, dir);
    }

    public void doRender(EntityNinjastar ninjastar, double transX, double transY, double transZ, float param5, float dir){
//        bindEntityTexture(star);
//        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
//        GlStateManager.pushMatrix();
//
//        GlStateManager.translate((float) transX, (float) transY, (float) transZ);
//        GlStateManager.rotate(star.prevRotationYaw + (star.rotationYaw - star.prevRotationYaw) * dir - 90.0F, 0.0F, 1.0F, 0.0F);
//        GlStateManager.rotate(star.getRandomTilt(), 1.0F, 0.0F, 0.0F);
//
//        Tessellator tessellator = Tessellator.getInstance();
//        WorldRenderer worldRenderer = tessellator.getWorldRenderer();
//
//        GlStateManager.enableRescaleNormal();
//
//        float scale = 0.75F;
//        GlStateManager.scale(scale, scale, scale);
//
//        GL11.glNormal3f(0.0F, 0.0F, scale);
//
//        worldRenderer.startDrawingQuads();
//        worldRenderer.addVertexWithUV(-2.0D, -2.0D, 0.0D, 0, 0);
//        worldRenderer.addVertexWithUV(-2.0D, -2.0D, 0.0D, 1, 0);
//        worldRenderer.addVertexWithUV(-2.0D, -2.0D, 0.0D, 1, 1);
//        worldRenderer.addVertexWithUV(-2.0D, -2.0D, 0.0D, 0, 1);
//        tessellator.draw();
//
//        GlStateManager.rotate(100.0F, 1.0F, 0.0F, 0.0F);
//        worldRenderer.startDrawingQuads();
//        worldRenderer.addVertexWithUV(-2.0D, -2.0D, 0.0D, 0, 0);
//        worldRenderer.addVertexWithUV(-2.0D, -2.0D, 0.0D, 1, 0);
//        worldRenderer.addVertexWithUV(-2.0D, -2.0D, 0.0D, 1, 1);
//        worldRenderer.addVertexWithUV(-2.0D, -2.0D, 0.0D, 0, 1);
//        tessellator.draw();
//
//        GlStateManager.disableRescaleNormal();
//        GlStateManager.popMatrix();
//
//        super.doRender(star, transX, transY, transZ, param5, dir);



        //        bindEntityTexture(ninjastar);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.pushMatrix();

        GlStateManager.translate((float) transX, (float) transY, (float) transZ);

        //moved
        GlStateManager.enableRescaleNormal();
        float scale = 0.75F;
        GlStateManager.scale(scale, scale, scale);

        GlStateManager.rotate(ninjastar.prevRotationYaw + (ninjastar.rotationYaw - ninjastar.prevRotationYaw) * dir - 90.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(ninjastar.getRandomTilt(), 1.0F, 0.0F, 0.0F);

        //moved
        bindEntityTexture(ninjastar);

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
        this.renderItemNinjastar.renderItemModel(this.func_177082_d(ninjastar));

        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();

        super.doRender(ninjastar, transX, transY, transZ, param5, dir);

    }

    public ItemStack func_177082_d(Entity entity) {
        return new ItemStack(this.itemNinjastar, 1, 0);
    }
}
