package net.ellieraven.elliecraft;

import com.mojang.logging.LogUtils;

import net.ellieraven.elliecraft.block.ModBlocks;
import net.ellieraven.elliecraft.dispenser.ModDispenserBehaviours;
import net.ellieraven.elliecraft.gui.SplashHandler;
import net.ellieraven.elliecraft.item.ModCreativeModeTabs;
import net.ellieraven.elliecraft.item.ModItems;
import net.ellieraven.elliecraft.client.ModKeybinds;
import net.ellieraven.elliecraft.item.custom.PestleAndMortarItem;
import net.ellieraven.elliecraft.network.PacketHandler;
import net.ellieraven.elliecraft.worldgen.structure.ModStructures;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.GrassColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(EllieCraft.MOD_ID)
public class EllieCraft
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "elliecraft";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public EllieCraft()
    {
        FMLJavaModLoadingContext context = FMLJavaModLoadingContext.get();

        IEventBus modEventBus = context.getModEventBus();
        ModCreativeModeTabs.register(modEventBus);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        ModStructures.STRUCTURE_TYPES.register(modEventBus);
        ModStructures.STRUCTURE_PIECES.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(new SplashHandler());

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);

        PacketHandler.register();
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(ModDispenserBehaviours::register);
        event.enqueueWork(PestleAndMortarItem::initRecipes);
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.SAPPHIRE);
            event.accept(ModItems.RAW_SAPPHIRE);
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            event.enqueueWork(() -> {
                ItemBlockRenderTypes.setRenderLayer(
                        ModBlocks.BUSH.get(),
                        RenderType.cutout()
                );
            });

            Minecraft.getInstance().getBlockColors().register(
                    (state, level, pos, tintIndex) -> {
                        return level != null && pos != null
                                ? Minecraft.getInstance().level.getBiome(pos)
                                .value().getGrassColor(pos.getX(), pos.getZ())
                                : GrassColor.get(0.5D, 1.0D);
                    },
                    ModBlocks.PASSTHROUGH_BLOCK_GRASS.get()
            );

        }

        @SubscribeEvent
        public static void registerKeys(RegisterKeyMappingsEvent event) {
            event.register(ModKeybinds.END_BLADE_KEY);
        }
    }
}
