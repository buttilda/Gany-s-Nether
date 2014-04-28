package ganymedes01.ganysnether.recipes;

import ganymedes01.ganysnether.core.utils.UnsizedStack;
import ganymedes01.ganysnether.items.ModItems;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
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
		addMobDropAndEggTuple(new ItemStack(ModItems.skeletonSpawner), new ItemStack(Item.bone));
		addMobDropAndEggTuple(new ItemStack(ModItems.skeletonSpawner, 1, 1), new ItemStack(Item.coal));

		addMobDropAndEggTuple(50, Item.gunpowder);
		addMobDropAndEggTuple(52, Item.spiderEye);
		addMobDropAndEggTuple(59, Item.spiderEye);
		addMobDropAndEggTuple(54, Item.rottenFlesh);
		addMobDropAndEggTuple(55, Item.slimeBall);
		addMobDropAndEggTuple(56, Item.ghastTear);
		addMobDropAndEggTuple(57, Item.goldNugget);
		addMobDropAndEggTuple(58, Item.enderPearl);
		addMobDropAndEggTuple(60, ModItems.silverfishScale);
		addMobDropAndEggTuple(61, Item.blazeRod);
		addMobDropAndEggTuple(62, Item.magmaCream);
		addMobDropAndEggTuple(65, ModItems.batWing);
		addMobDropAndEggTuple(66, Item.glowstone);
		addMobDropAndEggTuple(90, Item.porkRaw);
		addMobDropAndEggTuple(91, Block.cloth);
		addMobDropAndEggTuple(92, Item.beefRaw);
		addMobDropAndEggTuple(93, Item.chickenRaw);
		addMobDropAndEggTuple(94, Item.dyePowder);
		addMobDropAndEggTuple(95, ModItems.wolfTeeth);
		addMobDropAndEggTuple(96, Block.mushroomRed);
		addMobDropAndEggTuple(97, Item.snowball);
		addMobDropAndEggTuple(98, Item.fishRaw);
		addMobDropAndEggTuple(99, Item.ingotIron);
		addMobDropAndEggTuple(100, Item.leather);
		addMobDropAndEggTuple(120, Item.emerald);
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

	private static void addMobDropAndEggTuple(int eggMeta, ItemStack drop) {
		addMobDropAndEggTuple(new ItemStack(Item.monsterPlacer, 1, eggMeta), drop);
	}

	private static void addMobDropAndEggTuple(int eggMeta, Item drop) {
		addMobDropAndEggTuple(new ItemStack(Item.monsterPlacer, 1, eggMeta), new ItemStack(drop));
	}

	private static void addMobDropAndEggTuple(int eggMeta, Block drop) {
		addMobDropAndEggTuple(new ItemStack(Item.monsterPlacer, 1, eggMeta), new ItemStack(drop));
	}

	public static ItemStack getMobDropFromEgg(ItemStack egg) {
		return eggDropTuple.get(egg);
	}
}