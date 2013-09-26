package ganymedes01.ganysnether.recipes;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.blocks.ModBlocks;
import ganymedes01.ganysnether.items.ModItems;

import java.util.Iterator;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityEggInfo;
import net.minecraft.entity.EntityList;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class ModRecipes {

	private static String[] dyes = new String[] { "dyeBlack", "dyeRed", "dyeGreen", "dyeBrown", "dyeBlue", "dyePurple", "dyeCyan", "dyeLightGray", "dyeGray", "dyePink", "dyeLime", "dyeYellow", "dyeLightBlue", "dyeMagenta", "dyeOrange", "dyeWhite" };

	public static void init() {
		registerOreDictionary();
		registerBlockRecipes();
		registerItemRecipes();
		registerArmourRecipes();
	}

	private static void registerArmourRecipes() {
		GameRegistry.addRecipe(new ItemStack(ModItems.blazeHelmet), "xxx", "x x", 'x', new ItemStack(ModItems.blazeIngot, 1, 1));
		GameRegistry.addRecipe(new ItemStack(ModItems.blazeChestplate), "x x", "xxx", "xxx", 'x', new ItemStack(ModItems.blazeIngot, 1, 1));
		GameRegistry.addRecipe(new ItemStack(ModItems.blazeLeggings), "xxx", "x x", "x x", 'x', new ItemStack(ModItems.blazeIngot, 1, 1));
		GameRegistry.addRecipe(new ItemStack(ModItems.blazeBoots), "x x", "x x", 'x', new ItemStack(ModItems.blazeIngot, 1, 1));
	}

	private static void registerItemRecipes() {
		GameRegistry.addRecipe(new ItemStack(ModItems.spookyFlour, 6), "xxx", 'x', ModItems.spectreWheat);
		GameRegistry.addRecipe(new ItemStack(ModItems.bottomlessBucket), "xxx", "zxz", "yzy", 'x', Item.bucketEmpty, 'y', Block.slowSand, 'z', Item.netherrackBrick);
		GameRegistry.addRecipe(new ItemStack(ModItems.dimensionalBread), "xxx", 'x', ModItems.spookyFlour);
		ItemStack knockBackBookI = new ItemStack(Item.enchantedBook);
		knockBackBookI.addEnchantment(Enchantment.knockback, 1);
		GameRegistry.addRecipe(new ItemStack(ModItems.baseballBat), " zx", " yz", "y  ", 'x', Item.diamond, 'y', Item.netherrackBrick, 'z', knockBackBookI);
		ItemStack knockBackBookII = new ItemStack(Item.enchantedBook);
		knockBackBookII.addEnchantment(Enchantment.knockback, 2);
		GameRegistry.addRecipe(new ItemStack(ModItems.baseballBat), " zx", " y ", "y  ", 'x', Item.diamond, 'y', Item.netherrackBrick, 'z', knockBackBookII);
		GameRegistry.addRecipe(new ItemStack(ModItems.sceptreOfConcealment, 1, GanysNether.sceptreOfConcealmentDurability), "  x", " y ", "z  ", 'x', Item.netherStar, 'y', Item.ingotGold, 'z', Item.netherrackBrick);
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.sceptreOfConcealment), new ItemStack(Item.netherStar), new ItemStack(ModItems.sceptreOfConcealment, 1, GanysNether.sceptreOfConcealmentDurability));
		GameRegistry.addSmelting(ModItems.batWing.itemID, new ItemStack(ModItems.cookedBatWing), 0.0F);
		FurnaceRecipes.smelting().addSmelting(ModItems.blazeIngot.itemID, 0, new ItemStack(ModItems.blazeIngot, 1, 1), 0.0F);
		GameRegistry.addRecipe(new ItemStack(ModItems.blazeIngot, 1, 0), "x", "x", "x", 'x', Item.blazeRod);

		// Vanilla
		GameRegistry.addShapelessRecipe(new ItemStack(Item.glowstone, 2), new ItemStack(ModItems.glowingReed));
		GameRegistry.addRecipe(new ItemStack(Item.netherQuartz, 6), "xxx", "yyy", "xxx", 'x', ModItems.quarzBerry, 'y', Block.glass);
		GameRegistry.addRecipe(new ItemStack(Item.arrow, 16), "x", "y", "z", 'x', ModItems.wolfTeeth, 'y', Item.stick, 'z', Item.feather);
	}

	private static void registerBlockRecipes() {
		GameRegistry.addSmelting(Block.slowSand.blockID, new ItemStack(ModBlocks.soulGlass), 1F);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.soulGlass, 4, 1), "xx", "xx", 'x', new ItemStack(ModBlocks.soulGlass, 1, 0));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.soulChest), "xyx", "x x", "xxx", 'x', Block.slowSand, 'y', Item.netherQuartz);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.volcanicFurnaceIdle), "yxy", "wyw", "zwz", 'x', Item.cauldron, 'y', Block.netherBrick, 'z', Block.obsidian, 'w', ModBlocks.denseLavaCell);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.denseLavaCell), "yxy", "xxx", "yxy", 'x', Item.bucketLava, 'y', Item.diamond);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.glowBox, 4, 11), " y ", "yxy", " y ", 'x', Block.glowStone, 'y', Block.thinGlass);

		for (int i = 0; i < 16; i++) {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.glowBox, 8, i), "xxx", "xyx", "xxx", 'x', "ganysNetherGlowBox", 'y', dyes[i]));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.colouredQuartzBlock, 8, i), "xxx", "xyx", "xxx", 'x', "ganysNetherColouredQuartzBlock", 'y', dyes[i]));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.colouredChiselledQuartzBlock, 8, i), "xxx", "xyx", "xxx", 'x', "ganysNetherColouredChiseledQuartz", 'y', dyes[i]));
			GameRegistry.addRecipe(new ItemStack(ModBlocks.colouredQuartzBlockStairs[i], 4), "x  ", "xx ", "xxx", 'x', new ItemStack(ModBlocks.colouredQuartzBlock, 1, i));
		}
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Block.blockNetherQuartz, 1, 0), "ganysNetherColouredQuartzBlock", new ItemStack(Item.potion, 1, 0)));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Block.blockNetherQuartz, 1, 1), "ganysNetherColouredChiseledQuartz", new ItemStack(Item.potion, 1, 0)));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.soulGlassStairs), "x  ", "xx ", "xxx", 'x', new ItemStack(ModBlocks.soulGlass, 1, 1));

		int index = 0;
		for (Block pillar : ModBlocks.colouredQuartzPillar)
			for (int i = 0; i < 4; i++) {
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(pillar, 8, i), "xxx", "xyx", "xxx", 'x', "ganysNetherColouredQuartzPillar", 'y', dyes[index]));
				index++;
			}

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.reproducer), "yzy", "wxw", "zwz", 'x', new ItemStack(Block.blockNetherQuartz, 1, 2), 'y', Block.obsidian, 'z', Block.slowSand, 'w', "ganysNetherSpawnEggs"));
		GameRegistry.addSmelting(ModBlocks.soulChest.blockID, new ItemStack(ModBlocks.undertaker), 1.0F);

		// Vanilla
		GameRegistry.addRecipe(new ItemStack(Block.slowSand, 6), "xxx", "yyy", "xxx", 'x', ModItems.spookyFlour, 'y', Block.sand);
	}

	private static void registerOreDictionary() {
		for (int i = 0; i < 16; i++) {
			OreDictionary.registerOre("ganysNetherGlowBox", new ItemStack(ModBlocks.glowBox, 1, i));
			OreDictionary.registerOre("ganysNetherColouredQuartzBlock", new ItemStack(ModBlocks.colouredQuartzBlock, 1, i));
			OreDictionary.registerOre("ganysNetherColouredChiseledQuartz", new ItemStack(ModBlocks.colouredChiselledQuartzBlock, 1, i));
		}

		for (Block pillar : ModBlocks.colouredQuartzPillar)
			for (int i = 0; i < 4; i++)
				OreDictionary.registerOre("ganysNetherColouredQuartzPillar", new ItemStack(pillar, 1, i));

		OreDictionary.registerOre("ganysNetherColouredQuartzBlock", new ItemStack(Block.blockNetherQuartz, 1, 0));
		OreDictionary.registerOre("ganysNetherColouredChiseledQuartz", new ItemStack(Block.blockNetherQuartz, 1, 1));
		OreDictionary.registerOre("ganysNetherColouredQuartzPillar", new ItemStack(Block.blockNetherQuartz, 1, 2));

		Iterator iterator = EntityList.entityEggs.values().iterator();
		while (iterator.hasNext()) {
			EntityEggInfo entityegginfo = (EntityEggInfo) iterator.next();
			OreDictionary.registerOre("ganysNetherSpawnEggs", new ItemStack(Item.monsterPlacer, 1, entityegginfo.spawnedID));
		}
		OreDictionary.registerOre("ganysNetherSpawnEggs", new ItemStack(ModItems.skeletonSpawner, 1, 0));
		OreDictionary.registerOre("ganysNetherSpawnEggs", new ItemStack(ModItems.skeletonSpawner, 1, 1));
	}
}
