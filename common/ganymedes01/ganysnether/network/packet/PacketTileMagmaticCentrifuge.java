package ganymedes01.ganysnether.network.packet;

import ganymedes01.ganysnether.GanysNether;
import ganymedes01.ganysnether.network.PacketTypeHandler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.item.ItemStack;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public class PacketTileMagmaticCentrifuge extends CustomPacket {

	private int x, y, z;
	private int itemID1, meta1, stackSize1;
	private int itemID2, meta2, stackSize2;
	private boolean isRecipeValid;

	public PacketTileMagmaticCentrifuge(int x, int y, int z, ItemStack material1, ItemStack material2, boolean isRecipeValid) {
		super(PacketTypeHandler.TILE_MAGMATIC_CENTRIFUGE);
		this.x = x;
		this.y = y;
		this.z = z;

		itemID1 = material1.itemID;
		meta1 = material1.getItemDamage();
		stackSize1 = material1.stackSize;

		itemID2 = material2.itemID;
		meta2 = material2.getItemDamage();
		stackSize2 = material2.stackSize;

		this.isRecipeValid = isRecipeValid;
	}

	@Override
	public void writeData(DataOutputStream data) throws IOException {
		data.writeInt(x);
		data.writeInt(y);
		data.writeInt(z);

		data.writeInt(itemID1);
		data.writeInt(meta1);
		data.writeInt(stackSize1);

		data.writeInt(itemID2);
		data.writeInt(meta2);
		data.writeInt(stackSize2);

		data.writeBoolean(isRecipeValid);
	}

	@Override
	public void readData(DataInputStream data) throws IOException {
		x = data.readInt();
		y = data.readInt();
		z = data.readInt();

		itemID1 = data.readInt();
		meta1 = data.readInt();
		stackSize1 = data.readInt();

		itemID2 = data.readInt();
		meta2 = data.readInt();
		stackSize2 = data.readInt();

		isRecipeValid = data.readBoolean();
	}

	@Override
	public void execute() {
		GanysNether.proxy.handleTileMagmaticCentrifugePacket(x, y, z, new ItemStack(itemID1, meta1, stackSize1), new ItemStack(itemID2, meta2, stackSize2), isRecipeValid);
	}
}