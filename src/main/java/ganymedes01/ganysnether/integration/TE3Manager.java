package ganymedes01.ganysnether.integration;

import ganymedes01.ganysnether.ModItems;
import ganymedes01.ganysnether.recipes.MagmaticCentrifugeRecipes;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import cpw.mods.fml.common.event.FMLInterModComms;

/**
 * Gany's Nether
 *
 * @author ganymedes01
 *
 */

public class TE3Manager extends Integration {

	@Override
	public void init() {
		addMagmaCruicibleRecipe(30000, new ItemStack(ModItems.lavaBerry), new FluidStack(FluidRegistry.LAVA, 250));

		addInductionSmelterRecipe(4000, new ItemStack(ModItems.spookyFlour), new ItemStack(Blocks.sand), new ItemStack(Blocks.soul_sand));
		addInductionSmelterRecipe(4000, new ItemStack(ModItems.quarzBerry), new ItemStack(Blocks.glass), new ItemStack(Items.quartz));
		addInductionSmelterRecipe(8000, new ItemStack(ModItems.silverfishScale), new ItemStack(Blocks.stonebrick, 8, 3), new ItemStack(Blocks.gravel), new ItemStack(Items.emerald), 5);

		addPulveriserRecipe(3000, new ItemStack(ModItems.glowingReed), new ItemStack(Items.glowstone_dust, 3));
		addPulveriserRecipe(6000, new ItemStack(ModItems.spectreWheat), new ItemStack(ModItems.spookyFlour));
		addPulveriserRecipe(1500, new ItemStack(Items.wheat), new ItemStack(ModItems.flour));

		try {
			ItemStack ingotInvar = new ItemStack((Item) Class.forName("thermalexpansion.item.TEItems").getDeclaredField("itemMaterial").get(null), 2, 71);
			MagmaticCentrifugeRecipes.addRecipe("ingotIron", "ingotNickel", ingotInvar);
		} catch (Exception e) {
		}
	}

	@Override
	public void postInit() {
	}

	@Override
	public String getModID() {
		return "ThermalExpansion";
	}

	private void addMagmaCruicibleRecipe(int energy, ItemStack input, FluidStack output) {
		NBTTagCompound data = new NBTTagCompound();

		data.setInteger("energy", energy);

		NBTTagCompound inputCompound = new NBTTagCompound();
		input.writeToNBT(inputCompound);
		data.setTag("input", inputCompound);

		NBTTagCompound outputCompound = new NBTTagCompound();
		output.writeToNBT(outputCompound);
		data.setTag("output", outputCompound);

		FMLInterModComms.sendMessage(getModID(), "CrucibleRecipe", data);
	}

	private void addInductionSmelterRecipe(int energy, ItemStack input1, ItemStack input2, ItemStack output) {
		addInductionSmelterRecipe(energy, input1, input2, output, null, 0);
	}

	private void addInductionSmelterRecipe(int energy, ItemStack input1, ItemStack input2, ItemStack output1, ItemStack output2, int chance) {
		NBTTagCompound data = new NBTTagCompound();

		data.setInteger("energy", energy);

		NBTTagCompound input1Compound = new NBTTagCompound();
		input1.writeToNBT(input1Compound);
		data.setTag("primaryInput", input1Compound);

		NBTTagCompound input2Compound = new NBTTagCompound();
		input2.writeToNBT(input2Compound);
		data.setTag("secondaryInput", input2Compound);

		NBTTagCompound output1Compound = new NBTTagCompound();
		output1.writeToNBT(output1Compound);
		data.setTag("primaryOutput", output1Compound);

		if (output2 != null) {
			NBTTagCompound output2Compound = new NBTTagCompound();
			output2.writeToNBT(output2Compound);
			data.setTag("secondaryOutput", output2Compound);

			data.setInteger("secondaryChance", chance);
		}

		FMLInterModComms.sendMessage(getModID(), "SmelterRecipe", data);
	}

	private void addPulveriserRecipe(int energy, ItemStack input, ItemStack output) {
		NBTTagCompound data = new NBTTagCompound();

		data.setInteger("energy", energy);

		NBTTagCompound inputCompound = new NBTTagCompound();
		input.writeToNBT(inputCompound);
		data.setTag("input", inputCompound);

		NBTTagCompound outputCompound = new NBTTagCompound();
		output.writeToNBT(outputCompound);
		data.setTag("primaryOutput", outputCompound);

		FMLInterModComms.sendMessage(getModID(), "PulverizerRecipe", data);
	}
}