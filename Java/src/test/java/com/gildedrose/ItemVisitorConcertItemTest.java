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
public class ItemVisitorConcertItemTest {

    @Autowired
    private ItemVisitor itemVisitor;


    @Test
    public void concertItemSellInShouldDecrease() {
        // given
        ConcertItem item = new ConcertItem(10, 5);
        // when
        itemVisitor.visit(item);
        // then
        assertThat(item.getSellIn(), is(equalTo(9)));
    }

    @Test
    public void concertItemQualityShouldIncrementWith2WhenSellInBetween6And10() {
        // given
        ConcertItem item1 = new ConcertItem(6, 6);
        ConcertItem item2 = new ConcertItem(10, 7);
        // when
        itemVisitor.visit(item1);
        itemVisitor.visit(item2);
        // then
        assertThat(item1.getQuality(), is(equalTo(8)));
        assertThat(item2.getQuality(), is(equalTo(9)));
    }

    @Test
    public void concertItemQualityShouldIncrementWith3WhenSellInBetween1And5() {
        // given
        ConcertItem item1 = new ConcertItem(1, 3);
        ConcertItem item2 = new ConcertItem(5, 4);
        // when
        itemVisitor.visit(item1);
        itemVisitor.visit(item2);
        // then
        assertThat(item1.getQuality(), is(equalTo(6)));
        assertThat(item2.getQuality(), is(equalTo(7)));
    }

    @Test
    public void concertItemQualityShouldSetToZeroAfterConcert() {
        // given
        ConcertItem item = new ConcertItem(0, 5);
        // when
        itemVisitor.visit(item);
        // then
        assertThat(item.getQuality(), is(equalTo(0)));
    }

    @Test
    public void concertItemQualityShouldNotIncrementWhenQualityIs50AndSellInHigherThan11() {
        // given
        ConcertItem item = new ConcertItem(11, 50);
        // when
        itemVisitor.visit(item);
        // then
        assertThat(item.getQuality(), is(equalTo(50)));
    }

    @Test
    public void concertItemQualityShouldNotIncrementWhenQualityIs50AndSellInBetween6And10() {
        // given
        ConcertItem item1 = new ConcertItem(11, 50);
        ConcertItem item2 = new ConcertItem(11, 50);
        // when
        itemVisitor.visit(item1);
        itemVisitor.visit(item2);
        // then
        assertThat(item1.getQuality(), is(equalTo(50)));
        assertThat(item2.getQuality(), is(equalTo(50)));
    }

    @Test
    public void concertItemQualityShouldNotIncrementWhenQualityIs50AndSellInBetween1And5() {
        // given
        ConcertItem item1 = new ConcertItem(1, 50);
        ConcertItem item2 = new ConcertItem(5, 50);
        // when
        itemVisitor.visit(item1);
        itemVisitor.visit(item2);
        // then
        assertThat(item1.getQuality(), is(equalTo(50)));
        assertThat(item2.getQuality(), is(equalTo(50)));
    }


}
