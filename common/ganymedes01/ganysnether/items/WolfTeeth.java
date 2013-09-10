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

	public WolfTeeth(int id) {
		super(id);
		setCreativeTab(GanysNether.netherTab);
		setTextureName(Utils.getItemTexture(Strings.WOLF_TEETH_NAME));
		setUnlocalizedName(Utils.getUnlocalizedName(Strings.WOLF_TEETH_NAME));
	}
}
