package ganymedes01.ganysnether.tileentities;

import ganymedes01.ganysnether.inventory.ContainerReproducer;
import ganymedes01.ganysnether.lib.Strings;
import ganymedes01.ganysnether.recipes.ReproducerRecipes;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class TileEntityReproducer extends GanysInventory implements ISidedInventory {

	private final int BASE_SLOT = 0;
	private final int REPLACE_SLOT = 1;
	private final int RESULT_SLOT = 2;
	private final int BASE_DROP_SLOT = 3;
	private final int REPLACE_DROP_SLOT = 4;

	private int workTime;
	private final int MAX_WORK_TIME = 200;

	public TileEntityReproducer() {
		super(5, Strings.Blocks.REPRODUCER_NAME);
	}

	@Override
	public void updateEntity() {
		if (worldObj.isRemote)
			return;
		if (!worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord))
			if (inventory[BASE_SLOT] != null && inventory[REPLACE_SLOT] != null) {
				if (inventory[BASE_SLOT].getItemDamage() == inventory[REPLACE_SLOT].getItemDamage()) {
					workTime = MAX_WORK_TIME;
					return;
				}
				if (hasCorrespondentDrop(BASE_SLOT, BASE_DROP_SLOT) && hasCorrespondentDrop(REPLACE_SLOT, REPLACE_DROP_SLOT)) {
					if (workTime > 0)
						workTime--;
					if (workTime <= 0) {
						if (inventory[RESULT_SLOT] == null)
							inventory[RESULT_SLOT] = new ItemStack(inventory[BASE_SLOT].getItem(), 2, inventory[BASE_SLOT].getItemDamage());
						else if (inventory[RESULT_SLOT].getItem() == inventory[BASE_SLOT].getItem() && inventory[RESULT_SLOT].getItemDamage() == inventory[BASE_SLOT].getItemDamage())
							if (inventory[RESULT_SLOT].stackSize < inventory[RESULT_SLOT].getMaxStackSize())
								inventory[RESULT_SLOT].stackSize += 2;
							else
								return;
						else
							return;
						inventory[BASE_SLOT].stackSize--;
						inventory[REPLACE_SLOT].stackSize--;
						inventory[BASE_DROP_SLOT].stackSize--;
						inventory[REPLACE_DROP_SLOT].stackSize--;

						if (inventory[BASE_SLOT].stackSize <= 0)
							inventory[BASE_SLOT] = null;
						if (inventory[REPLACE_SLOT].stackSize <= 0)
							inventory[REPLACE_SLOT] = null;

						if (inventory[BASE_DROP_SLOT].stackSize <= 0)
							inventory[BASE_DROP_SLOT] = null;
						if (inventory[REPLACE_DROP_SLOT].stackSize <= 0)
							inventory[REPLACE_DROP_SLOT] = null;
						workTime = MAX_WORK_TIME;
					}
				} else
					workTime = MAX_WORK_TIME;
			} else
				workTime = MAX_WORK_TIME;
	}

	private boolean hasCorrespondentDrop(int egg, int drop) {
		ItemStack mobDrop = ReproducerRecipes.getMobDropFromEgg(inventory[egg]);
		if (inventory[drop] == null || mobDrop == null)
			return false;
		else if (mobDrop.getItem() == inventory[drop].getItem())
			if (mobDrop.getItemDamage() == inventory[drop].getItemDamage())
				return true;
		return false;
	}

	public int getScaledWorkTime(int scale) {
		if (workTime == 0)
			return 0;
		return (int) (scale * ((float) workTime / (float) MAX_WORK_TIME));
	}

	public void getGUIData(int id, int value) {
		if (id == 0)
			workTime = value;
	}

	public void sendGUIData(ContainerReproducer reproducer, ICrafting craft) {
		craft.sendProgressBarUpdate(reproducer, 0, workTime);
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		return side == 0 ? new int[] { RESULT_SLOT } : side == 1 ? new int[] { BASE_SLOT, REPLACE_SLOT } : new int[] { BASE_DROP_SLOT, REPLACE_DROP_SLOT };
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return slot == RESULT_SLOT ? false : slot == BASE_SLOT || slot == REPLACE_SLOT ? ReproducerRecipes.isValidSpawnEgg(stack) : true;
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack stack, int side) {
		return isItemValidForSlot(slot, stack);
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack stack, int side) {
		return slot == RESULT_SLOT;
	}

	@Override
	public void readFromNBT(NBTTagCompound data) {
		super.readFromNBT(data);
		workTime = data.getInteger("workTime");
	}

	@Override
	public void writeToNBT(NBTTagCompound data) {
		super.writeToNBT(data);
		data.setInteger("workTime", workTime);
	}
}