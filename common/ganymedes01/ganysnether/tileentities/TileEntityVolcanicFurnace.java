package ganymedes01.ganysnether.tileentities;

import ganymedes01.ganysnether.blocks.VolcanicFurnace;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.inventory.ContainerVolcanicFurnace;
import ganymedes01.ganysnether.lib.Strings;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockHalfSlab;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.BlockWood;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
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
import net.minecraftforge.oredict.OreDictionary;
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
				furnaceItemStacks[2] = FluidContainerRegistry.fillFluidContainer(new FluidStack(FluidRegistry.LAVA, FluidContainerRegistry.BUCKET_VOLUME), furnaceItemStacks[1]);
				if (furnaceItemStacks[2] != null) {
					if (furnaceItemStacks[1].stackSize == 1)
						furnaceItemStacks[1] = null;
					else
						--furnaceItemStacks[1].stackSize;
					tank.drain(FluidContainerRegistry.BUCKET_VOLUME, true);
				}
			}

		VolcanicFurnace.updateFurnaceBlockState(tank.getFluidAmount() > 0, worldObj, xCoord, yCoord, zCoord);
		if (meltTime <= 0) {
			if (itemIsFuel(furnaceItemStacks[0]) && tank.getFluidAmount() < tank.getCapacity()) {
				currentItemMeltTime = meltTime = getItemBurnTime(furnaceItemStacks[0]);
				if (furnaceItemStacks[0].stackSize == 1)
					furnaceItemStacks[0] = null;
				else
					--furnaceItemStacks[0].stackSize;
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

	// 1000 = 1 bucket
	// Default - 20 (50 Items/Blocks = 1 Bucket)
	public static int getItemBurnTime(ItemStack stack) {
		if (stack == null || !itemIsFuel(stack))
			return 0;
		Item item = stack.getItem();
		Block block = null;
		if (stack.itemID < Block.blocksList.length)
			block = Block.blocksList[stack.itemID];

		if (item == Item.blazePowder || item == Item.dyePowder)
			return 7;
		else if (stack.getUnlocalizedName().toLowerCase().contains("nugget"))
			return 2;
		else if (item == Item.netherStar)
			return 10000;
		else if (block != null)
			if (block == Block.carpet)
				return 12;
			else if (block == Block.dragonEgg)
				return 1000000;
			else if (block == Block.netherrack)
				return 35 + new Random().nextInt(5);
			else if (block == Block.thinGlass || block == Block.fenceIron)
				return 7;
			else if (block instanceof BlockWood)
				return 5;
			else if (block instanceof BlockHalfSlab)
				return 2;
			else if (block instanceof BlockStairs)
				return 7;
			else if (block instanceof BlockSapling)
				return 10;
			else if (block instanceof BlockLeaves)
				return 10;
			else if (block instanceof BlockTorch)
				return 5;
			else if (block instanceof BlockFlower)
				return 10;

		for (ItemStack logs : OreDictionary.getOres("plankWood"))
			if (logs.getItem() == item)
				return 5;
		for (ItemStack logs : OreDictionary.getOres("slabWood"))
			if (logs.getItem() == item)
				return 2;
		for (ItemStack logs : OreDictionary.getOres("stairWood"))
			if (logs.getItem() == item)
				return 7;
		for (ItemStack logs : OreDictionary.getOres("treeSapling"))
			if (logs.getItem() == item)
				return 10;
		for (ItemStack logs : OreDictionary.getOres("treeLeaves"))
			if (logs.getItem() == item)
				return 10;
		for (ItemStack logs : OreDictionary.getOres("stickWood"))
			if (logs.getItem() == item)
				return 2;

		return 16 + new Random().nextInt(5);
	}

	public static boolean itemIsFuel(ItemStack stack) {
		if (stack != null) {
			if (FluidContainerRegistry.isFilledContainer(stack))
				return FluidContainerRegistry.getFluidForFilledItem(stack) != null && FluidContainerRegistry.getFluidForFilledItem(stack).isFluidEqual(new FluidStack(FluidRegistry.LAVA, FluidContainerRegistry.BUCKET_VOLUME));
			for (ItemStack item : getItemsThatCantBeMelted())
				if (item.getItem() == stack.getItem())
					return false;
			if (stack.getItem() instanceof ItemBlock)
				if (stack.itemID < Block.blocksList.length) {
					Material material = Block.blocksList[stack.itemID].blockMaterial;
					if (material == Material.snow || material == Material.craftedSnow || material == Material.ice || material == Material.water)
						return false;
				}
			return true;
		}
		return false;
	}

	private static ArrayList<ItemStack> getItemsThatCantBeMelted() {
		ArrayList<ItemStack> items = new ArrayList<ItemStack>();
		items.add(new ItemStack(Item.potion));
		items.add(new ItemStack(Item.expBottle));
		items.add(new ItemStack(Item.snowball));
		return items;
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
		return slot == 0 ? itemIsFuel(stack) : slot == 1 ? FluidContainerRegistry.isEmptyContainer(stack) : false;
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
		NBTTagList nbttaglist = data.getTagList("Items");
		furnaceItemStacks = new ItemStack[getSizeInventory()];

		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbttaglist.tagAt(i);
			byte b0 = nbttagcompound1.getByte("Slot");

			if (b0 >= 0 && b0 < furnaceItemStacks.length)
				furnaceItemStacks[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
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

		for (int i = 0; i < furnaceItemStacks.length; ++i)
			if (furnaceItemStacks[i] != null) {
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				furnaceItemStacks[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
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
