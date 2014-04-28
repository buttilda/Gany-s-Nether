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

public class WolfTeeth extends Item {

	WolfTeeth() {
		super(ModIDs.WOLF_TEETH_ID);
		setCreativeTab(GanysNether.netherTab);
		setTextureName(Utils.getItemTexture(Strings.Items.WOLF_TEETH_NAME));
		setUnlocalizedName(Utils.getUnlocalizedName(Strings.Items.WOLF_TEETH_NAME));
	}
}