package ganymedes01.ganysnether.blocks;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.ModBlocks;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.RenderIDs;
import ganymedes01.ganysnether.lib.Strings;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Gany's Nether
 *
 * @author ganymedes01
 *
 */

public class FocusedLavaCell extends Block {

	public FocusedLavaCell() {
		super(Material.iron);
		setHardness(2F);
		setLightLevel(1F);
		setCreativeTab(GanysNether.netherTab);
		setBlockName(Utils.getUnlocalizedName(Strings.Blocks.FOCUSED_LAVA_CELL_NAME));
		setBlockTextureName(Utils.getBlockTexture(Strings.Blocks.FOCUSED_LAVA_CELL_NAME));
	}

	@Override
	public int getRenderType() {
		return RenderIDs.FOCUSED_LAVA_CELL;
	}

	public static int getCellCount(World world, int x, int y, int z) {
		int count = 0;
		for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS)
			if (world.getBlock(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ) == ModBlocks.focusedLavaCell)
				count++;
		return count;
	}
}