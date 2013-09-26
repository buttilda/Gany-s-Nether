package ganymedes01.ganysnether.core.proxy;

import ganymedes01.ganysnether.client.gui.inventory.GuiReproducer;
import ganymedes01.ganysnether.client.gui.inventory.GuiUndertaker;
import ganymedes01.ganysnether.client.gui.inventory.GuiVolcanicFurnace;
import ganymedes01.ganysnether.inventory.ContainerReproducer;
import ganymedes01.ganysnether.inventory.ContainerUndertaker;
import ganymedes01.ganysnether.inventory.ContainerVolcanicFurnace;
import ganymedes01.ganysnether.lib.GUIsID;
import ganymedes01.ganysnether.lib.Strings;
import ganymedes01.ganysnether.tileentities.TileEntityReproducer;
import ganymedes01.ganysnether.tileentities.TileEntitySoulChest;
import ganymedes01.ganysnether.tileentities.TileEntityUndertaker;
import ganymedes01.ganysnether.tileentities.TileEntityVolcanicFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
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
		GameRegistry.registerTileEntity(TileEntityUndertaker.class, Strings.UNDERTAKER_NAME);
	}

	public void registerRenderers() {

	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tile = world.getBlockTileEntity(x, y, z);
		switch (ID) {
			case (GUIsID.VOLCANIC_FURNACE):
				TileEntityVolcanicFurnace tileVolcanicFurnace = (TileEntityVolcanicFurnace) tile;
				return new ContainerVolcanicFurnace(player.inventory, tileVolcanicFurnace);
			case GUIsID.REPRODUCER:
				TileEntityReproducer tileReproducer = (TileEntityReproducer) tile;
				return new ContainerReproducer(player.inventory, tileReproducer);
			case GUIsID.UNDERTAKER:
				TileEntityUndertaker tileUndertaker = (TileEntityUndertaker) tile;
				return new ContainerUndertaker(player.inventory, tileUndertaker);
			default:
				return null;
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tile = world.getBlockTileEntity(x, y, z);
		switch (ID) {
			case (GUIsID.VOLCANIC_FURNACE):
				TileEntityVolcanicFurnace tileVolcanicFurnace = (TileEntityVolcanicFurnace) tile;
				return new GuiVolcanicFurnace(player.inventory, tileVolcanicFurnace);
			case GUIsID.REPRODUCER:
				TileEntityReproducer tileReproducer = (TileEntityReproducer) tile;
				return new GuiReproducer(player.inventory, tileReproducer);
			case GUIsID.UNDERTAKER:
				TileEntityUndertaker tileUndertaker = (TileEntityUndertaker) tile;
				return new GuiUndertaker(player.inventory, tileUndertaker);
			default:
				return null;
		}
	}
}
