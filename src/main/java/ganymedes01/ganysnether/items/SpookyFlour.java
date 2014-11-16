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

public class SpookyFlour extends Item {

	public SpookyFlour() {
		setTextureName(Utils.getItemTexture(Strings.Items.SPOOKY_FLOUR_NAME));
		setUnlocalizedName(Utils.getUnlocalizedName(Strings.Items.SPOOKY_FLOUR_NAME));
		setCreativeTab(GanysNether.shouldGenerateCrops ? GanysNether.netherTab : null);
	}
}