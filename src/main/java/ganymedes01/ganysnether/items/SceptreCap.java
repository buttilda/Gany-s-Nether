package ganymedes01.ganysnether.items;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.Strings;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSimpleFoiled;
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

public class SceptreCap extends ItemSimpleFoiled {

	@SideOnly(Side.CLIENT)
	private IIcon[] icon;

	public SceptreCap() {
		setMaxDamage(0);
		setHasSubtypes(true);
		setCreativeTab(GanysNether.enableSceptres ? GanysNether.netherTab : null);
		setUnlocalizedName(Utils.getUnlocalizedName(Strings.Items.SCEPTRE_CAP_NAME));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.rare;
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return super.getUnlocalizedName() + "_" + stack.getItemDamage();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int i) {
		return icon[i];
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void getSubItems(Item itemID, CreativeTabs tabs, List list) {
		for (int i = 0; i < icon.length; i++)
			list.add(new ItemStack(itemID, 1, i));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister reg) {
		icon = new IIcon[4];
		for (int i = 0; i < icon.length; i++)
			icon[i] = reg.registerIcon(Utils.getItemTexture(Strings.Items.SCEPTRE_CAP_NAME) + "_" + i);
	}
}