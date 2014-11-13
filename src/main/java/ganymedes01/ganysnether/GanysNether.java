package ganymedes01.ganysnether;

import ganymedes01.ganysnether.command.GanysNetherCommand;
import ganymedes01.ganysnether.configuration.ConfigurationHandler;
import ganymedes01.ganysnether.core.handlers.FuelHandler;
import ganymedes01.ganysnether.core.handlers.InterModComms;
import ganymedes01.ganysnether.core.proxy.CommonProxy;
import ganymedes01.ganysnether.core.utils.HoeList;
import ganymedes01.ganysnether.creativetab.CreativeTabNether;
import ganymedes01.ganysnether.integration.ModIntegrator;
import ganymedes01.ganysnether.lib.Reference;
import ganymedes01.ganysnether.network.PacketHandler;
import ganymedes01.ganysnether.recipes.MagmaticCentrifugeRecipes;
import ganymedes01.ganysnether.recipes.ModRecipes;
import ganymedes01.ganysnether.world.NetherWorldGen;
import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms.IMCEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Gany's Nether
 *
 * @author ganymedes01
 *
 */

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION_NUMBER, dependencies = Reference.DEPENDENCIES, guiFactory = Reference.GUI_FACTORY_CLASS)
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
	public static boolean enableUndertaker = true;
	public static boolean enableDynamicTextures = true;
	public static boolean enableSceptres = true;
	public static boolean enableSpawners = true;
	public static boolean enableQuartz = true;
	public static boolean enableGlowbox = true;
	public static boolean enableSoulGlass = true;
	public static boolean enableReproducerAndDrops = true;
	public static boolean enableBlazeArmour = true;
	public static boolean enableVolcanicFurnace = true;
	public static boolean enableMagmaticCentrifuge = true;
	public static int netherCropRate = 20;
	public static int witherShrubRate = 50;
	public static int undertakerRate = 1200;
	public static int undertakerFillSlotChance = 10;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ModIntegrator.preInit();

		ConfigurationHandler.INSTANCE.init(event);

		if (shouldGenerateUndertakers || shouldGenerateCrops)
			GameRegistry.registerWorldGenerator(new NetherWorldGen(), 0);

		ModBlocks.init();
		ModItems.init();
		ModRecipes.init();

		if (enableMagmaticCentrifuge)
			MagmaticCentrifugeRecipes.INSTANCE.init();
		if (shouldGenerateCrops)
			HoeList.init();
	}

	@EventHandler
	public void load(FMLInitializationEvent event) {
		PacketHandler.init();

		NetworkRegistry.INSTANCE.registerGuiHandler(instance, proxy);

		GameRegistry.registerFuelHandler(new FuelHandler());

		proxy.registerEvents();
		proxy.registerTileEntities();
		proxy.registerRenderers();
		proxy.registerEntities();

		ModIntegrator.init();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		ModIntegrator.postInit();
	}

	@EventHandler
	public void serverStarting(FMLServerStartingEvent event) {
		event.registerServerCommand(new GanysNetherCommand());
	}

	@EventHandler
	public void processIMCRequests(IMCEvent event) {
		InterModComms.processIMC(event);
	}
}