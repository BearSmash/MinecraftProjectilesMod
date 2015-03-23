package com.bearSmash.projectiles.mobs;

import java.util.Random;

import net.minecraft.entity.passive.EntityBat;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import com.bearSmash.projectiles.item.ItemBatclaw;


public class VanillaDrops {
	public static double rand;
	
	@SubscribeEvent
	public void playerKilledBat(LivingDropsEvent event){
		if (event.source.getDamageType().equals("player")){ //checks player damage
			System.out.println("You killed a bat! You Monster!");
			rand = Math.random();
			if (event.entityLiving instanceof EntityBat){
				System.out.print(rand); //debug print
				if(rand < .5D){ //50% drop chance
					event.entityLiving.dropItem(ItemBatclaw.batclaw, 1);
				}
			}
		}
	}
}

