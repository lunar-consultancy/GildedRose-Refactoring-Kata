package com.gildedrose;

import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class GildedRoseIT {

    @Test
    public void ensureThatSellInAndQualityIsLoweredAtTheEndOfTheDay() {
        // given
        Item[] items = new Item[] {new Item("item", 10, 20)};
        GildedRose app = new GildedRose(items);
        // when
        app.updateQuality();
        // then
        assertThat(app.items[0].quality, is(equalTo(19)));
        assertThat(app.items[0].quality, is(equalTo(19)));
    }

    @Test
    public void ensureThatQualityNeverGetsNegative() {
        // given
        Item[] items = new Item[] {new Item("item", 10, 0)};
        GildedRose app = new GildedRose(items);
        // when
        app.updateQuality();
        // then
        assertThat(app.items[0].sellIn, is(equalTo(9)));
        assertThat(app.items[0].quality, is(equalTo(0)));
    }

    @Test
    public void ensureThatAgedBrieIncreasesInQuality() {
        // given
        Item[] items = new Item[] {new Item("Aged Brie", 10, 20)};
        GildedRose app = new GildedRose(items);
        // when
        app.updateQuality();
        // then
        assertThat(app.items[0].sellIn, is(equalTo(9)));
        assertThat(app.items[0].quality, is(equalTo(21)));
    }

    @Test
    public void ensureThatQualityNeverIncreasesAbove50() {
        // given
        Item[] items = new Item[] {new Item("Aged Brie", 2, 0)};
        GildedRose app = new GildedRose(items);
        // when
        for (int i = 0; i < 75; i++) {
            app.updateQuality();
        }
        // then
        assertThat(app.items[0].sellIn, is(equalTo(-73)));
        assertThat(app.items[0].quality, is(equalTo(50)));
    }

    @Test
    public void ensureThatSulfurasNeverDecreasesQuality() {
        // given
        Item[] items = new Item[] {new Item("Sulfuras, Hand of Ragnaros", -1, 80)};
        GildedRose app = new GildedRose(items);
        // when
        app.updateQuality();
        // then
        assertThat(app.items[0].sellIn, is(equalTo(-1)));
        assertThat(app.items[0].quality, is(equalTo(80)));
    }

    @Test
    public void ensureThatBackstagePassesIncreasesInQualityBy1AsItsSellInValueApproaches() {
        // given
        Item[] items = new Item[] {new Item("Backstage passes to a TAFKAL80ETC concert", 15, 1)};
        GildedRose app = new GildedRose(items);
        // when
        app.updateQuality();
        // then
        assertThat(app.items[0].sellIn, is(equalTo(14)));
        assertThat(app.items[0].quality, is(equalTo(2)));
    }

    @Test
    public void ensureThatBackstagePassesIncreasesInQualityBy2AsItsSellInValueApproaches() {
        // given
        Item[] items = new Item[] {new Item("Backstage passes to a TAFKAL80ETC concert", 10, 1)};
        GildedRose app = new GildedRose(items);
        // when
        app.updateQuality();
        // then
        assertThat(app.items[0].sellIn, is(equalTo(9)));
        assertThat(app.items[0].quality, is(equalTo(3)));
    }

    @Test
    public void ensureThatBackstagePassesIncreasesInQualityBy3AsItsSellInValueApproaches() {
        // given
        Item[] items = new Item[] {new Item("Backstage passes to a TAFKAL80ETC concert", 5, 1)};
        GildedRose app = new GildedRose(items);
        // when
        app.updateQuality();
        // then
        assertThat(app.items[0].sellIn, is(equalTo(4)));
        assertThat(app.items[0].quality, is(equalTo(4)));
    }

    @Test
    public void ensureThatBackstagePassesDropsTo0AfterConcert() {
        // given
        Item[] items = new Item[] {new Item("Backstage passes to a TAFKAL80ETC concert", 0, 1)};
        GildedRose app = new GildedRose(items);
        // when
        app.updateQuality();
        // then
        assertThat(app.items[0].sellIn, is(equalTo(-1)));
        assertThat(app.items[0].quality, is(equalTo(0)));
    }

    @Test
    @Ignore("Not implemented yet")
    public void ensureThatConjuredDropsTwiceAsFastAsNormalItems() {
        // given
        Item[] items = new Item[] {new Item("Conjured Mana Cake", 3, 6)};
        GildedRose app = new GildedRose(items);
        // when
        app.updateQuality();
        // then
        assertThat(app.items[0].sellIn, is(equalTo(2)));
        assertThat(app.items[0].quality, is(equalTo(4)));
    }
}
