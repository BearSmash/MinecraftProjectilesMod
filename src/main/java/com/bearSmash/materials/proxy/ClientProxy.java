package com.bearSmash.materials.proxy;

import net.minecraftforge.common.MinecraftForge;

import com.bearSmash.mobs.drops.NewVanillaDrops;
import com.bearSmash.materials.entity.material.EntityBatclaw;
import com.bearSmash.projectiles.entity.projectile.EntityNinjastar;
import com.bearSmash.projectiles.item.ItemBatarang;
import com.bearSmash.materials.item.ItemBatclaw;
import com.bearSmash.projectiles.item.ItemNinjastar;
import com.bearSmash.projectiles.item.ItemPlayerFireballLarge;
import com.bearSmash.projectiles.item.ItemPlayerFireballSmall;

/**
 *
 * Created by Bear on 3/12/2015.
 */
public class ClientProxy extends ServerProxy {

    @Override
    public void registerRenders(){
        ItemBatclaw.registerRenders();
        EntityBatclaw.registerRenders();
    }
}
