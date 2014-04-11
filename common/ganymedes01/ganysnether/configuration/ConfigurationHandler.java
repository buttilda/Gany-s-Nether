package ganymedes01.ganysnether.configuration;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.core.utils.IdGenerator;
import ganymedes01.ganysnether.integration.Integration;
import ganymedes01.ganysnether.integration.ModIntegrator;
import ganymedes01.ganysnether.lib.ModIDs;
import ganymedes01.ganysnether.lib.Reference;
import ganymedes01.ganysnether.lib.Strings;

import java.io.File;
import java.util.logging.Level;

import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.FMLLog;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class ConfigurationHandler {

	public static Configuration configuration;
	private static IdGenerator idGen = new IdGenerator(Reference.ITEM_ID_BASE, Reference.BLOCK_ID_BASE);

	private static int configBlock(String name) {
		return configuration.getBlock(name, idGen.getNextBlockID()).getInt(idGen.getLastBlockID());
	}

	private static int configItem(String name) {
		return configuration.getItem(name, idGen.getNextItemID()).getInt(idGen.getLastItemID());
	}

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

			// Blocks
			ModIDs.TILLED_NETHERRACK_ID = configBlock(Strings.Blocks.TILLED_NETHERRACK_NAME);
			ModIDs.QUARZ_BERRY_BUSH_ID = configBlock(Strings.Blocks.QUARZ_BERRY_BUSH_NAME);
			ModIDs.SPECTRE_WHEAT_CROP_ID = configBlock(Strings.Blocks.SPECTRE_WHEAT_BLOCK_NAME);
			ModIDs.GLOWING_REED_CROP_ID = configBlock(Strings.Blocks.GLOWING_REED_BLOCK_NAME);
			ModIDs.SOUL_GLASS_ID = configBlock(Strings.Blocks.SOUL_GLASS_NAME);
			ModIDs.SOUL_CHEST_ID = configBlock(Strings.Blocks.SOUL_CHEST_NAME);
			ModIDs.VOLCANIC_FURNACE_IDLE_ID = configBlock(Strings.Blocks.VOLCANIC_FURNACE_NAME + "idle");
			ModIDs.VOLCANIC_FURNACE_ACTIVE_ID = configBlock(Strings.Blocks.VOLCANIC_FURNACE_NAME + "_active");
			ModIDs.DENSE_LAVA_CELL_ID = configBlock(Strings.Blocks.DENSE_LAVA_CELL_NAME);
			ModIDs.GLOW_BOX_ID = configBlock(Strings.Blocks.GLOW_BOX_NAME);
			ModIDs.COLOURED_QUARTZ_BLOCK_ID = configBlock(Strings.Blocks.COLOURED_QUARTZ_BLOCK_NAME);
			ModIDs.COLOURED_CHISELLED_QUARTZ_BLOCK_ID = configBlock(Strings.Blocks.COLOURED_CHISELLED_QUARTZ_BLOCK_NAME);
			for (int i = 0; i < ModIDs.COLOURED_QUARTZ_STAIRS_IDS.length; i++)
				ModIDs.COLOURED_QUARTZ_STAIRS_IDS[i] = configBlock(Strings.Blocks.COLOURED_QUARTZ_STAIRS_NAMES[i]);
			ModIDs.SOUL_GLASS_STAIRS_ID = configBlock(Strings.Blocks.SOUL_GLASS_STAIRS_NAME);
			for (int i = 0; i < ModIDs.COLOURED_QUARTZ_PILLARS_IDS.length; i++)
				ModIDs.COLOURED_QUARTZ_PILLARS_IDS[i] = configBlock(Strings.Blocks.COLOURED_QUARTZ_PILLARS_NAME + i);
			ModIDs.REPRODUCER_ID = configBlock(Strings.Blocks.REPRODUCER_NAME);
			ModIDs.UNDERTAKER_ID = configBlock(Strings.Blocks.UNDERTAKER_NAME);
			ModIDs.WITHER_SHRUB_ID = configBlock(Strings.Blocks.WITHER_SHRUB_NAME);
			ModIDs.MAGMATIC_CENTRIFUGE_ID = configBlock(Strings.Blocks.MAGMATIC_CENTRIFUGE_NAME);
			ModIDs.WEEPING_POD_ID = configBlock(Strings.Blocks.WEEPING_POD_NAME);
			ModIDs.SOUL_TNT_ID = configBlock(Strings.Blocks.SOUL_TNT_NAME);
			ModIDs.BLAZING_CACTOID_ID = configBlock(Strings.Blocks.BLAZING_CACTOID_NAME);
			ModIDs.HELL_BUSH_ID = configBlock(Strings.Blocks.HELL_BUSH_NAME);
			ModIDs.THERMAL_SMELTER_ID = configBlock(Strings.Blocks.THERMAL_SMELTER_NAME);
			ModIDs.HORSE_ARMOUR_STAND_ID = configBlock(Strings.Blocks.HORSE_ARMOUR_STAND_NAME);
			ModIDs.EXTENDED_SPAWNER_ID = configBlock(Strings.Blocks.EXTENDED_SPAWNER_NAME);

			// Armour
			ModIDs.BLAZE_HELMET_ID = configItem(Strings.Items.BLAZE_HELMET_NAME);
			ModIDs.BLAZE_CHESTPLATE_ID = configItem(Strings.Items.BLAZE_CHESTPLATE_NAME);
			ModIDs.BLAZE_LEGGINGS_ID = configItem(Strings.Items.BLAZE_LEGGINGS_NAME);
			ModIDs.BLAZE_BOOTS_ID = configItem(Strings.Items.BLAZE_BOOTS_NAME);

			// Items
			ModIDs.QUARZ_BERRY_SEEDS_ID = configItem(Strings.Items.QUARZ_BERRY_SEEDS_NAME);
			ModIDs.QUARZ_BERRY_ID = configItem(Strings.Items.QUARZ_BERRY_NAME);
			ModIDs.GHOST_SEEDS_ID = configItem(Strings.Items.GHOST_SEEDS_NAME);
			ModIDs.SPECTRE_WHEAT_ITEM_ID = configItem(Strings.Items.SPECTRE_WHEAT_NAME);
			ModIDs.SPOOKY_FLOUR_ID = configItem(Strings.Items.SPOOKY_FLOUR_NAME);
			ModIDs.GLOWING_REED_ITEM_ID = configItem(Strings.Items.GLOWING_REED_NAME);
			ModIDs.BOTTOMLESS_BUCKET_ID = configItem(Strings.Items.BOTTOMLESS_BUCKET_NAME);
			ModIDs.DIMENSIONAL_BREAD_ID = configItem(Strings.Items.DIMENSIONAL_BREAD_NAME);
			ModIDs.BASEBALL_BAT_ID = configItem(Strings.Items.BASEBALL_BAT_NAME);
			ModIDs.SCEPTRE_OF_CONCEALMENT_ID = configItem(Strings.Items.SCEPTRE_OF_CONCEALMENT_NAME);
			ModIDs.SKELETON_SPAWNER_ID = configItem(Strings.Items.SKELETON_SPAWNER_NAME);
			ModIDs.SILVERFISH_SCALE_ID = configItem(Strings.Items.SILVERFISH_SCALE_NAME);
			ModIDs.BAT_WING_ID = configItem(Strings.Items.BAT_WING_NAME);
			ModIDs.COOKED_BAT_WING_ID = configItem(Strings.Items.COOKED_BAT_WING_NAME);
			ModIDs.WOLF_TEETH_ID = configItem(Strings.Items.WOLF_TEETH_NAME);
			ModIDs.BLAZE_INGOT_ID = configItem(Strings.Items.BLAZE_INGOT_NAME);
			ModIDs.SCEPTRE_OF_FIRE_CHARGING_ID = configItem(Strings.Items.SCEPTRE_OF_FIRE_CHARGING_NAME);
			ModIDs.SCEPTRE_OF_LIGHTNING_ID = configItem(Strings.Items.SCEPTRE_OF_LIGHTNING_NAME);
			ModIDs.SCEPTRE_CAP_ID = configItem(Strings.Items.SCEPTRE_CAP_NAME);
			ModIDs.WITHER_SHRUB_SEEDS_ID = configItem(Strings.Items.WITHER_SHRUB_SEEDS_NAME);
			ModIDs.LIVING_SOUL_ID = configItem(Strings.Items.LIVING_SOUL_NAME);
			ModIDs.IRON_NUGGET_ID = configItem(Strings.Items.IRON_NUGGET_NAME);
			ModIDs.FLOUR_ID = configItem(Strings.Items.FLOUR_NAME);
			ModIDs.HELL_BUSH_SEEDS_ID = configItem(Strings.Items.HELL_BUSH_SEEDS_NAME);
			ModIDs.LAVA_BERRY_ID = configItem(Strings.Items.LAVA_BERRY_NAME);
			ModIDs.NETHER_CORE_ID = configItem(Strings.Items.NETHER_CORE_NAME);
			ModIDs.SPAWNER_UPGRADE_ID = configItem(Strings.Items.SPAWNER_UPGRADE_NAME);

			// Mod Integration
			for (Integration integration : ModIntegrator.modIntegrations)
				integration.setShouldIntegrate(configIntegrationBoolean(integration.getModID()));

			// Others
			GanysNether.sceptreOfConcealmentDurability = configDurability(Strings.Items.SCEPTRE_OF_CONCEALMENT_NAME, 128);
			GanysNether.sceptreOfLightningDurability = configDurability(Strings.Items.SCEPTRE_OF_LIGHTNING_NAME, 128);
			GanysNether.sceptreOfFireCharging = configDurability(Strings.Items.SCEPTRE_OF_FIRE_CHARGING_NAME, 32);
			GanysNether.baseballBatDurability = configDurability(Strings.Items.BASEBALL_BAT_NAME, 256);

			GanysNether.shouldGenerateCrops = configBoolean(Strings.Others.SHOULD_GENERATE_CROPS, true);
			GanysNether.shouldGenerateUndertakers = configBoolean(Strings.Others.SHOULD_GENERATE_UNDERTAKERS, true);
			GanysNether.shouldDoVersionCheck = configBoolean(Strings.Others.SHOULD_DO_VERSION_CHECK, true);
			GanysNether.shouldGhastTearHaveDispenserAction = configBoolean(Strings.Others.SHOULD_GHAST_TEAR_HAVE_DISPENSER_ACTION, true);
			GanysNether.enableUndertaker = configBoolean(Strings.Others.ENABLE_UNDERTAKER, true);

			GanysNether.netherCropRate = configInteger(Strings.Others.NETHER_CROP_RATE, GanysNether.netherCropRate);
			GanysNether.witherShrubRate = configInteger(Strings.Others.WITHER_SHRUB_RATE, GanysNether.witherShrubRate);
			GanysNether.undertakerRate = configInteger(Strings.Others.UNDERTAKER_RATE, GanysNether.undertakerRate);
			GanysNether.undertakerFillSlotChance = configInteger(Strings.Others.UNDERTAKER_FILL_SLOT_CHANCE, GanysNether.undertakerFillSlotChance);

		} catch (Exception e) {
			FMLLog.log(Level.SEVERE, e, Reference.MOD_NAME + " has had a problem loading its configuration");
			throw new RuntimeException(e);
		} finally {
			configuration.save();
		}

	}
}