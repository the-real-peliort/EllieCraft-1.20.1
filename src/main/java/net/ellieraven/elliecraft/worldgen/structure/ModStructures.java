package net.ellieraven.elliecraft.worldgen.structure;

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
    public static final RegistryObject<StructureType<StoneCircleFullStructure>> STONE_CIRCLE =
            STRUCTURE_TYPES.register("stone_circle_full",
                    () -> () -> StoneCircleFullStructure.CODEC
            );

    public static final RegistryObject<StructurePieceType> STONE_CIRCLE_PIECE =
            STRUCTURE_PIECES.register("stone_circle_full_piece",
                    () -> StoneCircleFullStructurePiece::new
            );

}
