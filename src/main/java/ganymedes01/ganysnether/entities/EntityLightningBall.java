package ganymedes01.ganysnether.entities;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class EntityLightningBall extends EntityThrowable {

	public EntityLightningBall(World world) {
		super(world);
		setSpeed();
	}

	public EntityLightningBall(World world, EntityLivingBase thrower) {
		super(world, thrower);
		setSpeed();
	}

	public EntityLightningBall(World world, double x, double y, double z) {
		super(world, x, y, z);
		setSpeed();
	}

	@Override
	protected void onImpact(MovingObjectPosition target) {
		if (target.entityHit != null)
			worldObj.addWeatherEffect(new EntityLightningBolt(worldObj, target.entityHit.posX, target.entityHit.posY, target.entityHit.posZ));
		else
			worldObj.addWeatherEffect(new EntityLightningBolt(worldObj, target.blockX, target.blockY, target.blockZ));

		if (!worldObj.isRemote)
			setDead();
	}

	@Override
	protected float getGravityVelocity() {
		return 0.0F;
	}

	private void setSpeed() {
		motionX *= 3;
		motionY *= 3;
		motionZ *= 3;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (!worldObj.isRemote && !worldObj.blockExists((int) posX, (int) posY, (int) posZ))
			setDead();
	}
}