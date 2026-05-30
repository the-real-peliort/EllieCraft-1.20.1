package net.ellieraven.elliecraft.item;

import net.ellieraven.elliecraft.EllieCraft;
import net.ellieraven.elliecraft.armor.ModArmorMaterials;
import net.ellieraven.elliecraft.item.custom.*;
import net.ellieraven.elliecraft.item.custom.blades.EndBladeItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
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
            () -> new PaintSpongeItem(new Item.Properties().stacksTo(1))
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

    public static final RegistryObject<Item> COUNTERFEIT_DIAMOND = ITEMS.register("counterfeit_diamond",
            () -> new Item(new Item.Properties())
    );

    public static final RegistryObject<Item> SWORD_PATTERN = ITEMS.register("sword_pattern",
            () -> new ReusableItem(new Item.Properties())
    );
    public static final RegistryObject<Item> PICKAXE_PATTERN = ITEMS.register("pickaxe_pattern",
            () -> new ReusableItem(new Item.Properties())
    );
    public static final RegistryObject<Item> AXE_PATTERN = ITEMS.register("axe_pattern",
            () -> new ReusableItem(new Item.Properties())
    );
    public static final RegistryObject<Item> SHOVEL_PATTERN = ITEMS.register("shovel_pattern",
            () -> new ReusableItem(new Item.Properties())
    );
    public static final RegistryObject<Item> HOE_PATTERN = ITEMS.register("hoe_pattern",
            () -> new ReusableItem(new Item.Properties())
    );
    public static final RegistryObject<Item> BLANK_PATTERN = ITEMS.register("blank_pattern",
            () -> new Item(new Item.Properties())
    );
    public static final RegistryObject<Item> BINDING_PATTERN = ITEMS.register("binding_pattern",
            () -> new ReusableItem(new Item.Properties())
    );

    public static final RegistryObject<Item> CRUDE_PATTERN = ITEMS.register("crude_pattern",
            () -> new Item(new Item.Properties())
    );

    public static final RegistryObject<Item> WOODEN_BINDING_PART = ITEMS.register("wooden_binding_part",
            () -> new Item(new Item.Properties())
    );

    public static final RegistryObject<Item> WOODEN_SWORD_PART = ITEMS.register("wooden_sword_part",
            () -> new Item(new Item.Properties())
    );
    public static final RegistryObject<Item> WOODEN_PICKAXE_PART = ITEMS.register("wooden_pickaxe_part",
            () -> new Item(new Item.Properties())
    );
    public static final RegistryObject<Item> WOODEN_AXE_PART = ITEMS.register("wooden_axe_part",
            () -> new Item(new Item.Properties())
    );
    public static final RegistryObject<Item> WOODEN_SHOVEL_PART = ITEMS.register("wooden_shovel_part",
            () -> new Item(new Item.Properties())
    );
    public static final RegistryObject<Item> WOODEN_HOE_PART = ITEMS.register("wooden_hoe_part",
            () -> new Item(new Item.Properties())
    );

    public static final RegistryObject<Item> STONE_BINDING_PART = ITEMS.register("stone_binding_part",
            () -> new Item(new Item.Properties())
    );

    public static final RegistryObject<Item> STONE_SWORD_PART = ITEMS.register("stone_sword_part",
            () -> new Item(new Item.Properties())
    );
    public static final RegistryObject<Item> STONE_PICKAXE_PART = ITEMS.register("stone_pickaxe_part",
            () -> new Item(new Item.Properties())
    );
    public static final RegistryObject<Item> STONE_AXE_PART = ITEMS.register("stone_axe_part",
            () -> new Item(new Item.Properties())
    );
    public static final RegistryObject<Item> STONE_SHOVEL_PART = ITEMS.register("stone_shovel_part",
            () -> new Item(new Item.Properties())
    );
    public static final RegistryObject<Item> STONE_HOE_PART = ITEMS.register("stone_hoe_part",
            () -> new Item(new Item.Properties())
    );

    public static final RegistryObject<Item> IRON_BINDING_PART = ITEMS.register("iron_binding_part",
            () -> new Item(new Item.Properties())
    );

    public static final RegistryObject<Item> IRON_SWORD_PART = ITEMS.register("iron_sword_part",
            () -> new Item(new Item.Properties())
    );
    public static final RegistryObject<Item> IRON_PICKAXE_PART = ITEMS.register("iron_pickaxe_part",
            () -> new Item(new Item.Properties())
    );
    public static final RegistryObject<Item> IRON_AXE_PART = ITEMS.register("iron_axe_part",
            () -> new Item(new Item.Properties())
    );
    public static final RegistryObject<Item> IRON_SHOVEL_PART = ITEMS.register("iron_shovel_part",
            () -> new Item(new Item.Properties())
    );
    public static final RegistryObject<Item> IRON_HOE_PART = ITEMS.register("iron_hoe_part",
            () -> new Item(new Item.Properties())
    );

    public static final RegistryObject<Item> ELLIECRAFT_STAR = ITEMS.register("elliecraft_star",
            () -> new EllieCraftStarItem(new Item.Properties())
    );
    public static final RegistryObject<Item> CHARGED_ELLIECRAFT_STAR = ITEMS.register("charged_elliecraft_star",
            () -> new ChargedEllieCraftStarItem(new Item.Properties())
    );

    public static final RegistryObject<Item> END_BLADE = ITEMS.register("end_blade",
            () -> new EndBladeItem(Tiers.NETHERITE, 10, -1.5f, new Item.Properties())
    );

    public static final RegistryObject<Item> PESTLE = ITEMS.register("pestle",
            () -> new Item(new Item.Properties().stacksTo(1))
    );
    public static final RegistryObject<Item> MORTAR = ITEMS.register("mortar",
            () -> new Item(new Item.Properties().stacksTo(1))
    );
    public static final RegistryObject<Item> PESTLE_AND_MORTAR = ITEMS.register("pestle_and_mortar",
            () -> new Item(new Item.Properties().stacksTo(1).durability(128))
    );

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
