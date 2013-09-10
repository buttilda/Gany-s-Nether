package ganymedes01.ganysnether.client.gui.inventory;

import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.inventory.ContainerVolcanicFurnace;
import ganymedes01.ganysnether.lib.Strings;
import ganymedes01.ganysnether.tileentities.TileEntityVolcanicFurnace;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fluids.FluidRegistry;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiVolcanicFurnace extends GuiContainer {

	private String FLUID_TO_GO = StatCollector.translateToLocal("fluidtogo") + " ";
	private String FLUID_IN_TANK = StatCollector.translateToLocal("intank") + " ";
	private TileEntityVolcanicFurnace furnace;
	private static final ResourceLocation BLOCK_TEXTURE = TextureMap.locationBlocksTexture;

	public GuiVolcanicFurnace(InventoryPlayer inventory, TileEntityVolcanicFurnace tile) {
		super(new ContainerVolcanicFurnace(inventory, tile));
		furnace = tile;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		fontRenderer.drawString(StatCollector.translateToLocal(furnace.getInvName()), xSize / 2 - fontRenderer.getStringWidth(StatCollector.translateToLocal(furnace.getInvName())) / 2, 6, 4210752);
		fontRenderer.drawString(FLUID_TO_GO, 10, 15, 4210752);
		fontRenderer.drawString(FLUID_IN_TANK, 80, ySize - 96 + 2, 4210752);
		fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(new ResourceLocation(Utils.getGUITexture(Strings.VOLCANIC_FURNACE_NAME)));
		int j = (width - xSize) / 2;
		int k = (height - ySize) / 2;

		drawTexturedModalRect(j, k, 0, 0, xSize, ySize);
		if (furnace.getMeltTimeRemainingScaled(24) > 0)
			drawTexturedModalRect(j + 73, k + 35, 177, 13, 24 - furnace.getMeltTimeRemainingScaled(24), 16);
		displayGauge(j, k, 11, 104, furnace.getScaledFluidAmount(52));
		String fluidAmount = Integer.toString(furnace.meltTime) + " mB";
		String fluidInTank = Integer.toString(furnace.getFluidAmount()) + " mB";
		fontRenderer.drawString(fluidAmount, j + 10 + (fontRenderer.getStringWidth(FLUID_TO_GO) / 2) - fontRenderer.getStringWidth(fluidAmount) / 2, k + 25, 4210752);
		fontRenderer.drawString(fluidInTank, j + 80 + (fontRenderer.getStringWidth(FLUID_IN_TANK)), k + 72, 4210752);
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
