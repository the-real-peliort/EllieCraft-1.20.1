package net.ellieraven.elliecraft.gui.book;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class EllieCraftBookScreen extends Screen {
    public EllieCraftBookScreen(Component pTitle) {
        super(pTitle);
    }
    public int page = 1;

    public int pastelPink = 0xFFC5D3;

    public List<Runnable> widgetPages = new ArrayList<>();
    public List<Consumer<GuiGraphics>> renderPages = new ArrayList<>();

    StackedWidgetHelper helper = new StackedWidgetHelper(4, 16);

    @Override
    protected void init() {

        helper.resetButtons();

        widgetPages.add(this::widgetPage1);
        widgetPages.add(this::widgetPage2);

        renderPages.add(guiGraphics -> drawPage1(guiGraphics));
        renderPages.add(guiGraphics -> drawPage2(guiGraphics));

        widgetPages.get(page - 1).run();
        pageWidgets();
    }

    private void testButton() {
        minecraft.getInstance().player.sendSystemMessage(Component.literal("TESTING :3"));
    }


    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        this.renderBackground(pGuiGraphics);

        renderPages.get(page - 1).accept(pGuiGraphics);

        //page numbering
        pGuiGraphics.drawCenteredString(this.font, Integer.toString(page), this.width/2, this.height - helper.spacing - 16, pastelPink);

        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
    }

    @Override
    public boolean mouseScrolled(double pMouseX, double pMouseY, double pDelta) {
        return super.mouseScrolled(pMouseX, pMouseY, pDelta);
    }

    float easeInOut(float t) {
        // t = progress from 0 to 1
        if (t < 0.5f) {
            return 2 * t * t;         // first half: ease-in
        } else {
            return 1 - 2 * (1 - t) * (1 - t); // second half: ease-out
        }
    }

    public void drawPage1(GuiGraphics pGuiGraphics) {
        helper.resetFreeSpace();
        ButtonBounds currentFreeSpace;
        currentFreeSpace = helper.getNextFreeSpace();
        pGuiGraphics.drawString(this.font, "EllieCraft", currentFreeSpace.X, currentFreeSpace.Y, pastelPink);
        currentFreeSpace = helper.getNextFreeSpace();
        pGuiGraphics.drawString(this.font, "Merp :3", currentFreeSpace.X, currentFreeSpace.Y, pastelPink);
    }

    public void widgetPage1() {
        clearWidgets();
        ButtonBounds currentBounds;

        helper.setTextFreeSpace();

        currentBounds = helper.getNextButtonBounds();
        addRenderableWidget(
                Button.builder(Component.literal("Test :3"),
                                pButton -> testButton())
                        .bounds(currentBounds.X, currentBounds.Y,
                                currentBounds.W, currentBounds.H)
                        .build()
        );

        helper.setTextFreeSpace(); //merp

        currentBounds = helper.getNextButtonBounds();
        addRenderableWidget(
                Button.builder(Component.literal("Close"),
                                pButton -> this.onClose())
                        .bounds(currentBounds.X, currentBounds.Y,
                                currentBounds.W, currentBounds.H)
                        .build()
        );

        addRenderableWidget(
                Button.builder(Component.literal("FUN"),
                                pButton -> {})
                        .bounds(currentBounds.X + currentBounds.W + helper.spacing, currentBounds.Y,
                                currentBounds.W, currentBounds.H)
                        .build()
        );
    }

    public void widgetPage2() {
        clearWidgets();
        ButtonBounds currentBounds;

        helper.setTextFreeSpace();
    }

    public void drawPage2(GuiGraphics pGuiGraphics) {
        helper.resetFreeSpace();
        ButtonBounds currentFreeSpace;
        currentFreeSpace = helper.getNextFreeSpace();
        pGuiGraphics.drawString(this.font, "EllieCraft Page 2", currentFreeSpace.X, currentFreeSpace.Y, pastelPink);
    }

    public void pageWidgets() {

        int buttonSize = 24;

        addRenderableWidget(
                Button.builder(Component.literal("<"),
                                pButton -> pageLeft())
                        .bounds(helper.spacing, height - helper.spacing - buttonSize,
                                buttonSize, buttonSize)
                        .build()
        );

        addRenderableWidget(
                Button.builder(Component.literal(">"),
                                pButton -> pageRight())
                        .bounds(width - helper.spacing - buttonSize, height - helper.spacing - buttonSize,
                                buttonSize, buttonSize)
                        .build()
        );

        addRenderableWidget(
                Button.builder(Component.literal("X"),
                                pButton -> this.onClose())
                        .bounds(width - helper.spacing - buttonSize, helper.spacing,
                                buttonSize, buttonSize)
                        .build()
        );
    }

    public void pageRight() {
        page += 1;
        if (page == widgetPages.size() + 1) {
            page = 1;
        }
        helper.resetAll();
        widgetPages.get(page - 1).run();
        pageWidgets();
    }

    public void pageLeft() {
        page -= 1;
        if (page == 0) {
            page = widgetPages.size();
        }
        helper.resetAll();
        widgetPages.get(page - 1).run();
        pageWidgets();
    }
}
