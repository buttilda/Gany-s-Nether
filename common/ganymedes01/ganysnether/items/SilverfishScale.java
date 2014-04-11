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

public class SilverfishScale extends Item {

	public SilverfishScale() {
		super(ModIDs.SILVERFISH_SCALE_ID);
		setCreativeTab(GanysNether.netherTab);
		setTextureName(Utils.getItemTexture(Strings.Items.SILVERFISH_SCALE_NAME));
		setUnlocalizedName(Utils.getUnlocalizedName(Strings.Items.SILVERFISH_SCALE_NAME));
	}
}