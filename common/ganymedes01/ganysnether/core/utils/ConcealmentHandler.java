package ganymedes01.ganysnether.core.utils;

import ganymedes01.ganysnether.items.ModItems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class ConcealmentHandler {

	private static Map<Class<? extends EntityLivingBase>, ItemStack> entityEggs = new HashMap<Class<? extends EntityLivingBase>, ItemStack>();
	private static ArrayList<Class<? extends EntityLivingBase>> blackListedEntities = new ArrayList<Class<? extends EntityLivingBase>>();

	public static boolean canBeConcealed(EntityLivingBase entity) {
		return entityEggs.containsKey(entity.getClass()) || !blackListedEntities.contains(entity.getClass()) && EntityList.entityEggs.get(EntityList.getEntityID(entity)) != null && !(entity instanceof IBossDisplayData);
	}

	public static ItemStack getEggFromEntity(EntityLivingBase entity) {
		if (canBeConcealed(entity))
			if (entityEggs.containsKey(entity.getClass()))
				return entityEggs.get(entity.getClass()).copy();
			else if (entity.getClass() == EntitySkeleton.class)
				return new ItemStack(ModItems.skeletonSpawner, 1, ((EntitySkeleton) entity).getSkeletonType());
			else
				return new ItemStack(Item.monsterPlacer, 1, EntityList.getEntityID(entity));
		else
			return null;
	}

	public static void blackListEntity(Class<? extends EntityLivingBase> entity) {
		if (!blackListedEntities.contains(entity))
			blackListedEntities.add(entity);
	}

	public static void addCustomEggDropForEntity(Class<? extends EntityLivingBase> entity, ItemStack egg) {
		if (!entityEggs.containsKey(entity) && !blackListedEntities.contains(entity))
			entityEggs.put(entity, egg);
	}

	public static ItemStack[] getEggs() {
		ItemStack[] eggs = new ItemStack[entityEggs.entrySet().size() + 2];
		eggs[0] = new ItemStack(ModItems.skeletonSpawner, 1, OreDictionary.WILDCARD_VALUE);
		eggs[1] = new ItemStack(Item.monsterPlacer, 1, OreDictionary.WILDCARD_VALUE);

		int index = 2;
		for (Entry<Class<? extends EntityLivingBase>, ItemStack> set : entityEggs.entrySet())
			eggs[index++] = set.getValue();

		for (ItemStack stack : eggs)
			System.out.println(stack);

		return eggs;
	}
}