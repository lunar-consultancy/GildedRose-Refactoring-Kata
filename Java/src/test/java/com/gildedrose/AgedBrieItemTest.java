package com.gildedrose;

import org.junit.Test;

import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AgedBrieItemTest {

    @Test
    public void constructorShouldSetValues() {
        // given
        int sellIn = nextInt();
        int quality = nextInt();
        // when
        AgedBrieItem item = new AgedBrieItem(sellIn, quality);
        // then
        assertThat(item.getName(), is(equalTo("Aged Brie")));
        assertThat(item.getSellIn(), is(equalTo(sellIn)));
        assertThat(item.getQuality(), is(equalTo(quality)));
    }

    @Test
    public void acceptShouldAcceptVisitor() {
        // given
        AgedBrieItem item = new AgedBrieItem(0, 0);
        Visitor visitor = mock(Visitor.class);
        // when
        item.accept(visitor);
        // then
        verify(visitor).visit(item);
    }
}
