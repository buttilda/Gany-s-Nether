package ganymedes01.ganysnether.integration;

import ganymedes01.ganysnether.items.ModItems;
import net.minecraft.block.Block;
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

		addInductionSmelterRecipe(4000, new ItemStack(ModItems.spookyFlour), new ItemStack(Block.sand), new ItemStack(Block.slowSand));
		addInductionSmelterRecipe(4000, new ItemStack(ModItems.quarzBerry), new ItemStack(Block.glass), new ItemStack(Item.netherQuartz));
		addInductionSmelterRecipe(8000, new ItemStack(ModItems.silverfishScale), new ItemStack(Block.stoneBrick, 8, 3), new ItemStack(Block.gravel), new ItemStack(Item.emerald), 5);

		addPulveriserRecipe(3000, new ItemStack(ModItems.glowingReed), new ItemStack(Item.glowstone, 3));
		addPulveriserRecipe(6000, new ItemStack(ModItems.spectreWheat), new ItemStack(ModItems.spookyFlour));
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
		data.setCompoundTag("input", inputCompound);

		NBTTagCompound outputCompound = new NBTTagCompound();
		output.writeToNBT(outputCompound);
		data.setCompoundTag("output", outputCompound);

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
		data.setCompoundTag("primaryInput", input1Compound);

		NBTTagCompound input2Compound = new NBTTagCompound();
		input2.writeToNBT(input2Compound);
		data.setCompoundTag("secondaryInput", input2Compound);

		NBTTagCompound output1Compound = new NBTTagCompound();
		output1.writeToNBT(output1Compound);
		data.setCompoundTag("primaryOutput", output1Compound);

		if (output2 != null) {
			NBTTagCompound output2Compound = new NBTTagCompound();
			output2.writeToNBT(output2Compound);
			data.setCompoundTag("secondaryOutput", output2Compound);

			data.setInteger("secondaryChance", chance);
		}

		FMLInterModComms.sendMessage(getModID(), "SmelterRecipe", data);
	}

	private void addPulveriserRecipe(int energy, ItemStack input, ItemStack output) {
		NBTTagCompound data = new NBTTagCompound();

		data.setInteger("energy", energy);

		NBTTagCompound inputCompound = new NBTTagCompound();
		input.writeToNBT(inputCompound);
		data.setCompoundTag("input", inputCompound);

		NBTTagCompound outputCompound = new NBTTagCompound();
		output.writeToNBT(outputCompound);
		data.setCompoundTag("primaryOutput", outputCompound);

		FMLInterModComms.sendMessage(getModID(), "PulverizerRecipe", data);
	}
}