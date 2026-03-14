package net.ellieraven.elliecraft.item.custom;

import net.ellieraven.elliecraft.gui.EllieCraftBookScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class EllieCraftBookItem extends Item {
    public EllieCraftBookItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if (pLevel.isClientSide) {
            Minecraft.getInstance().setScreen(new EllieCraftBookScreen(Component.literal("EllieCraft Guide :3")));
        }
        return InteractionResultHolder.success(pPlayer.getItemInHand(pUsedHand));
    }
}
