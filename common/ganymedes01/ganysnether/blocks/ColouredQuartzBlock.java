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
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */
public class ColouredQuartzBlock extends Block {

	private static ItemStack[] blocks;

	@SideOnly(Side.CLIENT)
	private Icon[] blockIcon;

	public ColouredQuartzBlock() {
		super(ModIDs.COLOURED_QUARTZ_BLOCK_ID, Material.rock);
		setHardness(0.8F);
		setStepSound(soundStoneFootstep);
		setCreativeTab(GanysNether.netherTab);
		setUnlocalizedName(Utils.getUnlocalizedName(Strings.COLOURED_QUARTZ_BLOCK_NAME));
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
		return blockIcon[meta];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister reg) {
		blockIcon = new Icon[16];
		for (int i = 0; i < blockIcon.length; i++)
			blockIcon[i] = reg.registerIcon(Utils.getBlockTexture(Strings.COLOURED_QUARTZ_BLOCK_NAME) + "_" + i);
	}

	public static ItemStack[] getQuartzBlocksForRecipe() {
		if (blocks == null)
			blocks = new ItemStack[] { new ItemStack(ModBlocks.colouredQuartzBlock, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Block.blockNetherQuartz, 1, 0) };
		return blocks;
	}
}