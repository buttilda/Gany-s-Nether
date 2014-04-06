package ganymedes01.ganysnether.blocks;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.ModIDs;
import ganymedes01.ganysnether.lib.Strings;
import ganymedes01.ganysnether.tileentities.TileEntityExtendedSpawner;
import net.minecraft.block.BlockMobSpawner;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class ExtendedSpawner extends BlockMobSpawner {

	public ExtendedSpawner() {
		super(ModIDs.EXTENDED_SPAWNER_ID);
		disableStats();
		setHardness(5.0F);
		setTextureName("mob_spawner");
		setStepSound(soundMetalFootstep);
		setCreativeTab(GanysNether.netherTab);
		setUnlocalizedName(Utils.getUnlocalizedName(Strings.EXTENDED_SPAWNER_NAME));
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityExtendedSpawner();
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int neighbourID) {
		TileEntity tile = world.getBlockTileEntity(x, y, z);
		if (tile instanceof TileEntityExtendedSpawner) {
			TileEntityExtendedSpawner spawner = (TileEntityExtendedSpawner) tile;
			spawner.logic.isBlockPowered = world.isBlockIndirectlyGettingPowered(x, y, z);
		}
	}

	@Override
	public int getExpDrop(World world, int meta, int fortune) {
		return meta == 0 ? 15 + world.rand.nextInt(15) + world.rand.nextInt(15) : 0;
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, int id, int meta) {
		if (!world.isRemote) {
			TileEntity tile = world.getBlockTileEntity(x, y, z);
			if (tile instanceof TileEntityExtendedSpawner) {
				TileEntityExtendedSpawner spawner = (TileEntityExtendedSpawner) tile;
				for (ItemStack stack : spawner.getUpgrades())
					Utils.dropStack(world, x, y, z, stack);
			}
		}
		super.breakBlock(world, x, y, z, id, meta);
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack stack) {
		if (stack.hasTagCompound() && stack.getTagCompound().hasKey("EntityId")) {
			TileEntity tile = world.getBlockTileEntity(x, y, z);
			if (tile instanceof TileEntityExtendedSpawner) {
				TileEntityExtendedSpawner spawner = (TileEntityExtendedSpawner) tile;
				spawner.logic.setMobID(stack.getTagCompound().getString("EntityId"));
			}
		}
	}
}