package ganymedes01.ganysnether.items;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.core.utils.ConcealmentHandler;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.Strings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class SceptreOfConcealment extends Sceptre {

	SceptreOfConcealment() {
		super();
		setMaxDamage(GanysNether.sceptreOfConcealmentDurability);
		setTextureName(Utils.getItemTexture(Strings.Items.SCEPTRE_OF_CONCEALMENT_NAME));
		setUnlocalizedName(Utils.getUnlocalizedName(Strings.Items.SCEPTRE_OF_CONCEALMENT_NAME));
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		if (!(entity instanceof EntityLivingBase))
			return false;
		EntityLivingBase target = (EntityLivingBase) entity;

		if (target.isChild())
			return false;
		if (ConcealmentHandler.canBeConcealed(target))
			if (player instanceof EntityPlayer)
				if (player.inventory.consumeInventoryItem(Items.egg)) {
					if (!player.worldObj.isRemote) {
						player.worldObj.playSoundAtEntity(target, "random.breath", 1.5F, player.worldObj.rand.nextFloat() * 0.1F + 0.9F);
						target.entityDropItem(ConcealmentHandler.getEggFromEntity(target), 1.0F);
						target.setDead();
						stack.damageItem(1, player);
					}
					return true;
				}
		return false;
	}

	@Override
	public boolean getIsRepairable(ItemStack item, ItemStack material) {
		return material.getItem() == ModItems.sceptreCap && material.getItemDamage() == 2;
	}
}