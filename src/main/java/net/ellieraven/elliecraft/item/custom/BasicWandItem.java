package net.ellieraven.elliecraft.item.custom;

import net.ellieraven.elliecraft.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;

public class BasicWandItem extends Item {
    public BasicWandItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {

        Level level = pContext.getLevel();
        BlockPos blockPos = pContext.getClickedPos();
        Player player = pContext.getPlayer();

        boolean converted = false;

        if (!level.isClientSide()) {
            int ExperienceNeeded = 10;
            converted = TryConvertAsBasicWand(level, blockPos, player, ExperienceNeeded);
            if (converted) {
                player.giveExperiencePoints(-ExperienceNeeded);

                ((ServerLevel)level).sendParticles(
                        ParticleTypes.ENCHANT,
                        blockPos.getX() + 0.5,
                        blockPos.getY() + 0.5,
                        blockPos.getZ() + 0.5,
                        150,  // count
                        0.25, 0.25, 0.25,  // x/y/z spread
                        0.1  // speed
                );
                ((ServerLevel)level).sendParticles(
                        ParticleTypes.HAPPY_VILLAGER,
                        blockPos.getX() + 0.5,
                        blockPos.getY() + 0.5,
                        blockPos.getZ() + 0.5,
                        100,  // count
                        0.25, 0.25, 0.25,  // x/y/z spread
                        0.5  // speed
                );

                level.playSound(null, blockPos, SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.PLAYERS, 1f, 1f);
                level.playSound(null, blockPos, SoundEvents.AMETHYST_BLOCK_CHIME, SoundSource.PLAYERS, 1f, 1f);


            }
            else {
                return InteractionResult.FAIL;
            }
        }

        return InteractionResult.SUCCESS;
    }

    private boolean TryConvertAsBasicWand(Level level, BlockPos blockPos, Player player, int ExperienceNeeded) {

        ServerPlayer serverPlayer = (ServerPlayer) player;

        GameType gameType = serverPlayer.gameMode.getGameModeForPlayer();

        if (serverPlayer.totalExperience < ExperienceNeeded && gameType != GameType.CREATIVE) {
            player.sendSystemMessage(Component.literal("You need XP to use this!"));
            return false;
        }

        if (level.getBlockState(blockPos).is(Blocks.OAK_SAPLING)) {
            BlockState newState = Blocks.SPRUCE_SAPLING.defaultBlockState();
            level.setBlock(blockPos, newState, 3);
            return true;
        }
        else if (level.getBlockState(blockPos).is(Blocks.SPRUCE_SAPLING)) {
            BlockState newState = Blocks.BIRCH_SAPLING.defaultBlockState();
            level.setBlock(blockPos, newState, 3);
            return true;
        }
        else if (level.getBlockState(blockPos).is(Blocks.BIRCH_SAPLING)) {
            BlockState newState = Blocks.OAK_SAPLING.defaultBlockState();
            level.setBlock(blockPos, newState, 3);
            return true;
        }


        else if (level.getBlockState(blockPos).is(ModBlocks.STONE_PILLAR.get())) {
            BlockState newState = ModBlocks.CONNECTING_STONE_PILLAR.get().defaultBlockState();
            level.setBlock(blockPos, newState, 3);
            return true;
        }
        else if (level.getBlockState(blockPos).is(ModBlocks.CONNECTING_STONE_PILLAR.get())) {
            BlockState newState = ModBlocks.STONE_PILLAR.get().defaultBlockState();
            level.setBlock(blockPos, newState, 3);
            return true;
        }


        return false;
    }
}
