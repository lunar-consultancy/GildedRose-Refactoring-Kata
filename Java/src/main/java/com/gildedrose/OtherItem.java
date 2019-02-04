package com.gildedrose;

final class OtherItem extends VisitableItem {

    OtherItem(final String name, final int sellIn, final int quality) {
        super(name, sellIn, quality);
    }

    @Override
    void accept(final Visitor visitor) {
        visitor.visit(this);
    }
}
