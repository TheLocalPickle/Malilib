package fi.dy.masa.malilib.network.packet;

import fi.dy.masa.malilib.MaLiLib;
import fi.dy.masa.malilib.event.SyncmaticaPayloadHandler;

public class PacketProvider
{
    static SyncmaticaPayloadListener syncmaticaListener = new SyncmaticaPayloadListener();
    private static boolean payloadsRegistered = false;
    public static void registerPayloads()
    {
        if (payloadsRegistered)
            return;
        MaLiLib.printDebug("PacketProvider#registerPayloads(): registerSyncmaticaHandler()");
        SyncmaticaPayloadHandler.getInstance().registerSyncmaticaHandler(syncmaticaListener);

        payloadsRegistered = true;
    }
    public static void unregisterPayloads()
    {
        MaLiLib.printDebug("PacketProvider#unregisterPayloads(): unregisterSyncmaticaHandler()");
        SyncmaticaPayloadHandler.getInstance().unregisterSyncmaticaHandler(syncmaticaListener);

        payloadsRegistered = false;
    }
}
