package ganymedes01.ganysnether.blocks;

import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.Strings;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
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
	private Icon wetIcon;
	@SideOnly(Side.CLIENT)
	private Icon dryIcon;

	protected TilledNetherrack(int id) {
		super(id);
		setHardness(0.5F);
		setStepSound(soundGravelFootstep);
		setUnlocalizedName(Utils.getUnlocalizedName(Strings.TILLED_NETHERRACK_NAME));
	}

	@Override
	public boolean canSustainPlant(World world, int x, int y, int z, ForgeDirection direction, IPlantable plant) {
		int plantID = plant.getPlantID(world, x, y + 1, z);
		if (plantID == ModBlocks.quarzBerryBush.blockID)
			return true;
		else if (plantID == ModBlocks.spectreWheat.blockID)
			return true;
		return false;
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		if (!isLavaNearby(world, x + 1, y, z) && !world.canLightningStrikeAt(x, y + 1, z)) {
			int l = world.getBlockMetadata(x, y, z);
			if (l > 0)
				world.setBlockMetadataWithNotify(x, y, z, l - 1, 2);
			else if (!isCropsNearby(world, x, y, z))
				world.setBlock(x, y, z, Block.netherrack.blockID);
		} else
			world.setBlockMetadataWithNotify(x, y, z, 7, 2);
	}

	@Override
	public void onFallenUpon(World world, int x, int y, int z, Entity entity, float hit) {
		if (!world.isRemote && world.rand.nextFloat() < hit - 0.5F) {
			if (!(entity instanceof EntityPlayer) && !world.getGameRules().getGameRuleBooleanValue("mobGriefing"))
				return;
			world.setBlock(x, y, z, Block.netherrack.blockID);
		}
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

	@SideOnly(Side.CLIENT)
	@Override
	public int idPicked(World world, int x, int y, int z) {
		return Block.netherrack.blockID;
	}

	private boolean isLavaNearby(World world, int x, int y, int z) {
		for (int l = x - 4; l <= x + 4; ++l)
			for (int i1 = y; i1 <= y + 1; ++i1)
				for (int j1 = z - 4; j1 <= z + 4; ++j1)
					if (world.getBlockMaterial(l, i1, j1) == Material.lava)
						return true;
		return false;
	}

	private boolean isCropsNearby(World world, int x, int y, int z) {
		byte b0 = 0;
		for (int l = x - b0; l <= x + b0; ++l)
			for (int i1 = z - b0; i1 <= z + b0; ++i1) {
				int j1 = world.getBlockId(l, y + 1, i1);
				Block plant = blocksList[j1];
				if (plant instanceof IPlantable && canSustainPlant(world, x, y, z, ForgeDirection.UP, (IPlantable) plant))
					return true;
			}
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister reg) {
		wetIcon = reg.registerIcon(Utils.getBlockTexture(Strings.TILLED_NETHERRACK_NAME, true) + "wet");
		dryIcon = reg.registerIcon(Utils.getBlockTexture(Strings.TILLED_NETHERRACK_NAME, true) + "dry");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int par1, int par2) {
		return par1 == 1 ? (par2 > 0 ? wetIcon : dryIcon) : Block.netherrack.getBlockTextureFromSide(par1);
	}
}
