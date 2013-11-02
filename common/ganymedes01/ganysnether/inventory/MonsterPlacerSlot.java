package ganymedes01.ganysnether.inventory;

import ganymedes01.ganysnether.recipes.ReproducerRecipes;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class MonsterPlacerSlot extends Slot {

	public MonsterPlacerSlot(IInventory inventory, int slot, int posX, int posY) {
		super(inventory, slot, posX, posY);
	}

	@Override
	public boolean isItemValid(ItemStack stack) {
		return ReproducerRecipes.isValidSpawnEgg(stack);
	}
}