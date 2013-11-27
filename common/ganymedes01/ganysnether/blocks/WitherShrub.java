package ganymedes01.ganysnether.blocks;

import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.items.ModItems;
import ganymedes01.ganysnether.lib.ModIDs;
import ganymedes01.ganysnether.lib.RenderIDs;
import ganymedes01.ganysnether.lib.Strings;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
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

	public WitherShrub() {
		super(ModIDs.WITHER_SHRUB_ID);
		setHardness(0.0F);
		setTickRandomly(true);
		setBlockBounds(0.375F, 0.0F, 0.375F, 0.625F, 0.25F, 0.625F);
		setUnlocalizedName(Utils.getUnlocalizedName(Strings.WITHER_SHRUB_NAME));
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		if (world.isRemote)
			return;
		if (canThisPlantGrowOnThisBlockID(world.getBlockId(x, y - 1, z))) {
			int random = rand.nextInt(90);
			if (random == 45) {
				int meta = world.getBlockMetadata(x, y, z);

				if (meta < 7)
					world.setBlockMetadataWithNotify(x, y, z, ++meta, 2);
				else
					growSkull(world, x, y, z, rand);
			}
		}
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int neighbourID) {
		if (world.isRemote)
			return;
		int meta = world.getBlockMetadata(x, y, z);
		for (int i = 0; i < 4; i++) {
			int skullX = x;
			int skullZ = z;

			switch (i) {
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
			if (world.getBlockId(skullX, y, skullZ) == Block.skull.blockID)
				if (meta >= 7) {
					world.setBlockMetadataWithNotify(x, y, z, 8, 3);
					return;
				}
		}
		if (meta >= 8)
			world.setBlockMetadataWithNotify(x, y, z, 7, 3);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (world.isRemote)
			return false;
		for (int direction : Utils.getRandomizedList(0, 4)) {
			int skullX = x;
			int skullZ = z;

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
			if (world.getBlockId(skullX, y, skullZ) == Block.skull.blockID) {
				TileEntity tileentity = world.getBlockTileEntity(skullX, y, skullZ);
				if (tileentity != null && tileentity instanceof TileEntitySkull)
					if (((TileEntitySkull) tileentity).getSkullType() == 1) {
						Utils.dropStack(world, skullX, y, skullZ, new ItemStack(Item.skull, 1, 1));
						world.playAuxSFXAtEntity(null, 2001, skullX, y, skullZ, Block.skull.blockID + (world.getBlockMetadata(skullX, y, skullZ) << 12));
						world.setBlockToAir(skullX, y, skullZ);
					}
			}
		}
		return false;
	}

	private void growSkull(World world, int x, int y, int z, Random rand) {
		for (int direction : Utils.getRandomizedList(0, 4)) {
			int skullX = x;
			int skullZ = z;

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
				return;
			}
		}
	}

	@Override
	public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune) {
		ArrayList<ItemStack> list = new ArrayList<ItemStack>();
		int size = 1;
		if (metadata >= 7 && new Random().nextInt(80) == 40)
			size++;

		list.add(new ItemStack(ModItems.witherShrubSeeds, size));
		return list;
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
	public void setBlockBoundsBasedOnState(IBlockAccess access, int x, int y, int z) {
		int meta = access.getBlockMetadata(x, y, z);
		if (meta < 7)
			setBlockBounds(0.375F, 0.0F, 0.375F, 0.625F, (meta * 2 + 2) / 16.0F, 0.625F);
		else
			setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.625F, 1.0F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int idPicked(World world, int x, int y, int z) {
		return ModItems.witherShrubSeeds.itemID;
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
	public int getRenderColor(int meta) {
		int r = 255 - meta * 32;
		int g = 255 - (255 - meta * 8) - 50;
		int b = 255 - meta * 4 - 100;
		return Utils.getColour(r, g, b);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess access, int x, int y, int z) {
		return getRenderColor(access.getBlockMetadata(x, y, z));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
		for (int i = 0; i < world.getBlockMetadata(x, y, z) * 5; i++) {
			float offX = rand.nextFloat() * 0.6F - 0.3F;
			float offY = rand.nextFloat() * 6.0F / 16.0F;
			float offZ = rand.nextFloat() * 0.6F - 0.3F;

			world.spawnParticle("smoke", x + 0.5F + offX, y + offY, z + 0.5F + offZ, 0.0D, 0.0D, 0.0D);
		}

		for (int direction : Utils.getRandomizedList(0, 4)) {
			int skullX = x;
			int skullZ = z;

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
			if (world.getBlockId(skullX, y, skullZ) == Block.skull.blockID) {
				float offX = rand.nextFloat() * 0.6F - 0.3F;
				float offY = rand.nextFloat() * 6.0F / 16.0F;
				float offZ = rand.nextFloat() * 0.6F - 0.3F;

				world.spawnParticle("smoke", skullX + 0.5F + offX, y + offY, skullZ + 0.5F + offZ, 0.0D, 0.0D, 0.0D);
				world.spawnParticle("flame", skullX + 0.5F + offX, y + offY, skullZ + 0.5F + offZ, 0.0D, 0.0D, 0.0D);
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister reg) {
		disconnected = reg.registerIcon("pumpkin_stem_disconnected");
		connected = reg.registerIcon(Utils.getBlockTexture(Strings.WITHER_SHRUB_NAME));
	}
}