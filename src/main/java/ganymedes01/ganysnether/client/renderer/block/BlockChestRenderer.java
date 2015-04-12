package ganymedes01.ganysnether.client.renderer.block;

import ganymedes01.ganysnether.ModBlocks;
import ganymedes01.ganysnether.client.OpenGLHelper;
import ganymedes01.ganysnether.client.model.ModelSoulChest;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.RenderIDs;
import ganymedes01.ganysnether.lib.Strings;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.FMLClientHandler;
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
public class BlockChestRenderer implements ISimpleBlockRenderingHandler {

	private final ModelSoulChest chest = new ModelSoulChest();
	private final ResourceLocation SOUL_CHEST = Utils.getResource(Utils.getEntityTexture(Strings.Blocks.SOUL_CHEST_NAME));
	private final ResourceLocation UNDERTAKER = Utils.getResource(Utils.getEntityTexture(Strings.Blocks.UNDERTAKER_NAME));

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		OpenGLHelper.pushMatrix();
		OpenGLHelper.rotate(90, 0, 1, 0);

		renderer.setRenderBounds(1F / 16F, 0, 1F / 16F, 15F / 16F, 10F / 16F, 15F / 16F);
		BlockRendererHelper.renderSimpleBlock(block, metadata, renderer);

		OpenGLHelper.pushMatrix();
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(block == ModBlocks.soulChest ? SOUL_CHEST : UNDERTAKER);
		OpenGLHelper.translate(0.0F, 1.0F, 0.0F);
		OpenGLHelper.scale(1, -1, -1);
		chest.renderAll();
		OpenGLHelper.popMatrix();

		OpenGLHelper.popMatrix();
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		if (renderer.hasOverrideBlockTexture())
			renderer.setRenderBounds(1F / 16F, 0, 1F / 16F, 15F / 16F, 14F / 16F, 15F / 16F);
		else
			renderer.setRenderBounds(1F / 16F, 0, 1F / 16F, 15F / 16F, 10F / 16F, 15F / 16F);

		return renderer.renderStandardBlock(block, x, y, z);
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}

	@Override
	public int getRenderId() {
		return RenderIDs.SOUL_CHEST;
	}
}