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

public class NetherCore extends Item {

	public NetherCore() {
		setTextureName(Utils.getItemTexture(Strings.Items.NETHER_CORE_NAME));
		setCreativeTab(GanysNether.enableSpawners ? GanysNether.netherTab : null);
		setUnlocalizedName(Utils.getUnlocalizedName(Strings.Items.NETHER_CORE_NAME));
	}
}