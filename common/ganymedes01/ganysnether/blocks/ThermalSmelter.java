package ganymedes01.ganysnether.blocks;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.GUIsID;
import ganymedes01.ganysnether.lib.ModIDs;
import ganymedes01.ganysnether.lib.Strings;
import ganymedes01.ganysnether.tileentities.TileEntityThermalSmelter;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class ThermalSmelter extends BlockContainer {

	@SideOnly(Side.CLIENT)
	private Icon[] icons;

	public ThermalSmelter() {
		super(ModIDs.THERMAL_SMELTER_ID, Material.rock);
		setHardness(2.5F);
		setStepSound(soundStoneFootstep);
		setCreativeTab(GanysNether.netherTab);
		setUnlocalizedName(Utils.getUnlocalizedName(Strings.THERMAL_SMELTER_NAME));
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityThermalSmelter();
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		super.onBlockAdded(world, x, y, z);
		setDefaultDirection(world, x, y, z);
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack stack) {
		int angle = MathHelper.floor_double(player.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

		if (angle == 0)
			world.setBlockMetadataWithNotify(x, y, z, 2, 2);
		if (angle == 1)
			world.setBlockMetadataWithNotify(x, y, z, 5, 2);
		if (angle == 2)
			world.setBlockMetadataWithNotify(x, y, z, 3, 2);
		if (angle == 3)
			world.setBlockMetadataWithNotify(x, y, z, 4, 2);
	}

	private void setDefaultDirection(World world, int x, int y, int z) {
		if (!world.isRemote) {
			int l = world.getBlockId(x, y, z - 1);
			int i1 = world.getBlockId(x, y, z + 1);
			int j1 = world.getBlockId(x - 1, y, z);
			int k1 = world.getBlockId(x + 1, y, z);
			byte dir = 3;

			if (Block.opaqueCubeLookup[i1] && !Block.opaqueCubeLookup[l])
				dir = 2;
			if (Block.opaqueCubeLookup[j1] && !Block.opaqueCubeLookup[k1])
				dir = 5;
			if (Block.opaqueCubeLookup[k1] && !Block.opaqueCubeLookup[j1])
				dir = 4;

			world.setBlockMetadataWithNotify(x, y, z, dir, 2);
		}
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (world.isRemote)
			return true;
		if (player.isSneaking())
			return false;
		else {
			TileEntityThermalSmelter tile = (TileEntityThermalSmelter) world.getBlockTileEntity(x, y, z);
			if (tile != null)
				player.openGui(GanysNether.instance, GUIsID.THERMAL_SMELTER, world, x, y, z);
			return true;
		}
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, int par5, int par6) {
		TileEntityThermalSmelter tile = (TileEntityThermalSmelter) world.getBlockTileEntity(x, y, z);
		if (tile != null) {
			for (int i = 0; i < tile.getSizeInventory(); i++) {
				ItemStack stack = tile.getStackInSlot(i);
				if (stack != null)
					Utils.dropStack(world, x, y, z, stack);
			}
			world.func_96440_m(x, y, z, par5);
		}
		super.breakBlock(world, x, y, z, par5, par6);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta) {
		return side <= 1 ? icons[2] : side == meta ? icons[0] : icons[1];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister reg) {
		icons = new Icon[3];
		for (int i = 0; i < 3; i++)
			icons[i] = reg.registerIcon(Utils.getBlockTexture(Strings.THERMAL_SMELTER_NAME) + i);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
		int l = world.getBlockMetadata(x, y, z);
		float f = x + 0.5F;
		float f1 = y + 0.0F + rand.nextFloat() * 6.0F / 16.0F;
		float f2 = z + 0.5F;
		float f3 = 0.52F;
		float f4 = rand.nextFloat() * 0.6F - 0.3F;

		if (l == 4) {
			world.spawnParticle("smoke", f - f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
			world.spawnParticle("flame", f - f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
		} else if (l == 5) {
			world.spawnParticle("smoke", f + f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
			world.spawnParticle("flame", f + f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
		} else if (l == 2) {
			world.spawnParticle("smoke", f + f4, f1, f2 - f3, 0.0D, 0.0D, 0.0D);
			world.spawnParticle("flame", f + f4, f1, f2 - f3, 0.0D, 0.0D, 0.0D);
		} else if (l == 3) {
			world.spawnParticle("smoke", f + f4, f1, f2 + f3, 0.0D, 0.0D, 0.0D);
			world.spawnParticle("flame", f + f4, f1, f2 + f3, 0.0D, 0.0D, 0.0D);
		}
	}
}