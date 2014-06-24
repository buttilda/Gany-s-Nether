package ganymedes01.ganysnether.blocks;

import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.items.ModItems;
import ganymedes01.ganysnether.lib.Strings;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Gany's Nether
 *
 * @author ganymedes01
 *
 */

public class SpectreWheatCrop extends NetherCrop {

	@SideOnly(Side.CLIENT)
	public static IIcon[] icons;

	SpectreWheatCrop() {
		setBlockName(Utils.getUnlocalizedName(Strings.Blocks.SPECTRE_WHEAT_BLOCK_NAME));
	}

	@Override
	protected Item func_149866_i() {
		return ModItems.ghostSeeds;
	}

	@Override
	protected Item func_149865_P() {
		return ModItems.spectreWheat;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderBlockPass() {
		return 1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		if (meta < 0 || meta > 7)
			return icons[7];
		return icons[meta];
	}
}