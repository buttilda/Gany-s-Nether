package ganymedes01.ganysnether.items;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.blocks.ModBlocks;
import ganymedes01.ganysnether.core.utils.Utils;
import ganymedes01.ganysnether.lib.ModIDs;
import ganymedes01.ganysnether.lib.Strings;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
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

public class SpawnerUpgrade extends Item {

	public SpawnerUpgrade() {
		super(ModIDs.SPAWNER_UPGRADE_ID);
		setMaxDamage(0);
		setHasSubtypes(true);
		setCreativeTab(GanysNether.netherTab);
		setTextureName(Utils.getItemTexture(Strings.SPAWNER_UPGRADE_NAME));
		setUnlocalizedName(Utils.getUnlocalizedName(Strings.SPAWNER_UPGRADE_NAME));
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if (world.isRemote)
			return true;
		int id = world.getBlockId(x, y, z);
		int meta = stack.getItemDamage();

		if (id == Block.mobSpawner.blockID) {
			if (meta != 0)
				return false;
			NBTTagCompound data = new NBTTagCompound();
			world.getBlockTileEntity(x, y, z).writeToNBT(data);
			world.setBlock(x, y, z, ModBlocks.extendedSpawner.blockID);
			TileEntity tile = world.getBlockTileEntity(x, y, z);
			tile.readFromNBT(data);
			PacketDispatcher.sendPacketToAllPlayers(tile.getDescriptionPacket());
			useItem(stack, player);
			return true;
		}
		if (id == ModBlocks.extendedSpawner.blockID)
			if (meta == 0)
				return false;
		return false;
	}

	private void useItem(ItemStack stack, EntityPlayer player) {
		if (player.capabilities.isCreativeMode)
			return;
		stack.stackSize--;
		if (stack.stackSize <= 0)
			stack = null;
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return super.getUnlocalizedName() + "_" + stack.getItemDamage();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack stack, int pass) {
		return super.getColorFromItemStack(stack, pass);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(int id, CreativeTabs tabs, List list) {
		for (int i = 0; i < 9; i++)
			list.add(new ItemStack(itemID, 1, i));
	}
}