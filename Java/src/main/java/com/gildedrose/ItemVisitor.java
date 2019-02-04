package com.gildedrose;

import org.springframework.stereotype.Component;

@Component
public class ItemVisitor implements Visitor {

    private static final int MAXIMUM_QUALITY = 50;
    private static final int MINIMAL_QUALITY = 0;
    private static final int MIN_SELL_IN_DATE = 0;
    private static final int CONCERT_THRESHOLD_10 = 10;
    private static final int CONCERT_THRESHOLD_5 = 5;

    @Override
    public void visit(final AgedBrieItem agedBrieItem) {
        incrementQuality(agedBrieItem);
        decrementSellIn(agedBrieItem);

        if (pastSellDate(agedBrieItem)) {
            incrementQuality(agedBrieItem);
        }
    }

    @Override
    public void visit(final ConcertItem concertItem) {
        incrementQuality(concertItem);

        if (concertItem.getSellIn() <= CONCERT_THRESHOLD_10) {
            incrementQuality(concertItem);
            if (concertItem.getSellIn() <= CONCERT_THRESHOLD_5) {
                incrementQuality(concertItem);
            }
        }

        decrementSellIn(concertItem);

        if (pastSellDate(concertItem)) {
            concertItem.setQuality(MINIMAL_QUALITY);
        }
    }

    @Override
    public void visit(final ConjuredItem conjuredItem) {
        decrementQuality(conjuredItem, 2);
        decrementSellIn(conjuredItem);
    }

    @Override
    public void visit(final OtherItem otherItem) {
        decrementQuality(otherItem);
        decrementSellIn(otherItem);

        if (pastSellDate(otherItem)) {
            decrementQuality(otherItem);
        }
    }

    @Override
    public void visit(final SulfurasItem sulfurasItem) {
        // "Sulfuras", being a legendary item, never has to be sold or decreases in Quality
    }

    private boolean pastSellDate(final Item item) {
        return item.getSellIn() < MIN_SELL_IN_DATE;
    }

    private void decrementQuality(final Item item) {
        decrementQuality(item, 1);
    }

    private void decrementQuality(final Item item, final int decrement) {
        if (item.getQuality() > MINIMAL_QUALITY) {
            item.setQuality(item.getQuality() - decrement);
        }
    }

    private void incrementQuality(final Item item) {
        if (item.getQuality() < MAXIMUM_QUALITY) {
            item.setQuality(item.getQuality() + 1);
        }
    }

    private void decrementSellIn(final Item item) {
        item.setSellIn(item.getSellIn() - 1);
    }
}
