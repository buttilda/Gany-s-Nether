package ganymedes01.ganysnether.blocks;

import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.items.blocks.ItemColouredChiselledQuartzBlock;
import ganymedes01.ganysnether.items.blocks.ItemColouredQuartzBlock;
import ganymedes01.ganysnether.items.blocks.ItemColouredQuartzPillars;
import ganymedes01.ganysnether.items.blocks.ItemGlowBox;
import ganymedes01.ganysnether.items.blocks.ItemHorseArmourStand;
import ganymedes01.ganysnether.items.blocks.ItemSoulGlass;
import ganymedes01.ganysnether.lib.Strings;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class ModBlocks {

	public static final Block tilledNetherrack = new TilledNetherrack();
	public static final Block quarzBerryBush = new QuarzBerryBush();
	public static final Block spectreWheat = new SpectreWheatCrop();
	public static final Block glowingReed = new GlowingReedCrop();
	public static final Block soulGlass = new SoulGlass();
	public static final Block soulChest = new SoulChest();
	public static final Block volcanicFurnaceIdle = new VolcanicFurnace();
	public static final Block denseLavaCell = new DenseLavaCell();
	public static final Block glowBox = new GlowBox();
	public static final Block colouredQuartzBlock = new ColouredQuartzBlock();
	public static final Block colouredChiselledQuartzBlock = new ColouredChiselledQuartzBlock();
	public static final Block[] colouredQuartzBlockStairs = new Block[16];
	public static final Block soulGlassStairs = new SoulGlassStairs();
	public static final Block[] colouredQuartzPillar = new Block[4];
	public static final Block reproducer = new Reproducer();
	public static final Block undertaker = new Undertaker();
	public static final Block witherShrub = new WitherShrub();
	public static final Block magmaticCentrifuge = new MagmaticCentrifuge();
	public static final Block weepingPod = new WeepingPod();
	public static final Block soulTNT = new SoulTNT();
	public static final Block blazingCactoid = new BlazingCactoid();
	public static final Block hellBush = new HellBush();
	public static final Block thermalSmelter = new ThermalSmelter();
	public static final Block horseArmourStand = new HorseArmourStand();
	public static final Block extendedSpawner = new ExtendedSpawner();
	public static final Block focusedLavaCell = new FocusedLavaCell();

	static {
		for (int i = 0; i < colouredQuartzBlockStairs.length; i++)
			colouredQuartzBlockStairs[i] = new ColouredQuartzStairs(i).setBlockName(Utils.getUnlocalizedName(Strings.Blocks.COLOURED_QUARTZ_STAIRS_NAMES[i]));
		for (int i = 0; i < colouredQuartzPillar.length; i++)
			colouredQuartzPillar[i] = new ColouredQuartzPillar(i).setBlockName(Utils.getUnlocalizedName(Strings.Blocks.COLOURED_QUARTZ_PILLARS_NAME) + i);
	}

	public static void init() {
		registerBlock(tilledNetherrack);
		registerBlock(quarzBerryBush);
		registerBlock(spectreWheat);
		registerBlock(glowingReed);
		registerBlock(soulGlass, ItemSoulGlass.class);
		registerBlock(soulGlassStairs);
		registerBlock(soulChest);
		registerBlock(volcanicFurnaceIdle);
		registerBlock(denseLavaCell);
		registerBlock(glowBox, ItemGlowBox.class);
		registerBlock(colouredQuartzBlock, ItemColouredQuartzBlock.class);
		registerBlock(colouredChiselledQuartzBlock, ItemColouredChiselledQuartzBlock.class);
		for (int i = 0; i < Strings.COLOURS.length; i++)
			registerBlock(colouredQuartzBlockStairs[i]);
		for (int i = 0; i < colouredQuartzPillar.length; i++)
			registerBlock(colouredQuartzPillar[i], ItemColouredQuartzPillars.class);
		registerBlock(reproducer);
		registerBlock(undertaker);
		registerBlock(witherShrub);
		registerBlock(magmaticCentrifuge);
		registerBlock(weepingPod);
		registerBlock(soulTNT);
		registerBlock(blazingCactoid);
		registerBlock(hellBush);
		registerBlock(thermalSmelter);
		registerBlock(horseArmourStand, ItemHorseArmourStand.class);
		registerBlock(extendedSpawner);
		registerBlock(focusedLavaCell);
	}

	private static void registerBlock(Block block) {
		String name = block.getUnlocalizedName();
		String[] strings = name.split("\\.");
		GameRegistry.registerBlock(block, strings[strings.length - 1]);
	}

	private static void registerBlock(Block block, Class<? extends ItemBlock> item) {
		String name = block.getUnlocalizedName();
		String[] strings = name.split("\\.");
		GameRegistry.registerBlock(block, item, strings[strings.length - 1]);
	}
}