package com.asw.shoplist;

import com.asw.shoplist.data.source.AppRepository;
import com.asw.shoplist.data.source.StubAppRepository;

public class ServiceLocator {
    private static ServiceLocator INSTANCE;
    private AppRepository repository;

    private ServiceLocator() {

    }

    public AppRepository provideAppRepository() {
        if (repository == null) {
            repository = new StubAppRepository();
        }
        return repository;
    }

    public static ServiceLocator getInstance() {
        if (INSTANCE == null) {
            synchronized (ServiceLocator.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ServiceLocator();
                }
            }
        }
        return INSTANCE;
    }
}
