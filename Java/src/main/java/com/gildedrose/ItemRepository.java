package com.gildedrose;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Getter
@Setter
public class ItemRepository {

    private List<VisitableItem> items;

}
