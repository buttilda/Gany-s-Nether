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

public class GlowBox extends Block {

	@SideOnly(Side.CLIENT)
	private Icon[] blockSide, blockTop;

	public GlowBox() {
		super(ModIDs.GLOW_BOX_ID, Material.glass);
		setLightValue(1F);
		setHardness(0.2F);
		setStepSound(soundGlassFootstep);
		setCreativeTab(GanysNether.netherTab);
		setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 1.0F, 0.75F);
		setTextureName(Utils.getBlockTexture(Strings.Blocks.GLOW_BOX_NAME));
		setUnlocalizedName(Utils.getUnlocalizedName(Strings.Blocks.GLOW_BOX_NAME));
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public int damageDropped(int metadata) {
		return metadata;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int id, CreativeTabs tab, List list) {
		for (int i = 0; i < 16; ++i)
			list.add(new ItemStack(id, 1, i));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta) {
		return side == 0 || side == 1 ? blockTop[meta] : blockSide[meta];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister reg) {
		blockTop = new Icon[16];
		blockSide = new Icon[16];
		for (int i = 0; i < 16; i++) {
			blockTop[i] = reg.registerIcon(Utils.getBlockTexture(Strings.Blocks.GLOW_BOX_NAME) + "_top_" + i);
			blockSide[i] = reg.registerIcon(Utils.getBlockTexture(Strings.Blocks.GLOW_BOX_NAME) + "_side_" + i);
		}
	}
}
