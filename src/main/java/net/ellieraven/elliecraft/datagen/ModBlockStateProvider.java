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
        blockWithItemInFolder(ModBlocks.BLACK_BRICKS, "bricks");
        blockWithItemInFolder(ModBlocks.BLUE_BRICKS, "bricks");
        blockWithItemInFolder(ModBlocks.YELLOW_BRICKS, "bricks");
        blockWithItemInFolder(ModBlocks.PINK_BRICKS, "bricks");
        blockWithItemInFolder(ModBlocks.ROUNDABOUT_SIGN, "traffic_signs");
        blockWithItemInFolder(ModBlocks.STOP_SIGN, "traffic_signs");
        blockWithItem(ModBlocks.RED_METAL_SHEET_BLOCK);
        blockWithItem(ModBlocks.BLUE_METAL_SHEET_BLOCK);
        blockWithItem(ModBlocks.YELLOW_METAL_SHEET_BLOCK);
        blockWithItemInFolder(ModBlocks.SPEED_LIMIT_SIGN_30, "traffic_signs/speed");
        blockWithItemInFolder(ModBlocks.SPEED_LIMIT_SIGN_40, "traffic_signs/speed");
        blockWithItemInFolder(ModBlocks.SPEED_LIMIT_SIGN_50, "traffic_signs/speed");
        blockWithItemInFolder(ModBlocks.SPEED_LIMIT_SIGN_60, "traffic_signs/speed");
        blockWithItemInFolder(ModBlocks.SPEED_LIMIT_SIGN_70, "traffic_signs/speed");
        blockWithItemInFolder(ModBlocks.SPEED_LIMIT_SIGN_80, "traffic_signs/speed");
        blockWithItem(ModBlocks.FATE_CUBE);
        blockWithItemInFolder(ModBlocks.COMPRESSED_COBBLESTONE_1X, "compressed/cobblestone");
        blockWithItemInFolder(ModBlocks.COMPRESSED_COBBLESTONE_2X, "compressed/cobblestone");
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    private void blockWithItemInFolder(RegistryObject<Block> blockRegistryObject, String folder) {
        simpleBlockWithItem(
                blockRegistryObject.get(),
                models().cubeAll(
                        blockRegistryObject.getId().getPath(),
                        modLoc("block/" + folder + "/" + blockRegistryObject.getId().getPath())
                )
        );
    }
}
