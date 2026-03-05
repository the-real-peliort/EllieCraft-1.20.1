package net.ellieraven.elliecraft.item;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties HAMBURGER = new FoodProperties.Builder()
            .nutrition(20)
            .saturationMod(0.4f)
            .alwaysEat()
            .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 200), 1f)
            .build();

}