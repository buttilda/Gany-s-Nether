package ganymedes01.ganysnether.integration;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.ModBlocks;
import ganymedes01.ganysnether.client.gui.inventory.GuiMagmaticCentrifuge;
import ganymedes01.ganysnether.integration.nei.MagmaticCentrifugeRecipeHandler;
import ganymedes01.ganysnether.integration.nei.ReproducerRecipeHandler;
import ganymedes01.ganysnether.integration.nei.VolcanicFurnaceYieldHandler;
import ganymedes01.ganysnether.lib.Reference;
import ganymedes01.ganysnether.lib.Strings;
import net.minecraft.item.ItemStack;
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

		if (GanysNether.shouldGenerateCrops) {
			API.hideItem(new ItemStack(ModBlocks.tilledNetherrack));
			if (GanysNether.shouldGenerateSpectreWheat)
				API.hideItem(new ItemStack(ModBlocks.spectreWheat));
			if (GanysNether.shouldGenerateQuarzBerryBush)
				API.hideItem(new ItemStack(ModBlocks.quarzBerryBush));
			if (GanysNether.shouldGenerateGlowingReed)
				API.hideItem(new ItemStack(ModBlocks.glowingReed));
			if (GanysNether.shouldGenerateWitherShrub)
				API.hideItem(new ItemStack(ModBlocks.witherShrub));
			if (GanysNether.shouldGenerateWeepingPod)
				API.hideItem(new ItemStack(ModBlocks.weepingPod));
			if (GanysNether.shouldGenerateHellBush)
				API.hideItem(new ItemStack(ModBlocks.hellBush));
		}
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