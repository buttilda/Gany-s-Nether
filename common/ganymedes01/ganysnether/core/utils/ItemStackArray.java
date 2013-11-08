package ganymedes01.ganysnether.core.utils;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class ItemStackArray extends ArrayList<ItemStack> {

	@Override
	public boolean contains(Object obj) {
		if (obj instanceof ItemStack)
			for (ItemStack stack : this)
				if (areItemsEqual(stack, (ItemStack) obj))
					return true;
		return false;
	}

	private boolean areItemsEqual(ItemStack stack1, ItemStack stack2) {
		if (stack1 == null || stack2 == null)
			return false;

		ItemStack stack1Single = stack1.copy();
		stack1Single.stackSize = 1;

		ItemStack stack2Single = stack2.copy();
		stack2Single.stackSize = 1;

		if (stack1.getItem().getMaxDamage() > 0 || stack2.getItem().getMaxDamage() > 0)
			return stack1.itemID == stack2.itemID;

		return ItemStack.areItemStacksEqual(stack1Single, stack2Single);
	}
}