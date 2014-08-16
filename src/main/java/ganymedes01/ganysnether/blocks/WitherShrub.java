package ganymedes01.ganysnether.blocks;

import ganymedes01.ganysnether.ModItems;
import ganymedes01.ganysnether.core.utils.InventoryUtils;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.RenderIDs;
import ganymedes01.ganysnether.lib.Strings;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.IIcon;
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
	private IIcon disconnected, connected;

	public WitherShrub() {
		setHardness(0.0F);
		setTickRandomly(true);
		setBlockBounds(0.375F, 0.0F, 0.375F, 0.625F, 0.25F, 0.625F);
		setBlockName(Utils.getUnlocalizedName(Strings.Blocks.WITHER_SHRUB_NAME));
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		if (world.isRemote)
			return;
		if (canPlaceBlockOn(world.getBlock(x, y - 1, z)))
			if (rand.nextInt(40) == 0) {
				int meta = world.getBlockMetadata(x, y, z);

				if (meta < 7)
					world.setBlockMetadataWithNotify(x, y, z, ++meta, 2);
				else
					growSkull(world, x, y, z, rand);
			}
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbour) {
		super.onNeighborBlockChange(world, x, y, z, neighbour);
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
			if (world.getBlock(skullX, y, skullZ) == Blocks.skull)
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
		boolean flag = false;
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
			if (world.getBlock(skullX, y, skullZ) == Blocks.skull) {
				TileEntitySkull tile = Utils.getTileEntity(world, skullX, y, skullZ, TileEntitySkull.class);
				if (tile != null)
					if (tile.func_145904_a() == 1) {
						InventoryUtils.dropStack(world, skullX, y, skullZ, new ItemStack(Items.skull, 1, 1));
						world.playAuxSFXAtEntity(null, 2001, skullX, y, skullZ, Block.getIdFromBlock(Blocks.skull) + (world.getBlockMetadata(skullX, y, skullZ) << 12));
						world.setBlockToAir(skullX, y, skullZ);
						flag = true;
					}
			}
		}
		return flag;
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
				world.setBlock(skullX, y, skullZ, Blocks.skull, direction + 2, 2);
				TileEntitySkull tile = Utils.getTileEntity(world, skullX, y, skullZ, TileEntitySkull.class);
				if (tile != null) {
					tile.func_152107_a(1);
					world.notifyBlockChange(skullX, y, skullZ, this);
				}
				return;
			}
		}
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
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
	public Item getItem(World world, int x, int y, int z) {
		return ModItems.witherShrubSeeds;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return meta >= 7 ? connected : disconnected;
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
			if (world.getBlock(skullX, y, skullZ) == Blocks.skull) {
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
	public void registerBlockIcons(IIconRegister reg) {
		disconnected = reg.registerIcon("pumpkin_stem_disconnected");
		connected = reg.registerIcon(Utils.getBlockTexture(Strings.Blocks.WITHER_SHRUB_NAME));
	}
}