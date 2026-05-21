package net.ellieraven.elliecraft.network;

import net.ellieraven.elliecraft.EllieCraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class PacketHandler {

    private static final String PROTOCOL = "1";

    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(EllieCraft.MOD_ID, "main"),
            () -> PROTOCOL,
            PROTOCOL::equals,
            PROTOCOL::equals
    );

    private static int id = 0;

    public static void register() {
        INSTANCE.messageBuilder(EndBladeRayPacket.class, id++)
                .encoder(EndBladeRayPacket::encode)
                .decoder(EndBladeRayPacket::decode)
                .consumerMainThread(EndBladeRayPacket::handle)
                .add();

        INSTANCE.messageBuilder(EndBladeBlinkPacket.class, id++)
                .encoder(EndBladeBlinkPacket::encode)
                .decoder(EndBladeBlinkPacket::decode)
                .consumerMainThread(EndBladeBlinkPacket::handle)
                .add();
    }

    public static <MSG> void sendToServer(MSG msg) {
        INSTANCE.sendToServer(msg);
    }
}