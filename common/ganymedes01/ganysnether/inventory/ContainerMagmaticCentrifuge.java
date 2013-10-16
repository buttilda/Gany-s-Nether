package ganymedes01.ganysnether.inventory;

import ganymedes01.ganysnether.tileentities.TileEntityMagmaticCentrifuge;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class ContainerMagmaticCentrifuge extends Container {

	private TileEntityMagmaticCentrifuge centrifuge;
	private float angle;
	private int posX, posY;

	public ContainerMagmaticCentrifuge(InventoryPlayer inventory, TileEntityMagmaticCentrifuge tile) {
		centrifuge = tile;
		angle = 0;
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

		posX = ((Slot) inventorySlots.get(0)).xDisplayPosition;
		posY = ((Slot) inventorySlots.get(0)).yDisplayPosition;
	}

	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		for (int i = 0; i < crafters.size(); i++)
			centrifuge.sendGUIData(this, (ICrafting) crafters.get(i));
	}

	@Override
	public void updateProgressBar(int i, int j) {
		centrifuge.getGUIData(i, j);

		((Slot) inventorySlots.get(0)).xDisplayPosition = (int) (posX * Math.cos(angle * (Math.PI / 180)) - posY * Math.sin(angle * (Math.PI / 180)));
		((Slot) inventorySlots.get(0)).yDisplayPosition = (int) (posX * Math.sin(angle * (Math.PI / 180)) + posY * Math.cos(angle * (Math.PI / 180)));
		angle++;
		if (angle >= 360)
			angle = 0;
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