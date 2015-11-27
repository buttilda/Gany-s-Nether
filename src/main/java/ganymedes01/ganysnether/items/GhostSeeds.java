package ganymedes01.ganysnether.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.ModBlocks;
import ganymedes01.ganysnether.ModItems;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.Strings;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

/**
 * Gany's Nether
 *
 * @author ganymedes01
 *
 */

public class GhostSeeds extends NetherSeeds {

	public GhostSeeds() {
		super(ModBlocks.spectreWheat);
		setUnlocalizedName(Utils.getUnlocalisedName(Strings.Items.GHOST_SEEDS_NAME));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister reg) {
		if (!GanysNether.enableDynamicTextures)
			itemIcon = reg.registerIcon(Utils.getItemTexture(Strings.Items.GHOST_SEEDS_NAME));
	}

	@Override
	public boolean isEnabled() {
		return GanysNether.shouldGenerateCrops && GanysNether.shouldGenerateSpectreWheat;
	}

	public static void setIcon(IIcon icon) {
		((GhostSeeds) ModItems.ghostSeeds).itemIcon = icon;
	}
}