package ganymedes01.ganysnether.integration;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.blocks.ModBlocks;
import ganymedes01.ganysnether.items.ModItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.pahimar.ee3.addon.AddonHandler;
import com.pahimar.ee3.api.OreStack;
import com.pahimar.ee3.emc.EmcValue;

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
		addEMCValueToStack(new ItemStack(ModItems.sceptreCap, 1, 2), 24823);
		addEMCValueToItem(ModItems.lavaBerry, 8);
		addEMCValueToItem(ModItems.hellBushSeeds, 4);
		addEMCValueToItem(ModItems.silverfishScale, 32);
		addEMCValueToItem(ModItems.batWing, 8);
		addEMCValueToItem(ModItems.cookedBatWing, 10);
		addEMCValueToItem(ModItems.witherShrubSeeds, 10000);

		addEMCValueToStack(new ItemStack(ModItems.blazeIngot, 1, 2), 512);
		addEMCValueToOre("dustWheat", 24);
	}

	private void addEMCValueToBlock(Block block, float value) {
		addEMCValueToStack(new ItemStack(block), value);
	}

	private void addEMCValueToItem(Item item, float value) {
		addEMCValueToStack(new ItemStack(item), value);
	}

	private void addEMCValueToStack(ItemStack stack, float value) {
		AddonHandler.sendPreValueAssignment(stack, new EmcValue(value));
	}

	private void addEMCValueToOre(String ore, float value) {
		AddonHandler.sendPreValueAssignment(new OreStack(ore), new EmcValue(value));
	}

	private void addPostEMCValueToStack(ItemStack stack, float value) {
		AddonHandler.sendPostValueAssignment(stack, new EmcValue(value));
	}

	@Override
	public void postInit() {
	}

	@Override
	public String getModID() {
		return "EE3";
	}
}