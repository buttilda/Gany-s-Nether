package ganymedes01.ganysnether.configuration;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.core.utils.IdGenerator;
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

	public static void init(File configFile) {
		configuration = new Configuration(configFile);

		try {
			configuration.load();

			// Blocks
			ModIDs.TILLED_NETHERRACK_ID = configBlock(Strings.TILLED_NETHERRACK_NAME);
			ModIDs.QUARZ_BERRY_BUSH_ID = configBlock(Strings.QUARZ_BERRY_BUSH_NAME);
			ModIDs.SPECTRE_WHEAT_CROP_ID = configBlock(Strings.SPECTRE_WHEAT_NAME);
			ModIDs.GLOWING_REED_CROP_ID = configBlock(Strings.GLOWING_REED_NAME);
			ModIDs.SOUL_GLASS_ID = configBlock(Strings.SOUL_GLASS_NAME);
			ModIDs.SOUL_CHEST_ID = configBlock(Strings.SOUL_CHEST_NAME);
			ModIDs.VOLCANIC_FURNACE_IDLE_ID = configBlock(Strings.VOLCANIC_FURNACE_NAME + "idle");
			ModIDs.VOLCANIC_FURNACE_ACTIVE_ID = configBlock(Strings.VOLCANIC_FURNACE_NAME + "_active");
			ModIDs.DENSE_LAVA_CELL_ID = configBlock(Strings.DENSE_LAVA_CELL_NAME);
			ModIDs.GLOW_BOX_ID = configBlock(Strings.GLOW_BOX_NAME);
			ModIDs.COLOURED_QUARTZ_BLOCK_ID = configBlock(Strings.COLOURED_QUARTZ_BLOCK_NAME);
			ModIDs.COLOURED_CHISELLED_QUARTZ_BLOCK_ID = configBlock(Strings.COLOURED_CHISELLED_QUARTZ_BLOCK_NAME);
			for (int i = 0; i < ModIDs.COLOURED_QUARTZ_STAIRS_IDS.length; i++)
				ModIDs.COLOURED_QUARTZ_STAIRS_IDS[i] = configBlock(Strings.COLOURED_QUARTZ_STAIRS_NAMES[i]);
			ModIDs.SOUL_GLASS_STAIRS_ID = configBlock(Strings.SOUL_GLASS_STAIRS_NAME);
			for (int i = 0; i < ModIDs.COLOURED_QUARTZ_PILLARS_IDS.length; i++)
				ModIDs.COLOURED_QUARTZ_PILLARS_IDS[i] = configBlock(Strings.COLOURED_QUARTZ_PILLARS_NAME + i);
			ModIDs.REPRODUCER_ID = configBlock(Strings.REPRODUCER_NAME);
			ModIDs.UNDERTAKER_ID = configBlock(Strings.UNDERTAKER_NAME);
			ModIDs.WITHER_SHRUB_ID = configBlock(Strings.WITHER_SHRUB_NAME);
			ModIDs.MAGMATIC_CENTRIFUGE_ID = configBlock(Strings.MAGMATIC_CENTRIFUGE_NAME);
			ModIDs.WEEPING_POD_ID = configBlock(Strings.WEEPING_POD_NAME);
			ModIDs.SOUL_TNT_ID = configBlock(Strings.SOUL_TNT_NAME);
			ModIDs.BLAZING_CACTOID_ID = configBlock(Strings.BLAZING_CACTOID_NAME);
			ModIDs.HELL_BUSH_ID = configBlock(Strings.HELL_BUSH_NAME);

			// Armour
			ModIDs.BLAZE_HELMET_ID = configItem(Strings.BLAZE_HELMET_NAME);
			ModIDs.BLAZE_CHESTPLATE_ID = configItem(Strings.BLAZE_CHESTPLATE_NAME);
			ModIDs.BLAZE_LEGGINGS_ID = configItem(Strings.BLAZE_LEGGINGS_NAME);
			ModIDs.BLAZE_BOOTS_ID = configItem(Strings.BLAZE_BOOTS_NAME);

			// Items
			ModIDs.QUARZ_BERRY_SEEDS_ID = configItem(Strings.QUARZ_BERRY_SEEDS_NAME);
			ModIDs.QUARZ_BERRY_ID = configItem(Strings.QUARZ_BERRY_NAME);
			ModIDs.GHOST_SEEDS_ID = configItem(Strings.GHOST_SEEDS_NAME);
			ModIDs.SPECTRE_WHEAT_ITEM_ID = configItem(Strings.SPECTRE_WHEAT_NAME);
			ModIDs.SPOOKY_FLOUR_ID = configItem(Strings.SPOOKY_FLOUR_NAME);
			ModIDs.GLOWING_REED_ITEM_ID = configItem(Strings.GLOWING_REED_NAME);
			ModIDs.BOTTOMLESS_BUCKET_ID = configItem(Strings.BOTTOMLESS_BUCKET_NAME);
			ModIDs.DIMENSIONAL_BREAD_ID = configItem(Strings.DIMENSIONAL_BREAD_NAME);
			ModIDs.BASEBALL_BAT_ID = configItem(Strings.BASEBALL_BAT_NAME);
			ModIDs.SCEPTRE_OF_CONCEALMENT_ID = configItem(Strings.SCEPTRE_OF_CONCEALMENT_NAME);
			ModIDs.SKELETON_SPAWNER_ID = configItem(Strings.SKELETON_SPAWNER_NAME);
			ModIDs.SILVERFISH_SCALE_ID = configItem(Strings.SILVERFISH_SCALE_NAME);
			ModIDs.BAT_WING_ID = configItem(Strings.BAT_WING_NAME);
			ModIDs.COOKED_BAT_WING_ID = configItem(Strings.COOKED_BAT_WING_NAME);
			ModIDs.WOLF_TEETH_ID = configItem(Strings.WOLF_TEETH_NAME);
			ModIDs.BLAZE_INGOT_ID = configItem(Strings.BLAZE_INGOT_NAME);
			ModIDs.SCEPTRE_OF_FIRE_CHARGING_ID = configItem(Strings.SCEPTRE_OF_FIRE_CHARGING_NAME);
			ModIDs.SCEPTRE_OF_LIGHTNING_ID = configItem(Strings.SCEPTRE_OF_LIGHTNING_NAME);
			ModIDs.SCEPTRE_CAP_ID = configItem(Strings.SCEPTRE_CAP_NAME);
			ModIDs.WITHER_SHRUB_SEEDS_ID = configItem(Strings.WITHER_SHRUB_SEEDS_NAME);
			ModIDs.LIVING_SOUL_ID = configItem(Strings.LIVING_SOUL_NAME);
			ModIDs.IRON_NUGGET_ID = configItem(Strings.IRON_NUGGET_NAME);
			ModIDs.FLOUR_ID = configItem(Strings.FLOUR_NAME);
			ModIDs.HELL_BUSH_SEEDS_ID = configItem(Strings.HELL_BUSH_SEEDS_NAME);
			ModIDs.LAVA_BERRY_ID = configItem(Strings.LAVA_BERRY_NAME);

			// Others
			GanysNether.sceptreOfConcealmentDurability = configDurability(Strings.SCEPTRE_OF_CONCEALMENT_NAME, 128);
			GanysNether.sceptreOfLightningDurability = configDurability(Strings.SCEPTRE_OF_LIGHTNING_NAME, 128);
			GanysNether.sceptreOfFireCharging = configDurability(Strings.SCEPTRE_OF_FIRE_CHARGING_NAME, 32);
			GanysNether.baseballBatDurability = configDurability(Strings.BASEBALL_BAT_NAME, 256);

			GanysNether.shouldGenerateCrops = configBoolean(Strings.SHOULD_GENERATE_CROPS, true);
			GanysNether.shouldGenerateUndertakers = configBoolean(Strings.SHOULD_GENERATE_UNDERTAKERS, true);
			GanysNether.shouldDoVersionCheck = configBoolean(Strings.SHOULD_DO_VERSION_CHECK, true);
			GanysNether.shouldGhastTearHaveDispenserAction = configBoolean(Strings.SHOULD_GHAST_TEAR_HAVE_DISPENSER_ACTION, true);

			GanysNether.netherCropRate = configInteger(Strings.NETHER_CROP_RATE, 20);
			GanysNether.witherShrubRate = configInteger(Strings.WITHER_SHRUB_RATE, 50);
			GanysNether.undertakerRate = configInteger(Strings.UNDERTAKER_RATE, 300);
			GanysNether.undertakerFillSlotChance = configInteger(Strings.UNDERTAKER_FILL_SLOT_CHANCE, 10);

		} catch (Exception e) {
			FMLLog.log(Level.SEVERE, e, Reference.MOD_NAME + " has had a problem loading its configuration");
			throw new RuntimeException(e);
		} finally {
			configuration.save();
		}

	}
}