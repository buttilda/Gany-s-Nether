package ganymedes01.ganysnether.items;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.IConfigurable;
import ganymedes01.ganysnether.ModItems;
import ganymedes01.ganysnether.client.model.ModelBlazeArmour;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.integration.GanysEndManager;
import ganymedes01.ganysnether.lib.ModMaterials;

import java.util.Map;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Gany's Nether
 *
 * @author ganymedes01
 *
 */

public class BlazeArmour extends ItemArmor implements IConfigurable {

	private final int MAX_COOL_DOWN = 160;
	private int coolDown = MAX_COOL_DOWN;

	BlazeArmour(int type) {
		super(ModMaterials.BLAZE, 0, type);
		setMaxStackSize(1);
		setCreativeTab(GanysNether.enableBlazeArmour ? GanysNether.netherTab : null);
	}

	@Override
	public boolean getIsRepairable(ItemStack item, ItemStack material) {
		return material.getItem() == ModItems.blazeIngot && material.getItemDamage() == 1;
	}

	@Override
	@SuppressWarnings("rawtypes")
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
		coolDown--;
		if (coolDown == 0) {
			if (stack.getItemDamage() > 0)
				stack.setItemDamage(stack.getItemDamage() - 1);
			else if (stack.getItemDamage() < 0)
				stack.setItemDamage(0);
			coolDown = MAX_COOL_DOWN;
		}

		boolean isWaterproof = false;
		Map enchs = EnchantmentHelper.getEnchantments(stack);
		if (!enchs.isEmpty() && enchs.get(GanysEndManager.getImperviousnessID()) != null)
			isWaterproof = true;

		if (!isWaterproof)
			if (player.isInWater())
				stack.damageItem(1, player);
			else if (player.handleLavaMovement() || player.isBurning())
				if (stack.getItemDamage() > 0)
					stack.setItemDamage(stack.getItemDamage() - 2);

		if (stack.getItemDamage() < 0)
			stack.setItemDamage(0);
		else if (stack.getItemDamage() >= this.getMaxDamage())
			for (int i = 0; i < player.inventory.armorInventory.length; i++)
				if (player.inventory.armorInventory[i] != null)
					if (player.inventory.armorInventory[i].getItemDamage() >= this.getMaxDamage()) {
						player.inventory.armorInventory[i] = null;
						player.renderBrokenItemStack(stack);
					}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.uncommon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String layer) {
		int type = ((ItemArmor) stack.getItem()).armorType;
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

	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase player, ItemStack stack, int slot) {
		ModelBlazeArmour model = slot == 2 ? new ModelBlazeArmour(0.5F, true) : new ModelBlazeArmour(1.0F, false);
		model.bipedHead.showModel = slot == 0;
		model.bipedHeadwear.showModel = slot == 0;
		model.bipedBody.showModel = slot == 1 || slot == 2;
		model.bipedRightArm.showModel = slot == 1;
		model.bipedLeftArm.showModel = slot == 1;
		model.bipedRightLeg.showModel = slot == 2 || slot == 3;
		model.bipedLeftLeg.showModel = slot == 2 || slot == 3;
		model.isSneak = player.isSneaking();
		return model;
	}

	@Override
	public boolean isEnabled() {
		return GanysNether.enableBlazeArmour;
	}
}