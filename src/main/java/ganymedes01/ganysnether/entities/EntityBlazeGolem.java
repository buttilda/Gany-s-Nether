package ganymedes01.ganysnether.entities;

import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityBlazeGolem extends EntitySnowman {

	public EntityBlazeGolem(World world) {
		super(world);
	}

	@Override
	public boolean attackEntityFrom(DamageSource src, float dmg) {
		if (src == DamageSource.onFire || src == DamageSource.lava)
			return false;

		return super.attackEntityFrom(src, dmg);
	}
}