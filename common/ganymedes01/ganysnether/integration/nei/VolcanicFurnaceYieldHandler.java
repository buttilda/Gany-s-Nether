package ganymedes01.ganysnether.integration.nei;

import static codechicken.core.gui.GuiDraw.changeTexture;
import static codechicken.core.gui.GuiDraw.drawTexturedModalRect;
import ganymedes01.ganysnether.client.gui.inventory.GuiVolcanicFurnace;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.Reference;
import ganymedes01.ganysnether.lib.Strings;
import ganymedes01.ganysnether.recipes.VolcanicFurnaceHandler;
import net.minecraft.block.Block;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.TextureMap;
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

public class VolcanicFurnaceYieldHandler extends TemplateRecipeHandler {

	@Override
	public Class<? extends GuiContainer> getGuiClass() {
		return GuiVolcanicFurnace.class;
	}

	@Override
	public String getRecipeName() {
		return StatCollector.translateToLocal(Utils.getConainerName(Strings.VOLCANIC_FURNACE_NAME));
	}

	public String getRecipeId() {
		return Reference.MOD_ID + "." + Strings.VOLCANIC_FURNACE_NAME;
	}

	@Override
	public String getGuiTexture() {
		return Utils.getGUITexture(Strings.VOLCANIC_FURNACE_NAME);
	}

	@Override
	public String getOverlayIdentifier() {
		return Strings.VOLCANIC_FURNACE_NAME;
	}

	@Override
	public int recipiesPerPage() {
		return 1;
	}

	@Override
	public void drawBackground(int index) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		changeTexture(getGuiTexture());
		drawTexturedModalRect(0, 0, 5, 11, 98, 65);

		int prog = (int) (22 * (cycleticks >= 20 ? (cycleticks - 20) % 20 / 20.0F : 0.0F));
		changeTexture(TextureMap.locationBlocksTexture);
		GuiDraw.gui.drawTexturedModelRectFromIcon(68, 25, Block.lavaStill.getIcon(0, 0), prog, 15);

		changeTexture(getGuiTexture());
		drawTexturedModalRect(68, 25, 177, 14, 22, 15);

		CachedRecipe recipe = arecipes.get(index);
		if (recipe instanceof CachedLavaYield) {
			CachedLavaYield yieldRecipe = (CachedLavaYield) recipe;
			GuiDraw.fontRenderer.drawString(yieldRecipe.getYield() + " mB", 100, 28, Utils.getColour(0, 0, 0));
		}
	}

	@Override
	public void loadUsageRecipes(ItemStack ingredient) {
		if (VolcanicFurnaceHandler.getItemBurnTime(ingredient) > 0)
			arecipes.add(new CachedLavaYield(ingredient.copy().splitStack(1)));
	}

	private class CachedLavaYield extends CachedRecipe {

		private PositionedStack material;

		public CachedLavaYield(ItemStack stack) {
			material = new PositionedStack(stack, 43, 25);
		}

		public int getYield() {
			return VolcanicFurnaceHandler.getItemBurnTime(material.item);
		}

		@Override
		public PositionedStack getIngredient() {
			return material;
		}

		@Override
		public PositionedStack getResult() {
			return null;
		}
	}
}