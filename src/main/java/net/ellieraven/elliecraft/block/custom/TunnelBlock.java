package net.ellieraven.elliecraft.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class TunnelBlock extends Block {
    public TunnelBlock(Properties props) {
        super(props);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(AXIS, Direction.Axis.Y));
    }

    private static final VoxelShape XSHAPE = Shapes.or(
            // walls along Y-Z plane (front/back in Z)
            Block.box(0, 0, 0, 16, 16, 1),    // front wall
            Block.box(0, 0, 15, 16, 16, 16),  // back wall
            // floor and ceiling
            Block.box(0, 0, 1, 16, 1, 15),    // bottom wall
            Block.box(0, 15, 1, 16, 16, 15)   // top wall
    );

    private static final VoxelShape YSHAPE = Shapes.or(
            Block.box(0, 0, 0, 16, 16, 1),
            Block.box(0, 0, 15, 16, 16, 16),
            Block.box(0, 0, 1, 1, 16, 15),
            Block.box(15, 0, 1, 16, 16, 15)
    );

    private static final VoxelShape ZSHAPE = Shapes.or(
            Block.box(0, 0, 0, 16, 1, 16),   // bottom wall
            Block.box(0, 15, 0, 16, 16, 16), // top wall
            Block.box(0, 0, 0, 1, 16, 16),   // left wall
            Block.box(15, 0, 0, 16, 16, 16)  // right wall
    );

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        if (pState.getValue(AXIS) == Direction.Axis.X) {
            return XSHAPE;
        }
        else if (pState.getValue(AXIS) == Direction.Axis.Y) {
            return YSHAPE;
        }
        else {
            return ZSHAPE;
        }
    }

    private static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.AXIS;

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(AXIS);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext pContext) {
        Direction.Axis axis = pContext.getClickedFace().getAxis();
        return this.defaultBlockState().setValue(AXIS, axis);
    }
}
