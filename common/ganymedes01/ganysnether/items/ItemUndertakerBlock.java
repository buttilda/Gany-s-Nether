package ganymedes01.ganysnether.items;

import ganymedes01.ganysnether.lib.Strings;
import net.minecraft.item.ItemStack;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class ItemUndertakerBlock extends ItemBlockBase {

	public ItemUndertakerBlock(int id) {
		super(id, Strings.UNDERTAKER_NAME + "_item_");
		setMaxStackSize(1);
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return getUnlocalizedName();
	}
}