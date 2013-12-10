package ganymedes01.ganysnether.integration;

import ganymedes01.ganysnether.items.ModItems;
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
		addMagmaCruicibleRecipe(400000, new ItemStack(ModItems.lavaBerry), new FluidStack(FluidRegistry.LAVA, 500));
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

		FMLInterModComms.sendMessage("ThermalExpansion", "CrucibleRecipe", data);
	}
}