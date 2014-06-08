package ganymedes01.ganysnether.network;

import ganymedes01.ganysnether.network.packet.PacketTileEntity;
import io.netty.buffer.ByteBuf;

/**
 * Gany's Nether
 * 
 * @author ganymedes01
 * 
 */

public interface IPacketHandlingTile {

	void readPacketData(ByteBuf buffer);

	PacketTileEntity getPacket();
}