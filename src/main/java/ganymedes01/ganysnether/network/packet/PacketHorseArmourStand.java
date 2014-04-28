package ganymedes01.ganysnether.network.packet;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.network.PacketTypeHandler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class PacketHorseArmourStand extends CustomPacket {

	private int x, y, z;
	private byte type, rotation;

	public PacketHorseArmourStand() {
		super(PacketTypeHandler.HORSE_ARMOUR_STAND);
	}

	public PacketHorseArmourStand(int x, int y, int z, byte type, byte rotation) {
		super(PacketTypeHandler.HORSE_ARMOUR_STAND);
		this.x = x;
		this.y = y;
		this.z = z;

		this.type = type;
		this.rotation = rotation;
	}

	@Override
	public void writeData(DataOutputStream data) throws IOException {
		data.writeInt(x);
		data.writeInt(y);
		data.writeInt(z);

		data.writeByte(type);
		data.writeByte(rotation);
	}

	@Override
	public void readData(DataInputStream data) throws IOException {
		x = data.readInt();
		y = data.readInt();
		z = data.readInt();

		type = data.readByte();
		rotation = data.readByte();
	}

	@Override
	public void execute() {
		GanysNether.proxy.handleHorseArmourStandPacket(x, y, z, type, rotation);
	}
}