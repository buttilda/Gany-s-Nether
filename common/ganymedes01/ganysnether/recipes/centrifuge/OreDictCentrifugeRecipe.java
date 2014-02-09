package ganymedes01.ganysnether.recipes.centrifuge;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class OreDictCentrifugeRecipe extends CentrifugeRecipe {

	private static final Random rand = new Random();

	public OreDictCentrifugeRecipe(String material1, Object material2, ItemStack... result) {
		super(material1, material2, result);
	}

	@Override
	public Object getMaterial(int num) {
		if (num == 1)
			return OreDictionary.getOres(getString(material1)).toArray(new ItemStack[0]);
		else if (num == 2)
			return material2 instanceof String ? OreDictionary.getOres(getString(material2)).toArray(new ItemStack[0]) : getStack(material2);
		return null;
	}

	@Override
	public boolean containsMaterial(ItemStack material) {
		if (material == null)
			return false;

		if (material2 instanceof ItemStack) {
			if (getStack(material2).itemID == material.itemID && getStack(material2).getItemDamage() == material.getItemDamage())
				return true;
		} else if (listContainsStack(OreDictionary.getOres(getString(material2)), material))
			return true;

		if (listContainsStack(OreDictionary.getOres(getString(material1)), material))
			return true;

		return false;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		else if (obj instanceof OreDictCentrifugeRecipe) {
			OreDictCentrifugeRecipe recipe = (OreDictCentrifugeRecipe) obj;
			if (getString(recipe.material1).matches(getString(material1)))
				if (material2.getClass() == recipe.material2.getClass())
					if (material2 instanceof String)
						return getString(recipe.material2).matches(getString(material2));
					else
						return getStack(material2).itemID == getStack(recipe.material2).itemID && getStack(material2).getItemDamage() == getStack(recipe.material2).getItemDamage();
		} else if (obj instanceof ItemStackCentrifugeRecipe) {
			ItemStackCentrifugeRecipe recipe = (ItemStackCentrifugeRecipe) obj;
			if (listContainsStack(OreDictionary.getOres(getString(material1)), getStack(recipe.material1)) && listContainsStack(OreDictionary.getOres(getString(material2)), getStack(recipe.material2)))
				return true;
			else
				return listContainsStack(OreDictionary.getOres(getString(material1)), getStack(recipe.material2)) && listContainsStack(OreDictionary.getOres(getString(material2)), getStack(recipe.material1));
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(material1 + " + " + material2 instanceof String ? getString(material2) : getStack(material2).getUnlocalizedName() + " = ");
		for (ItemStack stack : getResult())
			buffer.append(stack.getUnlocalizedName() + ", ");
		buffer.deleteCharAt(buffer.length() - 1);
		buffer.deleteCharAt(buffer.length() - 1);
		return buffer.toString();
	}

	private boolean listContainsStack(ArrayList<ItemStack> list, ItemStack stack) {
		for (ItemStack s : list)
			if (s.itemID == stack.itemID && s.getItemDamage() == stack.getItemDamage())
				return true;
		return false;
	}
}