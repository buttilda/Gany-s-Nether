package ganymedes01.ganysnether.items;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.IConfigurable;
import ganymedes01.ganysnether.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Gany's Nether
 *
 * @author ganymedes01
 *
 */

public abstract class NetherSeeds extends ItemSeeds implements IConfigurable {

	private final Block blockType;

	NetherSeeds(Block crop) {
		super(crop, ModBlocks.tilledNetherrack);
		blockType = crop;
		setCreativeTab(GanysNether.shouldGenerateCrops ? GanysNether.netherTab : null);
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if (side != 1)
			return false;
		else if (player.canPlayerEdit(x, y, z, side, stack) && player.canPlayerEdit(x, y + 1, z, side, stack)) {
			Block soil = world.getBlock(x, y, z);
			if (soil == ModBlocks.tilledNetherrack)
				if (soil.canSustainPlant(world, x, y, z, ForgeDirection.UP, this) && world.isAirBlock(x, y + 1, z)) {
					world.setBlock(x, y + 1, z, blockType);
					stack.stackSize--;
					return true;
				}
		}
		return false;
	}
}