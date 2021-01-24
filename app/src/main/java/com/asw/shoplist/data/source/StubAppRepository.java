package com.asw.shoplist.data.source;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class StubAppRepository implements AppRepository {
    @Override
    public List<ShopItem> getAllShopItems() {
        return null;
    }

    @Override
    public LiveData<List<ShopItem>> observeAllShopItems() {
        List<ShopItem> items = new ArrayList<>();
        items.add(new ShopItem("Shop 1", "Item 1", 34.0));
        items.add(new ShopItem("Shop 1", "Item 2", 25.0));
        items.add(new ShopItem("Shop 2", "Item 1", 30));
        items.add(new ShopItem("Shop 2", "Item 2", 32.0));

        return new MutableLiveData<>(items);
    }

    @Override
    public List<ShopItem> getShopItemsByShopName(String shopName) {
        return null;
    }

    @Override
    public LiveData<List<ShopItem>> observeShopItemsByShopName(String shopName) {
        return null;
    }

    @Override
    public void saveShopItem(ShopItem item) {

    }
}
