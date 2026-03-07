package net.ellieraven.elliecraft.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class ConnectingStonePillarBlock extends Block {
    public ConnectingStonePillarBlock(Properties props) {
        super(props);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(NORTH, false)
                .setValue(EAST, false)
                .setValue(SOUTH, false)
                .setValue(WEST, false));
    }

    private static final VoxelShape SHAPE = Block.box(2, 0, 2, 14, 16, 14);

    public static final BooleanProperty NORTH = BooleanProperty.create("north");
    public static final BooleanProperty EAST  = BooleanProperty.create("east");
    public static final BooleanProperty SOUTH = BooleanProperty.create("south");
    public static final BooleanProperty WEST  = BooleanProperty.create("west");

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        int minX = 2;
        int minY = 0;
        int minZ = 2;
        int maxX = 14;
        int maxY = 16;
        int maxZ = 14;

        VoxelShape shape = Block.box(2, 0, 2, 14, 16, 14); // central pillar

        if (pState.getValue(NORTH)) shape = Shapes.or(shape, Block.box(2, 0, 0, 14, 16, 2));
        if (pState.getValue(SOUTH)) shape = Shapes.or(shape, Block.box(2, 0, 14, 14, 16, 16));
        if (pState.getValue(WEST))  shape = Shapes.or(shape, Block.box(0, 0, 2, 2, 16, 14));
        if (pState.getValue(EAST))  shape = Shapes.or(shape, Block.box(14, 0, 2, 16, 16, 14));

        return shape;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(NORTH, EAST, SOUTH, WEST);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext pContext) {
        Level level = pContext.getLevel();
        BlockPos pos  = pContext.getClickedPos();

        return this.defaultBlockState()
                .setValue(NORTH, connectsTo(level.getBlockState(pos.north()), Direction.NORTH, pContext.getLevel(), pos))
                .setValue(EAST, connectsTo(level.getBlockState(pos.east()), Direction.EAST, pContext.getLevel(), pos))
                .setValue(SOUTH, connectsTo(level.getBlockState(pos.south()), Direction.SOUTH, pContext.getLevel(), pos))
                .setValue(WEST, connectsTo(level.getBlockState(pos.west()), Direction.WEST, pContext.getLevel(), pos));
    }

    private Boolean connectsTo(BlockState blockState, Direction direction, Level level, BlockPos pos) {
        return blockState.isFaceSturdy(level, pos, direction) || blockState.getBlock() instanceof ConnectingStonePillarBlock;
    }

    @Override
    public BlockState updateShape(BlockState pState, Direction pDirection, BlockState pNeighborState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pNeighborPos) {
        if (pDirection == Direction.NORTH)
            return pState.setValue(NORTH, connectsTo(pNeighborState, Direction.NORTH, (Level)pLevel, pCurrentPos));

        if (pDirection == Direction.EAST)
            return pState.setValue(EAST, connectsTo(pNeighborState, Direction.EAST, (Level)pLevel, pCurrentPos));

        if (pDirection == Direction.SOUTH)
            return pState.setValue(SOUTH, connectsTo(pNeighborState, Direction.SOUTH, (Level)pLevel, pCurrentPos));

        if (pDirection == Direction.WEST)
            return pState.setValue(WEST, connectsTo(pNeighborState, Direction.WEST, (Level)pLevel, pCurrentPos));

        return pState;
    }
}
