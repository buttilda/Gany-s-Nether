package ganymedes01.ganysnether.integration;

import ganymedes01.ganysnether.blocks.ModBlocks;
import ganymedes01.ganysnether.recipes.MagmaticCentrifugeRecipes;
import ganymedes01.ganysnether.recipes.MagmaticCentrifugeRecipes.CentrifugeRecipe;
import ganymedes01.ganysnether.recipes.ReproducerRecipes;

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

	public static void init() {
		new GanysNetherCraftGuideConfig();
	}

	@Override
	public void generateRecipes(RecipeGenerator generator) {
		for (CentrifugeRecipe recipe : MagmaticCentrifugeRecipes.getRecipes())
			addMagmaticCentrifugeRecipe(generator, recipe);
		for (Entry<ItemStack, ItemStack> tuple : ReproducerRecipes.getTupes().entrySet())
			addReproducerRecipe(generator, tuple.getKey(), tuple.getValue());

	}

	private void addMagmaticCentrifugeRecipe(RecipeGenerator generator, CentrifugeRecipe recipe) {
		Slot[] recipeSlots = new Slot[7];
		recipeSlots[0] = new ItemSlot(3, 23, 16, 16).setSlotType(SlotType.INPUT_SLOT).drawOwnBackground();
		recipeSlots[1] = new ItemSlot(59, 23, 16, 16).setSlotType(SlotType.INPUT_SLOT).drawOwnBackground();

		recipeSlots[2] = new ItemSlot(22, 15, 16, 16).setSlotType(SlotType.OUTPUT_SLOT).drawOwnBackground();
		recipeSlots[3] = new ItemSlot(22 + 18, 15, 16, 16).setSlotType(SlotType.OUTPUT_SLOT).drawOwnBackground();
		recipeSlots[4] = new ItemSlot(22, 15 + 18, 16, 16).setSlotType(SlotType.OUTPUT_SLOT).drawOwnBackground();
		recipeSlots[5] = new ItemSlot(22 + 18, 15 + 18, 16, 16).setSlotType(SlotType.OUTPUT_SLOT).drawOwnBackground();
		recipeSlots[6] = new ExtraSlot(60, 2, 16, 16, new ItemStack(ModBlocks.magmaticCentrifuge)).clickable().showName().setSlotType(SlotType.MACHINE_SLOT);

		RecipeTemplate template = generator.createRecipeTemplate(recipeSlots, recipe.getResult()[0]);
		template.setSize(79, 63);

		ItemStack[] contents = new ItemStack[7];
		contents[0] = recipe.getMaterial(1);
		contents[1] = recipe.getMaterial(2);
		for (int i = 0; i < recipe.getResult().length; i++)
			contents[2 + i] = recipe.getResult()[i];
		contents[6] = new ItemStack(ModBlocks.magmaticCentrifuge);

		generator.addRecipe(template, contents);
	}

	private void addReproducerRecipe(RecipeGenerator generator, ItemStack egg, ItemStack drop) {
		Slot[] recipeSlots = new Slot[3];
		recipeSlots[0] = new ItemSlot(3, 23, 16, 16).setSlotType(SlotType.INPUT_SLOT).drawOwnBackground();
		recipeSlots[1] = new ItemSlot(3 + 18, 23 - 18, 16, 16).setSlotType(SlotType.OUTPUT_SLOT).drawOwnBackground();
		recipeSlots[2] = new ExtraSlot(42, 2, 16, 16, new ItemStack(ModBlocks.reproducer)).clickable().showName().setSlotType(SlotType.MACHINE_SLOT);

		RecipeTemplate template = generator.createRecipeTemplate(recipeSlots, drop);
		template.setSize(61, 45);

		ItemStack[] contents = new ItemStack[3];
		contents[0] = egg;
		contents[1] = drop;
		contents[2] = new ItemStack(ModBlocks.reproducer);

		generator.addRecipe(template, contents);
	}
}