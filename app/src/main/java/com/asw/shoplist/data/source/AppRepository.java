package com.asw.shoplist.data.source;

import androidx.lifecycle.LiveData;

import java.util.List;

public interface AppRepository {
    List<ShopItem> getAllShopItems();

    LiveData<List<ShopItem>> observeAllShopItems();

    List<ShopItem> getShopItemsByShopName(String shopName);

    LiveData<List<ShopItem>> observeShopItemsByShopName(String shopName);

    void saveShopItem(ShopItem item);
}
