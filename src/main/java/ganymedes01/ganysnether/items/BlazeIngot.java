package ganymedes01.ganysnether.items;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.Strings;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Gany's Nether
 *
 * @author ganymedes01
 *
 */

public class BlazeIngot extends Item {

	@SideOnly(Side.CLIENT)
	private IIcon[] icon;

	public BlazeIngot() {
		setMaxDamage(0);
		setHasSubtypes(true);
		setCreativeTab(GanysNether.netherTab);
		setUnlocalizedName(Utils.getUnlocalisedName(Strings.Items.BLAZE_INGOT_NAME));
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return super.getUnlocalizedName() + stack.getItemDamage();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta) {
		return icon[meta];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister reg) {
		icon = new IIcon[3];

		for (int i = 0; i < icon.length; i++)
			icon[i] = reg.registerIcon(Utils.getItemTexture(Strings.Items.BLAZE_INGOT_NAME) + "_" + i);
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void getSubItems(Item itemID, CreativeTabs tab, List list) {
		for (int i = 0; i < 3; i++)
			list.add(new ItemStack(itemID, 1, i));
	}
}