package ganymedes01.ganysnether.client.renderer.tileentity;

import ganymedes01.ganysnether.client.model.ModelSoulChest;
import ganymedes01.ganysnether.tileentities.TileEntitySoulChest;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
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
public class TileEntitySoulChestRender extends TileEntitySpecialRenderer {

	private final ResourceLocation texture;
	private final ModelSoulChest modelchest = new ModelSoulChest();

	public TileEntitySoulChestRender(ResourceLocation texture) {
		this.texture = texture;
	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float angle) {
		TileEntitySoulChest soulChest = (TileEntitySoulChest) tile;

		bindTexture(texture);

		GL11.glPushMatrix();
		GL11.glTranslatef((float) x, (float) y + 2.0F, (float) z + 1.0F);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);

		short rotation = 0;
		switch (soulChest.getBlockMetadata()) {
			case 2:
				rotation = 180;
				break;
			case 4:
				rotation = 90;
				break;
			case 5:
				rotation = -90;
				break;
		}

		GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
		float lidRotation = 1.0F - (soulChest.prevLidAngle + (soulChest.lidAngle - soulChest.prevLidAngle) * angle);
		lidRotation = 1.0F - lidRotation * lidRotation * lidRotation;
		modelchest.setRotationAngles(lidRotation);
		modelchest.renderAll();
		GL11.glPopMatrix();
	}
}