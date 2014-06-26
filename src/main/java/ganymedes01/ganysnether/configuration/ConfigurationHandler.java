package ganymedes01.ganysnether.configuration;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.integration.Integration;
import ganymedes01.ganysnether.integration.ModIntegrator;
import ganymedes01.ganysnether.lib.Reference;
import ganymedes01.ganysnether.lib.Strings;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.client.event.ConfigChangedEvent;
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
	public String[] usedCategories = { Configuration.CATEGORY_GENERAL, "durability", "mod integration" };

	private int configDurability(String name, int def) {
		int config = configFile.get("Durability", name, def).setRequiresMcRestart(true).getInt(def);
		return config > 0 ? config : def;
	}

	private int configInteger(String name, int def) {
		int config = configFile.get(Configuration.CATEGORY_GENERAL, name, def).getInt(def);
		return config > 0 ? config : def;
	}

	private boolean configBoolean(String name, boolean requiresRestart, boolean def) {
		return configFile.get(Configuration.CATEGORY_GENERAL, name, def).getBoolean(def);
	}

	private boolean configIntegrationBoolean(String modID) {
		return configFile.get("Mod Integration", "Integrate " + modID, true).setRequiresMcRestart(true).getBoolean(true);
	}

	public void init(File file) {
		configFile = new Configuration(file);

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

		GanysNether.shouldGenerateCrops = configBoolean(Strings.Others.SHOULD_GENERATE_CROPS, false, GanysNether.shouldGenerateCrops);
		GanysNether.shouldGenerateUndertakers = configBoolean(Strings.Others.SHOULD_GENERATE_UNDERTAKERS, false, GanysNether.shouldGenerateUndertakers);
		GanysNether.shouldDoVersionCheck = configBoolean(Strings.Others.SHOULD_DO_VERSION_CHECK, true, GanysNether.shouldDoVersionCheck);
		GanysNether.shouldGhastTearHaveDispenserAction = configBoolean(Strings.Others.SHOULD_GHAST_TEAR_HAVE_DISPENSER_ACTION, true, GanysNether.shouldGhastTearHaveDispenserAction);
		GanysNether.enableUndertaker = configBoolean(Strings.Others.ENABLE_UNDERTAKER, true, GanysNether.enableUndertaker);
		GanysNether.enableAnvilRepairNEILookUp = configBoolean(Strings.Others.ENABLE_ANVIL_LOOK_UP, true, GanysNether.enableAnvilRepairNEILookUp);
		GanysNether.enableOreDictNEILookUp = configBoolean(Strings.Others.ENABLE_ORE_DICT_LOOK_UP, true, GanysNether.enableOreDictNEILookUp);

		GanysNether.netherCropRate = configInteger(Strings.Others.NETHER_CROP_RATE, GanysNether.netherCropRate);
		GanysNether.witherShrubRate = configInteger(Strings.Others.WITHER_SHRUB_RATE, GanysNether.witherShrubRate);
		GanysNether.undertakerRate = configInteger(Strings.Others.UNDERTAKER_RATE, GanysNether.undertakerRate);
		GanysNether.undertakerFillSlotChance = configInteger(Strings.Others.UNDERTAKER_FILL_SLOT_CHANCE, GanysNether.undertakerFillSlotChance);

		if (configFile.hasChanged())
			configFile.save();
	}

	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
		if (Reference.MOD_ID.equals(eventArgs.modID))
			syncConfigs();
	}
}