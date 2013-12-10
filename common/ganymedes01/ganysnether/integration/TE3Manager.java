package ganymedes01.ganysnether.integration;

import ganymedes01.ganysnether.items.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
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
		NBTTagList tagList = new NBTTagList();
		NBTTagCompound tagCompound;

		data.setInteger("energy", energy);

		tagCompound = new NBTTagCompound();
		tagCompound.setByte("input", (byte) 0);
		input.writeToNBT(tagCompound);
		tagList.appendTag(tagCompound);

		tagCompound = new NBTTagCompound();
		tagCompound.setByte("output", (byte) 0);
		output.writeToNBT(tagCompound);
		tagList.appendTag(tagCompound);

		FMLInterModComms.sendMessage("ThermalExpansion", "CrucibleRecipe", data);
	}
}