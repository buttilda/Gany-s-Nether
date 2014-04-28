package ganymedes01.ganysnether.items.blocks;

import ganymedes01.ganysnether.lib.Strings;
import net.minecraft.item.ItemStack;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class ItemSoulGlass extends ItemBlockBase {

	public ItemSoulGlass(int id) {
		super(id, Strings.Blocks.SOUL_GLASS_NAME + "_item_");
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		if (stack.getItemDamage() == 0)
			return getUnlocalizedName();
		else
			return getUnlocalizedName() + "Brick";
	}
}