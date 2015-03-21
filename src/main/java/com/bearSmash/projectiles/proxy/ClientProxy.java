package com.bearSmash.projectiles.proxy;

import com.bearSmash.projectiles.client.renderer.entity.RenderNinjastar;
import com.bearSmash.projectiles.entity.projectile.EntityNinjastar;
import com.bearSmash.projectiles.item.ItemBatarang;
import com.bearSmash.projectiles.item.ItemNinjastar;
import com.bearSmash.projectiles.item.ItemPlayerFireballLarge;
import com.bearSmash.projectiles.item.ItemPlayerFireballSmall;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

/**
 *
 * Created by Bear on 3/12/2015.
 */
public class ClientProxy extends ServerProxy {

    @Override
    public void registerRenders(){
        ItemNinjastar.registerRenders();
        EntityNinjastar.registerRenders();
        ItemPlayerFireballSmall.registerRenders();
        ItemPlayerFireballLarge.registerRenders();
        ItemBatarang.registerRenders();
        EntityNinjastar.registerRenders();
    }
}
