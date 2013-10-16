package ganymedes01.ganysnether.blocks;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.GUIsID;
import ganymedes01.ganysnether.lib.ModIDs;
import ganymedes01.ganysnether.lib.Strings;
import ganymedes01.ganysnether.tileentities.TileEntityVolcanicFurnace;

import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class VolcanicFurnace extends BlockContainer {

	private final boolean isActive;
	private static boolean keepFurnaceInventory;

	@SideOnly(Side.CLIENT)
	private Icon blockSide, blockBottom, blockTop;

	protected VolcanicFurnace(boolean isActive) {
		super(isActive ? ModIDs.VOLCANIC_FURNACE_ACTIVE_ID : ModIDs.VOLCANIC_FURNACE_IDLE_ID, Material.rock);
		this.isActive = isActive;
		if (!isActive)
			setCreativeTab(GanysNether.netherTab);
		else
			setLightValue(1.0F);
		setHardness(3.5F);
		setStepSound(soundStoneFootstep);
		setUnlocalizedName(Utils.getUnlocalizedName(Strings.VOLCANIC_FURNACE_NAME));
	}

	@Override
	public int idDropped(int id, Random rand, int fortune) {
		return ModBlocks.volcanicFurnaceIdle.blockID;
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (world.isRemote)
			return true;
		if (player.isSneaking())
			return false;
		else {
			TileEntityVolcanicFurnace tileentityfurnace = (TileEntityVolcanicFurnace) world.getBlockTileEntity(x, y, z);
			if (tileentityfurnace != null)
				player.openGui(GanysNether.instance, GUIsID.VOLCANIC_FURNACE, world, x, y, z);
			return true;
		}
	}

	public static void updateFurnaceBlockState(boolean isActive, World world, int x, int y, int z) {
		int l = world.getBlockMetadata(x, y, z);
		TileEntity tileentity = world.getBlockTileEntity(x, y, z);
		keepFurnaceInventory = true;

		if (isActive)
			world.setBlock(x, y, z, ModBlocks.volcanicFurnaceActive.blockID);
		else
			world.setBlock(x, y, z, ModBlocks.volcanicFurnaceIdle.blockID);

		keepFurnaceInventory = false;
		world.setBlockMetadataWithNotify(x, y, z, l, 2);

		if (tileentity != null) {
			tileentity.validate();
			world.setBlockTileEntity(x, y, z, tileentity);
		}
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityVolcanicFurnace();
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, int par5, int par6) {
		if (!keepFurnaceInventory) {
			TileEntityVolcanicFurnace tile = (TileEntityVolcanicFurnace) world.getBlockTileEntity(x, y, z);
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
		super.breakBlock(world, x, y, z, par5, par6);
	}

	@Override
	public boolean hasComparatorInputOverride() {
		return true;
	}

	@Override
	public int getComparatorInputOverride(World world, int x, int y, int z, int side) {
		return Container.calcRedstoneFromInventory((IInventory) world.getBlockTileEntity(x, y, z));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta) {
		return side == 0 ? blockBottom : side == 1 ? blockTop : blockSide;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister reg) {
		blockTop = reg.registerIcon(Utils.getBlockTexture(Strings.VOLCANIC_FURNACE_NAME, true) + "top_" + (isActive ? "on" : "off"));
		blockSide = reg.registerIcon(Utils.getBlockTexture(Strings.VOLCANIC_FURNACE_NAME, true) + "side");
		blockBottom = reg.registerIcon(Utils.getBlockTexture(Strings.VOLCANIC_FURNACE_NAME, true) + "bottom");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int idPicked(World world, int x, int y, int z) {
		return ModBlocks.volcanicFurnaceIdle.blockID;
	}
}
