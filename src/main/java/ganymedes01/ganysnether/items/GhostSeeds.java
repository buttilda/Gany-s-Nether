package ganymedes01.ganysnether.items;

import ganymedes01.ganysnether.blocks.ModBlocks;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.Strings;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class GhostSeeds extends NetherSeeds {

	GhostSeeds() {
		super(ModBlocks.spectreWheat);
		setTextureName(Utils.getItemTexture(Strings.Items.GHOST_SEEDS_NAME));
		setUnlocalizedName(Utils.getUnlocalizedName(Strings.Items.GHOST_SEEDS_NAME));
	}
}