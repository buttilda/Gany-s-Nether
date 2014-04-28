package ganymedes01.ganysnether.recipes.centrifuge;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class ItemStackCentrifugeRecipe extends CentrifugeRecipe {

	public ItemStackCentrifugeRecipe(ItemStack material1, ItemStack material2, ItemStack... result) {
		super(material1, material2, result);
	}

	@Override
	public ItemStack getMaterial(int num) {
		return num == 1 ? getStack(material1).copy() : num == 2 ? getStack(material2).copy() : null;
	}

	@Override
	public boolean containsMaterial(ItemStack material) {
		if (material == null)
			return false;

		return areStacksEqual(material, getStack(material1)) || areStacksEqual(material, getStack(material2));
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof ItemStackCentrifugeRecipe) {
			ItemStackCentrifugeRecipe recipe = (ItemStackCentrifugeRecipe) obj;
			ItemStack material1 = recipe.getMaterial(1);
			ItemStack material2 = recipe.getMaterial(2);
			if (material1 == null || material2 == null)
				return false;
			else {
				if (areStacksEqual(material1, getMaterial(1)))
					if (areStacksEqual(material2, getMaterial(2)))
						return true;
				if (areStacksEqual(material2, getMaterial(1)))
					if (areStacksEqual(material1, getMaterial(2)))
						return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(getStack(material1).getUnlocalizedName() + " + " + getStack(material2).getUnlocalizedName() + " = ");
		for (ItemStack stack : getResult())
			buffer.append(stack.getUnlocalizedName() + ", ");
		buffer.deleteCharAt(buffer.length() - 1);
		buffer.deleteCharAt(buffer.length() - 1);
		return buffer.toString();
	}

	private boolean areStacksEqual(ItemStack stack1, ItemStack stack2) {
		if (stack1.getItem() == stack2.getItem())
			return stack1.getItemDamage() == OreDictionary.WILDCARD_VALUE ? true : stack1.getItemDamage() == stack2.getItemDamage();
		return false;
	}
}