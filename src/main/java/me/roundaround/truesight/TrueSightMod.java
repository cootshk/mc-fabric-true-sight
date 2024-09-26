package me.roundaround.truesight;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TrueSightMod implements ModInitializer {
  public static final String MOD_ID = "truesight";
  public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

  @Override
  public void onInitialize() {
    Networking.registerPayloads();

    ServerPlayNetworking.registerGlobalReceiver(Networking.SyncC2S.ID, (payload, context) -> {
      TrueSightMod.LOGGER.info("[SERVER] True Sight client detected. Sending sync response.");
      ServerPlayNetworking.send(context.player(), new Networking.SyncS2C());
    });
  }
}
