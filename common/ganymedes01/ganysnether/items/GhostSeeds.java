package ganymedes01.ganysnether.items;

import ganymedes01.ganysnether.blocks.ModBlocks;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.Strings;

public class GhostSeeds extends NetherSeeds {

	public GhostSeeds(int id) {
		super(id, ModBlocks.spectreWheat.blockID);
		setTextureName(Utils.getItemTexture(Strings.GHOST_SEEDS_NAME));
		setUnlocalizedName(Utils.getUnlocalizedName(Strings.GHOST_SEEDS_NAME));
	}
}
