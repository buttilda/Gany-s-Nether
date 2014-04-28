package ganymedes01.ganysnether.client.renderer.tileentity;

import ganymedes01.ganysnether.client.model.ModelHorseArmourStand;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.Strings;
import ganymedes01.ganysnether.tileentities.TileEntityHorseArmourStand;

import java.util.HashMap;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.LayeredTexture;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

@SideOnly(Side.CLIENT)
public class TileEntityHorseArmourStandRender extends TileEntitySpecialRenderer {

	private static final String[] names = new String[] { "iron", "gold", "diamond", Utils.getEntityTexture(Strings.Blocks.HORSE_ARMOUR_STAND_NAME) };
	private static final HashMap<Byte, ResourceLocation> textures = new HashMap<Byte, ResourceLocation>();
	private final ModelHorseArmourStand model = new ModelHorseArmourStand();

	public static ResourceLocation getTexture(byte type) {
		ResourceLocation resource = textures.get(type);
		if (resource == null) {
			if (type < 0)
				resource = new ResourceLocation(names[names.length - 1]);
			else {
				resource = new ResourceLocation(Strings.Blocks.HORSE_ARMOUR_STAND_NAME + "_" + names[type]);
				Minecraft.getMinecraft().getTextureManager().loadTexture(resource, new LayeredTexture(new String[] { names[names.length - 1], "textures/entity/horse/armor/horse_armor_" + names[type] + ".png" }));
			}
			textures.put(type, resource);
		}
		return resource;
	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float partialTick) {
		if (tile instanceof TileEntityHorseArmourStand && tile.getBlockMetadata() == 0) {
			TileEntityHorseArmourStand stand = (TileEntityHorseArmourStand) tile;

			GL11.glPushMatrix();
			GL11.glDisable(GL11.GL_CULL_FACE);
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 1.0F);
			GL11.glScalef(1.0F, -1.0F, -1.0F);

			switch (stand.getRotation()) {
				case 1:
					GL11.glRotated(90, 0, 1, 0);
					GL11.glTranslatef(-0.5F, 0.0F, -0.5F);
					break;
				case 2:
					GL11.glRotated(180, 0, 1, 0);
					GL11.glTranslatef(0.0F, 0.0F, -1.0F);
					break;
				case 3:
					GL11.glRotated(270, 0, 1, 0);
					GL11.glTranslatef(0.5F, 0.0F, -0.5F);
					break;
			}
			bindTexture(getTexture(stand.getArmourType()));
			model.renderAll();
			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
			GL11.glEnable(GL11.GL_CULL_FACE);
			GL11.glPopMatrix();
		}
	}
}