package ganymedes01.ganysnether.tileentities;

import ganymedes01.ganysnether.blocks.FocusedLavaCell;
import ganymedes01.ganysnether.core.utils.InventoryUtils;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.inventory.ContainerVolcanicFurnace;
import ganymedes01.ganysnether.lib.Strings;
import ganymedes01.ganysnether.recipes.VolcanicFurnaceHandler;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
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

public class TileEntityVolcanicFurnace extends GanysInventory implements ISidedInventory, IFluidHandler {

	private final FluidTank tank;

	public int meltTime;
	public int currentItemMeltTime;
	private int FILL_RATE = 1;
	public boolean hasLava = false;
	public int cellCount = -1;

	public TileEntityVolcanicFurnace() {
		super(3, Strings.Blocks.VOLCANIC_FURNACE_NAME);

		tank = new FluidTank(FluidContainerRegistry.BUCKET_VOLUME * 16);
		tank.setFluid(new FluidStack(FluidRegistry.LAVA, 0));
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

		if (cellCount < 0) {
			cellCount = FocusedLavaCell.getCellCount(worldObj, xCoord, yCoord, zCoord);
			FILL_RATE = cellCount > 0 ? 200 * cellCount : 1;
			return;
		}

		ejectLava();
		meltItems();
		fillBuckets();
		sendUpdates();
	}

	private void ejectLava() {
		if (tank.getFluidAmount() <= (int) (tank.getCapacity() / 2F))
			return;
		if (worldObj.rand.nextBoolean()) {
			IFluidHandler fluidHandler = Utils.getTileEntity(worldObj, xCoord, yCoord + 1, zCoord, IFluidHandler.class);
			if (fluidHandler != null) {
				FluidStack drained = tank.drain((int) (FILL_RATE * 2F), true);
				if (drained != null) {
					drained.amount -= fluidHandler.fill(ForgeDirection.DOWN, drained, true);
					if (drained.amount > 0)
						tank.fill(drained, true);
				}
			}
		}
	}

	private void sendUpdates() {
		worldObj.addBlockEvent(xCoord, yCoord, zCoord, getBlockType(), 0, tank.getFluidAmount());
	}

	private void meltItems() {
		int amount = Math.min(FILL_RATE, meltTime);
		if (tank.getFluidAmount() >= tank.getCapacity() + amount)
			return;
		if (meltTime <= 0) {
			if (inventory[0] != null) {
				int burnTime = VolcanicFurnaceHandler.getBurnTime(inventory[0].copy());
				if (burnTime > 0) {
					currentItemMeltTime = meltTime = burnTime;
					inventory[0].stackSize--;
					if (inventory[0].stackSize <= 0)
						inventory[0] = null;
				}
			}
		} else if (meltTime > 0) {
			meltTime -= amount;
			tank.fill(new FluidStack(FluidRegistry.LAVA, amount), true);
		}
	}

	private void fillBuckets() {
		if (FluidContainerRegistry.isEmptyContainer(inventory[1])) {
			ItemStack filledContainer = FluidContainerRegistry.fillFluidContainer(tank.getFluid(), inventory[1]);
			if (filledContainer != null) {
				boolean shouldDrain = false;
				if (inventory[2] == null) {
					inventory[2] = filledContainer.copy();
					shouldDrain = true;
				} else if (InventoryUtils.areStacksTheSame(filledContainer, inventory[2], false) && inventory[2].stackSize + filledContainer.stackSize <= inventory[2].getMaxStackSize()) {
					inventory[2].stackSize++;
					shouldDrain = true;
				}

				if (shouldDrain) {
					tank.drain(FluidContainerRegistry.getFluidForFilledItem(filledContainer).amount, true);
					inventory[1].stackSize--;
					if (inventory[1].stackSize <= 0)
						inventory[1] = null;
				}
			}
		}
	}

	@Override
	public boolean receiveClientEvent(int eventId, int eventData) {
		switch (eventId) {
			case 0:
				boolean old = hasLava;
				hasLava = eventData > 0;
				if (hasLava != old) {
					worldObj.func_147451_t(xCoord, yCoord, zCoord);
					worldObj.func_147479_m(xCoord, yCoord, zCoord);
				}
				return true;
			default:
				return false;
		}
	}

	public void getGUIData(int id, int value) {
		switch (id) {
			case 1:
				tank.setFluid(new FluidStack(FluidRegistry.LAVA, value));
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
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return slot == 0 ? VolcanicFurnaceHandler.itemIsFuel(stack) : slot == 1 ? FluidContainerRegistry.isEmptyContainer(stack) : false;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		return new int[] { 0, 1, 2 };
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack stack, int side) {
		return isItemValidForSlot(slot, stack);
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack stack, int side) {
		return slot == 2;
	}

	@Override
	public void readFromNBT(NBTTagCompound data) {
		super.readFromNBT(data);
		tank.readFromNBT(data);
		meltTime = data.getInteger("meltTime");
		currentItemMeltTime = data.getInteger("currentItemMeltTime");
	}

	@Override
	public void writeToNBT(NBTTagCompound data) {
		super.writeToNBT(data);
		tank.writeToNBT(data);
		data.setInteger("meltTime", meltTime);
		data.setInteger("currentItemMeltTime", currentItemMeltTime);
	}

	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		return 0;
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
		if (resource == null)
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
		return new FluidTankInfo[] { tank.getInfo() };
	}

	public int getScaledFluidAmount(int scale) {
		return tank.getFluid() != null ? (int) ((float) tank.getFluid().amount / (float) tank.getCapacity() * scale) : 0;
	}
}