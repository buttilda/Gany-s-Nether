package ganymedes01.ganysnether.recipes;

import ganymedes01.ganysnether.core.utils.ItemStackArray;
import ganymedes01.ganysnether.core.utils.ItemStackMap;
import ganymedes01.ganysnether.items.ModItems;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockHalfSlab;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPane;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.BlockWood;
import net.minecraft.block.material.Material;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemSkull;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
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

		addBurnTimeForItem(new ItemStack(Item.netherStar), 10000);
		addBurnTimeForItem(new ItemStack(ModItems.blazeIngot, 1, 2), 2);
		addBurnTimeForItem(new ItemStack(Item.goldNugget), 2);
		addBurnTimeForItem(new ItemStack(ModItems.ironNugget), 2);
		addBurnTimeForItem(new ItemStack(Block.dragonEgg), 1000000);
		addBurnTimeForItem(new ItemStack(Item.blazePowder), 7);
		addBurnTimeForItem(new ItemStack(Block.netherrack), 35);
		for (int i = 0; i < 16; i++) {
			addBurnTimeForItem(new ItemStack(Block.carpet), 12);
			addBurnTimeForItem(new ItemStack(Item.dyePowder), 7);
		}
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

			if (stack.getItem() instanceof ItemPotion)
				return false;
			if (FluidContainerRegistry.isFilledContainer(stack))
				return FluidContainerRegistry.getFluidForFilledItem(stack) != null && FluidContainerRegistry.getFluidForFilledItem(stack).isFluidEqual(new FluidStack(FluidRegistry.LAVA, FluidContainerRegistry.BUCKET_VOLUME));
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

	public static int getItemBurnTime(ItemStack stack) {
		if (burnTimes.containsKey(stack))
			return burnTimes.get(stack);

		if (!itemIsFuel(stack))
			return 0;

		Item item = stack.getItem();
		if (item instanceof ItemSeeds || item instanceof ItemFood)
			return 5;
		else if (item instanceof ItemSkull)
			return 32;
		else if (item.getRarity(stack) == EnumRarity.epic)
			return 50;
		else if (item.getRarity(stack) == EnumRarity.rare)
			return 30;
		else if (item.getRarity(stack) == EnumRarity.uncommon)
			return 24;

		Block block = null;
		if (stack.itemID < Block.blocksList.length)
			block = Block.blocksList[stack.itemID];

		if (stack.getUnlocalizedName().toLowerCase().contains("nugget"))
			return 2;
		else if (block != null)
			if (block instanceof BlockPane)
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

		return 16;
	}
}