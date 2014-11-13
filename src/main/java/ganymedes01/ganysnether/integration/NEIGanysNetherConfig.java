package ganymedes01.ganysnether.integration;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.ModBlocks;
import ganymedes01.ganysnether.ModItems;
import ganymedes01.ganysnether.client.gui.inventory.GuiMagmaticCentrifuge;
import ganymedes01.ganysnether.integration.nei.MagmaticCentrifugeRecipeHandler;
import ganymedes01.ganysnether.integration.nei.ReproducerRecipeHandler;
import ganymedes01.ganysnether.integration.nei.VolcanicFurnaceYieldHandler;
import ganymedes01.ganysnether.lib.Reference;
import ganymedes01.ganysnether.lib.Strings;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import codechicken.nei.recipe.DefaultOverlayHandler;

/**
 * Gany's Nether
 *
 * @author ganymedes01
 *
 */

public class NEIGanysNetherConfig implements IConfigureNEI {

	@Override
	public void loadConfig() {
		if (GanysNether.enableMagmaticCentrifuge) {
			API.registerRecipeHandler(new MagmaticCentrifugeRecipeHandler());
			API.registerUsageHandler(new MagmaticCentrifugeRecipeHandler());
			API.registerGuiOverlay(GuiMagmaticCentrifuge.class, Strings.Blocks.MAGMATIC_CENTRIFUGE_NAME, 5, 11);
			API.registerGuiOverlayHandler(GuiMagmaticCentrifuge.class, new DefaultOverlayHandler(5, 11), Strings.Blocks.MAGMATIC_CENTRIFUGE_NAME);
		}
		if (GanysNether.enableReproducerAndDrops) {
			API.registerRecipeHandler(new ReproducerRecipeHandler());
			API.registerUsageHandler(new ReproducerRecipeHandler());
		}
		if (GanysNether.enableVolcanicFurnace) {
			API.registerUsageHandler(new VolcanicFurnaceYieldHandler());
			API.registerRecipeHandler(new VolcanicFurnaceYieldHandler());
		}

		API.hideItem(new ItemStack(ModBlocks.tilledNetherrack));
		API.hideItem(new ItemStack(ModBlocks.spectreWheat));
		API.hideItem(new ItemStack(ModBlocks.quarzBerryBush));
		API.hideItem(new ItemStack(ModBlocks.glowingReed));
		API.hideItem(new ItemStack(ModBlocks.witherShrub));
		API.hideItem(new ItemStack(ModBlocks.weepingPod));
		API.hideItem(new ItemStack(ModBlocks.hellBush));
		if (!GanysNether.enableUndertaker)
			API.hideItem(new ItemStack(ModBlocks.undertaker));
		if (!GanysNether.shouldGenerateCrops) {
			API.hideItem(new ItemStack(ModItems.hellBushSeeds));
			API.hideItem(new ItemStack(ModItems.witherShrubSeeds));
			API.hideItem(new ItemStack(ModItems.quarzBerrySeeds));
			API.hideItem(new ItemStack(ModItems.ghostSeeds));
			API.hideItem(new ItemStack(ModItems.lavaBerry));
			API.hideItem(new ItemStack(ModItems.quarzBerry));
			API.hideItem(new ItemStack(ModItems.spectreWheat));
			API.hideItem(new ItemStack(ModItems.spookyFlour));
			API.hideItem(new ItemStack(ModItems.glowingReed));
			API.hideItem(new ItemStack(ModBlocks.blazingCactoid));
		}
		if (!GanysNether.enableSceptres) {
			API.hideItem(new ItemStack(ModItems.sceptreOfConcealment));
			API.hideItem(new ItemStack(ModItems.sceptreOfFireCharging));
			API.hideItem(new ItemStack(ModItems.sceptreOfLightning));
			API.hideItem(new ItemStack(ModItems.sceptreCap, 1, OreDictionary.WILDCARD_VALUE));
		}
		if (!GanysNether.enableSpawners) {
			API.hideItem(new ItemStack(ModItems.spawnerUpgrade, 1, OreDictionary.WILDCARD_VALUE));
			API.hideItem(new ItemStack(ModBlocks.extendedSpawner));
		}
		if (!GanysNether.enableQuartz) {
			API.hideItem(new ItemStack(ModBlocks.colouredQuartzBlock, 1, OreDictionary.WILDCARD_VALUE));
			API.hideItem(new ItemStack(ModBlocks.colouredChiselledQuartzBlock, 1, OreDictionary.WILDCARD_VALUE));
			for (Block b : ModBlocks.colouredQuartzPillar)
				API.hideItem(new ItemStack(b, 1, OreDictionary.WILDCARD_VALUE));
			for (Block b : ModBlocks.colouredQuartzBlockStairs)
				API.hideItem(new ItemStack(b, 1, OreDictionary.WILDCARD_VALUE));
		}
		if (!GanysNether.enableGlowbox)
			API.hideItem(new ItemStack(ModBlocks.glowBox, 1, OreDictionary.WILDCARD_VALUE));
		if (!GanysNether.enableSoulGlass) {
			API.hideItem(new ItemStack(ModBlocks.soulGlass, 1, OreDictionary.WILDCARD_VALUE));
			API.hideItem(new ItemStack(ModBlocks.soulGlassPane0));
			API.hideItem(new ItemStack(ModBlocks.soulGlassPane1));
			API.hideItem(new ItemStack(ModBlocks.soulGlassStairs));
		}
		if (!GanysNether.enableReproducerAndDrops) {
			API.hideItem(new ItemStack(ModBlocks.reproducer));
			API.hideItem(new ItemStack(ModItems.batWing));
			API.hideItem(new ItemStack(ModItems.cookedBatWing));
			API.hideItem(new ItemStack(ModItems.silverfishScale));
			API.hideItem(new ItemStack(ModItems.wolfTeeth));
		}
		if (!GanysNether.enableBlazeArmour) {
			API.hideItem(new ItemStack(ModItems.blazeHelmet));
			API.hideItem(new ItemStack(ModItems.blazeChestplate));
			API.hideItem(new ItemStack(ModItems.blazeLeggings));
			API.hideItem(new ItemStack(ModItems.blazeBoots));
		}
		if (!GanysNether.enableVolcanicFurnace) {
			API.hideItem(new ItemStack(ModBlocks.volcanicFurnace));
			API.hideItem(new ItemStack(ModBlocks.denseLavaCell));
			API.hideItem(new ItemStack(ModBlocks.focusedLavaCell));
		}
		if (!GanysNether.enableMagmaticCentrifuge)
			API.hideItem(new ItemStack(ModBlocks.magmaticCentrifuge));
	}

	@Override
	public String getName() {
		return Reference.MOD_NAME;
	}

	@Override
	public String getVersion() {
		return Reference.VERSION_NUMBER;
	}
}