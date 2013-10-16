package ganymedes01.ganysnether.core.handlers;

import ganymedes01.ganysnether.core.utils.MagmaticCentrifugeRecipes;
import ganymedes01.ganysnether.lib.Reference;

import java.util.logging.Level;
import java.util.logging.Logger;

import net.minecraft.item.ItemStack;

import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.primitives.Ints;

import cpw.mods.fml.common.event.FMLInterModComms.IMCEvent;
import cpw.mods.fml.common.event.FMLInterModComms.IMCMessage;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class InterModComms {

	public static void processIMC(IMCEvent event) {
		for (IMCMessage m : event.getMessages())
			if ("addCentrifugeRecipe".equals(m.key))
				addCentrifugeRecipe(m);
	}

	public static void addCentrifugeRecipe(IMCMessage m) {
		ItemStack material1 = null;
		ItemStack material2 = null;

		ItemStack result1 = null;
		ItemStack result2 = null;
		ItemStack result3 = null;
		ItemStack result4 = null;

		try {
			Splitter splitter = Splitter.on("@").trimResults();

			String[] array = Iterables.toArray(splitter.split(m.getStringValue()), String.class);
			if (array.length != 7 && array.length != 10 && array.length != 13 && array.length != 16)
				Logger.getLogger(Reference.MOD_ID).log(Level.INFO, String.format("%s tried to add a recipe of invalid size.", m.getSender()));
			else {
				Integer mat1ID = Ints.tryParse(array[0]);
				Integer mat1META = Ints.tryParse(array[1]);

				Integer mat2ID = Ints.tryParse(array[2]);
				Integer mat2META = Ints.tryParse(array[3]);

				Integer res1ID = Ints.tryParse(array[4]);
				Integer res1SIZE = Ints.tryParse(array[5]);
				Integer res1META = Ints.tryParse(array[6]);

				if (mat1ID == null || mat1META == null || mat2ID == null || mat2META == null || res1ID == null || res1SIZE == null || res1META == null) {
					Logger.getLogger(Reference.MOD_ID).log(Level.INFO, String.format("%s tried to add an invalid recipe.", m.getSender()));
					return;
				}
				material1 = new ItemStack(mat1ID, 1, mat1META);
				material2 = new ItemStack(mat2ID, 1, mat2META);
				result1 = new ItemStack(res1ID, res1SIZE, res1META);

				if (array.length == 7)
					MagmaticCentrifugeRecipes.addRecipe(m.getSender(), material1, material2, result1);
				else if (array.length == 10) {
					Integer res2ID = Ints.tryParse(array[7]);
					Integer res2SIZE = Ints.tryParse(array[8]);
					Integer res2META = Ints.tryParse(array[9]);
					result2 = new ItemStack(res2ID, res2SIZE, res2META);

					if (res2ID == null || res2SIZE == null || res2META == null) {
						Logger.getLogger(Reference.MOD_ID).log(Level.INFO, String.format("%s tried to add an invalid recipe.", m.getSender()));
						return;
					}
					MagmaticCentrifugeRecipes.addRecipe(m.getSender(), material1, material2, result1, result2);
				} else if (array.length == 13) {
					Integer res2ID = Ints.tryParse(array[7]);
					Integer res2SIZE = Ints.tryParse(array[8]);
					Integer res2META = Ints.tryParse(array[9]);
					result2 = new ItemStack(res2ID, res2SIZE, res2META);

					Integer res3ID = Ints.tryParse(array[10]);
					Integer res3SIZE = Ints.tryParse(array[11]);
					Integer res3META = Ints.tryParse(array[12]);
					result3 = new ItemStack(res3ID, res3SIZE, res3META);

					if (res2ID == null || res2SIZE == null || res2META == null || res3ID == null || res3SIZE == null || res3META == null) {
						Logger.getLogger(Reference.MOD_ID).log(Level.INFO, String.format("%s tried to add an invalid recipe.", m.getSender()));
						return;
					}
					MagmaticCentrifugeRecipes.addRecipe(m.getSender(), material1, material2, result1, result2, result3);

				} else if (array.length == 16) {
					Integer res2ID = Ints.tryParse(array[7]);
					Integer res2SIZE = Ints.tryParse(array[8]);
					Integer res2META = Ints.tryParse(array[9]);
					result2 = new ItemStack(res2ID, res2SIZE, res2META);

					Integer res3ID = Ints.tryParse(array[10]);
					Integer res3SIZE = Ints.tryParse(array[11]);
					Integer res3META = Ints.tryParse(array[12]);
					result3 = new ItemStack(res3ID, res3SIZE, res3META);

					Integer res4ID = Ints.tryParse(array[13]);
					Integer res4SIZE = Ints.tryParse(array[14]);
					Integer res4META = Ints.tryParse(array[15]);
					result4 = new ItemStack(res4ID, res4SIZE, res4META);

					if (res2ID == null || res2SIZE == null || res2META == null || res3ID == null || res3SIZE == null || res3META == null || res4ID == null || res4SIZE == null || res4META == null) {
						Logger.getLogger(Reference.MOD_ID).log(Level.INFO, String.format("%s tried to add an invalid recipe.", m.getSender()));
						return;
					}
					MagmaticCentrifugeRecipes.addRecipe(m.getSender(), material1, material2, result1, result2, result3, result4);
				} else {
					Logger.getLogger(Reference.MOD_ID).log(Level.INFO, String.format("%s tried to add an invalid recipe.", m.getSender()));
					return;
				}
			}
		} catch (Exception ex) {

		}
	}
}