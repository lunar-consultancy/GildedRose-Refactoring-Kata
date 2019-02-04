package com.gildedrose;

final class ConcertItem extends VisitableItem {

    ConcertItem(final int sellIn, final int quality) {
        super("Backstage passes to a TAFKAL80ETC concert", sellIn, quality);
    }

    @Override
    void accept(final Visitor visitor) {
        visitor.visit(this);
    }
}
