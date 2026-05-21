package net.ellieraven.elliecraft.network;

import net.ellieraven.elliecraft.item.custom.blades.EndBladeItem;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class EndBladeBlinkPacket {

    public static void encode(EndBladeBlinkPacket msg, FriendlyByteBuf buf) {}

    public static EndBladeBlinkPacket decode(FriendlyByteBuf buf) {
        return new EndBladeBlinkPacket();
    }

    public static void handle(EndBladeBlinkPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {

            ServerPlayer player = ctx.get().getSender();
            if (player == null) return;

            ItemStack stack = player.getMainHandItem();
            if (!(stack.getItem() instanceof EndBladeItem)) return;

            Level level = player.serverLevel();

            EndBladeItem.blinkAbility(player);
        });

        ctx.get().setPacketHandled(true);
    }
}