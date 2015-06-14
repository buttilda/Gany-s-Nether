package ganymedes01.ganysnether.blocks;

import ganymedes01.ganysnether.IConfigurable;
import ganymedes01.ganysnether.ModBlocks;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Gany's Nether
 *
 * @author ganymedes01
 *
 */

public abstract class NetherCrop extends BlockCrops implements IConfigurable {

	@Override
	protected boolean canPlaceBlockOn(Block block) {
		return block == ModBlocks.tilledNetherrack;
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int meta, int fortune) {
		ArrayList<ItemStack> ret = super.getDrops(world, x, y, z, meta, fortune);
		boolean addSeed = true;
		for (ItemStack drop : ret)
			if (drop != null && drop.getItem() == func_149866_i() && drop.stackSize >= 1) {
				addSeed = false;
				break;
			}
		if (addSeed)
			ret.add(new ItemStack(func_149866_i(), 1, 0));

		return ret;
	}

	@Override
	public void dropBlockAsItemWithChance(World world, int x, int y, int z, int meta, float dropChance, int fortune) {
		super.dropBlockAsItemWithChance(world, x, y, z, meta, dropChance, fortune);
		if (!world.isRemote)
			if (world.rand.nextInt(30) == 15)
				dropBlockAsItem(world, x, y, z, new ItemStack(Items.rotten_flesh));
	}

	@Override
	public void func_149863_m(World world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);

		if (meta < 7)
			world.setBlockMetadataWithNotify(x, y, z, ++meta, 3);
	}
}