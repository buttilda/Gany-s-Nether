package ganymedes01.ganysnether.recipes;

import ganymedes01.ganysnether.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import buildcraft.api.transport.FacadeManager;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class BuildCraftFacadeManager {

	public static void init() {
		FacadeManager.addFacade(new ItemStack(ModBlocks.denseLavaCell));
		for (int i = 0; i < 16; i++) {
			FacadeManager.addFacade(new ItemStack(ModBlocks.colouredQuartzBlock, 1, i));
			FacadeManager.addFacade(new ItemStack(ModBlocks.colouredChiselledQuartzBlock, 1, i));
		}

		for (Block pillar : ModBlocks.colouredQuartzPillar)
			for (int i = 0; i < 4; i++)
				FacadeManager.addFacade(new ItemStack(pillar, 1, i));
	}
}
