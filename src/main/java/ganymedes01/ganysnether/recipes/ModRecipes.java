package ganymedes01.ganysnether.recipes;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.ModBlocks;
import ganymedes01.ganysnether.ModItems;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.items.SpawnerUpgrade.UpgradeType;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.RecipeSorter;
import net.minecraftforge.oredict.RecipeSorter.Category;
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
		RecipeSorter.register("EnchantSensitive", EnchantSensitiveRecipe.class, Category.SHAPED, "after:minecraft:shaped");

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
		GameRegistry.addRecipe(new ItemStack(ModItems.bottomlessBucket), "xxx", "zxz", "yzy", 'x', Items.bucket, 'y', Blocks.soul_sand, 'z', Items.netherbrick);
		GameRegistry.addRecipe(EnchantSensitiveRecipe.makeRecipe(new ItemStack(ModItems.baseballBat), " zx", " yz", "y  ", 'x', Items.diamond, 'y', Items.netherbrick, 'z', Utils.enchantStack(new ItemStack(Items.enchanted_book), Enchantment.knockback, 1)));
		GameRegistry.addRecipe(EnchantSensitiveRecipe.makeRecipe(new ItemStack(ModItems.baseballBat), " zx", " y ", "y  ", 'x', Items.diamond, 'y', Items.netherbrick, 'z', Utils.enchantStack(new ItemStack(Items.enchanted_book), Enchantment.knockback, 2)));
		GameRegistry.addSmelting(ModItems.batWing, new ItemStack(ModItems.cookedBatWing), 0.0F);
		GameRegistry.addSmelting(new ItemStack(ModItems.blazeIngot), new ItemStack(ModItems.blazeIngot, 1, 1), 0.0F);
		GameRegistry.addRecipe(new ItemStack(ModItems.blazeIngot, 1, 0), "x", "x", "x", 'x', Items.blaze_rod);
		GameRegistry.addRecipe(new ItemStack(ModItems.livingSoul, 2), "zxz", "xyx", "zyz", 'x', Items.rotten_flesh, 'y', Items.bone, 'z', Blocks.soul_sand);
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.blazeIngot, 1, 1), "xxx", "xxx", "xxx", 'x', "nuggetBlaze"));
		GameRegistry.addSmelting(ModItems.spookyFlour, new ItemStack(ModItems.dimensionalBread), 0.0F);

		createCapRecipe(0, new ItemStack(Blocks.tnt), new ItemStack(ModItems.blazeIngot, 1, 1), Items.fire_charge);
		createCapRecipe(1, new ItemStack(Items.quartz), new ItemStack(Items.nether_wart), Items.nether_star);
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.sceptreCap, 1, 2), "xxx", "xyx", "xxx", 'x', "egg", 'y', Items.nether_star));

		createSceptreRecipe(ModItems.sceptreOfFireCharging, 0, Items.magma_cream, GanysNether.sceptreOfFireCharging);
		createSceptreRecipe(ModItems.sceptreOfLightning, 1, Items.gold_ingot, GanysNether.sceptreOfLightningDurability);
		createSceptreRecipe(ModItems.sceptreOfConcealment, 2, Items.gold_ingot, GanysNether.sceptreOfConcealmentDurability);
		GameRegistry.addRecipe(new ItemStack(ModItems.netherCore), "xyz", "wab", "cde", 'x', Items.magma_cream, 'y', Items.nether_wart, 'z', Items.quartz, 'w', Items.blaze_rod, 'a', Items.glowstone_dust, 'b', Blocks.soul_sand, 'c', Blocks.nether_brick, 'd', Items.ghast_tear, 'e', new ItemStack(Items.skull, 1, 1));

		for (int i = 0; i < UpgradeType.values().length; i++)
			if (UpgradeType.values()[i].getMat1() != null && UpgradeType.values()[i].getMat2() != null)
				GameRegistry.addRecipe(new ItemStack(ModItems.spawnerUpgrade, 1, i), "xyx", "yzy", "xyx", 'x', UpgradeType.values()[i].getMat1(), 'y', UpgradeType.values()[i].getMat2(), 'z', ModItems.netherCore);

		GameRegistry.addRecipe(new ItemStack(ModItems.spawnerUpgrade, 1, UpgradeType.tierDragonEgg.ordinal()), "xyx", "yzy", "xyx", 'x', new ItemStack(Items.golden_apple, 1, 1), 'y', ModItems.netherCore, 'z', Blocks.dragon_egg);
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.spawnerUpgrade, 1, UpgradeType.spawnCount.ordinal()), "xyx", "yzy", "xyx", 'x', "mobEgg", 'y', "mobEgg", 'z', ModItems.netherCore));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.spawnerUpgrade, 1, UpgradeType.spawnCount.ordinal()), "xyx", "yzy", "xyx", 'x', "itemSkull", 'y', "itemSkull", 'z', ModItems.netherCore));
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.dragon_egg), new ItemStack(ModItems.spawnerUpgrade, 1, UpgradeType.tierDragonEgg.ordinal()));

		// Vanilla
		GameRegistry.addShapelessRecipe(new ItemStack(Items.glowstone_dust, 2), new ItemStack(ModItems.glowingReed));
		GameRegistry.addRecipe(new ItemStack(Items.quartz, 6), "xxx", "yyy", "xxx", 'x', ModItems.quarzBerry, 'y', Blocks.glass);
		GameRegistry.addRecipe(new ItemStack(Items.arrow, 16), "x", "y", "z", 'x', ModItems.wolfTeeth, 'y', Items.stick, 'z', Items.feather);
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.iron_ingot), "xxx", "xxx", "xxx", 'x', "nuggetIron"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.ironNugget, 9), "x", 'x', "ingotIron"));
		GameRegistry.addRecipe(new ItemStack(Blocks.monster_egg), "xxx", "xyx", "xxx", 'x', ModItems.silverfishScale, 'y', Blocks.stone);
		GameRegistry.addRecipe(new ItemStack(Blocks.monster_egg, 1, 1), "xxx", "xyx", "xxx", 'x', ModItems.silverfishScale, 'y', Blocks.cobblestone);
		GameRegistry.addRecipe(new ItemStack(Blocks.monster_egg, 1, 2), "xxx", "xyx", "xxx", 'x', ModItems.silverfishScale, 'y', new ItemStack(Blocks.stonebrick));
		GameRegistry.addSmelting(ModItems.flour, new ItemStack(Items.bread), 0.0F);
		GameRegistry.addRecipe(new ItemStack(Blocks.torch, 8), "x", "y", 'x', Blocks.fire, 'y', Items.stick);
		GameRegistry.addSmelting(ModBlocks.blazingCactoid, new ItemStack(ModItems.blazeIngot, 1, 2), 1F);
		GameRegistry.addRecipe(new ItemStack(Items.lava_bucket), "xxx", "xyx", "xxx", 'x', ModItems.lavaBerry, 'y', Items.bucket);
		GameRegistry.addRecipe(new ItemStack(Items.blaze_rod), "x", "x", "x", 'x', new ItemStack(ModItems.blazeIngot, 1, 2));
	}

	private static void createSceptreRecipe(Item sceptre, int capMeta, Item handle, int durability) {
		GameRegistry.addRecipe(new ItemStack(sceptre, 1, durability), "  x", " y ", "z  ", 'x', new ItemStack(ModItems.sceptreCap, 1, capMeta), 'y', handle, 'z', Items.netherbrick);
	}

	private static void createCapRecipe(int capMeta, ItemStack capMaterial, ItemStack capMaterialSec, Item capCore) {
		GameRegistry.addRecipe(new ItemStack(ModItems.sceptreCap, 1, capMeta), "yxy", "xzx", "yxy", 'x', capMaterial, 'y', capMaterialSec, 'z', capCore);
	}

	private static void registerBlockRecipes() {
		GameRegistry.addSmelting(Blocks.soul_sand, new ItemStack(ModBlocks.soulGlass), 1F);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.soulGlass, 4, 1), "xx", "xx", 'x', new ItemStack(ModBlocks.soulGlass, 1, 0));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.soulChest), "xyx", "x x", "xxx", 'x', Blocks.soul_sand, 'y', Items.quartz);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.volcanicFurnaceIdle), "yxy", "ywy", "zzz", 'x', Items.cauldron, 'y', Blocks.nether_brick, 'z', Blocks.obsidian, 'w', ModBlocks.denseLavaCell);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.denseLavaCell), "yxy", "xzx", "yxy", 'x', Items.lava_bucket, 'y', Items.diamond, 'z', Blocks.fire);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.glowBox, 4, 11), " y ", "yxy", " y ", 'x', Blocks.glowstone, 'y', Blocks.glass_pane);

		for (int i = 0; i < dyes.length; i++) {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.colouredQuartzBlock, 8, i), "xxx", "xyx", "xxx", 'x', "blockQuartz", 'y', dyes[i]));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.colouredChiselledQuartzBlock, 8, i), "xxx", "xyx", "xxx", 'x', "blockQuartzChiselled", 'y', dyes[i]));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.glowBox, 8, i), "xxx", "xyx", "xxx", 'x', new ItemStack(ModBlocks.glowBox, 1, OreDictionary.WILDCARD_VALUE), 'y', dyes[i]));
			GameRegistry.addRecipe(new ItemStack(ModBlocks.colouredQuartzBlockStairs[i], 4), "x  ", "xx ", "xxx", 'x', new ItemStack(ModBlocks.colouredQuartzBlock, 1, i));
		}
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Blocks.quartz_block, 1, 0), "   ", " x ", "   ", 'x', "blockQuartz"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Blocks.quartz_block, 1, 1), "   ", " x ", "   ", 'x', "blockQuartzChiselled"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Blocks.quartz_block, 1, 2), "   ", " x ", "   ", 'x', "blockQuartzPillar"));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.soulGlassStairs, 4), "x  ", "xx ", "xxx", 'x', new ItemStack(ModBlocks.soulGlass, 1, 1));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.reproducer), "yzy", "wxw", "zwz", 'x', new ItemStack(Blocks.quartz_block, 1, 2), 'y', Blocks.obsidian, 'z', Blocks.soul_sand, 'w', "mobEgg"));

		int index = 0;
		for (Block pillar : ModBlocks.colouredQuartzPillar)
			for (int i = 0; i < 4; i++)
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(pillar, 8, i), "xxx", "xyx", "xxx", 'x', "blockQuartzPillar", 'y', dyes[index++]));

		if (GanysNether.enableUndertaker)
			GameRegistry.addSmelting(ModBlocks.soulChest, new ItemStack(ModBlocks.undertaker), 1F);
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.magmaticCentrifuge), "zyz", "wxw", "zyz", 'x', Blocks.obsidian, 'y', Blocks.nether_brick, 'z', Items.blaze_rod, 'w', "blockGlass"));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.soulTNT), "xyx", "yxy", "xyx", 'x', Items.gunpowder, 'y', Blocks.soul_sand);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.thermalSmelter), "xxx", "yzy", 'x', Items.coal, 'y', Items.lava_bucket, 'z', Blocks.furnace);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.horseArmourStand), "x  ", "xxx", "y y", 'x', new ItemStack(Blocks.stone_slab), 'y', Items.iron_ingot);
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.focusedLavaCell), "zzz", "xyx", "xwx", 'x', ModBlocks.denseLavaCell, 'y', new ItemStack(ModBlocks.colouredChiselledQuartzBlock), 'z', "ingotBlaze", 'w', ModItems.netherCore));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.soulGlassPane0, 16), "xxx", "xxx", 'x', new ItemStack(ModBlocks.soulGlass));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.soulGlassPane1, 16), "xxx", "xxx", 'x', new ItemStack(ModBlocks.soulGlass, 1, 1));
		// Vanilla
		GameRegistry.addRecipe(new ItemStack(Blocks.soul_sand, 6), "xxx", "yyy", "xxx", 'x', ModItems.spookyFlour, 'y', Blocks.sand);
	}

	private static void registerOreDictionary() {
		OreDictionary.registerOre("ingotBlaze", new ItemStack(ModItems.blazeIngot, 1, 1));
		OreDictionary.registerOre("nuggetBlaze", new ItemStack(ModItems.blazeIngot, 1, 2));
		OreDictionary.registerOre("nuggetIron", ModItems.ironNugget);
		OreDictionary.registerOre("dustWheat", ModItems.flour);
		OreDictionary.registerOre("mobEgg", new ItemStack(Items.spawn_egg, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("mobEgg", new ItemStack(ModItems.skeletonSpawner, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("blockSpawner", new ItemStack(Blocks.mob_spawner, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("blockSpawner", new ItemStack(ModBlocks.extendedSpawner, 1, OreDictionary.WILDCARD_VALUE));

		OreDictionary.registerOre("blockQuartz", new ItemStack(ModBlocks.colouredQuartzBlock, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("blockQuartz", new ItemStack(Blocks.quartz_block, 1, 0));

		OreDictionary.registerOre("blockGlass", new ItemStack(ModBlocks.soulGlass, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("blockGlassBrown", new ItemStack(ModBlocks.soulGlass, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("paneGlass", new ItemStack(ModBlocks.soulGlassPane0));
		OreDictionary.registerOre("paneGlass", new ItemStack(ModBlocks.soulGlassPane1));
		OreDictionary.registerOre("paneGlassBrown", new ItemStack(ModBlocks.soulGlassPane0));
		OreDictionary.registerOre("paneGlassBrown", new ItemStack(ModBlocks.soulGlassPane1));

		OreDictionary.registerOre("blockQuartzChiselled", new ItemStack(ModBlocks.colouredChiselledQuartzBlock, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("blockQuartzChiselled", new ItemStack(Blocks.quartz_block, 1, 1));

		for (int i = 0; i < ModBlocks.colouredQuartzPillar.length; i++)
			OreDictionary.registerOre("blockQuartzPillar", new ItemStack(ModBlocks.colouredQuartzPillar[i], 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("blockQuartzPillar", new ItemStack(Blocks.quartz_block, 1, 2));

		OreDictionary.registerOre("egg", new ItemStack(Items.egg));
		OreDictionary.registerOre("itemSkull", new ItemStack(Items.skull, 1, OreDictionary.WILDCARD_VALUE));
	}
}