package ganymedes01.ganysnether.core.proxy;

import ganymedes01.ganysnether.blocks.ModBlocks;
import ganymedes01.ganysnether.client.renderer.item.ItemSoulChestRender;
import ganymedes01.ganysnether.client.renderer.tileentity.TileEntitySoulChestRender;
import ganymedes01.ganysnether.tileentities.TileEntitySoulChest;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.ClientRegistry;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class ClientProxy extends CommonProxy {

	@Override
	public void registerTileEntities() {
		super.registerTileEntities();
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySoulChest.class, new TileEntitySoulChestRender());
	}

	@Override
	public void registerRenderers() {
		MinecraftForgeClient.registerItemRenderer(ModBlocks.soulChest.blockID, new ItemSoulChestRender());
	}
}
