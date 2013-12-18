package ganymedes01.ganysnether.integration;

import ganymedes01.ganysnether.blocks.ModBlocks;
import ganymedes01.ganysnether.core.utils.ConcealmentHandler;
import ganymedes01.ganysnether.items.ModItems;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import cpw.mods.fml.common.event.FMLInterModComms;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class ForestryManager extends Integration {

	@Override
	public void init() {
		StringBuffer message = new StringBuffer();
		message.append(ModBlocks.soulGlass.blockID + ":*;");
		message.append(ModBlocks.colouredChiselledQuartzBlock.blockID + ":*;");
		message.append(ModBlocks.colouredQuartzBlock.blockID + ":*;");
		for (Block pillar : ModBlocks.colouredQuartzPillar)
			message.append(pillar.blockID + ":*;");
		for (Block stairs : ModBlocks.colouredQuartzBlockStairs)
			message.append(stairs.blockID + ";");
		message.append(ModBlocks.glowBox.blockID + ":*;");
		message.append(ModBlocks.soulGlassStairs.blockID + ";");
		FMLInterModComms.sendMessage(getModID(), "add-backpack-items", "builder@" + message.toString());

		message = new StringBuffer();
		message.append(ModItems.ghostSeeds.itemID + ";");
		message.append(ModItems.quarzBerrySeeds.itemID + ";");
		message.append(ModItems.witherShrubSeeds.itemID + ";");
		message.append(ModItems.hellBushSeeds.itemID + ";");
		message.append(ModItems.spectreWheat.itemID + ";");
		message.append(ModItems.quarzBerry.itemID + ";");
		message.append(ModItems.lavaBerry.itemID + ";");
		message.append(ModBlocks.blazingCactoid.blockID + ";");
		FMLInterModComms.sendMessage(getModID(), "add-backpack-items", "forester@" + message);

		message = new StringBuffer();
		message.append(ModItems.silverfishScale.itemID + ";");
		message.append(ModItems.batWing.itemID + ";");
		message.append(ModItems.wolfTeeth.itemID + ";");
		for (ItemStack egg : ConcealmentHandler.getEggs())
			message.append(egg.itemID + ":" + egg.getItemDamage() + ";");
		FMLInterModComms.sendMessage(getModID(), "add-backpack-items", "hunter@" + message);
	}

	@Override
	public void postInit() {
		addSqueezerRecipe(new ItemStack(ModItems.ghostSeeds), 20);
		addSqueezerRecipe(new ItemStack(ModItems.quarzBerrySeeds), 20);
		addSqueezerRecipe("lava", new ItemStack(ModItems.hellBushSeeds), 15);
		addSqueezerRecipe("lava", new ItemStack(ModItems.lavaBerry), 250);
		addSqueezerRecipe(new ItemStack(ModItems.witherShrubSeeds), 2000);
	}

	@Override
	public String getModID() {
		return "Forestry";
	}

	private void addSqueezerRecipe(ItemStack seeds, int amount) {
		addSqueezerRecipe("seedoil", seeds, amount);
	}

	private void addSqueezerRecipe(String fluid, ItemStack seeds, int amount) {
		try {
			Class<?> recipeManagers = Class.forName("forestry.api.recipes.RecipeManagers");
			Field field = recipeManagers.getField("squeezerManager");
			Object ret = field.get(null);
			Method addRecipe = ret.getClass().getMethod("addRecipe", int.class, ItemStack[].class, FluidStack.class);
			addRecipe.invoke(ret, 10, new ItemStack[] { seeds }, new FluidStack(FluidRegistry.getFluid(fluid), amount));

		} catch (Exception e) {
		}
	}
}