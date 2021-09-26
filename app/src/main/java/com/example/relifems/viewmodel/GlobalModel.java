package com.example.relifems.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.relifems.gettersetter.ArticleData;
import com.example.relifems.gettersetter.GetSetArticle;
import com.example.relifems.gettersetter.GetSetImages;

public class GlobalModel extends ViewModel {

    private MutableLiveData<GetSetArticle> getSetArticle = new MutableLiveData<>();

    public MutableLiveData<GetSetArticle> getSetArticleData() {
        return getSetArticle;
    }

    private MutableLiveData<ArticleData> articleData = new MutableLiveData<>();

    public MutableLiveData<ArticleData> getArticleData() {
        return articleData;
    }

    private MutableLiveData<GetSetImages> getSetImages = new MutableLiveData<>();

    public MutableLiveData<GetSetImages> getSetImages() {
        return getSetImages;
    }

}