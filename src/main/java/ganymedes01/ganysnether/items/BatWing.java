package ganymedes01.ganysnether.items;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.Strings;
import net.minecraft.item.Item;

/**
 * Gany's Nether
 *
 * @author ganymedes01
 *
 */

public class BatWing extends Item {

	public BatWing() {
		setCreativeTab(GanysNether.netherTab);
		setTextureName(Utils.getItemTexture(Strings.Items.BAT_WING_NAME));
		setUnlocalizedName(Utils.getUnlocalizedName(Strings.Items.BAT_WING_NAME));
	}
}