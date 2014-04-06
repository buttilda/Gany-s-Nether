package ganymedes01.ganysnether.tileentities;

import ganymedes01.ganysnether.blocks.ModBlocks;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;

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
		if (logic.redstoneUpgrade) {

		}
		if (logic.noPlayerUpgrade) {

		}
		if (logic.ignoreLightUpgrade) {

		}
		if (logic.silkyUpgrade) {
			ItemStack stack = new ItemStack(ModBlocks.extendedSpawner);
			stack.stackTagCompound = new NBTTagCompound();
			stack.stackTagCompound.setString("EntityId", logic.getEntityNameToSpawn());
			upgrades.add(stack);
		}
		for (int i = 0; i < logic.getSpawnCountUpgradeCount(); i++) {

		}
		for (int i = 0; i < logic.getSpawnRangeUpgradeCount(); i++) {

		}

		return upgrades.toArray(new ItemStack[0]);
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
		return new Packet132TileEntityData(xCoord, yCoord, zCoord, 1, data);
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