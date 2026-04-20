package net.ellieraven.elliecraft.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ReusableItem extends Item {
    public ReusableItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean hasCraftingRemainingItem(ItemStack stack) {
        return true;
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack itemStack) {
        ItemStack remainder = itemStack.copy();
        remainder.setCount(1);
        return remainder;
    }
}
