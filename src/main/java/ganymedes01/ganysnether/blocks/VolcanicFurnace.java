package ganymedes01.ganysnether.blocks;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.GUIsID;
import ganymedes01.ganysnether.lib.RenderIDs;
import ganymedes01.ganysnether.lib.Strings;
import ganymedes01.ganysnether.tileentities.TileEntityVolcanicFurnace;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class VolcanicFurnace extends InventoryBlock {

	@SideOnly(Side.CLIENT)
	private IIcon[] icons;

	public VolcanicFurnace() {
		super(Material.rock);
		setHardness(3.5F);
		setStepSound(soundTypeStone);
		setBlockName(Utils.getUnlocalisedName(Strings.Blocks.VOLCANIC_FURNACE_NAME));
		setCreativeTab(GanysNether.enableVolcanicFurnace ? GanysNether.netherTab : null);
	}

	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z) {
		TileEntityVolcanicFurnace tile = Utils.getTileEntity(world, x, y, z, TileEntityVolcanicFurnace.class);
		if (tile == null)
			return 0;
		return tile.hasLava ? 13 : 0;
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (world.isRemote)
			return true;
		if (player.isSneaking())
			return false;
		else {
			TileEntityVolcanicFurnace tile = Utils.getTileEntity(world, x, y, z, TileEntityVolcanicFurnace.class);
			if (tile != null)
				player.openGui(GanysNether.instance, GUIsID.VOLCANIC_FURNACE, world, x, y, z);
			return true;
		}
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbour) {
		TileEntityVolcanicFurnace furnace = Utils.getTileEntity(world, x, y, z, TileEntityVolcanicFurnace.class);
		if (furnace != null)
			furnace.cellCount = -1;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityVolcanicFurnace();
	}

	@Override
	public int getRenderType() {
		return RenderIDs.VOLCANIC_FURNACE;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return side == 0 || side == 1 ? icons[side] : icons[3];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
		switch (side) {
			case 0:
				return icons[0];
			case 1:
				TileEntityVolcanicFurnace tile = Utils.getTileEntity(world, x, y, z, TileEntityVolcanicFurnace.class);
				return tile != null && tile.hasLava ? icons[2] : icons[1];
			default:
				return icons[3];
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
		icons = new IIcon[4];

		icons[0] = reg.registerIcon(Utils.getBlockTexture(Strings.Blocks.VOLCANIC_FURNACE_NAME) + "_bottom");
		icons[1] = reg.registerIcon(Utils.getBlockTexture(Strings.Blocks.VOLCANIC_FURNACE_NAME) + "_top_off");
		icons[2] = reg.registerIcon(Utils.getBlockTexture(Strings.Blocks.VOLCANIC_FURNACE_NAME) + "_top_on");
		icons[3] = reg.registerIcon(Utils.getBlockTexture(Strings.Blocks.VOLCANIC_FURNACE_NAME) + "_side");
	}
}