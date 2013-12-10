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

public class BuildCraftManager extends Integration {

	@Override
	public void init() {
		addFacade(ModBlocks.denseLavaCell.blockID);
		for (int i = 0; i < 16; i++) {
			addFacade(ModBlocks.colouredQuartzBlock.blockID, i);
			addFacade(ModBlocks.colouredChiselledQuartzBlock.blockID, i);
		}

		for (Block pillar : ModBlocks.colouredQuartzPillar)
			for (int i = 0; i < 4; i++)
				addFacade(pillar.blockID, i);
	}

	@Override
	public void postInit() {

	}

	@Override
	public String getModID() {
		return "BuildCraft|Transport";
	}

	private void addFacade(int blockID) {
		addFacade(blockID, 0);
	}

	private void addFacade(int blockID, int meta) {
		FMLInterModComms.sendMessage("BuildCraft|Transport", "add-facade", blockID + "@" + meta);
	}
}