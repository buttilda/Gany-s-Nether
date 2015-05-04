package ganymedes01.ganysnether.blocks;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.IConfigurable;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.GUIsID;
import ganymedes01.ganysnether.lib.Strings;
import ganymedes01.ganysnether.tileentities.TileEntityReproducer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Gany's Nether
 *
 * @author ganymedes01
 *
 */

public class Reproducer extends InventoryBlock implements IConfigurable {

	@SideOnly(Side.CLIENT)
	private IIcon blockSide, blockBottom, blockTop;

	public Reproducer() {
		super(Material.rock);
		setHardness(2.5F);
		setStepSound(soundTypeStone);
		setBlockName(Utils.getUnlocalisedName(Strings.Blocks.REPRODUCER_NAME));
		setCreativeTab(GanysNether.enableReproducerAndDrops ? GanysNether.netherTab : null);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return side == 0 ? blockBottom : side == 1 ? blockTop : blockSide;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
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
			TileEntityReproducer tile = Utils.getTileEntity(world, x, y, z, TileEntityReproducer.class);
			if (tile != null)
				player.openGui(GanysNether.instance, GUIsID.REPRODUCER, world, x, y, z);
			return true;
		}
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityReproducer();
	}

	@Override
	public boolean isEnabled() {
		return GanysNether.enableReproducerAndDrops;
	}
}