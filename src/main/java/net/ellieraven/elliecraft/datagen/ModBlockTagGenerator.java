package net.ellieraven.elliecraft.datagen;

import net.ellieraven.elliecraft.EllieCraft;
import net.ellieraven.elliecraft.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, EllieCraft.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.SAPPHIRE_BLOCK.get())
                .add(ModBlocks.OAK_LOG_SAPPHIRE_GENERATOR.get());

        this.tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.SAPPHIRE_ORE.get());

        //this.tag(Tags.Blocks.NEEDS_NETHERITE_TOOL));

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.SAPPHIRE_BLOCK.get())
                .add(ModBlocks.SAPPHIRE_ORE.get())
                .add(ModBlocks.OAK_LOG_SAPPHIRE_GENERATOR.get())
                .add(ModBlocks.STONE_PILLAR.get())
                .add(ModBlocks.CONNECTING_STONE_PILLAR.get())
                .add(ModBlocks.PASSTHROUGH_BLOCK.get())
                .add(ModBlocks.ALGAE_STONE.get())
                .add(ModBlocks.BLACK_BRICKS.get())
                .add(ModBlocks.BLUE_BRICKS.get())
                .add(ModBlocks.YELLOW_BRICKS.get())
                .add(ModBlocks.PINK_BRICKS.get())
                .add(ModBlocks.ROUNDABOUT_SIGN.get())
                .add(ModBlocks.STOP_SIGN.get())
                .add(ModBlocks.RED_METAL_SHEET_BLOCK.get())
                .add(ModBlocks.BLUE_METAL_SHEET_BLOCK.get())
                .add(ModBlocks.YELLOW_METAL_SHEET_BLOCK.get())
                .add(ModBlocks.SPEED_LIMIT_SIGN_30.get())
                .add(ModBlocks.SPEED_LIMIT_SIGN_40.get())
                .add(ModBlocks.SPEED_LIMIT_SIGN_50.get())
                .add(ModBlocks.SPEED_LIMIT_SIGN_60.get())
                .add(ModBlocks.SPEED_LIMIT_SIGN_70.get())
                .add(ModBlocks.SPEED_LIMIT_SIGN_80.get())
                .add(ModBlocks.COMPRESSED_COBBLESTONE_1X.get())
                .add(ModBlocks.COMPRESSED_COBBLESTONE_2X.get())
                .add(ModBlocks.COMPRESSED_COBBLESTONE_3X.get())
                .add(ModBlocks.COMPRESSED_COBBLESTONE_4X.get())
                .add(ModBlocks.COMPRESSED_COBBLESTONE_5X.get())
                .add(ModBlocks.COMPRESSED_COBBLESTONE_6X.get())
        ;

        this.tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.OAK_LOG_PILLAR.get());
    }
}
