package org.example;

public class GildedRoseOld {
    public Item[] items;

    private static final String AGED_BRIE = "Aged Brie";
    private static final String BACKSTAGE = "Backstage passes to a TAFKAL80ETC concert";
    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";

    public GildedRoseOld(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            updateQualityForItem(item);
        }
    }

    // dispatcher (decide por tipo)
    private void updateQualityForItem(Item item) {
        if (isSulfuras(item)) {
            updateSulfuras(item);
            return;
        }

        // atualiza quality considerando o dia corrente
        if (isAgedBrie(item)) {
            updateAgedBrie(item);
        } else if (isBackstage(item)) {
            updateBackstagePasses(item);
        } else {
            updateNormalItem(item);
        }

        // passa o dia (sellIn--)
        decreaseSellIn(item);

        // P aplica regras de expiracao (sellIn < 0)
        if (item.sellIn < 0) {
            applyExpiredRules(item);
        }
    }

    // metodos por tipo (>= 3)
    private void updateAgedBrie(Item item) {
        increaseQualityIfPossible(item);
    }

    private void updateSulfuras(Item item) {
        // nao muda quality nem sellIn
    }

    private void updateBackstagePasses(Item item) {
        increaseQualityIfPossible(item);

        if (item.sellIn < 11) {
            increaseQualityIfPossible(item);
        }
        if (item.sellIn < 6) {
            increaseQualityIfPossible(item);
        }
    }

    private void updateNormalItem(Item item) {
        decreaseQualityIfPossible(item);
    }

    // Regra pos-expiracao
    private void applyExpiredRules(Item item) {
        if (isAgedBrie(item)) {
            increaseQualityIfPossible(item);
            return;
        }

        if (isBackstage(item)) {
            item.quality = 0;
            return;
        }

        // Normal (sulfuras nao chega aqui)
        decreaseQualityIfPossible(item);
    }

    // Helpers (reduzem repeticao)
    private void decreaseSellIn(Item item) {
        item.sellIn = item.sellIn - 1;
    }

    private void increaseQualityIfPossible(Item item) {
        if (item.quality < 50) {
            item.quality = item.quality + 1;
        }
    }

    private void decreaseQualityIfPossible(Item item) {
        if (item.quality > 0) {
            item.quality = item.quality - 1;
        }
    }

    private boolean isAgedBrie(Item item) {
        return AGED_BRIE.equals(item.name);
    }

    private boolean isBackstage(Item item) {
        return BACKSTAGE.equals(item.name);
    }

    private boolean isSulfuras(Item item) {
        return SULFURAS.equals(item.name);
    }
}