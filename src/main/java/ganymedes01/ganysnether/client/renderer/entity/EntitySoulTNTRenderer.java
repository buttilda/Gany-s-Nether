package ganymedes01.ganysnether.client.renderer.entity;

import ganymedes01.ganysnether.ModBlocks;
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

	private RenderBlocks blockRenderer = new RenderBlocks();

	public void renderSlowTNT(EntitySlowTNT tnt, double x, double y, double z, float rotation, float scale) {
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x, (float) y, (float) z);
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
			GL11.glScalef(f3, f3, f3);
		}

		f2 = (1.0F - (tnt.getFuse() - scale + 1.0F) / 100.0F) * 0.8F;
		bindEntityTexture(tnt);
		blockRenderer.renderBlockAsItem(ModBlocks.soulTNT, 0, tnt.getBrightness(scale));

		if (tnt.getFuse() / 5 % 2 == 0) {
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glDisable(GL11.GL_LIGHTING);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_DST_ALPHA);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, f2);
			blockRenderer.renderBlockAsItem(ModBlocks.soulTNT, 0, 1.0F);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glEnable(GL11.GL_LIGHTING);
			GL11.glEnable(GL11.GL_TEXTURE_2D);
		}

		GL11.glPopMatrix();
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