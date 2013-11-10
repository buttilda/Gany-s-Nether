package ganymedes01.ganysnether.core.proxy;

import ganymedes01.ganysnether.blocks.ModBlocks;
import ganymedes01.ganysnether.client.renderer.block.BlockWitherShrubRender;
import ganymedes01.ganysnether.client.renderer.entity.EntityLightningBallRenderer;
import ganymedes01.ganysnether.client.renderer.item.ItemMagmaticCentrifugeRender;
import ganymedes01.ganysnether.client.renderer.item.ItemSoulChestRender;
import ganymedes01.ganysnether.client.renderer.item.ItemSoulGlassRender;
import ganymedes01.ganysnether.client.renderer.tileentity.TileEntityMagmaticCentrifugeRender;
import ganymedes01.ganysnether.client.renderer.tileentity.TileEntitySoulChestRender;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.entities.EntityLightningBall;
import ganymedes01.ganysnether.lib.RenderIDs;
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
		MinecraftForgeClient.registerItemRenderer(ModBlocks.magmaticCentrifuge.blockID, new ItemMagmaticCentrifugeRender());
		MinecraftForgeClient.registerItemRenderer(ModBlocks.soulGlass.blockID, new ItemSoulGlassRender());
		MinecraftForgeClient.registerItemRenderer(ModBlocks.soulGlassStairs.blockID, new ItemSoulGlassRender());

		RenderingRegistry.registerBlockHandler(RenderIDs.WITHER_SHRUB, new BlockWitherShrubRender());

		RenderingRegistry.registerEntityRenderingHandler(EntityLightningBall.class, new EntityLightningBallRenderer());
	}

	@Override
	public void handleTileMagmaticCentrifugePacket(int x, int y, int z, int itemID1, int meta1, int stackSize1, int itemID2, int meta2, int stackSize2, boolean isRecipeValid) {
		World world = FMLClientHandler.instance().getClient().theWorld;
		if (world != null) {
			TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
			if (tileEntity != null)
				if (tileEntity instanceof TileEntityMagmaticCentrifuge) {
					ItemStack material1 = null, material2 = null;
					if (itemID1 > 0 && meta1 > -1 && stackSize1 > -1)
						material1 = new ItemStack(itemID1, stackSize1, meta1);
					if (itemID2 > 0 && meta2 > -1 && stackSize2 > -1)
						material2 = new ItemStack(itemID2, stackSize2, meta2);

					((TileEntityMagmaticCentrifuge) tileEntity).setInventorySlotContents(TileEntityMagmaticCentrifuge.MATERIAL_SLOT_1, material1);
					((TileEntityMagmaticCentrifuge) tileEntity).setInventorySlotContents(TileEntityMagmaticCentrifuge.MATERIAL_SLOT_2, material2);
					((TileEntityMagmaticCentrifuge) tileEntity).isRecipeValid = isRecipeValid;
				}
		}
	}
}