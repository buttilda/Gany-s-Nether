package ganymedes01.ganysnether.blocks;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.IConfigurable;
import ganymedes01.ganysnether.ModItems;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.Strings;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockReed;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Gany's Nether
 *
 * @author ganymedes01
 *
 */

public class GlowingReedCrop extends BlockReed implements IConfigurable {

	public GlowingReedCrop() {
		super();
		disableStats();
		setHardness(0.0F);
		setLightLevel(0.5F);
		setStepSound(soundTypeGrass);
		setBlockName(Utils.getUnlocalisedName(Strings.Blocks.GLOWING_REED_BLOCK_NAME));
		setBlockTextureName(Utils.getBlockTexture(Strings.Blocks.GLOWING_REED_BLOCK_NAME));
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		if (rand.nextInt(25) == 0 && canPlaceBlockAt(world, x, y, z))
			if (world.isAirBlock(x, y + 1, z)) {
				int height;

				for (height = 1; world.getBlock(x, y - height, z) == this; height++)
					;
				if (height < 4)
					world.setBlock(x, y + 1, z, this);
			}
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		Block block = world.getBlock(x, y - 1, z);
		if (block == null)
			return false;
		else if (block == this)
			return true;
		boolean isBeach = block == Blocks.netherrack || block == Blocks.soul_sand;
		boolean hasLava = world.getBlock(x - 1, y - 1, z).getMaterial() == Material.lava || world.getBlock(x + 1, y - 1, z).getMaterial() == Material.lava || world.getBlock(x, y - 1, z - 1).getMaterial() == Material.lava || world.getBlock(x, y - 1, z + 1).getMaterial() == Material.lava;
		return isBeach && hasLava;
	}

	@Override
	public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
		return EnumPlantType.Nether;
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		return canPlaceBlockAt(world, x, y, z);
	}

	@Override
	public Item getItemDropped(int id, Random random, int meta) {
		return ModItems.glowingReed;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getItem(World world, int x, int y, int z) {
		return ModItems.glowingReed;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess world, int x, int y, int z) {
		return Utils.getColour(255, 255, 255);
	}

	@Override
	public boolean isEnabled() {
		return GanysNether.shouldGenerateCrops && GanysNether.shouldGenerateGlowingReed;
	}
}