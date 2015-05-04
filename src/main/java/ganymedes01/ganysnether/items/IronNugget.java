package ganymedes01.ganysnether.items;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.IConfigurable;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.Strings;
import net.minecraft.item.Item;

/**
 * Gany's Nether
 *
 * @author ganymedes01
 *
 */

public class IronNugget extends Item implements IConfigurable {

	public IronNugget() {
		setCreativeTab(GanysNether.netherTab);
		setTextureName(Utils.getItemTexture(Strings.Items.IRON_NUGGET_NAME));
		setUnlocalizedName(Utils.getUnlocalisedName(Strings.Items.IRON_NUGGET_NAME));
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}