package ganymedes01.ganysnether.tileentities;

import ganymedes01.ganysnether.blocks.ModBlocks;
import ganymedes01.ganysnether.items.ModItems;
import ganymedes01.ganysnether.items.SpawnerUpgrade.Upgrade;
import ganymedes01.ganysnether.network.PacketTypeHandler;
import ganymedes01.ganysnether.network.packet.PacketExtendedSpawner;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumChatFormatting;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class TileEntityExtendedSpawner extends TileEntity {

	public final ExtendedSpawnerLogic logic = new ExtendedSpawnerLogic(this);

	public ItemStack[] getUpgrades() {
		ArrayList<ItemStack> upgrades = new ArrayList<ItemStack>();
		if (logic.redstoneUpgrade)
			upgrades.add(new ItemStack(ModItems.spawnerUpgrade, 1, Upgrade.redstone.ordinal()));
		if (logic.noPlayerUpgrade)
			upgrades.add(new ItemStack(ModItems.spawnerUpgrade, 1, Upgrade.noPlayer.ordinal()));
		if (logic.ignoreConditionsUpgrade)
			upgrades.add(new ItemStack(ModItems.spawnerUpgrade, 1, Upgrade.ignoreConditions.ordinal()));
		if (logic.silkyUpgrade) {
			ItemStack stack = new ItemStack(ModBlocks.extendedSpawner);
			stack.stackTagCompound = new NBTTagCompound();
			stack.stackTagCompound.setString("EntityId", logic.getEntityNameToSpawn());
			upgrades.add(stack);
			upgrades.add(new ItemStack(ModItems.spawnerUpgrade, 1, Upgrade.silky.ordinal()));
		}
		for (int i = 0; i < logic.getSpawnCountUpgradeCount(); i++)
			upgrades.add(new ItemStack(ModItems.spawnerUpgrade, 1, Upgrade.spawnCount.ordinal()));
		for (int i = 0; i < logic.getSpawnRangeUpgradeCount(); i++)
			upgrades.add(new ItemStack(ModItems.spawnerUpgrade, 1, Upgrade.spawnRange.ordinal()));
		for (int i = 1; i <= logic.tier; i++)
			upgrades.add(new ItemStack(ModItems.spawnerUpgrade, 1, i));

		return upgrades.toArray(new ItemStack[0]);
	}

	public String[] getUpgradeList() {
		ArrayList<String> list = new ArrayList<String>();
		list.add(EnumChatFormatting.BOLD + "Tier: " + EnumChatFormatting.RESET + logic.tier);
		int slots = getSlots();
		list.add(EnumChatFormatting.BOLD + "Slots: " + EnumChatFormatting.RESET + slots + " (" + (slots - getSlotsUsed()) + " available)");
		list.add("");

		list.add(EnumChatFormatting.ITALIC + "Spawn Quantity: " + EnumChatFormatting.RESET + logic.spawnCount);
		AxisAlignedBB area = AxisAlignedBB.getAABBPool().getAABB(0, 0, 0, 1, 1, 1).expand(logic.spawnRange * 2, 4.0D, logic.spawnRange * 2);
		list.add(EnumChatFormatting.ITALIC + "Range: " + EnumChatFormatting.RESET + logic.spawnRange + " (" + (int) (area.maxX - area.minX) + ", " + (int) (area.maxY - area.minY) + ", " + (int) (area.maxZ - area.minZ) + ")");

		if (logic.redstoneUpgrade)
			list.add(EnumChatFormatting.ITALIC + "Redstone controlled" + EnumChatFormatting.RESET);
		if (logic.noPlayerUpgrade)
			list.add(EnumChatFormatting.ITALIC + "Autonomous" + EnumChatFormatting.RESET);
		if (logic.ignoreConditionsUpgrade)
			list.add(EnumChatFormatting.ITALIC + "Ignores Spawn Conditions" + EnumChatFormatting.RESET);
		if (logic.silkyUpgrade)
			list.add(EnumChatFormatting.ITALIC + "Silky" + EnumChatFormatting.RESET);

		list.add("");
		return list.toArray(new String[0]);
	}

	public int getSlots() {
		return logic.tier == 0 ? 0 : (int) Math.pow(2, logic.tier);
	}

	public int getSlotsUsed() {
		int count = 0;
		if (logic.redstoneUpgrade)
			count++;
		if (logic.noPlayerUpgrade)
			count++;
		if (logic.ignoreConditionsUpgrade)
			count++;
		if (logic.silkyUpgrade)
			count++;
		count += logic.getSpawnCountUpgradeCount();
		count += logic.getSpawnRangeUpgradeCount();

		return count;
	}

	@Override
	public void updateEntity() {
		logic.updateSpawner();
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound data = new NBTTagCompound();
		writeToNBT(data);
		data.removeTag("SpawnPotentials");
		return PacketTypeHandler.populatePacket(new PacketExtendedSpawner(xCoord, yCoord, zCoord, data));
	}

	@Override
	public boolean receiveClientEvent(int eventId, int data) {
		return logic.setDelayToMin(eventId) ? true : super.receiveClientEvent(eventId, data);
	}

	@Override
	public void readFromNBT(NBTTagCompound data) {
		super.readFromNBT(data);
		logic.readFromNBT(data);
	}

	@Override
	public void writeToNBT(NBTTagCompound data) {
		super.writeToNBT(data);
		logic.writeToNBT(data);
	}
}