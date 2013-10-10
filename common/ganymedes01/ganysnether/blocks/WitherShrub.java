package ganymedes01.ganysnether.blocks;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.ModIDs;
import ganymedes01.ganysnether.lib.RenderIDs;
import ganymedes01.ganysnether.lib.Strings;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class WitherShrub extends NetherCrop {

	@SideOnly(Side.CLIENT)
	private Icon disconnected, connected;

	public static final int DEFAULT_CHANCE = 26;

	public WitherShrub() {
		super(ModIDs.WITHER_SHRUB_ID);
		setHardness(0.0F);
		setBlockBounds(0.375F, 0.0F, 0.375F, 0.625F, 0.25F, 0.625F);
		setUnlocalizedName(Utils.getUnlocalizedName(Strings.WITHER_SHRUB_NAME));
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		super.updateTick(world, x, y, z, rand);
		if (world.isRemote)
			return;

		if (rand.nextInt(GanysNether.chanceOfGrowingHead > 0 ? GanysNether.chanceOfGrowingHead : DEFAULT_CHANCE) == 0 && canThisPlantGrowOnThisBlockID(world.getBlockId(x, y - 1, z))) {
			int meta = world.getBlockMetadata(x, y, z);

			if (meta < 7)
				world.setBlockMetadataWithNotify(x, y, z, ++meta, 2);
			else
				growSkull(world, x, y, z, rand);
		}
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess access, int x, int y, int z) {
		int meta = access.getBlockMetadata(x, y, z);
		if (meta < 7)
			setBlockBounds(0.375F, 0.0F, 0.375F, 0.625F, (meta * 2 + 2) / 16.0F, 0.625F);
		else
			setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.625F, 1.0F);
	}

	private void growSkull(World world, int x, int y, int z, Random rand) {
		int skullX = x;
		int skullZ = z;
		int direction = rand.nextInt(4);

		switch (direction) {
			case 0:
				skullZ--;
				break;
			case 1:
				skullZ++;
				break;
			case 2:
				skullX--;
				break;
			case 3:
				skullX++;
				break;
		}

		if (world.isAirBlock(skullX, y, skullZ)) {
			world.setBlock(skullX, y, skullZ, Block.skull.blockID, direction + 2, 2);
			TileEntity tileentity = world.getBlockTileEntity(skullX, y, skullZ);
			if (tileentity != null && tileentity instanceof TileEntitySkull) {
				((TileEntitySkull) tileentity).setSkullType(1, "");
				world.notifyBlockChange(skullX, y, skullZ, blockID);
			}
		}
	}

	/* DEBUG */
	// @Override
	// public boolean onBlockActivated(World world, int x, int y, int z,
	// EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
	// if (world.isRemote)
	// return true;
	// int meta = world.getBlockMetadata(x, y, z);
	// if (meta < 7)
	// world.setBlockMetadataWithNotify(x, y, z, ++meta, 2);
	// else
	// growSkull(world, x, y, z, new Random());
	// return true;
	// }

	@Override
	public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune) {
		return new ArrayList<ItemStack>();
	}

	@Override
	public int quantityDropped(Random rand) {
		return 0;
	}

	@Override
	public int getRenderType() {
		return RenderIDs.WITHER_SHRUB;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta) {
		if (meta >= 7)
			return connected;
		else
			return disconnected;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister reg) {
		disconnected = reg.registerIcon(Utils.getBlockTexture(Strings.WITHER_SHRUB_NAME, true) + "disconnected");
		connected = reg.registerIcon(Utils.getBlockTexture(Strings.WITHER_SHRUB_NAME, true) + "connected");
	}
}