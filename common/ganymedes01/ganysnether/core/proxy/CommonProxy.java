package ganymedes01.ganysnether.core.proxy;

import ganymedes01.ganysnether.client.gui.inventory.GuiReproducer;
import ganymedes01.ganysnether.client.gui.inventory.GuiVolcanicFurnace;
import ganymedes01.ganysnether.inventory.ContainerReproducer;
import ganymedes01.ganysnether.inventory.ContainerVolcanicFurnace;
import ganymedes01.ganysnether.lib.GUIsID;
import ganymedes01.ganysnether.lib.Strings;
import ganymedes01.ganysnether.tileentities.TileEntityReproducer;
import ganymedes01.ganysnether.tileentities.TileEntitySoulChest;
import ganymedes01.ganysnether.tileentities.TileEntityVolcanicFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class CommonProxy implements IGuiHandler {

	public void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntitySoulChest.class, Strings.SOUL_CHEST_NAME);
		GameRegistry.registerTileEntity(TileEntityVolcanicFurnace.class, Strings.VOLCANIC_FURNACE_NAME);
		GameRegistry.registerTileEntity(TileEntityReproducer.class, Strings.REPRODUCER_NAME);
	}

	public void registerRenderers() {

	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == GUIsID.VOLCANIC_FURNACE) {
			TileEntityVolcanicFurnace tileVolcanicFurnace = (TileEntityVolcanicFurnace) world.getBlockTileEntity(x, y, z);
			return new ContainerVolcanicFurnace(player.inventory, tileVolcanicFurnace);
		}
		if (ID == GUIsID.REPRODUCER) {
			TileEntityReproducer tileReproducer = (TileEntityReproducer) world.getBlockTileEntity(x, y, z);
			return new ContainerReproducer(player.inventory, tileReproducer);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == GUIsID.VOLCANIC_FURNACE) {
			TileEntityVolcanicFurnace tileVolcanicFurnace = (TileEntityVolcanicFurnace) world.getBlockTileEntity(x, y, z);
			return new GuiVolcanicFurnace(player.inventory, tileVolcanicFurnace);
		}
		if (ID == GUIsID.REPRODUCER) {
			TileEntityReproducer tileReproducer = (TileEntityReproducer) world.getBlockTileEntity(x, y, z);
			return new GuiReproducer(player.inventory, tileReproducer);
		}
		return null;
	}
}
