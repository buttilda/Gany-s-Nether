package ganymedes01.ganysnether.integration.nei;

import ganymedes01.ganysnether.client.gui.inventory.GuiMagmaticCentrifuge;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.Reference;
import ganymedes01.ganysnether.lib.Strings;
import ganymedes01.ganysnether.recipes.MagmaticCentrifugeRecipes;
import ganymedes01.ganysnether.recipes.centrifuge.CentrifugeRecipe;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import codechicken.lib.gui.GuiDraw;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;

/**
 * Gany's Nether
 *
 * @author ganymedes01
 *
 */

public class MagmaticCentrifugeRecipeHandler extends TemplateRecipeHandler {

	private boolean change;
	private int ticks;
	private static final Random rand = new Random();

	@Override
	public Class<? extends GuiContainer> getGuiClass() {
		return GuiMagmaticCentrifuge.class;
	}

	@Override
	public String getRecipeName() {
		return StatCollector.translateToLocal(Utils.getConainerName(Strings.Blocks.MAGMATIC_CENTRIFUGE_NAME));
	}

	public String getRecipeId() {
		return Reference.MOD_ID + "." + Strings.Blocks.MAGMATIC_CENTRIFUGE_NAME;
	}

	@Override
	public String getGuiTexture() {
		return Utils.getGUITexture(Strings.Blocks.MAGMATIC_CENTRIFUGE_NAME);
	}

	@Override
	public String getOverlayIdentifier() {
		return Strings.Blocks.MAGMATIC_CENTRIFUGE_NAME;
	}

	@Override
	public void loadTransferRects() {
		transferRects.add(new RecipeTransferRect(new Rectangle(50, 65, 10, 8), getRecipeId()));
		transferRects.add(new RecipeTransferRect(new Rectangle(107, 65, 10, 8), getRecipeId()));
	}

	@Override
	public void drawBackground(int recipe) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GuiDraw.changeTexture(getGuiTexture());
		GuiDraw.drawTexturedModalRect(0, 0, 5, 50, 140, 65);

		GuiDraw.drawTexturedModalRect(29, 19, 176, 3, 18, 18);
		GuiDraw.drawTexturedModalRect(119, 19, 176, 3, 18, 18);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		ticks++;
		if (ticks >= 20) {
			ticks = 0;
			change = true;
		}
	}

	@Override
	public void drawExtras(int recipe) {
		if (change) {
			for (PositionedStack stack : arecipes.get(recipe).getIngredients())
				stack.setPermutationToRender(rand.nextInt(stack.items.length));
			change = false;
		}
	}

	@Override
	public void loadCraftingRecipes(String outputId, Object... results) {
		if (outputId.equals(getRecipeId()))
			for (CentrifugeRecipe recipe : MagmaticCentrifugeRecipes.getRecipes())
				arecipes.add(new CachedCentrifugeRecipe(recipe));
		else
			super.loadCraftingRecipes(outputId, results);
	}

	@Override
	public void loadCraftingRecipes(ItemStack result) {
		for (CentrifugeRecipe recipe : MagmaticCentrifugeRecipes.getRecipes())
			if (recipe.isPartOfResult(result))
				arecipes.add(new CachedCentrifugeRecipe(recipe));
	}

	@Override
	public void loadUsageRecipes(ItemStack ingredient) {
		for (CentrifugeRecipe recipe : MagmaticCentrifugeRecipes.getRecipes())
			if (recipe.containsMaterial(ingredient))
				arecipes.add(new CachedCentrifugeRecipe(recipe));
	}

	private class CachedCentrifugeRecipe extends CachedRecipe {

		private final ArrayList<PositionedStack> materials = new ArrayList<PositionedStack>();
		private final ArrayList<PositionedStack> result = new ArrayList<PositionedStack>();

		public CachedCentrifugeRecipe(CentrifugeRecipe recipe) {
			materials.add(new PositionedStack(recipe.getMaterial(1), 30, 20));
			materials.add(new PositionedStack(recipe.getMaterial(2), 120, 20));

			ItemStack[] results = recipe.getResult();

			for (int i = 0; i < 2; i++)
				for (int j = 0; j < 2; j++) {
					int index = j + 2 * i;
					if (index < results.length)
						result.add(new PositionedStack(results[index], 66 + 18 * i, 12 + 18 * j));
				}
		}

		@Override
		public List<PositionedStack> getIngredients() {
			return materials;
		}

		@Override
		public PositionedStack getResult() {
			return result.get(0);
		}

		@Override
		public List<PositionedStack> getOtherStacks() {
			List<PositionedStack> result = new ArrayList<PositionedStack>();
			if (this.result.size() >= 2)
				result.add(this.result.get(1));
			if (this.result.size() >= 3)
				result.add(this.result.get(2));
			if (this.result.size() == 4)
				result.add(this.result.get(3));

			return result;
		}
	}
}