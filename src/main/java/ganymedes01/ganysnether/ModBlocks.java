package ganymedes01.ganysnether;

import ganymedes01.ganysnether.blocks.BlazeBlock;
import ganymedes01.ganysnether.blocks.BlazingCactoid;
import ganymedes01.ganysnether.blocks.ColouredChiselledQuartzBlock;
import ganymedes01.ganysnether.blocks.ColouredQuartzBlock;
import ganymedes01.ganysnether.blocks.ColouredQuartzPillar;
import ganymedes01.ganysnether.blocks.ColouredQuartzStairs;
import ganymedes01.ganysnether.blocks.DenseLavaCell;
import ganymedes01.ganysnether.blocks.ExtendedSpawner;
import ganymedes01.ganysnether.blocks.FocusedLavaCell;
import ganymedes01.ganysnether.blocks.GlowBox;
import ganymedes01.ganysnether.blocks.GlowingReedCrop;
import ganymedes01.ganysnether.blocks.HellBush;
import ganymedes01.ganysnether.blocks.HorseArmourStand;
import ganymedes01.ganysnether.blocks.MagmaticCentrifuge;
import ganymedes01.ganysnether.blocks.QuarzBerryBush;
import ganymedes01.ganysnether.blocks.Reproducer;
import ganymedes01.ganysnether.blocks.SoulChest;
import ganymedes01.ganysnether.blocks.SoulGlass;
import ganymedes01.ganysnether.blocks.SoulGlassPane;
import ganymedes01.ganysnether.blocks.SoulGlassStairs;
import ganymedes01.ganysnether.blocks.SoulTNT;
import ganymedes01.ganysnether.blocks.SpectreWheatCrop;
import ganymedes01.ganysnether.blocks.ThermalSmelter;
import ganymedes01.ganysnether.blocks.TilledNetherrack;
import ganymedes01.ganysnether.blocks.Undertaker;
import ganymedes01.ganysnether.blocks.VolcanicFurnace;
import ganymedes01.ganysnether.blocks.WeepingPod;
import ganymedes01.ganysnether.blocks.WitherShrub;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.Strings;

import java.lang.reflect.Field;

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
	public static final Block volcanicFurnace = new VolcanicFurnace();
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
	public static final Block soulGlassPane0 = new SoulGlassPane(0);
	public static final Block soulGlassPane1 = new SoulGlassPane(1);
	public static final Block blazeBlock = new BlazeBlock();

	static {
		for (int i = 0; i < colouredQuartzBlockStairs.length; i++)
			colouredQuartzBlockStairs[i] = new ColouredQuartzStairs(i).setBlockName(Utils.getUnlocalisedName(Strings.Blocks.COLOURED_QUARTZ_STAIRS_NAMES[i]));
		for (int i = 0; i < colouredQuartzPillar.length; i++)
			colouredQuartzPillar[i] = new ColouredQuartzPillar(i).setBlockName(Utils.getUnlocalisedName(Strings.Blocks.COLOURED_QUARTZ_PILLARS_NAME) + i);
	}

	public static void init() {
		try {
			for (Field f : ModBlocks.class.getDeclaredFields()) {
				Object obj = f.get(null);
				if (obj instanceof Block)
					registerBlock((Block) obj);
				else if (obj instanceof Block[])
					for (Block block : (Block[]) obj)
						if (block != null)
							registerBlock(block);
			}
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	private static void registerBlock(Block block) {
		if (!(block instanceof IConfigurable) || ((IConfigurable) block).isEnabled()) {
			String name = block.getUnlocalizedName();
			String[] strings = name.split("\\.");

			if (block instanceof ISubBlocksBlock)
				GameRegistry.registerBlock(block, ((ISubBlocksBlock) block).getItemBlockClass(), strings[strings.length - 1]);
			else
				GameRegistry.registerBlock(block, strings[strings.length - 1]);
		}
	}

	public static interface ISubBlocksBlock {

		Class<? extends ItemBlock> getItemBlockClass();
	}
}