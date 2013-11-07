package ganymedes01.ganysnether.client.gui.inventory;

import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.inventory.ContainerMagmaticCentrifuge;
import ganymedes01.ganysnether.lib.Strings;
import ganymedes01.ganysnether.tileentities.TileEntityMagmaticCentrifuge;
import net.minecraft.inventory.Slot;
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
public class GuiMagmaticCentrifuge extends GuiGanysNether {

	private TileEntityMagmaticCentrifuge centrifuge;
	private ContainerMagmaticCentrifuge container;
	private int tankXMin, tankYMin, tankXMax, tankYMax;

	public GuiMagmaticCentrifuge(ContainerMagmaticCentrifuge container) {
		super(container);
		this.container = container;
		centrifuge = container.getCentrifuge();
		ySize = 238;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		fontRenderer.drawString(StatCollector.translateToLocal(centrifuge.getInvName()), xSize / 2 - fontRenderer.getStringWidth(StatCollector.translateToLocal(centrifuge.getInvName())) / 2, 6, BLACK);
		fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, BLACK);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int mouseX, int mouseY) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(new ResourceLocation(Utils.getGUITexture(Strings.MAGMATIC_CENTRIFUGE_NAME)));
		int j = (width - xSize) / 2;
		int k = (height - ySize) / 2;

		drawTexturedModalRect(j, k, 0, 0, xSize, ySize);

		drawTexturedModalRect(j + ((Slot) container.inventorySlots.get(2)).xDisplayPosition - 1, k + ((Slot) container.inventorySlots.get(2)).yDisplayPosition - 1, 176, 3, 18, 18);
		drawTexturedModalRect(j + ((Slot) container.inventorySlots.get(3)).xDisplayPosition - 1, k + ((Slot) container.inventorySlots.get(3)).yDisplayPosition - 1, 176, 3, 18, 18);
		displayGauge(FluidRegistry.LAVA, j, k, 155, 53, centrifuge.getScaledFluidAmount(52));

		mc.renderEngine.bindTexture(new ResourceLocation(Utils.getGUITexture(Strings.MAGMATIC_CENTRIFUGE_NAME)));
		drawTexturedModalRect(j + 155, k + 53, 176, 21, 16, 52);
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float f) {
		super.drawScreen(mouseX, mouseY, f);
		tankXMin = (width - xSize) / 2 + 156;
		tankYMin = (height - ySize) / 2 + 53;
		tankXMax = tankXMin + 16;
		tankYMax = tankYMin + 52;
		if (mouseX >= tankXMin && mouseX <= tankXMax)
			if (mouseY >= tankYMin && mouseY <= tankYMax)
				drawToolTip(mouseX, mouseY, centrifuge.getFluidAmount() + " mB");
	}
}