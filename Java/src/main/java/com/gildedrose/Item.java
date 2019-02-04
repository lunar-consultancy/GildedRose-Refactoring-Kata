package com.gildedrose;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
class Item {

    private String name;
    private int sellIn;
    private int quality;

}
