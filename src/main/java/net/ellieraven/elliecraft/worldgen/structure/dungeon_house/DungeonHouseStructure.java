package net.ellieraven.elliecraft.worldgen.structure.dungeon_house;

import com.mojang.serialization.Codec;
import net.ellieraven.elliecraft.worldgen.structure.ModStructures;
import net.ellieraven.elliecraft.worldgen.structure.stone_circle.broken_1.StoneCircleBroken1Structure;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pools.JigsawPlacement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class DungeonHouseStructure extends Structure {
    protected DungeonHouseStructure(StructureSettings pSettings) {
        super(pSettings);
    }

    public static final Logger LOGGER = LogManager.getLogger();
    public static final Codec<DungeonHouseStructure> CODEC = simpleCodec(DungeonHouseStructure::new);

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
            LOGGER.warn("Dungeon House skipped at {} {}", x, z);
            return Optional.empty();
        }

        return Optional.of(new GenerationStub(
                new BlockPos(x, y - 3, z),
                builder -> {
                    // Add the house piece
                    BlockPos housePos = new BlockPos(x, y - 3, z);
                    builder.addPiece(new DungeonHouseStructurePiece(
                            pContext.structureTemplateManager(), housePos
                    ));

                    // Get the jigsaw block position from the template
                    StructureTemplate template = pContext.structureTemplateManager()
                            .getOrCreate(new ResourceLocation("elliecraft", "dungeon/dungeon_house"));

                    StructurePlaceSettings settings = new StructurePlaceSettings()
                            .setMirror(Mirror.NONE)
                            .setRotation(Rotation.NONE);

                    for (StructureTemplate.StructureBlockInfo blockInfo : template.filterBlocks(
                            housePos, settings, Blocks.JIGSAW)) {

                        LOGGER.info("Jigsaw blockstate: {}", blockInfo.state());

                        LOGGER.info("Found jigsaw in template at {}", blockInfo.pos());

                        Registry<StructureTemplatePool> pools = pContext.registryAccess()
                                .registryOrThrow(Registries.TEMPLATE_POOL);
                        Holder<StructureTemplatePool> poolHolder = pools.getHolderOrThrow(
                                ResourceKey.create(Registries.TEMPLATE_POOL,
                                        new ResourceLocation("elliecraft", "dungeon_center_staircase_start"))
                        );


                        JigsawPlacement.addPieces(
                                pContext,
                                poolHolder,
                                Optional.of(new ResourceLocation("elliecraft", "house_entry")),
                                35,
                                blockInfo.pos(),
                                false,
                                Optional.empty(),
                                128
                        ).ifPresent(stub -> stub.generator().ifLeft(consumer -> consumer.accept(builder)));
                        break;
                    }
                }
        ));
    }

    @Override
    public StructureType<?> type() {
        return ModStructures.DUNGEON_HOUSE.get();
    }

    @Override
    public GenerationStep.Decoration step() {
        return GenerationStep.Decoration.SURFACE_STRUCTURES;
    }
}
