package ganymedes01.ganysnether.integration;

import ganymedes01.ganysnether.core.utils.HoeList;
import cpw.mods.fml.common.Loader;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class ModIntegrator {

	public static void init() {
		// BuildCraft
		if (Loader.isModLoaded("BuildCraft|Transport"))
			BuildCraftFacadeManager.registerFacades();

		// ThaumCraft
		if (Loader.isModLoaded("Thaumcraft")) {
			HoeList.registerThaumcraftHoes();
			ThaumCraftManager.init();
		}

		// Equivalent Exchange 3
		if (Loader.isModLoaded("EE3"))
			EE3Manager.init();

		// Gany's Surface
		if (Loader.isModLoaded("ganyssurface"))
			GanysSurfaceManager.init();

		// Industrial Craft 2
		if (Loader.isModLoaded("IC2"))
			IC2Manager.init();
	}

	public static void postInit() {
		// Applied Energistics
		if (Loader.isModLoaded("AppliedEnergistics"))
			AppEnerManager.init();
	}
}