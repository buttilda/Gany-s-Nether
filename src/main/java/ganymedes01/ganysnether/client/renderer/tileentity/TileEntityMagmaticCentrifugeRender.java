package ganymedes01.ganysnether.client.renderer.tileentity;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ganymedes01.ganysnether.client.OpenGLHelper;
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
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

/**
 * Gany's Nether
 *
 * @author ganymedes01
 *
 */

@SideOnly(Side.CLIENT)
public class TileEntityMagmaticCentrifugeRender extends TileEntitySpecialRenderer {

	private final RenderItem customRenderItem;
	private final ModelMagmaticCentrifuge modelCentrifuge = new ModelMagmaticCentrifuge();
	private final ResourceLocation texture = Utils.getResource(Utils.getEntityTexture(Strings.Blocks.MAGMATIC_CENTRIFUGE_NAME));

	public TileEntityMagmaticCentrifugeRender() {
		customRenderItem = new RenderItem() {
			@Override
			public boolean shouldBob() {
				return false;
			}

			@Override
			public byte getMiniBlockCount(ItemStack stack, byte original) {
				return 1;
			}

			@Override
			public byte getMiniItemCount(ItemStack stack, byte original) {
				return 1;
			}
		};
		customRenderItem.setRenderManager(RenderManager.instance);
	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float angle) {
		TileEntityMagmaticCentrifuge centrifuge = (TileEntityMagmaticCentrifuge) tile;

		float rotationAngle = centrifuge.getCoreRenderAngle();

		ItemStack material1 = centrifuge.getStackInSlot(TileEntityMagmaticCentrifuge.MATERIAL_SLOT_1);
		ItemStack material2 = centrifuge.getStackInSlot(TileEntityMagmaticCentrifuge.MATERIAL_SLOT_2);
		renderItem(centrifuge.getWorldObj(), x, y, z, material1, rotationAngle, false);
		renderItem(centrifuge.getWorldObj(), x, y, z, material2, rotationAngle, true);

		OpenGLHelper.colour(1.0F, 1.0F, 1.0F, 1.0F);
		bindTexture(texture);
		OpenGLHelper.pushMatrix();
		OpenGLHelper.enableBlend();
		OpenGLHelper.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		OpenGLHelper.translate((float) x, (float) y + 1.5F, (float) z + 1.0F);
		OpenGLHelper.scale(1.0F, -1.0F, -1.0F);
		OpenGLHelper.translate(0.5F, 0.5F, 0.5F);
		OpenGLHelper.rotate(90.0F, 0.0F, 1.0F, 0.0F);
		modelCentrifuge.setCoreAngle(rotationAngle);
		modelCentrifuge.renderAll();
		OpenGLHelper.disableBlend();
		OpenGLHelper.popMatrix();
	}

	private void renderItem(World world, double x, double y, double z, ItemStack centrifugeStack, float rotationAngle, boolean stackOffset) {
		if (centrifugeStack != null) {
			ItemStack stack = centrifugeStack.copy();

			stack.stackSize = 1;
			OpenGLHelper.pushMatrix();
			float scaleFactor, translate, offset = 0.4F;
			if (stack.getItem() instanceof ItemBlock) {
				scaleFactor = 0.75F;
				translate = 0.5F;
			} else {
				scaleFactor = 0.75F * 0.6F;
				translate = 0.4F;
				offset += 0.3F;
			}
			if (stackOffset)
				offset *= -1;

			EntityItem ghostEntityItem = new EntityItem(world);
			ghostEntityItem.hoverStart = 0.0F;
			ghostEntityItem.setEntityItemStack(stack);
			OpenGLHelper.translate(x + 0.5, y + translate, z + 0.5);
			OpenGLHelper.scale(scaleFactor, scaleFactor, scaleFactor);
			OpenGLHelper.rotate(90.0F + rotationAngle, 0.0F, 1.0F, 0.0F);
			OpenGLHelper.translate(0, 0, offset);
			OpenGLHelper.rotate(rotationAngle * 2, 0, 1, 0);
			customRenderItem.doRender(ghostEntityItem, 0, 0, 0, 0, 0);
			OpenGLHelper.enableCull();
			OpenGLHelper.enableLighting();
			OpenGLHelper.popMatrix();
		}
	}
}