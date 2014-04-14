package ganymedes01.ganysnether.recipes;

import ganymedes01.ganysnether.blocks.ModBlocks;
import ganymedes01.ganysnether.core.utils.UnsizedStack;
import ganymedes01.ganysnether.items.ModItems;

import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemSeeds;
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

	private static final String[] prefixes = new String[] { "m", "", "k", "M", "G" };

	private static ArrayList<UnsizedStack> meltingBlackList = new ArrayList<UnsizedStack>();
	private static ArrayList<UnsizedStack> meltingWhiteList = new ArrayList<UnsizedStack>();
	private static HashMap<UnsizedStack, Integer> burnTimes = new HashMap<UnsizedStack, Integer>();
	private static HashMap<Integer, Integer> oreBurnTimes = new HashMap<Integer, Integer>();

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
		addBurnTimeForItem(new ItemStack(Item.netherStar), 10000000);
		addBurnTimeForItem(new ItemStack(Block.fire), 600);
		addBurnTimeForItem(new ItemStack(Block.bedrock), 8000);
		addBurnTimeForItem(new ItemStack(Block.lavaMoving), 1000);
		addBurnTimeForItem(new ItemStack(Block.lavaStill), 1000);
		addBurnTimeForItem(new ItemStack(Block.dragonEgg), Integer.MAX_VALUE);
		addBurnTimeForItem(new ItemStack(Item.blazePowder), 7);
		addBurnTimeForItem(new ItemStack(Block.netherrack), 35);
		for (int i = 0; i < 16; i++)
			addBurnTimeForItem(new ItemStack(Item.dyePowder), 7);

		addBurnTimeForOre("plankWood", 5);
		addBurnTimeForOre("slabWood", 2);
		addBurnTimeForOre("stairWood", 7);
		addBurnTimeForOre("treeSapling", 5);
		addBurnTimeForOre("treeLeaves", 2);
		addBurnTimeForOre("stickWood", 2);
		addBurnTimeForOre("nuggetIron", 2);
		addBurnTimeForOre("nuggetCopper", 2);
		addBurnTimeForOre("nuggetTin", 2);
		addBurnTimeForOre("nuggetLead", 2);
		addBurnTimeForOre("nuggetSilver", 2);
		addBurnTimeForOre("nuggetAluminium", 2);
		addBurnTimeForOre("nuggetAluminum", 2);
		addBurnTimeForOre("nuggetGold", 2);
		addBurnTimeForOre("nuggetBlaze", 2);
		addBurnTimeForOre("mobHead", 32);
		addBurnTimeForOre("mobEgg", 64);
		addBurnTimeForOre("blockSpawner", 256);

		for (FluidContainerData data : FluidContainerRegistry.getRegisteredFluidContainerData())
			if (data != null)
				if (data.fluid != null && data.fluid.getFluid() == FluidRegistry.LAVA)
					addBurnTimeForItem(data.filledContainer, data.fluid.amount);
	}

	public static void addBurnTimeForItem(ItemStack stack, int burnTime) {
		if (stack != null && !burnTimes.containsKey(new UnsizedStack(stack))) {
			if (burnTime <= 0)
				return;

			if (!itemIsFuel(stack))
				whiteListItem(stack);

			burnTimes.put(new UnsizedStack(stack), burnTime);
		}
	}

	public static void addBurnTimeForOre(String oreName, int burnTime) {
		if (oreName != null && !oreBurnTimes.containsKey(OreDictionary.getOreID(oreName)))
			oreBurnTimes.put(OreDictionary.getOreID(oreName), burnTime);
	}

	public static void whiteListItem(ItemStack stack) {
		if (stack != null)
			meltingWhiteList.add(new UnsizedStack(stack));
	}

	public static void blackListItem(ItemStack stack) {
		if (stack != null)
			meltingBlackList.add(new UnsizedStack(stack));
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
					if (Block.blocksList[stack.itemID].blockHardness <= 0.0F) {
						blackListItem(stack);
						return false;
					}
				}
			return true;
		}
		return false;
	}

	public static int getBurnTime(ItemStack stack) {
		if (burnTimes.containsKey(new UnsizedStack(stack)))
			return burnTimes.get(new UnsizedStack(stack));
		if (oreBurnTimes.containsKey(OreDictionary.getOreID(stack)))
			return oreBurnTimes.get(OreDictionary.getOreID(stack));

		if (!itemIsFuel(stack))
			return 0;

		Item item = stack.getItem();
		if (item instanceof ItemBlock) {
			Block block = null;
			if (stack.itemID < Block.blocksList.length)
				block = Block.blocksList[stack.itemID];

			if (block != null)
				if (block.blockMaterial == Material.wood)
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

	public static String getUnitParsedValued(long value, String unit, int prefix) {
		prefix++;

		float v = value;
		while (v >= 1000) {
			prefix++;
			v /= 1000F;
		}

		return String.format("%,3.1f", v) + " " + prefixes[prefix] + unit;
	}
}