package com.asw.shoplist.data.source;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

import java.time.LocalDate;

@Entity(tableName = "shop_items", primaryKeys = {"shop_name", "item_name"})
public class ShopItem {
    @NonNull
    @ColumnInfo(name = "shop_name")
    private String shopName;

    @NonNull
    @ColumnInfo(name = "item_name")
    private String itemName;

    @NonNull
    @ColumnInfo(name = "item_price", defaultValue = "0.0")
    private double itemPrice;

    @NonNull
    @ColumnInfo(name = "date")
    private LocalDate date;

    public ShopItem(String shopName, String itemName, double itemPrice) {
        this.shopName = shopName;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.date = LocalDate.now();
    }

    public ShopItem(String shopName, String itemName, double itemPrice, LocalDate date) {
        this.shopName = shopName;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.date = date;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
