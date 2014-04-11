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

public class HellBushSeeds extends NetherSeeds {

	public HellBushSeeds() {
		super(ModIDs.HELL_BUSH_SEEDS_ID, ModBlocks.hellBush.blockID);
		setTextureName(Utils.getItemTexture(Strings.Items.HELL_BUSH_SEEDS_NAME));
		setUnlocalizedName(Utils.getUnlocalizedName(Strings.Items.HELL_BUSH_SEEDS_NAME));
	}
}