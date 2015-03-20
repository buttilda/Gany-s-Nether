package ganymedes01.ganysnether.items;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.ModItems;
import ganymedes01.ganysnether.lib.Reference;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemSimpleFoiled;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Gany's Nether
 *
 * @author ganymedes01
 *
 */

public class Sceptre extends ItemSimpleFoiled {

	private final int capMeta;

	Sceptre(int capMeta) {
		setFull3D();
		setMaxStackSize(1);
		this.capMeta = capMeta;
		setCreativeTab(GanysNether.enableSceptres ? GanysNether.netherTab : null);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.epic;
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean bool) {
		if (stack.getItemDamage() >= getMaxDamage())
			list.add(StatCollector.translateToLocal("tooltip." + Reference.MOD_ID + ".sceptre"));
	}

	@Override
	public int getItemEnchantability() {
		return 22;
	}

	@Override
	public boolean getIsRepairable(ItemStack item, ItemStack material) {
		return material.getItem() == ModItems.sceptreCap && material.getItemDamage() == capMeta;
	}
}