package net.ellieraven.elliecraft.item;

import net.ellieraven.elliecraft.EllieCraft;
import net.ellieraven.elliecraft.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = 
    DeferredRegister.create(Registries.CREATIVE_MODE_TAB, EllieCraft.MOD_ID);

    public static final RegistryObject<CreativeModeTab> ELLIECRAFT_TAB = CREATIVE_MODE_TABS.register("elliecraft_tab",
        () -> CreativeModeTab.builder()
        .icon(() -> new ItemStack(ModItems.SAPPHIRE.get()))
        .title(Component.translatable("creativetab.elliecraft_tab"))
        .displayItems((pParameters, pOutput) -> {
            pOutput.accept(ModItems.SAPPHIRE.get());
            pOutput.accept(ModItems.RAW_SAPPHIRE.get());
            pOutput.accept(ModBlocks.SAPPHIRE_BLOCK.get());
            pOutput.accept(ModBlocks.SAPPHIRE_ORE.get());
            pOutput.accept(ModItems.METAL_DETECTOR.get());
            pOutput.accept(ModItems.BASIC_WAND.get());
            pOutput.accept(ModItems.HAMBURGER.get());
            pOutput.accept(ModBlocks.STONE_PILLAR.get());
            pOutput.accept(ModBlocks.CONNECTING_STONE_PILLAR.get());

            pOutput.accept(ModBlocks.OAK_LOG_PILLAR.get());
            pOutput.accept(ModItems.OBSIDIAN_ROD.get());
            pOutput.accept(ModItems.REINFORCED_STICK.get());
            pOutput.accept(ModItems.LAG_STICK.get());
            pOutput.accept(ModItems.BASIC_SAPPHIRE_CORE.get());
            pOutput.accept(ModBlocks.OAK_LOG_SAPPHIRE_GENERATOR.get());
            pOutput.accept(ModBlocks.TUNNEL.get());
            pOutput.accept(ModItems.ELLIECRAFT_BOOK.get());
            pOutput.accept(ModBlocks.ALGAE_STONE.get());

            pOutput.accept(ModBlocks.PASSTHROUGH_BLOCK.get());
            pOutput.accept(ModBlocks.BLACK_BRICKS.get());
            pOutput.accept(ModBlocks.BLUE_BRICKS.get());
            pOutput.accept(ModBlocks.YELLOW_BRICKS.get());
            pOutput.accept(ModBlocks.PINK_BRICKS.get());
            pOutput.accept(ModBlocks.BLUE_METAL_SHEET_BLOCK.get());
            pOutput.accept(ModBlocks.ROUNDABOUT_SIGN.get());
            pOutput.accept(ModBlocks.RED_METAL_SHEET_BLOCK.get());
            pOutput.accept(ModBlocks.STOP_SIGN.get());

            pOutput.accept(ModBlocks.YELLOW_METAL_SHEET_BLOCK.get());
            pOutput.accept(ModBlocks.SPEED_LIMIT_SIGN_30.get());
            pOutput.accept(ModBlocks.SPEED_LIMIT_SIGN_40.get());
            pOutput.accept(ModBlocks.SPEED_LIMIT_SIGN_50.get());
            pOutput.accept(ModBlocks.SPEED_LIMIT_SIGN_60.get());
            pOutput.accept(ModBlocks.SPEED_LIMIT_SIGN_70.get());
            pOutput.accept(ModBlocks.SPEED_LIMIT_SIGN_80.get());
            pOutput.accept(ModItems.PAINT_SPONGE.get());
            pOutput.accept(ModItems.SOAP_BAR.get());

            pOutput.accept(ModBlocks.FATE_CUBE.get());
            pOutput.accept(ModItems.FATE_DELAYING_WAND.get());
            pOutput.accept(ModItems.GIRL_THIGH_HIGHS.get());
            pOutput.accept(ModItems.GIRL_HOODIE.get());
            pOutput.accept(ModItems.GIRL_HOODIE_HOOD.get());
            pOutput.accept(ModBlocks.BUSH.get());
            pOutput.accept(ModBlocks.COMPRESSED_COBBLESTONE_1X.get());
            pOutput.accept(ModBlocks.COMPRESSED_COBBLESTONE_2X.get());
            pOutput.accept(ModBlocks.COMPRESSED_COBBLESTONE_3X.get());

            pOutput.accept(ModBlocks.COMPRESSED_COBBLESTONE_4X.get());
            pOutput.accept(ModBlocks.COMPRESSED_COBBLESTONE_5X.get());
            pOutput.accept(ModBlocks.COMPRESSED_COBBLESTONE_6X.get());
            pOutput.accept(ModItems.COUNTERFEIT_DIAMOND.get());
            pOutput.accept(ModItems.SWORD_PATTERN.get());
            pOutput.accept(ModItems.PICKAXE_PATTERN.get());
            pOutput.accept(ModItems.AXE_PATTERN.get());
            pOutput.accept(ModItems.SHOVEL_PATTERN.get());
            pOutput.accept(ModItems.HOE_PATTERN.get());
            pOutput.accept(ModItems.BINDING_PATTERN.get());
            pOutput.accept(ModItems.BLANK_PATTERN.get());
            pOutput.accept(ModItems.CRUDE_PATTERN.get());

            pOutput.accept(ModItems.WOODEN_BINDING_PART.get());
            pOutput.accept(ModItems.WOODEN_SWORD_PART.get());
            pOutput.accept(ModItems.WOODEN_PICKAXE_PART.get());
            pOutput.accept(ModItems.WOODEN_AXE_PART.get());
            pOutput.accept(ModItems.WOODEN_SHOVEL_PART.get());
            pOutput.accept(ModItems.WOODEN_HOE_PART.get());
            pOutput.accept(ModItems.STONE_BINDING_PART.get());
            pOutput.accept(ModItems.STONE_SWORD_PART.get());
            pOutput.accept(ModItems.STONE_PICKAXE_PART.get());

            pOutput.accept(ModItems.STONE_AXE_PART.get());
            pOutput.accept(ModItems.STONE_SHOVEL_PART.get());
            pOutput.accept(ModItems.STONE_HOE_PART.get());
        })
        .build()
    );

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
