package net.ellieraven.elliecraft.block;

import java.util.function.Supplier;

import com.mojang.blaze3d.shaders.Uniform;

import net.ellieraven.elliecraft.EllieCraft;
import net.ellieraven.elliecraft.block.custom.*;
import net.ellieraven.elliecraft.item.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = 
    DeferredRegister.create(ForgeRegistries.BLOCKS, EllieCraft.MOD_ID);

    public static final RegistryObject<Block> SAPPHIRE_BLOCK = registerBlock("sapphire_block", 
    () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
    .sound(SoundType.CALCITE))
    );

    public static final RegistryObject<Block> SAPPHIRE_ORE = registerBlock("sapphire_ore", 
    () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
    .strength(1.8f).requiresCorrectToolForDrops(), UniformInt.of(3, 6))
    );

    public static final RegistryObject<Block> STONE_PILLAR = registerBlock("stone_pillar",
            () -> new StonePillarBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS).noOcclusion())
    );

    public static final RegistryObject<Block> CONNECTING_STONE_PILLAR = registerBlock("connecting_stone_pillar",
            () -> new ConnectingStonePillarBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS).noOcclusion())
    );

    public static final RegistryObject<Block> OAK_LOG_PILLAR = registerBlock("oak_log_pillar",
            () -> new OakLogPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).noOcclusion())
    );

    public static final RegistryObject<Block> OAK_LOG_SAPPHIRE_GENERATOR = registerBlock("oak_log_sapphire_generator",
            () -> new OakLogSapphireGeneratorBlock(BlockBehaviour.Properties.copy(Blocks.STONE).noOcclusion())
    );

    public static final RegistryObject<Block> TUNNEL = registerBlock("tunnel",
            () -> new TunnelBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).noOcclusion())
    );

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
