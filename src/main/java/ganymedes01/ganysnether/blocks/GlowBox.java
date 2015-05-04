package ganymedes01.ganysnether.blocks;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.IConfigurable;
import ganymedes01.ganysnether.ModBlocks.ISubBlocksBlock;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.items.blocks.ItemGlowBox;
import ganymedes01.ganysnether.lib.Strings;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
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

public class GlowBox extends Block implements ISubBlocksBlock, IConfigurable {

	@SideOnly(Side.CLIENT)
	private IIcon[] blockSide, blockTop;

	public GlowBox() {
		super(Material.glass);
		setLightLevel(1F);
		setHardness(0.2F);
		setStepSound(soundTypeGlass);
		setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 1.0F, 0.75F);
		setBlockName(Utils.getUnlocalisedName(Strings.Blocks.GLOW_BOX_NAME));
		setCreativeTab(GanysNether.enableGlowbox ? GanysNether.netherTab : null);
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
		return side == 0 || side == 1 ? blockTop[meta] : blockSide[meta];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
		blockTop = new IIcon[16];
		blockSide = new IIcon[16];
		for (int i = 0; i < 16; i++) {
			blockTop[i] = reg.registerIcon(Utils.getBlockTexture(Strings.Blocks.GLOW_BOX_NAME) + "_top_" + i);
			blockSide[i] = reg.registerIcon(Utils.getBlockTexture(Strings.Blocks.GLOW_BOX_NAME) + "_side_" + i);
		}
	}

	@Override
	public Class<? extends ItemBlock> getItemBlockClass() {
		return ItemGlowBox.class;
	}

	@Override
	public boolean isEnabled() {
		return GanysNether.enableGlowbox;
	}
}