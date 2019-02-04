package com.gildedrose;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class GildedRose {

    private final ItemRepository itemRepository;
    private final ItemVisitor itemVisitor;

    @Autowired
    GildedRose(final ItemRepository itemRepository, final ItemVisitor itemVisitor) {
        this.itemRepository = itemRepository;
        this.itemVisitor = itemVisitor;
    }

    // Ignore warnings -> needs to be refactored
    @SuppressWarnings({"checkstyle:cyclomaticcomplexity", "checkstyle:methodlength", "checkstyle:magicnumber"})
    void updateQuality() {
        itemRepository.getItems().forEach(item -> item.accept(itemVisitor));
    }
}
