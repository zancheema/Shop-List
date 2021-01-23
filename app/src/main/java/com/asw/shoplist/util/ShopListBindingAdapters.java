package com.asw.shoplist.util;

import android.util.Log;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.asw.shoplist.data.source.Shop;
import com.asw.shoplist.viewlists.ShopsListAdapter;

import java.util.List;

public class ShopListBindingAdapters {
    private static final String TAG = "ShopListBindingAdapters";

    @BindingAdapter("list_shops")
    public static void setShopListItems(RecyclerView shopList, List<Shop> items) {
        Log.d(TAG, "setShopListItems: called");
        if (items == null) return;

        Log.d(TAG, "setShopListItems: items of size: " + items.size());
        if (shopList.getAdapter() == null) {
            shopList.setAdapter(new ShopsListAdapter());
        }
        ((ShopsListAdapter) shopList.getAdapter()).submitList(items);
    }
}
