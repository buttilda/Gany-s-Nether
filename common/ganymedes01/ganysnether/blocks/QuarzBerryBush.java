package ganymedes01.ganysnether.blocks;

import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.items.ModItems;
import ganymedes01.ganysnether.lib.ModIDs;
import ganymedes01.ganysnether.lib.Strings;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
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
	private Icon[] iconArray;

	protected QuarzBerryBush() {
		super(ModIDs.QUARZ_BERRY_BUSH_ID);
		float f = 1F / 8F;
		setBlockBounds(3F * f, 0.0F, 3F * f, 1F - 3F * f, 2F * f, 1F - 3F * f);
		setUnlocalizedName(Utils.getUnlocalizedName(Strings.QUARZ_BERRY_BUSH_NAME));
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
		float f = 1F / 8F;
		switch (world.getBlockMetadata(x, y, z)) {
			case 0:
				setBlockBounds(3F * f, 0.0F, 3F * f, 1F - 3F * f, 2F * f, 1F - 3F * f);
				break;
			case 1:
				setBlockBounds(3F * f, 0.0F, 3F * f, 1F - 3F * f, 2F * f, 1F - 3F * f);
				break;
			case 2:
				setBlockBounds(2F * f, 0.0F, 2F * f, 1F - 2F * f, 4F * f, 1F - 2F * f);
				break;
			case 3:
				setBlockBounds(2F * f, 0.0F, 2F * f, 1F - 2F * f, 4F * f, 1F - 2F * f);
				break;
			case 4:
				setBlockBounds(1F * f, 0.0F, 1F * f, 1F - 1F * f, 6F * f, 1F - 1F * f);
				break;
			case 5:
				setBlockBounds(1F * f, 0.0F, 1F * f, 1F - 1F * f, 6F * f, 1F - 1F * f);
				break;
			case 6:
				setBlockBounds(1F * f, 0.0F, 1F * f, 1F - 1F * f, 6F * f, 1F - 1F * f);
				break;
			default:
				setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int getRenderType() {
		return 0;
	}

	@Override
	protected int getSeedItem() {
		return ModItems.quarzBerrySeeds.itemID;
	}

	@Override
	protected int getCropItem() {
		return ModItems.quarzBerry.itemID;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta) {
		if (meta < 7) {
			if (meta == 6)
				meta = 5;
			return iconArray[meta >> 1];
		}
		return iconArray[3];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister reg) {
		iconArray = new Icon[4];
		for (int i = 0; i < iconArray.length; i++)
			iconArray[i] = reg.registerIcon(Utils.getBlockTexture(Strings.QUARZ_BERRY_BUSH_NAME + "_stage", true) + i);
	}
}
