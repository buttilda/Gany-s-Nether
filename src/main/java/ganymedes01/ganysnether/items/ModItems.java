package ganymedes01.ganysnether.items;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.dispenser.DispenserBehaviorBottomlessBucket;
import ganymedes01.ganysnether.dispenser.DispenserBehaviorLivingSoul;
import ganymedes01.ganysnether.dispenser.DispenserBehaviorSceptreOfFireCharging;
import ganymedes01.ganysnether.dispenser.DispenserBehaviorSceptreOfLightning;
import ganymedes01.ganysnether.dispenser.DispenserBehaviorSkeletonSpawner;
import ganymedes01.ganysnether.dispenser.DispenserBehaviorWeepingPod;
import ganymedes01.ganysnether.lib.Strings;
import net.minecraft.block.BlockDispenser;
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
	public static Item witherShrubSeeds;
	public static Item livingSoul;
	public static Item ironNugget;
	public static Item flour;
	public static Item hellBushSeeds;
	public static Item lavaBerry;
	public static Item netherCore;
	public static Item spawnerUpgrade;

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
		witherShrubSeeds = new WitherShrubSeeds();
		livingSoul = new LivingSoul();
		ironNugget = new IronNugget();
		flour = new Flour();
		hellBushSeeds = new HellBushSeeds();
		lavaBerry = new LavaBerry();
		netherCore = new NetherCore();
		spawnerUpgrade = new SpawnerUpgrade();

		registerNames();
		registerDispenserActions();
	}

	private static void registerNames() {
		GameRegistry.registerItem(quarzBerrySeeds, Strings.Items.QUARZ_BERRY_SEEDS_NAME);
		GameRegistry.registerItem(quarzBerry, Strings.Items.QUARZ_BERRY_NAME);
		GameRegistry.registerItem(ghostSeeds, Strings.Items.GHOST_SEEDS_NAME);
		GameRegistry.registerItem(spectreWheat, Strings.Items.SPECTRE_WHEAT_NAME);
		GameRegistry.registerItem(spookyFlour, Strings.Items.SPOOKY_FLOUR_NAME);
		GameRegistry.registerItem(glowingReed, Strings.Items.GLOWING_REED_NAME);
		GameRegistry.registerItem(bottomlessBucket, Strings.Items.BOTTOMLESS_BUCKET_NAME);
		GameRegistry.registerItem(dimensionalBread, Strings.Items.DIMENSIONAL_BREAD_NAME);
		GameRegistry.registerItem(baseballBat, Strings.Items.BASEBALL_BAT_NAME);
		GameRegistry.registerItem(sceptreOfConcealment, Strings.Items.SCEPTRE_OF_CONCEALMENT_NAME);
		GameRegistry.registerItem(skeletonSpawner, Strings.Items.SKELETON_SPAWNER_NAME);
		GameRegistry.registerItem(silverfishScale, Strings.Items.SILVERFISH_SCALE_NAME);
		GameRegistry.registerItem(batWing, Strings.Items.BAT_WING_NAME);
		GameRegistry.registerItem(cookedBatWing, Strings.Items.COOKED_BAT_WING_NAME);
		GameRegistry.registerItem(wolfTeeth, Strings.Items.WOLF_TEETH_NAME);
		GameRegistry.registerItem(blazeIngot, Strings.Items.BLAZE_INGOT_NAME);
		GameRegistry.registerItem(sceptreOfFireCharging, Strings.Items.SCEPTRE_OF_FIRE_CHARGING_NAME);
		GameRegistry.registerItem(sceptreOfLightning, Strings.Items.SCEPTRE_OF_LIGHTNING_NAME);
		GameRegistry.registerItem(sceptreCap, Strings.Items.SCEPTRE_CAP_NAME);
		GameRegistry.registerItem(witherShrubSeeds, Strings.Items.WITHER_SHRUB_SEEDS_NAME);
		GameRegistry.registerItem(livingSoul, Strings.Items.LIVING_SOUL_NAME);
		GameRegistry.registerItem(ironNugget, Strings.Items.IRON_NUGGET_NAME);
		GameRegistry.registerItem(flour, Strings.Items.FLOUR_NAME);
		GameRegistry.registerItem(hellBushSeeds, Strings.Items.HELL_BUSH_SEEDS_NAME);
		GameRegistry.registerItem(lavaBerry, Strings.Items.LAVA_BERRY_NAME);
		GameRegistry.registerItem(netherCore, Strings.Items.NETHER_CORE_NAME);
		GameRegistry.registerItem(spawnerUpgrade, Strings.Items.SPAWNER_UPGRADE_NAME);

		// Armour
		GameRegistry.registerItem(blazeHelmet, Strings.Items.BLAZE_HELMET_NAME);
		GameRegistry.registerItem(blazeChestplate, Strings.Items.BLAZE_CHESTPLATE_NAME);
		GameRegistry.registerItem(blazeLeggings, Strings.Items.BLAZE_LEGGINGS_NAME);
		GameRegistry.registerItem(blazeBoots, Strings.Items.BLAZE_BOOTS_NAME);
	}

	private static void registerDispenserActions() {
		BlockDispenser.dispenseBehaviorRegistry.putObject(skeletonSpawner, new DispenserBehaviorSkeletonSpawner());
		BlockDispenser.dispenseBehaviorRegistry.putObject(livingSoul, new DispenserBehaviorLivingSoul());
		BlockDispenser.dispenseBehaviorRegistry.putObject(bottomlessBucket, new DispenserBehaviorBottomlessBucket());
		BlockDispenser.dispenseBehaviorRegistry.putObject(sceptreOfFireCharging, new DispenserBehaviorSceptreOfFireCharging());
		BlockDispenser.dispenseBehaviorRegistry.putObject(sceptreOfLightning, new DispenserBehaviorSceptreOfLightning());
		if (GanysNether.shouldGhastTearHaveDispenserAction)
			BlockDispenser.dispenseBehaviorRegistry.putObject(Item.ghastTear, new DispenserBehaviorWeepingPod());
	}
}