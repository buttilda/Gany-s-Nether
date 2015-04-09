package ganymedes01.ganysnether.blocks;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.core.utils.InventoryUtils;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.items.SpawnerUpgrade.UpgradeType;
import ganymedes01.ganysnether.lib.RenderIDs;
import ganymedes01.ganysnether.lib.Strings;
import ganymedes01.ganysnether.network.PacketHandler;
import ganymedes01.ganysnether.tileentities.TileEntityExtendedSpawner;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockMobSpawner;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
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
		disableStats();
		setHardness(5.0F);
		setStepSound(soundTypeMetal);
		setCreativeTab(GanysNether.enableSpawners ? GanysNether.netherTab : null);
		setBlockName(Utils.getUnlocalisedName(Strings.Blocks.EXTENDED_SPAWNER_NAME));
		setBlockTextureName(Utils.getBlockTexture(Strings.Blocks.EXTENDED_SPAWNER_NAME));
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitz) {
		if (!world.isRemote)
			if (player.isSneaking()) {
				TileEntityExtendedSpawner tile = Utils.getTileEntity(world, x, y, z, TileEntityExtendedSpawner.class);
				if (tile != null)
					for (String s : tile.getUpgradeList())
						Utils.sendMessageToPlayer(player, s);
				return true;
			}
		return false;
	}

	private boolean isMobEgg(ItemStack stack) {
		int mobEgg = OreDictionary.getOreID("mobEgg");
		for (int id : OreDictionary.getOreIDs(stack))
			if (id == mobEgg)
				return true;
		return false;
	}

	@Override
	public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player) {
		if (world.isRemote)
			return;
		ItemStack stack = player.getCurrentEquippedItem();
		if (stack == null)
			return;
		if (isMobEgg(stack)) {
			TileEntityExtendedSpawner spawner = Utils.getTileEntity(world, x, y, z, TileEntityExtendedSpawner.class);
			if (spawner != null)
				if (spawner.logic.addEgg(stack)) {
					stack.stackSize--;
					if (stack.stackSize <= 0)
						player.setCurrentItemOrArmor(0, null);
				}
		}
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityExtendedSpawner();
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbour) {
		TileEntityExtendedSpawner spawner = Utils.getTileEntity(world, x, y, z, TileEntityExtendedSpawner.class);
		if (spawner != null) {
			spawner.logic.isBlockPowered = world.isBlockIndirectlyGettingPowered(x, y, z);
			PacketHandler.sendToAll(spawner.getPacket());
		}
	}

	@Override
	public int getExpDrop(IBlockAccess world, int meta, int fortune) {
		Random rand = new Random();
		return meta == 0 ? 15 + rand.nextInt(15) + rand.nextInt(15) : 0;
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
		if (!world.isRemote) {
			TileEntityExtendedSpawner spawner = Utils.getTileEntity(world, x, y, z, TileEntityExtendedSpawner.class);
			if (spawner != null)
				for (ItemStack stack : spawner.getUpgrades())
					InventoryUtils.dropStack(world, x, y, z, stack);
		}
		super.breakBlock(world, x, y, z, block, meta);
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack stack) {
		if (stack.hasTagCompound() && stack.getTagCompound().hasKey("EntityId")) {
			TileEntityExtendedSpawner spawner = Utils.getTileEntity(world, x, y, z, TileEntityExtendedSpawner.class);
			if (spawner != null)
				spawner.logic.setEntityName(stack.getTagCompound().getString("EntityId"));
		}
	}

	@Override
	public int getRenderType() {
		return RenderIDs.EXTENDED_SPAWNER;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess world, int x, int y, int z) {
		TileEntityExtendedSpawner spawner = Utils.getTileEntity(world, x, y, z, TileEntityExtendedSpawner.class);
		if (spawner != null)
			return UpgradeType.values()[spawner.logic.tier].getColour();
		return super.colorMultiplier(world, x, y, z);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderColor(int meta) {
		return UpgradeType.tierCoal.getColour();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
		TileEntityExtendedSpawner spawner = Utils.getTileEntity(world, x, y, z, TileEntityExtendedSpawner.class);
		if (spawner != null)
			if (spawner.logic.tier == UpgradeType.tierDragonEgg.ordinal())
				Blocks.ender_chest.randomDisplayTick(world, x, y, z, rand);
	}
}