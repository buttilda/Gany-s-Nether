package ganymedes01.ganysnether.client.renderer.item;

import ganymedes01.ganysnether.client.model.ModelSoulChest;
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
public class ItemSoulChestRender implements IItemRenderer {

	private final ModelSoulChest modelChest = new ModelSoulChest();
	private final ResourceLocation texture;

	public ItemSoulChestRender(ResourceLocation texture) {
		this.texture = texture;
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
				renderSoulChest(0.5F, 0.5F, 0.5F);
				break;
			}
			case EQUIPPED: {
				renderSoulChest(1.0F, 1.0F, 1.0F);
				break;
			}
			case EQUIPPED_FIRST_PERSON: {
				renderSoulChest(1.0F, 1.0F, 1.0F);
				break;
			}
			case INVENTORY: {
				renderSoulChest(0.0F, 0.075F, 0.0F);
				break;
			}
			default:
				break;
		}
	}

	private void renderSoulChest(float x, float y, float z) {
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(texture);
		GL11.glPushMatrix();
		GL11.glTranslatef(x - 0.5F, y + 0.5F, z - 0.5F);
		GL11.glRotatef(180, 1, 0, 0);
		GL11.glRotatef(-90, 0, 1, 0);
		modelChest.renderAll();
		GL11.glPopMatrix();
	}
}
