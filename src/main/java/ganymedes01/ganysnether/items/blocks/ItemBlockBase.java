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
		return getUnlocalizedName() + Strings.COLOURS[stack.getItemDamage()];
	}

	@Override
	public int getMetadata(int meta) {
		return meta;
	}
}