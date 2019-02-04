package com.gildedrose;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GildedRose.class)
public class GildedRoseTest {

    @MockBean
    private ItemRepository itemRepository;
    @MockBean
    private ItemVisitor itemVisitor;

    @Autowired
    private GildedRose gildedRose;

    @Test
    public void updateQualityShouldIterateOverItemsAndAcceptVisitor() {
        // given
        VisitableItem item1 = mock(VisitableItem.class);
        List<VisitableItem> items = singletonList(item1);
        when(itemRepository.getItems()).thenReturn(items);
        // when
        gildedRose.updateQuality();
        // then
        verify(itemRepository).getItems();
        verify(item1).accept(itemVisitor);
    }
}
