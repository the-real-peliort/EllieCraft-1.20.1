package net.ellieraven.elliecraft.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

import java.util.Random;

public class OakLogSapphireGeneratorBlock extends Block {
    public OakLogSapphireGeneratorBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (pLevel.getBlockState(pPos.above()).isAir() && !pLevel.isClientSide()) {
            if (pLevel.random.nextFloat() < 0.005f && !pLevel.getBlockState(pPos.below()).is(Blocks.NETHERITE_BLOCK)) {
                Mob creeper = EntityType.CREEPER.create(pLevel);
                creeper.moveTo(new Vec3(pPos.getX() + 0.5, pPos.getY() + 1.5, pPos.getZ() + 0.5));
                pLevel.addFreshEntity(creeper);
                pPlayer.sendSystemMessage(Component.literal("MUAHAHAHAHAHAHAHA!"));
            }
            else {
                pLevel.setBlock(pPos.above(), Blocks.OAK_LOG.defaultBlockState(), 3);
            }
        }

        return InteractionResult.SUCCESS;
    }
}
