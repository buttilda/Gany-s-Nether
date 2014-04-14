package ganymedes01.ganysnether.items;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.ModIDs;
import ganymedes01.ganysnether.lib.Strings;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class CookedBatWing extends ItemFood {

	CookedBatWing() {
		super(ModIDs.COOKED_BAT_WING_ID, 1, true);
		setCreativeTab(GanysNether.netherTab);
		setTextureName(Utils.getItemTexture(Strings.Items.COOKED_BAT_WING_NAME));
		setUnlocalizedName(Utils.getUnlocalizedName(Strings.Items.COOKED_BAT_WING_NAME));
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 8;
	}
}