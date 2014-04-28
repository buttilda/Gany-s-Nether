package ganymedes01.ganysnether.client.renderer.block;

import ganymedes01.ganysnether.client.renderer.RenderingHelper;
import ganymedes01.ganysnether.lib.RenderIDs;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
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
public class BlockFocusedLavaCellRender implements ISimpleBlockRenderingHandler {

	@Override
	public void renderInventoryBlock(Block block, int meta, int modelID, RenderBlocks renderer) {
		RenderingHelper.renderCube(Block.lavaStill, meta, renderer);
		RenderingHelper.renderCube(block, meta, renderer);
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess blockAccess, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		Tessellator tessellator = Tessellator.instance;
		tessellator.setBrightness(block.getMixedBrightnessForBlock(blockAccess, x, y, z));
		int colorMult = block.colorMultiplier(blockAccess, x, y, z);
		float R = (colorMult >> 16 & 255) / 255.0F;
		float G = (colorMult >> 8 & 255) / 255.0F;
		float B = (colorMult & 255) / 255.0F;

		if (EntityRenderer.anaglyphEnable) {
			float r = (R * 30.0F + G * 59.0F + B * 11.0F) / 100.0F;
			float g = (R * 30.0F + G * 70.0F) / 100.0F;
			float b = (R * 30.0F + B * 70.0F) / 100.0F;
			R = r;
			G = g;
			B = b;
		}

		tessellator.setColorOpaque_F(R, G, B);
		renderer.renderFaceYNeg(Block.lavaStill, x, y, z, Block.lavaStill.getIcon(0, 0));
		renderer.renderFaceYPos(Block.lavaStill, x, y, z, Block.lavaStill.getIcon(0, 0));
		renderer.renderFaceXNeg(Block.lavaStill, x, y, z, Block.lavaStill.getIcon(0, 0));
		renderer.renderFaceXPos(Block.lavaStill, x, y, z, Block.lavaStill.getIcon(0, 0));
		renderer.renderFaceZNeg(Block.lavaStill, x, y, z, Block.lavaStill.getIcon(0, 0));
		renderer.renderFaceZPos(Block.lavaStill, x, y, z, Block.lavaStill.getIcon(0, 0));

		return renderer.renderStandardBlock(block, x, y, z);
	}

	@Override
	public boolean shouldRender3DInInventory() {
		return true;
	}

	@Override
	public int getRenderId() {
		return RenderIDs.FOCUSED_LAVA_CELL;
	}
}