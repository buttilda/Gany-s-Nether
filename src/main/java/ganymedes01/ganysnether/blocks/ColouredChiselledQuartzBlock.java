package ganymedes01.ganysnether.blocks;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.IConfigurable;
import ganymedes01.ganysnether.ModBlocks.ISubBlocksBlock;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.items.blocks.ItemColouredChiselledQuartzBlock;
import ganymedes01.ganysnether.lib.Strings;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

/**
 * Gany's Nether
 *
 * @author ganymedes01
 *
 */

public class ColouredChiselledQuartzBlock extends Block implements ISubBlocksBlock, IConfigurable {

	@SideOnly(Side.CLIENT)
	private IIcon[] blockTop, blockSide;

	public ColouredChiselledQuartzBlock() {
		super(Material.rock);
		setHardness(0.8F);
		setStepSound(soundTypeStone);
		setCreativeTab(GanysNether.enableQuartz ? GanysNether.netherTab : null);
		setBlockName(Utils.getUnlocalisedName(Strings.Blocks.COLOURED_CHISELLED_QUARTZ_BLOCK_NAME));
	}

	@Override
	public int damageDropped(int meta) {
		return meta;
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		for (int i = 0; i < 16; i++)
			list.add(new ItemStack(item, 1, i));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		meta = Math.max(0, Math.min(meta, blockSide.length - 1));
		if (side == 0 || side == 1)
			return blockTop[meta];
		return blockSide[meta];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
		blockTop = new IIcon[16];
		blockSide = new IIcon[16];
		for (int i = 0; i < blockSide.length; i++) {
			blockTop[i] = reg.registerIcon(Utils.getBlockTexture(Strings.Blocks.COLOURED_CHISELLED_QUARTZ_BLOCK_NAME) + "_top_" + i);
			blockSide[i] = reg.registerIcon(Utils.getBlockTexture(Strings.Blocks.COLOURED_CHISELLED_QUARTZ_BLOCK_NAME) + "_" + i);
		}
	}

	@Override
	public Class<? extends ItemBlock> getItemBlockClass() {
		return ItemColouredChiselledQuartzBlock.class;
	}

	@Override
	public boolean isEnabled() {
		return GanysNether.enableQuartz;
	}
}