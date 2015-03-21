package com.bearSmash.projectiles.item;

import com.bearSmash.projectiles.Reference;
import com.bearSmash.projectiles.entity.projectile.EntityBatarang;
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

/**
 * Batarang item registration
 * Created by Bear on 3/12/2015.
 */
public class ItemBatarang extends Item{

    public static Item batarang;

    public ItemBatarang(){
        super();
        this.maxStackSize = 64;
    }

    public static void preInit(){
        batarang = new ItemBatarang().setUnlocalizedName("batarang").setCreativeTab(CreativeTabs.tabCombat);
    }

    public static void init(){
        GameRegistry.addRecipe(new ItemStack(batarang, 5), new Object[]{
                                                                         "I I",
                                                                         " F ",
                                                                         "I I",
                'I', Items.iron_ingot, 'F', Items.flint});
    }

    public static void register(){
        GameRegistry.registerItem(batarang, batarang.getUnlocalizedName().substring(5));//substring is to remove "tile." before item name

    }

    public static void registerRenders(){

        registerRender(batarang);
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

        if(!worldObj.isRemote){
            EntityBatarang star = new EntityBatarang(worldObj, player);
            star.motionX *= 2;
            star.motionY *= 2;
            star.motionZ *= 2;
            worldObj.spawnEntityInWorld(star);
        }
        return itemStack;
    }


}
