package ganymedes01.ganysnether.client.renderer.item;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ganymedes01.ganysnether.client.OpenGLHelper;
import ganymedes01.ganysnether.client.model.ModelHorseArmourStand;
import ganymedes01.ganysnether.client.renderer.tileentity.TileEntityHorseArmourStandRender;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

/**
 * Gany's Nether
 *
 * @author ganymedes01
 *
 */

@SideOnly(Side.CLIENT)
public class ItemHorseArmourStandRender implements IItemRenderer {

	private final ModelHorseArmourStand model = new ModelHorseArmourStand();

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return type != ItemRenderType.FIRST_PERSON_MAP;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack stack, Object... data) {
		OpenGLHelper.pushMatrix();
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(TileEntityHorseArmourStandRender.getTexture((byte) -1));

		switch (type) {
			case ENTITY: {
				OpenGLHelper.rotate(90, 0, 1, 0);
				render(0.0F, 0.5F, 0.0F);
				break;
			}
			case EQUIPPED: {
				render(0.5F, 1.0F, 0.5F);
				break;
			}
			case EQUIPPED_FIRST_PERSON: {
				OpenGLHelper.rotate(180, 0, 1, 0);
				OpenGLHelper.rotate(-45, 1, 0, 0);
				render(0.25F, 1.5F, 0.25F);
				break;
			}
			case INVENTORY: {
				OpenGLHelper.rotate(180, 0, 1, 0);
				OpenGLHelper.scale(0.9, 0.9, 0.9);
				render(-0.5F, 1.0F, -0.5F);
				break;
			}
			default:
				break;
		}
		OpenGLHelper.popMatrix();
	}

	private void render(float x, float y, float z) {
		OpenGLHelper.translate(x, y, z);
		OpenGLHelper.rotate(180, 1, 0, 0);
		OpenGLHelper.rotate(180, 0, 1, 0);
		OpenGLHelper.scale(0.7, 0.7, 0.7);
		model.renderAll();
	}
}