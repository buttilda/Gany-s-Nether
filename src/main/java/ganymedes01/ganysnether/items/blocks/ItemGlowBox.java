package ganymedes01.ganysnether.items.blocks;

import ganymedes01.ganysnether.lib.Strings;
import net.minecraft.block.Block;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class ItemGlowBox extends ItemBlockBase {

	public ItemGlowBox(Block block) {
		super(block, Strings.Blocks.GLOW_BOX_NAME + "_item_");
	}
}