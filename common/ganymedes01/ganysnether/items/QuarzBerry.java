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

public class QuarzBerry extends Item {

	public QuarzBerry() {
		super(ModIDs.QUARZ_BERRY_ID);
		setCreativeTab(GanysNether.netherTab);
		setTextureName(Utils.getItemTexture(Strings.QUARZ_BERRY_NAME));
		setUnlocalizedName(Utils.getUnlocalizedName(Strings.QUARZ_BERRY_NAME));
	}
}
