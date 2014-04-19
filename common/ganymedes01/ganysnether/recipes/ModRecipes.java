package ganymedes01.ganysnether.recipes;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.blocks.ModBlocks;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.items.ModItems;
import ganymedes01.ganysnether.items.SpawnerUpgrade.UpgradeType;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
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
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.blazeHelmet), "xxx", "x x", 'x', "ingotBlaze"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.blazeChestplate), "x x", "xxx", "xxx", 'x', "ingotBlaze"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.blazeLeggings), "xxx", "x x", "x x", 'x', "ingotBlaze"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.blazeBoots), "x x", "x x", 'x', "ingotBlaze"));
	}

	private static void registerItemRecipes() {
		GameRegistry.addRecipe(new ItemStack(ModItems.bottomlessBucket), "xxx", "zxz", "yzy", 'x', Item.bucketEmpty, 'y', Block.slowSand, 'z', Item.netherrackBrick);
		GameRegistry.addRecipe(EnchantSensitiveRecipe.makeRecipe(new ItemStack(ModItems.baseballBat), " zx", " yz", "y  ", 'x', Item.diamond, 'y', Item.netherrackBrick, 'z', Utils.enchantStack(new ItemStack(Item.enchantedBook), Enchantment.knockback, 1)));
		GameRegistry.addRecipe(EnchantSensitiveRecipe.makeRecipe(new ItemStack(ModItems.baseballBat), " zx", " y ", "y  ", 'x', Item.diamond, 'y', Item.netherrackBrick, 'z', Utils.enchantStack(new ItemStack(Item.enchantedBook), Enchantment.knockback, 2)));
		GameRegistry.addSmelting(ModItems.batWing.itemID, new ItemStack(ModItems.cookedBatWing), 0.0F);
		FurnaceRecipes.smelting().addSmelting(ModItems.blazeIngot.itemID, 0, new ItemStack(ModItems.blazeIngot, 1, 1), 0.0F);
		GameRegistry.addRecipe(new ItemStack(ModItems.blazeIngot, 1, 0), "x", "x", "x", 'x', Item.blazeRod);
		GameRegistry.addRecipe(new ItemStack(ModItems.livingSoul, 2), "zxz", "xyx", "zyz", 'x', Item.rottenFlesh, 'y', Item.bone, 'z', Block.slowSand);
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.blazeIngot, 1, 1), "xxx", "xxx", "xxx", 'x', "nuggetBlaze"));
		GameRegistry.addSmelting(ModItems.spookyFlour.itemID, new ItemStack(ModItems.dimensionalBread), 0.0F);

		createCapRecipe(0, new ItemStack(Block.tnt), new ItemStack(ModItems.blazeIngot, 1, 1), Item.fireballCharge);
		createCapRecipe(1, new ItemStack(Item.netherQuartz), new ItemStack(Item.netherStalkSeeds), Item.netherStar);
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.sceptreCap, 1, 2), "xxx", "xyx", "xxx", 'x', "egg", 'y', Item.netherStar));

		createSceptreRecipe(ModItems.sceptreOfFireCharging, 0, Item.magmaCream, GanysNether.sceptreOfFireCharging);
		createSceptreRecipe(ModItems.sceptreOfLightning, 1, Item.ingotGold, GanysNether.sceptreOfLightningDurability);
		createSceptreRecipe(ModItems.sceptreOfConcealment, 2, Item.ingotGold, GanysNether.sceptreOfConcealmentDurability);
		GameRegistry.addRecipe(new ItemStack(ModItems.netherCore), "xyz", "wab", "cde", 'x', Item.magmaCream, 'y', Item.netherStalkSeeds, 'z', Item.netherQuartz, 'w', Item.blazeRod, 'a', Item.glowstone, 'b', Block.slowSand, 'c', Block.netherBrick, 'd', Item.ghastTear, 'e', new ItemStack(Item.skull, 1, 1));

		for (int i = 0; i < UpgradeType.values().length; i++)
			if (UpgradeType.values()[i].getMat1() != null && UpgradeType.values()[i].getMat2() != null)
				GameRegistry.addRecipe(new ItemStack(ModItems.spawnerUpgrade, 1, i), "xyx", "yzy", "xyx", 'x', UpgradeType.values()[i].getMat1(), 'y', UpgradeType.values()[i].getMat2(), 'z', ModItems.netherCore);

		GameRegistry.addRecipe(new ItemStack(ModItems.spawnerUpgrade, 1, UpgradeType.tierDragonEgg.ordinal()), "xyx", "yzy", "xyx", 'x', new ItemStack(Item.appleGold, 1, 1), 'y', ModItems.netherCore, 'z', Block.dragonEgg);
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.spawnerUpgrade, 1, UpgradeType.spawnCount.ordinal()), "xyx", "yzy", "xyx", 'x', "mobEgg", 'y', "mobEgg", 'z', ModItems.netherCore));
		GameRegistry.addShapelessRecipe(new ItemStack(Block.dragonEgg), new ItemStack(ModItems.spawnerUpgrade, 1, UpgradeType.tierDragonEgg.ordinal()));

		// Vanilla
		GameRegistry.addShapelessRecipe(new ItemStack(Item.glowstone, 2), new ItemStack(ModItems.glowingReed));
		GameRegistry.addRecipe(new ItemStack(Item.netherQuartz, 6), "xxx", "yyy", "xxx", 'x', ModItems.quarzBerry, 'y', Block.glass);
		GameRegistry.addRecipe(new ItemStack(Item.arrow, 16), "x", "y", "z", 'x', ModItems.wolfTeeth, 'y', Item.stick, 'z', Item.feather);
		GameRegistry.addRecipe(new ItemStack(Item.ingotIron), "xxx", "xxx", "xxx", 'x', ModItems.ironNugget);
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.ironNugget, 9), Item.ingotIron);
		GameRegistry.addRecipe(new ItemStack(Block.silverfish), "xxx", "xyx", "xxx", 'x', ModItems.silverfishScale, 'y', Block.stone);
		GameRegistry.addRecipe(new ItemStack(Block.silverfish, 1, 1), "xxx", "xyx", "xxx", 'x', ModItems.silverfishScale, 'y', Block.cobblestone);
		GameRegistry.addRecipe(new ItemStack(Block.silverfish, 1, 2), "xxx", "xyx", "xxx", 'x', ModItems.silverfishScale, 'y', new ItemStack(Block.stoneBrick));
		GameRegistry.addSmelting(ModItems.flour.itemID, new ItemStack(Item.bread), 0.0F);
		GameRegistry.addRecipe(new ItemStack(Block.torchWood, 8), "x", "y", 'x', Block.fire, 'y', Item.stick);
		GameRegistry.addSmelting(ModBlocks.blazingCactoid.blockID, new ItemStack(ModItems.blazeIngot, 1, 2), 1F);
		GameRegistry.addRecipe(new ItemStack(Item.bucketLava), "xxx", "xyx", "xxx", 'x', ModItems.lavaBerry, 'y', Item.bucketEmpty);
		GameRegistry.addRecipe(new ItemStack(Item.blazeRod), "x", "x", "x", 'x', new ItemStack(ModItems.blazeIngot, 1, 2));
	}

	private static void createSceptreRecipe(Item sceptre, int capMeta, Item handle, int durability) {
		GameRegistry.addRecipe(new ItemStack(sceptre, 1, durability), "  x", " y ", "z  ", 'x', new ItemStack(ModItems.sceptreCap, 1, capMeta), 'y', handle, 'z', Item.netherrackBrick);
	}

	private static void createCapRecipe(int capMeta, ItemStack capMaterial, Item capCore) {
		createCapRecipe(capMeta, capMaterial, capMaterial, capCore);
	}

	private static void createCapRecipe(int capMeta, ItemStack capMaterial, ItemStack capMaterialSec, Item capCore) {
		GameRegistry.addRecipe(new ItemStack(ModItems.sceptreCap, 1, capMeta), "yxy", "xzx", "yxy", 'x', capMaterial, 'y', capMaterialSec, 'z', capCore);
	}

	private static void registerBlockRecipes() {
		GameRegistry.addSmelting(Block.slowSand.blockID, new ItemStack(ModBlocks.soulGlass), 1F);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.soulGlass, 4, 1), "xx", "xx", 'x', new ItemStack(ModBlocks.soulGlass, 1, 0));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.soulChest), "xyx", "x x", "xxx", 'x', Block.slowSand, 'y', Item.netherQuartz);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.volcanicFurnaceIdle), "yxy", "ywy", "zzz", 'x', Item.cauldron, 'y', Block.netherBrick, 'z', Block.obsidian, 'w', ModBlocks.denseLavaCell);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.denseLavaCell), "yxy", "xzx", "yxy", 'x', Item.bucketLava, 'y', Item.diamond, 'z', Block.fire);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.glowBox, 4, 11), " y ", "yxy", " y ", 'x', Block.glowStone, 'y', Block.thinGlass);

		for (int i = 0; i < dyes.length; i++) {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.colouredQuartzBlock, 8, i), "xxx", "xyx", "xxx", 'x', "blockQuartz", 'y', dyes[i]));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.colouredChiselledQuartzBlock, 8, i), "xxx", "xyx", "xxx", 'x', "blockQuartzChiselled", 'y', dyes[i]));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.glowBox, 8, i), "xxx", "xyx", "xxx", 'x', new ItemStack(ModBlocks.glowBox, 1, OreDictionary.WILDCARD_VALUE), 'y', dyes[i]));
			GameRegistry.addRecipe(new ItemStack(ModBlocks.colouredQuartzBlockStairs[i], 4), "x  ", "xx ", "xxx", 'x', new ItemStack(ModBlocks.colouredQuartzBlock, 1, i));
		}
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.blockNetherQuartz, 1, 0), "   ", " x ", "   ", 'x', "blockQuartz"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.blockNetherQuartz, 1, 1), "   ", " x ", "   ", 'x', "blockQuartzChiselled"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.blockNetherQuartz, 1, 2), "   ", " x ", "   ", 'x', "blockQuartzPillar"));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.soulGlassStairs), "x  ", "xx ", "xxx", 'x', new ItemStack(ModBlocks.soulGlass, 1, 1));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.reproducer), "yzy", "wxw", "zwz", 'x', new ItemStack(Block.blockNetherQuartz, 1, 2), 'y', Block.obsidian, 'z', Block.slowSand, 'w', "mobEgg"));

		int index = 0;
		for (Block pillar : ModBlocks.colouredQuartzPillar)
			for (int i = 0; i < 4; i++)
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(pillar, 8, i), "xxx", "xyx", "xxx", 'x', "blockQuartzPillar", 'y', dyes[index++]));

		if (GanysNether.enableUndertaker)
			GameRegistry.addSmelting(ModBlocks.soulChest.blockID, new ItemStack(ModBlocks.undertaker), 1F);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.magmaticCentrifuge), "zyz", "wxw", "zyz", 'x', Block.obsidian, 'y', Block.netherBrick, 'z', Item.blazeRod, 'w', Block.glass);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.magmaticCentrifuge), "zyz", "wxw", "zyz", 'x', Block.obsidian, 'y', Block.netherBrick, 'z', Item.blazeRod, 'w', ModBlocks.soulGlass);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.soulTNT), "xyx", "yxy", "xyx", 'x', Item.gunpowder, 'y', Block.slowSand);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.thermalSmelter), "xxx", "yzy", 'x', Item.coal, 'y', Item.bucketLava, 'z', Block.furnaceIdle);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.horseArmourStand), "x  ", "xxx", "y y", 'x', new ItemStack(Block.stoneSingleSlab), 'y', Item.ingotIron);
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.focusedLavaCell), "zzz", "xyx", "xwx", 'x', ModBlocks.denseLavaCell, 'y', new ItemStack(ModBlocks.colouredChiselledQuartzBlock), 'z', "ingotBlaze", 'w', ModItems.netherCore));

		// Vanilla
		GameRegistry.addRecipe(new ItemStack(Block.slowSand, 6), "xxx", "yyy", "xxx", 'x', ModItems.spookyFlour, 'y', Block.sand);
	}

	private static void registerOreDictionary() {
		OreDictionary.registerOre("ingotBlaze", new ItemStack(ModItems.blazeIngot, 1, 1));
		OreDictionary.registerOre("nuggetBlaze", new ItemStack(ModItems.blazeIngot, 1, 2));
		OreDictionary.registerOre("nuggetIron", ModItems.ironNugget);
		OreDictionary.registerOre("dustWheat", ModItems.flour);
		OreDictionary.registerOre("mobEgg", new ItemStack(Item.monsterPlacer, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("mobEgg", new ItemStack(ModItems.skeletonSpawner, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("blockSpawner", new ItemStack(Block.mobSpawner, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("blockSpawner", new ItemStack(ModBlocks.extendedSpawner, 1, OreDictionary.WILDCARD_VALUE));

		OreDictionary.registerOre("blockQuartz", new ItemStack(ModBlocks.colouredQuartzBlock, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("blockQuartz", new ItemStack(Block.blockNetherQuartz, 1, 0));

		OreDictionary.registerOre("blockQuartzChiselled", new ItemStack(ModBlocks.colouredChiselledQuartzBlock, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("blockQuartzChiselled", new ItemStack(Block.blockNetherQuartz, 1, 1));

		for (int i = 0; i < ModBlocks.colouredQuartzPillar.length; i++)
			OreDictionary.registerOre("blockQuartzPillar", new ItemStack(ModBlocks.colouredQuartzPillar[i], 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("blockQuartzPillar", new ItemStack(Block.blockNetherQuartz, 1, 2));

		if (OreDictionary.getOres("egg").isEmpty())
			OreDictionary.registerOre("egg", new ItemStack(Item.egg));
		if (OreDictionary.getOres("mobHead").isEmpty())
			OreDictionary.registerOre("mobHead", new ItemStack(Item.skull, 1, OreDictionary.WILDCARD_VALUE));
	}
}