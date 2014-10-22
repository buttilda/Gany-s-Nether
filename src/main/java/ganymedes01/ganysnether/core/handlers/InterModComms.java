package ganymedes01.ganysnether.core.handlers;

import ganymedes01.ganysnether.core.utils.ConcealmentHandler;
import ganymedes01.ganysnether.core.utils.HoeList;
import ganymedes01.ganysnether.core.utils.RandomItemStackList;
import ganymedes01.ganysnether.lib.IMCKeys;
import ganymedes01.ganysnether.lib.Reference;
import ganymedes01.ganysnether.recipes.MagmaticCentrifugeRecipes;
import ganymedes01.ganysnether.recipes.ReproducerRecipes;
import ganymedes01.ganysnether.recipes.VolcanicFurnaceHandler;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import cpw.mods.fml.common.event.FMLInterModComms.IMCEvent;
import cpw.mods.fml.common.event.FMLInterModComms.IMCMessage;

/**
 * Gany's Nether
 *
 * @author ganymedes01
 *
 */

public class InterModComms {

	public static void processIMC(IMCEvent event) {
		for (IMCMessage message : event.getMessages())
			if (message.key.equals(IMCKeys.CENTRIFUGE_RECIPE))
				addCentrifugeRecipe(message);
			else if (message.key.equals(IMCKeys.NETHERRACK_HOE))
				addHoeThatCanTillNetherrack(message);
			else if (message.key.equals(IMCKeys.BLACK_LIST_ENTITY))
				blackListEntity(message);
			else if (message.key.equals(IMCKeys.ADD_CUSTOM_SPAWN_EGG))
				addCustomSpawnEgg(message);
			else if (message.key.equals(IMCKeys.ADD_MOB_DROP_AND_EGG_TUPLE))
				addMobDropAndEggTuple(message);
			else if (message.key.equals(IMCKeys.ADD_MOB_DROP_AND_ENTITY_TUPLE))
				addMobDropAndEntityTuple(message);
			else if (message.key.equals(IMCKeys.WHITE_LIST_MELTING_ITEM))
				whiteListMeltingItem(message);
			else if (message.key.equals(IMCKeys.BLACK_LIST_MELTING_ITEM))
				blackListMeltingItem(message);
			else if (message.key.equals(IMCKeys.ADD_BURN_TIME_FOR_ITEM))
				addBurnTimeForItem(message);
			else if (message.key.equals(IMCKeys.ADD_STACK_TO_UNDERTAKERS))
				addStackToUndertakers(message);
	}

	private static void addCentrifugeRecipe(IMCMessage message) {
		try {
			NBTTagCompound data = message.getNBTValue();
			NBTTagList tagList = data.getTagList("Recipe", 10);
			ItemStack material1 = null, material2 = null;
			ItemStack[] result = new ItemStack[tagList.tagCount() - 2];

			if (result.length > 4 || result.length <= 0) {
				Logger.getLogger(Reference.MOD_ID).log(Level.WARNING, String.format("%s tried to add an invalid recipe to the Magmatic Centrifuge.", message.getSender()));
				return;
			}

			NBTTagCompound tagCompound = tagList.getCompoundTagAt(0);
			if (tagCompound.getByte("material1") == 0)
				material1 = ItemStack.loadItemStackFromNBT(tagCompound);

			tagCompound = tagList.getCompoundTagAt(1);
			if (tagCompound.getByte("material2") == 1)
				material2 = ItemStack.loadItemStackFromNBT(tagCompound);

			for (int i = 2; i < tagList.tagCount(); i++) {
				tagCompound = tagList.getCompoundTagAt(i);
				byte slot = (byte) (tagCompound.getByte("result") - 2);
				if (slot >= 0 && slot < result.length)
					result[slot] = ItemStack.loadItemStackFromNBT(tagCompound);
			}

			MagmaticCentrifugeRecipes.INSTANCE.addRecipe(material1, material2, result);
		} catch (Exception e) {
			Logger.getLogger(Reference.MOD_ID).log(Level.WARNING, String.format("%s failed to add a recipe to the Magmatic Centrifuge.", message.getSender()));
		}
	}

	private static void addHoeThatCanTillNetherrack(IMCMessage message) {
		try {
			ItemStack hoe = message.getItemStackValue();
			if (hoe != null)
				HoeList.addHoe(hoe);
			else {
				Logger.getLogger(Reference.MOD_ID).log(Level.WARNING, String.format("%s failed to register a hoe: Null Stack", message.getSender()));
				return;
			}

		} catch (Exception e) {
			Logger.getLogger(Reference.MOD_ID).log(Level.WARNING, String.format("%s failed to register a hoe.", message.getSender()));
		}
	}

	@SuppressWarnings("unchecked")
	private static void blackListEntity(IMCMessage message) {
		String entityClass = message.getStringValue();
		try {
			ConcealmentHandler.blackListEntity((Class<? extends EntityLivingBase>) Class.forName(entityClass));
		} catch (ClassNotFoundException e) {
			Logger.getLogger(Reference.MOD_ID).log(Level.WARNING, String.format("%s failed to black list an entity: Wrong class name", message.getSender()));
		} catch (Exception e) {
			Logger.getLogger(Reference.MOD_ID).log(Level.WARNING, String.format("%s failed to black list an entity: Class is probably not EntityLivingBase", message.getSender()));
		}
	}

	@SuppressWarnings("unchecked")
	private static void addCustomSpawnEgg(IMCMessage message) {
		NBTTagCompound data = message.getNBTValue();
		try {
			Class<? extends EntityLivingBase> entityClass = (Class<? extends EntityLivingBase>) Class.forName(data.getString("entityClass"));
			ItemStack spawnEgg = ItemStack.loadItemStackFromNBT(data.getCompoundTag("spawnEgg"));

			ConcealmentHandler.addCustomEggDropForEntity(entityClass, spawnEgg);
		} catch (ClassNotFoundException e) {
			Logger.getLogger(Reference.MOD_ID).log(Level.WARNING, String.format("%s failed to register a custom spawn egg to a class: Class not found", message.getSender()));
		} catch (Exception e) {
			Logger.getLogger(Reference.MOD_ID).log(Level.WARNING, String.format("%s failed to register a custom spawn egg to a class: Probably formatted the message wrong", message.getSender()));
		}
	}

	private static void addMobDropAndEggTuple(IMCMessage message) {
		try {
			NBTTagCompound data = message.getNBTValue();

			ItemStack spawnEgg = ItemStack.loadItemStackFromNBT(data.getCompoundTag("spawnEgg"));
			ItemStack mobDrop = ItemStack.loadItemStackFromNBT(data.getCompoundTag("mobDrop"));

			ReproducerRecipes.addMobDropAndEggTuple(spawnEgg, mobDrop);
		} catch (Exception e) {
			Logger.getLogger(Reference.MOD_ID).log(Level.WARNING, String.format("%s failed to register a tuple to the Reproducer", message.getSender()));
		}
	}

	@SuppressWarnings("rawtypes")
	private static void addMobDropAndEntityTuple(IMCMessage message) {
		try {
			NBTTagCompound data = message.getNBTValue();
			String entityName = (String) EntityList.classToStringMapping.get(Class.forName(data.getString("entityClass")));

			Field field = EntityList.class.getDeclaredField("stringToIDMapping");
			field.setAccessible(true);
			Map stringToIDMapping = (Map) field.get(null);

			ItemStack spawnEgg = new ItemStack(Items.spawn_egg, 1, (Integer) stringToIDMapping.get(entityName));
			ItemStack mobDrop = ItemStack.loadItemStackFromNBT(data.getCompoundTag("mobDrop"));

			ReproducerRecipes.addMobDropAndEggTuple(spawnEgg, mobDrop);
		} catch (Exception e) {
			Logger.getLogger(Reference.MOD_ID).log(Level.WARNING, String.format("%s failed to register an entity to the Reproducer", message.getSender()));
		}
	}

	public static void whiteListMeltingItem(IMCMessage message) {
		try {
			ItemStack stack = message.getItemStackValue();
			if (stack != null) {
				if (stack.stackSize > 1)
					stack.stackSize = 1;

				VolcanicFurnaceHandler.whiteListItem(stack);
			}
		} catch (Exception e) {
			Logger.getLogger(Reference.MOD_ID).log(Level.WARNING, String.format("%s failed to white-list and item for the Volcanic Furnace", message.getSender()));
		}
	}

	public static void blackListMeltingItem(IMCMessage message) {
		try {
			ItemStack stack = message.getItemStackValue();
			if (stack != null) {
				if (stack.stackSize > 1)
					stack.stackSize = 1;

				VolcanicFurnaceHandler.blackListItem(stack);
			}
		} catch (Exception e) {
			Logger.getLogger(Reference.MOD_ID).log(Level.WARNING, String.format("%s failed to black-list and item for the Volcanic Furnace", message.getSender()));
		}
	}

	public static void addBurnTimeForItem(IMCMessage message) {
		try {
			NBTTagCompound data = message.getNBTValue();

			int burnTime = data.getInteger("burnTime");
			if (burnTime <= 0)
				return;

			ItemStack stack = ItemStack.loadItemStackFromNBT(data.getCompoundTag("stack"));
			if (stack.stackSize > 1)
				stack.stackSize = 1;

			VolcanicFurnaceHandler.addBurnTimeForItem(stack, burnTime);
		} catch (Exception e) {
			Logger.getLogger(Reference.MOD_ID).log(Level.WARNING, String.format("%s failed to set a custom burn time for an item on the Volcanic Furnace", message.getSender()));
		}
	}

	private static void addStackToUndertakers(IMCMessage message) {
		try {
			NBTTagCompound data = message.getNBTValue();
			ItemStack stack = ItemStack.loadItemStackFromNBT(data.getCompoundTag("stack"));
			int weight = data.getInteger("weight");
			if (stack != null && stack.stackSize > 0 && weight > 0)
				RandomItemStackList.insertStackOnList(stack, weight);
			else
				Logger.getLogger(Reference.MOD_ID).log(Level.WARNING, String.format("%s failed to add a custom stack to the Undertakers: Stack null, stacksize 0 or weight smaller than 0", message.getSender()));
		} catch (Exception e) {
			Logger.getLogger(Reference.MOD_ID).log(Level.WARNING, String.format("%s failed to add a custom stack to the Undertakers", message.getSender()));
		}
	}
}