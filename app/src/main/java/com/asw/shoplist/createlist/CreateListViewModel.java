package com.asw.shoplist.createlist;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.asw.shoplist.Event;
import com.asw.shoplist.data.source.AppRepository;
import com.asw.shoplist.data.source.ShopItem;

public class CreateListViewModel extends ViewModel {
    private final AppRepository repository;

    public final MutableLiveData<String> shopName;
    public final MutableLiveData<String> itemName;
    public final MutableLiveData<String> itemPrice;
    private final MutableLiveData<Event<Boolean>> emptyFieldEvent;
    private final MutableLiveData<Event<Boolean>> itemAddedEvent;


    public CreateListViewModel(
            AppRepository repository
    ) {
        this.repository = repository;
        this.shopName = new MutableLiveData<>();
        this.itemName = new MutableLiveData<>();
        this.itemPrice = new MutableLiveData<>();
        this.emptyFieldEvent = new MutableLiveData<>();
        this.itemAddedEvent = new MutableLiveData<>();
    }

    public LiveData<Event<Boolean>> observeEmptyFieldEvent() {
        return emptyFieldEvent;
    }

    public LiveData<Event<Boolean>> observeItemAddedEvent() {
        return itemAddedEvent;
    }

    public void saveItem() {
        String shop = shopName.getValue();
        String item = itemName.getValue();
        String price = itemPrice.getValue();

        if (isValidField(shop) && isValidField(item) && isValidField(price)) {
            repository.saveShopItem(
                    new ShopItem(shop, item, Double.parseDouble(price))
            );
            itemAddedEvent.setValue(new Event<>(true));
        } else {
            emptyFieldEvent.setValue(new Event<>(true));
        }
    }

    private boolean isValidField(String field) {
        return field != null && !field.isEmpty();
    }

    public void itemSaveComplete() {
        itemName.setValue("");
        itemPrice.setValue("");
        itemAddedEvent.setValue(new Event<>(false));
    }

    @SuppressWarnings("UNCHECKED_CAST")
    public static class CreateListViewModelFactory extends ViewModelProvider.NewInstanceFactory {
        private final AppRepository repository;

        public CreateListViewModelFactory(AppRepository repository) {
            this.repository = repository;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new CreateListViewModel(repository);
        }
    }
}
