package ganymedes01.ganysnether.inventory;

import ganymedes01.ganysnether.tileentities.TileEntityVolcanicFurnace;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class VolcanicFurnaceSlot extends Slot {

	public VolcanicFurnaceSlot(IInventory inventory, int slot, int posX, int posY) {
		super(inventory, slot, posX, posY);
	}

	@Override
	public boolean isItemValid(ItemStack stack) {
		return TileEntityVolcanicFurnace.itemIsFuel(stack);
	}
}
