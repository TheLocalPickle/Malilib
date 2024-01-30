package fi.dy.masa.malilib.listeners;

import fi.dy.masa.malilib.MaLiLib;
import fi.dy.masa.malilib.interfaces.IServerListener;
import fi.dy.masa.malilib.network.ClientNetworkPlayInitHandler;
import fi.dy.masa.malilib.network.packet.PacketProvider;
import fi.dy.masa.malilib.network.test.ClientDebugSuite;
import net.minecraft.server.MinecraftServer;

public class ServerListener implements IServerListener
{
    /**
     * This interface for IntegratedServers() works much more reliably than invoking a WorldLoadHandler
     * @param minecraftServer
     */

    public void onServerStarting(MinecraftServer minecraftServer)
    {
        // Register in case for whatever reason they aren't already
        ClientNetworkPlayInitHandler.registerPlayChannels();
        PacketProvider.registerPayloads();
        ClientDebugSuite.checkGlobalChannels();
        MaLiLib.printDebug("MinecraftServerEvents#onServerStarting(): invoked.");
    }
    public void onServerStarted(MinecraftServer minecraftServer)
    {
        ClientNetworkPlayInitHandler.registerReceivers();
        ClientDebugSuite.checkGlobalChannels();
        MaLiLib.printDebug("MinecraftServerEvents#onServerStarted(): invoked.");
    }
    public void onServerStopping(MinecraftServer minecraftServer)
    {
        ClientNetworkPlayInitHandler.unregisterReceivers();
        ClientDebugSuite.checkGlobalChannels();
        MaLiLib.printDebug("MinecraftServerEvents#onServerStopping(): invoked.");
    }
    public void onServerStopped(MinecraftServer minecraftServer)
    {
        PacketProvider.unregisterPayloads();
        ClientDebugSuite.checkGlobalChannels();
        MaLiLib.printDebug("MinecraftServerEvents#onServerStopped(): invoked.");
    }
}
