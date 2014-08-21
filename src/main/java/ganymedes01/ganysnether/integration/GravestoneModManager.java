package ganymedes01.ganysnether.integration;

import net.minecraft.entity.Entity;

/**
 * Gany's Nether
 *
 * @author ganymedes01
 *
 */

public class GravestoneModManager extends Integration {

	private static Class<?> EntityUndeadDog;

	@Override
	public void init() {
		try {
			EntityUndeadDog = Class.forName("gravestone.entity.monster.EntityUndeadDog");
		} catch (Exception e) {
			EntityUndeadDog = null;
		}
	}

	public static boolean isUndeadDog(Entity entity) {
		if (EntityUndeadDog == null)
			return false;
		return EntityUndeadDog.isAssignableFrom(entity.getClass());
	}

	@Override
	public void postInit() {
	}

	@Override
	public String getModID() {
		return "GraveStone";
	}
}