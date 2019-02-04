package com.gildedrose;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ItemVisitor.class)
public class ItemVisitorAgedBrieItemTest {

    @Autowired
    private ItemVisitor itemVisitor;

    @Test
    public void agedBrieItemSellInShouldBeDecreased() {
        // given
        AgedBrieItem item = new AgedBrieItem(0, 5);
        // when
        itemVisitor.visit(item);
        // then
        assertThat(item.getSellIn(), is(equalTo(-1)));
    }

    @Test
    public void agedBrieItemQualityShouldIncrease() {
        // given
        AgedBrieItem item = new AgedBrieItem(1, 10);
        // when
        itemVisitor.visit(item);
        // then
        assertThat(item.getQuality(), is(equalTo(11)));
    }

    @Test
    public void agedBrieItemQualityShouldNotIncreaseAbove50() {
        // given
        AgedBrieItem item = new AgedBrieItem(1, 50);
        // when
        itemVisitor.visit(item);
        // then
        assertThat(item.getQuality(), is(equalTo(50)));
    }

    @Test
    public void agedBrieItemQualityShouldIncreaseWith2WhenAgeBelowZero() {
        // given
        AgedBrieItem item = new AgedBrieItem(0, 5);
        // when
        itemVisitor.visit(item);
        // then
        assertThat(item.getQuality(), is(equalTo(7)));
    }
}
