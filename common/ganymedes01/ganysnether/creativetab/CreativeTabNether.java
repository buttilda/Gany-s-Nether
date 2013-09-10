package ganymedes01.ganysnether.creativetab;

import ganymedes01.ganysnether.lib.Reference;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CreativeTabNether extends CreativeTabs {

	public CreativeTabNether() {
		super(Reference.MOD_ID);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getTabIconItemIndex() {
		return Block.netherrack.blockID;
	}
}
