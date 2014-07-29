package ganymedes01.ganysnether.recipes;

import ganymedes01.ganysnether.ModBlocks;
import ganymedes01.ganysnether.ModItems;
import ganymedes01.ganysnether.lib.Reference;
import ganymedes01.ganysnether.recipes.centrifuge.CentrifugeRecipe;
import ganymedes01.ganysnether.recipes.centrifuge.ItemStackCentrifugeRecipe;
import ganymedes01.ganysnether.recipes.centrifuge.OreDictCentrifugeRecipe;

import java.util.ArrayList;
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

	public static ArrayList<CentrifugeRecipe> getRecipes() {
		return recipes;
	}

	public static void clearRecipeList() {
		recipes = new ArrayList<CentrifugeRecipe>();
	}

	public static void initRecipes() {
		clearRecipeList();

		addRecipe(new ItemStack(Items.glowstone_dust), new ItemStack(Blocks.netherrack), new ItemStack(Items.redstone, 4));
		addRecipe(new ItemStack(Items.magma_cream), new ItemStack(Items.magma_cream), new ItemStack(Items.blaze_powder, 2), new ItemStack(Items.slime_ball, 2));
		addRecipe(new ItemStack(ModItems.glowingReed), new ItemStack(ModItems.glowingReed), new ItemStack(Items.glowstone_dust, 2), new ItemStack(Items.sugar, 2));
		addRecipe(new ItemStack(ModItems.quarzBerry), new ItemStack(Items.glass_bottle), new ItemStack(Items.potionitem));
		addRecipe(new ItemStack(Blocks.glowstone), new ItemStack(Items.flint), new ItemStack(Items.glowstone_dust, 4));
		addRecipe(new ItemStack(Blocks.gravel), new ItemStack(Blocks.gravel), new ItemStack(Items.flint, 2));
		addRecipe(new ItemStack(Items.ender_eye), new ItemStack(Items.ender_eye), new ItemStack(Items.blaze_powder, 2), new ItemStack(Items.ender_pearl, 2));
		addRecipe(new ItemStack(ModItems.glowingReed), new ItemStack(Items.potionitem), new ItemStack(Items.reeds), new ItemStack(Items.glass_bottle));
		addRecipe(new ItemStack(Blocks.sand), new ItemStack(Blocks.sand), new ItemStack(Blocks.glass, 2));
		addRecipe(new ItemStack(Blocks.glass), new ItemStack(Blocks.glass), new ItemStack(Blocks.sand, 2));
		addRecipe(new ItemStack(Blocks.wool, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.flint), new ItemStack(Items.string, 6));
		addRecipe(new ItemStack(Items.rotten_flesh), new ItemStack(Items.rotten_flesh), new ItemStack(Items.leather));
		addRecipe(new ItemStack(ModBlocks.soulGlass), new ItemStack(ModBlocks.soulGlass), new ItemStack(Blocks.soul_sand, 2));
		addRecipe(new ItemStack(Blocks.soul_sand), new ItemStack(Blocks.soul_sand), new ItemStack(ModBlocks.soulGlass, 2));
		addRecipe(new ItemStack(Items.bucket), new ItemStack(Items.flint), new ItemStack(Items.iron_ingot, 3));
		addRecipe(new ItemStack(Items.arrow), new ItemStack(Items.arrow), new ItemStack(Items.flint, 2), new ItemStack(Items.stick, 2), new ItemStack(Items.feather, 2));
		addRecipe(new ItemStack(Items.coal), new ItemStack(Items.blaze_powder), new ItemStack(Items.gunpowder));
		addRecipe(new ItemStack(Blocks.stonebrick), new ItemStack(Blocks.vine), new ItemStack(Blocks.stonebrick, 1, 1));
		addRecipe(new ItemStack(Blocks.stonebrick), new ItemStack(Items.flint), new ItemStack(Blocks.stonebrick, 1, 2));
		addRecipe(new ItemStack(Blocks.cobblestone), new ItemStack(Blocks.vine), new ItemStack(Blocks.mossy_cobblestone));
		addRecipe(new ItemStack(Blocks.pumpkin), new ItemStack(Blocks.pumpkin), new ItemStack(Items.pumpkin_seeds, 12));
		addRecipe(new ItemStack(Items.melon), new ItemStack(Items.melon), new ItemStack(Items.melon_seeds, 3));
		addRecipe(new ItemStack(Blocks.sandstone), new ItemStack(Items.flint), new ItemStack(Blocks.sand, 4));
		addRecipe(new ItemStack(Blocks.dirt), new ItemStack(Items.dye, 1, 15), new ItemStack(Blocks.grass));
		addRecipe(new ItemStack(Blocks.vine), new ItemStack(Blocks.vine), new ItemStack(Items.string, 2));
		addRecipe(new ItemStack(Items.iron_horse_armor), new ItemStack(Items.flint), new ItemStack(Items.iron_ingot, 6));
		addRecipe(new ItemStack(Items.golden_horse_armor), new ItemStack(Items.flint), new ItemStack(Items.gold_ingot, 6));
		addRecipe(new ItemStack(Items.diamond_horse_armor), new ItemStack(Items.flint), new ItemStack(Items.diamond, 6));
		addRecipe(new ItemStack(Blocks.bookshelf), new ItemStack(Items.flint), new ItemStack(Blocks.planks, 6), new ItemStack(Items.book, 3));
		addRecipe(new ItemStack(Items.leather), new ItemStack(Items.flint), new ItemStack(Items.string, 3));
		addRecipe(new ItemStack(Blocks.monster_egg, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.quartz), new ItemStack(Items.spawn_egg, 1, 60), new ItemStack(Blocks.cobblestone));
		addRecipe(new ItemStack(ModItems.spectreWheat), new ItemStack(Items.diamond), new ItemStack(ModItems.spookyFlour), new ItemStack(Items.diamond));
		addRecipe(new ItemStack(Blocks.quartz_block), new ItemStack(Blocks.quartz_block), new ItemStack(Blocks.fire, 6));
		addRecipe(new ItemStack(Items.dye, 1, 2), new ItemStack(Items.dye, 1, 2), new ItemStack(Items.slime_ball));
		addRecipe(new ItemStack(Items.wheat), new ItemStack(Items.flint), new ItemStack(ModItems.flour), new ItemStack(Items.flint));
	}

	public static void initOreDictRecipes() {
		registerMetal("Gold", "Tin");
		registerMetal("Iron", new ItemStack(Items.iron_ingot), "Nickle");
		registerMetal("Copper", new ItemStack(Items.gold_nugget));
		registerMetal("Tin", "Copper");
		registerMetal("Silver", "Lead");
		registerMetal("Lead", "Silver");
		registerMetal("Aluminium", new ItemStack(ModItems.ironNugget));
		registerMetal("Nickle", "Platinum");

		addOreDictRecipe("dyeBlack", new ItemStack(Blocks.wool, 1, 4), new ItemStack(Blocks.sponge));
	}

	private static void registerMetal(String name, ItemStack ingot, Object nuggetMetal) {
		ItemStack nugget;
		if (nuggetMetal instanceof ItemStack)
			nugget = (ItemStack) nuggetMetal;
		else {
			nugget = getExample("nugget" + nuggetMetal);
			if (nugget == null) {
				nugget = getExample("dustTiny" + nuggetMetal);
				if (nugget == null) {
					nugget = getExample("nugget" + name);
					if (nugget == null)
						return;
				}
			}
		}
		ingot.stackSize = 3;

		addOreDictRecipe("ore" + name, "ore" + name, ingot, nugget);
	}

	private static void registerMetal(String name, Object nuggetMetal) {
		ItemStack ingot = getExample("ingot" + name);
		if (ingot == null)
			return;
		registerMetal(name, ingot, nuggetMetal);
	}

	private static ItemStack getExample(String s) {
		ArrayList<ItemStack> list = OreDictionary.getOres(s);
		return list != null && !list.isEmpty() ? list.get(0).copy() : null;
	}

	public static void addOreDictRecipe(String material1, Object material2, ItemStack... result) {
		if (!OreDictionary.getOres(material1).isEmpty()) {
			if (material2 instanceof String)
				if (OreDictionary.getOres((String) material2).isEmpty())
					return;
			OreDictCentrifugeRecipe recipe = new OreDictCentrifugeRecipe(material1, material2, result);
			if (isValidRecipe(recipe))
				recipes.add(recipe);
		}
	}

	public static void addRecipe(ItemStack material1, ItemStack material2, ItemStack... result) {
		addRecipeExternal(Reference.MOD_ID, material1, material2, result);
	}

	public static void addRecipeExternal(String sender, ItemStack material1, ItemStack material2, ItemStack... result) {
		if (result != null && result.length > 4 || material1 == null || material2 == null) {
			Logger.getLogger(Reference.MOD_ID).log(Level.WARNING, sender + " attempted to add an invalid recipe to the Magmatic Centrifuge: Null material or invalid sized result array");
			return;
		} else {
			for (ItemStack stack : result)
				if (stack == null) {
					Logger.getLogger(Reference.MOD_ID).log(Level.WARNING, sender + " attempted to add an invalid recipe to the Magmatic Centrifuge: Null result");
					return;
				}
			CentrifugeRecipe newRecipe = new ItemStackCentrifugeRecipe(material1, material2, result);
			if (isValidRecipe(newRecipe)) {
				recipes.add(newRecipe);
				Level lvl = Level.INFO;
				if (sender.equalsIgnoreCase(Reference.MOD_ID))
					lvl = Level.FINE;
				Logger.getLogger(Reference.MOD_ID).log(lvl, sender + " successfully added a recipe to the Magmatic Centrifuge.");
			} else
				Logger.getLogger(Reference.MOD_ID).log(Level.WARNING, sender + " attempted to add an existing recipe to the Magmatic Centrifuge: " + newRecipe.toString());
		}
	}

	public static boolean isRegisteredRecipe(ItemStack material1, ItemStack material2, ItemStack... result) {
		return contains(new ItemStackCentrifugeRecipe(material1, material2, result));
	}

	public static boolean isValidRecipe(CentrifugeRecipe recipe) {
		return !contains(recipe) && CentrifugeRecipe.isValidRecipe(recipe);
	}

	public static ItemStack[] getResult(ItemStack material1, ItemStack material2) {
		CentrifugeRecipe foundRecipe = recipes.get(indexOf(new ItemStackCentrifugeRecipe(material1, material2)));
		return foundRecipe != null ? foundRecipe.getResult() : null;
	}

	private static boolean contains(CentrifugeRecipe recipe) {
		for (CentrifugeRecipe rec : recipes)
			if (rec.equals(recipe))
				return true;
		return false;
	}

	// Oh well...
	private static int indexOf(CentrifugeRecipe recipe) {
		for (int i = 0; i < recipes.size(); i++)
			if (recipes.get(i).equals(recipe))
				return i;
		return -1;
	}
}