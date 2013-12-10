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

public class IC2Manager extends Integration {

	@Override
	public void init() {
		registerOre("copper");
		registerOre("tin");
		registerOre("silver");
		registerOre("lead");
		registerOre("iron", new ItemStack(Item.ingotIron, 2));
		registerOre("gold", new ItemStack(Item.ingotGold, 2));

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

	private void registerOre(String ore) {
		registerOre(ore, getItem(ore + "Ingot", 2));
	}

	private void registerOre(String ore, ItemStack ingot) {
		char first = Character.toUpperCase(ore.charAt(0));
		String Ore = first + ore.substring(1);

		MagmaticCentrifugeRecipes.addOreDictRecipe("ore" + Ore, "ore" + Ore, ingot, getItem("small" + Ore + "Dust"));
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