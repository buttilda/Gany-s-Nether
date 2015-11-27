package ganymedes01.ganysnether.tileentities;

import java.util.ArrayList;

import ganymedes01.ganysnether.inventory.ContainerThermalSmelter;
import ganymedes01.ganysnether.lib.Strings;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class TileEntityThermalSmelter extends GanysInventory implements ISidedInventory {

	private int cookTime;
	private ArrayList<ForgeDirection> dirs = new ArrayList<ForgeDirection>();
	public byte dirCheck, speedModifier;

	public TileEntityThermalSmelter() {
		super(2, Strings.Blocks.THERMAL_SMELTER_NAME);
	}

	@Override
	public void updateEntity() {
		if (worldObj.isRemote)
			return;

		checkForLava();

		if (inventory[0] == null || speedModifier <= 0) {
			cookTime = 0;
			return;
		}
		cookTime++;
		if (cookTime >= getMaxCookTime())
			smelt();
	}

	private void smelt() {
		if (inventory[0] != null) {
			ItemStack result = FurnaceRecipes.smelting().getSmeltingResult(inventory[0]);
			if (result != null) {
				boolean smelted = false;
				if (inventory[1] == null) {
					inventory[1] = result.copy();
					smelted = true;
				} else if (compareStacks(inventory[1], result) && inventory[1].stackSize + result.stackSize <= inventory[1].getMaxStackSize()) {
					inventory[1].stackSize += result.stackSize;
					smelted = true;
				}
				if (smelted) {
					cookTime = 0;
					inventory[0].stackSize--;
					if (inventory[0].stackSize <= 0)
						inventory[0] = null;
				}
			} else
				cookTime = 0;
		}
	}

	private boolean compareStacks(ItemStack stack1, ItemStack stack2) {
		if (stack1 == null || stack2 == null)
			return false;
		if (stack1.getItem() == stack2.getItem())
			if (stack1.getItemDamage() == stack2.getItemDamage())
				return true;
		return false;
	}

	private void checkForLava() {
		dirCheck++;
		if (dirCheck >= ForgeDirection.VALID_DIRECTIONS.length)
			dirCheck = 0;
		ForgeDirection currDir = ForgeDirection.VALID_DIRECTIONS[dirCheck];
		if (isLavaBlock(currDir)) {
			if (!dirs.contains(currDir))
				dirs.add(currDir);
		} else
			dirs.remove(currDir);

		speedModifier = (byte) Math.min(dirs.size(), 6);
	}

	private boolean isLavaBlock(ForgeDirection dir) {
		Block block = worldObj.getBlock(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ);
		return block == Blocks.lava;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		int[] slots = new int[getSizeInventory()];
		for (int i = 0; i < slots.length; i++)
			slots[i] = i;
		return slots;
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return slot == 0 && FurnaceRecipes.smelting().getSmeltingResult(stack) != null;
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack stack, int side) {
		return isItemValidForSlot(slot, stack);
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack stack, int side) {
		return slot == 1;
	}

	@Override
	public void readFromNBT(NBTTagCompound data) {
		super.readFromNBT(data);
		dirs = new ArrayList<ForgeDirection>();
		byte dirsSize = data.getByte("dirsSize");
		for (byte i = 0; i < dirsSize; i++)
			dirs.add(ForgeDirection.values()[data.getByte("dir" + i)]);

		cookTime = data.getInteger("cookTime");
	}

	@Override
	public void writeToNBT(NBTTagCompound data) {
		super.writeToNBT(data);
		data.setByte("dirsSize", (byte) dirs.size());
		for (byte i = 0; i < dirs.size(); i++)
			data.setByte("dir" + i, (byte) dirs.get(i).ordinal());

		data.setInteger("cookTime", cookTime);
	}

	private int getMaxCookTime() {
		return 1300 - 100 * speedModifier;
	}

	public int getCookTime(int scale) {
		return cookTime * scale / getMaxCookTime();
	}

	public void getGUIData(int id, int value) {
		if (id == 0)
			cookTime = value;
		else if (id == 1)
			speedModifier = (byte) value;
	}

	public void sendGUIData(ContainerThermalSmelter tile, ICrafting craft) {
		craft.sendProgressBarUpdate(tile, 0, cookTime);
		craft.sendProgressBarUpdate(tile, 1, speedModifier);
	}
}