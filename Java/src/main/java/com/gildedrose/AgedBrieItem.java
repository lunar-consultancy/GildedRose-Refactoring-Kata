package com.gildedrose;

final class AgedBrieItem extends VisitableItem {

    AgedBrieItem(final int sellIn, final int quality) {
        super("Aged Brie", sellIn, quality);
    }

    @Override
    void accept(final Visitor visitor) {
        visitor.visit(this);
    }
}
