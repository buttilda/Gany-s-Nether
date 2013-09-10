package ganymedes01.ganysnether.items;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.Strings;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
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

public class SceptreOfConcealment extends ItemSimpleFoiled {

	public SceptreOfConcealment(int id) {
		super(id);
		setFull3D();
		setMaxStackSize(1);
		setCreativeTab(GanysNether.netherTab);
		setMaxDamage(GanysNether.sceptreOfConcealmentDurability);
		setTextureName(Utils.getItemTexture(Strings.SCEPTRE_OF_CONCEALMENT_NAME));
		setUnlocalizedName(Utils.getUnlocalizedName(Strings.SCEPTRE_OF_CONCEALMENT_NAME));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.epic;
	}

	@Override
	public boolean hitEntity(ItemStack item, EntityLivingBase target, EntityLivingBase player) {
		if (target.isChild())
			return false;
		int id = EntityList.getEntityID(target);
		if (id >= 50 && id != 63 && id != 64 && id < 200)
			if (player instanceof EntityPlayer)
				if (((EntityPlayer) player).inventory.consumeInventoryItem(Item.egg.itemID)) {
					if (!player.worldObj.isRemote) {
						target.setDead();
						player.worldObj.playSoundAtEntity(target, "random.breath", 1.5F, player.worldObj.rand.nextFloat() * 0.1F + 0.9F);
						if (target instanceof EntitySkeleton)
							target.entityDropItem(new ItemStack(ModItems.skeletonSpawner, 1, ((EntitySkeleton) target).getSkeletonType()), 1.0F);
						else
							target.entityDropItem(new ItemStack(Item.monsterPlacer, 1, EntityList.getEntityID(target)), 1.0F);
						item.damageItem(1, player);
					}
					return true;
				}
		return false;
	}
}
