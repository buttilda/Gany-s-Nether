package ganymedes01.ganysnether.client.renderer.item;

import ganymedes01.ganysnether.blocks.ModBlocks;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

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
public class ItemSoulGlassRender implements IItemRenderer {

	RenderBlocks renderer = new RenderBlocks();

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
				renderBlock(0.0F, 0.0F, 0.0F, item.getItemDamage());
				break;
			}
			case EQUIPPED: {
				renderBlock(0.5F, 0.5F, 0.5F, item.getItemDamage());
				break;
			}
			case EQUIPPED_FIRST_PERSON: {
				renderBlock(0.5F, 0.5F, 0.5F, item.getItemDamage());
				break;
			}
			case INVENTORY: {
				renderBlock(-0.5F, -0.425F, -0.5F, item.getItemDamage());
				break;
			}
			default:
				break;
		}
	}

	private void renderBlock(float x, float y, float z, int meta) {
		GL11.glPushMatrix();
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glTranslatef(x, y, z);
		GL11.glRotatef(180, 1, 0, 0);
		GL11.glRotatef(-90, 0, 1, 0);
		renderer.renderBlockAsItem(ModBlocks.soulGlass, meta, 1.0F);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glPopMatrix();
	}
}