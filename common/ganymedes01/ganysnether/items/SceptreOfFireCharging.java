package ganymedes01.ganysnether.items;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.ModIDs;
import ganymedes01.ganysnether.lib.Strings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class SceptreOfFireCharging extends Sceptre {

	public static final int DEFAULT_DUR = 32;

	public SceptreOfFireCharging() {
		super(ModIDs.SCEPTRE_OF_FIRE_CHARGING_ID);
		setTextureName(Utils.getItemTexture(Strings.SCEPTRE_OF_FIRE_CHARGING_NAME));
		setUnlocalizedName(Utils.getUnlocalizedName(Strings.SCEPTRE_OF_FIRE_CHARGING_NAME));
		setMaxDamage(GanysNether.sceptreOfFireCharging > 0 ? GanysNether.sceptreOfFireCharging : DEFAULT_DUR);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if (!(player.inventory.consumeInventoryItem(Item.fireballCharge.itemID) || player.capabilities.isCreativeMode))
			stack.damageItem(1, player);
		player.swingItem();
		spawnFireCharge(world, player);
		world.playAuxSFX(1009, (int) player.posX, (int) player.posY, (int) player.posZ, 0);
		return stack;
	}

	private void spawnFireCharge(World world, EntityPlayer player) {
		if (world.isRemote)
			return;

		EntityLargeFireball entitylargefireball = new EntityLargeFireball(world, player, 0, 0, 0);
		entitylargefireball.field_92057_e = 1;

		Vec3 targetPos = player.getLook(1.0F);
		entitylargefireball.posX = player.posX + targetPos.xCoord;
		entitylargefireball.posY = player.posY + targetPos.yCoord + 1.0D;
		entitylargefireball.posZ = player.posZ + targetPos.zCoord;

		double factor = MathHelper.sqrt_double(targetPos.xCoord * targetPos.xCoord + targetPos.yCoord * targetPos.yCoord + targetPos.zCoord * targetPos.zCoord);
		entitylargefireball.accelerationX = targetPos.xCoord / factor * 0.1D;
		entitylargefireball.accelerationY = targetPos.yCoord / factor * 0.1D;
		entitylargefireball.accelerationZ = targetPos.zCoord / factor * 0.1D;

		world.spawnEntityInWorld(entitylargefireball);
	}
}