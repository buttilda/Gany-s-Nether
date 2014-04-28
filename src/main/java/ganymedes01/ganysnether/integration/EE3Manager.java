package ganymedes01.ganysnether.integration;

import ganymedes01.ganysnether.blocks.ModBlocks;
import ganymedes01.ganysnether.items.ModItems;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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
		addEMCValueToBlock(Blocks.fire, 160);
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
		addEMCValueToItem(ModItems.netherCore, 19429);

		addEMCValue(new ItemStack(ModItems.blazeIngot, 1, 2), 512);
		addEMCValue("dustWheat", 24);
		addEMCValue("mobEgg", 1024);
		addEMCValue("blockSpawner", 16384);
	}

	private void addEMCValueToBlock(Block block, float value) {
		if (block != null)
			addEMCValue(new ItemStack(block), value);
	}

	private void addEMCValueToItem(Item item, float value) {
		if (item != null)
			addEMCValue(new ItemStack(item), value);
	}

	private void addEMCValue(Object obj, float value) {
		String string = "{\"wrappedStack\":{\"className\":\"%s\",\"stackSize\":1,\"wrappedStack\":{%s}},\"emcValue\":{\"OMNI\":0.0,\"CORPOREAL\":%f,\"KINETIC\":0.0,\"TEMPORAL\":0.0,\"ESSENTIA\":0.0,\"AMORPHOUS\":0.0,\"VOID\":0.0}}";

		String stack = null;
		String className = null;
		if (obj instanceof ItemStack) {
			ItemStack s = (ItemStack) obj;
			stack = "\"stackSize\":" + s.stackSize + ",\"itemID\":" + s.getItem() + ",\"itemDamage\":" + s.getItemDamage();
			className = "ItemStack";
		} else if (obj instanceof String) {
			stack = "\"oreName\":\"" + (String) obj + "\",\"stackSize\":1";
			className = "OreStack";
		}

		if (stack != null && className != null)
			FMLInterModComms.sendMessage(getModID(), "emc-assign-value-pre", String.format(string, className, stack, value));
	}

	@Override
	public void postInit() {
	}

	@Override
	public String getModID() {
		return "EE3";
	}
}