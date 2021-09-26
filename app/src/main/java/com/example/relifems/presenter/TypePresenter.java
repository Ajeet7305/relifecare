package com.example.relifems.presenter;

import android.app.Activity;

import com.example.relifems.MyApplication;
import com.example.relifems.gettersetter.ArticleData;
import com.example.relifems.gettersetter.GetSetArticle;
import com.example.relifems.gettersetter.GetSetImages;
import com.example.relifems.service.ApiService;
import com.example.relifems.utils.StaticMethod;
import com.example.relifems.viewmodel.GlobalModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

public class TypePresenter {

    private ApiService apiService;
    private GlobalModel globalModel;
    private Activity activity;
    private final String TAG = "MainPresenter";

    public TypePresenter(Activity activity, GlobalModel globalModel) {

        try {
            this.activity = activity;
            this.globalModel = globalModel;
            apiService = ((MyApplication) activity.getApplication()).getApiService();
        } catch (Exception e) {
            apiService = new ApiService(activity);
            e.printStackTrace();
        }
    }

    public void getType(String url) {
        StaticMethod.loadingView(activity, true);

        apiService.callResponse(apiService.getRetrofitInstance(url).getArticles(), new ApiService.ApiCallbacks() {
            @Override
            public void onResponse(JSONObject response) {
                StaticMethod.loadingView(activity, false);
                try {

                    Gson gson = new GsonBuilder().create();
                    GetSetArticle getSetArticle = gson.fromJson(response.toString(), GetSetArticle.class);
                    globalModel.getSetArticleData().setValue(getSetArticle);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String errorResponse) {
                StaticMethod.loadingView(activity, false);
                //globalModel.getSetError().setValue(errorResponse);
            }
        });
    }

    public void getDetailArticle(String url, String id) {
        StaticMethod.loadingView(activity, true);

        apiService.callResponse(apiService.getRetrofitInstance(url).getDetailArticle(id), new ApiService.ApiCallbacks() {
            @Override
            public void onResponse(JSONObject response) {
                StaticMethod.loadingView(activity, false);
                try {

                    Gson gson = new GsonBuilder().create();
                    ArticleData articleData = gson.fromJson(response.toString(), ArticleData.class);
                    globalModel.getArticleData().setValue(articleData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String errorResponse) {
                StaticMethod.loadingView(activity, false);
                //globalModel.getSetError().setValue(errorResponse);
            }
        });
    }

    public void getImages(String url, int limit) {
        StaticMethod.loadingView(activity, true);

        apiService.callResponse(apiService.getRetrofitInstance(url).getImages(limit), new ApiService.ApiCallbacks() {
            @Override
            public void onResponse(JSONObject response) {
                StaticMethod.loadingView(activity, false);
                try {

                    Gson gson = new GsonBuilder().create();
                    GetSetImages getSetImages = gson.fromJson(response.toString(), GetSetImages.class);
                    globalModel.getSetImages().setValue(getSetImages);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String errorResponse) {
                StaticMethod.loadingView(activity, false);
                //globalModel.getSetError().setValue(errorResponse);
            }
        });
    }

}