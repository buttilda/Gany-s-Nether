package ganymedes01.ganysnether.client.renderer.entity;

import ganymedes01.ganysnether.ModBlocks;
import ganymedes01.ganysnether.client.OpenGLHelper;
import ganymedes01.ganysnether.entities.EntitySlowTNT;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Gany's Nether
 *
 * @author ganymedes01
 *
 */

@SideOnly(Side.CLIENT)
public class EntitySoulTNTRenderer extends Render {

	private final RenderBlocks blockRenderer = new RenderBlocks();

	public void renderSlowTNT(EntitySlowTNT tnt, double x, double y, double z, float rotation, float scale) {
		OpenGLHelper.pushMatrix();
		OpenGLHelper.translate((float) x, (float) y, (float) z);
		float f2;

		if (tnt.getFuse() - scale + 1.0F < 10.0F) {
			f2 = 1.0F - (tnt.getFuse() - scale + 1.0F) / 10.0F;

			if (f2 < 0.0F)
				f2 = 0.0F;

			if (f2 > 1.0F)
				f2 = 1.0F;

			f2 *= f2;
			f2 *= f2;
			float f3 = 1.0F + f2 * 0.3F;
			OpenGLHelper.scale(f3, f3, f3);
		}

		f2 = (1.0F - (tnt.getFuse() - scale + 1.0F) / 100.0F) * 0.8F;
		bindEntityTexture(tnt);
		blockRenderer.renderBlockAsItem(ModBlocks.soulTNT, 0, tnt.getBrightness(scale));

		if (tnt.getFuse() / 5 % 2 == 0) {
			OpenGLHelper.disableTexture2D();
			OpenGLHelper.disableLighting();
			OpenGLHelper.enableBlend();
			OpenGLHelper.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_DST_ALPHA);
			OpenGLHelper.colour(1.0F, 1.0F, 1.0F, f2);
			blockRenderer.renderBlockAsItem(ModBlocks.soulTNT, 0, 1.0F);
			OpenGLHelper.colour(1.0F, 1.0F, 1.0F, 1.0F);
			OpenGLHelper.disableBlend();
			OpenGLHelper.enableLighting();
			OpenGLHelper.enableTexture2D();
		}

		OpenGLHelper.popMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return TextureMap.locationBlocksTexture;
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float rotation, float scale) {
		renderSlowTNT((EntitySlowTNT) entity, x, y, z, rotation, scale);
	}
}