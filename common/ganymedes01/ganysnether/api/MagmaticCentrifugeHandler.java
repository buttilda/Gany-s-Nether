package ganymedes01.ganysnether.api;

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

public class MagmaticCentrifugeHandler {

	public static final void addRecipe(ItemStack material1, ItemStack material2, ItemStack... result) {
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
}