package com.bearSmash.mobs.proxy;

import com.bearSmash.mobs.drops.NewVanillaDrops;

import net.minecraftforge.common.MinecraftForge;

/**
 *
 * Created by Bear on 3/12/2015.
 */
public class ClientProxy extends ServerProxy {

    @Override
    public void registerRenders(){
        MinecraftForge.EVENT_BUS.register(new NewVanillaDrops());
    }
}
