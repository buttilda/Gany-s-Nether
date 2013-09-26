package ganymedes01.ganysnether.tileentities;

import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.Strings;
import net.minecraft.item.ItemStack;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class TileEntityUndertaker extends TileEntitySoulChest {

	public TileEntityUndertaker() {
		super(40);
	}

	@Override
	public String getInvName() {
		return Utils.getConainerName(Strings.UNDERTAKER_NAME);
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return false;
	}
}