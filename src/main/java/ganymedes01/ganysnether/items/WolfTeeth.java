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

public class WolfTeeth extends Item implements IConfigurable {

	public WolfTeeth() {
		setTextureName(Utils.getItemTexture(Strings.Items.WOLF_TEETH_NAME));
		setUnlocalizedName(Utils.getUnlocalisedName(Strings.Items.WOLF_TEETH_NAME));
		setCreativeTab(GanysNether.enableReproducerAndDrops ? GanysNether.netherTab : null);
	}

	@Override
	public boolean isEnabled() {
		return GanysNether.enableReproducerAndDrops;
	}
}