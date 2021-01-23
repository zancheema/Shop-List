package com.asw.shoplist.util;

import androidx.room.TypeConverter;

import java.time.LocalDate;

public class ShopListTypeConverters {
    @TypeConverter
    public static long getEpochFromLocalDate(LocalDate date) {
        return date.toEpochDay();
    }

    @TypeConverter
    public static LocalDate getLocalDateFromEpoch(long epoch) {
        return LocalDate.ofEpochDay(epoch);
    }
}
