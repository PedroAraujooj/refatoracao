package org.example.updaters;

import org.example.Item;

public class Updaters {
    private Updaters() {}

    static void increaseQuality(Item item) {
        if (item.quality < 50) item.quality++;
    }

    static void decreaseQuality(Item item) {
        if (item.quality > 0) item.quality--;
    }

    static void decreaseSellIn(Item item) {
        item.sellIn--;
    }
}

