package ganymedes01.ganysnether.core.proxy;

import ganymedes01.ganysnether.blocks.ModBlocks;
import ganymedes01.ganysnether.client.renderer.block.BlockWitherShrubRender;
import ganymedes01.ganysnether.client.renderer.entity.EntityLightningBallRenderer;
import ganymedes01.ganysnether.client.renderer.item.ItemSoulChestRender;
import ganymedes01.ganysnether.client.renderer.tileentity.TileEntityMagmaticCentrifugeRender;
import ganymedes01.ganysnether.client.renderer.tileentity.TileEntitySoulChestRender;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.entities.EntityLightningBall;
import ganymedes01.ganysnether.lib.Strings;
import ganymedes01.ganysnether.tileentities.TileEntityMagmaticCentrifuge;
import ganymedes01.ganysnether.tileentities.TileEntitySoulChest;
import ganymedes01.ganysnether.tileentities.TileEntityUndertaker;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

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
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMagmaticCentrifuge.class, new TileEntityMagmaticCentrifugeRender());
	}

	@Override
	public void registerRenderers() {
		MinecraftForgeClient.registerItemRenderer(ModBlocks.soulChest.blockID, new ItemSoulChestRender(Utils.getResource(Utils.getEntityTexture(Strings.SOUL_CHEST_NAME))));
		MinecraftForgeClient.registerItemRenderer(ModBlocks.undertaker.blockID, new ItemSoulChestRender(Utils.getResource(Utils.getEntityTexture(Strings.UNDERTAKER_NAME))));

		RenderingRegistry.registerBlockHandler(ganymedes01.ganysnether.lib.RenderIDs.WITHER_SHRUB, new BlockWitherShrubRender());

		RenderingRegistry.registerEntityRenderingHandler(EntityLightningBall.class, new EntityLightningBallRenderer());
	}

	@Override
	public void handleTileMagmaticCentrifugePacket(int x, int y, int z, ItemStack material1, ItemStack material2, boolean isRecipeValid) {
		World world = FMLClientHandler.instance().getClient().theWorld;
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		if (tileEntity != null)
			if (tileEntity instanceof TileEntityMagmaticCentrifuge) {
				((TileEntityMagmaticCentrifuge) tileEntity).setInventorySlotContents(TileEntityMagmaticCentrifuge.MATERIAL_SLOT_1, material1);
				((TileEntityMagmaticCentrifuge) tileEntity).setInventorySlotContents(TileEntityMagmaticCentrifuge.MATERIAL_SLOT_2, material2);
				((TileEntityMagmaticCentrifuge) tileEntity).isRecipeValid = isRecipeValid;
			}

	}
}