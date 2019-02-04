package com.gildedrose;

final class SulfurasItem extends VisitableItem {

    SulfurasItem(final int sellIn, final int quality) {
        super("Sulfuras, Hand of Ragnaros", sellIn, quality);
    }

    @Override
    void accept(final Visitor visitor) {
        visitor.visit(this);
    }
}
