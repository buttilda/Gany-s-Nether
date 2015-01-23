package ganymedes01.ganysnether.blocks;

import ganymedes01.ganysnether.ModItems;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.Strings;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Gany's Nether
 *
 * @author ganymedes01
 *
 */

public class QuarzBerryBush extends NetherCrop {

	@SideOnly(Side.CLIENT)
	protected IIcon[] iconArray;

	public QuarzBerryBush() {
		float f = 1F / 8F;
		setBlockBounds(3F * f, 0.0F, 3F * f, 1F - 3F * f, 2F * f, 1F - 3F * f);
		setBlockName(Utils.getUnlocalizedName(Strings.Blocks.QUARZ_BERRY_BUSH_NAME));
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
		float rate = 1 - (world.getBlockMetadata(x, y, z) + 1) * 2 / 16F;
		setBlockBounds(rate / 2F, 0, rate / 2F, 1 - rate / 2F, 1 - rate, 1 - rate / 2F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderType() {
		return 0;
	}

	@Override
	protected Item func_149866_i() {
		return ModItems.quarzBerrySeeds;
	}

	@Override
	protected Item func_149865_P() {
		return ModItems.quarzBerry;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		if (meta < 7) {
			if (meta == 6)
				meta = 5;
			return iconArray[meta >> 1];
		}
		return iconArray[3];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
		iconArray = new IIcon[4];
		for (int i = 0; i < iconArray.length; i++)
			iconArray[i] = reg.registerIcon(Utils.getBlockTexture(Strings.Blocks.QUARZ_BERRY_BUSH_NAME + "_stage_") + i);
	}
}