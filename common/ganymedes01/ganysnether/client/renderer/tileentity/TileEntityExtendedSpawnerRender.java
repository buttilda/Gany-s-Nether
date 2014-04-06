package ganymedes01.ganysnether.client.renderer.tileentity;

import ganymedes01.ganysnether.tileentities.TileEntityExtendedSpawner;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.MobSpawnerBaseLogic;
import net.minecraft.tileentity.TileEntity;

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
public class TileEntityExtendedSpawnerRender extends TileEntitySpecialRenderer {

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float partialTick) {
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) y, (float) z + 0.5F);
		renderLogic(((TileEntityExtendedSpawner) tile).logic, x, y, z, partialTick);
		GL11.glPopMatrix();
	}

	private void renderLogic(MobSpawnerBaseLogic logic, double x, double y, double z, float partialTick) {
		Entity entity = logic.func_98281_h();

		if (entity != null) {
			entity.setWorld(logic.getSpawnerWorld());
			GL11.glTranslatef(0.0F, 0.4F, 0.0F);
			GL11.glRotatef((float) (logic.field_98284_d + (logic.field_98287_c - logic.field_98284_d) * partialTick) * 10.0F, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(-30.0F, 1.0F, 0.0F, 0.0F);
			GL11.glTranslatef(0.0F, -0.4F, 0.0F);
			GL11.glScalef(0.4375F, 0.4375F, 0.4375F);
			entity.setLocationAndAngles(x, y, z, 0.0F, 0.0F);
			RenderManager.instance.renderEntityWithPosYaw(entity, 0.0D, 0.0D, 0.0D, 0.0F, partialTick);
		}
	}
}