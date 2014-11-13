package ganymedes01.ganysnether.recipes;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.ModBlocks;
import ganymedes01.ganysnether.ModItems;
import ganymedes01.ganysnether.core.utils.xml.XMLBuilder;
import ganymedes01.ganysnether.core.utils.xml.XMLNode;
import ganymedes01.ganysnether.lib.Reference;

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

public class MagmaticCentrifugeRecipes extends RecipeRegistry<CentrifugeRecipe> {

	public static final MagmaticCentrifugeRecipes INSTANCE = new MagmaticCentrifugeRecipes();

	protected MagmaticCentrifugeRecipes() {
		super("MagmaticCentrifuge");
	}

	@Override
	protected XMLNode toXML(CentrifugeRecipe recipe) {
		XMLBuilder builder = new XMLBuilder("recipe");
		builder.makeEntry("input1", recipe.getInput1());
		builder.makeEntry("input2", recipe.getInput2());

		builder.makeEntries("output", recipe.getResult());

		return builder.toNode();
	}

	@Override
	protected CentrifugeRecipe makeRecipe(XMLNode node) {
		return new CentrifugeRecipe(node);
	}

	@Override
	protected void addDefaultRecipes() {
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

	public void addRecipe(Object input1, Object input2, ItemStack... outputs) {
		addRecipe(new CentrifugeRecipe(input1, input2, outputs));
	}

	public void addRecipeExternal(String sender, ItemStack input1, ItemStack input2, ItemStack... outputs) {
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
			addRecipe(newRecipe);
			Level lvl = Level.INFO;
			if (sender.equalsIgnoreCase(Reference.MOD_ID))
				lvl = Level.FINE;
			Logger.getLogger(Reference.MOD_ID).log(lvl, sender + " successfully added a recipe to the Magmatic Centrifuge.");
		}
	}

	public ItemStack[] getResult(ItemStack input1, ItemStack input2) {
		if (GanysNether.enableMagmaticCentrifuge)
			for (CentrifugeRecipe recipe : getRecipes())
				if (recipe.matches(input1, input2))
					return recipe.getResult();
		return null;
	}
}