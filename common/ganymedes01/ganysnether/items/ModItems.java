package ganymedes01.ganysnether.items;

import ganymedes01.ganysnether.lib.ItemsID;
import ganymedes01.ganysnether.lib.Strings;
import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;

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
		quarzBerrySeeds = new QuarzBerrySeeds(ItemsID.QUARZ_BERRY_SEEDS_ID);
		quarzBerry = new QuarzBerry(ItemsID.QUARZ_BERRY_ID);
		ghostSeeds = new GhostSeeds(ItemsID.GHOST_SEEDS_ID);
		spectreWheat = new SpectreWheat(ItemsID.SPECTRE_WHEAT_ID);
		spookyFlour = new SpookyFlour(ItemsID.SPOOKY_FLOUR_ID);
		glowingReed = new GlowingReed(ItemsID.GLOWING_REED_ID);
		bottomlessBucket = new BottomlessBucket(ItemsID.BOTTOMLESS_BUCKET_ID);
		dimensionalBread = new DimensionalBread(ItemsID.DIMENSIONAL_BREAD_ID);
		baseballBat = new BaseballBat(ItemsID.BASEBALL_BAT_ID);
		sceptreOfConcealment = new SceptreOfConcealment(ItemsID.SCEPTRE_OF_CONCEALMENT_ID);
		skeletonSpawner = new SkeletonSpawner(ItemsID.SKELETON_SPAWNER_ID);
		silverfishScale = new SilverfishScale(ItemsID.SILVERFISH_SCALE_ID);
		batWing = new BatWing(ItemsID.BAT_WING_ID);
		cookedBatWing = new CookedBatWing(ItemsID.COOKED_BAT_WING_ID);
		wolfTeeth = new WolfTeeth(ItemsID.WOLF_TEETH_ID);
		blazeIngot = new BlazeIngot(ItemsID.BLAZE_INGOT_ID);

		// Armour
		blazeHelmet = new BlazeHelmet(ItemsID.BLAZE_HELMET_ID);
		blazeChestplate = new BlazeChestplate(ItemsID.BLAZE_CHESTPLATE_ID);
		blazeLeggings = new BlazeLeggings(ItemsID.BLAZE_LEGGINGS_ID);
		blazeBoots = new BlazeBoots(ItemsID.BLAZE_BOOTS_ID);

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
