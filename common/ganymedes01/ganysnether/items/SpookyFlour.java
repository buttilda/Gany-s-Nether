package ganymedes01.ganysnether.items;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.Strings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SpookyFlour extends Item {

	public SpookyFlour(int id) {
		super(id);
		setTextureName("sugar");
		setCreativeTab(GanysNether.netherTab);
		setUnlocalizedName(Utils.getUnlocalizedName(Strings.SPOOKY_FLOUR_NAME));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack stack, int meta) {
		return (113 * 65536) + (149 * 256) + 218;
	}
}