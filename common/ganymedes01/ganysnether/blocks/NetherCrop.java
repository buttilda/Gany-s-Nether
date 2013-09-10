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
	public void dropBlockAsItemWithChance(World world, int x, int y, int z, int meta, float par6, int fortune) {
		super.dropBlockAsItemWithChance(world, x, y, z, meta, par6, fortune);
		if (!world.isRemote)
			if (meta >= 7 && world.rand.nextInt(50) == 0)
				dropBlockAsItem_do(world, x, y, z, new ItemStack(Item.rottenFlesh));
	}
}
