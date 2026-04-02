package net.ellieraven.elliecraft.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;

import java.util.List;
import java.util.Random;

public class FateCubeBlock extends Block {
    public FateCubeBlock(Properties pProperties) {
        super(pProperties);
    }



    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
        if (!level.isClientSide) {
            List<Runnable> EVENTS = List.of(
                    () ->  {
                        ItemStack stack = new ItemStack(Items.DIAMOND, 1);
                        Block.popResource(level, pos, stack);
                    },
                    () ->  {
                        level.explode(null, pos.getX(), pos.getY(), pos.getZ(), 3, Level.ExplosionInteraction.BLOCK);
                    }
            );

            Random random = new Random();
            Runnable chosen = EVENTS.get(random.nextInt(EVENTS.size()));
            chosen.run();
        }

        return super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
    }
}
