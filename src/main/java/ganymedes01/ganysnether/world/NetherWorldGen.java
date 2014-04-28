package ganymedes01.ganysnether.world;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.blocks.ModBlocks;
import ganymedes01.ganysnether.core.utils.RandomItemStackList;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.tileentities.TileEntityUndertaker;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
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

	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		if (world.provider.dimensionId == -1)
			for (int x = 0; x < 16; x++)
				for (int y = 20; y < 84; y++)
					for (int z = 0; z < 16; z++) {
						int blockX = chunkX * 16 + x;
						int blockY = y + 1;
						int blockZ = chunkZ * 16 + z;

						if (GanysNether.shouldGenerateCrops)
							if (rand.nextInt(GanysNether.netherCropRate) == 0)
								if (shouldGenerate(world, blockX, blockY, blockZ))
									if (hasLavaNearby(world, blockX, blockY - 1, blockZ))
										switch (rand.nextInt(6)) {
											case 0:
												world.setBlock(blockX, blockY - 1, blockZ, Blocks.netherrack);
												world.setBlock(blockX, blockY, blockZ, ModBlocks.glowingReed);
												if (rand.nextInt(10) == 5)
													world.setBlock(blockX, blockY + 1, blockZ, ModBlocks.glowingReed);
												return;
											case 1:
												world.setBlock(blockX, blockY - 1, blockZ, ModBlocks.tilledNetherrack);
												world.setBlock(blockX, blockY, blockZ, ModBlocks.spectreWheat, rand.nextInt(7), 2);
												return;
											case 2:
												world.setBlock(blockX, blockY - 1, blockZ, ModBlocks.tilledNetherrack);
												world.setBlock(blockX, blockY, blockZ, ModBlocks.quarzBerryBush, rand.nextInt(7), 2);
												return;
											case 3:
												if (rand.nextInt(GanysNether.witherShrubRate) == 0) {
													world.setBlock(blockX, blockY - 1, blockZ, ModBlocks.tilledNetherrack);
													world.setBlock(blockX, blockY, blockZ, ModBlocks.witherShrub, rand.nextInt(6), 2);
													world.setBlock(blockX, blockY + 1, blockZ, Blocks.glowstone);
												}
												return;
											case 4:
												world.setBlock(blockX, blockY - 1, blockZ, Blocks.netherrack);
												world.setBlock(blockX, blockY, blockZ, ModBlocks.blazingCactoid);
												if (rand.nextInt(10) == 5)
													world.setBlock(blockX, blockY + 1, blockZ, ModBlocks.blazingCactoid);
												return;
											case 5:
												world.setBlock(blockX, blockY - 1, blockZ, ModBlocks.tilledNetherrack);
												world.setBlock(blockX, blockY, blockZ, ModBlocks.hellBush, rand.nextInt(7), 2);
												return;
										}

						if (GanysNether.shouldGenerateUndertakers)
							if (rand.nextInt(GanysNether.undertakerRate) == 0)
								if (shouldGenerate(world, blockX, blockY, blockZ))
									generateUndertakerWithRandomContents(world, blockX, blockY, blockZ, rand);
					}
	}

	private boolean shouldGenerate(World world, int x, int y, int z) {
		if (!world.isAirBlock(x, y - 1, z))
			if (world.isAirBlock(x, y, z))
				if (world.getBlock(x, y - 1, z).getMaterial() != Material.lava)
					return true;
		return false;
	}

	private boolean hasLavaNearby(World world, int x, int y, int z) {
		return world.getBlock(x + 1, y, z).getMaterial() == Material.lava || world.getBlock(x - 1, y, z).getMaterial() == Material.lava || world.getBlock(x, y, z + 1).getMaterial() == Material.lava || world.getBlock(x, y, z - 1).getMaterial() == Material.lava;
	}

	private void generateUndertakerWithRandomContents(World world, int x, int y, int z, Random rand) {
		if (y < 25)
			return;

		if (world.isAirBlock(x, y + 1, z))
			if (world.isAirBlock(x, y + 2, z)) {
				world.setBlock(x, y, z, ModBlocks.undertaker);
				TileEntityUndertaker undertaker = Utils.getTileEntity(world, x, y, z, TileEntityUndertaker.class);
				if (undertaker != null)
					RandomItemStackList.fillInventory(undertaker, undertaker.getSizeInventory() - 4, rand);
			}
	}
}