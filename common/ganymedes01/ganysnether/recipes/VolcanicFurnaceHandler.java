package ganymedes01.ganysnether.recipes;

import ganymedes01.ganysnether.blocks.ModBlocks;
import ganymedes01.ganysnether.core.utils.ItemStackArray;
import ganymedes01.ganysnether.core.utils.ItemStackMap;
import ganymedes01.ganysnether.items.ModItems;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPane;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.BlockWood;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemSkull;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidContainerRegistry.FluidContainerData;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class VolcanicFurnaceHandler {

	private static ArrayList<ItemStack> meltingBlackList = new ItemStackArray();
	private static ArrayList<ItemStack> meltingWhiteList = new ItemStackArray();
	private static ItemStackMap<Integer> burnTimes = new ItemStackMap<Integer>();

	static {
		blackListItem(new ItemStack(Item.expBottle));
		blackListItem(new ItemStack(Item.snowball));
		blackListItem(new ItemStack(Block.snow));
		blackListItem(new ItemStack(Block.ice));
		blackListItem(new ItemStack(Block.blockSnow));
		blackListItem(new ItemStack(Block.vine));
		blackListItem(new ItemStack(Block.cactus));

		addBurnTimeForItem(new ItemStack(ModItems.glowingReed), 32);
		addBurnTimeForItem(new ItemStack(ModBlocks.denseLavaCell), 5000);
		addBurnTimeForItem(new ItemStack(Item.netherStar), 10000);
		addBurnTimeForItem(new ItemStack(Block.fire), 600);
		addBurnTimeForItem(new ItemStack(Block.bedrock), 8000);
		addBurnTimeForItem(new ItemStack(Block.lavaMoving), 1000);
		addBurnTimeForItem(new ItemStack(Block.lavaStill), 1000);
		addBurnTimeForItem(new ItemStack(ModItems.blazeIngot, 1, 2), 2);
		addBurnTimeForItem(new ItemStack(Item.goldNugget), 2);
		addBurnTimeForItem(new ItemStack(ModItems.ironNugget), 2);
		addBurnTimeForItem(new ItemStack(Block.dragonEgg), 1000000);
		addBurnTimeForItem(new ItemStack(Item.blazePowder), 7);
		addBurnTimeForItem(new ItemStack(Block.netherrack), 35);
		for (int i = 0; i < 16; i++)
			addBurnTimeForItem(new ItemStack(Item.dyePowder), 7);

		addBurnTimeForItem(new ItemStack(Item.goldNugget), 2);
		addBurnTimeForItem(new ItemStack(ModItems.blazeIngot), 2);
		for (ItemStack log : OreDictionary.getOres("plankWood"))
			addBurnTimeForItem(log, 5);
		for (ItemStack slab : OreDictionary.getOres("slabWood"))
			addBurnTimeForItem(slab, 2);
		for (ItemStack stair : OreDictionary.getOres("stairWood"))
			addBurnTimeForItem(stair, 7);
		for (ItemStack sapling : OreDictionary.getOres("treeSapling"))
			addBurnTimeForItem(sapling, 5);
		for (ItemStack leave : OreDictionary.getOres("treeLeaves"))
			addBurnTimeForItem(leave, 5);
		for (ItemStack stick : OreDictionary.getOres("stickWood"))
			addBurnTimeForItem(stick, 2);
		for (ItemStack nugget : OreDictionary.getOres("nuggetIron"))
			addBurnTimeForItem(nugget, 2);
		for (ItemStack nugget : OreDictionary.getOres("nuggetCopper"))
			addBurnTimeForItem(nugget, 2);
		for (ItemStack nugget : OreDictionary.getOres("nuggetTin"))
			addBurnTimeForItem(nugget, 2);
		for (ItemStack nugget : OreDictionary.getOres("nuggetLead"))
			addBurnTimeForItem(nugget, 2);
		for (ItemStack nugget : OreDictionary.getOres("nuggetSilver"))
			addBurnTimeForItem(nugget, 2);

		for (FluidContainerData data : FluidContainerRegistry.getRegisteredFluidContainerData())
			if (data != null)
				if (data.fluid != null && data.fluid.getFluid() == FluidRegistry.LAVA)
					addBurnTimeForItem(data.filledContainer, data.fluid.amount);
	}

	public static void addBurnTimeForItem(ItemStack stack, int burnTime) {
		if (stack != null && !burnTimes.containsKey(stack)) {
			if (stack.stackSize > 1)
				stack.stackSize = 1;
			if (burnTime <= 0)
				return;

			if (!itemIsFuel(stack))
				whiteListItem(stack);

			burnTimes.put(stack, burnTime);
		}
	}

	public static void whiteListItem(ItemStack stack) {
		if (stack != null) {
			if (stack.stackSize > 1)
				stack.stackSize = 1;

			meltingWhiteList.add(stack);
		}
	}

	public static void blackListItem(ItemStack stack) {
		if (stack != null) {
			if (stack.stackSize > 1)
				stack.stackSize = 1;

			meltingBlackList.add(stack);
		}
	}

	public static boolean itemIsFuel(ItemStack stack) {
		if (stack != null) {
			if (meltingWhiteList.contains(stack))
				return true;
			if (meltingBlackList.contains(stack))
				return false;

			if (stack.getItem() instanceof ItemPotion) {
				blackListItem(stack);
				return false;
			}
			if (stack.getItem() instanceof ItemBlock)
				if (stack.itemID < Block.blocksList.length) {
					Material material = Block.blocksList[stack.itemID].blockMaterial;
					if (material == Material.snow || material == Material.craftedSnow || material == Material.ice || material == Material.water || material == Material.vine) {
						blackListItem(stack);
						return false;
					}
				}
			return true;
		}
		return false;
	}

	public static int getItemBurnTime(ItemStack stack) {
		if (burnTimes.containsKey(stack))
			return burnTimes.get(stack);

		if (!itemIsFuel(stack))
			return 0;

		Item item = stack.getItem();
		if (item instanceof ItemBlock) {
			Block block = null;
			if (stack.itemID < Block.blocksList.length)
				block = Block.blocksList[stack.itemID];

			if (block != null)
				if (block instanceof BlockPane)
					return 7;
				else if (block instanceof BlockWood)
					return 5;
				else if (block instanceof BlockStairs)
					return 7;
				else if (block instanceof BlockSapling)
					return 5;
				else if (block instanceof BlockTorch)
					return 5;

			return getBlockVolume(block, stack);
		} else if (item instanceof ItemSeeds || item instanceof ItemFood)
			return 5;
		else if (item instanceof ItemSkull)
			return 32;
		else
			return 16;
	}

	private static int getBlockVolume(Block block, ItemStack stack) {
		if (block == null)
			return 0;

		double maxX = block.getBlockBoundsMaxX();
		double maxY = block.getBlockBoundsMaxY();
		double maxZ = block.getBlockBoundsMaxZ();

		double minX = block.getBlockBoundsMinX();
		double minY = block.getBlockBoundsMinY();
		double minZ = block.getBlockBoundsMinZ();

		double x = maxX - minX;
		double y = maxY - minY;
		double z = maxZ - minZ;

		double volume = 16 * x * y * z;

		if (block.blockHardness > 0.0F)
			if (block.blockHardness <= 1.0F || MinecraftForge.getBlockHarvestLevel(block, stack.getItemDamage(), "pickaxe") > 2)
				volume *= block.blockHardness;

		int intVolume = (int) volume;

		if (intVolume <= 0)
			intVolume = 1;
		addBurnTimeForItem(stack, intVolume);

		return intVolume;
	}
}