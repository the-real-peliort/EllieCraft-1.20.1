package net.ellieraven.elliecraft.item;

import net.ellieraven.elliecraft.EllieCraft;
import net.ellieraven.elliecraft.item.custom.BasicWandItem;
import net.ellieraven.elliecraft.item.custom.MetalDetectorItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = 
    DeferredRegister.create(ForgeRegistries.ITEMS, EllieCraft.MOD_ID);

    public static final RegistryObject<Item> SAPPHIRE = ITEMS.register("sapphire", 
        () -> new Item(new Item.Properties())
    );

    public static final RegistryObject<Item> RAW_SAPPHIRE = ITEMS.register("raw_sapphire", 
        () -> new Item(new Item.Properties())
    );

    public static final RegistryObject<Item> BASIC_SAPPHIRE_CORE = ITEMS.register("basic_sapphire_core",
            () -> new Item(new Item.Properties())
    );

    public static final RegistryObject<Item> OBSIDIAN_ROD = ITEMS.register("obsidian_rod",
            () -> new Item(new Item.Properties())
    );

    public static final RegistryObject<Item> METAL_DETECTOR = ITEMS.register("metal_detector",
            () -> new MetalDetectorItem(new Item.Properties().durability(128))
    );

    public static final RegistryObject<Item> BASIC_WAND = ITEMS.register("basic_wand",
            () -> new BasicWandItem(new Item.Properties())
    );

    public static final RegistryObject<Item> HAMBURGER = ITEMS.register("hamburger",
            () -> new Item(new Item.Properties().food(ModFoods.HAMBURGER))
    );

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
