package ganymedes01.ganysnether.integration;

import net.minecraft.enchantment.Enchantment;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class GanysEndManager extends Integration {

	private static int imperviousnessID;

	@Override
	public void init() {
		for (Enchantment ench : Enchantment.enchantmentsList)
			if (ench != null && ench.getName().contains("imperviousness"))
				imperviousnessID = ench.effectId;
	}

	@Override
	public void postInit() {
	}

	@Override
	public String getModID() {
		return "ganysend";
	}

	public static int getImperviousnessID() {
		return imperviousnessID;
	}
}