package ganymedes01.ganysnether.integration;

import ganymedes01.ganysnether.core.utils.HoeList;

import java.lang.reflect.Method;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class GTechManager extends Integration {

	@Override
	public void init() {
		HoeList.addHoe(getItem("Tool_Hoe_TungstenSteel"));
	}

	@Override
	public void postInit() {
	}

	public ItemStack getItem(String name) {
		try {
			Class<?> items = Class.forName("gregtechmod.api.enums.GT_Items");
			Object[] enums = items.getEnumConstants();
			for (Object e : enums)
				if (name.equals(e.toString())) {
					items = e.getClass();
					Method getItemStack = items.getDeclaredMethod("getItem");
					return new ItemStack((Item) getItemStack.invoke(e));
				}
		} catch (Exception e) {
		}
		return null;
	}

	@Override
	public String getModID() {
		return "gregtech_addon";
	}
}