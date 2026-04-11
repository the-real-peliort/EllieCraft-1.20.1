package net.ellieraven.elliecraft.item;

import net.ellieraven.elliecraft.EllieCraft;
import net.ellieraven.elliecraft.armor.ModArmorMaterials;
import net.ellieraven.elliecraft.item.custom.*;
import net.minecraft.world.item.ArmorItem;
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

    public static final RegistryObject<Item> REINFORCED_STICK = ITEMS.register("reinforced_stick",
            () -> new Item(new Item.Properties())
    );

    public static final RegistryObject<Item> LAG_STICK = ITEMS.register("lag_stick",
            () -> new LagStickItem(new Item.Properties().stacksTo(1))
    );

    public static final RegistryObject<Item> ELLIECRAFT_BOOK = ITEMS.register("elliecraft_book",
            () -> new EllieCraftBookItem(new Item.Properties().stacksTo(1))
    );

    public static final RegistryObject<Item> PAINT_SPONGE = ITEMS.register("paint_sponge",
            () -> new PaintSpongeItem(new Item.Properties())
    );

    public static final RegistryObject<Item> SOAP_BAR = ITEMS.register("soap_bar",
            () -> new Item(new Item.Properties())
    );

    public static final RegistryObject<Item> FATE_DELAYING_WAND = ITEMS.register("fate_delaying_wand",
            () -> new FateDelayingWandItem(new Item.Properties())
    );

    public static final RegistryObject<Item> GIRL_HOODIE_HOOD = ITEMS.register("girl_hoodie_hood",
            () -> new ArmorItem(ModArmorMaterials.GIRL_CLOTHES, ArmorItem.Type.HELMET, new Item.Properties().stacksTo(1))
    );

    public static final RegistryObject<Item> GIRL_HOODIE = ITEMS.register("girl_hoodie",
            () -> new ArmorItem(ModArmorMaterials.GIRL_CLOTHES, ArmorItem.Type.CHESTPLATE, new Item.Properties().stacksTo(1))
    );
    public static final RegistryObject<Item> GIRL_THIGH_HIGHS = ITEMS.register("girl_thigh_highs",
            () -> new ArmorItem(ModArmorMaterials.GIRL_CLOTHES, ArmorItem.Type.LEGGINGS, new Item.Properties().stacksTo(1))
    );

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
