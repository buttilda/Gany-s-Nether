package ganymedes01.ganysnether.network.packet;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.network.PacketTypeHandler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class PacketTileMagmaticCentrifuge extends CustomPacket {

	private int x, y, z;
	private ItemStack material1, material2;
	private boolean isRecipeValid;

	public PacketTileMagmaticCentrifuge() {
		super(PacketTypeHandler.TILE_MAGMATIC_CENTRIFUGE);
	}

	public PacketTileMagmaticCentrifuge(int x, int y, int z, ItemStack material1, ItemStack material2, boolean isRecipeValid) {
		super(PacketTypeHandler.TILE_MAGMATIC_CENTRIFUGE);
		this.x = x;
		this.y = y;
		this.z = z;

		this.material1 = material1;
		this.material2 = material2;

		this.isRecipeValid = isRecipeValid;
	}

	@Override
	public void writeData(DataOutputStream data) throws IOException {
		data.writeInt(x);
		data.writeInt(y);
		data.writeInt(z);

		Packet.writeItemStack(material1, data);
		Packet.writeItemStack(material2, data);

		data.writeBoolean(isRecipeValid);
	}

	@Override
	public void readData(DataInputStream data) throws IOException {
		x = data.readInt();
		y = data.readInt();
		z = data.readInt();

		material1 = Packet.readItemStack(data);
		material2 = Packet.readItemStack(data);

		isRecipeValid = data.readBoolean();
	}

	@Override
	public void execute() {
		GanysNether.proxy.handleTileMagmaticCentrifugePacket(x, y, z, material1, material2, isRecipeValid);
	}
}