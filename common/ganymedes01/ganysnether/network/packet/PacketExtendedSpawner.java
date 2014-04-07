package ganymedes01.ganysnether.network.packet;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.network.PacketTypeHandler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class PacketExtendedSpawner extends CustomPacket {

	private int x, y, z;
	private NBTTagCompound nbt;

	public PacketExtendedSpawner() {
		super(PacketTypeHandler.EXTENDED_SPAWNER);
	}

	public PacketExtendedSpawner(int x, int y, int z, NBTTagCompound nbt) {
		super(PacketTypeHandler.EXTENDED_SPAWNER);
		this.x = x;
		this.y = y;
		this.z = z;

		this.nbt = nbt;
	}

	@Override
	public void writeData(DataOutputStream data) throws IOException {
		data.writeInt(x);
		data.writeInt(y);
		data.writeInt(z);

		PacketTypeHandler.writeNBTTagCompound(nbt, data);
	}

	@Override
	public void readData(DataInputStream data) throws IOException {
		x = data.readInt();
		y = data.readInt();
		z = data.readInt();

		nbt = Packet.readNBTTagCompound(data);
	}

	@Override
	public void execute() {
		GanysNether.proxy.handleExtendedSpawnerPacket(x, y, z, nbt);
	}
}