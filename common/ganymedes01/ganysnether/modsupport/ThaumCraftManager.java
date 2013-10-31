package ganymedes01.ganysnether.modsupport;

import ganymedes01.ganysnether.blocks.ModBlocks;
import ganymedes01.ganysnether.items.ModItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class ThaumCraftManager {

	public static void init() {
		ThaumcraftApi.addSmeltingBonus(new ItemStack(ModItems.blazeIngot, 1, 1), new ItemStack(ModItems.blazeIngot, 0, 2));

		addAspectsToItem(ModBlocks.soulGlass.blockID, new Aspect[] { Aspect.CRYSTAL, Aspect.SOUL }, new int[] { 1, 1 });
		addAspectsToItem(ModBlocks.glowBox.blockID, new Aspect[] { Aspect.LIGHT, Aspect.SENSES }, new int[] { 3, 1 });
		addAspectsToItem(ModBlocks.colouredQuartzBlock.blockID, new Aspect[] { Aspect.WATER, Aspect.CRYSTAL }, new int[] { 1, 1 });
		addAspectsToItem(ModBlocks.colouredChiselledQuartzBlock.blockID, new Aspect[] { Aspect.WATER, Aspect.CRYSTAL }, new int[] { 1, 1 });
		for (int i = 0; i < ModBlocks.colouredQuartzPillar.length; i++)
			addAspectsToItem(ModBlocks.colouredQuartzPillar[i].blockID, new Aspect[] { Aspect.WATER, Aspect.CRYSTAL }, new int[] { 1, 1 });
		for (int i = 0; i < ModBlocks.colouredQuartzBlockStairs.length; i++)
			addAspectsToItem(ModBlocks.colouredQuartzBlockStairs[i].blockID, new Aspect[] { Aspect.WATER, Aspect.CRYSTAL }, new int[] { 2, 2 });
		addAspectsToItem(ModBlocks.undertaker.blockID, new Aspect[] { Aspect.EARTH, Aspect.TRAP, Aspect.SOUL, Aspect.CRYSTAL, Aspect.ENERGY }, new int[] { 7, 7, 7, 1, 1 });
		addAspectsToItem(ModItems.blazeIngot.itemID, 1, new Aspect[] { Aspect.FIRE, Aspect.MAGIC, Aspect.METAL }, new int[] { 2, 2, 2 });
		addAspectsToItem(ModItems.quarzBerrySeeds.itemID, new Aspect[] { Aspect.SEED, Aspect.UNDEAD }, new int[] { 1, 1 });
		addAspectsToItem(ModItems.ghostSeeds.itemID, new Aspect[] { Aspect.SEED, Aspect.SOUL }, new int[] { 1, 1 });
		addAspectsToItem(ModItems.witherShrubSeeds.itemID, new Aspect[] { Aspect.SEED, Aspect.UNDEAD }, new int[] { 1, 1 });
		addAspectsToItem(ModItems.batWing.itemID, new Aspect[] { Aspect.AIR, Aspect.FLIGHT }, new int[] { 1, 1 });
		addAspectsToItem(ModItems.cookedBatWing.itemID, new Aspect[] { Aspect.AIR, Aspect.HUNGER }, new int[] { 1, 1 });
		addAspectsToItem(ModItems.wolfTeeth.itemID, new Aspect[] { Aspect.BEAST }, new int[] { 1 });
		addAspectsToItem(ModItems.silverfishScale.itemID, new Aspect[] { Aspect.BEAST, Aspect.EARTH }, new int[] { 1, 1 });
		addAspectsToItem(ModItems.glowingReed.itemID, new Aspect[] { Aspect.AIR, Aspect.FIRE, Aspect.LIGHT }, new int[] { 1, 1, 1 });
		addAspectsToItem(ModItems.spectreWheat.itemID, new Aspect[] { Aspect.CROP, Aspect.SOUL }, new int[] { 1, 1 });
		addAspectsToItem(ModItems.quarzBerry.itemID, new Aspect[] { Aspect.CROP, Aspect.CRYSTAL }, new int[] { 1, 1 });
		addAspectsToItem(Item.monsterPlacer.itemID, new Aspect[] { Aspect.SOUL, Aspect.TRAP }, new int[] { 1, 1 });
		addAspectsToItem(ModItems.skeletonSpawner.itemID, new Aspect[] { Aspect.SOUL, Aspect.TRAP }, new int[] { 1, 1 });
		addAspectsToItem(ModItems.spookyFlour.itemID, new Aspect[] { Aspect.SOUL, Aspect.CROP }, new int[] { 1, 1 });
		addAspectsToItem(ModItems.dimensionalBread.itemID, new Aspect[] { Aspect.SOUL, Aspect.CROP, Aspect.HUNGER }, new int[] { 1, 3, 2 });
		addAspectsToItem(ModItems.blazeIngot.itemID, 2, new Aspect[] { Aspect.FIRE }, new int[] { 1 });
	}

	private static void addAspectsToItem(int id, Aspect[] aspects, int[] amounts) {
		addAspectsToItem(id, -1, aspects, amounts);
	}

	private static void addAspectsToItem(int id, int meta, Aspect[] aspects, int[] amounts) {
		AspectList list = new AspectList();
		for (int i = 0; i < aspects.length; i++)
			list.add(aspects[i], amounts[i]);

		ThaumcraftApi.registerObjectTag(id, meta, list);
	}
}