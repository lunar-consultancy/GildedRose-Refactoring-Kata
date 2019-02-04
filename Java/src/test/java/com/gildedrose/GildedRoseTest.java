package com.gildedrose;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class GildedRoseTest {

    @Test
    public void foo() {
        // given
        Item[] items = new Item[] {new Item("foo", 0, 0)};
        GildedRose app = new GildedRose(items);
        // when
        app.updateQuality();
        // then
        assertThat(app.getItems()[0].getName(), is(equalTo("foo")));
    }
}
