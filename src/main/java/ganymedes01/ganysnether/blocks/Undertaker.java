package ganymedes01.ganysnether.blocks;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.GUIsID;
import ganymedes01.ganysnether.lib.Strings;
import ganymedes01.ganysnether.tileentities.TileEntityUndertaker;

import java.util.Random;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Undertaker extends SoulChest {

	public Undertaker() {
		if (GanysNether.enableUndertaker)
			setCreativeTab(GanysNether.netherTab);
		else
			setCreativeTab(null);
		setResistance(2000.0F);
		setBlockName(Utils.getUnlocalizedName(Strings.Blocks.UNDERTAKER_NAME));
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
	}

	@Override
	public Item getItemDropped(int id, Random rand, int fortune) {
		return null;
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (world.isRemote)
			return true;
		if (player.isSneaking())
			return false;
		else {
			TileEntityUndertaker tile = Utils.getTileEntity(world, x, y, z, TileEntityUndertaker.class);
			if (tile != null)
				player.openGui(GanysNether.instance, GUIsID.UNDERTAKER, world, x, y, z);
			return true;
		}
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityUndertaker();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
		blockIcon = reg.registerIcon(Utils.getBlockTexture(Strings.Blocks.UNDERTAKER_NAME + "_top"));
		sides = reg.registerIcon(Utils.getBlockTexture(Strings.Blocks.UNDERTAKER_NAME + "_side"));
		bottom = reg.registerIcon(Utils.getBlockTexture(Strings.Blocks.UNDERTAKER_NAME + "_bottom"));
	}
}