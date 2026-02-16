
import org.example.*;
import org.example.updaters.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemUpdatersTest {

    @Test
    void agedBrie_aumentaQualidade_ateNoMaximo50() {
        ItemUpdater updater = new AgedBrieUpdater();

        Item brieNoLimite = new Item("Aged Brie", 10, 50);
        updater.update(brieNoLimite);

        assertEquals(50, brieNoLimite.quality, "A qualidade do Aged Brie não pode passar de 50.");
        assertEquals(9, brieNoLimite.sellIn, "sellIn deve reduzir em 1.");
    }

    @Test
    void sulfuras_naoSofreAlteracao() {
        ItemUpdater updater = new SulfurasUpdater();

        Item sulfuras = new Item("Sulfuras, Hand of Ragnaros", 0, 80);
        updater.update(sulfuras);

        assertEquals("Sulfuras, Hand of Ragnaros", sulfuras.name);
        assertEquals(0, sulfuras.sellIn, "Sulfuras não deve alterar sellIn.");
        assertEquals(80, sulfuras.quality, "Sulfuras não deve alterar quality.");
    }

    @Test
    void backstagePass_zeraQualidade_aposDataDeVenda() {
        ItemUpdater updater = new BackstagePassUpdater();

        Item backstage = new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20);
        updater.update(backstage);

        assertEquals(-1, backstage.sellIn, "sellIn deve reduzir em 1.");
        assertEquals(0, backstage.quality, "Backstage Pass deve zerar quality após a data de venda.");
    }

    @Test
    void conjured_perdeQualidade_2xMaisRapidoQueDefault() {
        ItemUpdater conjuredUpdater = new ConjuredUpdater();
        ItemUpdater defaultUpdater = new DefaultUpdater();

        Item conjured = new Item("Conjured Mana Cake", 10, 20);
        Item normal = new Item("+5 Dexterity Vest", 10, 20);

        conjuredUpdater.update(conjured);
        defaultUpdater.update(normal);

        assertEquals(9, conjured.sellIn, "sellIn do conjured deve reduzir em 1.");
        assertEquals(9, normal.sellIn, "sellIn do item normal deve reduzir em 1.");

        assertEquals(18, conjured.quality, "Conjured deve perder 2 de quality antes de expirar.");
        assertEquals(19, normal.quality, "Item normal deve perder 1 de quality antes de expirar.");

        assertEquals(2, 20 - conjured.quality);
        assertEquals(1, 20 - normal.quality);
    }
}
