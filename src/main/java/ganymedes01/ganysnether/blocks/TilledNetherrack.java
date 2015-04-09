package ganymedes01.ganysnether.blocks;

import ganymedes01.ganysnether.ModBlocks;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.Strings;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Gany's Nether
 *
 * @author ganymedes01
 *
 */

public class TilledNetherrack extends BlockFarmland {

	@SideOnly(Side.CLIENT)
	private IIcon wetIcon, dryIcon;

	public TilledNetherrack() {
		setHardness(0.5F);
		setLightOpacity(0);
		setStepSound(soundTypeGravel);
		setBlockName(Utils.getUnlocalisedName(Strings.Blocks.TILLED_NETHERRACK_NAME));
	}

	@Override
	public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction, IPlantable plant) {
		if (direction != ForgeDirection.UP)
			return false;

		Block plantID = plant.getPlant(world, x, y + 1, z);
		if (plantID == ModBlocks.quarzBerryBush)
			return true;
		else if (plantID == ModBlocks.spectreWheat)
			return true;
		else if (plantID == ModBlocks.witherShrub)
			return true;
		else if (plantID == ModBlocks.hellBush)
			return true;
		return false;
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		if (!isLavaNearby(world, x, y, z) && !world.canLightningStrikeAt(x, y + 1, z)) {
			int meta = world.getBlockMetadata(x, y, z);
			if (meta > 0)
				world.setBlockMetadataWithNotify(x, y, z, --meta, 2);
			else if (!isCropsNearby(world, x, y, z))
				world.setBlock(x, y, z, Blocks.netherrack);
		} else
			world.setBlockMetadataWithNotify(x, y, z, 7, 2);
	}

	@Override
	public boolean isFertile(World world, int x, int y, int z) {
		return world.getBlockMetadata(x, y, z) > 0;
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbour) {
		super.onNeighborBlockChange(world, x, y, z, neighbour);
		Material material = world.getBlock(x, y + 1, z).getMaterial();
		if (material.isSolid())
			world.setBlock(x, y, z, Blocks.netherrack);
	}

	@Override
	public Item getItemDropped(int meta, Random rand, int fortune) {
		return Blocks.netherrack.getItemDropped(0, rand, fortune);
	}

	@Override
	public void onFallenUpon(World world, int x, int y, int z, Entity entity, float chance) {

	}

	private boolean isLavaNearby(World world, int x, int y, int z) {
		for (int i = x - 4; i <= x + 4; i++)
			for (int j = y; j <= y + 1; j++)
				for (int k = z - 4; k <= z + 4; k++)
					if (world.getBlock(i, j, k).getMaterial() == Material.lava)
						return true;
		return false;
	}

	private boolean isCropsNearby(World world, int x, int y, int z) {
		for (int i = x; i <= x; i++)
			for (int j = z; j <= z; j++) {
				Block plant = world.getBlock(i, y + 1, j);
				if (plant instanceof NetherCrop && canSustainPlant(world, x, y, z, ForgeDirection.UP, (NetherCrop) plant))
					return true;
			}
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getItem(World world, int x, int y, int z) {
		return Item.getItemFromBlock(Blocks.netherrack);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
		wetIcon = reg.registerIcon(Utils.getBlockTexture(Strings.Blocks.TILLED_NETHERRACK_NAME) + "_wet");
		dryIcon = reg.registerIcon(Utils.getBlockTexture(Strings.Blocks.TILLED_NETHERRACK_NAME) + "_dry");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return side == 1 ? meta > 0 ? wetIcon : dryIcon : Blocks.netherrack.getBlockTextureFromSide(side);
	}
}