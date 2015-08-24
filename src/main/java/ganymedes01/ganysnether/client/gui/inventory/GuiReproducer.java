package ganymedes01.ganysnether.client.gui.inventory;

import ganymedes01.ganysnether.client.OpenGLHelper;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.inventory.ContainerReproducer;
import ganymedes01.ganysnether.lib.Strings;
import ganymedes01.ganysnether.tileentities.TileEntityReproducer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Gany's Nether
 *
 * @author ganymedes01
 *
 */

@SideOnly(Side.CLIENT)
public class GuiReproducer extends GuiGanysNether {

	private final ResourceLocation TEXTURE = Utils.getResource(Utils.getGUITexture(Strings.Blocks.REPRODUCER_NAME));
	private final TileEntityReproducer reproducer;

	public GuiReproducer(InventoryPlayer inventory, TileEntityReproducer tile) {
		super(new ContainerReproducer(inventory, tile));
		reproducer = tile;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String name = StatCollector.translateToLocal(reproducer.getInventoryName());
		fontRendererObj.drawString(name, xSize / 2 - fontRendererObj.getStringWidth(name) / 2, 6, BLACK);
		fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, BLACK);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int mouseX, int mouseY) {
		OpenGLHelper.colour(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(TEXTURE);
		int j = (width - xSize) / 2;
		int k = (height - ySize) / 2;
		drawTexturedModalRect(j, k, 0, 0, xSize, ySize);
		drawTexturedModalRect(j + 103, k + 35, 181, 14, 13 - reproducer.getScaledWorkTime(13), 16);
	}
}
