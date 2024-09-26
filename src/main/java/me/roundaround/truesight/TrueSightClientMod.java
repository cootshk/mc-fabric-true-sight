package me.roundaround.truesight;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class TrueSightClientMod implements ClientModInitializer {
  @Override
  public void onInitializeClient() {
    ClientPlayNetworking.registerGlobalReceiver(Networking.SyncS2C.ID, (payload, context) -> {
      TrueSightMod.LOGGER.info("[CLIENT] Server has the mod installed!");
      Tracker.getInstance().activateMod();
    });

    ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> {
      TrueSightMod.LOGGER.info("[CLIENT] Joined a world; resetting tracker and requesting server status.");
      Tracker.getInstance().deactivateMod();

      if (!ClientPlayNetworking.canSend(Networking.SyncC2S.ID)) {
        TrueSightMod.LOGGER.warn(
            "Server does not have True Sight installed. In order to prevent cheating, True Sight must also be " +
                "installed on the server.");
        return;
      }

      ClientPlayNetworking.send(new Networking.SyncC2S());
    });
  }
}
