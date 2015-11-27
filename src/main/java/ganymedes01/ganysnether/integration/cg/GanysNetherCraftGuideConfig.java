package ganymedes01.ganysnether.integration.cg;

import java.util.Map.Entry;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.ModBlocks;
import ganymedes01.ganysnether.core.utils.UnsizedStack;
import ganymedes01.ganysnether.core.utils.xml.OreStack;
import ganymedes01.ganysnether.recipes.CentrifugeRecipe;
import ganymedes01.ganysnether.recipes.MagmaticCentrifugeRecipes;
import ganymedes01.ganysnether.recipes.RecipeInput;
import ganymedes01.ganysnether.recipes.ReproducerRecipes;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import uristqwerty.CraftGuide.api.CraftGuideAPIObject;
import uristqwerty.CraftGuide.api.ExtraSlot;
import uristqwerty.CraftGuide.api.ItemSlot;
import uristqwerty.CraftGuide.api.RecipeGenerator;
import uristqwerty.CraftGuide.api.RecipeProvider;
import uristqwerty.CraftGuide.api.RecipeTemplate;
import uristqwerty.CraftGuide.api.Slot;
import uristqwerty.CraftGuide.api.SlotType;

/**
 * Gany's Nether
 *
 * @author ganymedes01
 *
 */

public class GanysNetherCraftGuideConfig extends CraftGuideAPIObject implements RecipeProvider {

	@Override
	public void generateRecipes(RecipeGenerator generator) {
		if (GanysNether.enableMagmaticCentrifuge)
			addMagmaticCentrifugeRecipes(generator);
		if (GanysNether.enableReproducerAndDrops)
			addReproducerRecipes(generator);
	}

	private void addMagmaticCentrifugeRecipes(RecipeGenerator generator) {
		Slot[] recipeSlots = new Slot[7];
		recipeSlots[0] = new ItemSlot(3, 23, 16, 16).setSlotType(SlotType.INPUT_SLOT).drawOwnBackground();
		recipeSlots[1] = new ItemSlot(59, 23, 16, 16).setSlotType(SlotType.INPUT_SLOT).drawOwnBackground();

		recipeSlots[2] = new ItemSlot(22, 15, 16, 16, true).setSlotType(SlotType.OUTPUT_SLOT).drawOwnBackground();
		recipeSlots[3] = new ItemSlot(22 + 18, 15, 16, 16, true).setSlotType(SlotType.OUTPUT_SLOT).drawOwnBackground();
		recipeSlots[4] = new ItemSlot(22, 15 + 18, 16, 16, true).setSlotType(SlotType.OUTPUT_SLOT).drawOwnBackground();
		recipeSlots[5] = new ItemSlot(22 + 18, 15 + 18, 16, 16, true).setSlotType(SlotType.OUTPUT_SLOT).drawOwnBackground();

		recipeSlots[6] = new ExtraSlot(60, 2, 16, 16, new ItemStack(ModBlocks.magmaticCentrifuge)).clickable().showName().setSlotType(SlotType.MACHINE_SLOT);

		RecipeTemplate template = generator.createRecipeTemplate(recipeSlots, new ItemStack(ModBlocks.magmaticCentrifuge));
		template.setSize(79, 63);

		for (CentrifugeRecipe recipe : MagmaticCentrifugeRecipes.INSTANCE.getRecipes()) {
			ItemStack[] contents = new ItemStack[7];
			contents[0] = getStack(recipe.getInput1());
			contents[1] = getStack(recipe.getInput2());
			for (int i = 0; i < recipe.getResult().length; i++)
				contents[2 + i] = recipe.getResult()[i].copy();
			contents[6] = new ItemStack(ModBlocks.magmaticCentrifuge);

			generator.addRecipe(template, contents);
		}
	}

	private ItemStack getStack(RecipeInput<?> input) {
		Object obj = input.getObject();

		if (obj instanceof ItemStack)
			return (ItemStack) obj;
		else if (obj instanceof OreStack)
			return OreDictionary.getOres(((OreStack) obj).ore).get(0);
		else
			return null;
	}

	private void addReproducerRecipes(RecipeGenerator generator) {
		Slot[] recipeSlots = new Slot[3];
		recipeSlots[0] = new ItemSlot(3, 23, 16, 16).setSlotType(SlotType.INPUT_SLOT).drawOwnBackground();
		recipeSlots[1] = new ItemSlot(3 + 18, 23 - 18, 16, 16).setSlotType(SlotType.OUTPUT_SLOT).drawOwnBackground();
		recipeSlots[2] = new ExtraSlot(42, 2, 16, 16, new ItemStack(ModBlocks.reproducer)).clickable().showName().setSlotType(SlotType.MACHINE_SLOT);

		RecipeTemplate template = generator.createRecipeTemplate(recipeSlots, new ItemStack(ModBlocks.reproducer));
		template.setSize(61, 45);

		for (Entry<UnsizedStack, ItemStack> tuple : ReproducerRecipes.getTuples().entrySet()) {
			ItemStack[] contents = new ItemStack[3];
			contents[0] = tuple.getKey().getStack().copy();
			contents[1] = tuple.getValue().copy();
			contents[2] = new ItemStack(ModBlocks.reproducer);

			generator.addRecipe(template, contents);
		}
	}
}