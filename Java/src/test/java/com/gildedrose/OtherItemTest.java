package com.gildedrose;

import org.junit.Test;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class OtherItemTest {

    @Test
    public void constructorShouldSetValues() {
        // given
        String name = randomAlphanumeric(10);
        int sellIn = nextInt();
        int quality = nextInt();
        // when
        OtherItem item = new OtherItem(name, sellIn, quality);
        // then
        assertThat(item.getName(), is(equalTo(name)));
        assertThat(item.getSellIn(), is(equalTo(sellIn)));
        assertThat(item.getQuality(), is(equalTo(quality)));
    }

    @Test
    public void acceptShouldAcceptVisitor() {
        // given
        OtherItem item = new OtherItem("", 0, 0);
        Visitor visitor = mock(Visitor.class);
        // when
        item.accept(visitor);
        // then
        verify(visitor).visit(item);
    }
}
