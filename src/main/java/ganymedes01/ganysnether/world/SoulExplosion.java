package ganymedes01.ganysnether.world;

import java.util.Iterator;
import java.util.List;

import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class SoulExplosion extends Explosion {

	private final World worldObj;

	public SoulExplosion(World world, Entity entity, double x, double y, double z, float power) {
		super(world, entity, x, y, z, power);
		worldObj = world;
	}

	@Override
	@SuppressWarnings("rawtypes")
	public void doExplosionA() {
		super.doExplosionA();

		List list = worldObj.selectEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(explosionX - 2 * explosionSize, explosionY - 2 * explosionSize, explosionZ - 2 * explosionSize, explosionX + 2 * explosionSize, explosionY + 2 * explosionSize, explosionZ + 2 * explosionSize), IEntitySelector.selectAnything);
		if (!list.isEmpty()) {
			Iterator iterator = list.iterator();
			while (iterator.hasNext()) {
				EntityLivingBase entity = (EntityLivingBase) iterator.next();
				if (entity.worldObj == worldObj) {
					int duration = 3000;
					PotionEffect slowness = entity.getActivePotionEffect(Potion.moveSlowdown);
					if (slowness != null && slowness.getAmplifier() == 2)
						duration += slowness.getDuration();
					entity.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, duration, 2));
				}
			}
		}
	}
}