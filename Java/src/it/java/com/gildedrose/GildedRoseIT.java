package com.gildedrose;

import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class GildedRoseIT {

    @Test
    public void ensureThatWhenSellByDateHasPassedQualityDegradesTwiceAsFast() {
        // given
        Item[] items = new Item[] {new Item("+5 Dexterity Vest", -1, 2)};
        GildedRose app = new GildedRose(items);
        // when
        app.updateQuality();
        // then
        assertThat(app.getItems()[0].getSellIn(), is(equalTo(-2)));
        assertThat(app.getItems()[0].getQuality(), is(equalTo(0)));
    }

    @Test
    public void ensureThatSellInAndQualityIsLoweredAtTheEndOfTheDay() {
        // given
        Item[] items = new Item[] {new Item("item", 10, 20)};
        GildedRose app = new GildedRose(items);
        // when
        app.updateQuality();
        // then
        assertThat(app.getItems()[0].getQuality(), is(equalTo(19)));
        assertThat(app.getItems()[0].getQuality(), is(equalTo(19)));
    }

    @Test
    public void ensureThatQualityNeverGetsNegative() {
        // given
        Item[] items = new Item[] {new Item("item", 10, 0)};
        GildedRose app = new GildedRose(items);
        // when
        app.updateQuality();
        // then
        assertThat(app.getItems()[0].getSellIn(), is(equalTo(9)));
        assertThat(app.getItems()[0].getQuality(), is(equalTo(0)));
    }

    @Test
    public void ensureThatAgedBrieIncreasesInQuality() {
        // given
        Item[] items = new Item[] {new AgedBrieItem(10, 20)};
        GildedRose app = new GildedRose(items);
        // when
        app.updateQuality();
        // then
        assertThat(app.getItems()[0].getSellIn(), is(equalTo(9)));
        assertThat(app.getItems()[0].getQuality(), is(equalTo(21)));
    }

    @Test
    public void ensureThatQualityNeverIncreasesAbove50() {
        // given
        Item[] items = new Item[] {new AgedBrieItem(2, 0)};
        GildedRose app = new GildedRose(items);
        // when
        for (int i = 0; i < 75; i++) {
            app.updateQuality();
        }
        // then
        assertThat(app.getItems()[0].getSellIn(), is(equalTo(-73)));
        assertThat(app.getItems()[0].getQuality(), is(equalTo(50)));
    }

    @Test
    public void ensureThatSulfurasNeverDecreasesQuality() {
        // given
        Item[] items = new Item[] {new SulfurasItem(-1, 80)};
        GildedRose app = new GildedRose(items);
        // when
        app.updateQuality();
        // then
        assertThat(app.getItems()[0].getSellIn(), is(equalTo(-1)));
        assertThat(app.getItems()[0].getQuality(), is(equalTo(80)));
    }

    @Test
    public void ensureThatBackstagePassesIncreasesInQualityBy1AsItsSellInValueApproaches() {
        // given
        Item[] items = new Item[] {new ConcertItem(15, 1)};
        GildedRose app = new GildedRose(items);
        // when
        app.updateQuality();
        // then
        assertThat(app.getItems()[0].getSellIn(), is(equalTo(14)));
        assertThat(app.getItems()[0].getQuality(), is(equalTo(2)));
    }

    @Test
    public void ensureThatBackstagePassesIncreasesInQualityBy2AsItsSellInValueApproaches() {
        // given
        Item[] items = new Item[] {new ConcertItem(10, 1)};
        GildedRose app = new GildedRose(items);
        // when
        app.updateQuality();
        // then
        assertThat(app.getItems()[0].getSellIn(), is(equalTo(9)));
        assertThat(app.getItems()[0].getQuality(), is(equalTo(3)));
    }

    @Test
    public void ensureThatBackstagePassesIncreasesInQualityBy3AsItsSellInValueApproaches() {
        // given
        Item[] items = new Item[] {new ConcertItem(5, 1)};
        GildedRose app = new GildedRose(items);
        // when
        app.updateQuality();
        // then
        assertThat(app.getItems()[0].getSellIn(), is(equalTo(4)));
        assertThat(app.getItems()[0].getQuality(), is(equalTo(4)));
    }

    @Test
    public void ensureThatBackstagePassesDropsTo0AfterConcert() {
        // given
        Item[] items = new Item[] {new ConcertItem(0, 1)};
        GildedRose app = new GildedRose(items);
        // when
        app.updateQuality();
        // then
        assertThat(app.getItems()[0].getSellIn(), is(equalTo(-1)));
        assertThat(app.getItems()[0].getQuality(), is(equalTo(0)));
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
        assertThat(app.getItems()[0].getSellIn(), is(equalTo(2)));
        assertThat(app.getItems()[0].getQuality(), is(equalTo(4)));
    }
}
