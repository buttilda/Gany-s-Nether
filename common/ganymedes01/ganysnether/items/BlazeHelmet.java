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

	public BlazeHelmet(int id) {
		super(id, 0);
		setTextureName(Utils.getItemTexture(Strings.BLAZE_HELMET_NAME));
		setUnlocalizedName(Utils.getUnlocalizedName(Strings.BLAZE_HELMET_NAME));
	}
}
