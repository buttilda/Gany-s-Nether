package ganymedes01.ganysnether.core.utils.xml;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

/**
 * Gany's Nether
 *
 * @author ganymedes01
 *
 */

public class XMLHelper {

	public static BufferedWriter getWriter(File file, final String group, String comment) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(file)) {

			@Override
			public void close() throws IOException {
				write("</" + group + ">");
				super.close();
			}
		};
		bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		bw.newLine();
		bw.newLine();
		bw.write("<!--" + comment + "-->");
		bw.newLine();
		bw.newLine();
		bw.write("<" + group + ">");
		bw.newLine();

		return bw;
	}

	public static void writeNode(BufferedWriter bw, String node) throws IOException {
		bw.write("\t" + node.replace("\n", "\n\t"));
		bw.newLine();
		bw.newLine();
	}

	public static String readFile(File file) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		String l, line = "";
		while ((l = br.readLine()) != null)
			line += l;
		br.close();

		line = line.replace("\t", "");

		int begin = line.indexOf("<!--");
		while (begin > 0) {
			int end = line.indexOf("-->") + 3;
			if (end > begin) {
				line = line.replace(line.substring(begin, end), "");
				begin = line.indexOf("<!--");
			} else
				throw new RuntimeException("Comment opened but not closed!");
		}

		return line.substring(line.indexOf('>') + 1);
	}

	public static void getNodes(String str, List<XMLNode> list) {
		while (!str.isEmpty()) {
			String nodeName = str.substring(str.indexOf('<') + 1, str.indexOf('>'));
			XMLNode node = new XMLNode(getName(nodeName));
			stripProperties(nodeName, node.properties);

			String str2 = getNodeText(nodeName, str);
			if (str2.startsWith("<"))
				getNodes(str2, node.nodes);
			else
				node.value = str2;

			list.add(node);
			String s = "</" + node.name + ">";
			str = str.substring(str.indexOf(s) + s.length());
		}
	}

	private static String getNodeText(String name, String str) {
		String b = "<" + name + ">";
		return str.substring(str.indexOf(b) + b.length(), str.indexOf("</" + getName(name) + ">")).trim();
	}

	private static String getName(String str) {
		int space = str.indexOf(' ');
		if (space > 0)
			return str.substring(0, space);
		else
			return str;
	}

	private static List<XMLProperty> stripProperties(String str, List<XMLProperty> list) {
		String[] lines = str.split(" ");
		if (lines.length > 1)
			for (int i = 1; i < lines.length; i++)
				list.add(getProperty(lines[i]));

		return list;
	}

	private static XMLProperty getProperty(String str) {
		String[] data = str.split("=");
		return new XMLProperty(data[0], fixPropValue(data[1]));
	}

	private static String fixPropValue(String value) {
		if (value.startsWith("\""))
			value = value.substring(1);
		if (value.endsWith("\""))
			value = value.substring(0, value.length() - 1);

		return value;
	}

	public static String toNodeValue(Object obj) {
		obj = fixObj(obj);

		if (obj instanceof ItemStack) {
			ItemStack stack = (ItemStack) obj;
			return Item.itemRegistry.getNameForObject(stack.getItem()) + " " + stack.stackSize + " " + stack.getItemDamage() + (stack.hasTagCompound() ? " " + stack.getTagCompound() : "");
		} else if (obj instanceof String)
			return "\"" + obj + "\"";
		else if (obj instanceof FluidStack) {
			FluidStack stack = (FluidStack) obj;
			return FluidRegistry.getFluidName(stack) + " " + stack.amount + (stack.tag != null ? " " + stack.tag : "");
		} else if (obj instanceof OreStack) {
			OreStack stack = (OreStack) obj;
			if (stack.size > 1)
				return "\"" + stack.ore + "\" " + stack.size;
			else
				return "\"" + stack.ore + "\"";
		}

		return obj.toString();
	}

	private static Object fixObj(Object obj) {
		if (obj instanceof Block)
			return new ItemStack((Block) obj);
		else if (obj instanceof Item)
			return new ItemStack((Item) obj);

		return obj;
	}
}