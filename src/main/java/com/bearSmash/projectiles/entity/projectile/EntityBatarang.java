package com.bearSmash.projectiles.entity.projectile;

import com.bearSmash.projectiles.client.renderer.entity.RenderBatarang;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

/**
* Created by Bear on 3/2/2015.
*/
public class EntityBatarang extends EntityThrowable {
    private int randomTilt;
    private boolean onFire;

    public EntityBatarang(World world) {
        super(world);
        randomTilt = rand.nextInt(360);
        onFire = false;
    }

    public EntityBatarang(World world, EntityPlayer player) {
        super(world, player);
        randomTilt = rand.nextInt(360);
        onFire = false;
    }

    public EntityBatarang(World world, double x, double y, double z) {
        super(world, x, y, z);
        randomTilt = rand.nextInt(360);
        onFire = false;
    }

    private void inflictDamage(MovingObjectPosition movingObjectPos){
        movingObjectPos.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 2);
        if(this.getOnFire()){
            movingObjectPos.entityHit.setFire(3);
        }
    }

    private void destroySelf(){
        this.worldObj.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D, new int[0]);
        this.setDead();
    }

    @Override
    protected void onImpact(MovingObjectPosition movingObjectPos) {
        if(movingObjectPos.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK){
            Block block = this.worldObj.getBlockState(movingObjectPos.getBlockPos()).getBlock();

            if(block == Blocks.tallgrass || block == Blocks.vine || block == Blocks.red_flower || block == Blocks.brown_mushroom_block|| block == Blocks.red_mushroom||
               block == Blocks.reeds|| block == Blocks.double_plant|| block == Blocks.deadbush|| block == Blocks.brown_mushroom|| block == Blocks.wheat ||
               block == Blocks.waterlily|| block == Blocks.carrots|| block == Blocks.potatoes|| block == Blocks.snow_layer){
                BlockPos blockPos = movingObjectPos.getBlockPos();
                IBlockState blockState = worldObj.getBlockState(blockPos);
                TileEntity te = worldObj.getTileEntity(blockPos);

                if(getThrower() instanceof EntityPlayer){//if thrower is player
                    EntityPlayer player = (EntityPlayer)getThrower();
                    worldObj.destroyBlock(blockPos, false);
                    block.harvestBlock(worldObj, player, blockPos, blockState, te);
                }else if(this.getThrower() instanceof EntityTameable){
                    EntityTameable tameableEntity = (EntityTameable)this.getThrower();
                    if(tameableEntity != null && tameableEntity.isTamed()){
                        EntityPlayer player = (EntityPlayer)tameableEntity.getOwner();
                        if(player != null){
                            worldObj.destroyBlock(blockPos, false);
                            block.harvestBlock(worldObj, player, blockPos, blockState, te);
                        }
                    }
                }
            }else{
                destroySelf();
            }
        }else{
            if(movingObjectPos.entityHit != null){
                if(getThrower() instanceof EntityPlayer){
                    inflictDamage(movingObjectPos);
                }else if(getThrower() instanceof EntityTameable){
                    if(movingObjectPos.entityHit instanceof EntityTameable){
                        EntityTameable tameableEntity = (EntityTameable)movingObjectPos.entityHit;
                        if(tameableEntity != null && tameableEntity.isTamed()){
                            inflictDamage(movingObjectPos);
                        }
                    }else if(!(movingObjectPos.entityHit instanceof EntityPlayer)){
                        inflictDamage(movingObjectPos);
                    }
                }
            }

        }
    }

    public static void registerRenders(){

        registerRender();
    }

    public static void registerRender(){
        RenderingRegistry.registerEntityRenderingHandler(EntityBatarang.class, new RenderBatarang(Minecraft.getMinecraft().getRenderManager()));
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
