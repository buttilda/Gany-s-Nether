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

public class QuarzBerrySeeds extends NetherSeeds {

	QuarzBerrySeeds() {
		super(ModBlocks.quarzBerryBush);
		setTextureName(Utils.getItemTexture(Strings.Items.QUARZ_BERRY_SEEDS_NAME));
		setUnlocalizedName(Utils.getUnlocalizedName(Strings.Items.QUARZ_BERRY_SEEDS_NAME));
	}
}