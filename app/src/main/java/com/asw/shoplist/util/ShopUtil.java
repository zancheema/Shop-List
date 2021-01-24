package com.asw.shoplist.util;

import com.asw.shoplist.data.source.ShopTableItem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShopUtil {
    public static int isMoreConvenient(
            List<ShopTableItem> items,
            List<ShopTableItem> otherItems
    ) {
        Map<String, Double> itemsMap = ShopUtil.getMap(items);
        Map<String, Double> othersItemsMap = ShopUtil.getMap(otherItems);

        int count = 0;
        int otherCount = 0;
        for (ShopTableItem item : items) {
            boolean isConvenient = othersItemsMap.containsKey(item.getName()) &&
                    itemsMap.get(item.getName()) < othersItemsMap.get(item.getName());
            if (isConvenient) count++;
            else otherCount++;
        }

        return count - otherCount;
    }

    public static Map<String, Double> getMap(List<ShopTableItem> items) {
        Map<String, Double> itemsMap = new HashMap<>();
        for (ShopTableItem item : items) {
            itemsMap.put(item.getName(), item.getPrice());
        }
        return itemsMap;
    }
}
