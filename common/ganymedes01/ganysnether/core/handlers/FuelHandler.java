package ganymedes01.ganysnether.core.handlers;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.IFuelHandler;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class FuelHandler implements IFuelHandler {

	@Override
	public int getBurnTime(ItemStack fuel) {
		if (fuel.itemID == Block.fire.blockID)
			return 4000;
		return 0;
	}
}
