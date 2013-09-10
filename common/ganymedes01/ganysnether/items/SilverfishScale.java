package ganymedes01.ganysnether.items;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.Strings;
import net.minecraft.item.Item;

public class SilverfishScale extends Item {

	public SilverfishScale(int id) {
		super(id);
		setCreativeTab(GanysNether.netherTab);
		setTextureName(Utils.getItemTexture(Strings.SILVERFISH_SCALE_NAME));
		setUnlocalizedName(Utils.getUnlocalizedName(Strings.SILVERFISH_SCALE_NAME));
	}
}
