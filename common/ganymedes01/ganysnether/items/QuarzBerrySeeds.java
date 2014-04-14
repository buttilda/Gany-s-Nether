package ganymedes01.ganysnether.items;

import ganymedes01.ganysnether.blocks.ModBlocks;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.ModIDs;
import ganymedes01.ganysnether.lib.Strings;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class QuarzBerrySeeds extends NetherSeeds {

	QuarzBerrySeeds() {
		super(ModIDs.QUARZ_BERRY_SEEDS_ID, ModBlocks.quarzBerryBush.blockID);
		setTextureName(Utils.getItemTexture(Strings.Items.QUARZ_BERRY_SEEDS_NAME));
		setUnlocalizedName(Utils.getUnlocalizedName(Strings.Items.QUARZ_BERRY_SEEDS_NAME));
	}
}
