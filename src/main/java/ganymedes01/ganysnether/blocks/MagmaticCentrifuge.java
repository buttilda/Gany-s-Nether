package ganymedes01.ganysnether.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.IConfigurable;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.GUIsID;
import ganymedes01.ganysnether.lib.Strings;
import ganymedes01.ganysnether.tileentities.TileEntityMagmaticCentrifuge;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Gany's Nether
 *
 * @author ganymedes01
 *
 */

public class MagmaticCentrifuge extends InventoryBlock implements IConfigurable {

	public MagmaticCentrifuge() {
		super(Material.rock);
		setHardness(2.5F);
		setStepSound(soundTypeStone);
		setBlockTextureName("nether_brick");
		setBlockName(Utils.getUnlocalisedName(Strings.Blocks.MAGMATIC_CENTRIFUGE_NAME));
		setCreativeTab(GanysNether.enableMagmaticCentrifuge ? GanysNether.netherTab : null);
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public int getRenderType() {
		return -1;
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (world.isRemote)
			return true;
		if (player.isSneaking())
			return false;
		else {
			TileEntityMagmaticCentrifuge tile = Utils.getTileEntity(world, x, y, z, TileEntityMagmaticCentrifuge.class);
			if (tile != null)
				player.openGui(GanysNether.instance, GUIsID.MAGMATIC_CENTRIFUGE, world, x, y, z);
			return true;
		}
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityMagmaticCentrifuge();
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbour) {
		TileEntityMagmaticCentrifuge tile = Utils.getTileEntity(world, x, y, z, TileEntityMagmaticCentrifuge.class);
		if (tile != null)
			tile.isPowered = world.isBlockIndirectlyGettingPowered(x, y, z);
	}

	@Override
	public boolean isEnabled() {
		return GanysNether.enableMagmaticCentrifuge;
	}
}