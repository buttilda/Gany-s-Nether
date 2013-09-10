package ganymedes01.ganysnether.inventory;

import ganymedes01.ganysnether.tileentities.TileEntityReproducer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;

public class ContainerReproducer extends Container {

	TileEntityReproducer reproducer;

	public ContainerReproducer(InventoryPlayer inventory, TileEntityReproducer tile) {
		reproducer = tile;
		addSlotToContainer(new MonsterPlacerSlot(tile, 0, 36, 33));
		addSlotToContainer(new MonsterPlacerSlot(tile, 1, 72, 33));
		addSlotToContainer(new InvalidSlot(tile, 2, 130, 33));
		addSlotToContainer(new Slot(tile, 3, 54, 15));
		addSlotToContainer(new Slot(tile, 4, 54, 51));

		for (int i = 0; i < 3; ++i)
			for (int j = 0; j < 9; ++j)
				addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));

		for (int i = 0; i < 9; ++i)
			addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 142));
	}

	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		for (int i = 0; i < crafters.size(); i++)
			reproducer.sendGUIData(this, (ICrafting) crafters.get(i));
	}

	@Override
	public void updateProgressBar(int i, int j) {
		reproducer.getGUIData(i, j);
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex) {
		ItemStack itemStack = null;
		Slot slot = (Slot) inventorySlots.get(slotIndex);

		if (slot != null && slot.getHasStack()) {
			ItemStack slotItemStack = slot.getStack();
			itemStack = slotItemStack.copy();

			if (slotIndex < 5) {
				if (!mergeItemStack(slotItemStack, 5, inventorySlots.size(), true))
					return null;
			} else if (slotItemStack.getItem() instanceof ItemMonsterPlacer) {
				if (!mergeItemStack(slotItemStack, 0, 2, false))
					return null;
			} else if (!mergeItemStack(slotItemStack, 3, 5, false))
				return null;

			if (slotItemStack.stackSize == 0)
				slot.putStack((ItemStack) null);
			else
				slot.onSlotChanged();
		}

		return itemStack;
	}
}
