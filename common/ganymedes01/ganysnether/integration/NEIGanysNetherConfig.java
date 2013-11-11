package ganymedes01.ganysnether.integration;

import ganymedes01.ganysnether.blocks.ModBlocks;
import ganymedes01.ganysnether.integration.nei.MagmaticCentrifugeRecipeHandler;
import ganymedes01.ganysnether.integration.nei.ReproducerRecipeHandler;
import ganymedes01.ganysnether.integration.nei.VolcanicFurnaceYieldHandler;
import ganymedes01.ganysnether.lib.Reference;
import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class NEIGanysNetherConfig implements IConfigureNEI {

	@Override
	public void loadConfig() {
		API.registerRecipeHandler(new MagmaticCentrifugeRecipeHandler());
		API.registerUsageHandler(new MagmaticCentrifugeRecipeHandler());

		API.registerRecipeHandler(new ReproducerRecipeHandler());
		API.registerUsageHandler(new ReproducerRecipeHandler());

		API.registerUsageHandler(new VolcanicFurnaceYieldHandler());

		API.hideItem(ModBlocks.tilledNetherrack.blockID);
		API.hideItem(ModBlocks.volcanicFurnaceActive.blockID);
		API.hideItem(ModBlocks.spectreWheat.blockID);
		API.hideItem(ModBlocks.quarzBerryBush.blockID);
		API.hideItem(ModBlocks.glowingReed.blockID);
		API.hideItem(ModBlocks.witherShrub.blockID);
		API.hideItem(ModBlocks.weepingPod.blockID);
		API.hideItem(ModBlocks.soulGlassPane.blockID); // TODO Remove after 1.7
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