package ganymedes01.ganysnether.inventory;

import ganymedes01.ganysnether.inventory.slots.BetterSlot;
import ganymedes01.ganysnether.tileentities.TileEntityUndertaker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Gany's Nether
 *
 * @author ganymedes01
 *
 */

public class ContainerUndertaker extends Container {

	private final TileEntityUndertaker tile;

	public ContainerUndertaker(InventoryPlayer inventory, TileEntityUndertaker tile) {
		this.tile = tile;
		tile.openInventory();

		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 9; j++)
				addSlotToContainer(new BetterSlot(tile, j + i * 9 + 9, 8 + j * 18, 34 + i * 18));
		for (int i = 0; i < 9; i++)
			addSlotToContainer(new BetterSlot(tile, i, 8 + i * 18, 92));
		for (int i = 0; i < 4; i++) {
			final int index = i;
			addSlotToContainer(new BetterSlot(tile, i + 36, 8 + i * 18, 16) {
				@Override
				@SideOnly(Side.CLIENT)
				public IIcon getBackgroundIconIndex() {
					return ItemArmor.func_94602_b(index);
				}
			});
		}

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
		tile.closeInventory();
	}
}