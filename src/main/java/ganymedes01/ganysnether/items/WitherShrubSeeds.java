package ganymedes01.ganysnether.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.ModBlocks;
import ganymedes01.ganysnether.ModItems;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.Strings;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

/**
 * Gany's Nether
 *
 * @author ganymedes01
 *
 */

public class WitherShrubSeeds extends NetherSeeds {

	public WitherShrubSeeds() {
		super(ModBlocks.witherShrub);
		setUnlocalizedName(Utils.getUnlocalisedName(Strings.Items.WITHER_SHRUB_SEEDS_NAME));
	}

	public static void setIcon(IIcon icon) {
		((WitherShrubSeeds) ModItems.witherShrubSeeds).itemIcon = icon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister reg) {
		if (!GanysNether.enableDynamicTextures)
			itemIcon = reg.registerIcon(Utils.getItemTexture(Strings.Items.WITHER_SHRUB_SEEDS_NAME));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.epic;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return GanysNether.shouldGenerateCrops && GanysNether.shouldGenerateWitherShrub;
	}
}