package ganymedes01.ganysnether.client.renderer.entity;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

@SideOnly(Side.CLIENT)
public class EntityLightningBallRenderer extends Render {

	@Override
	public void doRender(Entity entity, double x, double y, double z, float f, float f1) {

	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return null;
	}
}