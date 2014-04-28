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

public class HellBush extends QuarzBerryBush {

	HellBush() {
		super(ModIDs.HELL_BUSH_ID);
		setUnlocalizedName(Utils.getUnlocalizedName(Strings.Blocks.HELL_BUSH_NAME));
	}

	@Override
	protected int getSeedItem() {
		return ModItems.hellBushSeeds.itemID;
	}

	@Override
	protected int getCropItem() {
		return ModItems.lavaBerry.itemID;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister reg) {
		iconArray = new Icon[4];
		for (int i = 0; i < iconArray.length; i++)
			iconArray[i] = reg.registerIcon(Utils.getBlockTexture(Strings.Blocks.HELL_BUSH_NAME + "_stage_") + i);
	}
}