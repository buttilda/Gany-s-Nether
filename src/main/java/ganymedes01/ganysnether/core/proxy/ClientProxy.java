package ganymedes01.ganysnether.core.proxy;

import ganymedes01.ganysnether.blocks.ModBlocks;
import ganymedes01.ganysnether.client.renderer.block.BlockBlazingCactoidRender;
import ganymedes01.ganysnether.client.renderer.block.BlockExtendedSpawnerRender;
import ganymedes01.ganysnether.client.renderer.block.BlockFocusedLavaCellRender;
import ganymedes01.ganysnether.client.renderer.block.BlockVolcanicFurnaceRender;
import ganymedes01.ganysnether.client.renderer.block.BlockWitherShrubRender;
import ganymedes01.ganysnether.client.renderer.entity.EntityLightningBallRenderer;
import ganymedes01.ganysnether.client.renderer.entity.EntitySoulTNTRenderer;
import ganymedes01.ganysnether.client.renderer.item.ItemHorseArmourStandRender;
import ganymedes01.ganysnether.client.renderer.item.ItemMagmaticCentrifugeRender;
import ganymedes01.ganysnether.client.renderer.item.ItemSoulChestRender;
import ganymedes01.ganysnether.client.renderer.item.ItemThermalSmelterRender;
import ganymedes01.ganysnether.client.renderer.tileentity.TileEntityExtendedSpawnerRender;
import ganymedes01.ganysnether.client.renderer.tileentity.TileEntityHorseArmourStandRender;
import ganymedes01.ganysnether.client.renderer.tileentity.TileEntityMagmaticCentrifugeRender;
import ganymedes01.ganysnether.client.renderer.tileentity.TileEntitySoulChestRender;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.entities.EntityLightningBall;
import ganymedes01.ganysnether.entities.EntitySlowTNT;
import ganymedes01.ganysnether.lib.Strings;
import ganymedes01.ganysnether.tileentities.TileEntityExtendedSpawner;
import ganymedes01.ganysnether.tileentities.TileEntityHorseArmourStand;
import ganymedes01.ganysnether.tileentities.TileEntityMagmaticCentrifuge;
import ganymedes01.ganysnether.tileentities.TileEntitySoulChest;
import ganymedes01.ganysnether.tileentities.TileEntityUndertaker;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
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
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySoulChest.class, new TileEntitySoulChestRender(Utils.getResource(Utils.getEntityTexture(Strings.Blocks.SOUL_CHEST_NAME))));
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityUndertaker.class, new TileEntitySoulChestRender(Utils.getResource(Utils.getEntityTexture(Strings.Blocks.UNDERTAKER_NAME))));
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMagmaticCentrifuge.class, new TileEntityMagmaticCentrifugeRender());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHorseArmourStand.class, new TileEntityHorseArmourStandRender());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityExtendedSpawner.class, new TileEntityExtendedSpawnerRender());
	}

	@Override
	public void registerRenderers() {
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.soulChest), new ItemSoulChestRender(Utils.getResource(Utils.getEntityTexture(Strings.Blocks.SOUL_CHEST_NAME))));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.undertaker), new ItemSoulChestRender(Utils.getResource(Utils.getEntityTexture(Strings.Blocks.UNDERTAKER_NAME))));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.magmaticCentrifuge), new ItemMagmaticCentrifugeRender());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.thermalSmelter), new ItemThermalSmelterRender());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.horseArmourStand), new ItemHorseArmourStandRender());

		RenderingRegistry.registerBlockHandler(new BlockWitherShrubRender());
		RenderingRegistry.registerBlockHandler(new BlockBlazingCactoidRender());
		RenderingRegistry.registerBlockHandler(new BlockExtendedSpawnerRender());
		RenderingRegistry.registerBlockHandler(new BlockFocusedLavaCellRender());
		RenderingRegistry.registerBlockHandler(new BlockVolcanicFurnaceRender());

		RenderingRegistry.registerEntityRenderingHandler(EntityLightningBall.class, new EntityLightningBallRenderer());
		RenderingRegistry.registerEntityRenderingHandler(EntitySlowTNT.class, new EntitySoulTNTRenderer());
	}
}