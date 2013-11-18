package ganymedes01.ganysnether.integration.nei;

import ganymedes01.ganysnether.client.gui.inventory.GuiReproducer;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.Reference;
import ganymedes01.ganysnether.lib.Strings;
import ganymedes01.ganysnether.recipes.ReproducerRecipes;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import codechicken.core.gui.GuiDraw;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class ReproducerRecipeHandler extends TemplateRecipeHandler {

	private static float ticks;

	@Override
	public Class<? extends GuiContainer> getGuiClass() {
		return GuiReproducer.class;
	}

	@Override
	public String getRecipeName() {
		return StatCollector.translateToLocal(Utils.getConainerName(Strings.REPRODUCER_NAME));
	}

	public String getRecipeId() {
		return Reference.MOD_ID + "." + Strings.REPRODUCER_NAME;
	}

	@Override
	public String getGuiTexture() {
		return Utils.getGUITexture(Strings.REPRODUCER_NAME);
	}

	@Override
	public String getOverlayIdentifier() {
		return Strings.REPRODUCER_NAME;
	}

	@Override
	public void loadTransferRects() {
		transferRects.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(98, 26, 13, 16), getRecipeId(), new Object[0]));
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		ticks += 0.05F;

		if (ticks > ReproducerRecipes.getTupes().size())
			ticks = 0.0F;
	}

	@Override
	public void drawBackground(int recipe) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GuiDraw.changeTexture(getGuiTexture());
		GuiDraw.drawTexturedModalRect(0, 0, 5, 9, 148, 65);
	}

	@Override
	public void drawExtras(int recipe) {
		float prog = cycleticks >= 20 ? (cycleticks - 20) % 20 / 20.0F : 0.0F;

		drawProgressBar(98, 26, 181, 14, 13, 16, prog, 0);
	}

	@Override
	public void loadCraftingRecipes(String outputId, Object... results) {
		if (outputId.equals(getRecipeId()))
			for (Entry<ItemStack, ItemStack> tuple : ReproducerRecipes.getTupes().entrySet())
				arecipes.add(new CachedReproducerRecipe(tuple.getKey(), tuple.getValue()));
		else
			super.loadCraftingRecipes(outputId, results);
	}

	@Override
	public void loadCraftingRecipes(ItemStack result) {
		for (Entry<ItemStack, ItemStack> tuple : ReproducerRecipes.getTupes().entrySet())
			if (tuple.getKey().itemID == result.itemID && tuple.getKey().getItemDamage() == result.getItemDamage())
				arecipes.add(new CachedReproducerRecipe(tuple.getKey(), tuple.getValue()));
	}

	@Override
	public void loadUsageRecipes(ItemStack ingredient) {
		for (Entry<ItemStack, ItemStack> tuple : ReproducerRecipes.getTupes().entrySet())
			if (tuple.getKey().itemID == ingredient.itemID && tuple.getKey().getItemDamage() == ingredient.getItemDamage() || tuple.getValue().itemID == ingredient.itemID && tuple.getValue().getItemDamage() == ingredient.getItemDamage())
				arecipes.add(new CachedReproducerRecipe(tuple.getKey(), tuple.getValue()));
	}

	private class CachedReproducerRecipe extends CachedRecipe {

		private ArrayList<PositionedStack> materials = new ArrayList<PositionedStack>();
		private PositionedStack result;

		public CachedReproducerRecipe(ItemStack egg, ItemStack drop) {
			materials.add(new PositionedStack(egg, 31, 24));
			materials.add(new PositionedStack(drop, 49, 6));

			result = new PositionedStack(egg, 125, 24);
		}

		@Override
		public List<PositionedStack> getIngredients() {
			return materials;
		}

		@Override
		public List<PositionedStack> getOtherStacks() {
			ArrayList<PositionedStack> extras = new ArrayList<PositionedStack>();

			int i = 0;
			for (Entry<ItemStack, ItemStack> tuple : ReproducerRecipes.getTupes().entrySet()) {
				i++;
				if (i >= ReproducerRecipes.getTupes().size())
					i = 0;

				if (i == (int) ReproducerRecipeHandler.ticks) {
					extras.add(new PositionedStack(tuple.getKey(), 67, 24));
					extras.add(new PositionedStack(tuple.getValue(), 49, 42));
					return extras;
				}
			}
			return extras;
		}

		@Override
		public PositionedStack getResult() {
			return result;
		}
	}
}