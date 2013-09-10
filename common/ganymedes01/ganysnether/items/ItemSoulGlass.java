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

public class ItemSoulGlass extends ItemBlock {

	public ItemSoulGlass(int id) {
		super(id);
		setHasSubtypes(true);
		setUnlocalizedName(Utils.getUnlocalizedName(Strings.SOUL_GLASS_NAME + "_item_"));
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		if (stack.getItemDamage() == 0)
			return getUnlocalizedName();
		else
			return getUnlocalizedName() + "Brick";
	}

	@Override
	public int getMetadata(int meta) {
		return meta;
	}
}
