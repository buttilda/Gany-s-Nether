package ganymedes01.ganysnether.items;

import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.Strings;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemGlowBox extends ItemBlock {

	public ItemGlowBox(int id) {
		super(id);
		setHasSubtypes(true);
		setUnlocalizedName(Utils.getUnlocalizedName(Strings.GLOW_BOX_NAME + "_item_"));
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return getUnlocalizedName() + Strings.COLOURS[stack.getItemDamage()];
	}

	@Override
	public int getMetadata(int meta) {
		return meta;
	}
}
