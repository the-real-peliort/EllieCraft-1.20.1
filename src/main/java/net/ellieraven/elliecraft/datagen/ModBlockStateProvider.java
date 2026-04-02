package net.ellieraven.elliecraft.datagen;

import net.ellieraven.elliecraft.EllieCraft;
import net.ellieraven.elliecraft.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, EllieCraft.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.SAPPHIRE_BLOCK);
        blockWithItem(ModBlocks.SAPPHIRE_ORE);
        blockWithItem(ModBlocks.PASSTHROUGH_BLOCK);
        blockWithItem(ModBlocks.BLACK_BRICKS);
        blockWithItem(ModBlocks.BLUE_BRICKS);
        blockWithItem(ModBlocks.YELLOW_BRICKS);
        blockWithItem(ModBlocks.PINK_BRICKS);
        blockWithItem(ModBlocks.ROUNDABOUT_SIGN);
        blockWithItem(ModBlocks.STOP_SIGN);
        blockWithItem(ModBlocks.RED_METAL_SHEET_BLOCK);
        blockWithItem(ModBlocks.BLUE_METAL_SHEET_BLOCK);
        blockWithItem(ModBlocks.YELLOW_METAL_SHEET_BLOCK);
        blockWithItem(ModBlocks.SPEED_LIMIT_SIGN_30);
        blockWithItem(ModBlocks.SPEED_LIMIT_SIGN_40);
        blockWithItem(ModBlocks.SPEED_LIMIT_SIGN_50);
        blockWithItem(ModBlocks.SPEED_LIMIT_SIGN_60);
        blockWithItem(ModBlocks.SPEED_LIMIT_SIGN_70);
        blockWithItem(ModBlocks.SPEED_LIMIT_SIGN_80);
        blockWithItem(ModBlocks.FATE_CUBE);
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
