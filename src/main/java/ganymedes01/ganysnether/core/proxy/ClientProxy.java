package ganymedes01.ganysnether.core.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.ModBlocks;
import ganymedes01.ganysnether.client.renderer.block.BlockBlazingCactoidRender;
import ganymedes01.ganysnether.client.renderer.block.BlockChestRenderer;
import ganymedes01.ganysnether.client.renderer.block.BlockExtendedSpawnerRender;
import ganymedes01.ganysnether.client.renderer.block.BlockFocusedLavaCellRender;
import ganymedes01.ganysnether.client.renderer.block.BlockVolcanicFurnaceRender;
import ganymedes01.ganysnether.client.renderer.block.BlockWitherShrubRender;
import ganymedes01.ganysnether.client.renderer.entity.EntityBlazeGolemRenderer;
import ganymedes01.ganysnether.client.renderer.entity.EntityLightningBallRenderer;
import ganymedes01.ganysnether.client.renderer.entity.EntitySoulTNTRenderer;
import ganymedes01.ganysnether.client.renderer.item.ItemHorseArmourStandRender;
import ganymedes01.ganysnether.client.renderer.item.ItemMagmaticCentrifugeRender;
import ganymedes01.ganysnether.client.renderer.tileentity.TileEntityExtendedSpawnerRender;
import ganymedes01.ganysnether.client.renderer.tileentity.TileEntityHorseArmourStandRender;
import ganymedes01.ganysnether.client.renderer.tileentity.TileEntityMagmaticCentrifugeRender;
import ganymedes01.ganysnether.client.renderer.tileentity.TileEntitySoulChestRender;
import ganymedes01.ganysnether.core.handlers.ClientEventsHandler;
import ganymedes01.ganysnether.core.handlers.VersionCheckTickHandler;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.core.utils.VersionHelper;
import ganymedes01.ganysnether.entities.EntityBlazeGolem;
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
import net.minecraftforge.common.MinecraftForge;

/**
 * Gany's Nether
 *
 * @author ganymedes01
 *
 */

public class ClientProxy extends CommonProxy {

	@Override
	public void registerEvents() {
		super.registerEvents();
		if (GanysNether.shouldDoVersionCheck) {
			VersionHelper.execute();
			FMLCommonHandler.instance().bus().register(new VersionCheckTickHandler());
		}
		MinecraftForge.EVENT_BUS.register(new ClientEventsHandler());
	}

	@Override
	public void registerTileEntities() {
		super.registerTileEntities();
		if (GanysNether.enableSoulChest)
			ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySoulChest.class, new TileEntitySoulChestRender(Utils.getResource(Utils.getEntityTexture(Strings.Blocks.SOUL_CHEST_NAME))));
		if (GanysNether.enableUndertaker)
			ClientRegistry.bindTileEntitySpecialRenderer(TileEntityUndertaker.class, new TileEntitySoulChestRender(Utils.getResource(Utils.getEntityTexture(Strings.Blocks.UNDERTAKER_NAME))));
		if (GanysNether.enableMagmaticCentrifuge)
			ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMagmaticCentrifuge.class, new TileEntityMagmaticCentrifugeRender());
		if (GanysNether.enableHorseArmourStand)
			ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHorseArmourStand.class, new TileEntityHorseArmourStandRender());
		if (GanysNether.enableSpawners)
			ClientRegistry.bindTileEntitySpecialRenderer(TileEntityExtendedSpawner.class, new TileEntityExtendedSpawnerRender());
	}

	@Override
	public void registerRenderers() {
		if (GanysNether.enableMagmaticCentrifuge)
			MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.magmaticCentrifuge), new ItemMagmaticCentrifugeRender());
		if (GanysNether.enableHorseArmourStand)
			MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.horseArmourStand), new ItemHorseArmourStandRender());

		RenderingRegistry.registerBlockHandler(new BlockWitherShrubRender());
		RenderingRegistry.registerBlockHandler(new BlockBlazingCactoidRender());
		if (GanysNether.enableSpawners)
			RenderingRegistry.registerBlockHandler(new BlockExtendedSpawnerRender());
		if (GanysNether.enableVolcanicFurnace) {
			RenderingRegistry.registerBlockHandler(new BlockFocusedLavaCellRender());
			RenderingRegistry.registerBlockHandler(new BlockVolcanicFurnaceRender());
		}
		RenderingRegistry.registerBlockHandler(new BlockChestRenderer());

		if (GanysNether.enableSceptres)
			RenderingRegistry.registerEntityRenderingHandler(EntityLightningBall.class, new EntityLightningBallRenderer());
		if (GanysNether.enableSoulTNT)
			RenderingRegistry.registerEntityRenderingHandler(EntitySlowTNT.class, new EntitySoulTNTRenderer());
		if (GanysNether.enableBlazeBlock)
			RenderingRegistry.registerEntityRenderingHandler(EntityBlazeGolem.class, new EntityBlazeGolemRenderer());
	}
}