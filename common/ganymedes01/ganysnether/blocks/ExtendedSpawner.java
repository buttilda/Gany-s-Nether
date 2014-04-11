package ganymedes01.ganysnether.blocks;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.items.ModItems;
import ganymedes01.ganysnether.items.SpawnerUpgrade.Upgrade;
import ganymedes01.ganysnether.lib.ModIDs;
import ganymedes01.ganysnether.lib.Strings;
import ganymedes01.ganysnether.tileentities.TileEntityExtendedSpawner;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockMobSpawner;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class ExtendedSpawner extends BlockMobSpawner {

	public ExtendedSpawner() {
		super(ModIDs.EXTENDED_SPAWNER_ID);
		disableStats();
		setHardness(5.0F);
		setStepSound(soundMetalFootstep);
		setCreativeTab(GanysNether.netherTab);
		setTextureName(Utils.getBlockTexture(Strings.Blocks.EXTENDED_SPAWNER_NAME));
		setUnlocalizedName(Utils.getUnlocalizedName(Strings.Blocks.EXTENDED_SPAWNER_NAME));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess world, int x, int y, int z) {
		TileEntity tile = world.getBlockTileEntity(x, y, z);
		if (tile instanceof TileEntityExtendedSpawner)
			return Upgrade.values()[((TileEntityExtendedSpawner) tile).logic.tier].getColour();
		return super.colorMultiplier(world, x, y, z);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderColor(int meta) {
		return Upgrade.tierCoal.getColour();
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitz) {
		if (!world.isRemote)
			if (player.isSneaking()) {
				TileEntity tile = world.getBlockTileEntity(x, y, z);
				if (tile instanceof TileEntityExtendedSpawner)
					for (String s : ((TileEntityExtendedSpawner) tile).getUpgradeList())
						player.addChatMessage(s);
				return true;
			}
		return false;
	}

	@Override
	public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player) {
		if (world.isRemote)
			return;
		ItemStack stack = player.getCurrentEquippedItem();
		if (stack == null)
			return;
		if (stack.itemID == Item.monsterPlacer.itemID || stack.itemID == ModItems.skeletonSpawner.itemID) {
			TileEntity tile = world.getBlockTileEntity(x, y, z);
			if (tile instanceof TileEntityExtendedSpawner) {
				TileEntityExtendedSpawner spawner = (TileEntityExtendedSpawner) tile;
				if (spawner.logic.addEgg(stack)) {
					stack.stackSize--;
					if (stack.stackSize <= 0)
						player.setCurrentItemOrArmor(0, null);
				}
			}
		}
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityExtendedSpawner();
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int neighbourID) {
		TileEntity tile = world.getBlockTileEntity(x, y, z);
		if (tile instanceof TileEntityExtendedSpawner) {
			TileEntityExtendedSpawner spawner = (TileEntityExtendedSpawner) tile;
			spawner.logic.isBlockPowered = world.isBlockIndirectlyGettingPowered(x, y, z);
			PacketDispatcher.sendPacketToAllPlayers(spawner.getDescriptionPacket());
		}
	}

	@Override
	public int getExpDrop(World world, int meta, int fortune) {
		return meta == 0 ? 15 + world.rand.nextInt(15) + world.rand.nextInt(15) : 0;
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, int id, int meta) {
		if (!world.isRemote) {
			TileEntity tile = world.getBlockTileEntity(x, y, z);
			if (tile instanceof TileEntityExtendedSpawner) {
				TileEntityExtendedSpawner spawner = (TileEntityExtendedSpawner) tile;
				for (ItemStack stack : spawner.getUpgrades())
					Utils.dropStack(world, x, y, z, stack);
			}
		}
		super.breakBlock(world, x, y, z, id, meta);
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack stack) {
		if (stack.hasTagCompound() && stack.getTagCompound().hasKey("EntityId")) {
			TileEntity tile = world.getBlockTileEntity(x, y, z);
			if (tile instanceof TileEntityExtendedSpawner) {
				TileEntityExtendedSpawner spawner = (TileEntityExtendedSpawner) tile;
				spawner.logic.setMobID(stack.getTagCompound().getString("EntityId"));
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
		TileEntity tile = world.getBlockTileEntity(x, y, z);
		if (tile instanceof TileEntityExtendedSpawner) {
			TileEntityExtendedSpawner spawner = (TileEntityExtendedSpawner) tile;
			if (spawner.logic.tier == Upgrade.tierDragonEgg.ordinal())
				Block.enderChest.randomDisplayTick(world, x, y, z, rand);
		}
	}
}