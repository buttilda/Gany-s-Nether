package ganymedes01.ganysnether.items;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class NetherSeeds extends ItemSeeds {

	private int blockType;

	public NetherSeeds(int id, int cropID) {
		super(id, cropID, ModBlocks.tilledNetherrack.blockID);
		blockType = cropID;
		setCreativeTab(GanysNether.netherTab);
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if (side != 1)
			return false;
		else if (player.canPlayerEdit(x, y, z, side, stack) && player.canPlayerEdit(x, y + 1, z, side, stack)) {
			int i1 = world.getBlockId(x, y, z);
			if (i1 == ModBlocks.tilledNetherrack.blockID) {
				Block soil = Block.blocksList[i1];

				if (soil != null && soil.canSustainPlant(world, x, y, z, ForgeDirection.UP, this) && world.isAirBlock(x, y + 1, z)) {
					world.setBlock(x, y + 1, z, blockType);
					--stack.stackSize;
					return true;
				}
			}
		}
		return false;
	}
}
