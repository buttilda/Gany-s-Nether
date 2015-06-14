package ganymedes01.ganysnether.blocks;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.ModItems;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.Strings;
import net.minecraft.client.renderer.texture.IIconRegister;
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

public class HellBush extends QuarzBerryBush {

	public HellBush() {
		setBlockName(Utils.getUnlocalisedName(Strings.Blocks.HELL_BUSH_NAME));
	}

	@Override
	protected Item func_149866_i() {
		return ModItems.hellBushSeeds;
	}

	@Override
	protected Item func_149865_P() {
		return ModItems.lavaBerry;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
		iconArray = new IIcon[4];
		for (int i = 0; i < iconArray.length; i++)
			iconArray[i] = reg.registerIcon(Utils.getBlockTexture(Strings.Blocks.HELL_BUSH_NAME + "_stage_") + i);
	}

	@Override
	public boolean isEnabled() {
		return GanysNether.shouldGenerateCrops && GanysNether.shouldGenerateHellBush;
	}
}