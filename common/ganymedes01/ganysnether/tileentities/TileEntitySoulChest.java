package ganymedes01.ganysnether.tileentities;

import ganymedes01.ganysnether.blocks.SoulChest;

import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public class TileEntitySoulChest extends TileEntity implements IInventory {
	private ItemStack[] chestContents = new ItemStack[36];

	public float lidAngle;
	public float prevLidAngle;
	public int numUsingPlayers;
	private int ticksSinceSync;

	public TileEntitySoulChest() {
	}

	@Override
	public int getSizeInventory() {
		return 27;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return chestContents[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int num) {
		if (chestContents[slot] != null) {
			ItemStack itemstack;

			if (chestContents[slot].stackSize <= num) {
				itemstack = chestContents[slot];
				chestContents[slot] = null;
				onInventoryChanged();
				return itemstack;
			} else {
				itemstack = chestContents[slot].splitStack(num);

				if (chestContents[slot].stackSize == 0)
					chestContents[slot] = null;

				onInventoryChanged();
				return itemstack;
			}
		}
		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		if (chestContents[slot] != null) {
			ItemStack itemstack = chestContents[slot];
			chestContents[slot] = null;
			return itemstack;
		}
		return null;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		chestContents[slot] = stack;

		if (stack != null && stack.stackSize > getInventoryStackLimit())
			stack.stackSize = getInventoryStackLimit();

		onInventoryChanged();
	}

	@Override
	public String getInvName() {
		return "Soul Chest";
	}

	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readFromNBT(par1NBTTagCompound);
		NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items");
		chestContents = new ItemStack[getSizeInventory()];

		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbttaglist.tagAt(i);
			int j = nbttagcompound1.getByte("Slot") & 255;

			if (j >= 0 && j < chestContents.length)
				chestContents[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeToNBT(par1NBTTagCompound);
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < chestContents.length; ++i)
			if (chestContents[i] != null) {
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				chestContents[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}

		par1NBTTagCompound.setTag("Items", nbttaglist);
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
		super.updateEntity();
		++ticksSinceSync;
		float f;

		if (!worldObj.isRemote && numUsingPlayers != 0 && (ticksSinceSync + xCoord + yCoord + zCoord) % 200 == 0) {
			numUsingPlayers = 0;
			f = 5.0F;
			List list = worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getAABBPool().getAABB(xCoord - f, yCoord - f, zCoord - f, xCoord + 1 + f, yCoord + 1 + f, zCoord + 1 + f));
			Iterator iterator = list.iterator();

			while (iterator.hasNext()) {
				EntityPlayer entityplayer = (EntityPlayer) iterator.next();

				if (entityplayer.openContainer instanceof ContainerChest)
					((ContainerChest) entityplayer.openContainer).getLowerChestInventory();
			}
		}

		prevLidAngle = lidAngle;
		f = 0.1F;
		double d0;

		if (numUsingPlayers > 0 && lidAngle == 0.0F) {
			double d1 = xCoord + 0.5D;
			d0 = zCoord + 0.5D;

			worldObj.playSoundEffect(d1, yCoord + 0.5D, d0, "random.chestopen", 0.5F, worldObj.rand.nextFloat() * 0.1F + 0.9F);
		}

		if (numUsingPlayers == 0 && lidAngle > 0.0F || numUsingPlayers > 0 && lidAngle < 1.0F) {
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
	public boolean receiveClientEvent(int par1, int par2) {
		if (par1 == 1) {
			numUsingPlayers = par2;
			return true;
		}
		return super.receiveClientEvent(par1, par2);
	}

	@Override
	public void openChest() {
		if (numUsingPlayers < 0)
			numUsingPlayers = 0;

		++numUsingPlayers;
		worldObj.addBlockEvent(xCoord, yCoord, zCoord, getBlockType().blockID, 1, numUsingPlayers);
		worldObj.notifyBlocksOfNeighborChange(xCoord, yCoord, zCoord, getBlockType().blockID);
		worldObj.notifyBlocksOfNeighborChange(xCoord, yCoord - 1, zCoord, getBlockType().blockID);
	}

	@Override
	public void closeChest() {
		if (getBlockType() != null && getBlockType() instanceof SoulChest) {
			--numUsingPlayers;
			worldObj.addBlockEvent(xCoord, yCoord, zCoord, getBlockType().blockID, 1, numUsingPlayers);
			worldObj.notifyBlocksOfNeighborChange(xCoord, yCoord, zCoord, getBlockType().blockID);
			worldObj.notifyBlocksOfNeighborChange(xCoord, yCoord - 1, zCoord, getBlockType().blockID);
		}
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return true;
	}

	@Override
	public void invalidate() {
		super.invalidate();
		updateContainingBlockInfo();
	}

	@Override
	public boolean isInvNameLocalized() {
		return false;
	}
}
