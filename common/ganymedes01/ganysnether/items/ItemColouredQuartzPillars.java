package ganymedes01.ganysnether.items;

import ganymedes01.ganysnether.lib.Strings;
import net.minecraft.item.ItemStack;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class ItemColouredQuartzPillars extends ItemBlockBase {

	public ItemColouredQuartzPillars(int id) {
		super(id, Strings.COLOURED_QUARTZ_PILLARS_NAME + "item_");
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return getUnlocalizedName() + stack.getItemDamage();
	}
}
