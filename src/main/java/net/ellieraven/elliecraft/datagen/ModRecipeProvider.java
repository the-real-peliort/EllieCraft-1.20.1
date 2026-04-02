package net.ellieraven.elliecraft.datagen;

import com.mojang.datafixers.types.templates.Tag;
import net.ellieraven.elliecraft.EllieCraft;
import net.ellieraven.elliecraft.block.ModBlocks;
import net.ellieraven.elliecraft.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
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
                .pattern("P P")
                .pattern("P P")
                .pattern("P P")
                .define('P', Blocks.OAK_PRESSURE_PLATE)
                .unlockedBy(getHasName(Blocks.CRAFTING_TABLE), has(Blocks.CRAFTING_TABLE))
                .save(pWriter, EllieCraft.MOD_ID + ":tunnel_vertical");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.TUNNEL.get(), 4)
                .pattern("PPP")
                .pattern("   ")
                .pattern("PPP")
                .define('P', Blocks.OAK_PRESSURE_PLATE)
                .unlockedBy(getHasName(Blocks.CRAFTING_TABLE), has(Blocks.CRAFTING_TABLE))
                .save(pWriter, EllieCraft.MOD_ID + ":tunnel_horizontal");

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
                .unlockedBy(getHasName(ModBlocks.STONE_PILLAR.get()), has(ModBlocks.STONE_PILLAR.get()))
                .save(pWriter, EllieCraft.MOD_ID + ":stone_pillar_from_connecting_stone_pillar");

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
                .save(pWriter, EllieCraft.MOD_ID + ":obsidian_rod_right");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.OBSIDIAN_ROD.get())
                .pattern("O  ")
                .pattern(" O ")
                .pattern("  O")
                .define('O', Blocks.OBSIDIAN)
                .unlockedBy(getHasName(Blocks.OBSIDIAN), has(Blocks.OBSIDIAN))
                .save(pWriter, EllieCraft.MOD_ID + ":obsidian_rod_left");

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
                .save(pWriter, EllieCraft.MOD_ID + ":metal_detector_left");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.METAL_DETECTOR.get())
                .pattern(" RD")
                .pattern(" SE")
                .pattern("S  ")
                .define('S', Items.STICK)
                .define('R', Items.REDSTONE)
                .define('D', Items.DIAMOND)
                .define('E', Items.EMERALD)
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(pWriter, EllieCraft.MOD_ID + ":metal_detector_right");

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

        createBrickRecipe(ModBlocks.BLACK_BRICKS.get(), "black_bricks", Items.BLACK_DYE, pWriter);
        createBrickRecipe(ModBlocks.BLUE_BRICKS.get(), "blue_bricks", Items.BLUE_DYE, pWriter);
        createBrickRecipe(ModBlocks.YELLOW_BRICKS.get(), "yellow_bricks", Items.YELLOW_DYE, pWriter);
        createBrickRecipe(ModBlocks.PINK_BRICKS.get(), "pink_bricks", Items.PINK_DYE, pWriter);

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
}
