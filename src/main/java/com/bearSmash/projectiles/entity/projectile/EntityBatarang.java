package com.bearSmash.projectiles.entity.projectile;

import com.bearSmash.projectiles.ProjectilesMod;
import com.bearSmash.projectiles.client.renderer.entity.RenderBatarang;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.EntityRegistry;

/**
 * Created by Bear on 3/2/2015.
 */
public class EntityBatarang extends EntityThrowable {
    private int randomTilt;
    private boolean onFire;
    private Item item;
    private ItemStack itemStack;
    private boolean hasHitSomething = false;

    public EntityBatarang(World world) {
        super(world);
        randomTilt = rand.nextInt(360);
        onFire = false;
    }

    public EntityBatarang(World world, EntityLivingBase player) {
        super(world, player);
        randomTilt = rand.nextInt(360);
        onFire = false;
    }

    public EntityBatarang(World world, EntityLivingBase player, Item item, ItemStack itemStack) {
        super(world, player);
        randomTilt = rand.nextInt(360);
        onFire = false;
        this.item = item;
        this.itemStack = itemStack;
    }

    public EntityBatarang(World world, double x, double y, double z) {
        super(world, x, y, z);
        randomTilt = rand.nextInt(360);
        onFire = false;
    }

    public static void preInit(){

    }

    public static void init(){
        int randomid = EntityRegistry.findGlobalUniqueEntityId();
        EntityRegistry.registerGlobalEntityID(EntityBatarang.class, ProjectilesMod.batarang.getUnlocalizedName(), randomid);
        EntityRegistry.registerModEntity(EntityBatarang.class, ProjectilesMod.batarang.getUnlocalizedName(), randomid, ProjectilesMod.modInstance, 128, 1, false);
    }

    private void inflictDamage(MovingObjectPosition movingObjectPos){
        movingObjectPos.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 4); //
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

        if (movingObjectPos.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {//if it's a block (not a mob)
            Block block = this.worldObj.getBlockState(movingObjectPos.getBlockPos()).getBlock();

            if (block == Blocks.tallgrass || block == Blocks.vine || block == Blocks.red_flower || block == Blocks.brown_mushroom_block || block == Blocks.red_mushroom ||
                    block == Blocks.reeds || block == Blocks.double_plant || block == Blocks.deadbush || block == Blocks.brown_mushroom || block == Blocks.wheat ||
                    block == Blocks.waterlily || block == Blocks.carrots || block == Blocks.potatoes || block == Blocks.snow_layer) {
                BlockPos blockPos = movingObjectPos.getBlockPos();
                IBlockState blockState = worldObj.getBlockState(blockPos);
                TileEntity te = worldObj.getTileEntity(blockPos);

                if (getThrower() instanceof EntityPlayer) {//if thrower is player
                    EntityPlayer player = (EntityPlayer) getThrower();
                    worldObj.destroyBlock(blockPos, false);
                    block.harvestBlock(worldObj, player, blockPos, blockState, te);
                } else if (this.getThrower() instanceof EntityTameable) {//special logic in case you make a tame mob able to throw batarang
                    EntityTameable tameableEntity = (EntityTameable) this.getThrower();
                    if (tameableEntity != null && tameableEntity.isTamed()) {
                        EntityPlayer player = (EntityPlayer) tameableEntity.getOwner();
                        if (player != null) {
                            worldObj.destroyBlock(blockPos, false);
                            block.harvestBlock(worldObj, player, blockPos, blockState, te);
                        }
                    }
                }
            } else {
                //TODO:  Do something with other block types, like trees and stone and stuff
            }
            bounceBack();

        } else if(movingObjectPos.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY) {//else it hit an entity (aka mob)
            if (movingObjectPos.entityHit != null) {
                if (getThrower() instanceof EntityPlayer) {//player threw it
                    if (movingObjectPos.entityHit.equals(getThrower())) {//if it's hitting the thrower, then it's a bounce back and thrower should catch it
//                        itemStack.stackSize++;
                        setDead();
                        EntityPlayer player = (EntityPlayer) getThrower();
                        player.onItemPickup(this, 1);
                        worldObj.playSoundAtEntity(this, "random.pop", 0.2F, ((rand.nextFloat() - rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
                    } else {
                        inflictDamage(movingObjectPos);
                        bounceBack();
                    }
                } else if (getThrower() instanceof EntityTameable) {//again, special logic in case you make a mob able to throw batarang
                    if (movingObjectPos.entityHit instanceof EntityTameable) {
                        EntityTameable tameableEntity = (EntityTameable) movingObjectPos.entityHit;
                        if (tameableEntity != null && tameableEntity.isTamed()) {
                            inflictDamage(movingObjectPos);
                        }
                    } else if (!(movingObjectPos.entityHit instanceof EntityPlayer)) {
                        inflictDamage(movingObjectPos);
                    }
                }

            } else if (movingObjectPos.typeOfHit == MovingObjectPosition.MovingObjectType.MISS) {//thrower missed.  Didn't hit anything
                //TODO:  bounce back after certain distance or time
                bounceBack();
            } else {//shouldn't be able to get here
                throw new RuntimeException("Error in entityHit.  Couldn't determine type of entity hit");
            }

        }
    }

    protected void bounceBack()
    {
        if(!hasHitSomething) {
            motionX *= -0.1D;
            motionY *= -0.1D;
            motionZ *= -0.1D;
            rotationYaw += 180F;
            prevRotationYaw += 180F;
        }

        if(!hasHitSomething)
            hasHitSomething = true;
    }

    public static void registerRenders(){

        registerRender();
    }

    public static void registerRender(){
        RenderBatarang renderBatarang = new RenderBatarang(Minecraft.getMinecraft().getRenderManager(), ProjectilesMod.modInstance.batarang, Minecraft.getMinecraft().getRenderItem());
        RenderingRegistry.registerEntityRenderingHandler(EntityBatarang.class, renderBatarang);
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
