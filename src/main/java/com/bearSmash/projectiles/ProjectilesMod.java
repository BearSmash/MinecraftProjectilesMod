package com.bearSmash.projectiles;

import com.bearSmash.projectiles.item.ItemNinjastar;
import com.bearSmash.projectiles.item.ItemPlayerFireballLarge;
import com.bearSmash.projectiles.item.ItemPlayerFireballSmall;
import com.bearSmash.projectiles.proxy.ServerProxy;
import net.minecraft.init.Bootstrap;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.WeightedRandom;
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
public class ProjectilesMod {

    public static Item ninjastar;
    public static Item player_fireball_small;
    public static Item player_fireball_large;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static ServerProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event){
        ItemNinjastar.preInit();
        ItemNinjastar.register();
        ItemPlayerFireballSmall.preInit();
        ItemPlayerFireballSmall.register();
        ItemPlayerFireballLarge.preInit();
        ItemPlayerFireballLarge.register();
    }

    @EventHandler
    public void init(FMLInitializationEvent event){
        ItemNinjastar.init();
        ItemPlayerFireballSmall.init();
        ItemPlayerFireballLarge.init();
        proxy.registerRenders();

    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event){

    }

    private static Item getRegisteredItem(String p_getRegisteredItem_0_) {
        return (Item)Item.itemRegistry.getObject(new ResourceLocation(p_getRegisteredItem_0_));
    }

    static {
        if (!Bootstrap.isRegistered()) {
            throw new RuntimeException("Accessed Items before Bootstrap!");
        } else {
            ninjastar = getRegisteredItem("ninjastar");
            player_fireball_small = getRegisteredItem("player_fireball_small");
            player_fireball_large = getRegisteredItem("player_fireball_large");
        }
    }
}
