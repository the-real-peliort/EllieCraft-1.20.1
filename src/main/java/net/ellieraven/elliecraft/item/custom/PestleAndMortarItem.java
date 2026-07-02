package net.ellieraven.elliecraft.item.custom;

import com.mojang.datafixers.util.Pair;
import net.ellieraven.elliecraft.item.ModItems;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

import java.util.HashMap;
import java.util.Map;

public class PestleAndMortarItem extends Item {
    public static Map<Item, Pair<Item, Integer>> RECIPES = new HashMap<>();

    public PestleAndMortarItem(Properties pProperties) {
        super(pProperties);
    }

    public static void initRecipes() {
        System.out.println("Initialized Pestle and Mortar Recipes");
        RECIPES.put(ModItems.HERBS.get(), Pair.of(ModItems.CRUSHED_HERBS.get(), 1));
        RECIPES.put(Items.SWEET_BERRIES, Pair.of(Items.RED_DYE, 2));
        RECIPES.put(Items.RED_MUSHROOM, Pair.of(Items.RED_DYE, 2));
        RECIPES.put(Items.BROWN_MUSHROOM, Pair.of(Items.BROWN_DYE, 2));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {

        ItemStack pestleAndMortar = pPlayer.getItemInHand(pUsedHand);
        if (pUsedHand == InteractionHand.MAIN_HAND && !pLevel.isClientSide()){
            ItemStack ingredient = pPlayer.getItemInHand(InteractionHand.OFF_HAND);

            if (RECIPES.containsKey(ingredient.getItem())) {
                pestleAndMortar.hurtAndBreak(1, pPlayer, (p) -> {
                    p.broadcastBreakEvent(pUsedHand);
                });
                Pair<Item, Integer> result = RECIPES.get(ingredient.getItem());
                ItemStack grinded = new ItemStack(result.getFirst(), result.getSecond());
                ingredient.shrink(1);
                if (!pPlayer.getInventory().add(grinded)) {
                    pPlayer.drop(grinded, false);
                }
                return InteractionResultHolder.success(pestleAndMortar);
            }
            else {
                return InteractionResultHolder.fail(pestleAndMortar);
            }
        }
        return InteractionResultHolder.fail(pestleAndMortar);
    }
}
