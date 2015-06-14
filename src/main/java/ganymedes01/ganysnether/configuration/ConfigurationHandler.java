package ganymedes01.ganysnether.configuration;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.integration.Integration;
import ganymedes01.ganysnether.integration.ModIntegrator;
import ganymedes01.ganysnether.lib.Reference;
import ganymedes01.ganysnether.lib.Strings;
import ganymedes01.ganysnether.recipes.RecipeRegistry;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

/**
 * Gany's Nether
 *
 * @author ganymedes01
 *
 */

public class ConfigurationHandler {

	public static ConfigurationHandler INSTANCE = new ConfigurationHandler();
	public Configuration configFile;
	public String[] usedCategories = { Configuration.CATEGORY_GENERAL, "durability", "mod integration", "crops" };

	private int configDurability(String name, int def) {
		int config = configFile.get("Durability", name, def).setRequiresMcRestart(true).getInt(def);
		return config > 0 ? config : def;
	}

	private int configInteger(String name, int def) {
		int config = configFile.get(Configuration.CATEGORY_GENERAL, name, def).getInt(def);
		return config > 0 ? config : def;
	}

	private boolean configBoolean(String name, boolean requiresRestart, boolean def) {
		return configBoolean(Configuration.CATEGORY_GENERAL, name, requiresRestart, def);
	}

	private boolean configBoolean(String category, String name, boolean requiresRestart, boolean def) {
		return configFile.get(category, name, def).getBoolean(def);
	}

	private boolean configIntegrationBoolean(String modID) {
		return configFile.get("Mod Integration", "Integrate " + modID, true).setRequiresMcRestart(true).getBoolean(true);
	}

	private File fixFile(File file, String name, String extension) {
		File parent = file.getParentFile();
		return new File(parent, File.separator + Reference.MASTER + File.separator + name + extension);
	}

	public void init(FMLPreInitializationEvent event) {
		configFile = new Configuration(new File(event.getModConfigurationDirectory().getAbsolutePath() + File.separator + Reference.MASTER + File.separator + Reference.MOD_ID + ".cfg"));

		File recipes = fixFile(event.getSuggestedConfigurationFile(), "Recipes", "");
		recipes.mkdirs();
		RecipeRegistry.baseFile = recipes;

		syncConfigs();
	}

	private void syncConfigs() {
		// Mod Integration
		for (Integration integration : ModIntegrator.modIntegrations)
			integration.setShouldIntegrate(configIntegrationBoolean(integration.getModID()));

		// Others
		GanysNether.sceptreOfConcealmentDurability = configDurability(Strings.Items.SCEPTRE_OF_CONCEALMENT_NAME, 128);
		GanysNether.sceptreOfLightningDurability = configDurability(Strings.Items.SCEPTRE_OF_LIGHTNING_NAME, 128);
		GanysNether.sceptreOfFireCharging = configDurability(Strings.Items.SCEPTRE_OF_FIRE_CHARGING_NAME, 32);
		GanysNether.baseballBatDurability = configDurability(Strings.Items.BASEBALL_BAT_NAME, 256);
		GanysNether.sceptreOfWithering = configDurability(Strings.Items.SCEPTRE_OF_WITHERING_NAME, 128);

		GanysNether.shouldGenerateCrops = configBoolean("shouldGenerateCrops", true, GanysNether.shouldGenerateCrops);
		GanysNether.shouldGenerateUndertakers = configBoolean("shouldGenerateUndertakers", true, GanysNether.shouldGenerateUndertakers);
		GanysNether.shouldDoVersionCheck = configBoolean("shouldDoVersionCheck", true, GanysNether.shouldDoVersionCheck);
		GanysNether.shouldGhastTearHaveDispenserAction = configBoolean("shouldGhastTearHaveDispenserAction", true, GanysNether.shouldGhastTearHaveDispenserAction);
		GanysNether.enableUndertaker = configBoolean("enableUndertaker", true, GanysNether.enableUndertaker);
		GanysNether.enableDynamicTextures = configBoolean("enableDynamicTextures", true, GanysNether.enableDynamicTextures);
		GanysNether.enableSceptres = configBoolean("enableSceptres", true, GanysNether.enableSceptres);
		GanysNether.enableSpawners = configBoolean("enableSpawners", true, GanysNether.enableSpawners);
		GanysNether.enableQuartz = configBoolean("enableQuartz", true, GanysNether.enableQuartz);
		GanysNether.enableGlowbox = configBoolean("enableGlowbox", true, GanysNether.enableGlowbox);
		GanysNether.enableSoulGlass = configBoolean("enableSoulGlass", true, GanysNether.enableSoulGlass);
		GanysNether.enableReproducerAndDrops = configBoolean("enableReproducerAndDrops", true, GanysNether.enableReproducerAndDrops);
		GanysNether.enableBlazeArmour = configBoolean("enableBlazeArmour", true, GanysNether.enableBlazeArmour);
		GanysNether.enableVolcanicFurnace = configBoolean("enableVolcanicFurnace", true, GanysNether.enableVolcanicFurnace);
		GanysNether.enableMagmaticCentrifuge = configBoolean("enableMagmaticCentrifuge", true, GanysNether.enableMagmaticCentrifuge);
		GanysNether.enableBottomlessBucket = configBoolean("enableBottomlessBucket", true, GanysNether.enableBottomlessBucket);
		GanysNether.enableSoulChest = configBoolean("enableSoulChest", true, GanysNether.enableSoulChest);
		GanysNether.enableHorseArmourStand = configBoolean("enableHorseArmourStand", true, GanysNether.enableHorseArmourStand);
		GanysNether.enableThermalSmelter = configBoolean("enableThermalSmelter", true, GanysNether.enableThermalSmelter);
		GanysNether.enableBaseballBat = configBoolean("enableBaseballBat", true, GanysNether.enableBaseballBat);
		GanysNether.enableLivingSoul = configBoolean("enableLivingSoul", true, GanysNether.enableLivingSoul);
		GanysNether.enableSoulTNT = configBoolean("enableSoulTNT", true, GanysNether.enableSoulTNT);
		GanysNether.enableFlour = configBoolean("enableFlour", true, GanysNether.enableFlour);

		GanysNether.shouldGenerateBlazingCactoid = configBoolean("crops", "blazing cactoid", true, GanysNether.shouldGenerateBlazingCactoid);
		GanysNether.shouldGenerateHellBush = configBoolean("crops", "hell bush", true, GanysNether.shouldGenerateHellBush);
		GanysNether.shouldGenerateGlowingReed = configBoolean("crops", "glowing reed", true, GanysNether.shouldGenerateGlowingReed);
		GanysNether.shouldGenerateQuarzBerryBush = configBoolean("crops", "quarz berry bush", true, GanysNether.shouldGenerateQuarzBerryBush);
		GanysNether.shouldGenerateSpectreWheat = configBoolean("crops", "spectre wheat", true, GanysNether.shouldGenerateSpectreWheat);
		GanysNether.shouldGenerateWitherShrub = configBoolean("crops", "wither shrub", true, GanysNether.shouldGenerateWitherShrub);
		GanysNether.shouldGenerateWeepingPod = configBoolean("crops", "weeping pod", true, GanysNether.shouldGenerateWeepingPod);

		GanysNether.netherCropRate = configInteger("netherCropSpawnRate", GanysNether.netherCropRate);
		GanysNether.witherShrubRate = configInteger("witherShrubSpawnRate", GanysNether.witherShrubRate);
		GanysNether.undertakerRate = configInteger("undertakerSpawnRate", GanysNether.undertakerRate);

		if (configFile.hasChanged())
			configFile.save();
	}

	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
		if (Reference.MOD_ID.equals(eventArgs.modID))
			syncConfigs();
	}
}