package ganymedes01.ganysnether.inventory;

import ganymedes01.ganysnether.inventory.slots.BetterSlot;
import ganymedes01.ganysnether.tileentities.TileEntityMagmaticCentrifuge;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;

/**
 * Gany's Nether
 *
 * @author ganymedes01
 *
 */

public class ContainerMagmaticCentrifuge extends Container {

	private final TileEntityMagmaticCentrifuge centrifuge;
	private final int centerX = 80, centerY = 71;
	private final int posX1, posY1, posX2, posY2;

	public ContainerMagmaticCentrifuge(InventoryPlayer inventory, TileEntityMagmaticCentrifuge tile) {
		centrifuge = tile;
		addSlotToContainer(new BetterSlot(tile, 0, 153, 31));
		addSlotToContainer(new BetterSlot(tile, 1, 153, 111));

		addSlotToContainer(new Slot(tile, 2, 30, 69));
		addSlotToContainer(new Slot(tile, 3, 128, 69));

		addSlotToContainer(new BetterSlot(tile, 4, 71, 62));
		addSlotToContainer(new BetterSlot(tile, 5, 71 + 18, 62));
		addSlotToContainer(new BetterSlot(tile, 6, 71, 62 + 18));
		addSlotToContainer(new BetterSlot(tile, 7, 71 + 18, 62 + 18));

		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 9; j++)
				addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 156 + i * 18));

		for (int i = 0; i < 9; i++)
			addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 214));

		posX1 = ((Slot) inventorySlots.get(2)).xDisplayPosition;
		posY1 = ((Slot) inventorySlots.get(2)).yDisplayPosition;
		posX2 = ((Slot) inventorySlots.get(3)).xDisplayPosition;
		posY2 = ((Slot) inventorySlots.get(3)).yDisplayPosition;
	}

	public TileEntityMagmaticCentrifuge getCentrifuge() {
		return centrifuge;
	}

	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		for (int i = 0; i < crafters.size(); i++)
			centrifuge.sendGUIData(this, (ICrafting) crafters.get(i));
	}

	@Override
	public void updateProgressBar(int id, int value) {
		centrifuge.getGUIData(id, value);

		float angle = centrifuge.getAngle();

		((Slot) inventorySlots.get(2)).xDisplayPosition = (int) (centerX + (posX1 - centerX) * Math.cos(angle) - (posY1 - centerY) * Math.sin(angle));
		((Slot) inventorySlots.get(2)).yDisplayPosition = (int) (centerY + (posX1 - centerX) * Math.sin(angle) + (posY1 - centerY) * Math.cos(angle));

		((Slot) inventorySlots.get(3)).xDisplayPosition = (int) (centerX + (posX2 - centerX) * Math.cos(angle) - (posY2 - centerY) * Math.sin(angle));
		((Slot) inventorySlots.get(3)).yDisplayPosition = (int) (centerY + (posX2 - centerX) * Math.sin(angle) + (posY2 - centerY) * Math.cos(angle));
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

			if (slotIndex < centrifuge.getSizeInventory()) {
				if (!mergeItemStack(slotItemStack, centrifuge.getSizeInventory(), inventorySlots.size(), true))
					return null;
			} else if (FluidContainerRegistry.isFilledContainer(slotItemStack)) {
				if (!mergeItemStack(slotItemStack, 0, 1, false))
					return null;
			} else if (!mergeItemStack(slotItemStack, 2, 4, false))
				return null;

			if (slotItemStack.stackSize == 0)
				slot.putStack((ItemStack) null);
			else
				slot.onSlotChanged();
		}

		return itemStack;
	}
}