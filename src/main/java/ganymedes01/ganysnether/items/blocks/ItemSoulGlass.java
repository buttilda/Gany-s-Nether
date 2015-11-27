package ganymedes01.ganysnether.items.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.item.ItemCloth;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class ItemSoulGlass extends ItemCloth {

	public ItemSoulGlass(Block block) {
		super(block);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta) {
		return field_150939_a.func_149735_b(2, meta);
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return getUnlocalizedName() + (stack.getItemDamage() == 0 ? "" : "Brick");
	}
}