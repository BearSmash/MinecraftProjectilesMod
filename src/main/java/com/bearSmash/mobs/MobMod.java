package com.bearSmash.mobs;

import com.bearSmash.mobs.drops.NewVanillaDrops;
import com.bearSmash.mobs.proxy.ServerProxy;

import net.minecraft.init.Bootstrap;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.WeightedRandom;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 *
 * Created by Bear on 3/12/2015.
 */
@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class MobMod {


    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static ServerProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event){
        MinecraftForge.EVENT_BUS.register(new NewVanillaDrops());
    }

    @EventHandler
    public void init(FMLInitializationEvent event){

        proxy.registerRenders();

    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event){

    }

    private static Item getRegisteredItem(String p_getRegisteredItem_0_) {
        return (Item)Item.itemRegistry.getObject(new ResourceLocation(p_getRegisteredItem_0_));
    }

}
