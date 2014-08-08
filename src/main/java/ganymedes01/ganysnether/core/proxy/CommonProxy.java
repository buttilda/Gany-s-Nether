package ganymedes01.ganysnether.core.proxy;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.client.gui.inventory.GuiMagmaticCentrifuge;
import ganymedes01.ganysnether.client.gui.inventory.GuiReproducer;
import ganymedes01.ganysnether.client.gui.inventory.GuiThermalSmelter;
import ganymedes01.ganysnether.client.gui.inventory.GuiUndertaker;
import ganymedes01.ganysnether.client.gui.inventory.GuiVolcanicFurnace;
import ganymedes01.ganysnether.configuration.ConfigurationHandler;
import ganymedes01.ganysnether.core.handlers.BonemealOnNetherCrops;
import ganymedes01.ganysnether.core.handlers.EntityEvents;
import ganymedes01.ganysnether.core.handlers.HoeEvent;
import ganymedes01.ganysnether.core.handlers.PlayerRightClickEvent;
import ganymedes01.ganysnether.core.handlers.TooltipEvent;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.entities.EntityLightningBall;
import ganymedes01.ganysnether.entities.EntitySlowTNT;
import ganymedes01.ganysnether.inventory.ContainerMagmaticCentrifuge;
import ganymedes01.ganysnether.inventory.ContainerReproducer;
import ganymedes01.ganysnether.inventory.ContainerThermalSmelter;
import ganymedes01.ganysnether.inventory.ContainerUndertaker;
import ganymedes01.ganysnether.inventory.ContainerVolcanicFurnace;
import ganymedes01.ganysnether.lib.GUIsID;
import ganymedes01.ganysnether.lib.ModIDs;
import ganymedes01.ganysnether.lib.Strings;
import ganymedes01.ganysnether.tileentities.TileEntityExtendedSpawner;
import ganymedes01.ganysnether.tileentities.TileEntityHorseArmourStand;
import ganymedes01.ganysnether.tileentities.TileEntityMagmaticCentrifuge;
import ganymedes01.ganysnether.tileentities.TileEntityReproducer;
import ganymedes01.ganysnether.tileentities.TileEntitySoulChest;
import ganymedes01.ganysnether.tileentities.TileEntityThermalSmelter;
import ganymedes01.ganysnether.tileentities.TileEntityUndertaker;
import ganymedes01.ganysnether.tileentities.TileEntityVolcanicFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Gany's Nether
 *
 * @author ganymedes01
 *
 */

public class CommonProxy implements IGuiHandler {

	public void registerEvents() {
		FMLCommonHandler.instance().bus().register(ConfigurationHandler.INSTANCE);

		MinecraftForge.EVENT_BUS.register(new HoeEvent());
		MinecraftForge.EVENT_BUS.register(new EntityEvents());
		MinecraftForge.EVENT_BUS.register(new BonemealOnNetherCrops());
		MinecraftForge.EVENT_BUS.register(new PlayerRightClickEvent());
		MinecraftForge.EVENT_BUS.register(new TooltipEvent());
	}

	public void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntitySoulChest.class, Strings.Blocks.SOUL_CHEST_NAME);
		GameRegistry.registerTileEntity(TileEntityVolcanicFurnace.class, Strings.Blocks.VOLCANIC_FURNACE_NAME);
		GameRegistry.registerTileEntity(TileEntityReproducer.class, Strings.Blocks.REPRODUCER_NAME);
		GameRegistry.registerTileEntity(TileEntityUndertaker.class, Strings.Blocks.UNDERTAKER_NAME);
		GameRegistry.registerTileEntity(TileEntityMagmaticCentrifuge.class, Utils.getUnlocalizedName(Strings.Blocks.MAGMATIC_CENTRIFUGE_NAME));
		GameRegistry.registerTileEntity(TileEntityThermalSmelter.class, Utils.getUnlocalizedName(Strings.Blocks.THERMAL_SMELTER_NAME));
		GameRegistry.registerTileEntity(TileEntityHorseArmourStand.class, Utils.getUnlocalizedName(Strings.Blocks.HORSE_ARMOUR_STAND_NAME));
		GameRegistry.registerTileEntity(TileEntityExtendedSpawner.class, Utils.getUnlocalizedName(Strings.Blocks.EXTENDED_SPAWNER_NAME));
	}

	public void registerEntities() {
		EntityRegistry.registerModEntity(EntityLightningBall.class, Utils.getUnlocalizedName(Strings.Entities.ENTITY_LIGHTNING_BALL_NAME), ModIDs.ENTITY_LIGHTNING_BALL_ID, GanysNether.instance, 160, 5, true);
		EntityRegistry.registerModEntity(EntitySlowTNT.class, Utils.getUnlocalizedName(Strings.Entities.ENTITY_SLOW_TNT_NAME), ModIDs.ENTITY_SLOW_TNT_ID, GanysNether.instance, 160, 5, true);
	}

	public void registerRenderers() {
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tile = world.getTileEntity(x, y, z);
		switch (ID) {
			case GUIsID.VOLCANIC_FURNACE:
				return new ContainerVolcanicFurnace(player.inventory, (TileEntityVolcanicFurnace) tile);
			case GUIsID.REPRODUCER:
				return new ContainerReproducer(player.inventory, (TileEntityReproducer) tile);
			case GUIsID.UNDERTAKER:
				return new ContainerUndertaker(player.inventory, (TileEntityUndertaker) tile);
			case GUIsID.MAGMATIC_CENTRIFUGE:
				return new ContainerMagmaticCentrifuge(player.inventory, (TileEntityMagmaticCentrifuge) tile);
			case GUIsID.THERMAL_SMELTER:
				return new ContainerThermalSmelter(player.inventory, (TileEntityThermalSmelter) tile);
			default:
				return null;
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tile = world.getTileEntity(x, y, z);
		switch (ID) {
			case GUIsID.VOLCANIC_FURNACE:
				return new GuiVolcanicFurnace(player.inventory, (TileEntityVolcanicFurnace) tile);
			case GUIsID.REPRODUCER:
				return new GuiReproducer(player.inventory, (TileEntityReproducer) tile);
			case GUIsID.UNDERTAKER:
				return new GuiUndertaker(player.inventory, (TileEntityUndertaker) tile);
			case GUIsID.MAGMATIC_CENTRIFUGE:
				return new GuiMagmaticCentrifuge(player.inventory, (TileEntityMagmaticCentrifuge) tile);
			case GUIsID.THERMAL_SMELTER:
				return new GuiThermalSmelter(player.inventory, (TileEntityThermalSmelter) tile);
			default:
				return null;
		}
	}
}