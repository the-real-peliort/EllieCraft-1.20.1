package net.ellieraven.elliecraft.worldgen.structure.stone_circle.full;

import net.ellieraven.elliecraft.worldgen.structure.ModStructures;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.TemplateStructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;


public class StoneCircleFullStructurePiece extends TemplateStructurePiece {

    public StoneCircleFullStructurePiece(StructureTemplateManager manager, BlockPos pos) {
        super(
                ModStructures.STONE_CIRCLE_PIECE.get(),
                0,
                manager,
                new ResourceLocation("elliecraft", "stone_circle/stone_circle_full"),
                "stone_circle_full",
                makeSettings(),
                pos
        );
    }

    public StoneCircleFullStructurePiece(StructurePieceSerializationContext context, CompoundTag tag) {
        super(
                ModStructures.STONE_CIRCLE_PIECE.get(),
                tag,
                context.structureTemplateManager(),
                (resourceLocation) -> makeSettings()
        );
    }

    private static StructurePlaceSettings makeSettings() {
        return new StructurePlaceSettings()
                .setMirror(Mirror.NONE)
                .setRotation(Rotation.NONE)
                .setIgnoreEntities(false);
    }

    @Override
    protected void addAdditionalSaveData(StructurePieceSerializationContext ctx, CompoundTag tag) {
        super.addAdditionalSaveData(ctx, tag);
    }

    @Override
    protected void handleDataMarker(String marker, BlockPos pos, ServerLevelAccessor level,
                                    RandomSource random, BoundingBox box) {
    }
}