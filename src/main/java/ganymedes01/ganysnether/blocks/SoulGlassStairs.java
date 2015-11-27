package ganymedes01.ganysnether.blocks;

import java.util.Random;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.IConfigurable;
import ganymedes01.ganysnether.ModBlocks;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.Strings;
import net.minecraft.block.BlockStairs;

/**
 * Gany's Nether
 *
 * @author ganymedes01
 *
 */

public class SoulGlassStairs extends BlockStairs implements IConfigurable {

	public SoulGlassStairs() {
		super(ModBlocks.soulGlass, 1);
		setHardness(0.3F);
		setLightOpacity(3);
		setStepSound(soundTypeGlass);
		setCreativeTab(GanysNether.enableSoulGlass ? GanysNether.netherTab : null);
		setBlockName(Utils.getUnlocalisedName(Strings.Blocks.SOUL_GLASS_STAIRS_NAME));
	}

	@Override
	protected boolean canSilkHarvest() {
		return true;
	}

	@Override
	public int quantityDropped(Random rand) {
		int quantity = rand.nextInt(10);
		if (quantity == 5)
			return 1;
		return 0;
	}

	@Override
	public boolean isEnabled() {
		return GanysNether.enableSoulGlass;
	}
}