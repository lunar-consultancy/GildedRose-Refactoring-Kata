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
public class ItemVisitorOtherItemTest {

    @Autowired
    private ItemVisitor itemVisitor;

    @Test
    public void otherItemSellInShouldBeDecreased() {
        // given
        OtherItem item = new OtherItem("",5, 5);
        // when
        itemVisitor.visit(item);
        // then
        assertThat(item.getSellIn(), is(equalTo(4)));
    }

    @Test
    public void otherItemQualityShouldBeDecreased() {
        // given
        OtherItem item = new OtherItem("",1, 10);
        // when
        itemVisitor.visit(item);
        // then
        assertThat(item.getQuality(), is(equalTo(9)));
    }

    @Test
    public void agedBrieItemQualityShouldBeDecreasedTwiceAsFastWhenSellInPassed() {
        // given
        OtherItem item = new OtherItem("",0, 10);
        // when
        itemVisitor.visit(item);
        // then
        assertThat(item.getQuality(), is(equalTo(8)));
    }

    @Test
    public void agedBrieItemQualityShouldNotBeDecreasedBelow0() {
        // given
        OtherItem item = new OtherItem("",10, 0);
        // when
        itemVisitor.visit(item);
        // then
        assertThat(item.getQuality(), is(equalTo(0)));
    }
}
