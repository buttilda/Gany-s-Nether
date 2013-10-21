package ganymedes01.ganysnether.client.renderer.tileentity;

import ganymedes01.ganysnether.client.model.ModelMagmaticCentrifuge;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.Strings;
import ganymedes01.ganysnether.tileentities.TileEntityMagmaticCentrifuge;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

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
public class TileEntityMagmaticCentrifugeRender extends TileEntitySpecialRenderer {

	private RenderItem customRenderItem;
	private float rotationAngle;

	public TileEntityMagmaticCentrifugeRender() {
		rotationAngle = 0.0F;
		customRenderItem = new RenderItem() {
			@Override
			public boolean shouldBob() {
				return false;
			}

			@Override
			public byte getMiniBlockCount(ItemStack stack) {
				return 1;
			}

			@Override
			public byte getMiniItemCount(ItemStack stack) {
				return 1;
			}
		};
		customRenderItem.setRenderManager(RenderManager.instance);
	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float angle) {
		TileEntityMagmaticCentrifuge centrifuge = (TileEntityMagmaticCentrifuge) tile;
		ModelMagmaticCentrifuge modelCentrifuge = new ModelMagmaticCentrifuge();
		bindTexture(Utils.getResource(Utils.getEntityTexture(Strings.MAGMATIC_CENTRIFUGE_NAME)));

		if (centrifuge.isRecipeValid)
			rotationAngle = (float) (720.0 * (System.currentTimeMillis() & 0x3FFFL) / 0x3FFFL);

		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glTranslatef((float) x, (float) y + 1.5F, (float) z + 1.0F);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		modelCentrifuge.setCoreAngle(rotationAngle);
		modelCentrifuge.renderAll();
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();

		ItemStack material1 = centrifuge.getStackInSlot(TileEntityMagmaticCentrifuge.MATERIAL_SLOT_1);
		ItemStack material2 = centrifuge.getStackInSlot(TileEntityMagmaticCentrifuge.MATERIAL_SLOT_2);
		renderItem(centrifuge.worldObj, x, y, z, material1, 0.4F);
		renderItem(centrifuge.worldObj, x, y, z, material2, -0.4F);
	}

	private void renderItem(World world, double x, double y, double z, ItemStack stack, float offset) {
		GL11.glPushMatrix();
		if (stack != null) {
			float scaleFactor, translate;
			if (stack.getItem() instanceof ItemBlock) {
				scaleFactor = 0.75F;
				translate = 0.5F;
			} else {
				scaleFactor = 0.75F * 0.6F;
				translate = 0.4F;
			}

			EntityItem ghostEntityItem = new EntityItem(world);
			ghostEntityItem.hoverStart = 0.0F;
			ghostEntityItem.setEntityItemStack(stack);
			GL11.glTranslatef((float) x + 0.5F, (float) (y + translate), (float) z + 0.5F);
			GL11.glScalef(scaleFactor, scaleFactor, scaleFactor);
			GL11.glRotatef(rotationAngle, 0.0F, 1.0F, 0.0F);

			customRenderItem.doRenderItem(ghostEntityItem, 0, 0, offset, 0, 0);
		}
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}
}
