package ganymedes01.ganysnether.recipes;

import ganymedes01.ganysnether.blocks.ModBlocks;
import ganymedes01.ganysnether.items.ModItems;
import ganymedes01.ganysnether.lib.Reference;
import ganymedes01.ganysnether.recipes.centrifuge.CentrifugeRecipe;
import ganymedes01.ganysnether.recipes.centrifuge.ItemStackCentrifugeRecipe;
import ganymedes01.ganysnether.recipes.centrifuge.OreDictCentrifugeRecipe;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
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

		addRecipe(new ItemStack(Item.glowstone), new ItemStack(Block.netherrack), new ItemStack(Item.redstone, 4));
		addRecipe(new ItemStack(Item.magmaCream), new ItemStack(Item.magmaCream), new ItemStack(Item.blazePowder, 2), new ItemStack(Item.slimeBall, 2));
		addRecipe(new ItemStack(ModItems.glowingReed), new ItemStack(ModItems.glowingReed), new ItemStack(Item.glowstone, 2), new ItemStack(Item.sugar, 2));
		addRecipe(new ItemStack(ModItems.quarzBerry), new ItemStack(Item.glassBottle), new ItemStack(Item.potion));
		addRecipe(new ItemStack(Block.glowStone), new ItemStack(Item.flint), new ItemStack(Item.glowstone, 4));
		addRecipe(new ItemStack(Block.gravel), new ItemStack(Block.gravel), new ItemStack(Item.flint, 2));
		addRecipe(new ItemStack(Item.eyeOfEnder), new ItemStack(Item.eyeOfEnder), new ItemStack(Item.blazePowder, 2), new ItemStack(Item.enderPearl, 2));
		addRecipe(new ItemStack(ModItems.glowingReed), new ItemStack(Item.potion), new ItemStack(Item.reed), new ItemStack(Item.glassBottle));
		addRecipe(new ItemStack(Block.sand), new ItemStack(Block.sand), new ItemStack(Block.glass, 2));
		addRecipe(new ItemStack(Block.glass), new ItemStack(Block.glass), new ItemStack(Block.sand, 2));
		addRecipe(new ItemStack(Block.cloth, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Item.flint), new ItemStack(Item.silk, 6));
		addRecipe(new ItemStack(Item.rottenFlesh), new ItemStack(Item.rottenFlesh), new ItemStack(Item.leather));
		addRecipe(new ItemStack(ModBlocks.soulGlass), new ItemStack(ModBlocks.soulGlass), new ItemStack(Block.slowSand, 2));
		addRecipe(new ItemStack(Block.slowSand), new ItemStack(Block.slowSand), new ItemStack(ModBlocks.soulGlass, 2));
		addRecipe(new ItemStack(Item.bucketEmpty), new ItemStack(Item.flint), new ItemStack(Item.ingotIron, 3));
		addRecipe(new ItemStack(Item.arrow), new ItemStack(Item.arrow), new ItemStack(Item.flint, 2), new ItemStack(Item.stick, 2), new ItemStack(Item.feather, 2));
		addRecipe(new ItemStack(Item.coal), new ItemStack(Item.blazePowder), new ItemStack(Item.gunpowder));
		addRecipe(new ItemStack(Block.stoneBrick), new ItemStack(Block.vine), new ItemStack(Block.stoneBrick, 1, 1));
		addRecipe(new ItemStack(Block.stoneBrick), new ItemStack(Item.flint), new ItemStack(Block.stoneBrick, 1, 2));
		addRecipe(new ItemStack(Block.cobblestone), new ItemStack(Block.vine), new ItemStack(Block.cobblestoneMossy));
		addRecipe(new ItemStack(Block.pumpkin), new ItemStack(Block.pumpkin), new ItemStack(Item.pumpkinSeeds, 12));
		addRecipe(new ItemStack(Item.melon), new ItemStack(Item.melon), new ItemStack(Item.melonSeeds, 3));
		addRecipe(new ItemStack(Block.sandStone), new ItemStack(Item.flint), new ItemStack(Block.sand, 4));
		addRecipe(new ItemStack(Block.dirt), new ItemStack(Item.dyePowder, 1, 15), new ItemStack(Block.grass));
		addRecipe(new ItemStack(Block.vine), new ItemStack(Block.vine), new ItemStack(Item.silk, 2));
		addRecipe(new ItemStack(Item.horseArmorIron), new ItemStack(Item.flint), new ItemStack(Item.ingotIron, 6));
		addRecipe(new ItemStack(Item.horseArmorGold), new ItemStack(Item.flint), new ItemStack(Item.ingotGold, 6));
		addRecipe(new ItemStack(Item.horseArmorDiamond), new ItemStack(Item.flint), new ItemStack(Item.diamond, 6));
		addRecipe(new ItemStack(Block.bookShelf), new ItemStack(Item.flint), new ItemStack(Block.planks, 6), new ItemStack(Item.book, 3));
		addRecipe(new ItemStack(Item.leather), new ItemStack(Item.flint), new ItemStack(Item.silk, 3));
		addRecipe(new ItemStack(Block.silverfish, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Item.netherQuartz), new ItemStack(Item.monsterPlacer, 1, 60), new ItemStack(Block.cobblestone));
		addRecipe(new ItemStack(ModItems.spectreWheat), new ItemStack(Item.diamond), new ItemStack(ModItems.spookyFlour), new ItemStack(Item.diamond));
		addRecipe(new ItemStack(Block.blockNetherQuartz), new ItemStack(Block.blockNetherQuartz), new ItemStack(Block.fire, 6));
		addRecipe(new ItemStack(Item.dyePowder, 1, 2), new ItemStack(Item.dyePowder, 1, 2), new ItemStack(Item.slimeBall));
		addRecipe(new ItemStack(Item.wheat), new ItemStack(Item.flint), new ItemStack(ModItems.flour), new ItemStack(Item.flint));
	}

	public static void initOreDictRecipes() {
		registerMetal("Gold", "Tin");
		registerMetal("Iron", new ItemStack(Item.ingotIron), "Nickle");
		registerMetal("Copper", new ItemStack(Item.goldNugget));
		registerMetal("Tin", "Copper");
		registerMetal("Silver", "Lead");
		registerMetal("Lead", "Silver");
		registerMetal("Aluminium", new ItemStack(ModItems.ironNugget));
		registerMetal("Nickle", "Platinum");

		addOreDictRecipe("dyeBlack", new ItemStack(Block.cloth, 1, 4), new ItemStack(Block.sponge));
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