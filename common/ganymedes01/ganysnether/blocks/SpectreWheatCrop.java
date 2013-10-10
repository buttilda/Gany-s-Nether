package ganymedes01.ganysnether.blocks;

import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.items.ModItems;
import ganymedes01.ganysnether.lib.ModIDs;
import ganymedes01.ganysnether.lib.Strings;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
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
	private Icon[] iconArray;

	public SpectreWheatCrop() {
		super(ModIDs.SPECTRE_WHEAT_CROP_ID);
		setUnlocalizedName(Utils.getUnlocalizedName(Strings.SPECTRE_WHEAT_BLOCK_NAME));
	}

	@Override
	protected int getSeedItem() {
		return ModItems.ghostSeeds.itemID;
	}

	@Override
	protected int getCropItem() {
		return ModItems.spectreWheat.itemID;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta) {
		if (meta < 0 || meta > 7)
			return iconArray[7];
		return iconArray[meta];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister reg) {
		iconArray = new Icon[8];
		for (int i = 0; i < iconArray.length; i++)
			iconArray[i] = reg.registerIcon(Utils.getBlockTexture(Strings.SPECTRE_WHEAT_BLOCK_NAME + "_stage", true) + i);
	}
}