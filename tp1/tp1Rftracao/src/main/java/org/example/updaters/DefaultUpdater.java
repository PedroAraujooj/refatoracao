package org.example.updaters;

import org.example.Item;

import static org.example.updaters.Updaters.decreaseQuality;
import static org.example.updaters.Updaters.decreaseSellIn;

public class DefaultUpdater implements ItemUpdater {
    @Override
    public void update(Item item) {
        decreaseQuality(item);
        decreaseSellIn(item);

        if (item.sellIn < 0) {
            decreaseQuality(item);
        }
    }
}