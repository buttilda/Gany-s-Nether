package ganymedes01.ganysnether.recipes;

import ganymedes01.ganysnether.blocks.ModBlocks;
import ganymedes01.ganysnether.items.ModItems;
import ganymedes01.ganysnether.lib.Reference;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.Loader;

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
		addRecipe(new ItemStack(Block.glowStone), new ItemStack(Item.flint), new ItemStack(Item.glowstone, 6));
		addRecipe(new ItemStack(Block.gravel), new ItemStack(Block.gravel), new ItemStack(Item.flint, 2));
		addRecipe(new ItemStack(Item.eyeOfEnder), new ItemStack(Item.eyeOfEnder), new ItemStack(Item.blazePowder, 2), new ItemStack(Item.enderPearl, 2));
		addRecipe(new ItemStack(ModItems.glowingReed), new ItemStack(Item.potion), new ItemStack(Item.reed), new ItemStack(Item.glassBottle));
		addRecipe(new ItemStack(Block.sand), new ItemStack(Block.sand), new ItemStack(Block.glass, 2));
		addRecipe(new ItemStack(Block.glass), new ItemStack(Block.glass), new ItemStack(Block.sand, 2));
		for (int i = 0; i < 16; i++)
			addRecipe(new ItemStack(Block.cloth, 1, i), new ItemStack(Item.flint), new ItemStack(Item.silk, 4));
		addRecipe(new ItemStack(Item.rottenFlesh), new ItemStack(Item.rottenFlesh), new ItemStack(Item.leather));
		addRecipe(new ItemStack(ModBlocks.soulGlass), new ItemStack(ModBlocks.soulGlass), new ItemStack(Block.slowSand, 2));
		addRecipe(new ItemStack(Block.slowSand), new ItemStack(Block.slowSand), new ItemStack(ModBlocks.soulGlass, 2));
		addRecipe(new ItemStack(Item.bucketEmpty), new ItemStack(Item.flint), new ItemStack(Item.ingotIron, 3));
		addRecipe(new ItemStack(Item.arrow), new ItemStack(Item.arrow), new ItemStack(Item.flint, 2), new ItemStack(Item.stick, 2), new ItemStack(Item.feather, 2));
		addRecipe(new ItemStack(Item.coal), new ItemStack(Item.blazePowder), new ItemStack(Item.gunpowder));
		addRecipe(new ItemStack(Block.stoneBrick), new ItemStack(Block.vine), new ItemStack(Block.stoneBrick, 1));
		addRecipe(new ItemStack(Block.stoneBrick), new ItemStack(Item.flint), new ItemStack(Block.stoneBrick, 2));
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
		for (int i = 0; i < 2; i++)
			addRecipe(new ItemStack(Block.silverfish, 1, i), new ItemStack(Item.netherQuartz), new ItemStack(Item.monsterPlacer, 1, 60), new ItemStack(Block.cobblestone));
		if (!Loader.isModLoaded("AppliedEnergistics"))
			addRecipe(new ItemStack(Item.wheat), new ItemStack(Item.diamond), new ItemStack(ModItems.flour), new ItemStack(Item.diamond));
		addRecipe(new ItemStack(ModItems.spectreWheat), new ItemStack(Item.diamond), new ItemStack(ModItems.spookyFlour), new ItemStack(Item.diamond));
		addRecipe(new ItemStack(Item.netherQuartz), new ItemStack(Item.netherQuartz), new ItemStack(Block.fire, 2));
	}

	public static void initOreDictRecipes() {
		if (!Loader.isModLoaded("IC2")) {
			addOreDictRecipe("oreGold", "oreGold", new ItemStack(Item.ingotGold, 2), new ItemStack(Item.goldNugget));
			addOreDictRecipe("oreIron", "oreIron", new ItemStack(Item.ingotIron, 2), new ItemStack(ModItems.ironNugget));
		}
		addOreDictRecipe("dyeBlack", new ItemStack(Block.cloth, 1, 4), new ItemStack(Block.sponge));
	}

	public static void addOreDictRecipe(String material1, String material2, ItemStack... result) {
		ArrayList<ItemStack> materials1 = OreDictionary.getOres(material1);
		ArrayList<ItemStack> materials2 = OreDictionary.getOres(material2);
		if (!materials1.isEmpty() && !materials2.isEmpty())
			for (ItemStack mat1 : materials1)
				for (ItemStack mat2 : materials2)
					if (isValidRecipe(new CentrifugeRecipe(mat1, mat2, result)))
						addRecipe(mat1, mat2, result);
	}

	public static void addOreDictRecipe(String material1, ItemStack material2, ItemStack... result) {
		ArrayList<ItemStack> materials1 = OreDictionary.getOres(material1);
		if (!materials1.isEmpty())
			for (ItemStack mat1 : materials1)
				addRecipe(mat1, material2, result);
	}

	private static void addRecipe(ItemStack material1, ItemStack material2, ItemStack... result) {
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
			CentrifugeRecipe newRecipe = new CentrifugeRecipe(material1, material2, result);
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
		return recipes.contains(new CentrifugeRecipe(material1, material2, result));
	}

	public static boolean isValidRecipe(CentrifugeRecipe recipe) {
		return !recipes.contains(recipe) && CentrifugeRecipe.isValidRecipe(recipe);
	}

	public static ItemStack[] getResult(ItemStack material1, ItemStack material2) {
		CentrifugeRecipe foundRecipe = recipes.get(recipes.indexOf(new CentrifugeRecipe(material1, material2)));
		return foundRecipe != null ? foundRecipe.getResult() : null;
	}

	public static class CentrifugeRecipe {

		private final ItemStack material1;
		private final ItemStack material2;
		private final ItemStack[] result;

		public CentrifugeRecipe(ItemStack material1, ItemStack material2) {
			this(material1, material2, (ItemStack) null);
		}

		public CentrifugeRecipe(ItemStack material1, ItemStack material2, ItemStack... result) {
			this.material1 = material1;
			this.material2 = material2;
			this.result = result;
		}

		public ItemStack getMaterial(int num) {
			return num == 1 ? material1.copy() : num == 2 ? material2.copy() : null;
		}

		public boolean containsMaterial(ItemStack material) {
			if (material == null)
				return false;

			return material.itemID == material1.itemID && material.getItemDamage() == material1.getItemDamage() || material.itemID == material2.itemID && material.getItemDamage() == material2.getItemDamage();
		}

		public boolean isPartOfResult(ItemStack result) {
			if (result == null)
				return false;

			for (ItemStack res : this.result)
				if (res.itemID == result.itemID && res.getItemDamage() == result.getItemDamage())
					return true;
			return false;
		}

		public static boolean isValidRecipe(CentrifugeRecipe recipe) {
			return recipe.getMaterial(1) != null && recipe.getMaterial(2) != null && recipe.getResult() != null && recipe.getResult().length <= 4;
		}

		public ItemStack[] getResult() {
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == null)
				return false;

			if (obj instanceof CentrifugeRecipe) {
				CentrifugeRecipe recipe = (CentrifugeRecipe) obj;
				ItemStack material1 = recipe.getMaterial(1);
				ItemStack material2 = recipe.getMaterial(2);
				if (material1 == null || material2 == null)
					return false;
				else {
					if (material1.itemID == getMaterial(1).itemID && material1.getItemDamage() == getMaterial(1).getItemDamage())
						if (material2.itemID == getMaterial(2).itemID && material2.getItemDamage() == getMaterial(2).getItemDamage())
							return true;
					if (material2.itemID == getMaterial(1).itemID && material2.getItemDamage() == getMaterial(1).getItemDamage())
						if (material1.itemID == getMaterial(2).itemID && material1.getItemDamage() == getMaterial(2).getItemDamage())
							return true;
				}
			}
			return false;
		}

		@Override
		public String toString() {
			StringBuffer buffer = new StringBuffer();
			buffer.append(material1.getUnlocalizedName() + " + " + material2.getUnlocalizedName() + " = ");
			for (ItemStack stack : getResult())
				buffer.append(stack.getUnlocalizedName() + ", ");
			buffer.deleteCharAt(buffer.length() - 1);
			buffer.deleteCharAt(buffer.length() - 1);
			return buffer.toString();
		}
	}
}