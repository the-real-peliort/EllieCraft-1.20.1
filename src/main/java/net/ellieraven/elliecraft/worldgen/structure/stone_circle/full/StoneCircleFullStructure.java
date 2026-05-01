package net.ellieraven.elliecraft.worldgen.structure.stone_circle.full;

import com.mojang.serialization.Codec;
import net.ellieraven.elliecraft.worldgen.structure.ModStructures;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class StoneCircleFullStructure extends Structure {
    protected StoneCircleFullStructure(StructureSettings pSettings) {
        super(pSettings);
    }

    public static final Logger LOGGER = LogManager.getLogger();
    public static final Codec<StoneCircleFullStructure> CODEC = simpleCodec(StoneCircleFullStructure::new);

    @Override
    protected Optional<GenerationStub> findGenerationPoint(GenerationContext pContext) {

        ChunkPos chunk = pContext.chunkPos();

        int x = chunk.getMinBlockX() + pContext.random().nextInt(16);
        int z = chunk.getMinBlockZ() + pContext.random().nextInt(16);

        int y = pContext.chunkGenerator().getBaseHeight(
                x, z,
                Heightmap.Types.OCEAN_FLOOR_WG,
                pContext.heightAccessor(),
                pContext.randomState()
        );

        if (y < 63) {
            LOGGER.warn("Stone circle skipped at {} {}", x, z);
            return Optional.empty();
        }

        return Optional.of(new GenerationStub(
                new BlockPos(x, y, z),
                builder -> builder.addPiece(
                        new StoneCircleFullStructurePiece(
                                pContext.structureTemplateManager(),
                                new BlockPos(x, y-3, z)
                        )
                )
        ));
    }

    @Override
    public StructureType<?> type() {
        return ModStructures.STONE_CIRCLE.get();
    }

    @Override
    public GenerationStep.Decoration step() {
        return GenerationStep.Decoration.SURFACE_STRUCTURES;
    }
}
