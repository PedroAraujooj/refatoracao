package org.example.updaters;

import org.example.Item;

public class ConjuredUpdater implements ItemUpdater {

    @Override
    public void update(Item item) {
        decreaseQuality(item);
        item.sellIn--;

        if (item.sellIn < 0) {
            decreaseQuality(item);
        }
    }

    private void decreaseQuality(Item item) {
        item.quality = Math.max(0, item.quality - 2);
    }
}