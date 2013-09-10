package ganymedes01.ganysnether.core.handlers;

import ganymedes01.ganysnether.items.ModItems;

import java.util.Random;

import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class MobDeathEvent {

	@ForgeSubscribe
	public void silverfishDeath(LivingDeathEvent event) {
		Random rand = new Random();
		if (!event.entity.worldObj.isRemote)
			if (event.entity instanceof EntitySilverfish) {
				event.entity.dropItem(ModItems.silverfishScale.itemID, rand.nextInt(4));
				return;
			} else if (event.entity instanceof EntityBat) {
				event.entity.dropItem(ModItems.batWing.itemID, 1 + rand.nextInt(1));
				return;
			} else if (event.entity instanceof EntityWolf) {
				event.entity.dropItem(ModItems.wolfTeeth.itemID, rand.nextInt(2));
				return;
			}
	}
}
