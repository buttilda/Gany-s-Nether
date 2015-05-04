package ganymedes01.ganysnether.blocks;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.IConfigurable;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.ModSounds;
import ganymedes01.ganysnether.lib.RenderIDs;
import ganymedes01.ganysnether.lib.Strings;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCactus;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Gany's Nether
 *
 * @author ganymedes01
 *
 */

public class BlazingCactoid extends BlockCactus implements IConfigurable {

	public BlazingCactoid() {
		super();
		setHardness(0.4F);
		setLightLevel(0.8F);
		setStepSound(ModSounds.soundBlaze);
		setBlockBounds(0.375F, 0.0F, 0.375F, 0.625F, 1.0F, 0.625F);
		setBlockName(Utils.getUnlocalisedName(Strings.Blocks.BLAZING_CACTOID_NAME));
		setCreativeTab(GanysNether.shouldGenerateCrops ? GanysNether.netherTab : null);
		setBlockTextureName(Utils.getBlockTexture(Strings.Blocks.BLAZING_CACTOID_NAME));
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		if (!world.isRemote)
			if (world.isAirBlock(x, y + 1, z) && rand.nextInt(10) == 0) {
				int height;
				for (height = 1; world.getBlock(x, y - height, z) == this; height++)
					;
				if (height < 5) {
					world.setBlock(x, y + 1, z, this);
					onNeighborBlockChange(world, x, y + 1, z, this);
				}
			}
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		return AxisAlignedBB.getBoundingBox(x + 0.375F, y + 0.0F, z + 0.375F, x + 0.625F, y + 1.0F, z + 0.625F);
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		if (world.getBlock(x - 1, y, z).getMaterial().isSolid())
			return false;
		else if (world.getBlock(x + 1, y, z).getMaterial().isSolid())
			return false;
		else if (world.getBlock(x, y, z - 1).getMaterial().isSolid())
			return false;
		else if (world.getBlock(x, y, z + 1).getMaterial().isSolid())
			return false;
		else {
			Block soil = world.getBlock(x, y - 1, z);
			return soil == Blocks.netherrack || soil == this;
		}
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
		entity.setFire(2);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return blockIcon;
	}

	@Override
	public int getRenderType() {
		return RenderIDs.BLAZING_CACTOID;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
		blockIcon = reg.registerIcon(getTextureName());
	}

	@Override
	public boolean isEnabled() {
		return GanysNether.shouldGenerateCrops;
	}
}