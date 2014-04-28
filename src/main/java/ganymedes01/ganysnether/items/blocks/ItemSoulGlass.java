package ganymedes01.ganysnether.items.blocks;

import ganymedes01.ganysnether.lib.Strings;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class ItemSoulGlass extends ItemBlockBase {

	public ItemSoulGlass(Block block) {
		super(block, Strings.Blocks.SOUL_GLASS_NAME + "_item_");
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return getUnlocalizedName() + (stack.getItemDamage() == 0 ? "" : "Brick");
	}
}