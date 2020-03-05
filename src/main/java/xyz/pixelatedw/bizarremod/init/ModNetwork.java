package xyz.pixelatedw.bizarremod.init;

import xyz.pixelatedw.bizarremod.packets.client.CRequestSyncStandDataPacket;
import xyz.pixelatedw.bizarremod.packets.client.CStandControlPacket;
import xyz.pixelatedw.bizarremod.packets.client.CSyncStandDataPacket;
import xyz.pixelatedw.bizarremod.packets.client.CUseAbilityPacket;
import xyz.pixelatedw.bizarremod.packets.server.SOpenScreenPacket;
import xyz.pixelatedw.bizarremod.packets.server.SStandExistencePacket;
import xyz.pixelatedw.bizarremod.packets.server.SSyncStandDataPacket;
import xyz.pixelatedw.bizarremod.packets.server.SUpdateWorldDataPacket;
import xyz.pixelatedw.wypi.APIDefaults;
import xyz.pixelatedw.wypi.network.WyNetwork;

public class ModNetwork
{
	public static void init()
	{
		APIDefaults.initPackets();
		
		// Client
		WyNetwork.registerPacket(CStandControlPacket.class, CStandControlPacket::encode, CStandControlPacket::decode, CStandControlPacket::handle);
		WyNetwork.registerPacket(CRequestSyncStandDataPacket.class, CRequestSyncStandDataPacket::encode, CRequestSyncStandDataPacket::decode, CRequestSyncStandDataPacket::handle);
		WyNetwork.registerPacket(CUseAbilityPacket.class, CUseAbilityPacket::encode, CUseAbilityPacket::decode, CUseAbilityPacket::handle);
		WyNetwork.registerPacket(CSyncStandDataPacket.class, CSyncStandDataPacket::encode, CSyncStandDataPacket::decode, CSyncStandDataPacket::handle);

		// Server
		WyNetwork.registerPacket(SSyncStandDataPacket.class, SSyncStandDataPacket::encode, SSyncStandDataPacket::decode, SSyncStandDataPacket::handle);
		WyNetwork.registerPacket(SStandExistencePacket.class, SStandExistencePacket::encode, SStandExistencePacket::decode, SStandExistencePacket::handle);
		WyNetwork.registerPacket(SOpenScreenPacket.class, SOpenScreenPacket::encode, SOpenScreenPacket::decode, SOpenScreenPacket::handle);
		WyNetwork.registerPacket(SUpdateWorldDataPacket.class, SUpdateWorldDataPacket::encode, SUpdateWorldDataPacket::decode, SUpdateWorldDataPacket::handle);
	}
}
