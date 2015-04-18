package ganymedes01.ganysnether.items;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.Reference;
import ganymedes01.ganysnether.lib.Strings;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;

/**
 * Gany's Nether
 *
 * @author ganymedes01
 *
 */

public class BaseballBat extends Item {

	public BaseballBat() {
		setFull3D();
		setMaxStackSize(1);
		setMaxDamage(GanysNether.baseballBatDurability);
		setTextureName(Utils.getItemTexture(Strings.Items.BASEBALL_BAT_NAME));
		setCreativeTab(GanysNether.enableBaseballBat ? GanysNether.netherTab : null);
		setUnlocalizedName(Utils.getUnlocalisedName(Strings.Items.BASEBALL_BAT_NAME));
	}

	@Override
	public boolean hitEntity(ItemStack item, EntityLivingBase target, EntityLivingBase player) {
		int max = item.getMaxDamage();
		int dam = max - item.getItemDamage();
		double d = target.posX - player.posX;
		double d1;
		for (d1 = target.posZ - player.posZ; d * d + d1 * d1 < 0.0001D; d1 = (Math.random() - Math.random()) * 0.01D)
			d = (Math.random() - Math.random()) * 0.01D;

		target.isAirBorne = true;
		float f = MathHelper.sqrt_double(d * d + d1 * d1);
		float f1 = 0.4F;
		target.motionX /= 2D;
		target.motionY /= 2D;
		target.motionZ /= 2D;
		target.motionX += d / f * f1 * dam / max * 10;
		target.motionY += 0.4D * dam / max * 2;
		target.motionZ += d1 / f * f1 * dam / max * 10;
		item.damageItem(1, player);
		player.worldObj.playSoundAtEntity(target, Reference.MOD_ID + ":bat", 1.5F, player.worldObj.rand.nextFloat() * 0.1F + 0.9F);

		return true;
	}
}
