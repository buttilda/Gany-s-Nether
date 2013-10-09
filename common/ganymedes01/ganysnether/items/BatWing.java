package ganymedes01.ganysnether.items;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.ModIDs;
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
		super(ModIDs.BAT_WING_ID);
		setCreativeTab(GanysNether.netherTab);
		setTextureName(Utils.getItemTexture(Strings.BAT_WING_NAME));
		setUnlocalizedName(Utils.getUnlocalizedName(Strings.BAT_WING_NAME));
	}
}
