package ganymedes01.ganysnether.tileentities;

import ganymedes01.ganysnether.blocks.ModBlocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
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
			worldObj.addBlockEvent(xCoord, yCoord, zCoord, ModBlocks.horseArmourStand, 1, rotation);
	}

	public void setArmourType(byte armourType) {
		this.armourType = armourType;
		if (!worldObj.isRemote)
			worldObj.addBlockEvent(xCoord, yCoord, zCoord, ModBlocks.horseArmourStand, 0, armourType);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getRenderBoundingBox() {
		return AxisAlignedBB.getBoundingBox(xCoord - 2, yCoord, zCoord - 2, xCoord + 2, yCoord + 2, zCoord + 2);
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
		NBTTagCompound nbt = new NBTTagCompound();
		writeToNBT(nbt);
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 0, nbt);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		if (pkt.func_148853_f() == 0)
			readFromNBT(pkt.func_148857_g());
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		armourType = nbt.getByte("armourType");
		rotation = nbt.getByte("rotation");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setByte("armourType", armourType);
		nbt.setByte("rotation", rotation);
	}
}