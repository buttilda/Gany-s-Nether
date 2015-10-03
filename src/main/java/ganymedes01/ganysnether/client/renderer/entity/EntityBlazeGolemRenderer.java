package ganymedes01.ganysnether.client.renderer.entity;

import static net.minecraftforge.client.IItemRenderer.ItemRenderType.EQUIPPED;
import static net.minecraftforge.client.IItemRenderer.ItemRendererHelper.BLOCK_3D;
import ganymedes01.ganysnether.core.utils.Utils;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelSnowMan;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderSnowMan;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EntityBlazeGolemRenderer extends RenderSnowMan {

	private static final ResourceLocation TEXTURE = Utils.getResource("textures/entity/snowman.png");
	private static final ItemStack head = new ItemStack(Blocks.lit_pumpkin);

	@Override
	protected void renderEquippedItems(EntitySnowman entity, float partialTickTime) {
		super.renderEquippedItems(entity, partialTickTime);

		GL11.glPushMatrix();
		((ModelSnowMan) mainModel).head.postRender(0.0625F);

		IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(head, EQUIPPED);
		boolean is3D = customRenderer != null && customRenderer.shouldUseRenderHelper(EQUIPPED, head, BLOCK_3D);

		if (is3D || RenderBlocks.renderItemIn3d(Block.getBlockFromItem(head.getItem()).getRenderType())) {
			float scale = 0.625F;
			GL11.glTranslatef(0.0F, -0.34375F, 0.0F);
			GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
			GL11.glScalef(scale, -scale, scale);
		}

		renderManager.itemRenderer.renderItem(entity, head, 0);
		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return TEXTURE;
	}
}