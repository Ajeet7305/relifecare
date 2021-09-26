package com.example.relifems;

import android.app.Application;
import android.content.Context;

import com.example.relifems.service.ApiService;

public class MyApplication extends Application {

    private static MyApplication mInstance;
    private static Context context;
    private ApiService getApiService;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
        context = this;
    }

    public ApiService getApiService() {
        if (getApiService == null) {
            getApiService = new ApiService(getApplicationContext());
        }
        return getApiService;
    }

}
