package org.example;

import org.example.updaters.*;

import java.util.HashMap;
import java.util.Map;

/*
 * Revisão (OCP / SRP / LSP)
 *
 * 1) A estrutura atual facilita a adição de novos tipos de item? (Princípio Aberto-Fechado)
 *    Sim. As regras de atualização ficam encapsuladas em classes separadas (ItemUpdater).
 *    Para adicionar um novo tipo, você cria um novo Updater sem precisar alterar
 *    os existentes.
 *
 * 2) ItemUpdater respeita Responsabilidade Única?
 *    Sim, em geral. Cada ItemUpdater tem uma única razão para mudar: a regra de atualização daquele tipo de item.
 *    O GildedRose tem outra responsabilidade: escolher/delegar para o updater correto (roteamento).

 *
 * 3) Há violação de Liskov na hierarquia? Se sim, corrija.
 *    Não há hierarquia com herança entre updaters (apenas uma interface), então LSP tende a ser naturalmente respeitado.
 */
public class GildedRose {
    public Item[] items;

    private static final String AGED_BRIE = "Aged Brie";
    private static final String BACKSTAGE = "Backstage passes to a TAFKAL80ETC concert";
    private static final String SULFURAS  = "Sulfuras, Hand of Ragnaros";
    private static final String CONJURED_PREFIX = "Conjured";

    private final Map<String, ItemUpdater> updaters = new HashMap<>();
    private final ItemUpdater defaultUpdater = new DefaultUpdater();

    public GildedRose(Item[] items) {
        this.items = items;

        updaters.put(AGED_BRIE, new AgedBrieUpdater());
        updaters.put(BACKSTAGE, new BackstagePassUpdater());
        updaters.put(SULFURAS, new SulfurasUpdater());
        updaters.put(CONJURED_PREFIX, new ConjuredUpdater());
    }

    public void updateQuality() {
        for (Item item : items) {
            resolveUpdater(item).update(item);
        }
    }

    private ItemUpdater resolveUpdater(Item item) {
        if (item.name != null && item.name.startsWith(CONJURED_PREFIX)) {
            return updaters.get(CONJURED_PREFIX);
        }
        return updaters.getOrDefault(item.name, defaultUpdater);
    }
}