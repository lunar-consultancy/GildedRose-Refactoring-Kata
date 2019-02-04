package com.gildedrose;

class GildedRose {

    private final Item[] items;

    GildedRose(final Item[] items) {
        this.items = items;
    }

    // Ignore warnings -> needs to be refactored
    @SuppressWarnings({"checkstyle:cyclomaticcomplexity", "checkstyle:methodlength", "checkstyle:magicnumber"})
    void updateQuality() {
        for (final Item item : items) {
            if (!(item instanceof AgedBrieItem)
                    && !(item instanceof ConcertItem)) {
                if (item.getQuality() > 0) {
                    if (!(item instanceof SulfurasItem)) {
                        item.setQuality(item.getQuality() - 1);
                    }
                }
            } else {
                if (item.getQuality() < 50) {
                    item.setQuality(item.getQuality() + 1);

                    if (item instanceof ConcertItem) {
                        if (item.getSellIn() < 11) {
                            if (item.getQuality() < 50) {
                                item.setQuality(item.getQuality() + 1);
                            }
                        }

                        if (item.getSellIn() < 6) {
                            if (item.getQuality() < 50) {
                                item.setQuality(item.getQuality() + 1);
                            }
                        }
                    }
                }
            }

            if (!(item instanceof SulfurasItem)) {
                item.setSellIn(item.getSellIn() - 1);
            }

            if (item.getSellIn() < 0) {
                if (!(item instanceof AgedBrieItem)) {
                    if (!(item instanceof ConcertItem)) {
                        if (item.getQuality() > 0) {
                            if (!(item instanceof SulfurasItem)) {
                                item.setQuality(item.getQuality() - 1);
                            }
                        }
                    } else {
                        item.setQuality(item.getQuality() - item.getQuality());
                    }
                } else {
                    if (item.getQuality() < 50) {
                        item.setQuality(item.getQuality() + 1);
                    }
                }
            }
        }
    }

    Item[] getItems() {
        return items;
    }
}
