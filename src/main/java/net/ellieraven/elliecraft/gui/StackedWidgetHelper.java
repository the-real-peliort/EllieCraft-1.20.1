package net.ellieraven.elliecraft.gui;


import java.util.*;

public class StackedWidgetHelper {
    public int spacing;
    public int widgetHeight;
    public int progress = 0;
    public int freeSpaceProgress = 0;

    public List<ButtonBounds> freeSpaces = new ArrayList<>();

    StackedWidgetHelper(int spacing, int widgetHeight) {
        this.spacing = spacing;
        this.widgetHeight = widgetHeight;
    }

    public ButtonBounds getNextButtonBounds() {
        ButtonBounds properties = new ButtonBounds(spacing, spacing + (widgetHeight + spacing) * progress, 64, widgetHeight);
        progress += 1;
        return properties;
    }

    public void setTextFreeSpace() {
        freeSpaces.add(new ButtonBounds(spacing, spacing + (widgetHeight + spacing) * progress + 4, 0, 0));
        progress += 1;
    }

    public ButtonBounds getNextFreeSpace() {
        ButtonBounds freeSpace = freeSpaces.get(freeSpaceProgress);
        freeSpaceProgress += 1;
        return  freeSpace;
    }

    public void resetFreeSpace() {
        freeSpaceProgress = 0;
    }

    public void resetButtons() {
        progress = 0;
    }
    public void resetAll() {
        progress = 0;
        freeSpaces.clear();
        freeSpaceProgress = 0;
    }
}
