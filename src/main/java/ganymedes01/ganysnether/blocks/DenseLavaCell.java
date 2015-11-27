package ganymedes01.ganysnether.blocks;

import java.util.Random;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.IConfigurable;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.Strings;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

/**
 * Gany's Nether
 *
 * @author ganymedes01
 *
 */

public class DenseLavaCell extends Block implements IConfigurable {

	public DenseLavaCell() {
		super(Material.iron);
		setHardness(5F);
		setLightLevel(1F);
		setTickRandomly(true);
		setBlockName(Utils.getUnlocalisedName(Strings.Blocks.DENSE_LAVA_CELL_NAME));
		setBlockTextureName(Utils.getBlockTexture(Strings.Blocks.DENSE_LAVA_CELL_NAME));
		setCreativeTab(GanysNether.enableVolcanicFurnace ? GanysNether.netherTab : null);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		return AxisAlignedBB.getBoundingBox(x, y, z, x + 1, y + 0.875F, z + 1);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
		entity.setFire(5);
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		int num = rand.nextInt(3);

		for (int i = 0; i < num; i++) {
			x += rand.nextInt(3) - 1;
			y++;
			z += rand.nextInt(3) - 1;

			if (isFlammable(world, x - 1, y, z) || isFlammable(world, x + 1, y, z) || isFlammable(world, x, y, z - 1) || isFlammable(world, x, y, z + 1) || isFlammable(world, x, y - 1, z) || isFlammable(world, x, y + 1, z)) {
				world.setBlock(x, y, z, Blocks.fire);
				return;
			}
		}

		if (num == 0)
			for (int i = 0; i < 3; i++) {
				x = x + rand.nextInt(3) - 1;
				z = z + rand.nextInt(3) - 1;

				if (world.isAirBlock(x, y + 1, z) && isFlammable(world, x, y, z))
					world.setBlock(x, y + 1, z, Blocks.fire);
			}
	}

	private boolean isFlammable(World world, int x, int y, int z) {
		return world.getBlock(x, y, z).getMaterial().getCanBurn();
	}

	@Override
	public boolean isEnabled() {
		return GanysNether.enableVolcanicFurnace;
	}
}