package ganymedes01.ganysnether.entities;

import ganymedes01.ganysnether.world.SlowExplosion;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.world.World;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class EntitySlowTNT extends EntityTNTPrimed {

	public EntitySlowTNT(World world) {
		super(world);
	}

	public EntitySlowTNT(World world, float x, float y, float z, EntityLivingBase entity) {
		super(world, x, y, z, entity);
	}

	@Override
	public void onUpdate() {
		prevPosX = posX;
		prevPosY = posY;
		prevPosZ = posZ;
		motionY -= 0.03999999910593033D;
		moveEntity(motionX, motionY, motionZ);
		motionX *= 0.9800000190734863D;
		motionY *= 0.9800000190734863D;
		motionZ *= 0.9800000190734863D;

		if (onGround) {
			motionX *= 0.699999988079071D;
			motionZ *= 0.699999988079071D;
			motionY *= -0.5D;
		}

		if (fuse-- <= 0) {
			setDead();

			if (!worldObj.isRemote)
				explode();
		} else
			worldObj.spawnParticle("smoke", posX, posY + 0.5D, posZ, 0.0D, 0.0D, 0.0D);
	}

	private void explode() {
		SlowExplosion explosion = new SlowExplosion(worldObj, this, posX, posY, posZ, 4.0F);
		explosion.isFlaming = true;
		explosion.isSmoking = true;
		explosion.doExplosionA();
		explosion.doExplosionB(true);
	}
}