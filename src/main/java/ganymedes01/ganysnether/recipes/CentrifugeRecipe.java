package ganymedes01.ganysnether.recipes;

import java.util.ArrayList;
import java.util.List;

import ganymedes01.ganysnether.core.utils.InventoryUtils;
import ganymedes01.ganysnether.core.utils.xml.OreStack;
import ganymedes01.ganysnether.core.utils.xml.XMLNode;
import ganymedes01.ganysnether.core.utils.xml.XMLParser;
import ganymedes01.ganysnether.recipes.RecipeInput.RecipeInputItemStack;
import ganymedes01.ganysnether.recipes.RecipeInput.RecipeInputOre;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;

/**
 * Gany's Nether
 *
 * @author ganymedes01
 *
 */

public class CentrifugeRecipe {

	private final RecipeInput<?> input1;
	private final RecipeInput<?> input2;
	private final ItemStack[] outputs;
	private final int lavaAmount;

	public CentrifugeRecipe(XMLNode node) {
		input1 = getValidObject(XMLParser.parseNode(node.getNode("input1")));
		input2 = getValidObject(XMLParser.parseNode(node.getNode("input2")));

		List<Object> outputs = new ArrayList<Object>();
		for (int i = 1; i <= 4; i++) {
			XMLNode n = node.getNode("output" + i);
			if (n != null)
				outputs.add(XMLParser.parseItemStackNode(n));
		}

		XMLNode n = node.getNode("lavaAmount");
		if (n != null)
			lavaAmount = Integer.parseInt(n.getValue());
		else
			lavaAmount = FluidContainerRegistry.BUCKET_VOLUME / 10;

		this.outputs = outputs.toArray(new ItemStack[0]);
	}

	public CentrifugeRecipe(Object input1, Object input2, int lavaAmount, ItemStack... outputs) {
		this.input1 = getValidObject(input1);
		this.input2 = getValidObject(input2);
		this.lavaAmount = lavaAmount;
		this.outputs = outputs;
	}

	public CentrifugeRecipe(Object input1, Object input2, ItemStack... outputs) {
		this(input1, input2, FluidContainerRegistry.BUCKET_VOLUME / 10, outputs);
	}

	public boolean isPartOfResult(ItemStack target) {
		for (ItemStack output : outputs)
			if (InventoryUtils.areStacksTheSame(output, target, false))
				return true;
		return false;
	}

	public boolean isPartOfInput(ItemStack target) {
		return input1.matches(target) || input2.matches(target);
	}

	public boolean matches(ItemStack input1, ItemStack input2) {
		if (this.input1.matches(input1) && this.input2.matches(input2))
			return true;
		if (this.input1.matches(input2) && this.input2.matches(input1))
			return true;
		return false;
	}

	public RecipeInput<?> getInput1() {
		return input1;
	}

	public RecipeInput<?> getInput2() {
		return input2;
	}

	public ItemStack[] getResult() {
		return outputs;
	}

	public int getLavaAmount() {
		return lavaAmount;
	}

	private RecipeInput<?> getValidObject(Object obj) {
		if (obj instanceof Item)
			return new RecipeInputItemStack(new ItemStack((Item) obj));
		else if (obj instanceof Block)
			return new RecipeInputItemStack(new ItemStack((Block) obj));
		else if (obj instanceof ItemStack)
			return new RecipeInputItemStack((ItemStack) obj);
		else if (obj instanceof String)
			return new RecipeInputOre(new OreStack((String) obj));
		else if (obj instanceof OreStack)
			return new RecipeInputOre((OreStack) obj);
		else if (obj instanceof RecipeInput)
			return (RecipeInput<?>) obj;

		return null;
	}
}