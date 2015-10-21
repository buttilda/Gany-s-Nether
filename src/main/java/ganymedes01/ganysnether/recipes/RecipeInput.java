package ganymedes01.ganysnether.recipes;

import ganymedes01.ganysnether.core.utils.InventoryUtils;
import ganymedes01.ganysnether.core.utils.xml.OreStack;
import net.minecraft.item.ItemStack;

/**
 * Gany's Nether
 *
 * @author ganymedes01
 *
 */

public abstract class RecipeInput<T> {

	protected final T obj;

	public RecipeInput(T obj) {
		this.obj = obj;
	}

	public abstract int getSize();

	public abstract boolean matches(ItemStack target);

	public final Object getObject() {
		return obj;
	}

	public static class RecipeInputItemStack extends RecipeInput<ItemStack> {

		public RecipeInputItemStack(ItemStack stack) {
			super(stack);
		}

		@Override
		public int getSize() {
			return obj.stackSize;
		}

		@Override
		public boolean matches(ItemStack target) {
			return obj.stackSize <= target.stackSize && InventoryUtils.areStacksTheSame(obj, target, false);
		}
	}

	public static class RecipeInputOre extends RecipeInput<OreStack> {

		public RecipeInputOre(OreStack stack) {
			super(stack);
		}

		@Override
		public int getSize() {
			return obj.size;
		}

		@Override
		public boolean matches(ItemStack target) {
			return obj.size <= target.stackSize && InventoryUtils.isItemOre(target, obj.ore);
		}
	}
}