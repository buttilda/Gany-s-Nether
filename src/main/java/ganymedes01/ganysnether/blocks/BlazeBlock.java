package ganymedes01.ganysnether.blocks;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.IConfigurable;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.entities.EntityBlazeGolem;
import ganymedes01.ganysnether.lib.Strings;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

/**
 * Gany's Nether
 *
 * @author ganymedes01
 *
 */

public class BlazeBlock extends Block implements IConfigurable {

	public BlazeBlock() {
		super(Material.iron);
		setHardness(5.0F);
		setResistance(10.0F);
		setStepSound(soundTypeMetal);
		setBlockName(Utils.getUnlocalisedName(Strings.Blocks.BLAZE_BLOCK_NAME));
		setBlockTextureName(Utils.getBlockTexture(Strings.Blocks.BLAZE_BLOCK_NAME));
		setCreativeTab(GanysNether.enableBlazeBlock ? GanysNether.netherTab : null);
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbour) {
		if (world.getBlock(x, y - 1, z) == this && world.getBlock(x, y + 1, z) == Blocks.pumpkin) {
			world.setBlockToAir(x, y + 1, z);
			world.setBlockToAir(x, y, z);
			world.setBlockToAir(x, y - 1, z);

			EntityBlazeGolem golem = new EntityBlazeGolem(world);
			golem.setPositionAndRotation(x + 0.5, y - 1, z + 0.5, 0, 0);
			world.spawnEntityInWorld(golem);

			world.playAuxSFXAtEntity(null, 2001, x, y - 1, z, Block.getIdFromBlock(this));
			world.playAuxSFXAtEntity(null, 2001, x, y, z, Block.getIdFromBlock(this));
			world.playAuxSFXAtEntity(null, 2001, x, y + 1, z, Block.getIdFromBlock(this));
		}
	}

	@Override
	public boolean isEnabled() {
		return GanysNether.enableBlazeBlock;
	}
}