package ganymedes01.ganysnether.client.gui.inventory;

import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.inventory.ContainerThermalSmelter;
import ganymedes01.ganysnether.lib.Strings;
import ganymedes01.ganysnether.tileentities.TileEntityThermalSmelter;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

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
public class GuiThermalSmelter extends GuiGanysNether {

	private final TileEntityThermalSmelter tile;

	public GuiThermalSmelter(InventoryPlayer inventory, TileEntityThermalSmelter tile) {
		super(new ContainerThermalSmelter(inventory, tile));
		this.tile = tile;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String name = StatCollector.translateToLocal(tile.getInventoryName());
		fontRendererObj.drawString(name, xSize / 2 - fontRendererObj.getStringWidth(name) / 2, 6, BLACK);
		fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, BLACK);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int mouseX, int mouseY) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(new ResourceLocation(Utils.getGUITexture(Strings.Blocks.THERMAL_SMELTER_NAME)));
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		drawTexturedModalRect(x, y, 0, 0, xSize, ySize);

		if (tile.speedModifier > 0) {
			int fire = tile.speedModifier * 2;
			drawTexturedModalRect(x + 56, y + 54 + 12 - fire, 176, 12 - fire, 14, fire + 2);
		}

		drawTexturedModalRect(x + 79, y + 34, 176, 14, tile.getCookTime(24) + 1, 16);
	}
}