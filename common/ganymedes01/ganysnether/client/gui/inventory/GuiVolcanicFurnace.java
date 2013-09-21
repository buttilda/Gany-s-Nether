package ganymedes01.ganysnether.client.gui.inventory;

import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.inventory.ContainerVolcanicFurnace;
import ganymedes01.ganysnether.lib.Strings;
import ganymedes01.ganysnether.tileentities.TileEntityVolcanicFurnace;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fluids.FluidRegistry;

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
public class GuiVolcanicFurnace extends GuiContainer {

	private String FLUID_TO_GO = StatCollector.translateToLocal("fluidtogo") + " ";
	private TileEntityVolcanicFurnace furnace;
	private static final ResourceLocation BLOCK_TEXTURE = TextureMap.locationBlocksTexture;
	private int tankXMin, tankYMin, tankXMax, tankYMax;

	public GuiVolcanicFurnace(InventoryPlayer inventory, TileEntityVolcanicFurnace tile) {
		super(new ContainerVolcanicFurnace(inventory, tile));
		furnace = tile;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		fontRenderer.drawString(StatCollector.translateToLocal(furnace.getInvName()), xSize / 2 - fontRenderer.getStringWidth(StatCollector.translateToLocal(furnace.getInvName())) / 2, 6, 4210752);
		fontRenderer.drawString(FLUID_TO_GO, 10, 15, 4210752);
		fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int mouseX, int mouseY) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(new ResourceLocation(Utils.getGUITexture(Strings.VOLCANIC_FURNACE_NAME)));
		int j = (width - xSize) / 2;
		int k = (height - ySize) / 2;

		drawTexturedModalRect(j, k, 0, 0, xSize, ySize);
		if (furnace.getMeltTimeRemainingScaled(24) > 0)
			drawTexturedModalRect(j + 73, k + 35, 177, 13, 24 - furnace.getMeltTimeRemainingScaled(24), 16);
		displayGauge(j, k, 11, 104, furnace.getScaledFluidAmount(52));
		String fluidAmount = Integer.toString(furnace.meltTime) + " mB";
		fontRenderer.drawString(fluidAmount, j + 10 + (fontRenderer.getStringWidth(FLUID_TO_GO) / 2) - fontRenderer.getStringWidth(fluidAmount) / 2, k + 25, 4210752);
	}

	public void drawScreen(int mouseX, int mouseY, float f) {
		super.drawScreen(mouseX, mouseY, f);
		tankXMin = ((width - xSize) / 2) + 104;
		tankYMin = ((height - ySize) / 2) + 17;
		tankXMax = tankXMin + 16;
		tankYMax = tankYMin + 52;
		if (mouseX >= tankXMin && mouseX <= tankXMax)
			if (mouseY >= tankYMin && mouseY <= tankYMax)
				drawToolTip(mouseX, mouseY, furnace.getFluidAmount() + " mB");
	}

	private void drawToolTip(int mouseX, int mouseY, String text) {
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		RenderHelper.disableStandardItemLighting();
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		int k = 0;
		int l = fontRenderer.getStringWidth(text);

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
		itemRenderer.zLevel = 300.0F;
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

		fontRenderer.drawStringWithShadow(text, i1, j1, -1);

		zLevel = 0.0F;
		itemRenderer.zLevel = 0.0F;
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		RenderHelper.enableStandardItemLighting();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
	}

	private void displayGauge(int j, int k, int line, int col, int scaled) {
		int start = 0;
		mc.renderEngine.bindTexture(BLOCK_TEXTURE);
		while (true) {
			int x;
			if (scaled > 16) {
				x = 16;
				scaled -= 16;
			} else {
				x = scaled;
				scaled = 0;
			}
			drawTexturedModelRectFromIcon(j + col, k + line + 58 - x - start, FluidRegistry.LAVA.getStillIcon(), 16, 16 - (16 - x));
			start += 16;
			if (x == 0 || scaled == 0)
				break;
		}
	}
}
