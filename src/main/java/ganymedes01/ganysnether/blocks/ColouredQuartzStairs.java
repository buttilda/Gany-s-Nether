package ganymedes01.ganysnether.blocks;

import ganymedes01.ganysnether.GanysNether;
import net.minecraft.block.BlockStairs;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class ColouredQuartzStairs extends BlockStairs {

	ColouredQuartzStairs(int meta) {
		super(ModBlocks.colouredQuartzBlock, meta);
		setHardness(0.8F);
		setLightOpacity(0);
		setStepSound(soundTypeStone);
		setCreativeTab(GanysNether.netherTab);
	}
}