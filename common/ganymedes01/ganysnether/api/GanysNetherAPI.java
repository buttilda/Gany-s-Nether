package ganymedes01.ganysnether.api;

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
}