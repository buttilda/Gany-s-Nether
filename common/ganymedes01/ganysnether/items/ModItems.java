package ganymedes01.ganysnether.items;

import ganymedes01.ganysnether.lib.ModIDs;
import ganymedes01.ganysnether.lib.Strings;
import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class ModItems {

	// Items
	public static Item quarzBerrySeeds;
	public static Item quarzBerry;
	public static Item ghostSeeds;
	public static Item spectreWheat;
	public static Item spookyFlour;
	public static Item glowingReed;
	public static Item bottomlessBucket;
	public static Item dimensionalBread;
	public static Item baseballBat;
	public static Item sceptreOfConcealment;
	public static Item skeletonSpawner;
	public static Item silverfishScale;
	public static Item batWing;
	public static Item cookedBatWing;
	public static Item wolfTeeth;
	public static Item blazeIngot;

	// Armour
	public static Item blazeHelmet;
	public static Item blazeChestplate;
	public static Item blazeLeggings;
	public static Item blazeBoots;

	public static void init() {
		// Armour
		blazeHelmet = new BlazeHelmet(ModIDs.BLAZE_HELMET_ID);
		blazeChestplate = new BlazeChestplate(ModIDs.BLAZE_CHESTPLATE_ID);
		blazeLeggings = new BlazeLeggings(ModIDs.BLAZE_LEGGINGS_ID);
		blazeBoots = new BlazeBoots(ModIDs.BLAZE_BOOTS_ID);

		// Items
		quarzBerrySeeds = new QuarzBerrySeeds(ModIDs.QUARZ_BERRY_SEEDS_ID);
		quarzBerry = new QuarzBerry(ModIDs.QUARZ_BERRY_ID);
		ghostSeeds = new GhostSeeds(ModIDs.GHOST_SEEDS_ID);
		spectreWheat = new SpectreWheat(ModIDs.SPECTRE_WHEAT_ITEM_ID);
		spookyFlour = new SpookyFlour(ModIDs.SPOOKY_FLOUR_ID);
		glowingReed = new GlowingReed(ModIDs.GLOWING_REED_ITEM_ID);
		bottomlessBucket = new BottomlessBucket(ModIDs.BOTTOMLESS_BUCKET_ID);
		dimensionalBread = new DimensionalBread(ModIDs.DIMENSIONAL_BREAD_ID);
		baseballBat = new BaseballBat(ModIDs.BASEBALL_BAT_ID);
		sceptreOfConcealment = new SceptreOfConcealment(ModIDs.SCEPTRE_OF_CONCEALMENT_ID);
		skeletonSpawner = new SkeletonSpawner(ModIDs.SKELETON_SPAWNER_ID);
		silverfishScale = new SilverfishScale(ModIDs.SILVERFISH_SCALE_ID);
		batWing = new BatWing(ModIDs.BAT_WING_ID);
		cookedBatWing = new CookedBatWing(ModIDs.COOKED_BAT_WING_ID);
		wolfTeeth = new WolfTeeth(ModIDs.WOLF_TEETH_ID);
		blazeIngot = new BlazeIngot(ModIDs.BLAZE_INGOT_ID);

		registerNames();
	}

	private static void registerNames() {
		GameRegistry.registerItem(quarzBerrySeeds, Strings.QUARZ_BERRY_SEEDS_NAME);
		GameRegistry.registerItem(quarzBerry, Strings.QUARZ_BERRY_NAME);
		GameRegistry.registerItem(ghostSeeds, Strings.GHOST_SEEDS_NAME);
		GameRegistry.registerItem(spectreWheat, Strings.SPECTRE_WHEAT_NAME);
		GameRegistry.registerItem(spookyFlour, Strings.SPOOKY_FLOUR_NAME);
		GameRegistry.registerItem(glowingReed, Strings.GLOWING_REED_NAME);
		GameRegistry.registerItem(bottomlessBucket, Strings.BOTTOMLESS_BUCKET_NAME);
		GameRegistry.registerItem(dimensionalBread, Strings.DIMENSIONAL_BREAD_NAME);
		GameRegistry.registerItem(baseballBat, Strings.BASEBALL_BAT_NAME);
		GameRegistry.registerItem(sceptreOfConcealment, Strings.SCEPTRE_OF_CONCEALMENT_NAME);
		GameRegistry.registerItem(skeletonSpawner, Strings.SKELETON_SPAWNER_NAME);
		GameRegistry.registerItem(silverfishScale, Strings.SILVERFISH_SCALE_NAME);
		GameRegistry.registerItem(batWing, Strings.BAT_WING_NAME);
		GameRegistry.registerItem(cookedBatWing, Strings.COOKED_BAT_WING_NAME);
		GameRegistry.registerItem(wolfTeeth, Strings.WOLF_TEETH_NAME);
		GameRegistry.registerItem(blazeIngot, Strings.BLAZE_INGOT_NAME);

		// Armour
		GameRegistry.registerItem(blazeHelmet, Strings.BLAZE_HELMET_NAME);
		GameRegistry.registerItem(blazeChestplate, Strings.BLAZE_CHESTPLATE_NAME);
		GameRegistry.registerItem(blazeLeggings, Strings.BLAZE_LEGGINGS_NAME);
		GameRegistry.registerItem(blazeBoots, Strings.BLAZE_BOOTS_NAME);
	}
}
