package ganymedes01.ganysnether.items;

import ganymedes01.ganysnether.blocks.ModBlocks;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.ModIDs;
import ganymedes01.ganysnether.lib.Strings;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class WitherShrubSeeds extends NetherSeeds {

	public WitherShrubSeeds() {
		super(ModIDs.WITHER_SHRUB_SEEDS_ID, ModBlocks.witherShrub.blockID);
		setTextureName(Utils.getItemTexture(Strings.WITHER_SHRUB_SEEDS_NAME));
		setUnlocalizedName(Utils.getUnlocalizedName(Strings.WITHER_SHRUB_SEEDS_NAME));
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
}
