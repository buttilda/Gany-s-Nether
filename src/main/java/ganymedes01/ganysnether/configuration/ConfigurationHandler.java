package ganymedes01.ganysnether.configuration;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.integration.Integration;
import ganymedes01.ganysnether.integration.ModIntegrator;
import ganymedes01.ganysnether.lib.Reference;
import ganymedes01.ganysnether.lib.Strings;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.FMLLog;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class ConfigurationHandler {

	public static Configuration configuration;

	private static int configDurability(String name, int def) {
		int config = configuration.get("Durability", name, def).getInt(def);
		return config > 0 ? config : def;
	}

	private static int configInteger(String name, int def) {
		int config = configuration.get("Others", name, def).getInt(def);
		return config > 0 ? config : def;
	}

	private static boolean configBoolean(String name, boolean def) {
		return configuration.get("Others", name, def).getBoolean(def);
	}

	private static boolean configIntegrationBoolean(String modID) {
		return configuration.get("Mod Integration", "Integrate " + modID, true).getBoolean(true);
	}

	public static void init(File configFile) {
		configuration = new Configuration(configFile);

		try {
			configuration.load();

			// Mod Integration
			for (Integration integration : ModIntegrator.modIntegrations)
				integration.setShouldIntegrate(configIntegrationBoolean(integration.getModID()));

			// Others
			GanysNether.sceptreOfConcealmentDurability = configDurability(Strings.Items.SCEPTRE_OF_CONCEALMENT_NAME, 128);
			GanysNether.sceptreOfLightningDurability = configDurability(Strings.Items.SCEPTRE_OF_LIGHTNING_NAME, 128);
			GanysNether.sceptreOfFireCharging = configDurability(Strings.Items.SCEPTRE_OF_FIRE_CHARGING_NAME, 32);
			GanysNether.baseballBatDurability = configDurability(Strings.Items.BASEBALL_BAT_NAME, 256);

			GanysNether.shouldGenerateCrops = configBoolean(Strings.Others.SHOULD_GENERATE_CROPS, GanysNether.shouldGenerateCrops);
			GanysNether.shouldGenerateUndertakers = configBoolean(Strings.Others.SHOULD_GENERATE_UNDERTAKERS, GanysNether.shouldGenerateUndertakers);
			GanysNether.shouldDoVersionCheck = configBoolean(Strings.Others.SHOULD_DO_VERSION_CHECK, GanysNether.shouldDoVersionCheck);
			GanysNether.shouldGhastTearHaveDispenserAction = configBoolean(Strings.Others.SHOULD_GHAST_TEAR_HAVE_DISPENSER_ACTION, GanysNether.shouldGhastTearHaveDispenserAction);
			GanysNether.enableUndertaker = configBoolean(Strings.Others.ENABLE_UNDERTAKER, GanysNether.enableUndertaker);
			GanysNether.enableAnvilRepairNEILookUp = configBoolean(Strings.Others.ENABLE_ANVIL_LOOK_UP, GanysNether.enableAnvilRepairNEILookUp);
			GanysNether.enableOreDictNEILookUp = configBoolean(Strings.Others.ENABLE_ORE_DICT_LOOK_UP, GanysNether.enableOreDictNEILookUp);

			GanysNether.netherCropRate = configInteger(Strings.Others.NETHER_CROP_RATE, GanysNether.netherCropRate);
			GanysNether.witherShrubRate = configInteger(Strings.Others.WITHER_SHRUB_RATE, GanysNether.witherShrubRate);
			GanysNether.undertakerRate = configInteger(Strings.Others.UNDERTAKER_RATE, GanysNether.undertakerRate);
			GanysNether.undertakerFillSlotChance = configInteger(Strings.Others.UNDERTAKER_FILL_SLOT_CHANCE, GanysNether.undertakerFillSlotChance);

		} catch (Exception e) {
			FMLLog.severe(Reference.MOD_NAME + " has had a problem loading its configuration");
			throw new RuntimeException(e);
		} finally {
			configuration.save();
		}

	}
}