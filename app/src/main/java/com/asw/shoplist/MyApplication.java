package com.asw.shoplist;

import android.app.Application;

import com.asw.shoplist.data.source.AppRepository;

public class MyApplication extends Application {
    public AppRepository getRepository() {
        return ServiceLocator.getInstance().provideAppRepository();
    }
}
