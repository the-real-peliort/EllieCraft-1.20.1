package net.ellieraven.elliecraft.client;

import net.ellieraven.elliecraft.EllieCraft;
import net.ellieraven.elliecraft.network.EndBladeBlinkPacket;
import net.ellieraven.elliecraft.network.EndBladeRayPacket;
import net.ellieraven.elliecraft.network.PacketHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EllieCraft.MOD_ID, value = Dist.CLIENT)
public class ClientInputEvents {

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        while (ModKeybinds.END_BLADE_KEY.consumeClick()) {

            Minecraft mc = Minecraft.getInstance();
            LocalPlayer player = mc.player;

            if (player == null) return;

            PacketHandler.sendToServer(new EndBladeBlinkPacket());
        }
    }
}