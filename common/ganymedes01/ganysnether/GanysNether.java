package ganymedes01.ganysnether;

import ganymedes01.ganysnether.blocks.ModBlocks;
import ganymedes01.ganysnether.configuration.ConfigurationHandler;
import ganymedes01.ganysnether.core.handlers.BonemealOnNetherCrops;
import ganymedes01.ganysnether.core.handlers.EntityDeathEvent;
import ganymedes01.ganysnether.core.handlers.FuelHandler;
import ganymedes01.ganysnether.core.handlers.HoeEvent;
import ganymedes01.ganysnether.core.handlers.InterModComms;
import ganymedes01.ganysnether.core.handlers.PlayerRightClickEvent;
import ganymedes01.ganysnether.core.handlers.VersionCheckTickHandler;
import ganymedes01.ganysnether.core.proxy.CommonProxy;
import ganymedes01.ganysnether.core.utils.HoeList;
import ganymedes01.ganysnether.core.utils.VersionHelper;
import ganymedes01.ganysnether.creativetab.CreativeTabNether;
import ganymedes01.ganysnether.integration.ModIntegrator;
import ganymedes01.ganysnether.items.ModItems;
import ganymedes01.ganysnether.lib.Reference;
import ganymedes01.ganysnether.network.PacketHandler;
import ganymedes01.ganysnether.recipes.MagmaticCentrifugeRecipes;
import ganymedes01.ganysnether.recipes.ModRecipes;
import ganymedes01.ganysnether.world.NetherWorldGen;

import java.io.File;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms.IMCEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION_NUMBER, dependencies = Reference.DEPENDENCIES)
@NetworkMod(channels = { Reference.CHANNEL_NAME }, clientSideRequired = true, serverSideRequired = true, packetHandler = PacketHandler.class)
public class GanysNether {

	@Instance(Reference.MOD_ID)
	public static GanysNether instance;

	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;

	public static CreativeTabs netherTab = new CreativeTabNether();
	public static int sceptreOfConcealmentDurability;
	public static int sceptreOfLightningDurability;
	public static int sceptreOfFireCharging;
	public static int baseballBatDurability;
	public static boolean shouldGenerateCrops = true;
	public static boolean shouldGenerateUndertakers = true;
	public static boolean shouldDoVersionCheck = true;
	public static boolean shouldGhastTearHaveDispenserAction = true;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ConfigurationHandler.init(new File(event.getModConfigurationDirectory().getAbsolutePath() + File.separator + Reference.MASTER + File.separator + Reference.MOD_ID + ".cfg"));

		if (shouldDoVersionCheck) {
			VersionHelper.execute();
			TickRegistry.registerTickHandler(new VersionCheckTickHandler(), Side.CLIENT);
		}

		proxy.registerEntities();

		ModBlocks.init();
		ModItems.init();
		ModRecipes.init();

		MagmaticCentrifugeRecipes.initRecipes();
		HoeList.init();
	}

	@EventHandler
	public void load(FMLInitializationEvent event) {
		NetworkRegistry.instance().registerGuiHandler(instance, proxy);
		GameRegistry.registerFuelHandler(new FuelHandler());

		MinecraftForge.EVENT_BUS.register(new HoeEvent());
		MinecraftForge.EVENT_BUS.register(new EntityDeathEvent());
		MinecraftForge.EVENT_BUS.register(new BonemealOnNetherCrops());
		MinecraftForge.EVENT_BUS.register(new PlayerRightClickEvent());

		proxy.registerTileEntities();
		proxy.registerRenderers();

		if (shouldGenerateCrops || shouldGenerateUndertakers)
			GameRegistry.registerWorldGenerator(new NetherWorldGen());

		ModIntegrator.init();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		MagmaticCentrifugeRecipes.initOreDictRecipes();
		ModIntegrator.postInit();
	}

	@EventHandler
	public void processIMCRequests(IMCEvent event) {
		InterModComms.processIMC(event);
	}
}