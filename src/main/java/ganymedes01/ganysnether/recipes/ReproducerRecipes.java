package ganymedes01.ganysnether.recipes;

import ganymedes01.ganysnether.core.utils.UnsizedStack;
import ganymedes01.ganysnether.items.ModItems;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class ReproducerRecipes {

	private static HashMap<UnsizedStack, ItemStack> eggDropTuple = new HashMap<UnsizedStack, ItemStack>();

	static {
		addMobDropAndEggTuple(new ItemStack(ModItems.skeletonSpawner), new ItemStack(Items.bone));
		addMobDropAndEggTuple(new ItemStack(ModItems.skeletonSpawner, 1, 1), new ItemStack(Items.coal));

		addMobDropAndEggTuple(50, Items.gunpowder);
		addMobDropAndEggTuple(52, Items.spider_eye);
		addMobDropAndEggTuple(59, Items.spider_eye);
		addMobDropAndEggTuple(54, Items.rotten_flesh);
		addMobDropAndEggTuple(55, Items.slime_ball);
		addMobDropAndEggTuple(56, Items.ghast_tear);
		addMobDropAndEggTuple(57, Items.gold_nugget);
		addMobDropAndEggTuple(58, Items.ender_pearl);
		addMobDropAndEggTuple(60, ModItems.silverfishScale);
		addMobDropAndEggTuple(61, Items.blaze_rod);
		addMobDropAndEggTuple(62, Items.magma_cream);
		addMobDropAndEggTuple(65, ModItems.batWing);
		addMobDropAndEggTuple(66, Items.glowstone_dust);
		addMobDropAndEggTuple(90, Items.porkchop);
		addMobDropAndEggTuple(91, Blocks.wool);
		addMobDropAndEggTuple(92, Items.beef);
		addMobDropAndEggTuple(93, Items.chicken);
		addMobDropAndEggTuple(94, Items.dye);
		addMobDropAndEggTuple(95, ModItems.wolfTeeth);
		addMobDropAndEggTuple(96, Blocks.red_mushroom);
		addMobDropAndEggTuple(97, Items.snowball);
		addMobDropAndEggTuple(98, Items.fish);
		addMobDropAndEggTuple(99, Items.iron_ingot);
		addMobDropAndEggTuple(100, Items.leather);
		addMobDropAndEggTuple(120, Items.emerald);
	}

	public static Map<UnsizedStack, ItemStack> getTuples() {
		return eggDropTuple;
	}

	public static boolean isValidSpawnEgg(ItemStack stack) {
		return eggDropTuple.containsKey(stack);
	}

	public static void addMobDropAndEggTuple(ItemStack egg, ItemStack drop) {
		if (egg != null && drop != null)
			if (!eggDropTuple.containsKey(egg)) {
				egg.stackSize = 1;
				drop.stackSize = 1;
				eggDropTuple.put(new UnsizedStack(egg), drop);
			}
	}

	private static void addMobDropAndEggTuple(int eggMeta, Item drop) {
		addMobDropAndEggTuple(new ItemStack(Items.spawn_egg, 1, eggMeta), new ItemStack(drop));
	}

	private static void addMobDropAndEggTuple(int eggMeta, Block drop) {
		addMobDropAndEggTuple(new ItemStack(Items.spawn_egg, 1, eggMeta), new ItemStack(drop));
	}

	public static ItemStack getMobDropFromEgg(ItemStack egg) {
		return eggDropTuple.get(egg);
	}
}