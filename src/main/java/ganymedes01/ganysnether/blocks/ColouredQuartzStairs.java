package ganymedes01.ganysnether.blocks;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.IConfigurable;
import ganymedes01.ganysnether.ModBlocks;
import net.minecraft.block.BlockStairs;

/**
 * Gany's Nether
 *
 * @author ganymedes01
 *
 */

public class ColouredQuartzStairs extends BlockStairs implements IConfigurable {

	public ColouredQuartzStairs(int meta) {
		super(ModBlocks.colouredQuartzBlock, meta);
		setHardness(0.8F);
		setLightOpacity(0);
		setStepSound(soundTypeStone);
		setCreativeTab(GanysNether.enableQuartz ? GanysNether.netherTab : null);
	}

	@Override
	public boolean isEnabled() {
		return GanysNether.enableQuartz;
	}
}