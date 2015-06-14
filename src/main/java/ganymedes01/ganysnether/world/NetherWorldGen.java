package ganymedes01.ganysnether.world;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.ModBlocks;
import ganymedes01.ganysnether.ModItems;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.Reference;
import ganymedes01.ganysnether.lib.Strings;
import ganymedes01.ganysnether.tileentities.TileEntityUndertaker;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.ChestGenHooks;
import cpw.mods.fml.common.IWorldGenerator;

/**
 * Gany's Nether
 *
 * @author ganymedes01
 *
 */

public class NetherWorldGen implements IWorldGenerator {

	public static void initChestGenHook() {
		String key = Reference.MOD_ID + "." + Strings.Blocks.UNDERTAKER_NAME;
		ChestGenHooks info = ChestGenHooks.getInfo(key);
		info.setMin(2);
		info.setMax(8);

		if (GanysNether.shouldGenerateCrops) {
			if (GanysNether.shouldGenerateWitherShrub)
				info.addItem(new WeightedRandomChestContent(new ItemStack(ModItems.witherShrubSeeds), 1, 2, 1));
			if (GanysNether.shouldGenerateQuarzBerryBush)
				info.addItem(new WeightedRandomChestContent(new ItemStack(ModItems.quarzBerrySeeds), 0, 12, 10));
			if (GanysNether.shouldGenerateSpectreWheat) {
				info.addItem(new WeightedRandomChestContent(new ItemStack(ModItems.ghostSeeds), 0, 16, 8));
				info.addItem(new WeightedRandomChestContent(new ItemStack(ModItems.dimensionalBread), 0, 18, 15));
			}
		}

		info.addItem(new WeightedRandomChestContent(new ItemStack(ModItems.skeletonSpawner), 0, 1, 8));
		info.addItem(new WeightedRandomChestContent(new ItemStack(ModItems.skeletonSpawner, 1, 1), 0, 1, 9));

		info.addItem(new WeightedRandomChestContent(new ItemStack(Blocks.torch), 0, 32, 13));
		info.addItem(new WeightedRandomChestContent(new ItemStack(Items.coal), 0, 32, 20));
		info.addItem(new WeightedRandomChestContent(new ItemStack(Items.stick), 0, 32, 22));
		info.addItem(new WeightedRandomChestContent(new ItemStack(Items.beef), 0, 10, 12));
		info.addItem(new WeightedRandomChestContent(new ItemStack(Items.baked_potato), 0, 20, 21));
		info.addItem(new WeightedRandomChestContent(new ItemStack(Items.gold_nugget), 0, 14, 15));
		info.addItem(new WeightedRandomChestContent(new ItemStack(Items.rotten_flesh), 0, 64, 25));
		info.addItem(new WeightedRandomChestContent(new ItemStack(Blocks.nether_brick), 0, 64, 15));
		info.addItem(new WeightedRandomChestContent(new ItemStack(Blocks.end_stone), 0, 40, 10));
		info.addItem(new WeightedRandomChestContent(new ItemStack(Blocks.log), 0, 32, 15));
		info.addItem(new WeightedRandomChestContent(new ItemStack(Blocks.log2), 0, 16, 15));
		info.addItem(new WeightedRandomChestContent(new ItemStack(Blocks.dirt), 0, 64, 20));
		info.addItem(new WeightedRandomChestContent(new ItemStack(Blocks.sand), 0, 64, 19));
		info.addItem(new WeightedRandomChestContent(new ItemStack(Items.wheat_seeds), 0, 20, 11));
		info.addItem(new WeightedRandomChestContent(new ItemStack(Items.leather), 0, 10, 10));
		info.addItem(new WeightedRandomChestContent(new ItemStack(Blocks.cobblestone), 0, 64, 30));
		info.addItem(new WeightedRandomChestContent(new ItemStack(Items.redstone), 0, 24, 15));
	}

	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		if (!world.provider.isHellWorld)
			return;

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
											if (GanysNether.shouldGenerateGlowingReed) {
												world.setBlock(blockX, blockY - 1, blockZ, Blocks.netherrack);
												world.setBlock(blockX, blockY, blockZ, ModBlocks.glowingReed);
												if (rand.nextInt(10) == 5)
													world.setBlock(blockX, blockY + 1, blockZ, ModBlocks.glowingReed);
											}
											return;
										case 1:
											if (GanysNether.shouldGenerateSpectreWheat) {
												world.setBlock(blockX, blockY - 1, blockZ, ModBlocks.tilledNetherrack);
												world.setBlock(blockX, blockY, blockZ, ModBlocks.spectreWheat, rand.nextInt(7), 2);
											}
											return;
										case 2:
											if (GanysNether.shouldGenerateQuarzBerryBush) {
												world.setBlock(blockX, blockY - 1, blockZ, ModBlocks.tilledNetherrack);
												world.setBlock(blockX, blockY, blockZ, ModBlocks.quarzBerryBush, rand.nextInt(7), 2);
											}
											return;
										case 3:
											if (GanysNether.shouldGenerateWitherShrub)
												if (rand.nextInt(GanysNether.witherShrubRate) == 0) {
													world.setBlock(blockX, blockY - 1, blockZ, ModBlocks.tilledNetherrack);
													world.setBlock(blockX, blockY, blockZ, ModBlocks.witherShrub, rand.nextInt(6), 2);
													world.setBlock(blockX, blockY + 1, blockZ, Blocks.glowstone);
												}
											return;
										case 4:
											if (GanysNether.shouldGenerateBlazingCactoid) {
												world.setBlock(blockX, blockY - 1, blockZ, Blocks.netherrack);
												world.setBlock(blockX, blockY, blockZ, ModBlocks.blazingCactoid);
												if (rand.nextInt(10) == 5)
													world.setBlock(blockX, blockY + 1, blockZ, ModBlocks.blazingCactoid);
											}
											return;
										case 5:
											if (GanysNether.shouldGenerateHellBush) {
												world.setBlock(blockX, blockY - 1, blockZ, ModBlocks.tilledNetherrack);
												world.setBlock(blockX, blockY, blockZ, ModBlocks.hellBush, rand.nextInt(7), 2);
											}
											return;
									}

					if (GanysNether.shouldGenerateUndertakers)
						if (rand.nextInt(GanysNether.undertakerRate) == 0)
							if (shouldGenerate(world, blockX, blockY, blockZ))
								generateUndertakerWithRandomContents(world, blockX, blockY, blockZ, rand);
				}
	}

	private boolean shouldGenerate(World world, int x, int y, int z) {
		if (y >= 25)
			if (!world.isAirBlock(x, y - 1, z) && world.getBlock(x, y - 1, z).getMaterial() != Material.lava)
				if (world.isAirBlock(x, y, z) && world.isAirBlock(x, y + 1, z) && world.isAirBlock(x, y + 2, z))
					return true;
		return false;
	}

	private boolean hasLavaNearby(World world, int x, int y, int z) {
		return world.getBlock(x + 1, y, z).getMaterial() == Material.lava || world.getBlock(x - 1, y, z).getMaterial() == Material.lava || world.getBlock(x, y, z + 1).getMaterial() == Material.lava || world.getBlock(x, y, z - 1).getMaterial() == Material.lava;
	}

	private void generateUndertakerWithRandomContents(World world, int x, int y, int z, Random rand) {
		world.setBlock(x, y, z, ModBlocks.undertaker);
		TileEntityUndertaker undertaker = Utils.getTileEntity(world, x, y, z, TileEntityUndertaker.class);
		if (undertaker != null) {
			ChestGenHooks info = ChestGenHooks.getInfo(Reference.MOD_ID + "." + Strings.Blocks.UNDERTAKER_NAME);
			WeightedRandomChestContent.generateChestContents(world.rand, info.getItems(world.rand), undertaker, info.getCount(world.rand));
		}
	}
}