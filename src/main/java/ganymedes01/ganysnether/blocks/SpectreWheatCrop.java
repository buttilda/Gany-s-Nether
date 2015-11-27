package ganymedes01.ganysnether.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.ModItems;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.Strings;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

/**
 * Gany's Nether
 *
 * @author ganymedes01
 *
 */

public class SpectreWheatCrop extends NetherCrop {

	@SideOnly(Side.CLIENT)
	public static IIcon[] icons;

	public SpectreWheatCrop() {
		setBlockName(Utils.getUnlocalisedName(Strings.Blocks.SPECTRE_WHEAT_BLOCK_NAME));
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

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
		if (!GanysNether.enableDynamicTextures) {
			icons = new IIcon[8];
			for (int i = 0; i < icons.length; i++)
				icons[i] = reg.registerIcon(Utils.getBlockTexture(Strings.Blocks.SPECTRE_WHEAT_BLOCK_NAME + "_stage_" + i));
		}
	}

	@Override
	public boolean isEnabled() {
		return GanysNether.shouldGenerateCrops && GanysNether.shouldGenerateSpectreWheat;
	}
}