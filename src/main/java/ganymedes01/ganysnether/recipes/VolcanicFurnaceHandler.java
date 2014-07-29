package ganymedes01.ganysnether.recipes;

import ganymedes01.ganysnether.blocks.ModBlocks;
import ganymedes01.ganysnether.core.utils.UnsizedStack;
import ganymedes01.ganysnether.items.ModItems;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidContainerRegistry.FluidContainerData;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.relauncher.ReflectionHelper;

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
		blackListItem(new ItemStack(Items.experience_bottle));
		blackListItem(new ItemStack(Items.snowball));
		blackListItem(new ItemStack(Blocks.snow));
		blackListItem(new ItemStack(Blocks.ice));
		blackListItem(new ItemStack(Blocks.snow));
		blackListItem(new ItemStack(Blocks.vine));
		blackListItem(new ItemStack(Blocks.cactus));
		blackListItem(new ItemStack(Blocks.packed_ice));

		addBurnTimeForItem(new ItemStack(ModItems.glowingReed), 32);
		addBurnTimeForItem(new ItemStack(ModBlocks.denseLavaCell), 5000);
		addBurnTimeForItem(new ItemStack(ModBlocks.focusedLavaCell), 25000);
		addBurnTimeForItem(new ItemStack(Items.nether_star), 10000000);
		addBurnTimeForItem(new ItemStack(Blocks.fire), 600);
		addBurnTimeForItem(new ItemStack(Blocks.bedrock), 8000);
		addBurnTimeForItem(new ItemStack(Blocks.lava), 1000);
		addBurnTimeForItem(new ItemStack(Blocks.dragon_egg), Integer.MAX_VALUE);
		addBurnTimeForItem(new ItemStack(Items.blaze_powder), 7);
		addBurnTimeForItem(new ItemStack(Blocks.netherrack), 35);
		for (int i = 0; i < 16; i++)
			addBurnTimeForItem(new ItemStack(Items.dye), 7);

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
		addBurnTimeForOre("itemSkull", 32);
		addBurnTimeForOre("mobEgg", 64);
		addBurnTimeForOre("blockSpawner", 256);

		for (FluidContainerData data : FluidContainerRegistry.getRegisteredFluidContainerData())
			if (data != null)
				if (data.fluid != null && data.fluid.getFluid() == FluidRegistry.LAVA)
					addBurnTimeForItem(data.filledContainer, data.fluid.amount);

		try {
			Block.class.getField("blockHardness").setAccessible(true);
		} catch (Exception e) {
		}
	}

	public static void addBurnTimeForItem(ItemStack stack, int burnTime) {
		if (stack != null && !burnTimes.containsKey(new UnsizedStack(stack))) {
			if (burnTime <= 0)
				return;

			if (!isItemMeltable(stack))
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

	public static boolean isItemMeltable(ItemStack stack) {
		if (stack != null) {
			if (meltingWhiteList.contains(new UnsizedStack(stack)))
				return true;
			if (meltingBlackList.contains(new UnsizedStack(stack)))
				return false;

			if (stack.getItem() instanceof ItemPotion) {
				blackListItem(stack);
				return false;
			}
			if (stack.getItem() instanceof ItemBlock) {
				Block block = Block.getBlockFromItem(stack.getItem());
				Material material = block.getMaterial();
				if (material == Material.snow || material == Material.craftedSnow || material == Material.ice || material == Material.water || material == Material.vine) {
					blackListItem(stack);
					return false;
				}

				if (getHardness(block) <= 0.0F) {
					blackListItem(stack);
					return false;
				}
			}
			return true;
		}
		return false;
	}

	private static boolean mapContainsKeys(HashMap<Integer, Integer> map, ItemStack stack) {
		for (int id : OreDictionary.getOreIDs(stack))
			if (map.containsKey(id))
				return true;
		return false;
	}

	private static int getValue(HashMap<Integer, Integer> map, ItemStack stack) {
		for (int id : OreDictionary.getOreIDs(stack)) {
			Integer value = map.get(id);
			if (value != null)
				return value;
		}
		return -1;
	}

	public static int getBurnTime(ItemStack stack) {
		if (burnTimes.containsKey(new UnsizedStack(stack)))
			return burnTimes.get(new UnsizedStack(stack));
		if (mapContainsKeys(oreBurnTimes, stack))
			return getValue(oreBurnTimes, stack);

		if (!isItemMeltable(stack))
			return 0;

		Item item = stack.getItem();
		if (item instanceof ItemBlock) {
			Block block = Block.getBlockFromItem(stack.getItem());

			if (block != null)
				if (block.getMaterial() == Material.wood)
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

		float hardness = getHardness(block);
		if (hardness > 0.0F)
			if (hardness <= 1.0F || block.getHarvestLevel(2) > 2)
				volume *= hardness;

		int intVolume = (int) volume;

		if (intVolume <= 0)
			intVolume = 1;
		addBurnTimeForItem(stack, intVolume);

		return intVolume;
	}

	public static String getUnitParsedValued(long value, String unit, int prefix) {
		float v = value;
		while (v >= 1000) {
			prefix++;
			v /= 1000F;
		}

		return String.format("%,3.1f", v) + " " + prefixes[prefix] + unit;
	}

	private static float getHardness(Block block) {
		Field hardness = ReflectionHelper.findField(Block.class, "field_149782_v", "blockHardness");
		try {
			return hardness.getFloat(block);
		} catch (Exception e) {
			return 0.0F;
		}
	}
}