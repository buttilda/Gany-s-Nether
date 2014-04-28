package ganymedes01.ganysnether.blocks;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.ModIDs;
import ganymedes01.ganysnether.lib.RenderIDs;
import ganymedes01.ganysnether.lib.Strings;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class FocusedLavaCell extends Block {

	FocusedLavaCell() {
		super(ModIDs.FOCUSED_LAVA_CELL_ID, Material.iron);
		setHardness(2F);
		setLightValue(1F);
		setCreativeTab(GanysNether.netherTab);
		setTextureName(Utils.getBlockTexture(Strings.Blocks.FOCUSED_LAVA_CELL_NAME));
		setUnlocalizedName(Utils.getUnlocalizedName(Strings.Blocks.FOCUSED_LAVA_CELL_NAME));
	}

	@Override
	public int getRenderType() {
		return RenderIDs.FOCUSED_LAVA_CELL;
	}

	public static int getCellCount(World world, int x, int y, int z) {
		int count = 0;
		for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS)
			if (world.getBlockId(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ) == ModBlocks.focusedLavaCell.blockID)
				count++;
		return count;
	}
}