package ganymedes01.ganysnether.tileentities;

import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.Strings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
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

public class TileEntitySoulChest extends TileEntity implements IInventory {

	private ItemStack[] chestContents;
	public float lidAngle;
	public float prevLidAngle;
	public int numUsingPlayers;
	protected int ticksSinceSync;

	public TileEntitySoulChest() {
		this(27);
	}

	public TileEntitySoulChest(int size) {
		chestContents = new ItemStack[size];
	}

	@Override
	public int getSizeInventory() {
		return chestContents.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return chestContents[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int size) {
		if (chestContents[slot] != null) {
			ItemStack itemstack;
			if (chestContents[slot].stackSize <= size) {
				itemstack = chestContents[slot];
				chestContents[slot] = null;
				return itemstack;
			} else {
				itemstack = chestContents[slot].splitStack(size);
				if (chestContents[slot].stackSize == 0)
					chestContents[slot] = null;
				return itemstack;
			}
		} else
			return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		if (chestContents[slot] != null) {
			ItemStack itemstack = chestContents[slot];
			chestContents[slot] = null;
			return itemstack;
		} else
			return null;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		chestContents[slot] = stack;

		if (stack != null && stack.stackSize > getInventoryStackLimit())
			stack.stackSize = getInventoryStackLimit();
	}

	@Override
	public String getInvName() {
		return Utils.getConainerName(Strings.SOUL_CHEST_NAME);
	}

	@Override
	public void readFromNBT(NBTTagCompound data) {
		super.readFromNBT(data);
		NBTTagList nbttaglist = data.getTagList("Items");
		chestContents = new ItemStack[getSizeInventory()];

		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbttaglist.tagAt(i);
			byte b0 = nbttagcompound1.getByte("Slot");

			if (b0 >= 0 && b0 < chestContents.length)
				chestContents[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound data) {
		super.writeToNBT(data);
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < chestContents.length; ++i)
			if (chestContents[i] != null) {
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				chestContents[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}

		data.setTag("Items", nbttaglist);
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
	public void updateEntity() {
		ticksSinceSync++;

		float f;
		if (!worldObj.isRemote && numUsingPlayers != 0 && (ticksSinceSync + xCoord + yCoord + zCoord) % 200 == 0)
			numUsingPlayers = 0;

		prevLidAngle = lidAngle;
		f = 0.1F;
		double d0;

		if (numUsingPlayers > 0 && lidAngle == 0.0F) {
			double d1 = xCoord + 0.5D;
			d0 = zCoord + 0.5D;

			worldObj.playSoundEffect(d1, yCoord + 0.5D, d0, "random.chestopen", 0.5F, worldObj.rand.nextFloat() * 0.1F + 0.9F);
		}

		if (numUsingPlayers <= 0 && lidAngle > 0.0F || numUsingPlayers > 0 && lidAngle < 1.0F) {
			float f1 = lidAngle;

			if (numUsingPlayers > 0)
				lidAngle += f;
			else
				lidAngle -= f;

			if (lidAngle > 1.0F)
				lidAngle = 1.0F;

			float f2 = 0.5F;

			if (lidAngle < f2 && f1 >= f2) {
				d0 = xCoord + 0.5D;
				double d2 = zCoord + 0.5D;

				worldObj.playSoundEffect(d0, yCoord + 0.5D, d2, "random.chestclosed", 0.5F, worldObj.rand.nextFloat() * 0.1F + 0.9F);
			}

			if (lidAngle < 0.0F)
				lidAngle = 0.0F;
		}
	}

	@Override
	public boolean receiveClientEvent(int eventType, int arg) {
		if (eventType == 1) {
			numUsingPlayers = arg;
			return true;
		}
		return false;
	}

	@Override
	public void openChest() {
		if (numUsingPlayers < 0)
			numUsingPlayers = 0;

		numUsingPlayers++;
		worldObj.addBlockEvent(xCoord, yCoord, zCoord, getBlockType().blockID, 1, numUsingPlayers);
	}

	@Override
	public void closeChest() {
		numUsingPlayers--;
		worldObj.addBlockEvent(xCoord, yCoord, zCoord, getBlockType().blockID, 1, numUsingPlayers);
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return true;
	}

	@Override
	public boolean isInvNameLocalized() {
		return false;
	}
}