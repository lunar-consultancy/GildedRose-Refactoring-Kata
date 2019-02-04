package com.gildedrose;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {GildedRoseApplication.class})
public class GildedRoseIT {

    @Autowired
    private GildedRose gildedRose;
    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void ensureThatWhenSellByDateHasPassedQualityDegradesTwiceAsFast() {
        // given
        List<VisitableItem> items = Collections.singletonList(new OtherItem("+5 Dexterity Vest", -1, 2));
        itemRepository.setItems(items);
        // when
        gildedRose.updateQuality();
        // then
        assertThat(itemRepository.getItems().get(0).getSellIn(), is(equalTo(-2)));
        assertThat(itemRepository.getItems().get(0).getQuality(), is(equalTo(0)));
    }

    @Test
    public void ensureThatSellInAndQualityIsLoweredAtTheEndOfTheDay() {
        // given
        List<VisitableItem> items = Collections.singletonList(new OtherItem("item", 10, 20));
        itemRepository.setItems(items);
        // when
        gildedRose.updateQuality();
        // then
        assertThat(itemRepository.getItems().get(0).getQuality(), is(equalTo(19)));
        assertThat(itemRepository.getItems().get(0).getQuality(), is(equalTo(19)));
    }

    @Test
    public void ensureThatQualityNeverGetsNegative() {
        // given
        List<VisitableItem> items = Collections.singletonList(new OtherItem("item", 10, 0));
        itemRepository.setItems(items);
        // when
        gildedRose.updateQuality();
        // then
        assertThat(itemRepository.getItems().get(0).getSellIn(), is(equalTo(9)));
        assertThat(itemRepository.getItems().get(0).getQuality(), is(equalTo(0)));
    }

    @Test
    public void ensureThatAgedBrieIncreasesInQuality() {
        // given
        List<VisitableItem> items = Collections.singletonList(new AgedBrieItem(10, 20));
        itemRepository.setItems(items);
        // when
        gildedRose.updateQuality();
        // then
        assertThat(itemRepository.getItems().get(0).getSellIn(), is(equalTo(9)));
        assertThat(itemRepository.getItems().get(0).getQuality(), is(equalTo(21)));
    }

    @Test
    public void ensureThatQualityNeverIncreasesAbove50() {
        // given
        List<VisitableItem> items = Collections.singletonList(new AgedBrieItem(2, 0));
        itemRepository.setItems(items);
        // when
        for (int i = 0; i < 75; i++) {
            gildedRose.updateQuality();
        }
        // then
        assertThat(itemRepository.getItems().get(0).getSellIn(), is(equalTo(-73)));
        assertThat(itemRepository.getItems().get(0).getQuality(), is(equalTo(50)));
    }

    @Test
    public void ensureThatSulfurasNeverDecreasesQuality() {
        // given
        List<VisitableItem> items = Collections.singletonList(new SulfurasItem(-1, 80));
        itemRepository.setItems(items);
        // when
        gildedRose.updateQuality();
        // then
        assertThat(itemRepository.getItems().get(0).getSellIn(), is(equalTo(-1)));
        assertThat(itemRepository.getItems().get(0).getQuality(), is(equalTo(80)));
    }

    @Test
    public void ensureThatBackstagePassesIncreasesInQualityBy1AsItsSellInValueApproaches() {
        // given
        List<VisitableItem> items = Collections.singletonList(new ConcertItem(15, 1));
        itemRepository.setItems(items);
        // when
        gildedRose.updateQuality();
        // then
        assertThat(itemRepository.getItems().get(0).getSellIn(), is(equalTo(14)));
        assertThat(itemRepository.getItems().get(0).getQuality(), is(equalTo(2)));
    }

    @Test
    public void ensureThatBackstagePassesIncreasesInQualityBy2AsItsSellInValueApproaches() {
        // given
        List<VisitableItem> items = Collections.singletonList(new ConcertItem(10, 1));
        itemRepository.setItems(items);
        // when
        gildedRose.updateQuality();
        // then
        assertThat(itemRepository.getItems().get(0).getSellIn(), is(equalTo(9)));
        assertThat(itemRepository.getItems().get(0).getQuality(), is(equalTo(3)));
    }

    @Test
    public void ensureThatBackstagePassesIncreasesInQualityBy3AsItsSellInValueApproaches() {
        // given
        List<VisitableItem> items = Collections.singletonList(new ConcertItem(5, 1));
        itemRepository.setItems(items);
        // when
        gildedRose.updateQuality();
        // then
        assertThat(itemRepository.getItems().get(0).getSellIn(), is(equalTo(4)));
        assertThat(itemRepository.getItems().get(0).getQuality(), is(equalTo(4)));
    }

    @Test
    public void ensureThatBackstagePassesDropsTo0AfterConcert() {
        // given
        List<VisitableItem> items = Collections.singletonList(new ConcertItem(0, 1));
        itemRepository.setItems(items);
        // when
        gildedRose.updateQuality();
        // then
        assertThat(itemRepository.getItems().get(0).getSellIn(), is(equalTo(-1)));
        assertThat(itemRepository.getItems().get(0).getQuality(), is(equalTo(0)));
    }

    @Test
    public void ensureThatConjuredDropsTwiceAsFastAsNormalItems() {
        // given
        List<VisitableItem> items = Collections.singletonList(new ConjuredItem("Conjured Mana Cake", 3, 6));
        itemRepository.setItems(items);
        // when
        gildedRose.updateQuality();
        // then
        assertThat(itemRepository.getItems().get(0).getSellIn(), is(equalTo(2)));
        assertThat(itemRepository.getItems().get(0).getQuality(), is(equalTo(4)));
    }
}
