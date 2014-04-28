package ganymedes01.ganysnether.creativetab;

import ganymedes01.ganysnether.lib.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class CreativeTabNether extends CreativeTabs {

	public CreativeTabNether() {
		super(Reference.MOD_ID);
	}

	@Override
	public Item getTabIconItem() {
		return Item.getItemFromBlock(Blocks.netherrack);
	}
}