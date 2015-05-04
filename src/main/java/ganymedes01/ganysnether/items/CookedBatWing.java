package ganymedes01.ganysnether.items;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.IConfigurable;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.Strings;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;

/**
 * Gany's Nether
 *
 * @author ganymedes01
 *
 */

public class CookedBatWing extends ItemFood implements IConfigurable {

	public CookedBatWing() {
		super(1, true);
		setTextureName(Utils.getItemTexture(Strings.Items.COOKED_BAT_WING_NAME));
		setUnlocalizedName(Utils.getUnlocalisedName(Strings.Items.COOKED_BAT_WING_NAME));
		setCreativeTab(GanysNether.enableReproducerAndDrops ? GanysNether.netherTab : null);
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 8;
	}

	@Override
	public boolean isEnabled() {
		return GanysNether.enableReproducerAndDrops;
	}
}