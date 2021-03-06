package com.bearSmash.projectiles.client.renderer.entity;

import com.bearSmash.projectiles.entity.projectile.EntityBatclaw;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

/**
 * Created by Jramey on 3/22/2015.
 */
public class RenderBatclaw extends Render {
	
    private static final ResourceLocation clawTexture = new ResourceLocation("textures/items/batclaw.png");

    public RenderBatclaw(RenderManager renderManager) {

        super(renderManager);
    }

    protected ResourceLocation getEntityTexture(EntityBatclaw claw) {
        return clawTexture;
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity){
        return getEntityTexture((EntityBatclaw)entity);
    }

    @Override
    public void doRender(Entity entity, double transX, double transY, double transZ, float param5, float dir){
        doRender((EntityBatclaw) entity, transX, transY, transZ, param5, dir);
    }

    public void doRender(EntityBatclaw star, double transX, double transY, double transZ, float param5, float dir){
        bindEntityTexture(star);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.pushMatrix();

        GlStateManager.translate((float) transX, (float) transY, (float) transZ);
        GlStateManager.rotate(star.prevRotationYaw + (star.rotationYaw - star.prevRotationYaw) * dir - 90.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(star.getRandomTilt(), 1.0F, 0.0F, 0.0F);

        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldRenderer = tessellator.getWorldRenderer();

        GlStateManager.enableRescaleNormal();

        float scale = 0.05F;
        GlStateManager.scale(scale, scale, scale);

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

        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();

        super.doRender(star, transX, transY, transZ, param5, dir);

    }
}
