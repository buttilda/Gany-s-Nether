package ganymedes01.ganysnether.recipes;

import ganymedes01.ganysnether.core.utils.InventoryUtils;
import ganymedes01.ganysnether.core.utils.xml.XMLNode;
import ganymedes01.ganysnether.core.utils.xml.XMLParser;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Gany's Nether
 *
 * @author ganymedes01
 *
 */

public class CentrifugeRecipe {

	private final Object input1;
	private final Object input2;
	private final ItemStack[] outputs;
	private final int lavaAmount;

	public CentrifugeRecipe(XMLNode node) {
		input1 = XMLParser.parseNode(node.getNode("input1"));
		input2 = XMLParser.parseNode(node.getNode("input2"));

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
		return areStacksTheSame(input1, target) || areStacksTheSame(input2, target);
	}

	public boolean matches(ItemStack input1, ItemStack input2) {
		if (areStacksTheSame(this.input1, input1) && areStacksTheSame(this.input2, input2))
			return true;
		if (areStacksTheSame(this.input1, input2) && areStacksTheSame(this.input2, input1))
			return true;
		return false;
	}

	private boolean areStacksTheSame(Object obj, ItemStack target) {
		if (obj instanceof ItemStack)
			return InventoryUtils.areStacksTheSame((ItemStack) obj, target, false);
		else if (obj instanceof String) {
			List<ItemStack> list = OreDictionary.getOres((String) obj);
			for (ItemStack stack : list)
				if (InventoryUtils.areStacksTheSame(stack, target, false))
					return true;
		}

		return false;
	}

	public Object getInput1() {
		return input1;
	}

	public Object getInput2() {
		return input2;
	}

	public ItemStack[] getResult() {
		return outputs;
	}

	public int getLavaAmount() {
		return lavaAmount;
	}

	private Object getValidObject(Object obj) {
		if (obj instanceof Item)
			return new ItemStack((Item) obj);
		else if (obj instanceof Block)
			return new ItemStack((Block) obj);
		else if (obj instanceof ItemStack || obj instanceof String)
			return obj;

		return null;
	}
}