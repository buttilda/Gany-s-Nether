package ganymedes01.ganysnether.items;

import ganymedes01.ganysnether.ModBlocks;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.Strings;

/**
 * Gany's Nether
 *
 * @author ganymedes01
 *
 */

public class HellBushSeeds extends NetherSeeds {

	public HellBushSeeds() {
		super(ModBlocks.hellBush);
		setTextureName(Utils.getItemTexture(Strings.Items.HELL_BUSH_SEEDS_NAME));
		setUnlocalizedName(Utils.getUnlocalizedName(Strings.Items.HELL_BUSH_SEEDS_NAME));
	}
}