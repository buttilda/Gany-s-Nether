package ganymedes01.ganysnether.integration;

import ganymedes01.ganysnether.ModBlocks;
import ganymedes01.ganysnether.ModItems;
import ganymedes01.ganysnether.core.utils.Utils;

import java.util.List;
import java.util.Map;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import powercrystals.minefactoryreloaded.api.FactoryRegistry;
import powercrystals.minefactoryreloaded.api.HarvestType;
import powercrystals.minefactoryreloaded.api.IFactoryHarvestable;
import powercrystals.minefactoryreloaded.api.IFactoryPlantable;
import powercrystals.minefactoryreloaded.api.ReplacementBlock;

/**
 * Gany's Nether
 *
 * @author ganymedes01
 *
 */

public class MFRManager extends Integration {

	private final ForgeDirection[] SIDES = { ForgeDirection.EAST, ForgeDirection.WEST, ForgeDirection.SOUTH, ForgeDirection.NORTH };

	@Override
	public void init() {
		FactoryRegistry.sendMessage("registerPlantable", new Plantable(ModItems.ghostSeeds, ModBlocks.spectreWheat));
		FactoryRegistry.sendMessage("registerPlantable", new Plantable(ModItems.quarzBerrySeeds, ModBlocks.quarzBerryBush));
		FactoryRegistry.sendMessage("registerPlantable", new Plantable(ModItems.hellBushSeeds, ModBlocks.hellBush));
		FactoryRegistry.sendMessage("registerPlantable", new PlantableSugarCane(Item.getItemFromBlock(ModBlocks.blazingCactoid), ModBlocks.blazingCactoid));
		FactoryRegistry.sendMessage("registerPlantable", new PlantableSugarCane(ModItems.glowingReed, ModBlocks.glowingReed));
		FactoryRegistry.sendMessage("registerPlantable", new Plantable(ModItems.witherShrubSeeds, ModBlocks.witherShrub));
		FactoryRegistry.sendMessage("registerPlantable", new Plantable(Items.ghast_tear, ModBlocks.weepingPod) {

			@Override
			public boolean canBePlantedHere(World world, int x, int y, int z, ItemStack stack) {
				for (ForgeDirection side : SIDES)
					if (world.getBlock(x + side.offsetX, y, z + side.offsetZ) == Blocks.obsidian)
						return true;
				return false;
			}

			@Override
			public ReplacementBlock getPlantedBlock(World world, int x, int y, int z, ItemStack stack) {
				ReplacementBlock block = super.getPlantedBlock(world, x, y, z, stack);
				for (ForgeDirection side : SIDES)
					if (world.getBlock(x + side.offsetX, y, z + side.offsetZ) == Blocks.obsidian)
						switch (side.getOpposite().ordinal()) {
							case 2:
								block.setMeta(0);
								break;
							case 3:
								block.setMeta(2);
								break;
							case 4:
								block.setMeta(3);
								break;
							case 5:
								block.setMeta(1);
								break;
							default:
								break;
						}

				return block;
			}
		});

		FactoryRegistry.sendMessage("registerHarvestable", new Harvestable(ModBlocks.spectreWheat));
		FactoryRegistry.sendMessage("registerHarvestable", new Harvestable(ModBlocks.quarzBerryBush));
		FactoryRegistry.sendMessage("registerHarvestable", new Harvestable(ModBlocks.hellBush));
		FactoryRegistry.sendMessage("registerHarvestable", new HarvestableSugarCane(ModBlocks.blazingCactoid));
		FactoryRegistry.sendMessage("registerHarvestable", new HarvestableSugarCane(ModBlocks.glowingReed));

		FactoryRegistry.sendMessage("registerHarvestable", new Harvestable(Blocks.skull) {
			@Override
			public boolean canBeHarvested(World world, Map<String, Boolean> harvesterSettings, int x, int y, int z) {
				for (ForgeDirection side : SIDES)
					if (world.getBlock(x + side.offsetX, y, z + side.offsetZ) == ModBlocks.witherShrub) {
						TileEntitySkull tile = Utils.getTileEntity(world, x, y, z, TileEntitySkull.class);
						return tile != null && tile.func_145904_a() == 1;
					}
				return false;
			}
		});

		FactoryRegistry.sendMessage("registerHarvestable", new Harvestable(ModBlocks.weepingPod) {
			@Override
			public boolean canBeHarvested(World world, Map<String, Boolean> harvesterSettings, int x, int y, int z) {
				return (world.getBlockMetadata(x, y, z) & 12) >> 2 >= 2;
			}
		});
	}

	@Override
	public void postInit() {
	}

	@Override
	public String getModID() {
		return "MineFactoryReloaded";
	}

	private class HarvestableSugarCane extends Harvestable {

		private HarvestableSugarCane(Block block) {
			super(block);
		}

		@Override
		public HarvestType getHarvestType() {
			return HarvestType.LeaveBottom;
		}

		@Override
		public boolean canBeHarvested(World world, Map<String, Boolean> harvesterSettings, int x, int y, int z) {
			return true;
		}
	}

	private class Harvestable implements IFactoryHarvestable {

		private final Block block;

		private Harvestable(Block block) {
			this.block = block;
		}

		@Override
		public Block getPlant() {
			return block;
		}

		@Override
		public HarvestType getHarvestType() {
			return HarvestType.Normal;
		}

		@Override
		public boolean breakBlock() {
			return true;
		}

		@Override
		public boolean canBeHarvested(World world, Map<String, Boolean> harvesterSettings, int x, int y, int z) {
			return world.getBlockMetadata(x, y, z) >= 7;
		}

		@Override
		public List<ItemStack> getDrops(World world, Random rand, Map<String, Boolean> harvesterSettings, int x, int y, int z) {
			return world.getBlock(x, y, z).getDrops(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
		}

		@Override
		public void preHarvest(World world, int x, int y, int z) {
		}

		@Override
		public void postHarvest(World world, int x, int y, int z) {
		}
	}

	private class PlantableSugarCane extends Plantable {

		private PlantableSugarCane(Item seed, Block plant) {
			super(seed, plant);
		}

		@Override
		public void prePlant(World world, int x, int y, int z, ItemStack stack) {
		}
	}

	private class Plantable implements IFactoryPlantable {

		private final Item seed;
		private final Block plant;

		private Plantable(Item seed, Block plant) {
			this.seed = seed;
			this.plant = plant;
		}

		@Override
		public Item getSeed() {
			return seed;
		}

		@Override
		public boolean canBePlanted(ItemStack stack, boolean forFermenting) {
			return true;
		}

		@Override
		public ReplacementBlock getPlantedBlock(World world, int x, int y, int z, ItemStack stack) {
			return new ReplacementBlock(plant);
		}

		@Override
		public boolean canBePlantedHere(World world, int x, int y, int z, ItemStack stack) {
			return plant.canPlaceBlockAt(world, x, y, z);
		}

		@Override
		public void prePlant(World world, int x, int y, int z, ItemStack stack) {
			if (world.getBlock(x, y, z) == Blocks.netherrack)
				world.setBlock(x, y, z, ModBlocks.tilledNetherrack);
		}

		@Override
		public void postPlant(World world, int x, int y, int z, ItemStack stack) {
		}
	}
}