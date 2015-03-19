package com.bearSmash.projectiles.item;

import com.bearSmash.projectiles.Reference;
import com.bearSmash.projectiles.entity.projectile.EntityPlayerFireballSmall;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFireball;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.Random;

/**
 * FireballSmall item registration
 * Created by Bear on 3/12/2015.
 */
public class ItemPlayerFireballSmall extends ItemFireball{

    public static Item fireballSmall;

    public ItemPlayerFireballSmall(){
        super();
        this.maxStackSize = 64;
    }

    public static void preInit(){
        fireballSmall = new ItemPlayerFireballSmall().setUnlocalizedName("player_fireball_small").setCreativeTab(CreativeTabs.tabCombat);
    }

    public static void init(){
        GameRegistry.addRecipe(new ItemStack(fireballSmall, 5), new Object[]{
                                                                         "M M",
                                                                         " F ",
                                                                         "M M",
                'F', Items.flint_and_steel, 'M', Items.magma_cream});
    }

    public static void register(){
        GameRegistry.registerItem(fireballSmall, fireballSmall.getUnlocalizedName().substring(5));//substring is to remove "tile." before item name

    }

    public static void registerRenders(){

        registerRender(fireballSmall);
    }

    public static void registerRender(Item item){
        String resourceLocation = Reference.MOD_ID + ":" + item.getUnlocalizedName().substring(5);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
                .register(item, 0, new ModelResourceLocation(resourceLocation, "inventory"));
    }

    @Override
    public boolean canHarvestBlock(Block blockIn){
        return false;
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair){
        return false;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World worldObj, EntityPlayer player){
        if(!player.capabilities.isCreativeMode){
            --itemStack.stackSize;
        }

        player.swingItem();
        player.setItemInUse(itemStack, this.getMaxItemUseDuration(itemStack));
        worldObj.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

        Vec3 look = player.getLookVec();
        EntityFireball fireball = new EntityPlayerFireballSmall(worldObj, player, 0, 0, 0);
        fireball.setPosition(
                player.posX + look.xCoord * 2,
                player.posY + look.yCoord * 2,
                player.posZ + look.zCoord * 2);
        fireball.accelerationX = look.xCoord * 0.1;
        fireball.accelerationY = look.yCoord * 0.1;
        fireball.accelerationZ = look.zCoord * 0.1;
        worldObj.spawnEntityInWorld(fireball);

        return itemStack;
    }


}
