package ganymedes01.ganysnether.core.handlers;

import ganymedes01.ganysnether.core.utils.HoeList;
import ganymedes01.ganysnether.lib.IMCKeys;
import ganymedes01.ganysnether.lib.Reference;
import ganymedes01.ganysnether.recipes.MagmaticCentrifugeRecipes;

import java.util.logging.Level;
import java.util.logging.Logger;

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
	}

	private static void addCentrifugeRecipe(IMCMessage message) {
		NBTTagCompound data = message.getNBTValue();
		NBTTagList tagList = data.getTagList("Recipe");
		ItemStack material1 = null, material2 = null;
		ItemStack[] result = new ItemStack[tagList.tagCount() - 2];

		if (result.length > 4 || result.length <= 0) {
			Logger.getLogger(Reference.MOD_ID).log(Level.WARNING, String.format("%s tried to add an invalid recipe.", message.getSender()));
			return;
		}

		NBTTagCompound tagCompound = (NBTTagCompound) tagList.tagAt(0);
		if (tagCompound.getByte("material1") == 0)
			material1 = ItemStack.loadItemStackFromNBT(tagCompound);

		tagCompound = (NBTTagCompound) tagList.tagAt(1);
		if (tagCompound.getByte("material2") == 1)
			material2 = ItemStack.loadItemStackFromNBT(tagCompound);

		for (int i = 2; i < tagList.tagCount(); i++) {
			tagCompound = (NBTTagCompound) tagList.tagAt(i);
			byte slot = (byte) (tagCompound.getByte("result") - 2);
			if (slot >= 0 && slot < result.length)
				result[slot] = ItemStack.loadItemStackFromNBT(tagCompound);
		}

		MagmaticCentrifugeRecipes.addRecipe(message.getSender(), material1, material2, result);
	}

	private static void addHoeThatCanTillNetherrack(IMCMessage message) {
		HoeList.addHoe(message.getItemStackValue());
	}
}