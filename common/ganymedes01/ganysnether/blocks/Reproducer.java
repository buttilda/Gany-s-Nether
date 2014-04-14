package ganymedes01.ganysnether.blocks;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.GUIsID;
import ganymedes01.ganysnether.lib.ModIDs;
import ganymedes01.ganysnether.lib.Strings;
import ganymedes01.ganysnether.tileentities.TileEntityReproducer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class Reproducer extends InventoryBlock {

	@SideOnly(Side.CLIENT)
	private Icon blockSide, blockBottom, blockTop;

	Reproducer() {
		super(ModIDs.REPRODUCER_ID, Material.rock);
		setHardness(2.5F);
		setStepSound(soundStoneFootstep);
		setCreativeTab(GanysNether.netherTab);
		setUnlocalizedName(Utils.getUnlocalizedName(Strings.Blocks.REPRODUCER_NAME));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta) {
		return side == 0 ? blockBottom : side == 1 ? blockTop : blockSide;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister reg) {
		blockTop = reg.registerIcon(Utils.getBlockTexture(Strings.Blocks.REPRODUCER_NAME) + "_top");
		blockSide = reg.registerIcon(Utils.getBlockTexture(Strings.Blocks.REPRODUCER_NAME) + "_side");
		blockBottom = reg.registerIcon(Utils.getBlockTexture(Strings.Blocks.REPRODUCER_NAME) + "_bottom");
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (world.isRemote)
			return true;
		if (player.isSneaking())
			return false;
		else {
			TileEntityReproducer tileReproducer = (TileEntityReproducer) world.getBlockTileEntity(x, y, z);
			if (tileReproducer != null)
				player.openGui(GanysNether.instance, GUIsID.REPRODUCER, world, x, y, z);
			return true;
		}
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityReproducer();
	}

	@Override
	public boolean hasComparatorInputOverride() {
		return true;
	}

	@Override
	public int getComparatorInputOverride(World world, int x, int y, int z, int side) {
		return Container.calcRedstoneFromInventory((IInventory) world.getBlockTileEntity(x, y, z));
	}
}