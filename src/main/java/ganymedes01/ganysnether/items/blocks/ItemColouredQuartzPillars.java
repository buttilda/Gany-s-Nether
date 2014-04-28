package ganymedes01.ganysnether.items.blocks;

import ganymedes01.ganysnether.lib.Strings;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class ItemColouredQuartzPillars extends ItemBlockBase {

	public ItemColouredQuartzPillars(Block block) {
		super(block, Strings.Blocks.COLOURED_QUARTZ_PILLARS_NAME + "item_");
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return getUnlocalizedName() + stack.getItemDamage();
	}
}