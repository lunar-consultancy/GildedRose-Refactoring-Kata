package com.gildedrose;

abstract class VisitableItem extends Item {

    VisitableItem(final String name, final int sellIn, final int quality) {
        super(name, sellIn, quality);
    }

    abstract void accept(Visitor visitor);

}
