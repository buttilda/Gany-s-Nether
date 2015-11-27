package ganymedes01.ganysnether.client.renderer.block;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ganymedes01.ganysnether.lib.RenderIDs;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

@SideOnly(Side.CLIENT)
public class BlockBlazingCactoidRender implements ISimpleBlockRenderingHandler {

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess access, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		renderer.renderCrossedSquares(block, x, y, z);
		renderCross(access, x, y, z, block, renderer);
		renderer.renderBlockFire(Blocks.fire, x, y, z);
		return renderer.renderStandardBlock(block, x, y, z);
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return false;
	}

	@Override
	public int getRenderId() {
		return RenderIDs.BLAZING_CACTOID;
	}

	private void renderCross(IBlockAccess access, int x, int y, int z, Block block, RenderBlocks renderer) {
		Tessellator tessellator = Tessellator.instance;
		tessellator.setBrightness(block.getMixedBrightnessForBlock(access, x, y, z));
		int colour = block.colorMultiplier(access, x, y, z);
		float R = (colour >> 16 & 255) / 255.0F;
		float G = (colour >> 8 & 255) / 255.0F;
		float B = (colour & 255) / 255.0F;

		if (EntityRenderer.anaglyphEnable) {
			float r = (R * 30.0F + G * 59.0F + B * 11.0F) / 100.0F;
			float g = (R * 30.0F + G * 70.0F) / 100.0F;
			float b = (R * 30.0F + B * 70.0F) / 100.0F;
			R = r;
			G = g;
			B = b;
		}

		tessellator.setColorOpaque_F(R, G, B);
		IIcon icon = block.getIcon(0, 0);

		renderer.setRenderBounds(0, 0, 0, 1, 1, 1);
		renderer.renderFaceXNeg(block, x + 0.5F, y, z, icon);
		renderer.renderFaceXPos(block, x - 0.5F, y, z, icon);
		renderer.renderFaceZNeg(block, x, y, z + 0.5F, icon);
		renderer.renderFaceZPos(block, x, y, z - 0.5F, icon);
		renderer.setRenderBoundsFromBlock(block);
	}
}