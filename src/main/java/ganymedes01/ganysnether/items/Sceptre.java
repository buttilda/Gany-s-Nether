package ganymedes01.ganysnether.items;

import ganymedes01.ganysnether.GanysNether;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemSimpleFoiled;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Gany's Nether
 *
 * @author ganymedes01
 *
 */

public class Sceptre extends ItemSimpleFoiled {

	Sceptre() {
		setFull3D();
		setMaxStackSize(1);
		setCreativeTab(GanysNether.netherTab);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.epic;
	}

	@Override
	public int getItemEnchantability() {
		return 22;
	}
}