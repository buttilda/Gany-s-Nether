package ganymedes01.ganysnether.api;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import cpw.mods.fml.common.event.FMLInterModComms;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class GanysNetherAPI {

	/**
	 * Allows you to register a recipe for the Magmatic Centrifuge
	 * 
	 * The order of materials is not important!
	 * 
	 * Result must not be null and must contain 4 or less items. If you try to
	 * create a recipe that is already registered it will be denyed.
	 * 
	 * Both materials must not be null.
	 * 
	 * @param material1
	 *            : One of the materials
	 * @param material2
	 *            : The other material
	 * @param result
	 *            : Up to 4 outputs
	 */
	public static final void addMagmaticCentrifugeRecipe(ItemStack material1, ItemStack material2, ItemStack... result) {
		if (result.length > 4 || material1 == null || material2 == null)
			return;
		else
			for (ItemStack res : result)
				if (res == null)
					return;

		NBTTagCompound data = new NBTTagCompound();
		NBTTagList tagList = new NBTTagList();
		NBTTagCompound tagCompound;

		tagCompound = new NBTTagCompound();
		tagCompound.setByte("material1", (byte) 0);
		material1.writeToNBT(tagCompound);
		tagList.appendTag(tagCompound);

		tagCompound = new NBTTagCompound();
		tagCompound.setByte("material2", (byte) 1);
		material2.writeToNBT(tagCompound);
		tagList.appendTag(tagCompound);

		for (int i = 0; i < result.length; i++)
			if (result[i] != null) {
				tagCompound = new NBTTagCompound();
				tagCompound.setByte("result", (byte) (i + 2));
				result[i].writeToNBT(tagCompound);
				tagList.appendTag(tagCompound);
			}
		data.setTag("Recipe", tagList);

		FMLInterModComms.sendMessage("ganysnether", "addCentrifugeRecipe", data);
	}

	/**
	 * Allows you to register an item that will till netherrack.
	 * 
	 * ITEM MUST CREATE A UseHoeEvent EVENT ON THE onItemUse METHOD OTHERWISE
	 * THIS WON'T WORK! Either extend the ItemHoe class or look at the onItemUse
	 * method inside of it for an example of what to do.
	 * 
	 * @param hoe
	 */
	public static final void addHoeThatCanTillNetherrack(Item hoe) {
		if (hoe != null)
			FMLInterModComms.sendMessage("ganysnether", "addHoeThatCanTillNetherrack", new ItemStack(hoe));
	}

	/**
	 * Any entity classes passed to this method WON'T be collected by the
	 * Sceptre of Concealment
	 * 
	 * @param entity
	 */
	public static final void blackListEntity(Class<? extends EntityLivingBase> entity) {
		if (entity != null)
			FMLInterModComms.sendMessage("ganysnether", "blackListEntity", entity.getName());
	}

	/**
	 * If your mod adds an entity that doesn't use the vanilla spawn eggs use
	 * this method to make the Sceptre of Concealment drop the correct egg
	 * (otherwise it won't drop anything)
	 * 
	 * NOTE: Be smart... passing any random items to this won't turn them into
	 * spawners. That's for you to handle. All this will do is drop the item.
	 * 
	 * @param entity
	 * @param egg
	 *            : Item that spawns entity
	 */
	public static final void addCustomSpawnEgg(Class<? extends EntityLivingBase> entity, ItemStack egg) {
		if (entity != null && egg != null) {
			NBTTagCompound data = new NBTTagCompound();
			data.setString("entityClass", entity.getName());

			NBTTagCompound tagCompound = new NBTTagCompound();
			egg.writeToNBT(tagCompound);
			data.setCompoundTag("spawnEgg", tagCompound);

			FMLInterModComms.sendMessage("ganysnether", "addCustomSpawnEgg", data);
		}
	}

	/**
	 * You'll be able to duplicate/duplicate other items on the Reproducer using
	 * these two items together. Not much point to it unless your mob gives you
	 * access to the spawnEgg item or you added it to the Sceptre of Concealment
	 * list.
	 * 
	 * You won't be able to override already registered tuples!
	 * 
	 * @param spawnEgg
	 *            : Item that spawns the entity
	 * @param mobDrop
	 *            : Item dropped by the entity
	 */
	public static final void addMobDropAndEggTuple(ItemStack spawnEgg, ItemStack mobDrop) {
		if (spawnEgg != null && mobDrop != null) {
			NBTTagCompound data = new NBTTagCompound();

			NBTTagCompound tagCompound = new NBTTagCompound();
			spawnEgg.writeToNBT(tagCompound);
			data.setCompoundTag("spawnEgg", tagCompound);

			tagCompound = new NBTTagCompound();
			mobDrop.writeToNBT(tagCompound);
			data.setCompoundTag("mobDrop", tagCompound);

			FMLInterModComms.sendMessage("ganysnether", "addMobDropAndEggTuple", data);
		}
	}

	/**
	 * If your entity uses vanilla eggs you can use this method to register its
	 * drop to the Reproducer
	 * 
	 * You won't be able to override already registered tuples!
	 * 
	 * @param entity
	 *            : Entity that has a vanilla spawn egg
	 * @param mobDrop
	 *            : Item dropped by the entity
	 */
	public static final void addMobDropAndEggTuple(Class<? extends EntityLivingBase> entity, ItemStack mobDrop) {
		if (mobDrop != null) {
			NBTTagCompound data = new NBTTagCompound();

			data.setString("entityClass", entity.getName());

			NBTTagCompound tagCompound = new NBTTagCompound();
			mobDrop.writeToNBT(tagCompound);
			data.setCompoundTag("mobDrop", tagCompound);

			FMLInterModComms.sendMessage("ganysnether", "addMobDropAndEntityTuple", data);
		}
	}
}