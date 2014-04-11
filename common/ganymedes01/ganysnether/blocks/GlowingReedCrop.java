package ganymedes01.ganysnether.blocks;

import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.items.ModItems;
import ganymedes01.ganysnether.lib.ModIDs;
import ganymedes01.ganysnether.lib.Strings;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockReed;
import net.minecraft.block.material.Material;
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

public class GlowingReedCrop extends BlockReed {

	public GlowingReedCrop() {
		super(ModIDs.GLOWING_REED_CROP_ID);
		disableStats();
		setHardness(0.0F);
		setLightValue(0.5F);
		setStepSound(soundGrassFootstep);
		setTextureName(Utils.getBlockTexture(Strings.Blocks.GLOWING_REED_BLOCK_NAME));
		setUnlocalizedName(Utils.getUnlocalizedName(Strings.Blocks.GLOWING_REED_BLOCK_NAME));
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		if (rand.nextInt(5) == 0)
			super.updateTick(world, x, y, z, rand);
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		Block block = Block.blocksList[world.getBlockId(x, y - 1, z)];
		if (block == null)
			return false;
		else if (block.blockID == blockID)
			return true;
		boolean isBeach = block.blockID == Block.netherrack.blockID || block.blockID == Block.slowSand.blockID;
		boolean hasLava = world.getBlockMaterial(x - 1, y - 1, z) == Material.lava || world.getBlockMaterial(x + 1, y - 1, z) == Material.lava || world.getBlockMaterial(x, y - 1, z - 1) == Material.lava || world.getBlockMaterial(x, y - 1, z + 1) == Material.lava;
		return isBeach && hasLava;
	}

	@Override
	public EnumPlantType getPlantType(World world, int x, int y, int z) {
		return EnumPlantType.Nether;
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		return canPlaceBlockAt(world, x, y, z);
	}

	@Override
	public int idDropped(int id, Random random, int meta) {
		return ModItems.glowingReed.itemID;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int idPicked(World world, int x, int y, int z) {
		return ModItems.glowingReed.itemID;
	}
}
