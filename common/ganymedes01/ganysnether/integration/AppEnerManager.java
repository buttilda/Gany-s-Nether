package ganymedes01.ganysnether.integration;

import ganymedes01.ganysnether.recipes.MagmaticCentrifugeRecipes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class AppEnerManager extends Integration {

	@Override
	public void init() {
	}

	@Override
	public void postInit() {
		MagmaticCentrifugeRecipes.addOreDictRecipe("oreCertusQuartz", "oreCertusQuartz", getItem("matQuartz", 2), getItem("matQuartzDust", 1));
		MagmaticCentrifugeRecipes.addOreDictRecipe("oreQuartz", "oreQuartz", new ItemStack(Item.netherQuartz, 2), getItem("matQuartzDustNether", 1));
	}

	@Override
	public String getModID() {
		return "AppliedEnergistics";
	}

	private ItemStack getItem(String name, int size) {
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

	private ItemStack getBlock(String name, int size) {
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