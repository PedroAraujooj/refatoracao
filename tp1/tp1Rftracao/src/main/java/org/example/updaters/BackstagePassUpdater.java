package org.example.updaters;

import org.example.Item;

import static org.example.updaters.Updaters.decreaseSellIn;
import static org.example.updaters.Updaters.increaseQuality;

public class BackstagePassUpdater  implements ItemUpdater {
    @Override
    public void update(Item item) {
        increaseQuality(item);

        if (item.sellIn < 11) increaseQuality(item);
        if (item.sellIn < 6)  increaseQuality(item);

        decreaseSellIn(item);

        if (item.sellIn < 0) {
            item.quality = 0;
        }
    }
}