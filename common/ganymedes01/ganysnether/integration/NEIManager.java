package ganymedes01.ganysnether.integration;

import ganymedes01.ganysnether.client.gui.inventory.GuiMagmaticCentrifuge;
import ganymedes01.ganysnether.integration.nei.MagmaticCentrifugeRecipeHandler;
import codechicken.nei.api.API;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class NEIManager {

	public static void init() {
		API.registerRecipeHandler(new MagmaticCentrifugeRecipeHandler());
		API.registerUsageHandler(new MagmaticCentrifugeRecipeHandler());
		API.registerGuiOverlay(GuiMagmaticCentrifuge.class, "magmaticCentrifuge", 5, 11);
	}
}