package ganymedes01.ganysnether.items;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.blocks.ModBlocks;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.Strings;
import net.minecraft.item.ItemReed;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class GlowingReed extends ItemReed {

	GlowingReed() {
		super(ModBlocks.glowingReed);
		setCreativeTab(GanysNether.netherTab);
		setTextureName(Utils.getItemTexture(Strings.Items.GLOWING_REED_NAME));
		setUnlocalizedName(Utils.getUnlocalizedName(Strings.Items.GLOWING_REED_NAME));
	}
}