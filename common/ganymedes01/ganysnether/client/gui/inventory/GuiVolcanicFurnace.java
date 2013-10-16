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

	private String FLUID_TO_GO = StatCollector.translateToLocal("fluidtogo") + " ";
	private TileEntityVolcanicFurnace furnace;
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
		int j = (width - xSize) / 2;
		int k = (height - ySize) / 2;

		drawTexturedModalRect(j, k, 0, 0, xSize, ySize);
		if (furnace.getMeltTimeRemainingScaled(24) > 0)
			drawTexturedModalRect(j + 73, k + 35, 177, 13, 24 - furnace.getMeltTimeRemainingScaled(24), 16);
		displayGauge(FluidRegistry.LAVA, j, k, 104, 17, furnace.getScaledFluidAmount(52));
		String fluidAmount = Integer.toString(furnace.meltTime) + " mB";
		fontRenderer.drawString(fluidAmount, j + 10 + fontRenderer.getStringWidth(FLUID_TO_GO) / 2 - fontRenderer.getStringWidth(fluidAmount) / 2, k + 25, BLACK);
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