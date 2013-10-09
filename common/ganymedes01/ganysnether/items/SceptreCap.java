package ganymedes01.ganysnether.items;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.ModIDs;
import ganymedes01.ganysnether.lib.Strings;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemSimpleFoiled;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
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
	private Icon[] icon = new Icon[3];

	public SceptreCap() {
		super(ModIDs.SCEPTRE_CAP_ID);
		setMaxDamage(0);
		setHasSubtypes(true);
		setCreativeTab(GanysNether.netherTab);
		setUnlocalizedName(Utils.getUnlocalizedName(Strings.SCEPTRE_CAP_NAME));
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
	public Icon getIconFromDamage(int i) {
		return icon[i];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(int itemID, CreativeTabs tabs, List list) {
		for (int i = 0; i < icon.length; i++)
			list.add(new ItemStack(itemID, 1, i));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister reg) {
		for (int i = 0; i < icon.length; i++)
			icon[i] = reg.registerIcon(Utils.getItemTexture(Strings.SCEPTRE_CAP_NAME) + "_" + i);
	}
}