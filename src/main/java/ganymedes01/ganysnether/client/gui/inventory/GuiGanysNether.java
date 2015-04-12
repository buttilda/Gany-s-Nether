package ganymedes01.ganysnether.client.gui.inventory;

import ganymedes01.ganysnether.client.OpenGLHelper;
import ganymedes01.ganysnether.core.utils.Utils;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.inventory.Container;
import net.minecraft.util.IIcon;
import net.minecraftforge.fluids.Fluid;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Gany's Nether
 *
 * @author ganymedes01
 *
 */

@SideOnly(Side.CLIENT)
public abstract class GuiGanysNether extends GuiContainer {

	protected final int BLACK = Utils.getColour(0, 0, 0);

	public GuiGanysNether(Container container) {
		super(container);
	}

	@Override
	protected abstract void drawGuiContainerBackgroundLayer(float f, int mouseX, int mouseY);

	protected void drawToolTip(int mouseX, int mouseY, String text) {
		OpenGLHelper.disableRescaleNormal();
		RenderHelper.disableStandardItemLighting();
		OpenGLHelper.disableLighting();
		OpenGLHelper.disableDepth();
		int k = 0;
		int l = fontRendererObj.getStringWidth(text);

		if (l > k)
			k = l;

		int i1 = mouseX + 12;
		int j1 = mouseY - 12;
		int k1 = 8;

		if (i1 + k > width)
			i1 -= 28 + k;

		if (j1 + k1 + 6 > height)
			j1 = height - k1 - 6;

		zLevel = 300.0F;
		itemRender.zLevel = 300.0F;
		int l1 = -267386864;
		drawGradientRect(i1 - 3, j1 - 4, i1 + k + 3, j1 - 3, l1, l1);
		drawGradientRect(i1 - 3, j1 + k1 + 3, i1 + k + 3, j1 + k1 + 4, l1, l1);
		drawGradientRect(i1 - 3, j1 - 3, i1 + k + 3, j1 + k1 + 3, l1, l1);
		drawGradientRect(i1 - 4, j1 - 3, i1 - 3, j1 + k1 + 3, l1, l1);
		drawGradientRect(i1 + k + 3, j1 - 3, i1 + k + 4, j1 + k1 + 3, l1, l1);
		int i2 = 1347420415;
		int j2 = (i2 & 16711422) >> 1 | i2 & -16777216;
		drawGradientRect(i1 - 3, j1 - 3 + 1, i1 - 3 + 1, j1 + k1 + 3 - 1, i2, j2);
		drawGradientRect(i1 + k + 2, j1 - 3 + 1, i1 + k + 3, j1 + k1 + 3 - 1, i2, j2);
		drawGradientRect(i1 - 3, j1 - 3, i1 + k + 3, j1 - 3 + 1, i2, i2);
		drawGradientRect(i1 - 3, j1 + k1 + 2, i1 + k + 3, j1 + k1 + 3, j2, j2);

		fontRendererObj.drawStringWithShadow(text, i1, j1, -1);

		zLevel = 0.0F;
		itemRender.zLevel = 0.0F;
		OpenGLHelper.enableLighting();
		OpenGLHelper.enableDepth();
		RenderHelper.enableStandardItemLighting();
		OpenGLHelper.enableRescaleNormal();
	}

	protected void drawFluid(Fluid fluid, int level, int x, int y, int width, int height) {
		if (fluid == null)
			return;
		IIcon icon = fluid.getIcon();
		mc.renderEngine.bindTexture(TextureMap.locationBlocksTexture);
		setColour(fluid.getColor());
		int fullX = width / 16;
		int fullY = height / 16;
		int lastX = width - fullX * 16;
		int lastY = height - fullY * 16;
		int fullLvl = (height - level) / 16;
		int lastLvl = height - level - fullLvl * 16;
		for (int i = 0; i < fullX; i++)
			for (int j = 0; j < fullY; j++)
				if (j >= fullLvl)
					drawCutIcon(icon, x + i * 16, y + j * 16, 16, 16, j == fullLvl ? lastLvl : 0);
		for (int i = 0; i < fullX; i++)
			drawCutIcon(icon, x + i * 16, y + fullY * 16, 16, lastY, fullLvl == fullY ? lastLvl : 0);
		for (int i = 0; i < fullY; i++)
			if (i >= fullLvl)
				drawCutIcon(icon, x + fullX * 16, y + i * 16, lastX, 16, i == fullLvl ? lastLvl : 0);
		drawCutIcon(icon, x + fullX * 16, y + fullY * 16, lastX, lastY, fullLvl == fullY ? lastLvl : 0);
	}

	private void drawCutIcon(IIcon icon, int x, int y, int width, int height, int cut) {
		Tessellator tess = Tessellator.instance;
		tess.startDrawingQuads();
		tess.addVertexWithUV(x, y + height, zLevel, icon.getMinU(), icon.getInterpolatedV(height));
		tess.addVertexWithUV(x + width, y + height, zLevel, icon.getInterpolatedU(width), icon.getInterpolatedV(height));
		tess.addVertexWithUV(x + width, y + cut, zLevel, icon.getInterpolatedU(width), icon.getInterpolatedV(cut));
		tess.addVertexWithUV(x, y + cut, zLevel, icon.getMinU(), icon.getInterpolatedV(cut));
		tess.draw();
	}

	protected void setColour(int color) {
		float red = (color >> 16 & 255) / 255.0F;
		float green = (color >> 8 & 255) / 255.0F;
		float blue = (color & 255) / 255.0F;
		OpenGLHelper.colour(red, green, blue, 1.0F);
	}
}