package com.bearSmash.projectiles;

import com.bearSmash.projectiles.mobs.VanillaDrops;
import com.bearSmash.projectiles.entity.projectile.EntityBatarang;
import com.bearSmash.projectiles.entity.projectile.EntityNinjastar;
import com.bearSmash.projectiles.entity.projectile.EntityBatclaw;
import com.bearSmash.projectiles.item.ItemBatarang;
import com.bearSmash.projectiles.item.ItemNinjastar;
import com.bearSmash.projectiles.item.ItemPlayerFireballLarge;
import com.bearSmash.projectiles.item.ItemPlayerFireballSmall;
import com.bearSmash.projectiles.item.ItemBatclaw;
import com.bearSmash.projectiles.proxy.ServerProxy;

import net.minecraft.init.Bootstrap;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.WeightedRandom;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;

/**
 *
 * Created by Bear on 3/12/2015.
 */
@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class ProjectilesMod {

    public static Item ninjastar;
    public static Item player_fireball_small;
    public static Item player_fireball_large;
    public static Item batarang;
    public static Item batclaw;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static ServerProxy proxy;

    @Instance(Reference.MOD_ID)
    public static ProjectilesMod modInstance;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event){
        ninjastar = ItemNinjastar.preInit();
        EntityNinjastar.preInit();
        ItemPlayerFireballSmall.preInit();
        ItemPlayerFireballSmall.register();
        ItemPlayerFireballLarge.preInit();
        ItemPlayerFireballLarge.register();
        batarang = ItemBatarang.preInit();
        EntityBatarang.preInit();
        EntityBatclaw.registerRender();
        batclaw = ItemBatclaw.preInit();

        MinecraftForge.EVENT_BUS.register(new VanillaDrops());
    }

    @EventHandler
    public void init(FMLInitializationEvent event){
        ItemNinjastar.init();
        EntityNinjastar.init();
        ItemPlayerFireballSmall.init();
        ItemPlayerFireballLarge.init();
        ItemBatarang.init();
        EntityBatarang.init();
        ItemBatclaw.init();
        EntityBatclaw.init();

        proxy.registerRenders();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event){

    }

    private static Item getRegisteredItem(String p_getRegisteredItem_0_) {
        return (Item)Item.itemRegistry.getObject(new ResourceLocation(p_getRegisteredItem_0_));
    }
}
