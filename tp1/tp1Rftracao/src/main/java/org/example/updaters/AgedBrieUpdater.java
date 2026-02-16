package org.example.updaters;

import org.example.Item;

import static org.example.updaters.Updaters.decreaseSellIn;
import static org.example.updaters.Updaters.increaseQuality;

public class AgedBrieUpdater implements ItemUpdater {
    @Override
    public void update(Item item) {
        increaseQuality(item);
        decreaseSellIn(item);

        if (item.sellIn < 0) {
            increaseQuality(item);
        }
    }
}