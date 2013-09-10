package ganymedes01.ganysnether.world;

import ganymedes01.ganysnether.blocks.ModBlocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class CropsGenerator implements IWorldGenerator {

	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		if (world.provider.dimensionId == -1)
			for (int x = 0; x < 16; x++)
				for (int y = 0; y < 120; y++)
					for (int z = 0; z < 16; z++) {
						int blockX = chunkX * 16 + x;
						int blockY = y + 1;
						int blockZ = chunkZ * 16 + z;

						if (world.getBlockId(blockX, blockY - 1, blockZ) == Block.netherrack.blockID)
							if (world.isAirBlock(blockX, blockY, blockZ))
								if (hasLavaNearby(world, blockX, blockY - 1, blockZ))
									if (rand.nextInt(20) == 10)
										switch (rand.nextInt(4)) {
											case 2:
												world.setBlock(blockX, blockY - 1, blockZ, ModBlocks.tilledNetherrack.blockID);
												world.setBlock(blockX, blockY, blockZ, ModBlocks.spectreWheat.blockID);
												world.setBlockMetadataWithNotify(blockX, blockY, blockZ, rand.nextInt(7), 2);
												break;
											case 3:
												world.setBlock(blockX, blockY - 1, blockZ, ModBlocks.tilledNetherrack.blockID);
												world.setBlock(blockX, blockY, blockZ, ModBlocks.quarzBerryBush.blockID);
												world.setBlockMetadataWithNotify(blockX, blockY, blockZ, rand.nextInt(7), 2);
												break;
											default:
												world.setBlock(blockX, blockY, blockZ, ModBlocks.glowingReed.blockID);
												break;
										}
					}
	}

	private boolean hasLavaNearby(World world, int x, int y, int z) {
		return world.getBlockMaterial(x + 1, y, z) == Material.lava || world.getBlockMaterial(x - 1, y, z) == Material.lava || world.getBlockMaterial(x, y, z + 1) == Material.lava || world.getBlockMaterial(x, y, z - 1) == Material.lava;
	}
}
