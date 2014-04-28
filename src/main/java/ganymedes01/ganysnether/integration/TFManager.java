package ganymedes01.ganysnether.integration;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class TFManager extends Integration {

	@Override
	public void init() {
		try {
			Class TFItems = Class.forName("twilightforest.item.TFItems");
			OreDictionary.registerOre("mobEgg", new ItemStack((Item) TFItems.getDeclaredField("spawnEgg").get(null), 1, OreDictionary.WILDCARD_VALUE));
		} catch (Exception e) {
		}
	}

	@Override
	public void postInit() {

	}

	@Override
	public String getModID() {
		return "TwilightForest";
	}
}