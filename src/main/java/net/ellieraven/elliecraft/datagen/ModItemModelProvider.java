package net.ellieraven.elliecraft.datagen;

import net.ellieraven.elliecraft.EllieCraft;
import net.ellieraven.elliecraft.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SimpleFoiledItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, EllieCraft.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.SAPPHIRE);
        simpleItem(ModItems.RAW_SAPPHIRE);
        simpleItem(ModItems.BASIC_SAPPHIRE_CORE);
        simpleItem(ModItems.BASIC_WAND);
        simpleItem(ModItems.HAMBURGER);
        simpleItem(ModItems.METAL_DETECTOR);
        simpleItem(ModItems.OBSIDIAN_ROD);
        simpleItem(ModItems.REINFORCED_STICK);
        simpleItem(ModItems.ELLIECRAFT_BOOK);
        simpleItem(ModItems.PAINT_SPONGE);
        simpleItem(ModItems.SOAP_BAR);
        simpleItem(ModItems.FATE_DELAYING_WAND);
        simpleItem(ModItems.GIRL_HOODIE_HOOD);
        simpleItem(ModItems.GIRL_HOODIE);
        simpleItem(ModItems.GIRL_THIGH_HIGHS);

        simpleItemInFolder(ModItems.SWORD_PATTERN, "crafting_patterns");
        simpleItemInFolder(ModItems.PICKAXE_PATTERN, "crafting_patterns");
        simpleItemInFolder(ModItems.AXE_PATTERN, "crafting_patterns");
        simpleItemInFolder(ModItems.SHOVEL_PATTERN, "crafting_patterns");
        simpleItemInFolder(ModItems.HOE_PATTERN, "crafting_patterns");
        simpleItemInFolder(ModItems.BLANK_PATTERN, "crafting_patterns");
        simpleItemInFolder(ModItems.BINDING_PATTERN, "crafting_patterns");

        simpleItemInFolder(ModItems.WOODEN_SWORD_PART, "tool_parts/wooden");
        simpleItemInFolder(ModItems.WOODEN_PICKAXE_PART, "tool_parts/wooden");
        simpleItemInFolder(ModItems.WOODEN_AXE_PART, "tool_parts/wooden");

        simpleVanillaTextureItem(ModItems.LAG_STICK, "stick");
        simpleVanillaTextureItem(ModItems.COUNTERFEIT_DIAMOND, "diamond");
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(EllieCraft.MOD_ID,"item/" + item.getId().getPath()));
    }

    private ItemModelBuilder simpleVanillaTextureItem(RegistryObject<Item> item, String textureName) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation("minecraft:item/" + textureName));
    }

    private ItemModelBuilder simpleItemInFolder(RegistryObject<Item> item, String folder) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(EllieCraft.MOD_ID, "item/" + folder + "/" + item.getId().getPath()));
    }
}
