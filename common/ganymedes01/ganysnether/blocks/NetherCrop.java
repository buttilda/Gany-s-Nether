package ganymedes01.ganysnether.blocks;

import net.minecraft.block.BlockCrops;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class NetherCrop extends BlockCrops {

	protected NetherCrop(int id) {
		super(id);
	}

	@Override
	protected boolean canThisPlantGrowOnThisBlockID(int id) {
		return id == ModBlocks.tilledNetherrack.blockID;
	}

	@Override
	public void dropBlockAsItemWithChance(World world, int x, int y, int z, int meta, float dropChance, int fortune) {
		super.dropBlockAsItemWithChance(world, x, y, z, meta, dropChance, fortune);
		if (!world.isRemote)
			if (world.rand.nextInt(30) == 15)
				dropBlockAsItem_do(world, x, y, z, new ItemStack(Item.rottenFlesh));
	}

	@Override
	public void fertilize(World world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);

		if (meta < 7)
			world.setBlockMetadataWithNotify(x, y, z, ++meta, 3);
	}
}