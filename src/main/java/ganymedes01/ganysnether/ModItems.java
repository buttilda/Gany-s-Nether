package ganymedes01.ganysnether;

import ganymedes01.ganysnether.dispenser.DispenserBehaviorBottomlessBucket;
import ganymedes01.ganysnether.dispenser.DispenserBehaviorLivingSoul;
import ganymedes01.ganysnether.dispenser.DispenserBehaviorSceptreOfFireCharging;
import ganymedes01.ganysnether.dispenser.DispenserBehaviorSceptreOfLightning;
import ganymedes01.ganysnether.dispenser.DispenserBehaviorSceptreOfWithering;
import ganymedes01.ganysnether.dispenser.DispenserBehaviorSkeletonSpawner;
import ganymedes01.ganysnether.dispenser.DispenserBehaviorWeepingPod;
import ganymedes01.ganysnether.items.BaseballBat;
import ganymedes01.ganysnether.items.BatWing;
import ganymedes01.ganysnether.items.BlazeBoots;
import ganymedes01.ganysnether.items.BlazeChestplate;
import ganymedes01.ganysnether.items.BlazeHelmet;
import ganymedes01.ganysnether.items.BlazeIngot;
import ganymedes01.ganysnether.items.BlazeLeggings;
import ganymedes01.ganysnether.items.BottomlessBucket;
import ganymedes01.ganysnether.items.CookedBatWing;
import ganymedes01.ganysnether.items.DimensionalBread;
import ganymedes01.ganysnether.items.Flour;
import ganymedes01.ganysnether.items.GhostSeeds;
import ganymedes01.ganysnether.items.GlowingReed;
import ganymedes01.ganysnether.items.HellBushSeeds;
import ganymedes01.ganysnether.items.IronNugget;
import ganymedes01.ganysnether.items.LavaBerry;
import ganymedes01.ganysnether.items.LivingSoul;
import ganymedes01.ganysnether.items.NetherCore;
import ganymedes01.ganysnether.items.QuarzBerry;
import ganymedes01.ganysnether.items.QuarzBerrySeeds;
import ganymedes01.ganysnether.items.SceptreCap;
import ganymedes01.ganysnether.items.SceptreOfConcealment;
import ganymedes01.ganysnether.items.SceptreOfFireCharging;
import ganymedes01.ganysnether.items.SceptreOfLightning;
import ganymedes01.ganysnether.items.SceptreOfWithering;
import ganymedes01.ganysnether.items.SilverfishScale;
import ganymedes01.ganysnether.items.SkeletonSpawner;
import ganymedes01.ganysnether.items.SpawnerUpgrade;
import ganymedes01.ganysnether.items.SpectreWheat;
import ganymedes01.ganysnether.items.SpookyFlour;
import ganymedes01.ganysnether.items.WitherShrubSeeds;
import ganymedes01.ganysnether.items.WolfTeeth;
import net.minecraft.block.BlockDispenser;
import net.minecraft.init.Items;
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
	public static final Item quarzBerrySeeds = new QuarzBerrySeeds();
	public static final Item quarzBerry = new QuarzBerry();
	public static final Item ghostSeeds = new GhostSeeds();
	public static final Item spectreWheat = new SpectreWheat();
	public static final Item spookyFlour = new SpookyFlour();
	public static final Item glowingReed = new GlowingReed();
	public static final Item bottomlessBucket = new BottomlessBucket();
	public static final Item dimensionalBread = new DimensionalBread();
	public static final Item baseballBat = new BaseballBat();
	public static final Item sceptreOfConcealment = new SceptreOfConcealment();
	public static final Item skeletonSpawner = new SkeletonSpawner();
	public static final Item silverfishScale = new SilverfishScale();
	public static final Item batWing = new BatWing();
	public static final Item cookedBatWing = new CookedBatWing();
	public static final Item wolfTeeth = new WolfTeeth();
	public static final Item blazeIngot = new BlazeIngot();
	public static final Item sceptreOfFireCharging = new SceptreOfFireCharging();
	public static final Item sceptreOfLightning = new SceptreOfLightning();
	public static final Item sceptreCap = new SceptreCap();
	public static final Item witherShrubSeeds = new WitherShrubSeeds();
	public static final Item livingSoul = new LivingSoul();
	public static final Item ironNugget = new IronNugget();
	public static final Item flour = new Flour();
	public static final Item hellBushSeeds = new HellBushSeeds();
	public static final Item lavaBerry = new LavaBerry();
	public static final Item netherCore = new NetherCore();
	public static final Item spawnerUpgrade = new SpawnerUpgrade();
	public static final Item sceptreOfWithering = new SceptreOfWithering();

	// Armour
	public static final Item blazeHelmet = new BlazeHelmet();
	public static final Item blazeChestplate = new BlazeChestplate();
	public static final Item blazeLeggings = new BlazeLeggings();
	public static final Item blazeBoots = new BlazeBoots();

	public static void init() {
		registerItem(quarzBerrySeeds);
		registerItem(quarzBerry);
		registerItem(ghostSeeds);
		registerItem(spectreWheat);
		registerItem(spookyFlour);
		registerItem(glowingReed);
		registerItem(bottomlessBucket);
		registerItem(dimensionalBread);
		registerItem(baseballBat);
		registerItem(sceptreOfConcealment);
		registerItem(skeletonSpawner);
		registerItem(silverfishScale);
		registerItem(batWing);
		registerItem(cookedBatWing);
		registerItem(wolfTeeth);
		registerItem(blazeIngot);
		registerItem(sceptreOfFireCharging);
		registerItem(sceptreOfLightning);
		registerItem(sceptreCap);
		registerItem(witherShrubSeeds);
		registerItem(livingSoul);
		registerItem(ironNugget);
		registerItem(flour);
		registerItem(hellBushSeeds);
		registerItem(lavaBerry);
		registerItem(netherCore);
		registerItem(spawnerUpgrade);
		registerItem(sceptreOfWithering);

		// Armour
		registerItem(blazeHelmet);
		registerItem(blazeChestplate);
		registerItem(blazeLeggings);
		registerItem(blazeBoots);

		BlockDispenser.dispenseBehaviorRegistry.putObject(skeletonSpawner, new DispenserBehaviorSkeletonSpawner());
		BlockDispenser.dispenseBehaviorRegistry.putObject(livingSoul, new DispenserBehaviorLivingSoul());
		BlockDispenser.dispenseBehaviorRegistry.putObject(bottomlessBucket, new DispenserBehaviorBottomlessBucket());
		BlockDispenser.dispenseBehaviorRegistry.putObject(sceptreOfFireCharging, new DispenserBehaviorSceptreOfFireCharging());
		BlockDispenser.dispenseBehaviorRegistry.putObject(sceptreOfLightning, new DispenserBehaviorSceptreOfLightning());
		BlockDispenser.dispenseBehaviorRegistry.putObject(sceptreOfWithering, new DispenserBehaviorSceptreOfWithering());
		if (GanysNether.shouldGhastTearHaveDispenserAction)
			BlockDispenser.dispenseBehaviorRegistry.putObject(Items.ghast_tear, new DispenserBehaviorWeepingPod());
	}

	private static void registerItem(Item item) {
		String name = item.getUnlocalizedName();
		String[] strings = name.split("\\.");
		GameRegistry.registerItem(item, strings[strings.length - 1]);
	}
}