package ganymedes01.ganysnether.integration;

import ganymedes01.ganysnether.recipes.MagmaticCentrifugeRecipes;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class TConstructManager extends Integration {

	@Override
	public void init() {
		registerOre("Cobalt");
		registerOre("Ardite");
		registerOre("Aluminium");
	}

	private void registerOre(String ore) {
		ItemStack ingot = getStack("ingot" + ore, 3);
		ItemStack nugget = getStack("nugget" + ore, 1);
		if (ingot != null && nugget != null)
			MagmaticCentrifugeRecipes.addOreDictRecipe("ore" + ore, "ore" + ore, ingot, nugget);
	}

	private ItemStack getStack(String ore, int size) {
		ArrayList<ItemStack> ores = OreDictionary.getOres(ore);
		if (ores != null && !ores.isEmpty()) {
			ItemStack stack = ores.get(0).copy();
			stack.stackSize = size;
			return stack;
		}
		return null;
	}

	@Override
	public void postInit() {
	}

	@Override
	public String getModID() {
		return "TConstruct";
	}
}