package ganymedes01.ganysnether.core.proxy;

import ganymedes01.ganysnether.blocks.ModBlocks;
import ganymedes01.ganysnether.client.renderer.block.BlockBlazingCactoidRender;
import ganymedes01.ganysnether.client.renderer.block.BlockWitherShrubRender;
import ganymedes01.ganysnether.client.renderer.entity.EntityLightningBallRenderer;
import ganymedes01.ganysnether.client.renderer.entity.EntitySoulTNTRenderer;
import ganymedes01.ganysnether.client.renderer.item.ItemHorseArmourStandRender;
import ganymedes01.ganysnether.client.renderer.item.ItemMagmaticCentrifugeRender;
import ganymedes01.ganysnether.client.renderer.item.ItemSoulChestRender;
import ganymedes01.ganysnether.client.renderer.item.ItemSoulGlassRender;
import ganymedes01.ganysnether.client.renderer.item.ItemThermalSmelterRender;
import ganymedes01.ganysnether.client.renderer.tileentity.TileEntityExtendedSpawnerRender;
import ganymedes01.ganysnether.client.renderer.tileentity.TileEntityHorseArmourStandRender;
import ganymedes01.ganysnether.client.renderer.tileentity.TileEntityMagmaticCentrifugeRender;
import ganymedes01.ganysnether.client.renderer.tileentity.TileEntitySoulChestRender;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.entities.EntityLightningBall;
import ganymedes01.ganysnether.entities.EntitySlowTNT;
import ganymedes01.ganysnether.lib.RenderIDs;
import ganymedes01.ganysnether.lib.Strings;
import ganymedes01.ganysnether.tileentities.TileEntityExtendedSpawner;
import ganymedes01.ganysnether.tileentities.TileEntityHorseArmourStand;
import ganymedes01.ganysnether.tileentities.TileEntityMagmaticCentrifuge;
import ganymedes01.ganysnether.tileentities.TileEntitySoulChest;
import ganymedes01.ganysnether.tileentities.TileEntityUndertaker;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
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
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHorseArmourStand.class, new TileEntityHorseArmourStandRender());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityExtendedSpawner.class, new TileEntityExtendedSpawnerRender());
	}

	@Override
	public void registerRenderers() {
		MinecraftForgeClient.registerItemRenderer(ModBlocks.soulChest.blockID, new ItemSoulChestRender(Utils.getResource(Utils.getEntityTexture(Strings.SOUL_CHEST_NAME))));
		MinecraftForgeClient.registerItemRenderer(ModBlocks.undertaker.blockID, new ItemSoulChestRender(Utils.getResource(Utils.getEntityTexture(Strings.UNDERTAKER_NAME))));
		MinecraftForgeClient.registerItemRenderer(ModBlocks.magmaticCentrifuge.blockID, new ItemMagmaticCentrifugeRender());
		MinecraftForgeClient.registerItemRenderer(ModBlocks.soulGlass.blockID, new ItemSoulGlassRender());
		MinecraftForgeClient.registerItemRenderer(ModBlocks.soulGlassStairs.blockID, new ItemSoulGlassRender());
		MinecraftForgeClient.registerItemRenderer(ModBlocks.thermalSmelter.blockID, new ItemThermalSmelterRender());
		MinecraftForgeClient.registerItemRenderer(ModBlocks.horseArmourStand.blockID, new ItemHorseArmourStandRender());

		RenderingRegistry.registerBlockHandler(RenderIDs.WITHER_SHRUB, new BlockWitherShrubRender());
		RenderingRegistry.registerBlockHandler(RenderIDs.BLAZING_CACTOID, new BlockBlazingCactoidRender());

		RenderingRegistry.registerEntityRenderingHandler(EntityLightningBall.class, new EntityLightningBallRenderer());
		RenderingRegistry.registerEntityRenderingHandler(EntitySlowTNT.class, new EntitySoulTNTRenderer());
	}

	@Override
	public void handleMagmaticCentrifugePacket(int x, int y, int z, ItemStack material1, ItemStack material2, boolean isRecipeValid) {
		World world = FMLClientHandler.instance().getClient().theWorld;
		if (world != null) {
			TileEntity tile = world.getBlockTileEntity(x, y, z);
			if (tile instanceof TileEntityMagmaticCentrifuge) {
				TileEntityMagmaticCentrifuge centrifuge = (TileEntityMagmaticCentrifuge) tile;

				centrifuge.setInventorySlotContents(TileEntityMagmaticCentrifuge.MATERIAL_SLOT_1, material1);
				centrifuge.setInventorySlotContents(TileEntityMagmaticCentrifuge.MATERIAL_SLOT_2, material2);
				centrifuge.isRecipeValid = isRecipeValid;
			}
		}
	}

	@Override
	public void handleHorseArmourStandPacket(int x, int y, int z, byte type, byte rotation) {
		World world = FMLClientHandler.instance().getClient().theWorld;
		if (world != null) {
			TileEntity tile = world.getBlockTileEntity(x, y, z);
			if (tile instanceof TileEntityHorseArmourStand) {
				TileEntityHorseArmourStand stand = (TileEntityHorseArmourStand) tile;

				stand.setArmourType(type);
				stand.setRotation(rotation);
			}
		}
	}

	@Override
	public void handleExtendedSpawnerPacket(int x, int y, int z, NBTTagCompound data) {
		World world = FMLClientHandler.instance().getClient().theWorld;
		if (world != null) {
			TileEntity tile = world.getBlockTileEntity(x, y, z);
			if (tile instanceof TileEntityExtendedSpawner)
				tile.readFromNBT(data);
			tile.worldObj.markBlockForRenderUpdate(tile.xCoord, tile.yCoord, tile.zCoord);
		}
	}
}