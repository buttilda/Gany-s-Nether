package ganymedes01.ganysnether.api;

import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.event.FMLInterModComms;

public class MagmaticCentrifugeHandler {

	public static final void addRecipe(ItemStack material1, ItemStack material2, ItemStack... result) {
		if (result.length > 4 && material1 != null && material2 != null)
			return;
		else
			for (ItemStack stack : result)
				if (stack == null)
					return;
		StringBuffer formatBuffer = new StringBuffer("%d@%d@ %d@%d@");
		for (int i = 0; i < result.length; i++)
			formatBuffer.append(" %d@%d@%d@");
		formatBuffer.deleteCharAt(formatBuffer.length() - 1);

		String format = formatBuffer.toString();

		switch (result.length) {
			case 1:
				FMLInterModComms.sendMessage("ganysnether", "addCentrifugeRecipe", String.format(format, material1.itemID, material1.getItemDamage(), material2.itemID, material1.getItemDamage(), result[0].itemID, result[0].stackSize, result[0].getItemDamage()));
				break;
			case 2:
				FMLInterModComms.sendMessage("ganysnether", "addCentrifugeRecipe",
				String.format(format, material1.itemID, material1.getItemDamage(), material2.itemID, material1.getItemDamage(), result[0].itemID, result[0].stackSize, result[0].getItemDamage(), result[1].itemID, result[1].stackSize, result[1].getItemDamage()));
				break;
			case 3:
				FMLInterModComms.sendMessage("ganysnether", "addCentrifugeRecipe", String.format(format, material1.itemID, material1.getItemDamage(), material2.itemID, material1.getItemDamage(), result[0].itemID, result[0].stackSize, result[0].getItemDamage(), result[1].itemID, result[1].stackSize,
				result[1].getItemDamage(), result[2].itemID, result[2].stackSize, result[2].getItemDamage()));
				break;
			case 4:
				FMLInterModComms.sendMessage("ganysnether", "addCentrifugeRecipe", String.format(format, material1.itemID, material1.getItemDamage(), material2.itemID, material1.getItemDamage(), result[0].itemID, result[0].stackSize, result[0].getItemDamage(), result[1].itemID, result[1].stackSize,
				result[1].getItemDamage(), result[2].itemID, result[2].stackSize, result[2].getItemDamage(), result[3].itemID, result[3].stackSize, result[3].getItemDamage()));
				break;
		}
	}
}