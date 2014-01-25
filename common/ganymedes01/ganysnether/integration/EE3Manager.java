package ganymedes01.ganysnether.integration;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.blocks.ModBlocks;
import ganymedes01.ganysnether.items.ModItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.common.event.FMLInterModComms;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class EE3Manager extends Integration {

	@Override
	public void init() {
		addEMCValueToBlock(ModBlocks.soulGlass, 49);
		addEMCValueToBlock(Block.fire, 160);
		if (GanysNether.enableUndertaker)
			addEMCValueToBlock(ModBlocks.undertaker, 599);
		addEMCValueToBlock(ModBlocks.blazingCactoid, 1200);
		addEMCValueToItem(ModItems.spectreWheat, 48);
		addEMCValueToItem(ModItems.spookyFlour, 48.5F);
		addEMCValueToItem(ModItems.dimensionalBread, 48.5F);
		addEMCValueToItem(ModItems.ghostSeeds, 24);
		addEMCValueToItem(ModItems.quarzBerry, 255.5F);
		addEMCValueToItem(ModItems.quarzBerrySeeds, 127.75F);
		addEMCValueToItem(ModItems.wolfTeeth, 172);
		addEMCValueToItem(ModItems.glowingReed, 300);
		addEMCValue(new ItemStack(ModItems.sceptreCap, 1, 2), 24823);
		addEMCValueToItem(ModItems.lavaBerry, 8);
		addEMCValueToItem(ModItems.hellBushSeeds, 4);
		addEMCValueToItem(ModItems.silverfishScale, 32);
		addEMCValueToItem(ModItems.batWing, 8);
		addEMCValueToItem(ModItems.cookedBatWing, 10);
		addEMCValueToItem(ModItems.witherShrubSeeds, 10000);

		addEMCValue(new ItemStack(ModItems.blazeIngot, 1, 2), 512);
		addEMCValue("dustWheat", 24);
	}

	private void addEMCValueToBlock(Block block, float value) {
		addEMCValue(new ItemStack(block), value);
	}

	private void addEMCValueToItem(Item item, float value) {
		addEMCValue(new ItemStack(item), value);
	}

	private void addEMCValue(Object obj, float value) {
		NBTTagCompound data = new NBTTagCompound();

		data.setFloat("emcValue", value);

		if (obj instanceof ItemStack) {
			NBTTagCompound stackCompound = new NBTTagCompound();
			((ItemStack) obj).writeToNBT(stackCompound);
			data.setCompoundTag("itemStack", stackCompound);
		} else if (obj instanceof String)
			data.setString("oreName", (String) obj);

		FMLInterModComms.sendMessage(getModID(), "emc-assign-value-pre", data);
	}

	private void addPostEMCValue(Object obj, float value) {
		NBTTagCompound data = new NBTTagCompound();

		data.setFloat("emcValue", value);

		if (obj instanceof ItemStack) {
			NBTTagCompound stackCompound = new NBTTagCompound();
			((ItemStack) obj).writeToNBT(stackCompound);
			data.setCompoundTag("itemStack", stackCompound);
		} else if (obj instanceof String)
			data.setString("oreName", (String) obj);

		FMLInterModComms.sendMessage(getModID(), "emc-assign-value-post", data);
	}

	@Override
	public void postInit() {
	}

	@Override
	public String getModID() {
		return "EE3";
	}
}