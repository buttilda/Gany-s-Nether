package ganymedes01.ganysnether.tileentities;

import ganymedes01.ganysnether.blocks.VolcanicFurnace;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.inventory.ContainerVolcanicFurnace;
import ganymedes01.ganysnether.lib.Strings;
import ganymedes01.ganysnether.recipes.VolcanicFurnaceHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class TileEntityVolcanicFurnace extends TileEntity implements ISidedInventory, IFluidHandler {

	private ItemStack[] furnaceItemStacks = new ItemStack[3];
	private FluidTank tank;

	public int meltTime;
	public int currentItemMeltTime;
	private final int FILL_RATE = 1;

	public TileEntityVolcanicFurnace() {
		tank = new FluidTank(FluidContainerRegistry.BUCKET_VOLUME * 16);
		tank.setFluid(new FluidStack(FluidRegistry.LAVA, 0));

		// Initiates the registry
		VolcanicFurnaceHandler.getItemBurnTime(new ItemStack(Item.appleGold));
	}

	@Override
	public int getSizeInventory() {
		return furnaceItemStacks.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return furnaceItemStacks[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int size) {
		if (furnaceItemStacks[slot] != null) {
			ItemStack itemstack;
			if (furnaceItemStacks[slot].stackSize <= size) {
				itemstack = furnaceItemStacks[slot];
				furnaceItemStacks[slot] = null;
				return itemstack;
			} else {
				itemstack = furnaceItemStacks[slot].splitStack(size);
				if (furnaceItemStacks[slot].stackSize == 0)
					furnaceItemStacks[slot] = null;
				return itemstack;
			}
		} else
			return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		if (furnaceItemStacks[slot] != null) {
			ItemStack itemstack = furnaceItemStacks[slot];
			furnaceItemStacks[slot] = null;
			return itemstack;
		} else
			return null;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		furnaceItemStacks[slot] = stack;

		if (stack != null && stack.stackSize > getInventoryStackLimit())
			stack.stackSize = getInventoryStackLimit();
	}

	@Override
	public String getInvName() {
		return Utils.getConainerName(Strings.VOLCANIC_FURNACE_NAME);
	}

	@Override
	public boolean isInvNameLocalized() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@SideOnly(Side.CLIENT)
	public int getMeltTimeRemainingScaled(int scale) {
		if (currentItemMeltTime == 0)
			return 0;
		return (int) (scale * ((float) meltTime / (float) currentItemMeltTime));
	}

	@SideOnly(Side.CLIENT)
	public int getFluidAmount() {
		return tank.getFluidAmount();
	}

	@Override
	public void updateEntity() {
		if (worldObj.isRemote)
			return;
		if (furnaceItemStacks[1] != null)
			if (FluidContainerRegistry.isContainer(furnaceItemStacks[1]) && tank.getFluidAmount() >= FluidContainerRegistry.BUCKET_VOLUME && furnaceItemStacks[2] == null) {
				furnaceItemStacks[2] = FluidContainerRegistry.fillFluidContainer(tank.drain(FluidContainerRegistry.BUCKET_VOLUME, true), furnaceItemStacks[1]);
				if (furnaceItemStacks[2] != null) {
					furnaceItemStacks[1].stackSize--;
					if (furnaceItemStacks[1].stackSize <= 0)
						furnaceItemStacks[1] = null;
				}
			}

		VolcanicFurnace.updateFurnaceBlockState(tank.getFluidAmount() > 0, worldObj, xCoord, yCoord, zCoord);
		if (meltTime <= 0) {
			if (furnaceItemStacks[0] != null && tank.getFluidAmount() < tank.getCapacity()) {
				int burnTime = VolcanicFurnaceHandler.getItemBurnTime(furnaceItemStacks[0]);
				if (burnTime > 0) {
					currentItemMeltTime = meltTime = burnTime;
					furnaceItemStacks[0].stackSize--;
					if (furnaceItemStacks[0].stackSize <= 0)
						furnaceItemStacks[0] = null;
				}
			}
		} else if (meltTime > 0 && tank.getFluidAmount() < tank.getCapacity()) {
			meltTime--;
			tank.fill(new FluidStack(FluidRegistry.LAVA, FILL_RATE), true);
		}
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
				currentItemMeltTime = value;
				break;
			case 3:
				meltTime = value;
				break;
		}
	}

	public void sendGUIData(ContainerVolcanicFurnace furnace, ICrafting craft) {
		craft.sendProgressBarUpdate(furnace, 1, tank.getFluid() != null ? tank.getFluid().amount : 0);
		craft.sendProgressBarUpdate(furnace, 2, currentItemMeltTime);
		craft.sendProgressBarUpdate(furnace, 3, meltTime);
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
		return slot == 0 ? VolcanicFurnaceHandler.itemIsFuel(stack) : slot == 1 ? FluidContainerRegistry.isEmptyContainer(stack) : false;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		return side == 0 ? new int[] { 2 } : side == 1 ? new int[] { 0 } : new int[] { 1 };
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
		tank.readFromNBT(data);
		NBTTagList list = data.getTagList("Items");
		furnaceItemStacks = new ItemStack[getSizeInventory()];

		for (int i = 0; i < list.tagCount(); i++) {
			NBTTagCompound tag = (NBTTagCompound) list.tagAt(i);
			byte b0 = tag.getByte("Slot");

			if (b0 >= 0 && b0 < furnaceItemStacks.length)
				furnaceItemStacks[b0] = ItemStack.loadItemStackFromNBT(tag);
		}

		meltTime = data.getInteger("meltTime");
		currentItemMeltTime = data.getInteger("currentItemMeltTime");
	}

	@Override
	public void writeToNBT(NBTTagCompound data) {
		super.writeToNBT(data);
		tank.writeToNBT(data);
		data.setInteger("meltTime", meltTime);
		data.setInteger("currentItemMeltTime", currentItemMeltTime);
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < furnaceItemStacks.length; i++)
			if (furnaceItemStacks[i] != null) {
				NBTTagCompound tag = new NBTTagCompound();
				tag.setByte("Slot", (byte) i);
				furnaceItemStacks[i].writeToNBT(tag);
				nbttaglist.appendTag(tag);
			}

		data.setTag("Items", nbttaglist);
	}

	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		return 0;
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
		if (resource != null && !resource.isFluidEqual(tank.getFluid()))
			return null;
		return drain(from, resource.amount, doDrain);
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		return tank.drain(maxDrain, doDrain);
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		return false;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return true;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		if (!worldObj.isRemote)
			if (from == ForgeDirection.UP || from == ForgeDirection.DOWN)
				return new FluidTankInfo[] { tank.getInfo() };
		return null;
	}

	public int getScaledFluidAmount(int scale) {
		return tank.getFluid() != null ? (int) ((float) tank.getFluid().amount / (float) tank.getCapacity() * scale) : 0;
	}
}
