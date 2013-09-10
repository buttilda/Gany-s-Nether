package ganymedes01.ganysnether.items;

import ganymedes01.ganysnether.blocks.ModBlocks;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.Strings;

public class QuarzBerrySeeds extends NetherSeeds {

	public QuarzBerrySeeds(int id) {
		super(id, ModBlocks.quarzBerryBush.blockID);
		setTextureName(Utils.getItemTexture(Strings.QUARZ_BERRY_SEEDS_NAME));
		setUnlocalizedName(Utils.getUnlocalizedName(Strings.QUARZ_BERRY_SEEDS_NAME));
	}
}
