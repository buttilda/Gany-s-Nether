package ganymedes01.ganysnether.dispenser;

import ganymedes01.ganysnether.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class DispenserBehaviorWeepingPod extends BehaviorDefaultDispenseItem {

	@Override
	public ItemStack dispenseStack(IBlockSource block, ItemStack stack) {
		World world = block.getWorld();
		EnumFacing enumfacing = BlockDispenser.getFacing(block.getBlockMetadata());
		int x = block.getXInt() + enumfacing.getFrontOffsetX();
		int y = block.getYInt() + enumfacing.getFrontOffsetY();
		int z = block.getZInt() + enumfacing.getFrontOffsetZ();

		if (world.isAirBlock(x, y, z))
			if (world.getBlockId(x + enumfacing.getFrontOffsetX(), y + enumfacing.getFrontOffsetY(), z + enumfacing.getFrontOffsetZ()) == Block.obsidian.blockID)
				plantBlock(world, x, y, z, enumfacing.ordinal());

		return stack;
	}

	public static void plantBlock(World world, int x, int y, int z, int face) {
		int meta = 0;
		switch (face) {
			case 0:
			case 1:
				return;
			case 2:
				meta = 2;
				break;
			case 4:
				meta = 1;
				break;
			case 5:
				meta = 3;
				break;
		}

		world.setBlock(x, y, z, ModBlocks.weepingPod.blockID, meta, 3);
	}
}