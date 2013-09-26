package ganymedes01.ganysnether.core.proxy;

import ganymedes01.ganysnether.blocks.ModBlocks;
import ganymedes01.ganysnether.client.renderer.item.ItemSoulChestRender;
import ganymedes01.ganysnether.client.renderer.tileentity.TileEntitySoulChestRender;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.Strings;
import ganymedes01.ganysnether.tileentities.TileEntitySoulChest;
import ganymedes01.ganysnether.tileentities.TileEntityUndertaker;
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
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySoulChest.class, new TileEntitySoulChestRender(Utils.getResource(Utils.getEntityTexture(Strings.SOUL_CHEST_NAME))));
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityUndertaker.class, new TileEntitySoulChestRender(Utils.getResource(Utils.getEntityTexture(Strings.UNDERTAKER_NAME))));
	}

	@Override
	public void registerRenderers() {
		MinecraftForgeClient.registerItemRenderer(ModBlocks.soulChest.blockID, new ItemSoulChestRender(Utils.getResource(Utils.getEntityTexture(Strings.SOUL_CHEST_NAME))));
		MinecraftForgeClient.registerItemRenderer(ModBlocks.undertaker.blockID, new ItemSoulChestRender(Utils.getResource(Utils.getEntityTexture(Strings.UNDERTAKER_NAME))));
	}
}