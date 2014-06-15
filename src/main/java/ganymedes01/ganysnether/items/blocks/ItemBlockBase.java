package ganymedes01.ganysnether.items.blocks;

import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.Strings;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

/**
 * Gany's Nether
 *
 * @author ganymedes01
 *
 */

public class ItemBlockBase extends ItemBlock {

	public ItemBlockBase(Block block, String name) {
		super(block);
		setHasSubtypes(true);
		setUnlocalizedName(Utils.getUnlocalizedName(name));
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		int meta = stack.getItemDamage();
		if (meta >= Strings.COLOURS.length)
			meta = Strings.COLOURS.length - 1;
		if (meta < 0)
			meta = 0;
		return getUnlocalizedName() + Strings.COLOURS[meta];
	}

	@Override
	public int getMetadata(int meta) {
		return meta;
	}
}