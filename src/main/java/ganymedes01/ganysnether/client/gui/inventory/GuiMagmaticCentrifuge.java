package ganymedes01.ganysnether.client.gui.inventory;

import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.inventory.ContainerMagmaticCentrifuge;
import ganymedes01.ganysnether.lib.Strings;
import ganymedes01.ganysnether.tileentities.TileEntityMagmaticCentrifuge;
import net.minecraft.entity.player.InventoryPlayer;
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

	private final TileEntityMagmaticCentrifuge centrifuge;
	private int tankXMin, tankYMin, tankXMax, tankYMax;

	public GuiMagmaticCentrifuge(InventoryPlayer inventory, TileEntityMagmaticCentrifuge centrifuge) {
		super(new ContainerMagmaticCentrifuge(inventory, centrifuge));
		this.centrifuge = centrifuge;
		ySize = 238;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String name = StatCollector.translateToLocal(centrifuge.getInventoryName());
		fontRendererObj.drawString(name, xSize / 2 - fontRendererObj.getStringWidth(name) / 2, 6, BLACK);
		fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, BLACK);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int mouseX, int mouseY) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(new ResourceLocation(Utils.getGUITexture(Strings.Blocks.MAGMATIC_CENTRIFUGE_NAME)));
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;

		drawTexturedModalRect(x, y, 0, 0, xSize, ySize);

		ContainerMagmaticCentrifuge container = (ContainerMagmaticCentrifuge) inventorySlots;
		drawTexturedModalRect(x + ((Slot) container.inventorySlots.get(2)).xDisplayPosition - 1, y + ((Slot) container.inventorySlots.get(2)).yDisplayPosition - 1, 176, 3, 18, 18);
		drawTexturedModalRect(x + ((Slot) container.inventorySlots.get(3)).xDisplayPosition - 1, y + ((Slot) container.inventorySlots.get(3)).yDisplayPosition - 1, 176, 3, 18, 18);
		drawFluid(FluidRegistry.LAVA, centrifuge.getScaledFluidAmount(52), x + 155, y + 53, 16, 52);

		mc.renderEngine.bindTexture(new ResourceLocation(Utils.getGUITexture(Strings.Blocks.MAGMATIC_CENTRIFUGE_NAME)));
		drawTexturedModalRect(x + 155, y + 53, 176, 21, 16, 52);
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