package com.asw.shoplist.data.source;

import java.util.List;

public interface AppRepository {
    List<ShopItem> getAllShopItems();

    List<ShopItem> getShopItemsByShopName(String shopName);

    void saveShopItem(ShopItem item);
}
