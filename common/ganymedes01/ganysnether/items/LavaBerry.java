package ganymedes01.ganysnether.items;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.ModIDs;
import ganymedes01.ganysnether.lib.Strings;
import net.minecraft.item.Item;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class LavaBerry extends Item {

	public LavaBerry() {
		super(ModIDs.LAVA_BERRY_ID);
		setCreativeTab(GanysNether.netherTab);
		setTextureName(Utils.getItemTexture(Strings.LAVA_BERRY_NAME));
		setUnlocalizedName(Utils.getUnlocalizedName(Strings.LAVA_BERRY_NAME));
	}
}