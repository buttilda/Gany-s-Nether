package ganymedes01.ganysnether.integration;

import ganymedes01.ganysnether.integration.cg.GanysNetherCraftGuideConfig;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class CraftGuideManager extends Integration {

	@Override
	public void init() {
		new GanysNetherCraftGuideConfig();
	}

	@Override
	public void postInit() {
	}

	@Override
	public String getModID() {
		return "craftguide";
	}
}