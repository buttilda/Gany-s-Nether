package ganymedes01.ganysnether.integration.nei;

import ganymedes01.ganysnether.core.utils.InventoryUtils;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.Reference;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import org.lwjgl.opengl.GL11;

import codechicken.lib.gui.GuiDraw;
import codechicken.nei.ItemList;
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
		return "Ore Dictionary";
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
		for (int id : OreDictionary.getOreIDs(ingredient))
			arecipes.addAll(getRecipes(OreDictionary.getOreName(id)));
	}

	@Override
	public void loadCraftingRecipes(String outputId, Object... results) {
		if (outputId.equals(Reference.MOD_ID + "OreDictionary"))
			for (String name : OreDictionary.getOreNames())
				arecipes.addAll(getRecipes(name));

		else
			super.loadCraftingRecipes(outputId, results);
	}

	@Override
	public void drawBackground(int index) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		CachedOres recipe = (CachedOres) arecipes.get(index);
		GuiDraw.drawString(recipe.name, 4, 6, Utils.getColour(64, 64, 64), false);
	}

	@Override
	public void loadTransferRects() {
		transferRects.add(new RecipeTransferRect(new Rectangle(4, 6, 50, 8), Reference.MOD_ID + "OreDictionary"));
	}

	@Override
	public void drawForeground(int recipe) {
	}

	private List<CachedOres> getRecipes(String oreID) {
		ArrayList<CachedOres> list = new ArrayList<CachedOres>();

		ArrayList<ItemStack> equivalents = new ArrayList<ItemStack>();
		for (ItemStack stack : OreDictionary.getOres(oreID))
			if (stack != null) {
				if (stack.getItemDamage() != OreDictionary.WILDCARD_VALUE)
					equivalents.add(stack);
				equivalents.addAll(getValidStacks(oreID, getValidItems(stack.getItem())));
			}

		if (equivalents.size() > 0) {
			if (equivalents.size() > 1)
				cleanItemStackArray(equivalents);
			for (ItemStack[] array : splitArray(equivalents.toArray(new ItemStack[0]), 9 * 6))
				list.add(new CachedOres(array, oreID));
		}

		return list;
	}

	private List<ItemStack> getValidItems(Item item) {
		List<ItemStack> values = ItemList.itemMap.get(item);
		if (values == null)
			return Collections.emptyList();
		return values;
	}

	private List<ItemStack> getValidStacks(String oreID, List<ItemStack> list) {
		ArrayList<ItemStack> valid = new ArrayList<ItemStack>();
		for (ItemStack stack : list)
			for (int id : OreDictionary.getOreIDs(stack))
				if (OreDictionary.getOreName(id).equals(oreID))
					valid.add(stack);
		return valid;
	}

	private void cleanItemStackArray(ArrayList<ItemStack> list) {
		for (int i = 0; i < list.size(); i++)
			for (int j = 0; j < list.size(); j++)
				if (i != j)
					if (InventoryUtils.areStacksTheSame(list.get(i), list.get(j), false)) {
						list.remove(j);
						cleanItemStackArray(list);
						return;
					}
	}

	private <T> List<T[]> splitArray(T[] array, int max) {
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

		private final PositionedStack[] equivalents;
		public String name;

		public CachedOres(ItemStack[] equivalents, String name) {
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
			return null;
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