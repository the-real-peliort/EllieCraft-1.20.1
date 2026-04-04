package net.ellieraven.elliecraft.item.custom;

import net.ellieraven.elliecraft.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class FateDelayingWandItem extends Item {
    public FateDelayingWandItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Level pLevel = pContext.getLevel();
        BlockPos pPos = pContext.getClickedPos();
        BlockState pState = pLevel.getBlockState(pPos);

        if (pState.is(ModBlocks.FATE_CUBE.get())) {
             pLevel.removeBlock(pPos, false);
             ItemStack stack = new ItemStack(ModBlocks.FATE_CUBE.get(), 1);
             return InteractionResult.SUCCESS;
        }

        return InteractionResult.FAIL;
    }
}
