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
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
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
		return StatCollector.translateToLocal(Utils.getConainerName(Strings.Blocks.VOLCANIC_FURNACE_NAME));
	}

	public String getRecipeId() {
		return Reference.MOD_ID + "." + Strings.Blocks.VOLCANIC_FURNACE_NAME;
	}

	@Override
	public String getGuiTexture() {
		return Utils.getGUITexture(Strings.Blocks.VOLCANIC_FURNACE_NAME);
	}

	@Override
	public String getOverlayIdentifier() {
		return Strings.Blocks.VOLCANIC_FURNACE_NAME;
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
		drawTexturedModelRectFromIcon(68, 25, Block.lavaStill.getIcon(0, 0), prog, 15);

		changeTexture(getGuiTexture());
		drawTexturedModalRect(68, 25, 177, 14, 22, 15);

		CachedRecipe recipe = arecipes.get(index);
		if (recipe instanceof CachedLavaYield) {
			CachedLavaYield yieldRecipe = (CachedLavaYield) recipe;
			GuiDraw.fontRenderer.drawString(yieldRecipe.getYield() + " mB", 100, 28, Utils.getColour(0, 0, 0));
			GuiDraw.fontRenderer.drawString("1000 mB = 1 " + StatCollector.translateToLocal(Item.bucketLava.getUnlocalizedName() + ".name"), 20, 46, Utils.getColour(0, 0, 0));
		}
	}

	private void drawTexturedModelRectFromIcon(int x, int y, Icon icon, int width, int height) {
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(x + 0, y + height, 0.0F, icon.getMinU(), icon.getMaxV());
		tessellator.addVertexWithUV(x + width, y + height, 0.0F, icon.getMaxU(), icon.getMaxV());
		tessellator.addVertexWithUV(x + width, y + 0, 0.0F, icon.getMaxU(), icon.getMinV());
		tessellator.addVertexWithUV(x + 0, y + 0, 0.0F, icon.getMinU(), icon.getMinV());
		tessellator.draw();
	}

	@Override
	public void loadUsageRecipes(ItemStack ingredient) {
		int lavaYield = VolcanicFurnaceHandler.getItemBurnTime(ingredient);
		if (lavaYield != 16 && lavaYield > 0)
			arecipes.add(new CachedLavaYield(ingredient.copy().splitStack(1)));
	}

	private class CachedLavaYield extends CachedRecipe {

		private final PositionedStack material;

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