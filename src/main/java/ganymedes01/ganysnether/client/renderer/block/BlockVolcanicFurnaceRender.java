package ganymedes01.ganysnether.client.renderer.block;

import ganymedes01.ganysnether.client.renderer.RenderingHelper;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.RenderIDs;
import ganymedes01.ganysnether.tileentities.TileEntityVolcanicFurnace;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Gany's Nether
 *
 * @author ganymedes01
 *
 */

@SideOnly(Side.CLIENT)
public class BlockVolcanicFurnaceRender implements ISimpleBlockRenderingHandler {

	@Override
	public void renderInventoryBlock(Block block, int meta, int modelID, RenderBlocks renderer) {
		RenderingHelper.renderCube(block, 0, renderer);
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess blockAccess, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		TileEntityVolcanicFurnace furnace = Utils.getTileEntity(blockAccess, x, y, z, TileEntityVolcanicFurnace.class);

		if (furnace != null && furnace.hasLava) {
			Tessellator tessellator = Tessellator.instance;
			tessellator.setBrightness(block.getMixedBrightnessForBlock(blockAccess, x, y, z));
			tessellator.setColorOpaque_F(1, 1, 1);
			renderer.renderFaceYPos(Blocks.lava, x, y, z, Blocks.lava.getIcon(0, 0));
		}

		return renderer.renderStandardBlock(block, x, y, z);
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}

	@Override
	public int getRenderId() {
		return RenderIDs.VOLCANIC_FURNACE;
	}
}