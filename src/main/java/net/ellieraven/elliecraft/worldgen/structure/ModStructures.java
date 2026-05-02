package net.ellieraven.elliecraft.worldgen.structure;

import net.ellieraven.elliecraft.worldgen.structure.dungeon_house.DungeonHouseStructure;
import net.ellieraven.elliecraft.worldgen.structure.dungeon_house.DungeonHouseStructurePiece;
import net.ellieraven.elliecraft.worldgen.structure.stone_circle.broken_1.StoneCircleBroken1Structure;
import net.ellieraven.elliecraft.worldgen.structure.stone_circle.broken_1.StoneCircleBroken1StructurePiece;
import net.ellieraven.elliecraft.worldgen.structure.stone_circle.full.StoneCircleFullStructure;
import net.ellieraven.elliecraft.worldgen.structure.stone_circle.full.StoneCircleFullStructurePiece;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;


public class ModStructures{
    public static final DeferredRegister<StructureType<?>> STRUCTURE_TYPES =
            DeferredRegister.create(Registries.STRUCTURE_TYPE, "elliecraft");

    public static final DeferredRegister<StructurePieceType> STRUCTURE_PIECES =
            DeferredRegister.create(Registries.STRUCTURE_PIECE, "elliecraft");

    // Stone Circle
    public static final RegistryObject<StructureType<StoneCircleFullStructure>> STONE_CIRCLE_FULL =
            STRUCTURE_TYPES.register("stone_circle_full",
                    () -> () -> StoneCircleFullStructure.CODEC
            );

    public static final RegistryObject<StructurePieceType> STONE_CIRCLE_FULL_PIECE =
            STRUCTURE_PIECES.register("stone_circle_full_piece",
                    () -> StoneCircleFullStructurePiece::new
            );

    public static final RegistryObject<StructureType<StoneCircleBroken1Structure>> STONE_CIRCLE_BROKEN_1 =
            STRUCTURE_TYPES.register("stone_circle_broken_1",
                    () -> () -> StoneCircleBroken1Structure.CODEC
            );

    public static final RegistryObject<StructurePieceType> STONE_CIRCLE_BROKEN_1_PIECE =
            STRUCTURE_PIECES.register("stone_circle_broken_1_piece",
                    () -> StoneCircleBroken1StructurePiece::new
            );

    public static final RegistryObject<StructureType<DungeonHouseStructure>> DUNGEON_HOUSE =
            STRUCTURE_TYPES.register("dungeon_house",
                    () -> () -> DungeonHouseStructure.CODEC
            );

    public static final RegistryObject<StructurePieceType> DUNGEON_HOUSE_PIECE =
            STRUCTURE_PIECES.register("dungeon_house_piece",
                    () -> DungeonHouseStructurePiece::new
            );

}
