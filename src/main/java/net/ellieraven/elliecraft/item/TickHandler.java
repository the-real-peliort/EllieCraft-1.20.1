package net.ellieraven.elliecraft.item;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class TickHandler {

    @SubscribeEvent
    public static void onLevelTick(TickEvent.LevelTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        if (event.level.isClientSide()) return;

        ServerLevel level = (ServerLevel) event.level;

        for (Entity entity : level.getAllEntities()) {
            if (entity instanceof ItemEntity itemEntity) {

                ItemStack stack = itemEntity.getItem();

                boolean isStill =
                                Math.abs(itemEntity.getDeltaMovement().x) < 0.01 &&
                                Math.abs(itemEntity.getDeltaMovement().y) < 0.01 &&
                                Math.abs(itemEntity.getDeltaMovement().z) < 0.01;

                if (stack.getItem() == ModItems.END_BLADE.get() && isStill) {

                    for (int i = 0; i < 100; i++) {
                        level.sendParticles(
                                ParticleTypes.END_ROD,
                                itemEntity.getX(),
                                itemEntity.getY() + (i * 0.5)+3,
                                itemEntity.getZ(),
                                2,
                                0, 0.5, 0,
                                0.0
                        );
                    }
                    for (int i = 0; i < 50; i++) {
                        level.sendParticles(
                                ParticleTypes.DRAGON_BREATH,
                                itemEntity.getX(),
                                itemEntity.getY() + i +3,
                                itemEntity.getZ(),
                                1,
                                0.3, 0.5, 0.3,
                                0.05
                        );
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onItemPickup(PlayerEvent.ItemPickupEvent event) {

        Player player = event.getEntity();
        ItemStack itemStack = event.getStack();
        System.out.println("Pickup event fired: " + itemStack);

        if (player.level().isClientSide) return;

        if (itemStack.getItem() != ModItems.END_BLADE.get()) return;

        player.level().playSound(
                null,
                player.getX(),
                player.getY(),
                player.getZ(),
                SoundEvents.BEACON_ACTIVATE,
                SoundSource.BLOCKS,
                1F,
                1.0F
        );
    }
}