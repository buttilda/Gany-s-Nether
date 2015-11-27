package ganymedes01.ganysnether.api;

import java.lang.reflect.Field;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.event.FMLInterModComms;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

/**
 * Gany's Nether
 *
 * @author ganymedes01
 *
 */

public class GanysNetherAPI {

	// MAGMATIC CENTRIFUGE //

	/**
	 * Allows you to register a recipe for the Magmatic Centrifuge
	 *
	 * The order of materials is not important!
	 *
	 * Result must not be null and must contain 4 or less items. If you try to create a recipe that is already registered it will be denied.
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

	// TILLING NETHERRACK //

	/**
	 * Allows you to register an item that will till netherrack.
	 *
	 * ITEM MUST CREATE A UseHoeEvent EVENT ON THE onItemUse METHOD OTHERWISE THIS WON'T WORK! Either extend the ItemHoe class or look at the onItemUse method inside of it for an example of what to do.
	 *
	 * @param hoe
	 */
	public static final void addHoeThatCanTillNetherrack(Item hoe) {
		if (hoe != null)
			FMLInterModComms.sendMessage("ganysnether", "addHoeThatCanTillNetherrack", new ItemStack(hoe));
	}

	// SCEPTRE OF CONCEALMENT //

	/**
	 * Any entity classes passed to this method WON'T be collected by the Sceptre of Concealment
	 *
	 * @param entity
	 */
	public static final void blackListEntity(Class<? extends EntityLivingBase> entity) {
		if (entity != null)
			FMLInterModComms.sendMessage("ganysnether", "blackListEntity", entity.getName());
	}

	/**
	 * If your mod adds an entity that doesn't use the vanilla spawn eggs use this method to make the Sceptre of Concealment drop the correct egg (otherwise it won't drop anything)
	 *
	 * NOTE: Be smart... passing any random items to this won't turn them into spawners. That's for you to handle. All this will do is drop the item.
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
			data.setTag("spawnEgg", tagCompound);

			FMLInterModComms.sendMessage("ganysnether", "addCustomSpawnEgg", data);
		}
	}

	// REPRODUCER //

	/**
	 * You'll be able to duplicate/duplicate other items on the Reproducer using these two items together. Not much point to it unless your mob gives you access to the spawnEgg item or you added it to the Sceptre of Concealment list.
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
			data.setTag("spawnEgg", tagCompound);

			tagCompound = new NBTTagCompound();
			mobDrop.writeToNBT(tagCompound);
			data.setTag("mobDrop", tagCompound);

			FMLInterModComms.sendMessage("ganysnether", "addMobDropAndEggTuple", data);
		}
	}

	/**
	 * If your entity uses vanilla eggs you can use this method to register its drop to the Reproducer
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
			data.setTag("mobDrop", tagCompound);

			FMLInterModComms.sendMessage("ganysnether", "addMobDropAndEntityTuple", data);
		}
	}

	// VOLCANIC FURNACE //

	/**
	 * White-Listing an item/block will force it to be melt down in the Volcanic Furnace
	 *
	 * This is metadata sensitive.
	 *
	 * @param stack
	 *            : ItemStack of item/block to be white-listed
	 */
	public static final void whiteListMeltingItem(ItemStack stack) {
		if (stack != null)
			FMLInterModComms.sendMessage("ganysnether", "whiteListMeltingItem", stack);
	}

	/**
	 * Block-Listing an item/block will force it NOT to be melt down in the Volcanic Furnace
	 *
	 * This is metadata sensitive.
	 *
	 * @param stack
	 *            : ItemStack of item/block to be black-listed
	 */
	public static final void blackListMeltingItem(ItemStack stack) {
		if (stack != null)
			FMLInterModComms.sendMessage("ganysnether", "blackListMeltingItem", stack);
	}

	/**
	 * Sets a custom burnTime of an specific item/block. Keep in mind that the burnTime is directly related to the amount of lava produced by an item.
	 *
	 * This is metadata sensitive.
	 *
	 * DO NOT SET THE BURNTIME TO ZERO. If you wish to block an item from being melted use the blackListMeltingItem method.
	 *
	 * Default is between 16 and 20
	 *
	 * 1 Bucket of lava = 1000
	 *
	 * @param stack
	 *            : ItemStack of item/block
	 * @param burnTime
	 *            : Burn time / Amount of lava produced by stack
	 */
	public static final void addBurnTimeForItem(ItemStack stack, int burnTime) {
		if (stack != null && burnTime > 0) {
			NBTTagCompound data = new NBTTagCompound();

			data.setInteger("burnTime", burnTime);

			NBTTagCompound tagCompound = new NBTTagCompound();
			stack.writeToNBT(tagCompound);
			data.setTag("stack", tagCompound);

			FMLInterModComms.sendMessage("ganysnether", "addBurnTimeForItem", data);
		}
	}

	// BLOCKS
	/*
	 * Here's a list of the blocks that can/should be retrieved by this method tilledNetherrack quarzBerryBush spectreWheat glowingReed soulGlass soulChest volcanicFurnaceIdle volcanicFurnaceActive denseLavaCell glowBox colouredQuartzBlock colouredChiselledQuartzBlock soulGlassStairs; reproducer undertaker witherShrub magmaticCentrifuge
	 */
	public static final Block getBlock(String blockName) {
		try {
			Class<?> modBlocks = Class.forName("ganymedes01.ganysnether.blocks.ModBlocks");
			Field block = modBlocks.getField(blockName);
			return (Block) block.get(null);
		} catch (Exception e) {
			FMLLog.warning("[ganysnether] Problems retrieving block: " + blockName);
			return null;
		}
	}

	// ITEMS
	/*
	 * Here's a list of the items that can/should be retrieved by this method quarzBerrySeeds quarzBerry ghostSeeds spectreWheat spookyFlour glowingReed bottomlessBucket dimensionalBread baseballBat sceptreOfConcealment skeletonSpawner silverfishScale batWing cookedBatWing wolfTeeth blazeIngot sceptreOfFireCharging sceptreOfLightning sceptreCap witherShrubSeeds livingSoul ironNugget flour blazeHelmet blazeChestplate blazeLeggings blazeBoots
	 */
	public static final Item getItem(String itemName) {
		try {
			Class<?> modBlocks = Class.forName("ganymedes01.ganysnether.items.ModItems");
			Field item = modBlocks.getField(itemName);
			return (Item) item.get(null);
		} catch (Exception e) {
			FMLLog.warning("[ganysnether] Problems retrieving item: " + itemName);
			return null;
		}
	}
}