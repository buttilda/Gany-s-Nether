package ganymedes01.ganysnether.recipes;

import ganymedes01.ganysnether.ModBlocks;
import ganymedes01.ganysnether.ModItems;
import ganymedes01.ganysnether.core.utils.XMLHelper;
import ganymedes01.ganysnether.lib.Reference;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Gany's Nether
 *
 * @author ganymedes01
 *
 */

public class MagmaticCentrifugeRecipes {

	private static ArrayList<CentrifugeRecipe> recipes;
	public static File recipesFile;

	public static List<CentrifugeRecipe> getRecipes() {
		return Collections.unmodifiableList(recipes);
	}

	public static void clearRecipeList() {
		recipes = new ArrayList<CentrifugeRecipe>();
	}

	public static void initRecipes() {
		clearRecipeList();
		try {
			if (!recipesFile.exists()) {
				addDefaultRecipes();
				BufferedWriter bw = XMLHelper.getWriter(recipesFile);
				for (CentrifugeRecipe recipe : recipes) {
					bw.write(recipe.toString());
					bw.newLine();
					bw.newLine();
				}
				bw.close();
			} else {
				String line = XMLHelper.readFile(recipesFile);
				Iterator<String> iterator = XMLHelper.getIterator("recipe", line);
				while (iterator.hasNext())
					recipes.add(new CentrifugeRecipe(iterator.next()));
			}
		} catch (IOException e) {
			throw new RuntimeException("Problem reading Magmatic Centrifuge recipes!" + e);
		}
	}

	private static void addDefaultRecipes() {
		addRecipe(Items.glowstone_dust, Blocks.netherrack, new ItemStack(Items.redstone, 4));
		addRecipe(Items.magma_cream, Items.magma_cream, new ItemStack(Items.blaze_powder, 2), new ItemStack(Items.slime_ball, 2));
		addRecipe(ModItems.glowingReed, ModItems.glowingReed, new ItemStack(Items.glowstone_dust, 2), new ItemStack(Items.sugar, 2));
		addRecipe(ModItems.quarzBerry, Items.glass_bottle, new ItemStack(Items.potionitem));
		addRecipe(Blocks.glowstone, Items.flint, new ItemStack(Items.glowstone_dust, 4));
		addRecipe(Blocks.gravel, Blocks.gravel, new ItemStack(Items.flint, 2));
		addRecipe(Items.ender_eye, Items.ender_eye, new ItemStack(Items.blaze_powder, 2), new ItemStack(Items.ender_pearl, 2));
		addRecipe(ModItems.glowingReed, Items.potionitem, new ItemStack(Items.reeds), new ItemStack(Items.glass_bottle));
		addRecipe(Blocks.sand, Blocks.sand, new ItemStack(Blocks.glass, 2));
		addRecipe("blockGlass", "blockGlass", new ItemStack(Blocks.sand, 2));
		addRecipe(new ItemStack(Blocks.wool, 1, OreDictionary.WILDCARD_VALUE), Items.flint, new ItemStack(Items.string, 6));
		addRecipe(Items.rotten_flesh, Items.rotten_flesh, new ItemStack(Items.leather));
		addRecipe(ModBlocks.soulGlass, ModBlocks.soulGlass, new ItemStack(Blocks.soul_sand, 2));
		addRecipe(Blocks.soul_sand, Blocks.soul_sand, new ItemStack(ModBlocks.soulGlass, 2));
		addRecipe(Items.bucket, Items.flint, new ItemStack(Items.iron_ingot, 3));
		addRecipe(Items.arrow, Items.arrow, new ItemStack(Items.flint, 2), new ItemStack(Items.stick, 2), new ItemStack(Items.feather, 2));
		addRecipe(Items.coal, Items.blaze_powder, new ItemStack(Items.gunpowder));
		addRecipe(Blocks.pumpkin, Blocks.pumpkin, new ItemStack(Items.pumpkin_seeds, 12));
		addRecipe(Items.melon, Items.melon, new ItemStack(Items.melon_seeds, 3));
		addRecipe(Blocks.sandstone, Items.flint, new ItemStack(Blocks.sand, 4));
		addRecipe(Blocks.dirt, new ItemStack(Items.dye, 1, 15), new ItemStack(Blocks.grass));
		addRecipe(Blocks.vine, Blocks.vine, new ItemStack(Items.string, 2));
		addRecipe(Items.iron_horse_armor, Items.flint, new ItemStack(Items.iron_ingot, 6));
		addRecipe(Items.golden_horse_armor, Items.flint, new ItemStack(Items.gold_ingot, 6));
		addRecipe(Items.diamond_horse_armor, Items.flint, new ItemStack(Items.diamond, 6));
		addRecipe(Blocks.bookshelf, Items.flint, new ItemStack(Blocks.planks, 6), new ItemStack(Items.book, 3));
		addRecipe(Items.leather, Items.flint, new ItemStack(Items.string, 3));
		addRecipe(new ItemStack(Blocks.monster_egg, 1, OreDictionary.WILDCARD_VALUE), "gemQuartz", new ItemStack(Items.spawn_egg, 1, 60), new ItemStack(Blocks.cobblestone));
		addRecipe(ModItems.spectreWheat, "gemDiamond", new ItemStack(ModItems.spookyFlour), new ItemStack(Items.diamond));
		addRecipe(Blocks.quartz_block, Blocks.quartz_block, new ItemStack(Blocks.fire, 6));
		addRecipe(new ItemStack(Items.dye, 1, 2), new ItemStack(Items.dye, 1, 2), new ItemStack(Items.slime_ball));
		addRecipe(Items.wheat, Items.flint, new ItemStack(ModItems.flour), new ItemStack(Items.flint));
		addRecipe("dyeBlack", new ItemStack(Blocks.wool, 1, 4), new ItemStack(Blocks.sponge));
	}

	public static void addRecipe(Object input1, Object input2, ItemStack... outputs) {
		recipes.add(new CentrifugeRecipe(input1, input2, outputs));
	}

	public static void addRecipeExternal(String sender, ItemStack input1, ItemStack input2, ItemStack... outputs) {
		if (outputs != null && outputs.length > 4 || input1 == null || input2 == null) {
			Logger.getLogger(Reference.MOD_ID).log(Level.WARNING, sender + " attempted to add an invalid recipe to the Magmatic Centrifuge: Null material or invalid sized result array");
			return;
		} else {
			for (ItemStack stack : outputs)
				if (stack == null) {
					Logger.getLogger(Reference.MOD_ID).log(Level.WARNING, sender + " attempted to add an invalid recipe to the Magmatic Centrifuge: Null result");
					return;
				}
			CentrifugeRecipe newRecipe = new CentrifugeRecipe(input1, input2, outputs);
			recipes.add(newRecipe);
			Level lvl = Level.INFO;
			if (sender.equalsIgnoreCase(Reference.MOD_ID))
				lvl = Level.FINE;
			Logger.getLogger(Reference.MOD_ID).log(lvl, sender + " successfully added a recipe to the Magmatic Centrifuge.");
		}
	}

	public static ItemStack[] getResult(ItemStack input1, ItemStack input2) {
		for (CentrifugeRecipe recipe : recipes)
			if (recipe.matches(input1, input2))
				return recipe.getResult();
		return null;
	}
}