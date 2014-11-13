package ganymedes01.ganysnether.blocks;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.ModBlocks;
import net.minecraft.block.BlockStairs;

/**
 * Gany's Nether
 *
 * @author ganymedes01
 *
 */

public class ColouredQuartzStairs extends BlockStairs {

	public ColouredQuartzStairs(int meta) {
		super(ModBlocks.colouredQuartzBlock, meta);
		setHardness(0.8F);
		setLightOpacity(0);
		setStepSound(soundTypeStone);
		setCreativeTab(GanysNether.enableQuartz ? GanysNether.netherTab : null);
	}
}