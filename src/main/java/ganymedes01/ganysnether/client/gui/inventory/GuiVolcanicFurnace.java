package ganymedes01.ganysnether.client.gui.inventory;

import ganymedes01.ganysnether.client.OpenGLHelper;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.inventory.ContainerVolcanicFurnace;
import ganymedes01.ganysnether.lib.Strings;
import ganymedes01.ganysnether.recipes.VolcanicFurnaceHandler;
import ganymedes01.ganysnether.tileentities.TileEntityVolcanicFurnace;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fluids.FluidRegistry;
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

	private static final ResourceLocation TEXTURE = Utils.getResource(Utils.getGUITexture(Strings.Blocks.VOLCANIC_FURNACE_NAME));
	private final String FLUID_TO_GO = StatCollector.translateToLocal("string.ganysnether.fluidtogo") + " ";
	private final TileEntityVolcanicFurnace furnace;
	private int tankXMin, tankYMin, tankXMax, tankYMax;

	public GuiVolcanicFurnace(InventoryPlayer inventory, TileEntityVolcanicFurnace tile) {
		super(new ContainerVolcanicFurnace(inventory, tile));
		furnace = tile;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String name = StatCollector.translateToLocal(furnace.getInventoryName());
		fontRendererObj.drawString(name, xSize / 2 - fontRendererObj.getStringWidth(name) / 2, 6, BLACK);
		fontRendererObj.drawString(FLUID_TO_GO, 10, 15, BLACK);
		fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, BLACK);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int mouseX, int mouseY) {
		OpenGLHelper.colour(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(TEXTURE);
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;

		drawTexturedModalRect(x, y, 0, 0, xSize, ySize);

		drawFluid(FluidRegistry.LAVA, furnace.getScaledFluidAmount(52), x + 104, y + 17, 16, 52);
		if (furnace.getMeltTimeRemainingScaled(24) > 0)
			drawTexturedModelRectFromIcon(x + 73, y + 36, FluidRegistry.LAVA.getStillIcon(), 22 - furnace.getMeltTimeRemainingScaled(24), 15);

		mc.renderEngine.bindTexture(TEXTURE);

		drawTexturedModalRect(x + 73, y + 35, 177, 13, 24, 16);
		drawTexturedModalRect(x + 104, y + 17, 176, 31, 16, 52);

		String fluidAmount = VolcanicFurnaceHandler.getUnitParsedValued(furnace.meltTime, "B", 0);
		fontRendererObj.drawString(fluidAmount, x + 10 + fontRendererObj.getStringWidth(FLUID_TO_GO) / 2 - fontRendererObj.getStringWidth(fluidAmount) / 2, y + 25, BLACK);
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