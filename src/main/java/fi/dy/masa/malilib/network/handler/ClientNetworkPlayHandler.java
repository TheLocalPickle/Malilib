package fi.dy.masa.malilib.network.handler;

import fi.dy.masa.malilib.MaLiLib;
import fi.dy.masa.malilib.event.CarpetHandler;
import fi.dy.masa.malilib.network.payload.*;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.text.Text;

public class ClientNetworkPlayHandler
{
    // String Payload
    public static void send(C2SStringPayload payload)
    {
        // Server-bound packet sent from the Client
        if (ClientPlayNetworking.canSend(payload.getId()))
        {
            ClientPlayNetworking.send(payload);
            MaLiLib.printDebug("C2SStringListener#send(): sending payload id: {}", payload.getId());
        }
    }
    public static void receive(S2CStringPayload payload, ClientPlayNetworking.Context ctx)
    {
        // Client-bound packet received from the Server
        String response = payload.toString();
        MaLiLib.printDebug("S2CStringListener#receive(): received S2CString Payload: {}", response);
        ctx.player().sendMessage(Text.of("Received a message from the server."));
        ctx.player().sendMessage(Text.of("You were sent (STRING): "+response));
    }
    // Data Payload
    public static void send(C2SDataPayload payload)
    {
        // Server-bound packet sent from the Client
        if (ClientPlayNetworking.canSend(payload.getId()))
        {
            ClientPlayNetworking.send(payload);
            MaLiLib.printDebug("C2SDataListener#send(): sending payload id: {}", payload.getId());
        }
    }
    public static void receive(S2CDataPayload payload, ClientPlayNetworking.Context ctx)
    {
        // Client-bound packet received from server
        MaLiLib.printDebug("S2CDataListener#receive(): received S2CData Payload (size in bytes): {}", payload.data().readableBytes());
        MaLiLib.printDebug("S2CDataListener#receive(): id: {}", payload.data().readIdentifier());
        String response = payload.data().readString();
        MaLiLib.printDebug("S2CDataListener#receive(): String: {}", response);
        ctx.player().sendMessage(Text.of("Received a message from the server."));
        ctx.player().sendMessage(Text.of("You were sent (DATA): "+response));
    }
    public static void sendCarpet(CarpetPayload payload)
    {
        // Server-bound packet sent from the Client
        // --> Carpet server present
        if (ClientPlayNetworking.canSend(payload.getId()))
        {
            ClientPlayNetworking.send(payload);
        }
    }
    public static void receiveCarpet(CarpetPayload payload, ClientPlayNetworking.Context ctx)
    {
        // Client-bound packet received from server
        // --> Carpet server present
        MaLiLib.printDebug("S2CDataListener#receiveCarpet(): received Carpet Payload (size in bytes): {}", payload.data().getSizeInBytes());

        // Handle Carpet packet
        ((CarpetHandler) CarpetHandler.getInstance()).onCarpetPayload(payload.data(), ctx);
    }
}
