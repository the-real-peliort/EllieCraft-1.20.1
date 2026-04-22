package net.ellieraven.elliecraft.gui;

import net.minecraft.client.gui.components.SplashRenderer;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.awt.*;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class SplashHandler {
    private static int ticks = 0;

    private static int index = 0;

    private static final String[] LOOP = {
            "-",
            "/",
            "|",
            "\\",
    };

    List<BiConsumer<TitleScreen, Integer>> SPLASHES = List.of(
            (title, ticks) ->  {
                setSplash(title, LOOP[(int)Math.floor((double)ticks/10) % LOOP.length]);
            },
            (title, ticks) ->  {
                setSplash(title, "EllieCraft :3");
            },
            (title, ticks) ->  {
                setSplash(title, ":3");
            }
    );

    @SubscribeEvent
    public void onRender(ScreenEvent.Render.Post event) {
        if (event.getScreen() instanceof TitleScreen title) {
            ticks++;


            SPLASHES.get(index).accept(title, ticks);
        }
    }

    @SubscribeEvent
    public void onScreenOpen(ScreenEvent.Opening event) {
        if (event.getScreen() instanceof TitleScreen title) {
            index = new Random().nextInt(0, SPLASHES.toArray().length);
        }
    }

    private void setSplash(TitleScreen title, String text) {
        try {
            SplashRenderer renderer = new SplashRenderer(text);
            Field splashField = TitleScreen.class.getDeclaredField("splash");
            splashField.setAccessible(true);
            splashField.set(title, renderer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
