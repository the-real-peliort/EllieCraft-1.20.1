package net.ellieraven.elliecraft.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Fluids;

public class AlgaeStoneBlock extends Block {
    public static final IntegerProperty MOISTURE = IntegerProperty.create("moisture", 0, 4);
    public static final int MAX_MOISTURE = 4;

    public AlgaeStoneBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.defaultBlockState().setValue(MOISTURE, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(MOISTURE);
    }

    @Override
    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        int radius = 3;
        int closestDistance = radius + 1;

        // Find nearest water
        outer:
        for (int dx = -radius; dx <= radius; dx++) {
            for (int dy = -radius; dy <= radius; dy++) {
                for (int dz = -radius; dz <= radius; dz++) {
                    BlockPos checkPos = pos.offset(dx, dy, dz);
                    if (world.getBlockState(checkPos).getFluidState().is(Fluids.WATER)) {
                        // Manhattan distance
                        int dist = Math.abs(dx) + Math.abs(dy) + Math.abs(dz);
                        if (dist < closestDistance) {
                            closestDistance = dist;
                        }
                        if (closestDistance == 1) break outer; // 1 is the closest possible
                    }
                }
            }
        }

        if (closestDistance <= radius) {
            int moisture = state.getValue(MOISTURE);

            // Correct gradient: distance 1 → 4, distance 2 → 3, etc.
            int gradientCap = Math.max(1, 5 - closestDistance);

            // Gradual growth, but never exceed gradient
            if (moisture < gradientCap) {
                world.setBlock(pos, state.setValue(MOISTURE, moisture + 1), 3);
            }
        }
    }
}