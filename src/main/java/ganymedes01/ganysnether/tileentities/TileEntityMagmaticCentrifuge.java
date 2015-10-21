package ganymedes01.ganysnether.tileentities;

import ganymedes01.ganysnether.core.utils.InventoryUtils;
import ganymedes01.ganysnether.inventory.ContainerMagmaticCentrifuge;
import ganymedes01.ganysnether.lib.Strings;
import ganymedes01.ganysnether.recipes.CentrifugeRecipe;
import ganymedes01.ganysnether.recipes.MagmaticCentrifugeRecipes;

import java.util.ArrayList;

import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidContainerRegistry.FluidContainerData;
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

public class TileEntityMagmaticCentrifuge extends GanysInventory implements ISidedInventory, IFluidHandler {

	private final FluidTank tank = new FluidTank(FluidContainerRegistry.BUCKET_VOLUME * 16);

	private int angle = 0;
	private float rotationAngle = 0.0F;
	private int turnsCount = 0;

	public boolean isRecipeValid = false;
	public boolean isPowered = false;

	public static final int FULL_BUCKET_SLOT = 0, EMPTY_BUCKET_SLOT = 1, MATERIAL_SLOT_1 = 2, MATERIAL_SLOT_2 = 3, RESULT_SLOT_1 = 4, RESULT_SLOT_2 = 5, RESULT_SLOT_3 = 6, RESULT_SLOT_4 = 7;

	public TileEntityMagmaticCentrifuge() {
		super(8, Strings.Blocks.MAGMATIC_CENTRIFUGE_NAME);
		tank.setFluid(new FluidStack(FluidRegistry.LAVA, 0));
		checkRecipe();
	}

	@Override
	public void updateEntity() {
		if (worldObj.isRemote || isPowered)
			return;

		if (isRecipeValid) {
			angle++;
			if (angle >= 360) {
				angle -= 360;
				turnsCount++;
			}
		} else {
			turnsCount = 0;
			if (angle != 0) {
				angle -= 6;
				if (angle < 0)
					angle = 0;
			}
		}

		fillTankFromContainer();

		if (turnsCount >= 3) {
			centrifuge();
			turnsCount = 0;
		}
	}

	@Override
	public void markDirty() {
		super.markDirty();
		checkRecipe();
	}

	private void fillTankFromContainer() {
		if (tank.getFluidAmount() <= tank.getCapacity() - FluidContainerRegistry.BUCKET_VOLUME)
			if (FluidContainerRegistry.isFilledContainer(inventory[FULL_BUCKET_SLOT])) {
				ItemStack emptyContainer = getEmptyContainer();
				if (inventory[EMPTY_BUCKET_SLOT] == null || emptyContainer != null && emptyContainer.getItem() == inventory[EMPTY_BUCKET_SLOT].getItem() && emptyContainer.getItemDamage() == inventory[EMPTY_BUCKET_SLOT].getItemDamage())
					if (FluidContainerRegistry.getFluidForFilledItem(inventory[FULL_BUCKET_SLOT]).getFluid() == FluidRegistry.LAVA) {
						tank.fill(FluidContainerRegistry.getFluidForFilledItem(inventory[FULL_BUCKET_SLOT]), true);
						boolean flag = false;
						if (inventory[EMPTY_BUCKET_SLOT] == null) {
							inventory[EMPTY_BUCKET_SLOT] = emptyContainer;
							flag = true;
						} else if (inventory[EMPTY_BUCKET_SLOT].stackSize < inventory[EMPTY_BUCKET_SLOT].getMaxStackSize()) {
							inventory[EMPTY_BUCKET_SLOT].stackSize++;
							flag = true;
						}
						if (flag) {
							inventory[FULL_BUCKET_SLOT].stackSize--;
							if (inventory[FULL_BUCKET_SLOT].stackSize <= 0)
								inventory[FULL_BUCKET_SLOT] = null;
							markDirty();
						}
					}
			}
	}

	private ItemStack getEmptyContainer() {
		for (FluidContainerData data : FluidContainerRegistry.getRegisteredFluidContainerData())
			if (data != null && inventory[FULL_BUCKET_SLOT] != null)
				if (data.filledContainer.getItem() == inventory[FULL_BUCKET_SLOT].getItem() && data.filledContainer.getItemDamage() == inventory[FULL_BUCKET_SLOT].getItemDamage())
					return data.emptyContainer.copy();
		return null;
	}

	private void centrifuge() {
		CentrifugeRecipe recipe = MagmaticCentrifugeRecipes.INSTANCE.getRecipe(inventory[MATERIAL_SLOT_1], inventory[MATERIAL_SLOT_2]);
		if (tank.getFluidAmount() < recipe.getLavaAmount())
			return;

		ItemStack[] resultContents = recipe.getResult();
		ArrayList<Integer> slotsTaken = new ArrayList<Integer>();
		if (resultContents != null && resultContents.length <= 4) {
			for (ItemStack result : resultContents) {
				ArrayList<Integer> availableSlots = getAvailableSlots(result, slotsTaken);
				if (availableSlots.isEmpty())
					return;
				else
					slotsTaken.add(availableSlots.get(0));
			}
			if (slotsTaken.size() >= resultContents.length) {
				for (int i = 0; i < resultContents.length; i++) {
					ItemStack result = resultContents[i];
					ItemStack resultSlot = inventory[slotsTaken.get(i)];
					if (result == null)
						break;
					if (resultSlot != null) {
						if (InventoryUtils.areStacksTheSame(result, resultSlot, false) && resultSlot.stackSize + result.stackSize <= resultSlot.getMaxStackSize())
							resultSlot.stackSize += result.stackSize;
					} else
						inventory[slotsTaken.get(i)] = result.copy();
				}

				tank.drain(recipe.getLavaAmount(), true);

				if (recipe.getInput1().getSize() != recipe.getInput2().getSize()) {
					if (recipe.getInput1().matches(inventory[MATERIAL_SLOT_1])) { // if the input1 matches the slot 1 then the slot 2 will match input2
						inventory[MATERIAL_SLOT_1].stackSize -= recipe.getInput1().getSize();
						inventory[MATERIAL_SLOT_2].stackSize -= recipe.getInput2().getSize();
					} else { // else... input1 must match slot2 and slot1 must match input2
						inventory[MATERIAL_SLOT_1].stackSize -= recipe.getInput2().getSize();
						inventory[MATERIAL_SLOT_2].stackSize -= recipe.getInput1().getSize();
					}
				} else {
					inventory[MATERIAL_SLOT_1].stackSize -= recipe.getInput1().getSize();
					inventory[MATERIAL_SLOT_2].stackSize -= recipe.getInput1().getSize();
				}

				if (inventory[MATERIAL_SLOT_1].stackSize <= 0)
					inventory[MATERIAL_SLOT_1] = null;
				if (inventory[MATERIAL_SLOT_2].stackSize <= 0)
					inventory[MATERIAL_SLOT_2] = null;

				markDirty();
			}
		}
	}

	private ArrayList<Integer> getAvailableSlots(ItemStack stack, ArrayList<Integer> ignore) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 4; i < 8; i++) {
			if (inventory[i] == null) {
				if (ignore.contains(i))
					continue;
				list.add(i);
				continue;
			}
			if (InventoryUtils.areStacksTheSame(stack, inventory[i], false) && inventory[i].stackSize + stack.stackSize <= inventory[i].getMaxStackSize()) {
				if (ignore.contains(i))
					continue;
				list.add(i);
				continue;
			}
		}
		return list;
	}

	private void checkRecipe() {
		if (inventory[MATERIAL_SLOT_1] == null || inventory[MATERIAL_SLOT_2] == null)
			isRecipeValid = false;
		else {
			CentrifugeRecipe recipe = MagmaticCentrifugeRecipes.INSTANCE.getRecipe(inventory[MATERIAL_SLOT_1], inventory[MATERIAL_SLOT_2]);
			isRecipeValid = recipe != null && tank.getFluidAmount() >= recipe.getLavaAmount();
		}
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		return side == 0 ? new int[] { RESULT_SLOT_1, RESULT_SLOT_2, RESULT_SLOT_3, RESULT_SLOT_4, EMPTY_BUCKET_SLOT } : side == 1 ? new int[] { MATERIAL_SLOT_1, MATERIAL_SLOT_2 } : new int[] { FULL_BUCKET_SLOT };
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
	public Packet getDescriptionPacket() {
		NBTTagCompound nbt = new NBTTagCompound();
		writeToNBT(nbt);
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 0, nbt);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		if (pkt.func_148853_f() == 0)
			readFromNBT(pkt.func_148857_g());
	}

	@SideOnly(Side.CLIENT)
	public float getCoreRenderAngle() {
		if (isRecipeValid)
			rotationAngle = (float) (360.0 * (System.currentTimeMillis() & 0x3FFFL) / 0x3FFFL);
		else
			rotationAngle -= rotationAngle > 0 ? 2 : 0;
		return rotationAngle;
	}

	@Override
	public void readFromNBT(NBTTagCompound data) {
		super.readFromNBT(data);
		tank.readFromNBT(data);
		angle = data.getInteger("angle");
		turnsCount = data.getInteger("turnsCount");
		rotationAngle = data.getFloat("rotationAngle");
	}

	@Override
	public void writeToNBT(NBTTagCompound data) {
		super.writeToNBT(data);
		tank.writeToNBT(data);
		data.setInteger("angle", angle);
		data.setInteger("turnsCount", turnsCount);
		data.setFloat("rotationAngle", rotationAngle);
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

	public int getTurnsCount() {
		return turnsCount;
	}

	public void getGUIData(int id, int value) {
		switch (id) {
			case 1:
				if (tank.getFluid() == null)
					tank.setFluid(new FluidStack(FluidRegistry.LAVA, value));
				else
					tank.getFluid().amount = value;
				break;
			case 2:
				angle = value;
				break;
			case 3:
				turnsCount = value;
				break;
		}
	}

	public void sendGUIData(ContainerMagmaticCentrifuge containerMagmaticCentrifuge, ICrafting craft) {
		craft.sendProgressBarUpdate(containerMagmaticCentrifuge, 1, tank.getFluid() != null ? tank.getFluid().amount : 0);
		craft.sendProgressBarUpdate(containerMagmaticCentrifuge, 2, angle);
		craft.sendProgressBarUpdate(containerMagmaticCentrifuge, 3, turnsCount);
	}

	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		if (resource == null || resource.getFluid() != FluidRegistry.LAVA)
			return 0;

		int filled = tank.fill(resource, doFill);
		checkRecipe();
		return filled;
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