package ganymedes01.ganysnether.items;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.Strings;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class DimensionalBread extends ItemFood {

	public DimensionalBread(int id) {
		super(id, 2, false);
		setCreativeTab(GanysNether.netherTab);
		setPotionEffect(Potion.invisibility.id, 60, 0, 0.5F);
		setTextureName(Utils.getItemTexture(Strings.DIMENSIONAL_BREAD_NAME));
		setUnlocalizedName(Utils.getUnlocalizedName(Strings.DIMENSIONAL_BREAD_NAME));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.uncommon;
	}
}
