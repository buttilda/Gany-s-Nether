package ganymedes01.ganysnether.configuration;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.lib.BlocksID;
import ganymedes01.ganysnether.lib.ItemsID;
import ganymedes01.ganysnether.lib.Reference;
import ganymedes01.ganysnether.lib.Strings;

import java.io.File;
import java.util.logging.Level;

import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.FMLLog;

public class ConfigurationHandler {

	public static Configuration configuration;

	public static void init(File configFile) {
		configuration = new Configuration(configFile);

		try {
			configuration.load();

			// Blocks
			BlocksID.TILLED_NETHERRACK_ID = configuration.getBlock(Strings.TILLED_NETHERRACK_NAME, BlocksID.TILLED_NETHERRACK_ID_DEFAULT).getInt(BlocksID.TILLED_NETHERRACK_ID_DEFAULT);
			BlocksID.QUARZ_BERRY_BUSH_ID = configuration.getBlock(Strings.QUARZ_BERRY_BUSH_NAME, BlocksID.QUARZ_BERRY_BUSH_ID_DEFAULT).getInt(BlocksID.QUARZ_BERRY_BUSH_ID_DEFAULT);
			BlocksID.SPECTRE_WHEAT_ID = configuration.getBlock(Strings.SPECTRE_WHEAT_NAME, BlocksID.SPECTRE_WHEAT_ID_DEFAULT).getInt(BlocksID.SPECTRE_WHEAT_ID_DEFAULT);
			BlocksID.GLOWING_REED_ID = configuration.getBlock(Strings.GLOWING_REED_NAME, BlocksID.GLOWING_REED_ID_DEFAULT).getInt(BlocksID.GLOWING_REED_ID_DEFAULT);
			BlocksID.SOUL_GLASS_ID = configuration.getBlock(Strings.SOUL_GLASS_NAME, BlocksID.SOUL_GLASS_ID_DEFAULT).getInt(BlocksID.SOUL_GLASS_ID_DEFAULT);
			BlocksID.SOUL_CHEST_ID = configuration.getBlock(Strings.SOUL_CHEST_NAME, BlocksID.SOUL_CHEST_ID_DEFAULT).getInt(BlocksID.SOUL_CHEST_ID_DEFAULT);
			BlocksID.VOLCANIC_FURNACE_IDLE_ID = configuration.getBlock(Strings.VOLCANIC_FURNACE_NAME + "idle", BlocksID.VOLCANIC_FURNACE_IDLE_ID_DEFAULT).getInt(BlocksID.VOLCANIC_FURNACE_IDLE_ID_DEFAULT);
			BlocksID.VOLCANIC_FURNACE_ACTIVE_ID = configuration.getBlock(Strings.VOLCANIC_FURNACE_NAME + "_active", BlocksID.VOLCANIC_FURNACE_ACTIVE_ID_DEFAULT).getInt(BlocksID.VOLCANIC_FURNACE_ACTIVE_ID_DEFAULT);
			BlocksID.DENSE_LAVA_CELL_ID = configuration.getBlock(Strings.DENSE_LAVA_CELL_NAME, BlocksID.DENSE_LAVA_CELL_ID_DEFAULT).getInt(BlocksID.DENSE_LAVA_CELL_ID_DEFAULT);
			BlocksID.GLOW_BOX_ID = configuration.getBlock(Strings.GLOW_BOX_NAME, BlocksID.GLOW_BOX_ID_DEFAULT).getInt(BlocksID.GLOW_BOX_ID_DEFAULT);
			BlocksID.COLOURED_QUARTZ_BLOCK_ID = configuration.getBlock(Strings.COLOURED_QUARTZ_BLOCK_NAME, BlocksID.COLOURED_QUARTZ_BLOCK_ID_DEFAULT).getInt(BlocksID.COLOURED_QUARTZ_BLOCK_ID_DEFAULT);
			BlocksID.COLOURED_CHISELLED_QUARTZ_BLOCK_ID = configuration.getBlock(Strings.COLOURED_CHISELLED_QUARTZ_BLOCK_NAME, BlocksID.COLOURED_CHISELLED_QUARTZ_BLOCK_ID_DEFAULT).getInt(BlocksID.COLOURED_CHISELLED_QUARTZ_BLOCK_ID_DEFAULT);
			BlocksID.SOUL_GLASS_STAIRS_ID = configuration.getBlock(Strings.SOUL_GLASS_STAIRS_NAME, BlocksID.SOUL_GLASS_STAIRS_ID_DEFAULT).getInt(BlocksID.SOUL_GLASS_STAIRS_ID_DEFAULT);
			for (int i = 0; i < BlocksID.COLOURED_QUARTZ_PILLARS_IDS.length; i++)
				BlocksID.COLOURED_QUARTZ_PILLARS_IDS[i] = configuration.getBlock(Strings.COLOURED_QUARTZ_PILLARS_NAME + i, BlocksID.COLOURED_QUARTZ_PILLARS_IDS_DEFAULT[i]).getInt(BlocksID.COLOURED_QUARTZ_PILLARS_IDS_DEFAULT[i]);
			BlocksID.REPRODUCER_ID = configuration.getBlock(Strings.REPRODUCER_NAME, BlocksID.REPRODUCER_ID_DEFAULT).getInt(BlocksID.REPRODUCER_ID_DEFAULT);

			// Stairs
			for (int i = 0; i < BlocksID.COLOURED_QUARTZ_STAIRS_IDS.length; i++)
				BlocksID.COLOURED_QUARTZ_STAIRS_IDS[i] = configuration.get("Coloured Quartz Stairs", Strings.COLOURED_QUARTZ_STAIRS_NAMES[i], BlocksID.COLOURED_QUARTZ_STAIRS_IDS_DEFAULT[i]).getInt(BlocksID.COLOURED_QUARTZ_STAIRS_IDS_DEFAULT[i]);

			// Items
			ItemsID.QUARZ_BERRY_SEEDS_ID = configuration.getItem(Strings.QUARZ_BERRY_SEEDS_NAME, ItemsID.QUARZ_BERRY_SEEDS_ID_DEFAULT).getInt(ItemsID.QUARZ_BERRY_SEEDS_ID_DEFAULT);
			ItemsID.QUARZ_BERRY_ID = configuration.getItem(Strings.QUARZ_BERRY_NAME, ItemsID.QUARZ_BERRY_ID_DEFAULT).getInt(ItemsID.QUARZ_BERRY_ID_DEFAULT);
			ItemsID.GHOST_SEEDS_ID = configuration.getItem(Strings.GHOST_SEEDS_NAME, ItemsID.GHOST_SEEDS_ID_DEFAULT).getInt(ItemsID.GHOST_SEEDS_ID_DEFAULT);
			ItemsID.SPECTRE_WHEAT_ID = configuration.getItem(Strings.SPECTRE_WHEAT_NAME, ItemsID.SPECTRE_WHEAT_ID_DEFAULT).getInt(ItemsID.SPECTRE_WHEAT_ID_DEFAULT);
			ItemsID.SPOOKY_FLOUR_ID = configuration.getItem(Strings.SPOOKY_FLOUR_NAME, ItemsID.SPOOKY_FLOUR_ID_DEFAULT).getInt(ItemsID.SPOOKY_FLOUR_ID_DEFAULT);
			ItemsID.GLOWING_REED_ID = configuration.getItem(Strings.GLOWING_REED_NAME, ItemsID.GLOWING_REED_ID_DEFAULT).getInt(ItemsID.GLOWING_REED_ID_DEFAULT);
			ItemsID.BOTTOMLESS_BUCKET_ID = configuration.getItem(Strings.BOTTOMLESS_BUCKET_NAME, ItemsID.BOTTOMLESS_BUCKET_ID_DEFAULT).getInt(ItemsID.BOTTOMLESS_BUCKET_ID_DEFAULT);
			ItemsID.DIMENSIONAL_BREAD_ID = configuration.getItem(Strings.DIMENSIONAL_BREAD_NAME, ItemsID.DIMENSIONAL_BREAD_ID_DEFAULT).getInt(ItemsID.DIMENSIONAL_BREAD_ID_DEFAULT);
			ItemsID.BASEBALL_BAT_ID = configuration.getItem(Strings.BASEBALL_BAT_NAME, ItemsID.BASEBALL_BAT_ID_DEFAULT).getInt(ItemsID.BASEBALL_BAT_ID_DEFAULT);
			ItemsID.SCEPTRE_OF_CONCEALMENT_ID = configuration.getItem(Strings.SCEPTRE_OF_CONCEALMENT_NAME, ItemsID.SCEPTRE_OF_CONCEALMENT_ID_DEFAULT).getInt(ItemsID.SCEPTRE_OF_CONCEALMENT_ID_DEFAULT);
			ItemsID.SKELETON_SPAWNER_ID = configuration.getItem(Strings.SKELETON_SPAWNER_NAME, ItemsID.SKELETON_SPAWNER_ID_DEFAULT).getInt(ItemsID.SKELETON_SPAWNER_ID_DEFAULT);
			ItemsID.SILVERFISH_SCALE_ID = configuration.getItem(Strings.SILVERFISH_SCALE_NAME, ItemsID.SILVERFISH_SCALE_ID_DEFAULT).getInt(ItemsID.SILVERFISH_SCALE_ID_DEFAULT);
			ItemsID.BAT_WING_ID = configuration.getItem(Strings.BAT_WING_NAME, ItemsID.BAT_WING_ID_DEFAULT).getInt(ItemsID.BAT_WING_ID_DEFAULT);
			ItemsID.COOKED_BAT_WING_ID = configuration.getItem(Strings.COOKED_BAT_WING_NAME, ItemsID.COOKED_BAT_WING_ID_DEFAULT).getInt(ItemsID.COOKED_BAT_WING_ID_DEFAULT);
			ItemsID.WOLF_TEETH_ID = configuration.getItem(Strings.WOLF_TEETH_NAME, ItemsID.WOLF_TEETH_ID_DEFAULT).getInt(ItemsID.WOLF_TEETH_ID_DEFAULT);
			ItemsID.BLAZE_INGOT_ID = configuration.getItem(Strings.BLAZE_INGOT_NAME, ItemsID.BLAZE_INGOT_ID_DEFAULT).getInt(ItemsID.BLAZE_INGOT_ID_DEFAULT);

			// Armour
			ItemsID.BLAZE_HELMET_ID = configuration.getItem(Strings.BLAZE_HELMET_NAME, ItemsID.BLAZE_HELMET_ID_DEFAULT).getInt(ItemsID.BLAZE_HELMET_ID_DEFAULT);
			ItemsID.BLAZE_CHESTPLATE_ID = configuration.getItem(Strings.BLAZE_CHESTPLATE_NAME, ItemsID.BLAZE_CHESTPLATE_ID_DEFAULT).getInt(ItemsID.BLAZE_CHESTPLATE_ID_DEFAULT);
			ItemsID.BLAZE_LEGGINGS_ID = configuration.getItem(Strings.BLAZE_LEGGINGS_NAME, ItemsID.BLAZE_LEGGINGS_ID_DEFAULT).getInt(ItemsID.BLAZE_LEGGINGS_ID_DEFAULT);
			ItemsID.BLAZE_BOOTS_ID = configuration.getItem(Strings.BLAZE_BOOTS_NAME, ItemsID.BLAZE_BOOTS_ID_DEFAULT).getInt(ItemsID.BLAZE_BOOTS_ID_DEFAULT);

			// Others
			GanysNether.sceptreOfConcealmentDurability = configuration.get("Durability", Strings.SCEPTRE_OF_CONCEALMENT_NAME, 128).getInt(128);
			GanysNether.baseballBatDurability = configuration.get("Durability", Strings.BASEBALL_BAT_NAME, 256).getInt(256);
			GanysNether.shouldGenerateCrops = configuration.get("Others", Strings.SHOULD_GENERATE_CROPS, true).getBoolean(true);

		} catch (Exception e) {
			FMLLog.log(Level.SEVERE, e, Reference.MOD_NAME + " has had a problem loading its configuration");
			throw new RuntimeException(e);
		} finally {
			configuration.save();
		}

	}
}
