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

public class Flour extends Item implements IConfigurable {

	public Flour() {
		setCreativeTab(GanysNether.enableFlour ? GanysNether.netherTab : null);
		setTextureName(Utils.getItemTexture(Strings.Items.FLOUR_NAME));
		setUnlocalizedName(Utils.getUnlocalisedName(Strings.Items.FLOUR_NAME));
	}

	@Override
	public boolean isEnabled() {
		return GanysNether.enableFlour;
	}
}