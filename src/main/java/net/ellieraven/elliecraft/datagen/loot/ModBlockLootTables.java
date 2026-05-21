package net.ellieraven.elliecraft.datagen.loot;

import net.ellieraven.elliecraft.block.ModBlocks;
import net.ellieraven.elliecraft.item.ModItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.SAPPHIRE_BLOCK.get());
        this.dropSelf(ModBlocks.CONNECTING_OAK_LOG_PILLAR.get());
        this.dropSelf(ModBlocks.OAK_LOG_PILLAR.get());
        this.dropSelf(ModBlocks.STONE_PILLAR.get());
        this.dropSelf(ModBlocks.CONNECTING_STONE_PILLAR.get());
        this.dropSelf(ModBlocks.TUNNEL.get());
        this.dropSelf(ModBlocks.OAK_LOG_SAPPHIRE_GENERATOR.get());
        this.dropSelf(ModBlocks.ALGAE_STONE.get());
        this.dropSelf(ModBlocks.PASSTHROUGH_BLOCK.get());
        this.dropSelf(ModBlocks.PASSTHROUGH_BLOCK_GRASS.get());
        this.dropSelf(ModBlocks.PASSTHROUGH_BLOCK_STONE.get());
        this.dropSelf(ModBlocks.PASSTHROUGH_BLOCK_DIRT.get());
        this.dropSelf(ModBlocks.BLACK_BRICKS.get());
        this.dropSelf(ModBlocks.BLUE_BRICKS.get());
        this.dropSelf(ModBlocks.YELLOW_BRICKS.get());
        this.dropSelf(ModBlocks.PINK_BRICKS.get());
        this.dropSelf(ModBlocks.ROUNDABOUT_SIGN.get());
        this.dropSelf(ModBlocks.STOP_SIGN.get());
        this.dropSelf(ModBlocks.RED_METAL_SHEET_BLOCK.get());
        this.dropSelf(ModBlocks.BLUE_METAL_SHEET_BLOCK.get());
        this.dropSelf(ModBlocks.YELLOW_METAL_SHEET_BLOCK.get());
        this.dropSelf(ModBlocks.SPEED_LIMIT_SIGN_30.get());
        this.dropSelf(ModBlocks.SPEED_LIMIT_SIGN_40.get());
        this.dropSelf(ModBlocks.SPEED_LIMIT_SIGN_50.get());
        this.dropSelf(ModBlocks.SPEED_LIMIT_SIGN_60.get());
        this.dropSelf(ModBlocks.SPEED_LIMIT_SIGN_70.get());
        this.dropSelf(ModBlocks.SPEED_LIMIT_SIGN_80.get());
        this.dropSelf(ModBlocks.FATE_CUBE.get());
        this.dropSelf(ModBlocks.BUSH.get());
        this.dropSelf(ModBlocks.COMPRESSED_COBBLESTONE_1X.get());
        this.dropSelf(ModBlocks.COMPRESSED_COBBLESTONE_2X.get());
        this.dropSelf(ModBlocks.COMPRESSED_COBBLESTONE_3X.get());
        this.dropSelf(ModBlocks.COMPRESSED_COBBLESTONE_4X.get());
        this.dropSelf(ModBlocks.COMPRESSED_COBBLESTONE_5X.get());
        this.dropSelf(ModBlocks.COMPRESSED_COBBLESTONE_6X.get());
        this.dropSelf(ModBlocks.VERTICAL_BRICK_SLAB.get());
        this.dropSelf(ModBlocks.VERTICAL_STONE_BRICK_SLAB.get());
        this.dropSelf(ModBlocks.KITCHEN_TILES.get());

        this.add(ModBlocks.SAPPHIRE_ORE.get(),
                block -> createOreDrop(ModBlocks.SAPPHIRE_ORE.get(), ModItems.RAW_SAPPHIRE.get()));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
