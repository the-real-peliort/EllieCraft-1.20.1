package net.ellieraven.elliecraft.datagen;

import com.mojang.datafixers.types.templates.Tag;
import net.ellieraven.elliecraft.EllieCraft;
import net.ellieraven.elliecraft.block.ModBlocks;
import net.ellieraven.elliecraft.item.ModItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.fml.common.Mod;

import java.io.Writer;
import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    private static final List<ItemLike> SAPPHIRE_SMELTABLES = List.of(ModItems.RAW_SAPPHIRE.get(),
            ModBlocks.SAPPHIRE_ORE.get());

    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {

        createSignRecipes(pWriter);
        createPatternRecipes(pWriter);
        createToolPartRecipesCrude(pWriter);
        createToolPartRecipes(pWriter);
        createToolRecipes(pWriter);

        oreSmelting(pWriter, SAPPHIRE_SMELTABLES, RecipeCategory.MISC, ModItems.SAPPHIRE.get(), 0.25f, 200, "sapphire");
        oreBlasting(pWriter, SAPPHIRE_SMELTABLES, RecipeCategory.MISC, ModItems.SAPPHIRE.get(), 0.25f, 100, "sapphire");
        /*
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SAPPHIRE_BLOCK.get())
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModItems.SAPPHIRE.get())
                .unlockedBy(getHasName(ModItems.SAPPHIRE.get()), has(ModItems.SAPPHIRE.get()))
                .save(pWriter);
         */

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.SAPPHIRE_BLOCK.get(), 1)
                .requires(ModItems.SAPPHIRE.get(), 9)
                .unlockedBy(getHasName(ModItems.SAPPHIRE.get()), has(ModItems.SAPPHIRE.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SAPPHIRE.get(), 9)
                .requires(ModBlocks.SAPPHIRE_BLOCK.get(), 1)
                .unlockedBy(getHasName(ModBlocks.SAPPHIRE_BLOCK.get()), has(ModBlocks.SAPPHIRE_BLOCK.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.TUNNEL.get(), 4)
                .pattern(" P ")
                .pattern("P P")
                .pattern(" P ")
                .define('P', ItemTags.WOODEN_PRESSURE_PLATES)
                .unlockedBy(getHasName(Blocks.CRAFTING_TABLE), has(Blocks.CRAFTING_TABLE))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BASIC_SAPPHIRE_CORE.get())
                .pattern("GSG")
                .pattern("SDS")
                .pattern("GSG")
                .define('G', Blocks.GOLD_BLOCK)
                .define('S', ModBlocks.SAPPHIRE_BLOCK.get())
                .define('D', Blocks.DIAMOND_BLOCK)
                .unlockedBy(getHasName(Blocks.CRAFTING_TABLE), has(Blocks.CRAFTING_TABLE))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BASIC_WAND.get())
                .pattern("A")
                .pattern("S")
                .pattern("S")
                .define('S', Items.STICK)
                .define('A', ModItems.BASIC_SAPPHIRE_CORE.get())
                .unlockedBy(getHasName(ModItems.BASIC_SAPPHIRE_CORE.get()), has(ModItems.BASIC_SAPPHIRE_CORE.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.CONNECTING_STONE_PILLAR.get(), 1)
                .requires(ModBlocks.STONE_PILLAR.get(), 1)
                .unlockedBy(getHasName(ModBlocks.STONE_PILLAR.get()), has(ModBlocks.STONE_PILLAR.get()))
                .save(pWriter, EllieCraft.MOD_ID + ":connecting_stone_pillar_from_stone_pillar");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.STONE_PILLAR.get())
                .requires(ModBlocks.CONNECTING_STONE_PILLAR.get(), 1)
                .unlockedBy(getHasName(ModBlocks.CONNECTING_STONE_PILLAR.get()), has(ModBlocks.CONNECTING_STONE_PILLAR.get()))
                .save(pWriter, EllieCraft.MOD_ID + ":stone_pillar_from_connecting_stone_pillar");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.CONNECTING_OAK_LOG_PILLAR.get(), 1)
                .requires(ModBlocks.OAK_LOG_PILLAR.get(), 1)
                .unlockedBy(getHasName(ModBlocks.OAK_LOG_PILLAR.get()), has(ModBlocks.OAK_LOG_PILLAR.get()))
                .save(pWriter, EllieCraft.MOD_ID + ":connecting_oak_log_pillar_from_oak_log_pillar");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.OAK_LOG_PILLAR.get())
                .requires(ModBlocks.CONNECTING_OAK_LOG_PILLAR.get(), 1)
                .unlockedBy(getHasName(ModBlocks.CONNECTING_OAK_LOG_PILLAR.get()), has(ModBlocks.CONNECTING_OAK_LOG_PILLAR.get()))
                .save(pWriter, EllieCraft.MOD_ID + ":oak_log_pillar_from_connecting_oak_log_pillar");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.HAMBURGER.get())
                .pattern("B")
                .pattern("S")
                .pattern("B")
                .define('B', Items.BREAD)
                .define('S', Items.COOKED_BEEF)
                .unlockedBy(getHasName(Items.BREAD), has(Items.BREAD))
                .unlockedBy(getHasName(Items.COOKED_BEEF), has(Items.COOKED_BEEF))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.STONE_PILLAR.get(), 2)
                .pattern("B")
                .pattern("B")
                .define('B', Blocks.STONE_BRICKS)
                .unlockedBy(getHasName(Blocks.STONE_BRICKS), has(Blocks.STONE_BRICKS))
                .save(pWriter, EllieCraft.MOD_ID + ":stone_pillar_from_stone_bricks");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.REINFORCED_STICK.get())
                .pattern(" NS")
                .pattern("NSN")
                .pattern("SN ")
                .define('S', Items.STICK)
                .define('N', Items.IRON_NUGGET)
                .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.OBSIDIAN_ROD.get())
                .pattern("  O")
                .pattern(" O ")
                .pattern("O  ")
                .define('O', Blocks.OBSIDIAN)
                .unlockedBy(getHasName(Blocks.OBSIDIAN), has(Blocks.OBSIDIAN))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Blocks.HOPPER)
                .pattern("ILI")
                .pattern("ILI")
                .pattern(" I ")
                .define('L', Blocks.OAK_LOG)
                .define('I', Items.IRON_INGOT)
                .unlockedBy(getHasName(Items.HOPPER), has(Items.HOPPER))
                .save(pWriter, EllieCraft.MOD_ID + ":hopper_shortcut");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.METAL_DETECTOR.get())
                .pattern("DE ")
                .pattern("RS ")
                .pattern("  S")
                .define('S', Items.STICK)
                .define('R', Items.REDSTONE)
                .define('D', Items.DIAMOND)
                .define('E', Items.EMERALD)
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.OAK_LOG_SAPPHIRE_GENERATOR.get())
                .pattern("OCO")
                .pattern("BSB")
                .pattern("NPN")
                .define('C', ModItems.BASIC_SAPPHIRE_CORE.get())
                .define('O', ModItems.OBSIDIAN_ROD.get())
                .define('S', Blocks.OAK_SAPLING)
                .define('B', Blocks.IRON_BLOCK)
                .define('P', Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE)
                .define('N', Items.IRON_NUGGET)
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.OAK_LOG_PILLAR.get(), 2)
                .pattern("L")
                .pattern("L")
                .define('L', Blocks.OAK_LOG)
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.LAG_STICK.get(), 1)
                .pattern(" S ")
                .pattern("  S")
                .pattern("S  ")
                .define('S', Items.STICK)
                .unlockedBy(getHasName(Items.BEDROCK), has(Items.BEDROCK))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.PASSTHROUGH_BLOCK.get(), 1)
                .pattern(" E ")
                .pattern("YBY")
                .pattern(" E ")
                .define('B', Blocks.IRON_BLOCK)
                .define('E', Items.ENDER_PEARL)
                .define('Y', Blocks.YELLOW_CONCRETE)
                .unlockedBy(getHasName(Items.ENDER_PEARL), has(Items.ENDER_PEARL))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SAPPHIRE.get(), 1)
                .requires(Items.EMERALD, 1)
                .requires(Items.LIGHT_BLUE_DYE, 1)
                .unlockedBy(getHasName(Items.EMERALD), has(Items.EMERALD))
                .save(pWriter, EllieCraft.MOD_ID + ":color_emerald_into_sapphire");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SOAP_BAR.get(), 1)
                .requires(Blocks.SLIME_BLOCK, 1)
                .requires(Ingredient.of(ItemTags.SMALL_FLOWERS), 1)
                .unlockedBy(getHasName(Blocks.SLIME_BLOCK), has(Blocks.SLIME_BLOCK))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.PAINT_SPONGE.get(), 1)
                .requires(ModItems.SOAP_BAR.get(), 1)
                .requires(Blocks.SPONGE, 1)
                .unlockedBy(getHasName(Blocks.SPONGE), has(Blocks.SPONGE))
                .save(pWriter);

        createBrickRecipe(ModBlocks.BLACK_BRICKS.get(), "black_bricks", Items.BLACK_DYE, pWriter);
        createBrickRecipe(ModBlocks.BLUE_BRICKS.get(), "blue_bricks", Items.BLUE_DYE, pWriter);
        createBrickRecipe(ModBlocks.YELLOW_BRICKS.get(), "yellow_bricks", Items.YELLOW_DYE, pWriter);
        createBrickRecipe(ModBlocks.PINK_BRICKS.get(), "pink_bricks", Items.PINK_DYE, pWriter);

        createCompressedRecipe(Blocks.COBBLESTONE, ModBlocks.COMPRESSED_COBBLESTONE_1X.get(), pWriter);
        createCompressedRecipe(ModBlocks.COMPRESSED_COBBLESTONE_1X.get(), ModBlocks.COMPRESSED_COBBLESTONE_2X.get(), pWriter);
        createCompressedRecipe(ModBlocks.COMPRESSED_COBBLESTONE_2X.get(), ModBlocks.COMPRESSED_COBBLESTONE_3X.get(), pWriter);
        createCompressedRecipe(ModBlocks.COMPRESSED_COBBLESTONE_3X.get(), ModBlocks.COMPRESSED_COBBLESTONE_4X.get(), pWriter);
        createCompressedRecipe(ModBlocks.COMPRESSED_COBBLESTONE_4X.get(), ModBlocks.COMPRESSED_COBBLESTONE_5X.get(), pWriter);
        createCompressedRecipe(ModBlocks.COMPRESSED_COBBLESTONE_5X.get(), ModBlocks.COMPRESSED_COBBLESTONE_6X.get(), pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.COUNTERFEIT_DIAMOND.get(), 1)
                .requires(Items.LIGHT_BLUE_DYE, 1)
                .requires(Items.COAL, 1)
                .unlockedBy(getHasName(Items.COAL), has(Items.COAL))
                .save(pWriter, EllieCraft.MOD_ID + ":color_coal_into_counterfeit_diamond");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.COAL, 1)
                .requires(ModItems.COUNTERFEIT_DIAMOND.get(), 1)
                .requires(ModItems.PAINT_SPONGE.get(), 1)
                .unlockedBy(getHasName(Items.COAL), has(Items.COAL))
                .save(pWriter, EllieCraft.MOD_ID + ":wash_counterfeit_diamond_into_coal");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.RED_DYE, 1)
                .requires(Items.SWEET_BERRIES, 1)
                .unlockedBy(getHasName(Items.SWEET_BERRIES), has(Items.SWEET_BERRIES))
                .save(pWriter, EllieCraft.MOD_ID + ":red_dye_from_berries");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.PASSTHROUGH_BLOCK_GRASS.get(), 1)
                .requires(ModBlocks.PASSTHROUGH_BLOCK.get(), 1)
                .requires(Blocks.GRASS_BLOCK, 1)
                .unlockedBy(getHasName(ModBlocks.PASSTHROUGH_BLOCK.get()), has(ModBlocks.PASSTHROUGH_BLOCK.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.PASSTHROUGH_BLOCK_STONE.get(), 1)
                .requires(ModBlocks.PASSTHROUGH_BLOCK.get(), 1)
                .requires(Blocks.STONE, 1)
                .unlockedBy(getHasName(ModBlocks.PASSTHROUGH_BLOCK.get()), has(ModBlocks.PASSTHROUGH_BLOCK.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.PASSTHROUGH_BLOCK_DIRT.get(), 1)
                .requires(ModBlocks.PASSTHROUGH_BLOCK.get(), 1)
                .requires(Blocks.DIRT, 1)
                .unlockedBy(getHasName(ModBlocks.PASSTHROUGH_BLOCK.get()), has(ModBlocks.PASSTHROUGH_BLOCK.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.PASSTHROUGH_BLOCK_COBBLESTONE.get(), 1)
                .requires(ModBlocks.PASSTHROUGH_BLOCK.get(), 1)
                .requires(Blocks.COBBLESTONE, 1)
                .unlockedBy(getHasName(ModBlocks.PASSTHROUGH_BLOCK.get()), has(ModBlocks.PASSTHROUGH_BLOCK.get()))
                .save(pWriter);
    }

    static void createToolPartRecipe(Item toolPart, Item pattern, Item material, int amount, Consumer<FinishedRecipe> pWriter) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, toolPart, 1)
                .requires(pattern, 1)
                .requires(material, amount)
                .unlockedBy(getHasName(pattern), has(pattern))
                .save(pWriter, EllieCraft.MOD_ID + ":" +
                        BuiltInRegistries.ITEM.getKey(toolPart).getPath() + "_from_"+
                        BuiltInRegistries.ITEM.getKey(pattern).getPath());
    }
    static void createToolPartRecipe(Item toolPart, Item pattern, TagKey<Item> material, int amount, Consumer<FinishedRecipe> pWriter) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, toolPart, 1)
                .requires(pattern, 1)
                .requires(Ingredient.of(material), amount)
                .unlockedBy(getHasName(pattern), has(pattern))
                .save(pWriter, EllieCraft.MOD_ID + ":" +
                        BuiltInRegistries.ITEM.getKey(toolPart).getPath() + "_from_"+
                        BuiltInRegistries.ITEM.getKey(pattern).getPath());
    }

    static void createBrickRecipe(Block block, String blockName, Item dye, Consumer<FinishedRecipe> pWriter) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, block, 1)
                .requires(dye, 1)
                .requires(Blocks.BRICKS, 1)
                .unlockedBy(getHasName(Blocks.BRICKS), has(Blocks.BRICKS))
                .save(pWriter, EllieCraft.MOD_ID + ":" + blockName + "_single");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, block, 8)
                .requires(dye, 1)
                .requires(Blocks.BRICKS, 8)
                .unlockedBy(getHasName(Blocks.BRICKS), has(Blocks.BRICKS))
                .save(pWriter, EllieCraft.MOD_ID + ":" + blockName + "_bulk");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Blocks.BRICKS, 1)
                .requires(Items.WATER_BUCKET, 1)
                .requires(block, 1)
                .unlockedBy(getHasName(Blocks.BRICKS), has(Blocks.BRICKS))
                .save(pWriter, EllieCraft.MOD_ID + ":" + blockName + "_single_uncolor");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Blocks.BRICKS, 8)
                .requires(Items.WATER_BUCKET, 1)
                .requires(block, 8)
                .unlockedBy(getHasName(Blocks.BRICKS), has(Blocks.BRICKS))
                .save(pWriter, EllieCraft.MOD_ID + ":" + blockName + "_bulk_uncolor");
    }

    static void createCompressedRecipe(Block tiny, Block big, Consumer<FinishedRecipe> pWriter) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, big, 1)
                .requires(tiny, 9)
                .unlockedBy(getHasName(tiny), has(tiny))
                .save(pWriter, EllieCraft.MOD_ID + ":" +
                        BuiltInRegistries.BLOCK.getKey(tiny).getPath() + "_to_" +
                        BuiltInRegistries.BLOCK.getKey(big).getPath());
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, tiny, 9)
                .requires(big, 1)
                .unlockedBy(getHasName(big), has(big))
                .save(pWriter, EllieCraft.MOD_ID + ":" +
                        BuiltInRegistries.BLOCK.getKey(big).getPath() + "_to_" +
                        BuiltInRegistries.BLOCK.getKey(tiny).getPath());
    }


    static void createToolRecipe(Item tool, Item mainPart, Item binding, Consumer<FinishedRecipe> pWriter) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, tool, 1)
                .requires(Items.STICK, 1)
                .requires(binding, 1)
                .requires(mainPart, 1)
                .unlockedBy(getHasName(mainPart), has(mainPart))
                .save(pWriter, EllieCraft.MOD_ID + ":" + BuiltInRegistries.ITEM.getKey(tool).getPath());
    }

    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult,
                            pExperience, pCookingTime, pCookingSerializer)
                    .group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pFinishedRecipeConsumer,  EllieCraft.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }

    private void createToolRecipes(Consumer<FinishedRecipe> pWriter) {
        createToolRecipe(Items.WOODEN_SWORD, ModItems.WOODEN_SWORD_PART.get(), ModItems.WOODEN_BINDING_PART.get(), pWriter);
        createToolRecipe(Items.WOODEN_PICKAXE, ModItems.WOODEN_PICKAXE_PART.get(), ModItems.WOODEN_BINDING_PART.get(), pWriter);
        createToolRecipe(Items.WOODEN_AXE, ModItems.WOODEN_AXE_PART.get(), ModItems.WOODEN_BINDING_PART.get(), pWriter);
        createToolRecipe(Items.WOODEN_SHOVEL, ModItems.WOODEN_SHOVEL_PART.get(), ModItems.WOODEN_BINDING_PART.get(), pWriter);
        createToolRecipe(Items.WOODEN_HOE, ModItems.WOODEN_HOE_PART.get(), ModItems.WOODEN_BINDING_PART.get(), pWriter);

        createToolRecipe(Items.STONE_SWORD, ModItems.STONE_SWORD_PART.get(), ModItems.STONE_BINDING_PART.get(), pWriter);
        createToolRecipe(Items.STONE_PICKAXE, ModItems.STONE_PICKAXE_PART.get(), ModItems.STONE_BINDING_PART.get(), pWriter);
        createToolRecipe(Items.STONE_AXE, ModItems.STONE_AXE_PART.get(), ModItems.STONE_BINDING_PART.get(), pWriter);
        createToolRecipe(Items.STONE_SHOVEL, ModItems.STONE_SHOVEL_PART.get(), ModItems.STONE_BINDING_PART.get(), pWriter);
        createToolRecipe(Items.STONE_HOE, ModItems.STONE_HOE_PART.get(), ModItems.STONE_BINDING_PART.get(), pWriter);

        createToolRecipe(Items.IRON_SWORD, ModItems.IRON_SWORD_PART.get(), ModItems.IRON_BINDING_PART.get(), pWriter);
        createToolRecipe(Items.IRON_PICKAXE, ModItems.IRON_PICKAXE_PART.get(), ModItems.IRON_BINDING_PART.get(), pWriter);
        createToolRecipe(Items.IRON_AXE, ModItems.IRON_AXE_PART.get(), ModItems.IRON_BINDING_PART.get(), pWriter);
        createToolRecipe(Items.IRON_SHOVEL, ModItems.IRON_SHOVEL_PART.get(), ModItems.IRON_BINDING_PART.get(), pWriter);
        createToolRecipe(Items.IRON_HOE, ModItems.IRON_HOE_PART.get(), ModItems.IRON_BINDING_PART.get(), pWriter);
    }

    private void createToolPartRecipes(Consumer<FinishedRecipe> pWriter) {
        createToolPartRecipe(ModItems.WOODEN_BINDING_PART.get(), ModItems.BINDING_PATTERN.get(), ItemTags.PLANKS, 1, pWriter);

        createToolPartRecipe(ModItems.WOODEN_SWORD_PART.get(),      ModItems.SWORD_PATTERN.get(),   ItemTags.PLANKS, 2, pWriter);
        createToolPartRecipe(ModItems.WOODEN_PICKAXE_PART.get(),    ModItems.PICKAXE_PATTERN.get(), ItemTags.PLANKS, 3, pWriter);
        createToolPartRecipe(ModItems.WOODEN_AXE_PART.get(),        ModItems.AXE_PATTERN.get(),     ItemTags.PLANKS, 3, pWriter);
        createToolPartRecipe(ModItems.WOODEN_SHOVEL_PART.get(),     ModItems.SHOVEL_PATTERN.get(),  ItemTags.PLANKS, 1, pWriter);
        createToolPartRecipe(ModItems.WOODEN_HOE_PART.get(),        ModItems.HOE_PATTERN.get(),     ItemTags.PLANKS, 2, pWriter);

        createToolPartRecipe(ModItems.STONE_BINDING_PART.get(), ModItems.BINDING_PATTERN.get(), Tags.Items.COBBLESTONE, 1, pWriter);

        createToolPartRecipe(ModItems.STONE_SWORD_PART.get(),      ModItems.SWORD_PATTERN.get(),   Tags.Items.COBBLESTONE, 2, pWriter);
        createToolPartRecipe(ModItems.STONE_PICKAXE_PART.get(),    ModItems.PICKAXE_PATTERN.get(), Tags.Items.COBBLESTONE, 3, pWriter);
        createToolPartRecipe(ModItems.STONE_AXE_PART.get(),        ModItems.AXE_PATTERN.get(),     Tags.Items.COBBLESTONE, 3, pWriter);
        createToolPartRecipe(ModItems.STONE_SHOVEL_PART.get(),     ModItems.SHOVEL_PATTERN.get(),  Tags.Items.COBBLESTONE, 1, pWriter);
        createToolPartRecipe(ModItems.STONE_HOE_PART.get(),        ModItems.HOE_PATTERN.get(),     Tags.Items.COBBLESTONE, 2, pWriter);
    }

    private void createToolPartRecipesCrude(Consumer<FinishedRecipe> pWriter) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STONE_SWORD_PART.get(), 1)
                .pattern("W")
                .pattern("W")
                .pattern("P")
                .define('W', Tags.Items.COBBLESTONE)
                .define('P', ModItems.CRUDE_PATTERN.get())
                .unlockedBy(getHasName(ModItems.BLANK_PATTERN.get()), has(ModItems.BLANK_PATTERN.get()))
                .save(pWriter, EllieCraft.MOD_ID + ":stone_sword_part_crude");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STONE_AXE_PART.get(), 1)
                .pattern("WW")
                .pattern("WP")
                .define('W', Tags.Items.COBBLESTONE)
                .define('P', ModItems.CRUDE_PATTERN.get())
                .unlockedBy(getHasName(ModItems.BLANK_PATTERN.get()), has(ModItems.BLANK_PATTERN.get()))
                .save(pWriter, EllieCraft.MOD_ID + ":stone_axe_part_crude");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STONE_PICKAXE_PART.get(), 1)
                .pattern("WWW")
                .pattern(" P ")
                .define('W', Tags.Items.COBBLESTONE)
                .define('P', ModItems.CRUDE_PATTERN.get())
                .unlockedBy(getHasName(ModItems.BLANK_PATTERN.get()), has(ModItems.BLANK_PATTERN.get()))
                .save(pWriter, EllieCraft.MOD_ID + ":stone_pickaxe_part_crude");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STONE_HOE_PART.get(), 1)
                .pattern("WW")
                .pattern(" P")
                .define('W', Tags.Items.COBBLESTONE)
                .define('P', ModItems.CRUDE_PATTERN.get())
                .unlockedBy(getHasName(ModItems.BLANK_PATTERN.get()), has(ModItems.BLANK_PATTERN.get()))
                .save(pWriter, EllieCraft.MOD_ID + ":stone_hoe_part_crude");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STONE_SHOVEL_PART.get(), 1)
                .pattern("W")
                .pattern("P")
                .define('W', Tags.Items.COBBLESTONE)
                .define('P', ModItems.CRUDE_PATTERN.get())
                .unlockedBy(getHasName(ModItems.BLANK_PATTERN.get()), has(ModItems.BLANK_PATTERN.get()))
                .save(pWriter, EllieCraft.MOD_ID + ":stone_shovel_part_crude");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STONE_BINDING_PART.get(), 1)
                .pattern("P")
                .pattern("W")
                .define('W', Tags.Items.COBBLESTONE)
                .define('P', ModItems.CRUDE_PATTERN.get())
                .unlockedBy(getHasName(ModItems.BLANK_PATTERN.get()), has(ModItems.BLANK_PATTERN.get()))
                .save(pWriter, EllieCraft.MOD_ID + ":stone_binding_part_crude");


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.WOODEN_SWORD_PART.get(), 1)
                .pattern("W")
                .pattern("W")
                .pattern("P")
                .define('W', ItemTags.PLANKS)
                .define('P', ModItems.CRUDE_PATTERN.get())
                .unlockedBy(getHasName(ModItems.BLANK_PATTERN.get()), has(ModItems.BLANK_PATTERN.get()))
                .save(pWriter, EllieCraft.MOD_ID + ":wooden_sword_part_crude");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.WOODEN_AXE_PART.get(), 1)
                .pattern("WW")
                .pattern("WP")
                .define('W', ItemTags.PLANKS)
                .define('P', ModItems.CRUDE_PATTERN.get())
                .unlockedBy(getHasName(ModItems.BLANK_PATTERN.get()), has(ModItems.BLANK_PATTERN.get()))
                .save(pWriter, EllieCraft.MOD_ID + ":wooden_axe_part_crude");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.WOODEN_PICKAXE_PART.get(), 1)
                .pattern("WWW")
                .pattern(" P ")
                .define('W', ItemTags.PLANKS)
                .define('P', ModItems.CRUDE_PATTERN.get())
                .unlockedBy(getHasName(ModItems.BLANK_PATTERN.get()), has(ModItems.BLANK_PATTERN.get()))
                .save(pWriter, EllieCraft.MOD_ID + ":wooden_pickaxe_part_crude");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.WOODEN_HOE_PART.get(), 1)
                .pattern("WW")
                .pattern(" P")
                .define('W', ItemTags.PLANKS)
                .define('P', ModItems.CRUDE_PATTERN.get())
                .unlockedBy(getHasName(ModItems.BLANK_PATTERN.get()), has(ModItems.BLANK_PATTERN.get()))
                .save(pWriter, EllieCraft.MOD_ID + ":wooden_hoe_part_crude");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.WOODEN_SHOVEL_PART.get(), 1)
                .pattern("W")
                .pattern("P")
                .define('W', ItemTags.PLANKS)
                .define('P', ModItems.CRUDE_PATTERN.get())
                .unlockedBy(getHasName(ModItems.BLANK_PATTERN.get()), has(ModItems.BLANK_PATTERN.get()))
                .save(pWriter, EllieCraft.MOD_ID + ":wooden_shovel_part_crude");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.WOODEN_BINDING_PART.get(), 1)
                .pattern("P")
                .pattern("W")
                .define('W', ItemTags.PLANKS)
                .define('P', ModItems.CRUDE_PATTERN.get())
                .unlockedBy(getHasName(ModItems.BLANK_PATTERN.get()), has(ModItems.BLANK_PATTERN.get()))
                .save(pWriter, EllieCraft.MOD_ID + ":wooden_binding_part_crude");
    }

    private void createPatternRecipes(Consumer<FinishedRecipe> pWriter) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BLANK_PATTERN.get(), 2)
                .pattern("PIP")
                .pattern("ICI")
                .pattern("PIP")
                .define('P', ItemTags.PLANKS)
                .define('I', ModItems.CRUDE_PATTERN.get())
                .define('C', Tags.Items.COBBLESTONE)
                .unlockedBy("has_cobblestone", has(Tags.Items.COBBLESTONE))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.CRUDE_PATTERN.get(), 2)
                .pattern("IP")
                .pattern("PI")
                .define('I', ItemTags.PLANKS)
                .define('P', ItemTags.LOGS)
                .unlockedBy("has_planks", has(ItemTags.PLANKS))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SWORD_PATTERN.get(), 1)
                .pattern("W")
                .pattern("W")
                .pattern("P")
                .define('W', Tags.Items.DYES)
                .define('P', ModItems.BLANK_PATTERN.get())
                .unlockedBy(getHasName(ModItems.BLANK_PATTERN.get()), has(ModItems.BLANK_PATTERN.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.AXE_PATTERN.get(), 1)
                .pattern("WW")
                .pattern("WP")
                .define('W', Tags.Items.DYES)
                .define('P', ModItems.BLANK_PATTERN.get())
                .unlockedBy(getHasName(ModItems.BLANK_PATTERN.get()), has(ModItems.BLANK_PATTERN.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.PICKAXE_PATTERN.get(), 1)
                .pattern("WWW")
                .pattern(" P ")
                .define('W', Tags.Items.DYES)
                .define('P', ModItems.BLANK_PATTERN.get())
                .unlockedBy(getHasName(ModItems.BLANK_PATTERN.get()), has(ModItems.BLANK_PATTERN.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.HOE_PATTERN.get(), 1)
                .pattern("WW")
                .pattern(" P")
                .define('W', Tags.Items.DYES)
                .define('P', ModItems.BLANK_PATTERN.get())
                .unlockedBy(getHasName(ModItems.BLANK_PATTERN.get()), has(ModItems.BLANK_PATTERN.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SHOVEL_PATTERN.get(), 1)
                .pattern("W")
                .pattern("P")
                .define('W', Tags.Items.DYES)
                .define('P', ModItems.BLANK_PATTERN.get())
                .unlockedBy(getHasName(ModItems.BLANK_PATTERN.get()), has(ModItems.BLANK_PATTERN.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BINDING_PATTERN.get(), 1)
                .pattern("P")
                .pattern("W")
                .define('W', Tags.Items.DYES)
                .define('P', ModItems.BLANK_PATTERN.get())
                .unlockedBy(getHasName(ModItems.BLANK_PATTERN.get()), has(ModItems.BLANK_PATTERN.get()))
                .save(pWriter);
    }

    private void createSignRecipes(Consumer<FinishedRecipe> pWriter) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SPEED_LIMIT_SIGN_30.get(), 1)
                .pattern("YRB")
                .pattern("   ")
                .pattern("   ")
                .define('Y', ModBlocks.YELLOW_METAL_SHEET_BLOCK.get())
                .define('R', Items.RED_DYE)
                .define('B', Items.BLACK_DYE)
                .unlockedBy(getHasName(ModBlocks.YELLOW_METAL_SHEET_BLOCK.get()), has(ModBlocks.YELLOW_METAL_SHEET_BLOCK.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SPEED_LIMIT_SIGN_40.get(), 1)
                .pattern("YR ")
                .pattern("B  ")
                .pattern("   ")
                .define('Y', ModBlocks.YELLOW_METAL_SHEET_BLOCK.get())
                .define('R', Items.RED_DYE)
                .define('B', Items.BLACK_DYE)
                .unlockedBy(getHasName(ModBlocks.YELLOW_METAL_SHEET_BLOCK.get()), has(ModBlocks.YELLOW_METAL_SHEET_BLOCK.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SPEED_LIMIT_SIGN_50.get(), 1)
                .pattern("YR ")
                .pattern(" B ")
                .pattern("   ")
                .define('Y', ModBlocks.YELLOW_METAL_SHEET_BLOCK.get())
                .define('R', Items.RED_DYE)
                .define('B', Items.BLACK_DYE)
                .unlockedBy(getHasName(ModBlocks.YELLOW_METAL_SHEET_BLOCK.get()), has(ModBlocks.YELLOW_METAL_SHEET_BLOCK.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SPEED_LIMIT_SIGN_60.get(), 1)
                .pattern("YR ")
                .pattern("  B")
                .pattern("   ")
                .define('Y', ModBlocks.YELLOW_METAL_SHEET_BLOCK.get())
                .define('R', Items.RED_DYE)
                .define('B', Items.BLACK_DYE)
                .unlockedBy(getHasName(ModBlocks.YELLOW_METAL_SHEET_BLOCK.get()), has(ModBlocks.YELLOW_METAL_SHEET_BLOCK.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SPEED_LIMIT_SIGN_70.get(), 1)
                .pattern("YR ")
                .pattern("   ")
                .pattern("B  ")
                .define('Y', ModBlocks.YELLOW_METAL_SHEET_BLOCK.get())
                .define('R', Items.RED_DYE)
                .define('B', Items.BLACK_DYE)
                .unlockedBy(getHasName(ModBlocks.YELLOW_METAL_SHEET_BLOCK.get()), has(ModBlocks.YELLOW_METAL_SHEET_BLOCK.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SPEED_LIMIT_SIGN_80.get(), 1)
                .pattern("YR ")
                .pattern("   ")
                .pattern(" B ")
                .define('Y', ModBlocks.YELLOW_METAL_SHEET_BLOCK.get())
                .define('R', Items.RED_DYE)
                .define('B', Items.BLACK_DYE)
                .unlockedBy(getHasName(ModBlocks.YELLOW_METAL_SHEET_BLOCK.get()), has(ModBlocks.YELLOW_METAL_SHEET_BLOCK.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BLUE_METAL_SHEET_BLOCK.get(), 1)
                .pattern(" I ")
                .pattern("IDI")
                .pattern(" I ")
                .define('I', Items.IRON_INGOT)
                .define('D', Items.BLUE_DYE)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.YELLOW_METAL_SHEET_BLOCK.get(), 1)
                .pattern(" I ")
                .pattern("IDI")
                .pattern(" I ")
                .define('I', Items.IRON_INGOT)
                .define('D', Items.YELLOW_DYE)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.RED_METAL_SHEET_BLOCK.get(), 1)
                .pattern(" I ")
                .pattern("IDI")
                .pattern(" I ")
                .define('I', Items.IRON_INGOT)
                .define('D', Items.RED_DYE)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ROUNDABOUT_SIGN.get(), 1)
                .pattern("W W")
                .pattern(" S ")
                .pattern("W W")
                .define('W', Items.WHITE_DYE)
                .define('S', ModBlocks.BLUE_METAL_SHEET_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.BLUE_METAL_SHEET_BLOCK.get()), has(ModBlocks.BLUE_METAL_SHEET_BLOCK.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.STOP_SIGN.get(), 1)
                .pattern("SW")
                .define('W', Items.WHITE_DYE)
                .define('S', ModBlocks.RED_METAL_SHEET_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.RED_METAL_SHEET_BLOCK.get()), has(ModBlocks.RED_METAL_SHEET_BLOCK.get()))
                .save(pWriter);
    }
}
