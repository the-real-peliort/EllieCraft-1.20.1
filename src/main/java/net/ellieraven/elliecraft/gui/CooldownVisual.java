package net.ellieraven.elliecraft.gui;

import net.ellieraven.elliecraft.item.ModItems;
import net.ellieraven.elliecraft.item.custom.blades.EndBladeItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber
public class CooldownVisual {
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;

        if (event.phase != TickEvent.Phase.END) return;
        if (!player.level().isClientSide()) return;

        ItemStack stack = player.getMainHandItem();

        String cooldowns = "";

        if (stack.getItem() == ModItems.END_BLADE.get()) {
            float ray_cd = EndBladeItem.getCooldown(stack, "ray_cd") / 20.0f;
            float blink_cd = EndBladeItem.getCooldown(stack, "blink_cd") / 20.0f;


            cooldowns += "§dRay§f: ";
            if (ray_cd <= 0) { //Ready
                cooldowns += "§aReady!";
            }
            else {
                cooldowns += String.format("§c%.2fs", ray_cd);
            }
            cooldowns += "§f | ";
            cooldowns += "§dTP§f: ";
            if (blink_cd <= 0) { //Ready
                cooldowns += "§aReady!";
            }
            else {
                cooldowns += String.format("§c%.2fs", blink_cd);
            }
        }

        if (cooldowns != "") { //holding item
            player.displayClientMessage(
                    Component.literal(cooldowns),
                    true // action bar
            );
        }
        else { //nothing or non blade item
            player.displayClientMessage(
                    Component.literal(cooldowns),
                    true // action bar
            );
        }
    }
}
