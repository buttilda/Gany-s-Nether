package ganymedes01.ganysnether.blocks;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.GUIsID;
import ganymedes01.ganysnether.lib.ModIDs;
import ganymedes01.ganysnether.lib.Strings;
import ganymedes01.ganysnether.tileentities.TileEntityVolcanicFurnace;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class VolcanicFurnace extends InventoryBlock {

	@SideOnly(Side.CLIENT)
	private Icon[] icons;

	VolcanicFurnace(boolean isActive) {
		super(isActive ? ModIDs.VOLCANIC_FURNACE_ACTIVE_ID : ModIDs.VOLCANIC_FURNACE_IDLE_ID, Material.rock);
		if (!isActive)
			setCreativeTab(GanysNether.netherTab);
		setHardness(3.5F);
		setStepSound(soundStoneFootstep);
		setUnlocalizedName(Utils.getUnlocalizedName(Strings.Blocks.VOLCANIC_FURNACE_NAME + "_" + isActive));
	}

	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z) {
		TileEntityVolcanicFurnace tile = (TileEntityVolcanicFurnace) world.getBlockTileEntity(x, y, z);
		if (tile == null)
			return 0;
		return tile.hasLava ? 13 : 0;
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

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityVolcanicFurnace();
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
		return side == 0 || side == 1 ? icons[side] : icons[3];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getBlockTexture(IBlockAccess world, int x, int y, int z, int side) {
		switch (side) {
			case 0:
				return icons[0];
			case 1:
				TileEntityVolcanicFurnace tile = (TileEntityVolcanicFurnace) world.getBlockTileEntity(x, y, z);
				return tile != null && tile.hasLava ? icons[2] : icons[1];
			default:
				return icons[3];
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister reg) {
		icons = new Icon[4];

		icons[0] = reg.registerIcon(Utils.getBlockTexture(Strings.Blocks.VOLCANIC_FURNACE_NAME) + "_bottom");
		icons[1] = reg.registerIcon(Utils.getBlockTexture(Strings.Blocks.VOLCANIC_FURNACE_NAME) + "_top_off");
		icons[2] = reg.registerIcon(Utils.getBlockTexture(Strings.Blocks.VOLCANIC_FURNACE_NAME) + "_top_on");
		icons[3] = reg.registerIcon(Utils.getBlockTexture(Strings.Blocks.VOLCANIC_FURNACE_NAME) + "_side");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int idPicked(World world, int x, int y, int z) {
		return ModBlocks.volcanicFurnaceIdle.blockID;
	}
}