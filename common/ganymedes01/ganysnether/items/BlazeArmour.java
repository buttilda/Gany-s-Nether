package ganymedes01.ganysnether.items;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.ModMaterials;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class BlazeArmour extends ItemArmor {

	private final int type;

	public BlazeArmour(int id, int type) {
		super(id, ModMaterials.BLAZE, 0, type);
		this.type = type;
		setMaxStackSize(1);
		setCreativeTab(GanysNether.netherTab);
	}

	@Override
	public boolean getIsRepairable(ItemStack item, ItemStack material) {
		return material.getItem() == ModItems.blazeIngot && material.getItemDamage() == 1;
	}

	@Override
	public void onArmorTickUpdate(World world, EntityPlayer player, ItemStack stack) {
		if (world.isRemote)
			return;
		player.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 4, -3));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.uncommon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer) {
		switch (type) {
			case 0:
				return Utils.getArmourTexture(ModMaterials.BLAZE.name(), 1);
			case 1:
				return Utils.getArmourTexture(ModMaterials.BLAZE.name(), 1);
			case 2:
				return Utils.getArmourTexture(ModMaterials.BLAZE.name(), 2);
			case 3:
				return Utils.getArmourTexture(ModMaterials.BLAZE.name(), 1);
			default:
				return null;
		}
	}
}
