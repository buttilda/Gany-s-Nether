package ganymedes01.ganysnether.items;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.ModBlocks;
import ganymedes01.ganysnether.ModItems;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.Strings;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Gany's Nether
 *
 * @author ganymedes01
 *
 */

public class GhostSeeds extends NetherSeeds {

	public GhostSeeds() {
		super(ModBlocks.spectreWheat);
		setUnlocalizedName(Utils.getUnlocalizedName(Strings.Items.GHOST_SEEDS_NAME));
		if (!GanysNether.enableDynamicTextures)
			setTextureName(Utils.getItemTexture(Strings.Items.GHOST_SEEDS_NAME));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister reg) {
		if (!GanysNether.enableDynamicTextures)
			super.registerIcons(reg);
	}

	public static void setIcon(IIcon icon) {
		((GhostSeeds) ModItems.ghostSeeds).itemIcon = icon;
	}
}