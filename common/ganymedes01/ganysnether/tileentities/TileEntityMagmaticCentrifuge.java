package ganymedes01.ganysnether.tileentities;

import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.inventory.ContainerMagmaticCentrifuge;
import ganymedes01.ganysnether.lib.Strings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidContainerRegistry.FluidContainerData;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class TileEntityMagmaticCentrifuge extends TileEntity implements ISidedInventory, IFluidHandler {

	private ItemStack[] inventory = new ItemStack[8];
	private FluidTank tank = new FluidTank(FluidContainerRegistry.BUCKET_VOLUME * 16);

	private int angle = 0;
	private int turnsCount = 0;

	private final int FULL_BUCKET_SLOT = 0;
	private final int EMPTY_BUCKET_SLOT = 1;
	private final int MATERIAL_SLOT_1 = 2;
	private final int MATERIAL_SLOT_2 = 3;
	private final int RESULT_SLOT_1 = 4;
	private final int RESULT_SLOT_2 = 5;
	private final int RESULT_SLOT_3 = 6;
	private final int RESULT_SLOT_4 = 7;

	public TileEntityMagmaticCentrifuge() {
		tank.setFluid(new FluidStack(FluidRegistry.LAVA, 0));
	}

	@Override
	public void updateEntity() {
		if (worldObj.isRemote)
			return;
		if (worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord))
			return;

		angle++;
		if (angle >= 360) {
			angle = 0;
			turnsCount++;
		}

		fillTankFromContainer();
		if (turnsCount >= 3) {
			centrifuge();
			turnsCount = 0;
		}
	}

	private void fillTankFromContainer() {
		if (tank.getFluidAmount() <= tank.getCapacity() - FluidContainerRegistry.BUCKET_VOLUME)
			if (FluidContainerRegistry.isFilledContainer(inventory[FULL_BUCKET_SLOT])) {
				tank.fill(FluidContainerRegistry.getFluidForFilledItem(inventory[FULL_BUCKET_SLOT]), true);
				for (FluidContainerData data : FluidContainerRegistry.getRegisteredFluidContainerData())
					if (data != null && inventory[FULL_BUCKET_SLOT] != null)
						if (data.filledContainer.itemID == inventory[FULL_BUCKET_SLOT].itemID && data.filledContainer.getItemDamage() == inventory[FULL_BUCKET_SLOT].getItemDamage()) {
							if (inventory[EMPTY_BUCKET_SLOT] != null) {
								if (inventory[EMPTY_BUCKET_SLOT].itemID == data.emptyContainer.itemID && inventory[EMPTY_BUCKET_SLOT].getItemDamage() == data.emptyContainer.getItemDamage())
									inventory[EMPTY_BUCKET_SLOT].stackSize++;
							} else
								inventory[EMPTY_BUCKET_SLOT] = data.emptyContainer.copy();
							inventory[FULL_BUCKET_SLOT].stackSize--;
							if (inventory[FULL_BUCKET_SLOT].stackSize <= 0)
								inventory[FULL_BUCKET_SLOT] = null;
						}
			}
	}

	private void centrifuge() {
		// TODO
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		return side == 0 ? new int[] { RESULT_SLOT_1, RESULT_SLOT_2, RESULT_SLOT_3, RESULT_SLOT_4 } : side == 1 ? new int[] { MATERIAL_SLOT_1, MATERIAL_SLOT_2 } : new int[] { FULL_BUCKET_SLOT, EMPTY_BUCKET_SLOT };
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
		return Utils.getConainerName(Strings.MAGMATIC_CENTRIFUGE_NAME);
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
		return slot == FULL_BUCKET_SLOT ? FluidContainerRegistry.isFilledContainer(stack) : slot == MATERIAL_SLOT_1 || slot == MATERIAL_SLOT_2;
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack stack, int side) {
		return isItemValidForSlot(slot, stack);
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack stack, int side) {
		return slot != MATERIAL_SLOT_1 || slot != MATERIAL_SLOT_2 || slot != FULL_BUCKET_SLOT;
	}

	@Override
	public void readFromNBT(NBTTagCompound data) {
		super.readFromNBT(data);
		tank.readFromNBT(data);
		NBTTagList nbttaglist = data.getTagList("Items");
		inventory = new ItemStack[getSizeInventory()];

		for (int i = 0; i < nbttaglist.tagCount(); i++) {
			NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbttaglist.tagAt(i);
			byte b0 = nbttagcompound1.getByte("Slot");

			if (b0 >= 0 && b0 < inventory.length)
				inventory[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
		}

		angle = data.getInteger("angle");
	}

	@Override
	public void writeToNBT(NBTTagCompound data) {
		super.writeToNBT(data);
		tank.writeToNBT(data);
		NBTTagList nbttaglist = new NBTTagList();
		for (int i = 0; i < inventory.length; i++)
			if (inventory[i] != null) {
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				inventory[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}

		data.setTag("Items", nbttaglist);

		data.setInteger("angle", angle);
	}

	public int getScaledFluidAmount(int scale) {
		return tank.getFluid() != null ? (int) ((float) tank.getFluid().amount / (float) tank.getCapacity() * scale) : 0;
	}

	public int getFluidAmount() {
		return tank.getFluidAmount();
	}

	public float getAngle() {
		return (float) (angle * (Math.PI / 180));
	}

	public void getGUIData(int id, int value) {
		switch (id) {
			case 1:
				if (tank.getFluid() == null)
					tank.setFluid(new FluidStack(0, value));
				else
					tank.getFluid().amount = value;
				break;
			case 2:
				angle = value;
				break;
		}
	}

	public void sendGUIData(ContainerMagmaticCentrifuge containerMagmaticCentrifuge, ICrafting craft) {
		craft.sendProgressBarUpdate(containerMagmaticCentrifuge, 1, tank.getFluid() != null ? tank.getFluid().amount : 0);
		craft.sendProgressBarUpdate(containerMagmaticCentrifuge, 2, angle);
	}

	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		return tank.fill(resource, doFill);
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
		return null;
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		return null;
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		return true;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return false;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		return new FluidTankInfo[] { tank.getInfo() };
	}
}