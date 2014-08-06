package ganymedes01.ganysnether.blocks;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.Strings;

import java.util.List;

import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Gany's Nether
 *
 * @author ganymedes01
 *
 */

public class ColouredQuartzPillar extends BlockRotatedPillar {

	@SideOnly(Side.CLIENT)
	private IIcon[] blockSide, blockTop;

	private final int startIndex;

	public ColouredQuartzPillar(int startIndex) {
		super(Material.rock);
		setHardness(0.8F);
		this.startIndex = startIndex;
		setStepSound(soundTypeStone);
		setCreativeTab(GanysNether.netherTab);
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		for (int i = 0; i < 4; i++)
			list.add(new ItemStack(item, 1, i));
	}

	@Override
	@SideOnly(Side.CLIENT)
	protected IIcon getSideIcon(int index) {
		index = Math.max(0, Math.min(index, blockSide.length - 1));
		return blockSide[index];
	}

	@Override
	@SideOnly(Side.CLIENT)
	protected IIcon getTopIcon(int index) {
		index = Math.max(0, Math.min(index, blockTop.length - 1));
		return blockTop[index];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
		blockSide = new IIcon[4];
		blockTop = new IIcon[4];
		for (int i = 0; i < 4; i++) {
			blockSide[i] = reg.registerIcon(Utils.getBlockTexture(Strings.Blocks.COLOURED_QUARTZ_PILLARS_NAME) + "_side_" + (startIndex * 4 + i));
			blockTop[i] = reg.registerIcon(Utils.getBlockTexture(Strings.Blocks.COLOURED_QUARTZ_PILLARS_NAME) + "_top_" + (startIndex * 4 + i));
		}
	}
}