package ganymedes01.ganysnether.items;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.ModIDs;
import ganymedes01.ganysnether.lib.Strings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class SceptreOfConcealment extends Sceptre {

	public SceptreOfConcealment() {
		super(ModIDs.SCEPTRE_OF_CONCEALMENT_ID);
		setMaxDamage(GanysNether.sceptreOfConcealmentDurability);
		setTextureName(Utils.getItemTexture(Strings.SCEPTRE_OF_CONCEALMENT_NAME));
		setUnlocalizedName(Utils.getUnlocalizedName(Strings.SCEPTRE_OF_CONCEALMENT_NAME));
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity target) {
		if (!(target instanceof EntityLivingBase))
			return false;

		if (((EntityLivingBase) target).isChild())
			return false;
		int id = EntityList.getEntityID(target);
		if (id >= 50 && id != 63 && id != 64 && id < 200)
			if (player instanceof EntityPlayer)
				if (player.inventory.consumeInventoryItem(Item.egg.itemID)) {
					if (!player.worldObj.isRemote) {
						target.setDead();
						player.worldObj.playSoundAtEntity(target, "random.breath", 1.5F, player.worldObj.rand.nextFloat() * 0.1F + 0.9F);
						if (target instanceof EntitySkeleton)
							target.entityDropItem(new ItemStack(ModItems.skeletonSpawner, 1, ((EntitySkeleton) target).getSkeletonType()), 1.0F);
						else
							target.entityDropItem(new ItemStack(Item.monsterPlacer, 1, EntityList.getEntityID(target)), 1.0F);
						stack.damageItem(1, player);
					}
					return true;
				}
		return false;
	}
}
