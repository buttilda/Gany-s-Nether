package ganymedes01.ganysnether.blocks;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.ModBlocks.ISubBlocksBlock;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.items.blocks.ItemSoulGlass;
import ganymedes01.ganysnether.lib.Strings;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Gany's Nether
 *
 * @author ganymedes01
 *
 */

public class SoulGlass extends Block implements ISubBlocksBlock {

	@SideOnly(Side.CLIENT)
	private IIcon block, brick;

	public SoulGlass() {
		super(Material.glass);
		setHardness(0.3F);
		setLightOpacity(3);
		setStepSound(soundTypeGlass);
		setBlockName(Utils.getUnlocalizedName(Strings.Blocks.SOUL_GLASS_NAME));
		setCreativeTab(GanysNether.enableSoulGlass ? GanysNether.netherTab : null);
	}

	@Override
	protected boolean canSilkHarvest() {
		return true;
	}

	@Override
	public int quantityDropped(Random rand) {
		int quantity = rand.nextInt(10);
		if (quantity == 5)
			return 1;
		return 0;
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
		for (int i = 0; i < 2; i++)
			list.add(new ItemStack(item, 1, i));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess access, int x, int y, int z, int side) {
		return access.getBlock(x, y, z) == this ? false : super.shouldSideBeRendered(access, x, y, z, side);
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderBlockPass() {
		return 1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		if (meta == 0)
			return block;
		return brick;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
		block = reg.registerIcon(Utils.getBlockTexture(Strings.Blocks.SOUL_GLASS_NAME) + "_0");
		brick = reg.registerIcon(Utils.getBlockTexture(Strings.Blocks.SOUL_GLASS_NAME) + "_1");
	}

	@Override
	public Class<? extends ItemBlock> getItemBlockClass() {
		return ItemSoulGlass.class;
	}
}