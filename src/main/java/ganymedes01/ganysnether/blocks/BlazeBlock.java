package ganymedes01.ganysnether.blocks;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.IConfigurable;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.Strings;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * Gany's Nether
 *
 * @author ganymedes01
 *
 */

public class BlazeBlock extends Block implements IConfigurable {

	public BlazeBlock() {
		super(Material.iron);
		setHardness(5.0F);
		setResistance(10.0F);
		setStepSound(soundTypeMetal);
		setBlockName(Utils.getUnlocalisedName(Strings.Blocks.BLAZE_BLOCK_NAME));
		setBlockTextureName(Utils.getBlockTexture(Strings.Blocks.BLAZE_BLOCK_NAME));
		setCreativeTab(GanysNether.enableBlazeBlock ? GanysNether.netherTab : null);
	}

	@Override
	public boolean isEnabled() {
		return GanysNether.enableBlazeBlock;
	}
}