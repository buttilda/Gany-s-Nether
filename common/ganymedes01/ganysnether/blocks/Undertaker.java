package ganymedes01.ganysnether.blocks;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.GUIsID;
import ganymedes01.ganysnether.lib.ModIDs;
import ganymedes01.ganysnether.lib.Strings;
import ganymedes01.ganysnether.tileentities.TileEntityUndertaker;

import java.util.Random;

import net.minecraft.block.BlockChest;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class Undertaker extends SoulChest {

	public Undertaker() {
		super(ModIDs.UNDERTAKER_ID);
		if (GanysNether.enableUndertaker)
			setCreativeTab(GanysNether.netherTab);
		setUnlocalizedName(Utils.getUnlocalizedName(Strings.UNDERTAKER_NAME));
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {

	}

	@Override
	public int idDropped(int id, Random rand, int fortune) {
		return 0;
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (world.isRemote)
			return true;
		if (player.isSneaking())
			return false;
		else {
			TileEntityUndertaker tileUndertaker = (TileEntityUndertaker) world.getBlockTileEntity(x, y, z);
			if (tileUndertaker != null && !BlockChest.isOcelotBlockingChest(world, x, y, z))
				player.openGui(GanysNether.instance, GUIsID.UNDERTAKER, world, x, y, z);
			return true;
		}
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityUndertaker();
	}
}