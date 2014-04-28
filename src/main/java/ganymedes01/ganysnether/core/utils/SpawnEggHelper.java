package ganymedes01.ganysnether.core.utils;

import ganymedes01.ganysnether.items.SkeletonSpawner;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class SpawnEggHelper {

	public static Entity getEntity(World world, double x, double y, double z, ItemStack egg) {
		if (egg.getItem().getClass() == ItemMonsterPlacer.class)
			return getEntity(world, egg.getItemDamage(), x, y, z);
		else if (egg.getItem().getClass() == SkeletonSpawner.class)
			return getEntity(world, 51, x, y, z);
		else
			try {
				if (egg.getItem().getClass() == Class.forName("erebus.item.ItemSpawnEggs")) {
					Method getEggData = egg.getItem().getClass().getDeclaredMethod("getEggData", ItemStack.class);
					getEggData.setAccessible(true);
					Object eggData = getEggData.invoke(null, egg);
					Field entityClass = eggData.getClass().getDeclaredField("entityClass");
					entityClass.setAccessible(true);

					return createEntity(world, x, y, z, (Class<? extends Entity>) entityClass.get(eggData));
				} else if (egg.getItem().getClass() == Class.forName("twilightforest.item.ItemTFSpawnEgg")) {
					Method createEntityByID = Class.forName("twilightforest.entity.TFCreatures").getDeclaredMethod("createEntityByID", int.class, World.class);
					return setEntityPositions((Entity) createEntityByID.invoke(null, egg.getItemDamage(), world), world, x, y, z);
				}
				return null;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
	}

	public static Entity getEntity(World world, int damage, double x, double y, double z) {
		return createEntity(world, x, y, z, (Class<? extends Entity>) EntityList.IDtoClassMapping.get(damage));
	}

	private static Entity createEntity(World world, double x, double y, double z, Class<? extends Entity> oclass) {
		try {
			if (oclass != null) {
				Entity entity = oclass.getConstructor(new Class[] { World.class }).newInstance(new Object[] { world });
				return setEntityPositions(entity, world, x, y, z);
			}
		} catch (Exception e) {
		}

		return null;
	}

	private static Entity setEntityPositions(Entity entity, World world, double x, double y, double z) {
		if (entity != null && entity instanceof EntityLivingBase) {
			EntityLiving entityliving = (EntityLiving) entity;
			entity.setLocationAndAngles(x, y, z, MathHelper.wrapAngleTo180_float(world.rand.nextFloat() * 360.0F), 0.0F);
			entityliving.rotationYawHead = entityliving.rotationYaw;
			entityliving.renderYawOffset = entityliving.rotationYaw;
			return entityliving;
		}
		return entity;
	}
}