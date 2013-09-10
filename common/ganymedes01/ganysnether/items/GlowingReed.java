package ganymedes01.ganysnether.items;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.blocks.ModBlocks;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.Strings;
import net.minecraft.item.ItemReed;

public class GlowingReed extends ItemReed {

	public GlowingReed(int id) {
		super(id, ModBlocks.glowingReed);
		setCreativeTab(GanysNether.netherTab);
		setTextureName(Utils.getItemTexture(Strings.GLOWING_REED_NAME));
		setUnlocalizedName(Utils.getUnlocalizedName(Strings.GLOWING_REED_NAME));
	}
}
