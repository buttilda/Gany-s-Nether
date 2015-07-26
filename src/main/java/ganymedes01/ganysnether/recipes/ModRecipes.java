package ganymedes01.ganysnether.recipes;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.ModBlocks;
import ganymedes01.ganysnether.ModItems;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.items.SpawnerUpgrade.UpgradeType;
import ganymedes01.ganysnether.lib.Reference;
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
		RecipeSorter.register(Reference.MOD_ID + ".EnchantSensitive", EnchantSensitiveRecipe.class, Category.SHAPED, "after:minecraft:shaped");

		registerOreDictionary();

		registerBlockRecipes();
		registerItemRecipes();
	}

	private static void registerItemRecipes() {
		if (GanysNether.enableBlazeArmour) {
			addShapedRecipe(new ItemStack(ModItems.blazeHelmet), "xxx", "x x", 'x', "ingotBlaze");
			addShapedRecipe(new ItemStack(ModItems.blazeChestplate), "x x", "xxx", "xxx", 'x', "ingotBlaze");
			addShapedRecipe(new ItemStack(ModItems.blazeLeggings), "xxx", "x x", "x x", 'x', "ingotBlaze");
			addShapedRecipe(new ItemStack(ModItems.blazeBoots), "x x", "x x", 'x', "ingotBlaze");
		}

		GameRegistry.addSmelting(new ItemStack(ModItems.blazeIngot), new ItemStack(ModItems.blazeIngot, 1, 1), 0.0F);
		addShapedRecipe(new ItemStack(ModItems.blazeIngot, 1, 0), "x", "x", "x", 'x', Items.blaze_rod);
		addShapedRecipe(new ItemStack(ModItems.blazeIngot, 1, 1), "xxx", "xxx", "xxx", 'x', "nuggetBlaze");
		addShapelessRecipe(new ItemStack(ModItems.blazeIngot, 9, 2), "ingotBlaze");

		if (GanysNether.enableBottomlessBucket)
			addShapedRecipe(new ItemStack(ModItems.bottomlessBucket), "xxx", "zxz", "yzy", 'x', Items.bucket, 'y', Blocks.soul_sand, 'z', Items.netherbrick);

		if (GanysNether.enableBaseballBat) {
			GameRegistry.addRecipe(EnchantSensitiveRecipe.makeRecipe(new ItemStack(ModItems.baseballBat), " zx", " yz", "y  ", 'x', "gemDiamond", 'y', Items.netherbrick, 'z', Utils.enchantStack(new ItemStack(Items.enchanted_book), Enchantment.knockback, 1)));
			GameRegistry.addRecipe(EnchantSensitiveRecipe.makeRecipe(new ItemStack(ModItems.baseballBat), " zx", " y ", "y  ", 'x', "gemDiamond", 'y', Items.netherbrick, 'z', Utils.enchantStack(new ItemStack(Items.enchanted_book), Enchantment.knockback, 2)));
		}

		if (GanysNether.enableLivingSoul)
			addShapedRecipe(new ItemStack(ModItems.livingSoul, 2), "zxz", "xyx", "zyz", 'x', Items.rotten_flesh, 'y', Items.bone, 'z', Blocks.soul_sand);

		if (GanysNether.enableSceptres) {
			createCapRecipe(0, new ItemStack(Blocks.tnt), "ingotBlaze", Items.fire_charge);
			createCapRecipe(1, new ItemStack(Items.quartz), new ItemStack(Items.nether_wart), Items.nether_star);
			addShapedRecipe(new ItemStack(ModItems.sceptreCap, 1, 2), "xxx", "xyx", "xxx", 'x', "egg", 'y', Items.nether_star);
			createCapRecipe(3, "skullWitherSkeleton", new ItemStack(Items.coal), Items.nether_star);
			createSceptreRecipe(ModItems.sceptreOfFireCharging, 0, Items.magma_cream);
			createSceptreRecipe(ModItems.sceptreOfLightning, 1, "ingotGold");
			createSceptreRecipe(ModItems.sceptreOfConcealment, 2, "ingotGold");
			createSceptreRecipe(ModItems.sceptreOfWithering, 3, Items.bone);
		}

		if (GanysNether.enableVolcanicFurnace || GanysNether.enableSpawners)
			addShapedRecipe(new ItemStack(ModItems.netherCore), "xyz", "wab", "cde", 'x', Items.magma_cream, 'y', Items.nether_wart, 'z', Items.quartz, 'w', Items.blaze_rod, 'a', Items.glowstone_dust, 'b', Blocks.soul_sand, 'c', Blocks.nether_brick, 'd', Items.ghast_tear, 'e', new ItemStack(Items.skull, 1, 1));

		if (GanysNether.enableSpawners) {
			for (int i = 0; i < UpgradeType.values().length; i++)
				if (UpgradeType.values()[i].getMat1() != null && UpgradeType.values()[i].getMat2() != null)
					addShapedRecipe(new ItemStack(ModItems.spawnerUpgrade, 1, i), "xyx", "yzy", "xyx", 'x', UpgradeType.values()[i].getMat1(), 'y', UpgradeType.values()[i].getMat2(), 'z', ModItems.netherCore);
			addShapedRecipe(new ItemStack(ModItems.spawnerUpgrade, 1, UpgradeType.tierDragonEgg.ordinal()), "xyx", "yzy", "xyx", 'x', new ItemStack(Items.golden_apple, 1, 1), 'y', ModItems.netherCore, 'z', Blocks.dragon_egg);
			addShapedRecipe(new ItemStack(ModItems.spawnerUpgrade, 1, UpgradeType.spawnCount.ordinal()), "xyx", "yzy", "xyx", 'x', "mobEgg", 'y', "mobEgg", 'z', ModItems.netherCore);
			addShapedRecipe(new ItemStack(ModItems.spawnerUpgrade, 1, UpgradeType.spawnCount.ordinal()), "xyx", "yzy", "xyx", 'x', "itemSkull", 'y', "itemSkull", 'z', ModItems.netherCore);
			addShapelessRecipe(new ItemStack(Blocks.dragon_egg), new ItemStack(ModItems.spawnerUpgrade, 1, UpgradeType.tierDragonEgg.ordinal()));
		}

		if (GanysNether.enableReproducerAndDrops) {
			addShapedRecipe(new ItemStack(Items.arrow, 16), "x", "y", "z", 'x', ModItems.wolfTeeth, 'y', "stickWood", 'z', Items.feather);
			addShapedRecipe(new ItemStack(Blocks.monster_egg), "xxx", "xyx", "xxx", 'x', ModItems.silverfishScale, 'y', Blocks.stone);
			addShapedRecipe(new ItemStack(Blocks.monster_egg, 1, 1), "xxx", "xyx", "xxx", 'x', ModItems.silverfishScale, 'y', Blocks.cobblestone);
			addShapedRecipe(new ItemStack(Blocks.monster_egg, 1, 2), "xxx", "xyx", "xxx", 'x', ModItems.silverfishScale, 'y', new ItemStack(Blocks.stonebrick));
			GameRegistry.addSmelting(ModItems.batWing, new ItemStack(ModItems.cookedBatWing), 0.0F);
		}

		// Others
		if (GanysNether.enableFlour)
			GameRegistry.addSmelting(ModItems.flour, new ItemStack(Items.bread), 0.0F);
		if (GanysNether.enableMagmaticCentrifuge)
			addShapedRecipe(new ItemStack(Blocks.torch, 8), "x", "y", 'x', Blocks.fire, 'y', "stickWood");

		// Iron
		addShapedRecipe(new ItemStack(Items.iron_ingot), "xxx", "xxx", "xxx", 'x', "nuggetIron");
		addShapedRecipe(new ItemStack(ModItems.ironNugget, 9), "x", 'x', "ingotIron");

		if (GanysNether.shouldGenerateCrops) {
			if (GanysNether.shouldGenerateBlazingCactoid) {
				GameRegistry.addSmelting(ModBlocks.blazingCactoid, new ItemStack(ModItems.blazeIngot, 1, 2), 1F);
				addShapedRecipe(new ItemStack(Items.blaze_rod), "x", "x", "x", 'x', "nuggetBlaze");
			}
			if (GanysNether.shouldGenerateGlowingReed)
				addShapelessRecipe(new ItemStack(Items.glowstone_dust, 2), new ItemStack(ModItems.glowingReed));
			if (GanysNether.shouldGenerateSpectreWheat)
				GameRegistry.addSmelting(ModItems.spookyFlour, new ItemStack(ModItems.dimensionalBread), 0.0F);
			if (GanysNether.shouldGenerateQuarzBerryBush)
				addShapedRecipe(new ItemStack(Items.quartz, 6), "xxx", "yyy", "xxx", 'x', ModItems.quarzBerry, 'y', Blocks.glass);
			if (GanysNether.shouldGenerateHellBush)
				addShapedRecipe(new ItemStack(Items.lava_bucket), "xxx", "xyx", "xxx", 'x', ModItems.lavaBerry, 'y', Items.bucket);

		}
	}

	private static void createSceptreRecipe(Item sceptre, int capMeta, Object handle) {
		addShapedRecipe(new ItemStack(sceptre), "  x", " y ", "z  ", 'x', new ItemStack(ModItems.sceptreCap, 1, capMeta), 'y', handle, 'z', Items.netherbrick);
	}

	private static void createCapRecipe(int capMeta, Object capMaterial, Object capMaterialSec, Item capCore) {
		addShapedRecipe(new ItemStack(ModItems.sceptreCap, 1, capMeta), "yxy", "xzx", "yxy", 'x', capMaterial, 'y', capMaterialSec, 'z', capCore);
	}

	private static void registerBlockRecipes() {
		if (GanysNether.enableSoulGlass) {
			GameRegistry.addSmelting(Blocks.soul_sand, new ItemStack(ModBlocks.soulGlass), 1F);
			addShapedRecipe(new ItemStack(ModBlocks.soulGlass, 4, 1), "xx", "xx", 'x', new ItemStack(ModBlocks.soulGlass, 1, 0));
			addShapedRecipe(new ItemStack(ModBlocks.soulGlassStairs, 4), "x  ", "xx ", "xxx", 'x', new ItemStack(ModBlocks.soulGlass, 1, 1));
			addShapedRecipe(new ItemStack(ModBlocks.soulGlassPane0, 16), "xxx", "xxx", 'x', new ItemStack(ModBlocks.soulGlass));
			addShapedRecipe(new ItemStack(ModBlocks.soulGlassPane1, 16), "xxx", "xxx", 'x', new ItemStack(ModBlocks.soulGlass, 1, 1));
		}

		if (GanysNether.enableSoulChest)
			addShapedRecipe(new ItemStack(ModBlocks.soulChest), "xyx", "x x", "xxx", 'x', Blocks.soul_sand, 'y', "gemQuartz");

		if (GanysNether.enableVolcanicFurnace) {
			addShapedRecipe(new ItemStack(ModBlocks.volcanicFurnace), "yxy", "ywy", "zzz", 'x', Items.cauldron, 'y', Blocks.nether_brick, 'z', Blocks.obsidian, 'w', ModBlocks.denseLavaCell);
			addShapedRecipe(new ItemStack(ModBlocks.denseLavaCell), "yxy", "xzx", "yxy", 'x', Items.lava_bucket, 'y', "gemDiamond", 'z', Blocks.fire);
			addShapedRecipe(new ItemStack(ModBlocks.focusedLavaCell), "zzz", "xyx", "xwx", 'x', ModBlocks.denseLavaCell, 'y', GanysNether.enableQuartz ? new ItemStack(ModBlocks.colouredChiselledQuartzBlock) : "blockCoal", 'z', "ingotBlaze", 'w', ModItems.netherCore);
		}

		if (GanysNether.enableGlowbox) {
			addShapedRecipe(new ItemStack(ModBlocks.glowBox, 4, 11), " y ", "yxy", " y ", 'x', "dustGlowstone", 'y', Blocks.glass_pane);
			for (int i = 0; i < dyes.length; i++)
				addShapedRecipe(new ItemStack(ModBlocks.glowBox, 8, i), "xxx", "xyx", "xxx", 'x', new ItemStack(ModBlocks.glowBox, 1, OreDictionary.WILDCARD_VALUE), 'y', dyes[i]);
		}

		if (GanysNether.enableQuartz) {
			for (int i = 0; i < dyes.length; i++) {
				addShapedRecipe(new ItemStack(ModBlocks.colouredQuartzBlock, 8, i), "xxx", "xyx", "xxx", 'x', "blockQuartz", 'y', dyes[i]);
				addShapedRecipe(new ItemStack(ModBlocks.colouredChiselledQuartzBlock, 8, i), "xxx", "xyx", "xxx", 'x', "blockQuartzChiselled", 'y', dyes[i]);
				addShapedRecipe(new ItemStack(ModBlocks.colouredQuartzBlockStairs[i], 4), "x  ", "xx ", "xxx", 'x', new ItemStack(ModBlocks.colouredQuartzBlock, 1, i));
			}
			int index = 0;
			for (Block pillar : ModBlocks.colouredQuartzPillar)
				for (int i = 0; i < 4; i++)
					addShapedRecipe(new ItemStack(pillar, 8, i), "xxx", "xyx", "xxx", 'x', "blockQuartzPillar", 'y', dyes[index++]);
			addShapedRecipe(new ItemStack(Blocks.quartz_block, 1, 0), "   ", " x ", "   ", 'x', "blockQuartz");
			addShapedRecipe(new ItemStack(Blocks.quartz_block, 1, 1), "   ", " x ", "   ", 'x', "blockQuartzChiselled");
			addShapedRecipe(new ItemStack(Blocks.quartz_block, 1, 2), "   ", " x ", "   ", 'x', "blockQuartzPillar");
		}

		if (GanysNether.enableReproducerAndDrops)
			addShapedRecipe(new ItemStack(ModBlocks.reproducer), "yzy", "wxw", "zwz", 'x', new ItemStack(Blocks.quartz_block, 1, 2), 'y', Blocks.obsidian, 'z', Blocks.soul_sand, 'w', "mobEgg");

		if (GanysNether.enableMagmaticCentrifuge)
			addShapedRecipe(new ItemStack(ModBlocks.magmaticCentrifuge), "zyz", "wxw", "zyz", 'x', Blocks.obsidian, 'y', Blocks.nether_brick, 'z', Items.blaze_rod, 'w', "blockGlass");

		if (GanysNether.enableSoulTNT)
			addShapedRecipe(new ItemStack(ModBlocks.soulTNT), "xyx", "yxy", "xyx", 'x', Items.gunpowder, 'y', Blocks.soul_sand);

		if (GanysNether.enableThermalSmelter)
			addShapedRecipe(new ItemStack(ModBlocks.thermalSmelter), "xxx", "yzy", 'x', Items.coal, 'y', Items.lava_bucket, 'z', Blocks.furnace);

		if (GanysNether.enableHorseArmourStand)
			addShapedRecipe(new ItemStack(ModBlocks.horseArmourStand), "x  ", "xxx", "y y", 'x', new ItemStack(Blocks.stone_slab), 'y', Items.iron_ingot);

		if (GanysNether.shouldGenerateCrops && GanysNether.shouldGenerateSpectreWheat)
			addShapedRecipe(new ItemStack(Blocks.soul_sand, 6), "xxx", "yyy", "xxx", 'x', ModItems.spookyFlour, 'y', Blocks.sand);

		if (GanysNether.enableUndertaker)
			GameRegistry.addSmelting(ModBlocks.soulChest, new ItemStack(ModBlocks.undertaker), 0.1F);

		if (GanysNether.enableBlazeBlock) {
			addShapedRecipe(new ItemStack(ModBlocks.blazeBlock), "xxx", "xxx", "xxx", 'x', "ingotBlaze");
			addShapelessRecipe(new ItemStack(ModItems.blazeIngot, 9, 1), "blockBlaze");
		}
	}

	private static void registerOreDictionary() {
		OreDictionary.registerOre("ingotBlaze", new ItemStack(ModItems.blazeIngot, 1, 1));
		OreDictionary.registerOre("nuggetBlaze", new ItemStack(ModItems.blazeIngot, 1, 2));

		if (GanysNether.enableBlazeBlock)
			OreDictionary.registerOre("blockBlaze", ModBlocks.blazeBlock);

		OreDictionary.registerOre("nuggetIron", ModItems.ironNugget);
		if (GanysNether.enableFlour)
			OreDictionary.registerOre("dustWheat", ModItems.flour);
		OreDictionary.registerOre("mobEgg", new ItemStack(Items.spawn_egg, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("mobEgg", new ItemStack(ModItems.skeletonSpawner, 1, OreDictionary.WILDCARD_VALUE));

		if (GanysNether.enableSpawners) {
			OreDictionary.registerOre("blockSpawner", new ItemStack(Blocks.mob_spawner, 1, OreDictionary.WILDCARD_VALUE));
			OreDictionary.registerOre("blockSpawner", new ItemStack(ModBlocks.extendedSpawner, 1, OreDictionary.WILDCARD_VALUE));
		}

		if (GanysNether.enableQuartz) {
			OreDictionary.registerOre("blockQuartz", new ItemStack(ModBlocks.colouredQuartzBlock, 1, OreDictionary.WILDCARD_VALUE));

			OreDictionary.registerOre("blockQuartzChiselled", new ItemStack(ModBlocks.colouredChiselledQuartzBlock, 1, OreDictionary.WILDCARD_VALUE));
			OreDictionary.registerOre("blockQuartzChiselled", new ItemStack(Blocks.quartz_block, 1, 1));

			for (int i = 0; i < ModBlocks.colouredQuartzPillar.length; i++)
				OreDictionary.registerOre("blockQuartzPillar", new ItemStack(ModBlocks.colouredQuartzPillar[i], 1, OreDictionary.WILDCARD_VALUE));
			OreDictionary.registerOre("blockQuartzPillar", new ItemStack(Blocks.quartz_block, 1, 2));
		}

		if (GanysNether.enableSoulGlass) {
			OreDictionary.registerOre("blockGlass", new ItemStack(ModBlocks.soulGlass, 1, OreDictionary.WILDCARD_VALUE));
			OreDictionary.registerOre("blockGlassBrown", new ItemStack(ModBlocks.soulGlass, 1, OreDictionary.WILDCARD_VALUE));
			OreDictionary.registerOre("paneGlass", ModBlocks.soulGlassPane0);
			OreDictionary.registerOre("paneGlass", ModBlocks.soulGlassPane1);
			OreDictionary.registerOre("paneGlassBrown", ModBlocks.soulGlassPane0);
			OreDictionary.registerOre("paneGlassBrown", ModBlocks.soulGlassPane1);
		}

		OreDictionary.registerOre("egg", Items.egg);
		OreDictionary.registerOre("itemSkull", new ItemStack(Items.skull, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("skullSkeleton", new ItemStack(Items.skull, 1, 0));
		OreDictionary.registerOre("skullWitherSkeleton", new ItemStack(Items.skull, 1, 1));
		OreDictionary.registerOre("skullZombie", new ItemStack(Items.skull, 1, 2));
		OreDictionary.registerOre("skullPlayer", new ItemStack(Items.skull, 1, 3));
		OreDictionary.registerOre("skullCreeper", new ItemStack(Items.skull, 1, 4));
	}

	private static void addShapedRecipe(ItemStack output, Object... objects) {
		GameRegistry.addRecipe(new ShapedOreRecipe(output, objects));
	}

	private static void addShapelessRecipe(ItemStack output, Object... objects) {
		GameRegistry.addRecipe(new ShapelessOreRecipe(output, objects));
	}
}