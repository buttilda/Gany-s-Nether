package ganymedes01.ganysnether.integration.nei;

import ganymedes01.ganysnether.core.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraftforge.oredict.OreDictionary;

import org.lwjgl.opengl.GL11;

import codechicken.core.gui.GuiDraw;
import codechicken.nei.NEIClientUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class OreDictionaryHandler extends TemplateRecipeHandler {

	@Override
	public String getRecipeName() {
		return StatCollector.translateToLocal("Ore Dictionary");
	}

	@Override
	public String getGuiTexture() {
		return null;
	}

	@Override
	public int recipiesPerPage() {
		return 1;
	}

	@Override
	public void loadUsageRecipes(ItemStack ingredient) {
		arecipes.addAll(getRecipes(ingredient.copy()));
	}

	@Override
	public void drawBackground(int index) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		CachedOres recipe = (CachedOres) arecipes.get(index);
		GuiDraw.drawString(recipe.name, 26, 4, Utils.getColour(64, 64, 64), false);
	}

	@Override
	public void drawForeground(int recipe) {
	}

	private List<CachedOres> getRecipes(ItemStack material) {
		ArrayList<CachedOres> list = new ArrayList<CachedOres>();

		ArrayList<ItemStack> equivalents = new ArrayList<ItemStack>();
		for (ItemStack stack : OreDictionary.getOres(OreDictionary.getOreID(material)))
			if (stack != null)
				equivalents.addAll(getValidStacks(material, NEIClientUtils.getValidItems(stack.itemID)));

		if (equivalents.size() > 0)
			for (ItemStack[] array : splitArray(equivalents.toArray(new ItemStack[0]), 9 * 6))
				list.add(new CachedOres(material, array, OreDictionary.getOreName(OreDictionary.getOreID(material))));

		return list;
	}

	private static List<ItemStack> getValidStacks(ItemStack material, List<ItemStack> list) {
		ArrayList<ItemStack> valid = new ArrayList<ItemStack>();
		String name = OreDictionary.getOreName(OreDictionary.getOreID(material));
		for (ItemStack stack : list)
			if (OreDictionary.getOreName(OreDictionary.getOreID(stack)) == name)
				valid.add(stack);
		return valid;
	}

	private static <T> List<T[]> splitArray(T[] array, int max) {
		int x = array.length / max;
		int lower = 0;
		int upper = 0;

		List<T[]> list = new ArrayList<T[]>();
		if (array.length <= max) {
			list.add(array);
			return list;
		}

		for (int i = 0; i < x; i++) {
			upper += max;
			list.add(Arrays.copyOfRange(array, lower, upper));
			lower = upper;
		}
		if (upper < array.length - 1) {
			lower = upper;
			upper = array.length;
			list.add(Arrays.copyOfRange(array, lower, upper));
		}

		return list;
	}

	private class CachedOres extends CachedRecipe {

		private final PositionedStack material;
		private final PositionedStack[] equivalents;
		public String name;

		public CachedOres(ItemStack stack, ItemStack[] equivalents, String name) {
			material = new PositionedStack(stack, 3, 0);

			ArrayList<PositionedStack> list = new ArrayList<PositionedStack>();
			int i = 0, j = 1;
			for (ItemStack s : equivalents) {
				list.add(new PositionedStack(s, 3 + i * 18, j * 18));
				i++;
				if (i == 9) {
					i = 0;
					j++;
				}
			}
			this.equivalents = list.toArray(new PositionedStack[0]);
			this.name = name;
		}

		@Override
		public PositionedStack getIngredient() {
			return material;
		}

		@Override
		public List<PositionedStack> getOtherStacks() {
			return Arrays.asList(equivalents);
		}

		@Override
		public PositionedStack getResult() {
			return null;
		}
	}
}