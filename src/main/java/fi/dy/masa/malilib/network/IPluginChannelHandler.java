package fi.dy.masa.malilib.network;

import java.util.List;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

public interface IPluginChannelHandler
{
    List<Identifier> getChannels();

    void onPacketReceived(PacketByteBuf buf);

    PacketByteBuf onPacketSend();
}
