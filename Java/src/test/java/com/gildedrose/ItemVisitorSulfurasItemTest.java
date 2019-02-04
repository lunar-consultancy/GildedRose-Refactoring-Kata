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
public class ItemVisitorSulfurasItemTest {

    @Autowired
    private ItemVisitor itemVisitor;

    @Test
    public void sulfurasItemSellInShouldStayTheSame() {
        // given
        SulfurasItem item = new SulfurasItem(5, 5);
        // when
        itemVisitor.visit(item);
        // then
        assertThat(item.getSellIn(), is(equalTo(5)));
    }

    @Test
    public void sulfurasItemQualityShouldStayTheSame() {
        // given
        SulfurasItem item = new SulfurasItem(1, 10);
        // when
        itemVisitor.visit(item);
        // then
        assertThat(item.getQuality(), is(equalTo(10)));
    }

    @Test
    public void sulfurasItemQualityShouldNotIncreaseAbove50() {
        // given
        SulfurasItem item = new SulfurasItem(5, 50);
        // when
        itemVisitor.visit(item);
        // then
        assertThat(item.getQuality(), is(equalTo(50)));
    }

    @Test
    public void sulfurasItemQualityShouldNotDecreaseWhenSellInPast() {
        // given
        SulfurasItem item = new SulfurasItem(-0, 5);
        // when
        itemVisitor.visit(item);
        // then
        assertThat(item.getQuality(), is(equalTo(5)));
    }
}
