package ganymedes01.ganysnether.integration;

import ganymedes01.ganysnether.recipes.MagmaticCentrifugeRecipes;
import net.minecraft.item.ItemStack;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class IC2Manager extends Integration {

	@Override
	public void init() {
		MagmaticCentrifugeRecipes.addOreDictRecipe("ingotCopper", "ingotTin", getItem("bronzeIngot", 2));
		MagmaticCentrifugeRecipes.addRecipe(getItem("copperDust"), getItem("tinDust"), getItem("bronzeDust", 2));
	}

	@Override
	public void postInit() {
	}

	@Override
	public String getModID() {
		return "IC2";
	}

	private ItemStack getItem(String name) {
		return getItem(name, 1);
	}

	private ItemStack getItem(String name, int size) {
		try {
			Class<?> Ic2Items = Class.forName("ic2.core.Ic2Items");

			Object ret = Ic2Items.getField(name).get(null);

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