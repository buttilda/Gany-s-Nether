package ganymedes01.ganysnether.tileentities;

import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.inventory.ContainerReproducer;
import ganymedes01.ganysnether.items.ModItems;
import ganymedes01.ganysnether.items.SkeletonSpawner;
import ganymedes01.ganysnether.lib.Strings;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class TileEntityReproducer extends TileEntity implements ISidedInventory {

	ItemStack[] inventory = new ItemStack[5];
	private final int BASE_SLOT = 0;
	private final int REPLACE_SLOT = 1;
	private final int RESULT_SLOT = 2;
	private final int BASE_DROP_SLOT = 3;
	private final int REPLACE_DROP_SLOT = 4;

	private int workTime;
	private final int MAX_WORK_TIME = 200;

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
		if (inventory[drop] == null)
			return false;
		else if (getMobDropFromEgg(inventory[egg]) != null)
			if (getMobDropFromEgg(inventory[egg]).getItem() == inventory[drop].getItem())
				if (getMobDropFromEgg(inventory[egg]).getItemDamage() == inventory[drop].getItemDamage())
					return true;
		return false;
	}

	private ItemStack getMobDropFromEgg(ItemStack egg) {
		if (egg.getItem() instanceof SkeletonSpawner)
			return egg.getItemDamage() == 0 ? new ItemStack(Item.bone) : egg.getItemDamage() == 1 ? new ItemStack(Item.coal, 1, 0) : null;
		else
			switch (egg.getItemDamage()) {
				case 50:
					return new ItemStack(Item.gunpowder);
				case 52:
				case 59:
					return new ItemStack(Item.spiderEye);
				case 54:
					return new ItemStack(Item.rottenFlesh);
				case 55:
					return new ItemStack(Item.slimeBall);
				case 56:
					return new ItemStack(Item.ghastTear);
				case 57:
					return new ItemStack(Item.goldNugget);
				case 58:
					return new ItemStack(Item.enderPearl);
				case 60:
					return new ItemStack(ModItems.silverfishScale);
				case 61:
					return new ItemStack(Item.blazeRod);
				case 62:
					return new ItemStack(Item.magmaCream);
				case 65:
					return new ItemStack(ModItems.batWing);
				case 66:
					return new ItemStack(Item.glowstone);
				case 90:
					return new ItemStack(Item.porkRaw);
				case 91:
					return new ItemStack(Block.cloth);
				case 92:
					return new ItemStack(Item.beefRaw);
				case 93:
					return new ItemStack(Item.chickenRaw);
				case 94:
					return new ItemStack(Item.dyePowder, 1, 0);
				case 95:
					return new ItemStack(ModItems.wolfTeeth);
				case 96:
					return new ItemStack(Block.mushroomRed);
				case 97:
					return new ItemStack(Item.snowball);
				case 98:
					return new ItemStack(Item.fishRaw);
				case 99:
					return new ItemStack(Item.ingotIron);
				case 100:
					return new ItemStack(Item.leather);
				case 101:
					return new ItemStack(Item.emerald);
				default:
					return null;
			}
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
		return side == 0 ? new int[] { 2 } : side == 1 ? new int[] { 0 } : new int[] { 1 };
	}

	@Override
	public int getSizeInventory() {
		return inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return inventory[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int size) {
		if (inventory[slot] != null) {
			ItemStack itemstack;
			if (inventory[slot].stackSize <= size) {
				itemstack = inventory[slot];
				inventory[slot] = null;
				return itemstack;
			} else {
				itemstack = inventory[slot].splitStack(size);
				if (inventory[slot].stackSize == 0)
					inventory[slot] = null;
				return itemstack;
			}
		} else
			return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		if (inventory[slot] != null) {
			ItemStack itemstack = inventory[slot];
			inventory[slot] = null;
			return itemstack;
		} else
			return null;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		inventory[slot] = stack;

		if (stack != null && stack.stackSize > getInventoryStackLimit())
			stack.stackSize = getInventoryStackLimit();
	}

	@Override
	public String getInvName() {
		return Utils.getConainerName(Strings.REPRODUCER_NAME);
	}

	@Override
	public boolean isInvNameLocalized() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return true;
	}

	@Override
	public void openChest() {
	}

	@Override
	public void closeChest() {
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return slot == 2 ? false : stack != null && stack.getItem() instanceof ItemMonsterPlacer;
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack stack, int side) {
		return isItemValidForSlot(slot, stack);
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack stack, int side) {
		return true;
	}

	@Override
	public void readFromNBT(NBTTagCompound data) {
		super.readFromNBT(data);
		NBTTagList nbttaglist = data.getTagList("Items");
		inventory = new ItemStack[getSizeInventory()];

		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbttaglist.tagAt(i);
			byte b0 = nbttagcompound1.getByte("Slot");

			if (b0 >= 0 && b0 < inventory.length)
				inventory[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
		}
		workTime = data.getInteger("workTime");
	}

	@Override
	public void writeToNBT(NBTTagCompound data) {
		super.writeToNBT(data);
		NBTTagList nbttaglist = new NBTTagList();
		for (int i = 0; i < inventory.length; ++i)
			if (inventory[i] != null) {
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				inventory[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}

		data.setTag("Items", nbttaglist);
		data.setInteger("workTime", workTime);
	}
}
