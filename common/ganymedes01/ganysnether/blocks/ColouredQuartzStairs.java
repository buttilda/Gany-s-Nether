package ganymedes01.ganysnether.blocks;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.lib.ModIDs;
import net.minecraft.block.BlockStairs;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class ColouredQuartzStairs extends BlockStairs {

	ColouredQuartzStairs(int meta) {
		super(ModIDs.COLOURED_QUARTZ_STAIRS_IDS[meta], ModBlocks.colouredQuartzBlock, meta);
		setHardness(0.8F);
		setLightOpacity(0);
		setStepSound(soundStoneFootstep);
		setCreativeTab(GanysNether.netherTab);
	}
}
