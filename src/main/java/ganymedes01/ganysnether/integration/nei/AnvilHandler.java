package ganymedes01.ganysnether.integration.nei;

import ganymedes01.ganysnether.lib.Reference;

import java.awt.Rectangle;

import net.minecraft.item.ItemStack;

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

public class AnvilHandler extends TemplateRecipeHandler {

	@Override
	public String getRecipeName() {
		return "Anvil Repair";
	}

	@Override
	public String getGuiTexture() {
		return "textures/gui/container/anvil.png";
	}

	@Override
	public int recipiesPerPage() {
		return 2;
	}

	@Override
	public void drawBackground(int recipe) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GuiDraw.changeTexture(getGuiTexture());
		GuiDraw.drawTexturedModalRect(0, 0, 6, 4, 150, 65);
		GuiDraw.drawTexturedModalRect(53, 16, 0, 166, 110, 16);
	}

	@Override
	public void loadUsageRecipes(ItemStack repair) {
		for (ItemStack stack : ItemList.items)
			if (stack != null && stack.getItem().getIsRepairable(stack, repair))
				arecipes.add(new CachedRepair(stack, repair));
	}

	@Override
	public void loadCraftingRecipes(ItemStack stack) {
		for (ItemStack repair : ItemList.items)
			if (stack != null && stack.getItem().getIsRepairable(stack, repair))
				arecipes.add(new CachedRepair(stack, repair));
	}

	@Override
	public void loadCraftingRecipes(String outputId, Object... results) {
		if (outputId.equals(Reference.MOD_ID + "AnvilRepair")) {
			for (ItemStack stack : ItemList.items)
				for (ItemStack repair : ItemList.items)
					if (stack != null && stack.getItem().getIsRepairable(stack, repair))
						arecipes.add(new CachedRepair(stack, repair));
		} else
			super.loadCraftingRecipes(outputId, results);
	}

	@Override
	public void loadTransferRects() {
		transferRects.add(new RecipeTransferRect(new Rectangle(97, 43, 25, 16), Reference.MOD_ID + "AnvilRepair"));
	}

	private class CachedRepair extends CachedRecipe {

		private final PositionedStack stack;
		private final PositionedStack repair;

		public CachedRepair(ItemStack stack, ItemStack repair) {
			this.stack = new PositionedStack(stack, 21, 43);
			this.repair = new PositionedStack(repair, 70, 43);
		}

		@Override
		public PositionedStack getIngredient() {
			ItemStack s = stack.item.copy();
			float ratio = cycleticks % 48 / 48F;
			s.setItemDamage((int) (s.getMaxDamage() * ratio));
			return new PositionedStack(s, 21, 43);
		}

		@Override
		public PositionedStack getOtherStack() {
			return new PositionedStack(stack.item, 128, 43);
		}

		@Override
		public PositionedStack getResult() {
			return repair;
		}
	}
}