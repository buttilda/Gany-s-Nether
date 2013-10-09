package ganymedes01.ganysnether.items;

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
	public static Item sceptreOfFireCharging;
	public static Item sceptreOfLightning;
	public static Item sceptreCap;

	// Armour
	public static Item blazeHelmet;
	public static Item blazeChestplate;
	public static Item blazeLeggings;
	public static Item blazeBoots;

	public static void init() {
		// Armour
		blazeHelmet = new BlazeHelmet();
		blazeChestplate = new BlazeChestplate();
		blazeLeggings = new BlazeLeggings();
		blazeBoots = new BlazeBoots();

		// Items
		quarzBerrySeeds = new QuarzBerrySeeds();
		quarzBerry = new QuarzBerry();
		ghostSeeds = new GhostSeeds();
		spectreWheat = new SpectreWheat();
		spookyFlour = new SpookyFlour();
		glowingReed = new GlowingReed();
		bottomlessBucket = new BottomlessBucket();
		dimensionalBread = new DimensionalBread();
		baseballBat = new BaseballBat();
		sceptreOfConcealment = new SceptreOfConcealment();
		skeletonSpawner = new SkeletonSpawner();
		silverfishScale = new SilverfishScale();
		batWing = new BatWing();
		cookedBatWing = new CookedBatWing();
		wolfTeeth = new WolfTeeth();
		blazeIngot = new BlazeIngot();
		sceptreOfFireCharging = new SceptreOfFireCharging();
		sceptreOfLightning = new SceptreOfLightning();
		sceptreCap = new SceptreCap();

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
		GameRegistry.registerItem(sceptreOfFireCharging, Strings.SCEPTRE_OF_FIRE_CHARGING_NAME);
		GameRegistry.registerItem(sceptreOfLightning, Strings.SCEPTRE_OF_LIGHTNING_NAME);
		GameRegistry.registerItem(sceptreCap, Strings.SCEPTRE_CAP_NAME);

		// Armour
		GameRegistry.registerItem(blazeHelmet, Strings.BLAZE_HELMET_NAME);
		GameRegistry.registerItem(blazeChestplate, Strings.BLAZE_CHESTPLATE_NAME);
		GameRegistry.registerItem(blazeLeggings, Strings.BLAZE_LEGGINGS_NAME);
		GameRegistry.registerItem(blazeBoots, Strings.BLAZE_BOOTS_NAME);
	}
}
