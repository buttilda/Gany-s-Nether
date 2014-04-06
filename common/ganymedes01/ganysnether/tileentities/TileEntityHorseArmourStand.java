package ganymedes01.ganysnether.tileentities;

import ganymedes01.ganysnether.blocks.ModBlocks;
import ganymedes01.ganysnether.network.PacketTypeHandler;
import ganymedes01.ganysnether.network.packet.PacketTileHorseArmourStand;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class TileEntityHorseArmourStand extends TileEntity {

	private byte armourType = -1;
	private byte rotation = -1;

	public byte getArmourType() {
		return armourType;
	}

	public byte getRotation() {
		return rotation;
	}

	public void setRotation(byte rotation) {
		this.rotation = rotation;
		if (!worldObj.isRemote)
			worldObj.addBlockEvent(xCoord, yCoord, zCoord, ModBlocks.horseArmourStand.blockID, 1, rotation);
	}

	public void setArmourType(byte armourType) {
		this.armourType = armourType;
		if (!worldObj.isRemote)
			worldObj.addBlockEvent(xCoord, yCoord, zCoord, ModBlocks.horseArmourStand.blockID, 0, armourType);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getRenderBoundingBox() {
		return AxisAlignedBB.getAABBPool().getAABB(xCoord - 2, yCoord, zCoord - 2, xCoord + 2, yCoord + 2, zCoord + 2);
	}

	@Override
	public boolean receiveClientEvent(int eventId, int eventData) {
		switch (eventId) {
			case 0:
				armourType = (byte) eventData;
				return true;
			case 1:
				rotation = (byte) eventData;
				return true;
			default:
				return false;
		}
	}

	@Override
	public boolean canUpdate() {
		return false;
	}

	@Override
	public Packet getDescriptionPacket() {
		return PacketTypeHandler.populatePacket(new PacketTileHorseArmourStand(xCoord, yCoord, zCoord, armourType, rotation));
	}

	@Override
	public void readFromNBT(NBTTagCompound data) {
		super.readFromNBT(data);
		armourType = data.getByte("armourType");
		rotation = data.getByte("rotation");
	}

	@Override
	public void writeToNBT(NBTTagCompound data) {
		super.writeToNBT(data);
		data.setByte("armourType", armourType);
		data.setByte("rotation", rotation);
	}
}