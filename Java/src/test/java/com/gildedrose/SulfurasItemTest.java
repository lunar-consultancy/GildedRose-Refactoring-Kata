package com.gildedrose;

import org.junit.Test;

import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SulfurasItemTest {

    @Test
    public void constructorShouldSetValues() {
        // given
        int sellIn = nextInt();
        int quality = nextInt();
        // when
        SulfurasItem item = new SulfurasItem(sellIn, quality);
        // then
        assertThat(item.getName(), is(equalTo("Sulfuras, Hand of Ragnaros")));
        assertThat(item.getSellIn(), is(equalTo(sellIn)));
        assertThat(item.getQuality(), is(equalTo(quality)));
    }

    @Test
    public void acceptShouldAcceptVisitor() {
        // given
        SulfurasItem item = new SulfurasItem(0, 0);
        Visitor visitor = mock(Visitor.class);
        // when
        item.accept(visitor);
        // then
        verify(visitor).visit(item);
    }
}
