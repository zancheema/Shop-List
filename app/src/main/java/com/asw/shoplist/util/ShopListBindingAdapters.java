package com.asw.shoplist.util;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.asw.shoplist.R;
import com.asw.shoplist.data.source.Shop;
import com.asw.shoplist.data.source.ShopItem;
import com.asw.shoplist.data.source.ShopTableItem;
import com.asw.shoplist.viewlists.ShopsListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @BindingAdapter(value = {"spinner_items", "header"}, requireAll = false)
    public static void setSpinnerItems(Spinner spinner, List<String> items, String header) {
        if (spinner.getAdapter() == null) {
            final ArrayAdapter<String> adapter = new ArrayAdapter<>(
                    spinner.getContext(),
                    android.R.layout.simple_spinner_item,
                    new ArrayList<>()
            );
            if (header != null) adapter.add(header);
            adapter.addAll(items);
            spinner.setAdapter(adapter);
        }
    }


    @BindingAdapter(value = {"shop_table_items", "other_shop_table_items"})
    public static void setShopTableItems(TableLayout table, List<ShopTableItem> items, List<ShopTableItem> otherItems) {
        if (table == null || items == null) return;
        Context context = table.getContext();
        table.removeAllViews();
        table.addView(getShopTableItemRow(context, "Name", "Price", false));


        Map<String, Double> itemsMap = ShopUtil.getMap(items);
        Map<String, Double> othersItemsMap = ShopUtil.getMap(otherItems);

        for (ShopTableItem item : items) {
            boolean isConvenient = othersItemsMap.containsKey(item.getName()) &&
                    itemsMap.get(item.getName()) < othersItemsMap.get(item.getName());
            table.addView(getShopTableItemRow(
                    context, item.getName(), String.valueOf(item.getPrice()), isConvenient));
        }
    }



    private static TableRow getShopTableItemRow(Context context, String name, String price, boolean isConvenient) {
        TableRow row = new TableRow(context);
        row.addView(getShopItemTuple(context, name, false));
        row.addView(getShopItemTuple(context, price, isConvenient));

        return row;
    }

    private static TextView getShopItemTuple(Context context, String name, boolean isConvenient) {
        final Resources resources = context.getResources();
        final int dip = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                (float) 1, resources.getDisplayMetrics());

        TextView tuple = new TextView(context);
        tuple.setText(name);
        int color = isConvenient ? R.color.colorGreen : R.color.colorBlack;
        tuple.setTextColor(resources.getColor(color));
        tuple.setBackground(resources.getDrawable(R.drawable.border_table));//setting background
        tuple.setWidth(90 * dip);//setting width
        tuple.setHeight(30 * dip);//setting height
        tuple.setGravity(Gravity.CENTER);//setting text center in the text view

        return tuple;
    }
}
