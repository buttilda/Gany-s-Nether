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

public class NetherCore extends Item implements IConfigurable {

	public NetherCore() {
		setTextureName(Utils.getItemTexture(Strings.Items.NETHER_CORE_NAME));
		setUnlocalizedName(Utils.getUnlocalisedName(Strings.Items.NETHER_CORE_NAME));
		setCreativeTab(GanysNether.enableVolcanicFurnace || GanysNether.enableSpawners ? GanysNether.netherTab : null);
	}

	@Override
	public boolean isEnabled() {
		return GanysNether.enableVolcanicFurnace || GanysNether.enableSpawners;
	}
}