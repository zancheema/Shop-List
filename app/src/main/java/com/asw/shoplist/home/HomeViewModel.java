package com.asw.shoplist.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.asw.shoplist.Event;

public class HomeViewModel extends ViewModel {
    private final MutableLiveData<Event<Boolean>> addListEvent;
    private final MutableLiveData<Event<Boolean>> compareListsEvent;
    private final MutableLiveData<Event<Boolean>> viewListsEvent;

    public HomeViewModel() {
        addListEvent = new MutableLiveData<>();
        compareListsEvent = new MutableLiveData<>();
        viewListsEvent = new MutableLiveData<>();
    }

    public void addList() {
        addListEvent.setValue(new Event(true));
    }

    public void compareLists() {
        compareListsEvent.setValue(new Event(true));
    }

    public void viewLists() {
        viewListsEvent.setValue(new Event(true));
    }

    public LiveData<Event<Boolean>> observeAddListEvent() {
        return addListEvent;
    }

    public LiveData<Event<Boolean>> observeCompareListsEvent() {
        return compareListsEvent;
    }

    public LiveData<Event<Boolean>> observeViewListsEvent() {
        return viewListsEvent;
    }
}
