package net.ellieraven.elliecraft.item.custom;

import net.ellieraven.elliecraft.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;
import java.util.Map;

public class PaintSpongeItem extends Item {

    static final Map<Block, Block> CLEANING_MAP = Map.ofEntries(
            Map.entry(Blocks.RED_TERRACOTTA, Blocks.TERRACOTTA),
            Map.entry(Blocks.ORANGE_TERRACOTTA, Blocks.TERRACOTTA),
            Map.entry(Blocks.YELLOW_TERRACOTTA, Blocks.TERRACOTTA),
            Map.entry(Blocks.GREEN_TERRACOTTA, Blocks.TERRACOTTA),
            Map.entry(Blocks.LIME_TERRACOTTA, Blocks.TERRACOTTA),
            Map.entry(Blocks.LIGHT_BLUE_TERRACOTTA, Blocks.TERRACOTTA),
            Map.entry(Blocks.BLUE_TERRACOTTA, Blocks.TERRACOTTA),
            Map.entry(Blocks.PURPLE_TERRACOTTA, Blocks.TERRACOTTA),
            Map.entry(Blocks.MAGENTA_TERRACOTTA, Blocks.TERRACOTTA),
            Map.entry(Blocks.PINK_TERRACOTTA, Blocks.TERRACOTTA),
            Map.entry(Blocks.WHITE_TERRACOTTA, Blocks.TERRACOTTA),
            Map.entry(Blocks.LIGHT_GRAY_TERRACOTTA, Blocks.TERRACOTTA),
            Map.entry(Blocks.GRAY_TERRACOTTA, Blocks.TERRACOTTA),
            Map.entry(Blocks.BLACK_TERRACOTTA, Blocks.TERRACOTTA),
            Map.entry(Blocks.BROWN_TERRACOTTA, Blocks.TERRACOTTA),
            Map.entry(Blocks.CYAN_TERRACOTTA, Blocks.TERRACOTTA),
            Map.entry(ModBlocks.STOP_SIGN.get(), ModBlocks.RED_METAL_SHEET_BLOCK.get()),
            Map.entry(ModBlocks.ROUNDABOUT_SIGN.get(), ModBlocks.BLUE_METAL_SHEET_BLOCK.get()),
            Map.entry(ModBlocks.SPEED_LIMIT_SIGN_30.get(), ModBlocks.YELLOW_METAL_SHEET_BLOCK.get()),
            Map.entry(ModBlocks.SPEED_LIMIT_SIGN_40.get(), ModBlocks.YELLOW_METAL_SHEET_BLOCK.get()),
            Map.entry(ModBlocks.SPEED_LIMIT_SIGN_50.get(), ModBlocks.YELLOW_METAL_SHEET_BLOCK.get()),
            Map.entry(ModBlocks.SPEED_LIMIT_SIGN_60.get(), ModBlocks.YELLOW_METAL_SHEET_BLOCK.get()),
            Map.entry(ModBlocks.SPEED_LIMIT_SIGN_70.get(), ModBlocks.YELLOW_METAL_SHEET_BLOCK.get()),
            Map.entry(ModBlocks.SPEED_LIMIT_SIGN_80.get(), ModBlocks.YELLOW_METAL_SHEET_BLOCK.get())

            );

    public PaintSpongeItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Level pLevel = pContext.getLevel();
        BlockPos pPos = pContext.getClickedPos();
        BlockState pState = pLevel.getBlockState(pPos);

        Block cleanedBlock = CLEANING_MAP.get(pState.getBlock());
        if (cleanedBlock != null) {
            pLevel.setBlock(pPos, cleanedBlock.defaultBlockState(), 3);
            pLevel.playSound(null, pPos, SoundEvents.SLIME_JUMP, SoundSource.BLOCKS, 1f, 1f);
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }
}
