package com.asw.shoplist.data.source;

import java.util.List;

public class StubAppRepository implements AppRepository {
    @Override
    public List<ShopItem> getAllShopItems() {
        return null;
    }

    @Override
    public List<ShopItem> getShopItemsByShopName(String shopName) {
        return null;
    }

    @Override
    public void saveShopItem(ShopItem item) {

    }
}
