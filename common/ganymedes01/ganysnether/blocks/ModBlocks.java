package ganymedes01.ganysnether.blocks;

import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.items.blocks.ItemColouredChiselledQuartzBlock;
import ganymedes01.ganysnether.items.blocks.ItemColouredQuartzBlock;
import ganymedes01.ganysnether.items.blocks.ItemColouredQuartzPillars;
import ganymedes01.ganysnether.items.blocks.ItemGlowBox;
import ganymedes01.ganysnether.items.blocks.ItemSoulGlass;
import ganymedes01.ganysnether.lib.ModIDs;
import ganymedes01.ganysnether.lib.Strings;
import net.minecraft.block.Block;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class ModBlocks {

	public static Block tilledNetherrack;
	public static Block quarzBerryBush;
	public static Block spectreWheat;
	public static Block glowingReed;
	public static Block soulGlass;
	public static Block soulChest;
	public static Block volcanicFurnaceIdle;
	public static Block volcanicFurnaceActive;
	public static Block denseLavaCell;
	public static Block glowBox;
	public static Block colouredQuartzBlock;
	public static Block colouredChiselledQuartzBlock;
	public static Block[] colouredQuartzBlockStairs = new Block[16];
	public static Block soulGlassStairs;
	public static Block[] colouredQuartzPillar = new Block[4];
	public static Block reproducer;
	public static Block undertaker;
	public static Block witherShrub;

	public static void init() {
		tilledNetherrack = new TilledNetherrack();
		quarzBerryBush = new QuarzBerryBush();
		spectreWheat = new SpectreWheatCrop();
		glowingReed = new GlowingReedCrop();
		soulGlass = new SoulGlass();
		soulChest = new SoulChest();
		volcanicFurnaceIdle = new VolcanicFurnace(false);
		volcanicFurnaceActive = new VolcanicFurnace(true);
		denseLavaCell = new DenseLavaCell();
		glowBox = new GlowBox();
		colouredQuartzBlock = new ColouredQuartzBlock();
		colouredChiselledQuartzBlock = new ColouredChiselledQuartzBlock();
		for (int i = 0; i < colouredQuartzBlockStairs.length; i++)
			colouredQuartzBlockStairs[i] = new ColouredQuartzStairs(i).setUnlocalizedName(Utils.getUnlocalizedName(Strings.COLOURED_QUARTZ_STAIRS_NAMES[i]));
		soulGlassStairs = new SoulGlassStairs(ModIDs.SOUL_GLASS_STAIRS_ID);
		for (int i = 0; i < colouredQuartzPillar.length; i++)
			colouredQuartzPillar[i] = new ColouredQuartzPillar(i).setUnlocalizedName(Utils.getUnlocalizedName(Strings.COLOURED_QUARTZ_PILLARS_NAME) + i);
		reproducer = new Reproducer();
		undertaker = new Undertaker();
		witherShrub = new WitherShrub();

		registerNames();
	}

	private static void registerNames() {
		GameRegistry.registerBlock(tilledNetherrack, Strings.TILLED_NETHERRACK_NAME);
		GameRegistry.registerBlock(quarzBerryBush, Strings.QUARZ_BERRY_BUSH_NAME);
		GameRegistry.registerBlock(spectreWheat, Strings.SPECTRE_WHEAT_BLOCK_NAME);
		GameRegistry.registerBlock(glowingReed, Strings.GLOWING_REED_BLOCK_NAME);
		GameRegistry.registerBlock(soulGlass, ItemSoulGlass.class, Strings.SOUL_GLASS_NAME);
		GameRegistry.registerBlock(soulGlassStairs, Strings.SOUL_GLASS_STAIRS_NAME);
		GameRegistry.registerBlock(soulChest, Strings.SOUL_CHEST_NAME);
		GameRegistry.registerBlock(volcanicFurnaceIdle, Strings.VOLCANIC_FURNACE_NAME);
		GameRegistry.registerBlock(volcanicFurnaceActive, Strings.VOLCANIC_FURNACE_NAME + "_on");
		GameRegistry.registerBlock(denseLavaCell, Strings.DENSE_LAVA_CELL_NAME);
		GameRegistry.registerBlock(glowBox, ItemGlowBox.class, Strings.GLOW_BOX_NAME);
		GameRegistry.registerBlock(colouredQuartzBlock, ItemColouredQuartzBlock.class, Strings.COLOURED_QUARTZ_BLOCK_NAME);
		GameRegistry.registerBlock(colouredChiselledQuartzBlock, ItemColouredChiselledQuartzBlock.class, Strings.COLOURED_CHISELLED_QUARTZ_BLOCK_NAME);
		for (int i = 0; i < Strings.COLOURS.length; i++)
			GameRegistry.registerBlock(colouredQuartzBlockStairs[i], Strings.COLOURED_QUARTZ_STAIRS_NAMES[i]);
		for (int i = 0; i < colouredQuartzPillar.length; i++)
			GameRegistry.registerBlock(colouredQuartzPillar[i], ItemColouredQuartzPillars.class, Strings.COLOURED_QUARTZ_PILLARS_NAME + i);
		GameRegistry.registerBlock(reproducer, Strings.REPRODUCER_NAME);
		GameRegistry.registerBlock(undertaker, Strings.UNDERTAKER_NAME);
		GameRegistry.registerBlock(witherShrub, Strings.WITHER_SHRUB_NAME);
	}
}
