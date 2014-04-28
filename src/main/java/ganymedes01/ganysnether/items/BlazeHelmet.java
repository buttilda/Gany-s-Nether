package ganymedes01.ganysnether.items;

import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.Strings;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class BlazeHelmet extends BlazeArmour {

	BlazeHelmet() {
		super(0);
		setTextureName(Utils.getItemTexture(Strings.Items.BLAZE_HELMET_NAME));
		setUnlocalizedName(Utils.getUnlocalizedName(Strings.Items.BLAZE_HELMET_NAME));
	}
}
