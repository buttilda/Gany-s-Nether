package ganymedes01.ganysnether.items;

import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.Strings;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class ItemColouredChiselledQuartzBlock extends ItemBlock {

	public ItemColouredChiselledQuartzBlock(int id) {
		super(id);
		setHasSubtypes(true);
		setUnlocalizedName(Utils.getUnlocalizedName(Strings.COLOURED_CHISELLED_QUARTZ_BLOCK_NAME + "_item_"));
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return getUnlocalizedName() + Strings.COLOURS[stack.getItemDamage()];
	}

	@Override
	public int getMetadata(int meta) {
		return meta;
	}
}
