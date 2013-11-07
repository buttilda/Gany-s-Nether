package ganymedes01.ganysnether.integration;

import ganymedes01.ganysnether.blocks.ModBlocks;
import net.minecraft.block.Block;
import cpw.mods.fml.common.event.FMLInterModComms;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class BuildCraftFacadeManager {

	public static void registerFacades() {
		addFacade(ModBlocks.denseLavaCell.blockID);
		for (int i = 0; i < 16; i++) {
			addFacade(ModBlocks.colouredQuartzBlock.blockID, i);
			addFacade(ModBlocks.colouredChiselledQuartzBlock.blockID, i);
		}

		for (Block pillar : ModBlocks.colouredQuartzPillar)
			for (int i = 0; i < 4; i++)
				addFacade(pillar.blockID, i);
	}

	private static void addFacade(int blockID) {
		addFacade(blockID, 0);
	}

	private static void addFacade(int blockID, int meta) {
		FMLInterModComms.sendMessage("BuildCraft|Transport", "add-facade", blockID + "@" + meta);
	}
}