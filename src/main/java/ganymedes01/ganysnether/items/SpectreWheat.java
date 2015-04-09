package ganymedes01.ganysnether.items;

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

public class SpectreWheat extends Item {

	public SpectreWheat() {
		setUnlocalizedName(Utils.getUnlocalisedName(Strings.Items.SPECTRE_WHEAT_NAME));
		setCreativeTab(GanysNether.shouldGenerateCrops ? GanysNether.netherTab : null);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister reg) {
		if (!GanysNether.enableDynamicTextures)
			itemIcon = reg.registerIcon(Utils.getItemTexture(Strings.Items.SPECTRE_WHEAT_NAME));
	}

	public static void setIcon(IIcon icon) {
		((SpectreWheat) ModItems.spectreWheat).itemIcon = icon;
	}
}