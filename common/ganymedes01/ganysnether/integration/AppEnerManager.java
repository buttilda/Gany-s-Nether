package ganymedes01.ganysnether.integration;

import ganymedes01.ganysnether.lib.Reference;
import ganymedes01.ganysnether.recipes.MagmaticCentrifugeRecipes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class AppEnerManager {

	public static void init() {
		MagmaticCentrifugeRecipes.addRecipeExternal(Reference.MOD_ID, new ItemStack(Item.wheat), new ItemStack(Item.diamond), getItem("matFlour", 1), new ItemStack(Item.diamond));
		MagmaticCentrifugeRecipes.addRecipeExternal(Reference.MOD_ID, getBlock("blkQuartzOre", 1), getBlock("blkQuartzOre", 1), getItem("matQuartz", 2), getItem("matQuartzDust", 1));
	}

	private static ItemStack getItem(String name, int size) {
		try {
			Class<?> appItems = Class.forName("appeng.api.Materials");

			Object ret = appItems.getField(name).get(null);

			if (ret instanceof ItemStack) {
				ItemStack newStack = ((ItemStack) ret).copy();
				newStack.stackSize = size;
				return newStack;
			}
			return null;
		} catch (Exception e) {
		}
		return null;
	}

	private static ItemStack getBlock(String name, int size) {
		try {
			Class<?> appItems = Class.forName("appeng.api.Blocks");

			Object ret = appItems.getField(name).get(null);

			if (ret instanceof ItemStack) {
				ItemStack newStack = ((ItemStack) ret).copy();
				newStack.stackSize = size;
				return newStack;
			}
			return null;
		} catch (Exception e) {
		}
		return null;
	}
}