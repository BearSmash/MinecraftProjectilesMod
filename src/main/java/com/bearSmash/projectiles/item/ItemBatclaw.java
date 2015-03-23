package com.bearSmash.projectiles.item;

import com.bearSmash.projectiles.Reference;
import com.bearSmash.projectiles.entity.projectile.EntityBatclaw;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.RegistryDelegate;

/**
 * Batclaw item registration
 * Created by Jramey on 3/22/2015.
 */

public class ItemBatclaw extends Item {
	public static Item batclaw;


    public ItemBatclaw(){
        super();
        this.maxStackSize = 64;
    }

    public static Item preInit(){
        batclaw = new ItemBatclaw().setUnlocalizedName("batclaw").setCreativeTab(CreativeTabs.tabMaterials);
        register();
        return batclaw;
    }

    public static void init(){
	}


    public static void register(){
        GameRegistry.registerItem(batclaw, batclaw.getUnlocalizedName().substring(5));//substring is to remove "tile." before item name

        RegistryDelegate delegate = batclaw.delegate;
        System.out.println("delegate = " + delegate);
    }

    public static void registerRenders(){

        registerRender(batclaw);
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
        return itemStack;
    }
    
}
