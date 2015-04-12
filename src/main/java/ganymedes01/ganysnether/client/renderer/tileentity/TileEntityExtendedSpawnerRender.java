package ganymedes01.ganysnether.client.renderer.tileentity;

import ganymedes01.ganysnether.client.OpenGLHelper;
import ganymedes01.ganysnether.items.SpawnerUpgrade.UpgradeType;
import ganymedes01.ganysnether.tileentities.ExtendedSpawnerLogic;
import ganymedes01.ganysnether.tileentities.TileEntityExtendedSpawner;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Gany's Nether
 *
 * @author ganymedes01
 *
 */

@SideOnly(Side.CLIENT)
public class TileEntityExtendedSpawnerRender extends TileEntitySpecialRenderer {

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float partialTick) {
		OpenGLHelper.pushMatrix();
		OpenGLHelper.translate(x + 0.5F, y, z + 0.5F);
		renderLogic(((TileEntityExtendedSpawner) tile).logic, x, y, z, partialTick);
		OpenGLHelper.popMatrix();
	}

	private void renderLogic(ExtendedSpawnerLogic logic, double x, double y, double z, float partialTick) {
		Entity entity = logic.func_98281_h();

		if (entity != null) {
			entity.setWorld(logic.getSpawnerWorld());
			OpenGLHelper.translate(0.0F, 0.4F, 0.0F);
			OpenGLHelper.rotate((float) (logic.field_98284_d + (logic.field_98287_c - logic.field_98284_d) * partialTick) * 10.0F, 0.0F, 1.0F, 0.0F);
			if (logic.tier != UpgradeType.tierDragonEgg.ordinal()) {
				OpenGLHelper.rotate(-30.0F, 1.0F, 0.0F, 0.0F);
				OpenGLHelper.translate(0.0F, -0.4F, 0.0F);
				OpenGLHelper.scale(0.4375F, 0.4375F, 0.4375F);
			} else {
				((EntityItem) entity).hoverStart = 0.0F;
				OpenGLHelper.translate(0.0F, -0.1F, 0.0F);
				OpenGLHelper.scale(3, 3, 3);
			}
			entity.setLocationAndAngles(x, y, z, 0.0F, 0.0F);
			RenderManager.instance.renderEntityWithPosYaw(entity, 0.0D, 0.0D, 0.0D, 0.0F, partialTick);
		}
	}
}