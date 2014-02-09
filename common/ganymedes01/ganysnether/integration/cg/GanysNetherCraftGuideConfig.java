package ganymedes01.ganysnether.integration.cg;

import ganymedes01.ganysnether.blocks.ModBlocks;
import ganymedes01.ganysnether.recipes.MagmaticCentrifugeRecipes;
import ganymedes01.ganysnether.recipes.ReproducerRecipes;
import ganymedes01.ganysnether.recipes.centrifuge.CentrifugeRecipe;

import java.util.ArrayList;
import java.util.Map.Entry;

import net.minecraft.item.ItemStack;
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
		addMagmaticCentrifugeRecipes(generator);
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

		for (CentrifugeRecipe recipe : MagmaticCentrifugeRecipes.getRecipes()) {
			ItemStack[] contents = new ItemStack[7];
			contents[0] = getStack(recipe.getMaterial(1));
			contents[1] = getStack(recipe.getMaterial(2));
			for (int i = 0; i < recipe.getResult().length; i++)
				contents[2 + i] = recipe.getResult()[i].copy();
			contents[6] = new ItemStack(ModBlocks.magmaticCentrifuge);

			generator.addRecipe(template, contents);
		}
	}

	private ItemStack getStack(Object obj) {
		if (obj instanceof ItemStack)
			return (ItemStack) obj;
		else if (obj instanceof ArrayList)
			return ((ArrayList<ItemStack>) obj).get(0);
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

		for (Entry<ItemStack, ItemStack> tuple : ReproducerRecipes.getTupes().entrySet()) {
			ItemStack[] contents = new ItemStack[3];
			contents[0] = tuple.getKey().copy();
			contents[1] = tuple.getValue().copy();
			contents[2] = new ItemStack(ModBlocks.reproducer);

			generator.addRecipe(template, contents);
		}
	}
}