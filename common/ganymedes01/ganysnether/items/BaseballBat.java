package ganymedes01.ganysnether.items;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.ModIDs;
import ganymedes01.ganysnether.lib.Strings;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemSimpleFoiled;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class BaseballBat extends ItemSimpleFoiled {

	public BaseballBat() {
		super(ModIDs.BASEBALL_BAT_ID);
		setFull3D();
		setMaxStackSize(1);
		setCreativeTab(GanysNether.netherTab);
		setMaxDamage(GanysNether.baseballBatDurability);
		setTextureName(Utils.getItemTexture(Strings.BASEBALL_BAT_NAME));
		setUnlocalizedName(Utils.getUnlocalizedName(Strings.BASEBALL_BAT_NAME));
	}

	@Override
	public boolean hitEntity(ItemStack item, EntityLivingBase attacked, EntityLivingBase player) {
		int max = item.getMaxDamage();
		int dam = max - item.getItemDamage();
		double d = attacked.posX - player.posX;
		double d1;
		for (d1 = attacked.posZ - player.posZ; d * d + d1 * d1 < 0.0001D; d1 = (Math.random() - Math.random()) * 0.01D)
			d = (Math.random() - Math.random()) * 0.01D;

		attacked.isAirBorne = true;
		float f = MathHelper.sqrt_double(d * d + d1 * d1);
		float f1 = 0.4F;
		attacked.motionX /= 2D;
		attacked.motionY /= 2D;
		attacked.motionZ /= 2D;
		attacked.motionX += d / f * f1 * dam / max * 10;
		attacked.motionY += 0.40000000596046448D * dam / max * 2;
		attacked.motionZ += d1 / f * f1 * dam / max * 10;
		item.damageItem(1, player);
		player.playSound("mob.zombie.woodbreak", 0.8F, 0.8F + player.worldObj.rand.nextFloat() * 0.4F);

		return true;
	}
}
