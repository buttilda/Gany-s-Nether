package ganymedes01.ganysnether.client.renderer.item;

import ganymedes01.ganysnether.client.OpenGLHelper;
import ganymedes01.ganysnether.client.model.ModelMagmaticCentrifuge;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.Strings;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Gany's Nether
 *
 * @author ganymedes01
 *
 */

@SideOnly(Side.CLIENT)
public class ItemMagmaticCentrifugeRender implements IItemRenderer {

	private final ModelMagmaticCentrifuge model;
	private final ResourceLocation texture = Utils.getResource(Utils.getEntityTexture(Strings.Blocks.MAGMATIC_CENTRIFUGE_NAME));

	public ItemMagmaticCentrifugeRender() {
		model = new ModelMagmaticCentrifuge();
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return type != ItemRenderType.FIRST_PERSON_MAP;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		switch (type) {
			case ENTITY: {
				renderCentrifuge(0.0F, 0.5F, 0.0F);
				break;
			}
			case EQUIPPED: {
				renderCentrifuge(0.5F, 1.0F, 0.5F);
				break;
			}
			case EQUIPPED_FIRST_PERSON: {
				renderCentrifuge(0.5F, 1.0F, 0.5F);
				break;
			}
			case INVENTORY: {
				renderCentrifuge(-0.5F, 0.075F, -0.5F);
				break;
			}
			default:
				break;
		}
	}

	private void renderCentrifuge(float x, float y, float z) {
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(texture);
		OpenGLHelper.pushMatrix();
		OpenGLHelper.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		OpenGLHelper.enableBlend();
		OpenGLHelper.translate(x, y, z);
		OpenGLHelper.rotate(180, 1, 0, 0);
		OpenGLHelper.rotate(90, 0, 1, 0);
		model.renderAll();
		OpenGLHelper.disableBlend();
		OpenGLHelper.popMatrix();
	}
}