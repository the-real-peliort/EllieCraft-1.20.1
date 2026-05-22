package net.ellieraven.elliecraft.gui.death;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class DamageSources {
    public static DamageSource endRay(Level level, Entity attacker) {
        return new DamageSource(level.registryAccess()
                .registryOrThrow(Registries.DAMAGE_TYPE)
                .getHolderOrThrow(DamageTypes.MAGIC)) {

            @Override
            public Component getLocalizedDeathMessage(LivingEntity victim) {
                if (attacker != null) {// fired by player
                    return Component.translatable("death.attack.end_ray_plr", victim.getDisplayName(), attacker.getDisplayName());
                }
                else {
                    return Component.translatable("death.attack.end_ray", victim.getDisplayName());
                }

            }
        };
    }
}
