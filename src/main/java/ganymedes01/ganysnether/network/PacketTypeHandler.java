package ganymedes01.ganysnether.network;

import ganymedes01.ganysnether.lib.Reference;
import ganymedes01.ganysnether.network.packet.CustomPacket;
import ganymedes01.ganysnether.network.packet.PacketExtendedSpawner;
import ganymedes01.ganysnether.network.packet.PacketHorseArmourStand;
import ganymedes01.ganysnether.network.packet.PacketMagmaticCentrifuge;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.IOException;

import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public enum PacketTypeHandler {

	//@formatter:off
	MAGMATIC_CENTRIFUGE(PacketMagmaticCentrifuge.class),
	HORSE_ARMOUR_STAND(PacketHorseArmourStand.class),
	EXTENDED_SPAWNER(PacketExtendedSpawner.class);
	//@formatter:on

	private Class<? extends CustomPacket> clazz;

	PacketTypeHandler(Class<? extends CustomPacket> clazz) {
		this.clazz = clazz;
	}

	public static CustomPacket buildPacket(byte[] data) {
		ByteArrayInputStream bis = new ByteArrayInputStream(data);
		int selector = bis.read();
		DataInputStream dis = new DataInputStream(bis);

		CustomPacket packet = null;

		try {
			packet = values()[selector].clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}

		packet.readPopulate(dis);

		return packet;
	}

	public static Packet populatePacket(CustomPacket customPacket) {
		byte[] data = customPacket.populate();

		Packet250CustomPayload packet250 = new Packet250CustomPayload();
		packet250.channel = Reference.CHANNEL_NAME;
		packet250.data = data;
		packet250.length = data.length;

		return packet250;
	}

	public static void writeNBTTagCompound(NBTTagCompound nbt, DataOutput data) throws IOException {
		if (nbt == null)
			data.writeShort(-1);
		else {
			byte[] array = CompressedStreamTools.compress(nbt);
			data.writeShort((short) array.length);
			data.write(array);
		}
	}
}