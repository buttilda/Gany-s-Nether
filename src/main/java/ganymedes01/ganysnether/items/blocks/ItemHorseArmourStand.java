package ganymedes01.ganysnether.items.blocks;

import ganymedes01.ganysnether.blocks.ModBlocks;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.tileentities.TileEntityHorseArmourStand;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class ItemHorseArmourStand extends ItemBlock {

	public ItemHorseArmourStand(Block block) {
		super(block);
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if (world.isRemote)
			return true;
		if (side != 1)
			return false;
		y++;
		byte angle = (byte) (MathHelper.floor_double(player.rotationYaw * 4.0F / 360.0F + 0.5D) & 3);

		byte X = 0;
		byte Z = 0;

		switch (angle) {
			case 0:
				Z = 1;
				break;
			case 1:
				X = -1;
				break;
			case 2:
				Z = -1;
				break;
			case 3:
				X = 1;
				break;

		}

		if (player.canPlayerEdit(x, y, z, side, stack) && player.canPlayerEdit(x + X, y, z + Z, side, stack) && player.canPlayerEdit(x, y + 1, z, side, stack) && player.canPlayerEdit(x + X, y + 1, z + Z, side, stack)) {
			if (world.isAirBlock(x, y, z) && world.isAirBlock(x + X, y, z + Z) && world.isAirBlock(x, y + 1, z) && world.isAirBlock(x + X, y + 1, z + Z)) {
				world.setBlock(x, y, z, ModBlocks.horseArmourStand);
				world.setBlock(x, y + 1, z, ModBlocks.horseArmourStand, 1, 3);
				world.setBlock(x + X, y, z + Z, ModBlocks.horseArmourStand, angle + 2, 3);
				world.setBlock(x + X, y + 1, z + Z, ModBlocks.horseArmourStand, angle + 6, 3);

				TileEntityHorseArmourStand tile = Utils.getTileEntity(world, x, y, z, TileEntityHorseArmourStand.class);
				if (tile != null)
					tile.setRotation(angle);

				stack.stackSize--;
				return true;
			} else
				return false;
		} else
			return false;
	}
}