package ganymedes01.ganysnether.world;

import ganymedes01.ganysnether.blocks.ModBlocks;
import ganymedes01.ganysnether.core.utils.RandomItemStackList;
import ganymedes01.ganysnether.tileentities.TileEntityUndertaker;

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

public class NetherWorldGen implements IWorldGenerator {

	private RandomItemStackList randomList = new RandomItemStackList();

	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		if (world.provider.dimensionId == -1)
			for (int x = 0; x < 16; x++)
				for (int y = 20; y < 84; y++)
					for (int z = 0; z < 16; z++) {
						int blockX = chunkX * 16 + x;
						int blockY = y + 1;
						int blockZ = chunkZ * 16 + z;

						if (rand.nextInt(20) == 10)
							if (!world.isAirBlock(blockX, blockY - 1, blockZ))
								if (world.getBlockMaterial(blockX, blockY - 1, blockZ) != Material.lava)
									if (world.isAirBlock(blockX, blockY, blockZ))
										if (hasLavaNearby(world, blockX, blockY - 1, blockZ))
											switch (rand.nextInt(4)) {
												case 0:
													world.setBlock(blockX, blockY - 1, blockZ, Block.netherrack.blockID);
													world.setBlock(blockX, blockY, blockZ, ModBlocks.glowingReed.blockID);
													if (rand.nextInt(10) == 5)
														world.setBlock(blockX, blockY + 1, blockZ, ModBlocks.glowingReed.blockID);
													return;
												case 1:
													world.setBlock(blockX, blockY - 1, blockZ, ModBlocks.tilledNetherrack.blockID);
													world.setBlock(blockX, blockY, blockZ, ModBlocks.spectreWheat.blockID, rand.nextInt(7), 2);
													return;
												case 2:
													world.setBlock(blockX, blockY - 1, blockZ, ModBlocks.tilledNetherrack.blockID);
													world.setBlock(blockX, blockY, blockZ, ModBlocks.quarzBerryBush.blockID, rand.nextInt(7), 2);
													return;
												case 3:
													if (rand.nextInt(50) == 25) {
														world.setBlock(blockX, blockY - 1, blockZ, ModBlocks.tilledNetherrack.blockID);
														world.setBlock(blockX, blockY, blockZ, ModBlocks.witherShrub.blockID, rand.nextInt(6), 2);
													}
													return;
											}
										else if (rand.nextInt(300) == 150)
											generateUndertakerWithRandomContents(world, blockX, blockY, blockZ, rand);
						continue;
					}
	}

	private boolean hasLavaNearby(World world, int x, int y, int z) {
		return world.getBlockMaterial(x + 1, y, z) == Material.lava || world.getBlockMaterial(x - 1, y, z) == Material.lava || world.getBlockMaterial(x, y, z + 1) == Material.lava || world.getBlockMaterial(x, y, z - 1) == Material.lava;
	}

	private void generateUndertakerWithRandomContents(World world, int x, int y, int z, Random rand) {
		if (y < 30)
			return;
		if (world.isAirBlock(x, y + 1, z))
			if (world.isAirBlock(x, y + 2, z)) {
				world.setBlock(x, y, z, ModBlocks.undertaker.blockID);
				TileEntityUndertaker undertaker = (TileEntityUndertaker) world.getBlockTileEntity(x, y, z);
				randomList.shuffle();
				if (undertaker != null)
					for (int i = 0; i < 36; i++)
						if (rand.nextInt(8) == 4)
							undertaker.setInventorySlotContents(i, randomList.getListItem(rand));
			}
	}
}
