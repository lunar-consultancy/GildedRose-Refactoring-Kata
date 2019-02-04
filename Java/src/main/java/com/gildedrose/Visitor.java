package com.gildedrose;

interface Visitor {

    void visit(AgedBrieItem agedBrieItem);

    void visit(ConcertItem concertItem);

    void visit(ConjuredItem conjuredItem);

    void visit(OtherItem otherItem);

    void visit(SulfurasItem sulfurasItem);

}
