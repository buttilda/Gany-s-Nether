package ganymedes01.ganysnether.client.gui.inventory;

import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.inventory.ContainerVolcanicFurnace;
import ganymedes01.ganysnether.lib.Strings;
import ganymedes01.ganysnether.tileentities.TileEntityVolcanicFurnace;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fluids.FluidRegistry;

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
public class GuiVolcanicFurnace extends GuiGanysNether {

	private final String FLUID_TO_GO = StatCollector.translateToLocal("fluidtogo") + " ";
	private final TileEntityVolcanicFurnace furnace;
	private int tankXMin, tankYMin, tankXMax, tankYMax;

	public GuiVolcanicFurnace(InventoryPlayer inventory, TileEntityVolcanicFurnace tile) {
		super(new ContainerVolcanicFurnace(inventory, tile));
		furnace = tile;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		fontRenderer.drawString(StatCollector.translateToLocal(furnace.getInvName()), xSize / 2 - fontRenderer.getStringWidth(StatCollector.translateToLocal(furnace.getInvName())) / 2, 6, BLACK);
		fontRenderer.drawString(FLUID_TO_GO, 10, 15, BLACK);
		fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, BLACK);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int mouseX, int mouseY) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(new ResourceLocation(Utils.getGUITexture(Strings.VOLCANIC_FURNACE_NAME)));
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;

		drawTexturedModalRect(x, y, 0, 0, xSize, ySize);

		drawFluid(FluidRegistry.LAVA, furnace.getScaledFluidAmount(52), x + 104, y + 17, 16, 52);
		if (furnace.getMeltTimeRemainingScaled(24) > 0)
			drawTexturedModelRectFromIcon(x + 73, y + 36, FluidRegistry.LAVA.getStillIcon(), 22 - furnace.getMeltTimeRemainingScaled(24), 15);

		mc.renderEngine.bindTexture(new ResourceLocation(Utils.getGUITexture(Strings.VOLCANIC_FURNACE_NAME)));

		drawTexturedModalRect(x + 73, y + 35, 177, 13, 24, 16);
		drawTexturedModalRect(x + 104, y + 17, 176, 31, 16, 52);

		String fluidAmount = Integer.toString(furnace.meltTime) + " mB";
		fontRenderer.drawString(fluidAmount, x + 10 + fontRenderer.getStringWidth(FLUID_TO_GO) / 2 - fontRenderer.getStringWidth(fluidAmount) / 2, y + 25, BLACK);
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float f) {
		super.drawScreen(mouseX, mouseY, f);
		tankXMin = (width - xSize) / 2 + 104;
		tankYMin = (height - ySize) / 2 + 17;
		tankXMax = tankXMin + 16;
		tankYMax = tankYMin + 52;
		if (mouseX >= tankXMin && mouseX <= tankXMax)
			if (mouseY >= tankYMin && mouseY <= tankYMax)
				drawToolTip(mouseX, mouseY, furnace.getFluidAmount() + " mB");
	}
}