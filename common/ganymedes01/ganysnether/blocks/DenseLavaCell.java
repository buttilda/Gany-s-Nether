package ganymedes01.ganysnether.blocks;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.Strings;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class DenseLavaCell extends Block {

	public DenseLavaCell(int id) {
		super(id, Material.iron);
		setHardness(5F);
		setLightValue(1F);
		setTickRandomly(true);
		setCreativeTab(GanysNether.netherTab);
		setTextureName(Utils.getBlockTexture(Strings.DENSE_LAVA_CELL_NAME, false));
		setUnlocalizedName(Utils.getUnlocalizedName(Strings.DENSE_LAVA_CELL_NAME));
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		return AxisAlignedBB.getAABBPool().getAABB(x, y, z, x + 1, y + 0.875F, z + 1);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
		entity.setFire(5);
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		int l = rand.nextInt(3);
		int i1;
		int j1;

		for (i1 = 0; i1 < l; ++i1) {
			x += rand.nextInt(3) - 1;
			++y;
			z += rand.nextInt(3) - 1;
			j1 = world.getBlockId(x, y, z);

			if (isFlammable(world, x - 1, y, z) || isFlammable(world, x + 1, y, z) || isFlammable(world, x, y, z - 1) || isFlammable(world, x, y, z + 1) || isFlammable(world, x, y - 1, z) || isFlammable(world, x, y + 1, z)) {
				world.setBlock(x, y, z, Block.fire.blockID);
				return;
			}
		}

		if (l == 0) {
			i1 = x;
			j1 = z;

			for (int k1 = 0; k1 < 3; ++k1) {
				x = i1 + rand.nextInt(3) - 1;
				z = j1 + rand.nextInt(3) - 1;

				if (world.isAirBlock(x, y + 1, z) && isFlammable(world, x, y, z))
					world.setBlock(x, y + 1, z, Block.fire.blockID);
			}
		}
	}

	private boolean isFlammable(World par1World, int par2, int par3, int par4) {
		return par1World.getBlockMaterial(par2, par3, par4).getCanBurn();
	}
}
