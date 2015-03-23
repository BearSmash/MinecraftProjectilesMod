package com.bearSmash.projectiles.entity.projectile;

import com.bearSmash.projectiles.ProjectilesMod;
import com.bearSmash.projectiles.client.renderer.entity.RenderBatclaw;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;


/**
* Created by Bear on 3/2/2015.
*/
public class EntityBatclaw extends EntityItem {
    private int randomTilt;
    private boolean onFire;

    public EntityBatclaw(World world) {
        super(world);
        randomTilt = rand.nextInt(360);
        onFire = false;
    }

    public EntityBatclaw(World world, double x, double y, double z) {
        super(world, x, y, z);
        randomTilt = rand.nextInt(360);
        onFire = false;
    }
    
    public static void preInit(){

    }

    public static void init(){
        int randomid = EntityRegistry.findGlobalUniqueEntityId();
        EntityRegistry.registerGlobalEntityID(EntityBatarang.class, ProjectilesMod.batclaw.getUnlocalizedName(), randomid);
        EntityRegistry.registerModEntity(EntityBatarang.class, ProjectilesMod.batclaw.getUnlocalizedName(), randomid, ProjectilesMod.modInstance, 128, 1, false);
    }


    private void destroySelf(){
        this.worldObj.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D, new int[0]);
        this.setDead();
    }


    public static void registerRenders(){

        registerRender();
    }

    public static void registerRender(){
        RenderingRegistry.registerEntityRenderingHandler(EntityBatclaw.class, new RenderBatclaw(Minecraft.getMinecraft().getRenderManager()));
    }

    public int getRandomTilt() {
        return randomTilt;
    }

    public void setRandomTilt(int angle) {
        this.randomTilt = angle;
    }

    public boolean getOnFire() {
        return onFire;
    }

    public void setOnFire(boolean onFire) {
        this.onFire = onFire;
    }

}
