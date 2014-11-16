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

public class Flour extends Item {

	public Flour() {
		setCreativeTab(GanysNether.netherTab);
		setTextureName(Utils.getItemTexture(Strings.Items.FLOUR_NAME));
		setUnlocalizedName(Utils.getUnlocalizedName(Strings.Items.FLOUR_NAME));
	}
}