package com.gildedrose;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static java.util.Arrays.asList;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {GildedRoseApplication.class})
@Slf4j
public class TexttestFixture {

    @Autowired
    private GildedRose gildedRose;
    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void test() {
        log.info("OMGHAI!");

        List<VisitableItem> items = asList(new OtherItem("+5 Dexterity Vest", 10, 20), //
                new AgedBrieItem(2, 0), //
                new OtherItem("Elixir of the Mongoose", 5, 7), //
                new SulfurasItem(0, 80), //
                new SulfurasItem(-1, 80),
                new ConcertItem(15, 20),
                new ConcertItem(10, 49),
                new ConcertItem(5, 49),
                // this conjured item does not work properly yet
                new OtherItem("Conjured Mana Cake", 3, 6));

        itemRepository.setItems(items);

        for (int i = 0; i < 2; i++) {
            log.info("-------- day " + i + " --------");
            log.info("name, sellIn, quality");
            for (final Item item : itemRepository.getItems()) {
                log.info(item.toString());
            }
            log.info("");
            gildedRose.updateQuality();
        }
    }
}
