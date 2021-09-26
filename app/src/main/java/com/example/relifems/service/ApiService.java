package com.example.relifems.service;

import android.content.Context;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class ApiService {

    private Context context;
    private final String TAG = "ApiService";
    private final String BASE_URL = "https://spaceflightnewsapi.net/api/v2/";
    private Retrofit objRetrofit;

    public ApiService(Context context) {
        this.context = context;
    }

    public interface ApiClient {

        @GET("articles")
        Call<JsonArray> getArticles();

        @GET("articles/{id}")
        Call<JsonObject> getDetailArticle(@Path("id") String id);

        @GET("search")
        Call<JsonArray> getImages(@Query("limit") int limit);

    }

    public interface ApiCallbacks {
        void onResponse(JSONObject response);

        void onError(String errorResponse);
    }

    public ApiClient getRetrofitInstance(String baseUrl) {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.connectTimeout(1, TimeUnit.MINUTES);
        httpClient.readTimeout(60, TimeUnit.SECONDS);
        httpClient.writeTimeout(15, TimeUnit.SECONDS);

        httpClient.addInterceptor(chain ->
        {
            Request request = chain.request().newBuilder().addHeader("Content-Type", "application/json").build();
            return chain.proceed(request);
        });

        if (objRetrofit == null) {
            objRetrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(httpClient.build())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return objRetrofit.create(ApiClient.class);
    }

    public <T> void callResponse(Call<T> call, ApiCallbacks apiCallbacks) {
        long time = Calendar.getInstance().getTimeInMillis();
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, retrofit2.Response<T> response) {
                try {
                    long respTime = Calendar.getInstance().getTimeInMillis();
                    if (response.isSuccessful()) {
                        try {
                            if (response.body() instanceof JsonObject) {
                                apiCallbacks.onResponse(new JSONObject(response.body().toString()));
                            } else if (response.body() instanceof JsonArray) {
                                JSONObject jsonObject = new JSONObject();
                                jsonObject.put("lists", new JSONArray(response.body().toString()));
                                apiCallbacks.onResponse(jsonObject);
                            } else {
                                apiCallbacks.onError("");
                            }
                        } catch (Exception e) {
                            apiCallbacks.onError("");
                            e.printStackTrace();
                        }
                    } else {
                        switch (response.code()) {
                            case 503:
                                apiCallbacks.onError("");
                                break;
                            case 404:
                                apiCallbacks.onError("");
                                break;
                            case 500:
                                apiCallbacks.onError("");
                                break;
                            case 403:
                                apiCallbacks.onError("");
                                break;
                            default:
                                apiCallbacks.onError("");
                                break;
                        }
                    }
                } catch (Exception e) {
                    apiCallbacks.onError("");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                call.cancel();
                if (t instanceof IOException) {
                    apiCallbacks.onError("");
                } else {
                    apiCallbacks.onError("");
                }
            }
        });
    }

}