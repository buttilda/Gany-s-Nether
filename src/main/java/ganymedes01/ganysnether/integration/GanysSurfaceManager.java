package ganymedes01.ganysnether.integration;

import ganymedes01.ganysnether.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.common.event.FMLInterModComms;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class GanysSurfaceManager extends Integration {

	@Override
	public void init() {
		addYieldForOrganicMatter(new ItemStack(ModItems.quarzBerry), 2);
		addYieldForOrganicMatter(new ItemStack(ModItems.spectreWheat), 2);
		addYieldForOrganicMatter(new ItemStack(ModItems.spookyFlour), 1);
		addYieldForOrganicMatter(new ItemStack(ModItems.glowingReed), 2);
		addYieldForOrganicMatter(new ItemStack(ModItems.skeletonSpawner), 2);
		addYieldForOrganicMatter(new ItemStack(ModItems.skeletonSpawner, 1, 1), 2);
		addYieldForOrganicMatter(new ItemStack(ModItems.silverfishScale), 2);
		addYieldForOrganicMatter(new ItemStack(ModItems.batWing), 1);
		addYieldForOrganicMatter(new ItemStack(ModItems.wolfTeeth), 1);
		addYieldForOrganicMatter(new ItemStack(ModItems.livingSoul), 5);
		addYieldForOrganicMatter(new ItemStack(ModItems.flour), 2);
	}

	@Override
	public void postInit() {
	}

	@Override
	public String getModID() {
		return "ganyssurface";
	}

	private void addYieldForOrganicMatter(ItemStack matter, int yield) {
		if (matter != null) {
			NBTTagCompound data = new NBTTagCompound();

			data.setInteger("yield", yield);

			NBTTagCompound nbt = new NBTTagCompound();
			matter.writeToNBT(nbt);
			data.setTag("matter", nbt);

			FMLInterModComms.sendMessage("ganyssurface", "registerOrganicMatter", data);
		}
	}
}