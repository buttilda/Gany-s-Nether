package ganymedes01.ganysnether.core.utils;

import java.util.ArrayList;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class HoeList {

	private static ArrayList<Item> hoesThatCanTillNetherrack;

	public static void init() {
		hoesThatCanTillNetherrack = new ArrayList<Item>();
		addHoe(Items.golden_hoe);
		addHoe(Items.diamond_hoe);
	}

	public static void addHoe(ItemStack hoe) {
		if (hoe != null)
			addHoe(hoe.getItem());
	}

	private static void addHoe(Item hoe) {
		if (hoe != null && !hoesThatCanTillNetherrack.contains(hoe))
			hoesThatCanTillNetherrack.add(hoe);
	}

	public static boolean canTillNetherrack(ItemStack hoe) {
		return hoe != null ? canTillNetherrack(hoe.getItem()) : false;
	}

	public static boolean canTillNetherrack(Item hoe) {
		return hoesThatCanTillNetherrack.contains(hoe);
	}
}