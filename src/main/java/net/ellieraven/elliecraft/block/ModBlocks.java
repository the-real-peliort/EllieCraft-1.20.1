package net.ellieraven.elliecraft.block;

import java.util.function.Supplier;

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
            () -> new PillarBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS).noOcclusion())
    );
    public static final RegistryObject<Block> CONNECTING_STONE_PILLAR = registerBlock("connecting_stone_pillar",
            () -> new ConnectingPillarBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS).noOcclusion())
    );
    public static final RegistryObject<Block> OAK_LOG_PILLAR = registerBlock("oak_log_pillar",
            () -> new PillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).noOcclusion())
    );
    public static final RegistryObject<Block> CONNECTING_OAK_LOG_PILLAR = registerBlock("connecting_oak_log_pillar",
            () -> new ConnectingPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).noOcclusion())
    );

    public static final RegistryObject<Block> OAK_LOG_SAPPHIRE_GENERATOR = registerBlock("oak_log_sapphire_generator",
            () -> new OakLogSapphireGeneratorBlock(BlockBehaviour.Properties.copy(Blocks.STONE).noOcclusion())
    );

    public static final RegistryObject<Block> TUNNEL = registerBlock("tunnel",
            () -> new TunnelBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).noOcclusion())
    );


    public static final RegistryObject<Block> ALGAE_STONE = registerBlock("algae_stone",
            () -> new AlgaeStoneBlock(BlockBehaviour.Properties.copy(Blocks.STONE).randomTicks())
    );

    public static final RegistryObject<Block> PASSTHROUGH_BLOCK = registerBlock("passthrough_block",
            () -> new PassthroughBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noCollission())
    );

    public static final RegistryObject<Block> BLACK_BRICKS = registerBlock("black_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.BRICKS))
    );

    public static final RegistryObject<Block> BLUE_BRICKS = registerBlock("blue_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.BRICKS))
    );

    public static final RegistryObject<Block> YELLOW_BRICKS = registerBlock("yellow_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.BRICKS))
    );

    public static final RegistryObject<Block> PINK_BRICKS = registerBlock("pink_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.BRICKS))
    );

    public static final RegistryObject<Block> ROUNDABOUT_SIGN = registerBlock("roundabout_sign",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).destroyTime(0.5f))
    );

    public static final RegistryObject<Block> STOP_SIGN = registerBlock("stop_sign",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).destroyTime(0.5f))
    );

    public static final RegistryObject<Block> RED_METAL_SHEET_BLOCK = registerBlock("red_metal_sheet_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).destroyTime(0.5f))
    );

    public static final RegistryObject<Block> BLUE_METAL_SHEET_BLOCK = registerBlock("blue_metal_sheet_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).destroyTime(0.5f))
    );

    public static final RegistryObject<Block> YELLOW_METAL_SHEET_BLOCK = registerBlock("yellow_metal_sheet_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).destroyTime(0.5f))
    );

    public static final RegistryObject<Block> SPEED_LIMIT_SIGN_30 = registerBlock("speed_limit_sign_30",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).destroyTime(0.5f))
    );

    public static final RegistryObject<Block> SPEED_LIMIT_SIGN_40 = registerBlock("speed_limit_sign_40",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).destroyTime(0.5f))
    );

    public static final RegistryObject<Block> SPEED_LIMIT_SIGN_50 = registerBlock("speed_limit_sign_50",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).destroyTime(0.5f))
    );

    public static final RegistryObject<Block> SPEED_LIMIT_SIGN_60 = registerBlock("speed_limit_sign_60",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).destroyTime(0.5f))
    );

    public static final RegistryObject<Block> SPEED_LIMIT_SIGN_70 = registerBlock("speed_limit_sign_70",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).destroyTime(0.5f))
    );

    public static final RegistryObject<Block> SPEED_LIMIT_SIGN_80 = registerBlock("speed_limit_sign_80",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK))
    );

    public static final RegistryObject<Block> FATE_CUBE = registerBlock("fate_cube",
            () -> new FateCubeBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).destroyTime(0.5f))
    );

    public static final RegistryObject<Block> BUSH = registerBlock("bush",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).noOcclusion().noCollission())
    );

    public static final RegistryObject<Block> COMPRESSED_COBBLESTONE_1X = registerBlock("compressed_cobblestone_1x",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE).destroyTime(2.5f))
    );

    public static final RegistryObject<Block> COMPRESSED_COBBLESTONE_2X = registerBlock("compressed_cobblestone_2x",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE).destroyTime(3f))
    );

    public static final RegistryObject<Block> COMPRESSED_COBBLESTONE_3X = registerBlock("compressed_cobblestone_3x",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE).destroyTime(3.5f))
    );

    public static final RegistryObject<Block> COMPRESSED_COBBLESTONE_4X = registerBlock("compressed_cobblestone_4x",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE).destroyTime(4f))
    );

    public static final RegistryObject<Block> COMPRESSED_COBBLESTONE_5X = registerBlock("compressed_cobblestone_5x",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE).destroyTime(4.5f))
    );

    public static final RegistryObject<Block> COMPRESSED_COBBLESTONE_6X = registerBlock("compressed_cobblestone_6x",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE).destroyTime(5f))
    );

    public static final RegistryObject<Block> VERTICAL_BRICK_SLAB = registerBlock("vertical_brick_slab",
            () -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(Blocks.BRICK_SLAB).noOcclusion())
    );
    public static final RegistryObject<Block> VERTICAL_STONE_BRICK_SLAB = registerBlock("vertical_stone_brick_slab",
            () -> new VerticalSlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BRICK_SLAB).noOcclusion())
    );

    public static final RegistryObject<Block> KITCHEN_TILES = registerBlock("kitchen_tiles",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.BONE_BLOCK).destroyTime(1.5f))
    );

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
}
