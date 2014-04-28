package ganymedes01.ganysnether.integration.nei;

import static codechicken.core.gui.GuiDraw.changeTexture;
import static codechicken.core.gui.GuiDraw.drawTexturedModalRect;
import ganymedes01.ganysnether.client.gui.inventory.GuiVolcanicFurnace;
import ganymedes01.ganysnether.core.utils.UnsizedStack;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.Reference;
import ganymedes01.ganysnether.lib.Strings;
import ganymedes01.ganysnether.recipes.VolcanicFurnaceHandler;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import codechicken.core.gui.GuiDraw;
import codechicken.nei.ItemList;
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
		return 2;
	}

	@Override
	public void drawBackground(int index) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		changeTexture(getGuiTexture());
		drawTexturedModalRect(0, 0, 5, 11, 98, 65);

		changeTexture(TextureMap.locationBlocksTexture);
		drawTexturedModelRectFromIcon(68, 25, Blocks.lava.getIcon(0, 0), cycleticks % 20, 15);

		changeTexture(getGuiTexture());
		drawTexturedModalRect(68, 25, 177, 14, 22, 15);

		CachedRecipe recipe = arecipes.get(index);
		if (recipe instanceof CachedLavaYield) {
			CachedLavaYield yieldRecipe = (CachedLavaYield) recipe;
			GuiDraw.fontRenderer.drawString(VolcanicFurnaceHandler.getUnitParsedValued(yieldRecipe.getYield(), "B", 0), 93, 28, Utils.getColour(0, 0, 0));
			GuiDraw.fontRenderer.drawString("1000 mB = 1 " + StatCollector.translateToLocal(Items.lava_bucket.getUnlocalizedName() + ".name"), 20, 46, Utils.getColour(0, 0, 0));
			GuiDraw.fontRenderer.drawString("1 B = 1 " + StatCollector.translateToLocal(Items.lava_bucket.getUnlocalizedName() + ".name"), 20, 55, Utils.getColour(0, 0, 0));
		}
	}

	private void drawTexturedModelRectFromIcon(int x, int y, IIcon icon, int width, int height) {
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(x + 0, y + height, 0.0F, icon.getMinU(), icon.getMaxV());
		tessellator.addVertexWithUV(x + width, y + height, 0.0F, icon.getMaxU(), icon.getMaxV());
		tessellator.addVertexWithUV(x + width, y + 0, 0.0F, icon.getMaxU(), icon.getMinV());
		tessellator.addVertexWithUV(x + 0, y + 0, 0.0F, icon.getMinU(), icon.getMinV());
		tessellator.draw();
	}

	@Override
	public void loadTransferRects() {
		transferRects.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(67, 26, 25, 16), getRecipeId(), new Object[0]));
	}

	@Override
	public void loadCraftingRecipes(String outputId, Object... results) {
		HashMap<UnsizedStack, Integer> map = new HashMap<UnsizedStack, Integer>();
		if (outputId.equals(getRecipeId())) {
			for (ItemStack stack : ItemList.items) {
				int lavaYield = VolcanicFurnaceHandler.getBurnTime(stack);
				if (lavaYield != 16 && lavaYield > 0)
					map.put(new UnsizedStack(stack), lavaYield);
			}
			LinkedHashMap<UnsizedStack, Integer> sortedMap = sortHashMapByValues(map);
			for (Entry<UnsizedStack, Integer> entry : sortedMap.entrySet())
				arecipes.add(new CachedLavaYield(entry.getKey().getStack(), entry.getValue()));
		} else
			super.loadCraftingRecipes(outputId, results);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <T, V> LinkedHashMap<T, V> sortHashMapByValues(HashMap<T, V> map) {
		List mapKeys = new ArrayList(map.keySet());
		List mapValues = new ArrayList(map.values());
		Collections.sort(mapValues);
		Collections.reverse(mapValues);
		Collections.sort(mapKeys);
		Collections.reverse(mapKeys);

		LinkedHashMap sortedMap = new LinkedHashMap();

		Iterator valueIt = mapValues.iterator();
		while (valueIt.hasNext()) {
			Object val = valueIt.next();
			Iterator keyIt = mapKeys.iterator();

			while (keyIt.hasNext()) {
				Object key = keyIt.next();
				String comp1 = map.get(key).toString();
				String comp2 = val.toString();

				if (comp1.equals(comp2)) {
					map.remove(key);
					mapKeys.remove(key);
					sortedMap.put(key, val);
					break;
				}
			}
		}
		return sortedMap;
	}

	@Override
	public void loadUsageRecipes(ItemStack ingredient) {
		int lavaYield = VolcanicFurnaceHandler.getBurnTime(ingredient);
		if (lavaYield != 16 && lavaYield > 0)
			arecipes.add(new CachedLavaYield(ingredient.copy().splitStack(1), lavaYield));
	}

	private class CachedLavaYield extends CachedRecipe {

		private final PositionedStack material;
		private final int yield;

		public CachedLavaYield(ItemStack stack, int yield) {
			material = new PositionedStack(stack, 43, 25);
			this.yield = yield;
		}

		public int getYield() {
			return yield;
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