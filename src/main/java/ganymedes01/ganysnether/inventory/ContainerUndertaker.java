package ganymedes01.ganysnether.inventory;

import ganymedes01.ganysnether.inventory.slots.InvalidArmourSlot;
import ganymedes01.ganysnether.inventory.slots.InvalidSlot;
import ganymedes01.ganysnether.tileentities.TileEntityUndertaker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class ContainerUndertaker extends Container {

	private TileEntityUndertaker undertaker;

	public ContainerUndertaker(InventoryPlayer inventory, TileEntityUndertaker tileUndertaker) {
		undertaker = tileUndertaker;
		tileUndertaker.openChest();

		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 9; j++)
				addSlotToContainer(new InvalidSlot(tileUndertaker, j + i * 9 + 9, 8 + j * 18, 34 + i * 18));
		for (int i = 0; i < 9; i++)
			addSlotToContainer(new InvalidSlot(tileUndertaker, i, 8 + i * 18, 92));
		for (int i = 0; i < 4; i++)
			addSlotToContainer(new InvalidArmourSlot(tileUndertaker, i + 36, 8 + i * 18, 16, i));

		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 9; j++)
				addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 123 + i * 18));
		for (int i = 0; i < 9; i++)
			addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 181));
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex) {
		ItemStack itemStack = null;
		Slot slot = (Slot) inventorySlots.get(slotIndex);

		if (slot != null && slot.getHasStack()) {
			ItemStack slotItemStack = slot.getStack();
			itemStack = slotItemStack.copy();

			if (slotIndex < 40) {
				if (!mergeItemStack(slotItemStack, 40, inventorySlots.size(), true))
					return null;
			} else
				return null;

			if (slotItemStack.stackSize == 0)
				slot.putStack((ItemStack) null);
			else
				slot.onSlotChanged();
		}
		return itemStack;
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}

	@Override
	public void onContainerClosed(EntityPlayer player) {
		super.onContainerClosed(player);
		undertaker.closeChest();
	}
}