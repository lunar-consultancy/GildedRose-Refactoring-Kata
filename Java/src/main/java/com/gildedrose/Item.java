package com.gildedrose;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
class Item {

    private String name;
    private int sellIn;
    private int quality;

}
