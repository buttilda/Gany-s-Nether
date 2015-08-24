package ganymedes01.ganysnether.client.gui.inventory;

import ganymedes01.ganysnether.client.OpenGLHelper;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.inventory.ContainerUndertaker;
import ganymedes01.ganysnether.lib.Strings;
import ganymedes01.ganysnether.tileentities.TileEntityUndertaker;
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
public class GuiUndertaker extends GuiGanysNether {

	private final ResourceLocation TEXTURE = Utils.getResource(Utils.getGUITexture(Strings.Blocks.UNDERTAKER_NAME));
	private final TileEntityUndertaker undertaker;

	public GuiUndertaker(InventoryPlayer inventory, TileEntityUndertaker tileUndertaker) {
		super(new ContainerUndertaker(inventory, tileUndertaker));
		ySize = 205;
		undertaker = tileUndertaker;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String name = StatCollector.translateToLocal(undertaker.getInventoryName());
		fontRendererObj.drawString(name, xSize / 2 - fontRendererObj.getStringWidth(name) / 2, 6, BLACK);
		fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, BLACK);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int mouseX, int mouseY) {
		OpenGLHelper.colour(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(TEXTURE);
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
	}
}