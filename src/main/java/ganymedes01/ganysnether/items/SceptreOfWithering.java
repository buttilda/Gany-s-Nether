package ganymedes01.ganysnether.items;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.Strings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityWitherSkull;
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

public class SceptreOfWithering extends Sceptre {

	public SceptreOfWithering() {
		super(3);
		setMaxDamage(GanysNether.sceptreOfWithering);
		setTextureName(Utils.getItemTexture(Strings.Items.SCEPTRE_OF_WITHERING_NAME));
		setUnlocalizedName(Utils.getUnlocalisedName(Strings.Items.SCEPTRE_OF_WITHERING_NAME));
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		world.playAuxSFXAtEntity(player, 1014, (int) player.posX, (int) player.posY, (int) player.posZ, 0);

		if (!world.isRemote) {
			Vec3 targetPos = player.getLook(1.0F);
			EntityWitherSkull witherskull = new EntityWitherSkull(world, player, 0, 0, 0);
			witherskull.setInvulnerable(true);

			witherskull.posX = player.posX + targetPos.xCoord;
			witherskull.posY = player.posY + targetPos.yCoord + 1.0D;
			witherskull.posZ = player.posZ + targetPos.zCoord;
			double factor = MathHelper.sqrt_double(targetPos.xCoord * targetPos.xCoord + targetPos.yCoord * targetPos.yCoord + targetPos.zCoord * targetPos.zCoord);
			witherskull.accelerationX = targetPos.xCoord / factor * 0.1D;
			witherskull.accelerationY = targetPos.yCoord / factor * 0.1D;
			witherskull.accelerationZ = targetPos.zCoord / factor * 0.1D;

			world.spawnEntityInWorld(witherskull);
		} else
			player.swingItem();

		if (!player.capabilities.isCreativeMode) {
			stack.damageItem(1, player);
			if (stack.stackSize <= 0)
				player.setCurrentItemOrArmor(0, null);
		}

		return stack;
	}
}