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

public class WolfTeeth extends Item {

	public WolfTeeth() {
		setTextureName(Utils.getItemTexture(Strings.Items.WOLF_TEETH_NAME));
		setUnlocalizedName(Utils.getUnlocalizedName(Strings.Items.WOLF_TEETH_NAME));
		setCreativeTab(GanysNether.enableReproducerAndDrops ? GanysNether.netherTab : null);
	}
}