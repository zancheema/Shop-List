package com.asw.shoplist.comparelists;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory;

import com.asw.shoplist.Event;
import com.asw.shoplist.data.source.AppRepository;
import com.asw.shoplist.data.source.ShopItem;
import com.asw.shoplist.data.source.ShopTableItem;
import com.asw.shoplist.util.ShopUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CompareListsViewModel extends ViewModel {

    private final AppRepository repository;
    public String shop1Head = "Select Shop 1";
    public String shop2Head = "Select Shop 2";
    private final MutableLiveData<Boolean> tablesVisibility;
    public final MutableLiveData<Integer> selectedShop1Index;
    public final MutableLiveData<Integer> selectedShop2Index;
    private final MutableLiveData<Event<Boolean>> invalidShopEvent;

    public CompareListsViewModel(AppRepository repository) {
        this.repository = repository;
        tablesVisibility = new MutableLiveData<>();
        selectedShop1Index = new MutableLiveData<>(0);
        selectedShop2Index = new MutableLiveData<>(0);
        invalidShopEvent = new MutableLiveData<>();
    }

    public LiveData<Event<Boolean>> observeInvalidShopEvent() {
        return invalidShopEvent;
    }

    public LiveData<List<String>> getShopNames() {
        return Transformations.map(repository.observeAllShopItems(), shopItems -> {
            Set<String> names = new HashSet<>();
            for (ShopItem s : shopItems) names.add(s.getShopName());
            return new ArrayList<>(names);
        });
    }

    public LiveData<Boolean> getTablesVisibility() {
        return tablesVisibility;
    }

    public LiveData<String> getSelectedShop1Name() {
        return Transformations.switchMap(selectedShop1Index, index ->
                Transformations.map(getShopNames(), names -> {
                    if (index == 0) return "Not Selected";
                    return names.get(index - 1);
                }));
    }

    public LiveData<String> getSelectedShop2Name() {
        return Transformations.switchMap(selectedShop2Index, index ->
                Transformations.map(getShopNames(), names -> {
                    if (index == 0) return "Not Selected";
                    return names.get(index - 1);
                }));
    }

    public LiveData<List<ShopTableItem>> getShop1Items() {
        return Transformations.switchMap(repository.observeAllShopItems(), shopItems ->
                getShopTableItems(shopItems, getSelectedShop1Name()));
    }

    public LiveData<List<ShopTableItem>> getShop2Items() {
        return Transformations.switchMap(repository.observeAllShopItems(), shopItems ->
                getShopTableItems(shopItems, getSelectedShop2Name()));
    }

    private LiveData<List<ShopTableItem>> getShopTableItems(List<ShopItem> shopItems, LiveData<String> selectedShop1Name) {
        return Transformations.map(selectedShop1Name, shopName -> {
            List<ShopTableItem> tableItems = new ArrayList<>();
            for (ShopItem item : shopItems) {
                if (item.getShopName().equals(shopName))
                    tableItems.add(new ShopTableItem(item.getItemName(), item.getItemPrice()));
            }
            return tableItems;
        });
    }

    public LiveData<String> getResultText(List<ShopTableItem> items, List<ShopTableItem> otherItems) {
        int result = ShopUtil.isMoreConvenient(items, otherItems);
        if (result == 0) {
            return new MutableLiveData<>("Both shops are equally convenient. You can buy from either of them");
        }
        LiveData<String> name = result > 0 ? getSelectedShop1Name() : getSelectedShop2Name();
        return Transformations.map(name, shopName -> "It is more convenient to by from " + shopName);
    }

    public void compareShops() {
        int index1 = selectedShop1Index.getValue();
        int index2 = selectedShop2Index.getValue();
        if (index1 == 0 || index2 == 0) {
            invalidShopEvent.setValue(new Event<>(true));
            tablesVisibility.setValue(false);
        } else {
            tablesVisibility.setValue(true);
        }
    }

    @SuppressWarnings("UNCHECKED_CAST")
    public static class CompareListsViewModelFactory extends NewInstanceFactory {
        private final AppRepository repository;

        public CompareListsViewModelFactory(AppRepository repository) {
            this.repository = repository;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new CompareListsViewModel(repository);
        }
    }
}
