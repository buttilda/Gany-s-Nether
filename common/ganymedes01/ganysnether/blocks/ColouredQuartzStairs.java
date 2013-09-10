package ganymedes01.ganysnether.blocks;

import ganymedes01.ganysnether.GanysNether;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class ColouredQuartzStairs extends BlockStairs {

	protected ColouredQuartzStairs(int id, Block block, int meta) {
		super(id, block, meta);
		setHardness(0.8F);
		setLightOpacity(0);
		setStepSound(soundStoneFootstep);
		setCreativeTab(GanysNether.netherTab);
	}
}
