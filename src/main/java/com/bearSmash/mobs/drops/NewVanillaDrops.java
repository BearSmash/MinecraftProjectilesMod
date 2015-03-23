package com.bearSmash.mobs.drops;

import java.util.Random;

import net.minecraft.entity.passive.EntityBat;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import com.bearSmash.materials.item.ItemBatclaw;


public class NewVanillaDrops {
	public static double rand;
	
	@SubscribeEvent
	public void playerKilledBat(LivingDropsEvent event){
		if (event.source.getDamageType().equals("player")){ //checks player damage
			System.out.println("You killed a bat! You Monster!");
			rand = Math.random();
			if (event.entityLiving instanceof EntityBat){
				System.out.print(rand); //debug print
				if(rand < .5D){ //100% drop chance FOR TESTING ONLY" TODO: lower chance after testing item drop. 
					event.entityLiving.dropItem(ItemBatclaw.batclaw, 1);
				}
			}
		}
	}
}
