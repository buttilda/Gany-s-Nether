package ganymedes01.ganysnether.integration;

import ganymedes01.ganysnether.items.ModItems;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class ForestryManager extends Integration {

	@Override
	public void init() {
	}

	@Override
	public void postInit() {
		addSqueezerRecipe(new ItemStack(ModItems.ghostSeeds), 20);
		addSqueezerRecipe(new ItemStack(ModItems.quarzBerrySeeds), 20);
		addSqueezerRecipe(new ItemStack(ModItems.hellBushSeeds), 15);
		addSqueezerRecipe(new ItemStack(ModItems.witherShrubSeeds), 2000);
	}

	@Override
	public String getModID() {
		return "Forestry";
	}

	private void addSqueezerRecipe(ItemStack seeds, int amount) {
		try {
			Class<?> recipeManagers = Class.forName("forestry.api.recipes.RecipeManagers");
			Field field = recipeManagers.getField("squeezerManager");
			Object ret = field.get(null);
			Method addRecipe = ret.getClass().getMethod("addRecipe", int.class, ItemStack[].class, FluidStack.class);
			addRecipe.invoke(ret, 10, new ItemStack[] { seeds }, new FluidStack(FluidRegistry.getFluid("seedoil"), amount));

		} catch (Exception e) {
		}
	}
}