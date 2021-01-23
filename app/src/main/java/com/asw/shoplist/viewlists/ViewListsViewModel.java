package com.asw.shoplist.viewlists;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.asw.shoplist.data.source.AppRepository;
import com.asw.shoplist.data.source.Shop;
import com.asw.shoplist.data.source.ShopItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewListsViewModel extends ViewModel {
    private final AppRepository repository;

    public ViewListsViewModel(AppRepository repository) {
        this.repository = repository;
    }

    public LiveData<List<Shop>> observeShops() {
        return Transformations.map(repository.observeAllShopItems(), shopItems -> {
            Map<String, Double> shopsMap = new HashMap<>();
            for (ShopItem s : shopItems) {
                String name = s.getShopName();
                shopsMap.put(name, shopsMap.getOrDefault(name, 0.0) + s.getItemPrice());
            }
            List<Shop> shops = new ArrayList<>();
            for (String name : shopsMap.keySet()) {
                shops.add(new Shop(name, shopsMap.get(name)));
            }
            return shops;
        });
    }

    @SuppressWarnings("UNCHECKED_CAST")
    public static class ViewListsViewModelFactory extends ViewModelProvider.NewInstanceFactory {
        private final AppRepository repository;

        public ViewListsViewModelFactory(AppRepository repository) {
            this.repository = repository;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new ViewListsViewModel(repository);
        }
    }
}
