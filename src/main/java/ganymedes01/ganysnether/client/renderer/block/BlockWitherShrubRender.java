package ganymedes01.ganysnether.client.renderer.block;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ganymedes01.ganysnether.lib.RenderIDs;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

@SideOnly(Side.CLIENT)
public class BlockWitherShrubRender implements ISimpleBlockRenderingHandler {

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess access, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
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

		int meta = access.getBlockMetadata(x, y, z);
		if (meta > 7)
			meta = 7;

		tessellator.setColorOpaque_F(R, G, B);
		IIcon icon = block.getIcon(0, meta);

		float newY = y - (1.0F - (meta * 2 + 2) / 16.0F);
		renderer.setRenderBounds(0, 0, 0, 1, 1, 1);
		renderer.renderFaceXNeg(block, x + 0.5F, newY, z, icon);
		renderer.renderFaceXPos(block, x - 0.5F, newY, z, icon);
		renderer.renderFaceZNeg(block, x, newY, z + 0.5F, icon);
		renderer.renderFaceZPos(block, x, newY, z - 0.5F, icon);

		return true;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}

	@Override
	public int getRenderId() {
		return RenderIDs.WITHER_SHRUB;
	}
}