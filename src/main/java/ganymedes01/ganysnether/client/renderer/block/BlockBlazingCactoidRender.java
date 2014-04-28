package ganymedes01.ganysnether.client.renderer.block;

import ganymedes01.ganysnether.lib.RenderIDs;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
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
public class BlockBlazingCactoidRender implements ISimpleBlockRenderingHandler {

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess access, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		renderer.renderCrossedSquares(block, x, y, z);
		renderCross(access, x, y, z, block);
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

	private void renderCross(IBlockAccess access, int x, int y, int z, Block block) {
		Tessellator tessellator = Tessellator.instance;
		tessellator.setBrightness(block.getMixedBrightnessForBlock(access, x, y, z));
		int colorMult = block.colorMultiplier(access, x, y, z);
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
		IIcon icon = block.getIcon(0, 0);

		double minU = icon.getMinU();
		double minV = icon.getMinV();
		double maxU = icon.getMaxU();
		double maxV = icon.getMaxV();
		double minX = x + 0.0D;
		double maxX = x + 1.0D;
		double minZ = z + 0.5D;
		double maxZ = z + 0.5D;

		tessellator.addVertexWithUV(minX, y + 1.0F, minZ, minU, minV);
		tessellator.addVertexWithUV(minX, y, minZ, minU, maxV);
		tessellator.addVertexWithUV(maxX, y, maxZ, maxU, maxV);
		tessellator.addVertexWithUV(maxX, y + 1.0F, maxZ, maxU, minV);

		tessellator.addVertexWithUV(maxX, y + 1.0F, maxZ, minU, minV);
		tessellator.addVertexWithUV(maxX, y, maxZ, minU, maxV);
		tessellator.addVertexWithUV(minX, y, minZ, maxU, maxV);
		tessellator.addVertexWithUV(minX, y + 1.0F, minZ, maxU, minV);

		minX = x + 0.5D;
		maxX = x + 0.5D;
		minZ = z + 0.0D;
		maxZ = z + 1.0D;

		tessellator.addVertexWithUV(minX, y + 1.0F, maxZ, minU, minV);
		tessellator.addVertexWithUV(minX, y, maxZ, minU, maxV);
		tessellator.addVertexWithUV(maxX, y, minZ, maxU, maxV);
		tessellator.addVertexWithUV(maxX, y + 1.0F, minZ, maxU, minV);

		tessellator.addVertexWithUV(maxX, y + 1.0F, minZ, minU, minV);
		tessellator.addVertexWithUV(maxX, y, minZ, minU, maxV);
		tessellator.addVertexWithUV(minX, y, maxZ, maxU, maxV);
		tessellator.addVertexWithUV(minX, y + 1.0F, maxZ, maxU, minV);
	}
}