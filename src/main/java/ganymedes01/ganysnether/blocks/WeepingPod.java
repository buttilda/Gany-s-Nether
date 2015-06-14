package ganymedes01.ganysnether.blocks;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.IConfigurable;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.Strings;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.BlockCocoa;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Gany's Nether
 *
 * @author ganymedes01
 *
 */

public class WeepingPod extends BlockCocoa implements IConfigurable {

	@SideOnly(Side.CLIENT)
	private IIcon[] iconArray;

	public WeepingPod() {
		setHardness(0.2F);
		setResistance(5.0F);
		setStepSound(soundTypeWood);
		setBlockName(Utils.getUnlocalisedName(Strings.Blocks.WEEPING_POD_NAME));
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		if (!canBlockStay(world, x, y, z)) {
			dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
			world.setBlockToAir(x, y, z);
		} else if (world.rand.nextInt(10) == 5) {
			int meta = world.getBlockMetadata(x, y, z);
			int dirMeta = func_149987_c(meta);

			if (dirMeta < 2) {
				dirMeta++;
				world.setBlockMetadataWithNotify(x, y, z, dirMeta << 2 | getDirection(meta), 2);
			}
		}
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int meta, int fortune) {
		ArrayList<ItemStack> dropped = new ArrayList<ItemStack>();
		dropped.add(new ItemStack(Items.ghast_tear));

		if ((meta & 12) >> 2 >= 2)
			dropped.add(new ItemStack(Items.ghast_tear));

		return dropped;
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		int direction = getDirection(world.getBlockMetadata(x, y, z));
		x += Direction.offsetX[direction];
		z += Direction.offsetZ[direction];
		return world.getBlock(x, y, z) == Blocks.obsidian;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
		iconArray = new IIcon[3];
		for (int i = 0; i < iconArray.length; i++)
			iconArray[i] = reg.registerIcon(Utils.getBlockTexture(Strings.Blocks.WEEPING_POD_NAME) + "_stage_" + i);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getCocoaIcon(int meta) {
		if (meta < 0 || meta >= iconArray.length)
			meta = iconArray.length - 1;

		return iconArray[meta];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return iconArray[2];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getItem(World world, int x, int y, int z) {
		return Items.ghast_tear;
	}

	@Override
	public int getDamageValue(World world, int x, int y, int z) {
		return 0;
	}

	@Override
	public boolean isEnabled() {
		return GanysNether.shouldGenerateCrops && GanysNether.shouldGenerateWeepingPod;
	}
}