package ganymedes01.ganysnether.recipes.centrifuge;

import net.minecraft.item.ItemStack;

public abstract class CentrifugeRecipe {

	protected final Object material1;
	protected final Object material2;
	protected final ItemStack[] result;

	public CentrifugeRecipe(Object material1, Object material2, ItemStack... result) {
		this.material1 = material1;
		this.material2 = material2;
		this.result = result;
	}

	public abstract Object getMaterial(int num);

	public abstract boolean containsMaterial(ItemStack material);

	public boolean isPartOfResult(ItemStack result) {
		if (result == null)
			return false;

		for (ItemStack res : this.result)
			if (res.getItem() == result.getItem() && res.getItemDamage() == result.getItemDamage())
				return true;
		return false;
	}

	public static boolean isValidRecipe(CentrifugeRecipe recipe) {
		return recipe.material1 != null && recipe.material2 != null && recipe.getResult() != null && recipe.getResult().length <= 4;
	}

	public ItemStack[] getResult() {
		return result;
	}

	protected ItemStack getStack(Object obj) {
		return (ItemStack) obj;
	}

	protected String getString(Object obj) {
		return (String) obj;
	}
}