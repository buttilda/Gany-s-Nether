package ganymedes01.ganysnether.blocks;

import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.ModIDs;
import ganymedes01.ganysnether.lib.Strings;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.IPlantable;
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
	private Icon wetIcon, dryIcon;

	protected TilledNetherrack() {
		super(ModIDs.TILLED_NETHERRACK_ID);
		setHardness(0.5F);
		setStepSound(soundGravelFootstep);
		setUnlocalizedName(Utils.getUnlocalizedName(Strings.TILLED_NETHERRACK_NAME));
	}

	@Override
	public boolean canSustainPlant(World world, int x, int y, int z, ForgeDirection direction, IPlantable plant) {
		if (direction != ForgeDirection.UP)
			return false;

		int plantID = plant.getPlantID(world, x, y + 1, z);
		if (plantID == ModBlocks.quarzBerryBush.blockID)
			return true;
		else if (plantID == ModBlocks.spectreWheat.blockID)
			return true;
		else if (plantID == ModBlocks.witherShrub.blockID)
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
				world.setBlock(x, y, z, Block.netherrack.blockID);
		} else
			world.setBlockMetadataWithNotify(x, y, z, 7, 2);
	}

	@Override
	public boolean isFertile(World world, int x, int y, int z) {
		return world.getBlockMetadata(x, y, z) > 0;
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int id) {
		super.onNeighborBlockChange(world, x, y, z, id);
		Material material = world.getBlockMaterial(x, y + 1, z);
		if (material.isSolid())
			world.setBlock(x, y, z, Block.netherrack.blockID);
	}

	@Override
	public int idDropped(int meta, Random rand, int fortune) {
		return Block.netherrack.idDropped(0, rand, fortune);
	}

	@Override
	public void onFallenUpon(World world, int x, int y, int z, Entity entity, float chance) {

	}

	private boolean isLavaNearby(World world, int x, int y, int z) {
		for (int i = x - 4; i <= x + 4; i++)
			for (int j = y; j <= y + 1; j++)
				for (int k = z - 4; k <= z + 4; k++)
					if (world.getBlockMaterial(i, j, k) == Material.lava)
						return true;
		return false;
	}

	private boolean isCropsNearby(World world, int x, int y, int z) {
		for (int i = x; i <= x; i++)
			for (int j = z; j <= z; j++) {
				int blockID = world.getBlockId(i, y + 1, j);
				Block plant = blocksList[blockID];
				if (plant instanceof NetherCrop && canSustainPlant(world, x, y, z, ForgeDirection.UP, (NetherCrop) plant))
					return true;
			}
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int idPicked(World world, int x, int y, int z) {
		return Block.netherrack.blockID;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister reg) {
		wetIcon = reg.registerIcon(Utils.getBlockTexture(Strings.TILLED_NETHERRACK_NAME) + "_wet");
		dryIcon = reg.registerIcon(Utils.getBlockTexture(Strings.TILLED_NETHERRACK_NAME) + "_dry");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta) {
		return side == 1 ? meta > 0 ? wetIcon : dryIcon : Block.netherrack.getBlockTextureFromSide(side);
	}
}
