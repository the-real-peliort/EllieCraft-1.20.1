package net.ellieraven.elliecraft.item.custom;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class LagStickItem extends Item {

    public LagStickItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {

        long end = System.nanoTime() + 10_000_000_000L; // 10 seconds

        while (System.nanoTime() < end) {
            for (int i = 0; i < 100000; i++) {
                Math.sqrt(Math.random());
            }
        }
        return InteractionResultHolder.success(ItemStack.EMPTY);
    }
}
