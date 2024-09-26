package me.roundaround.truesight;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public class Networking {
  private Networking() {
  }

  public static final Identifier SYNC_C2S = new Identifier(TrueSightMod.MOD_ID, "sync_c2s");
  public static final Identifier SYNC_S2C = new Identifier(TrueSightMod.MOD_ID, "sync_s2c");

  public static void registerPayloads() {
    PayloadTypeRegistry.playC2S().register(SyncC2S.ID, SyncC2S.CODEC);
    PayloadTypeRegistry.playS2C().register(SyncS2C.ID, SyncS2C.CODEC);
  }

  public record SyncC2S() implements CustomPayload {
    public static final Id<SyncC2S> ID = new Id<>(SYNC_C2S);
    public static final PacketCodec<RegistryByteBuf, SyncC2S> CODEC = PacketCodec.of((value, buf) -> {
    }, (buf) -> new SyncC2S());

    @Override
    public Id<? extends CustomPayload> getId() {
      return ID;
    }
  }

  public record SyncS2C() implements CustomPayload {
    public static final Id<SyncS2C> ID = new Id<>(SYNC_S2C);
    public static final PacketCodec<RegistryByteBuf, SyncS2C> CODEC = PacketCodec.of((value, buf) -> {
    }, (buf) -> new SyncS2C());

    @Override
    public Id<? extends CustomPayload> getId() {
      return ID;
    }
  }
}
