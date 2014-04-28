package ganymedes01.ganysnether.items;

import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.Strings;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class BlazeBoots extends BlazeArmour {

	BlazeBoots() {
		super(3);
		setTextureName(Utils.getItemTexture(Strings.Items.BLAZE_BOOTS_NAME));
		setUnlocalizedName(Utils.getUnlocalizedName(Strings.Items.BLAZE_BOOTS_NAME));
	}
}