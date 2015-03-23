package com.bearSmash.projectiles.proxy;

import com.bearSmash.projectiles.client.renderer.entity.RenderNinjastar;
import com.bearSmash.projectiles.entity.projectile.EntityBatarang;
import com.bearSmash.projectiles.entity.projectile.EntityBatclaw;
import com.bearSmash.projectiles.entity.projectile.EntityNinjastar;
import com.bearSmash.projectiles.item.ItemBatarang;
import com.bearSmash.projectiles.item.ItemBatclaw;
import com.bearSmash.projectiles.item.ItemNinjastar;
import com.bearSmash.projectiles.item.ItemPlayerFireballLarge;
import com.bearSmash.projectiles.item.ItemPlayerFireballSmall;
import com.bearSmash.projectiles.mobs.VanillaDrops;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

/**
 *
 * Created by Bear on 3/12/2015.
 */
public class ClientProxy extends ServerProxy {

    @Override
    public void registerRenders(){
    	MinecraftForge.EVENT_BUS.register(new VanillaDrops());
        ItemNinjastar.registerRenders();
        EntityNinjastar.registerRenders();
        ItemPlayerFireballSmall.registerRenders();
        ItemPlayerFireballLarge.registerRenders();
        ItemBatarang.registerRenders();
        ItemBatclaw.registerRenders();
        EntityBatarang.registerRenders();
        EntityBatclaw.registerRenders();
    }
}
