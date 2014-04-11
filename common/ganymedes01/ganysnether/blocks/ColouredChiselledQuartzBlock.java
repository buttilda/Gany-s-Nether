package ganymedes01.ganysnether.blocks;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.ModIDs;
import ganymedes01.ganysnether.lib.Strings;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class ColouredChiselledQuartzBlock extends Block {

	@SideOnly(Side.CLIENT)
	private Icon[] blockTop, blockSide;

	public ColouredChiselledQuartzBlock() {
		super(ModIDs.COLOURED_CHISELLED_QUARTZ_BLOCK_ID, Material.rock);
		setHardness(0.8F);
		setStepSound(soundStoneFootstep);
		setCreativeTab(GanysNether.netherTab);
		setUnlocalizedName(Utils.getUnlocalizedName(Strings.Blocks.COLOURED_CHISELLED_QUARTZ_BLOCK_NAME));
	}

	@Override
	public int damageDropped(int meta) {
		return meta;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int id, CreativeTabs tab, List list) {
		for (int i = 0; i < 16; i++)
			list.add(new ItemStack(id, 1, i));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta) {
		if (side == 0 || side == 1)
			return blockTop[meta];
		return blockSide[meta];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister reg) {
		blockTop = new Icon[16];
		blockSide = new Icon[16];
		for (int i = 0; i < blockSide.length; i++) {
			blockTop[i] = reg.registerIcon(Utils.getBlockTexture(Strings.Blocks.COLOURED_CHISELLED_QUARTZ_BLOCK_NAME) + "_top_" + i);
			blockSide[i] = reg.registerIcon(Utils.getBlockTexture(Strings.Blocks.COLOURED_CHISELLED_QUARTZ_BLOCK_NAME) + "_" + i);
		}
	}
}