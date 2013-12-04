package ganymedes01.ganysnether.blocks;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.ModIDs;
import ganymedes01.ganysnether.lib.Strings;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRotatedPillar;
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

public class ColouredQuartzPillar extends BlockRotatedPillar {

	private static ItemStack[] blocks;

	@SideOnly(Side.CLIENT)
	private Icon[] blockSide, blockTop;

	private int startIndex;

	public ColouredQuartzPillar(int startIndex) {
		super(ModIDs.COLOURED_QUARTZ_PILLARS_IDS[startIndex], Material.rock);
		setHardness(0.8F);
		this.startIndex = startIndex;
		setStepSound(soundStoneFootstep);
		setCreativeTab(GanysNether.netherTab);
	}

	public static int limitToValidMetadata(int meta) {
		return meta & 3;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int id, CreativeTabs tab, List list) {
		list.add(new ItemStack(id, 1, 0));
		list.add(new ItemStack(id, 1, 1));
		list.add(new ItemStack(id, 1, 2));
		list.add(new ItemStack(id, 1, 3));
	}

	@Override
	@SideOnly(Side.CLIENT)
	protected Icon getSideIcon(int index) {
		return blockSide[index];
	}

	@Override
	@SideOnly(Side.CLIENT)
	protected Icon getEndIcon(int index) {
		return blockTop[index];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister reg) {
		blockSide = new Icon[4];
		blockTop = new Icon[4];
		for (int i = 0; i < 4; i++) {
			blockSide[i] = reg.registerIcon(Utils.getBlockTexture(Strings.COLOURED_QUARTZ_PILLARS_NAME) + "_side_" + (startIndex * 4 + i));
			blockTop[i] = reg.registerIcon(Utils.getBlockTexture(Strings.COLOURED_QUARTZ_PILLARS_NAME) + "_top_" + (startIndex * 4 + i));
		}
	}

	public static ItemStack[] getQuartzBlocksForRecipe() {
		if (blocks == null) {
			blocks = new ItemStack[ModBlocks.colouredQuartzPillar.length + 1];

			for (int i = 0; i < ModBlocks.colouredQuartzPillar.length; i++)
				blocks[i] = new ItemStack(ModBlocks.colouredQuartzPillar[i], 1, OreDictionary.WILDCARD_VALUE);

			blocks[ModBlocks.colouredQuartzPillar.length] = new ItemStack(Block.blockNetherQuartz, 1, 2);
		}
		return blocks;
	}
}