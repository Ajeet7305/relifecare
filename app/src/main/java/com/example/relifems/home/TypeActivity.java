package com.example.relifems.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.relifems.R;
import com.example.relifems.adapter.ILBA_TypeAdapter;
import com.example.relifems.gettersetter.Breed;
import com.example.relifems.presenter.TypePresenter;
import com.example.relifems.utils.StaticVariable;
import com.example.relifems.viewmodel.GlobalModel;

import androidx.lifecycle.ViewModelProviders;

import java.util.ArrayList;

public class TypeActivity extends AppCompatActivity implements ILBA_TypeAdapter.OnItemClickI {

    private TypePresenter typePresenter;
    private GlobalModel globalModel;

    private RecyclerView rvDetailData;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type);

        type = getIntent().getStringExtra("type");

        String url;
        if (type != null && type.equals("Article")) {
            url = StaticVariable.ARTICLE;
            typePresenter().getType(url);
        } else {
            url = StaticVariable.IMAGES;
            typePresenter().getImages(url, 10);
        }

        bindViews();

    }

    public void bindViews() {

        rvDetailData = findViewById(R.id.rvDetailData);

        viewModelObservers();
    }

    public void viewModelObservers() {

        globalModel().getSetArticleData().observe(this, getSetArticle ->
        {
            try {
                ILBA_TypeAdapter ilba_typeAdapter = new ILBA_TypeAdapter(this, type, getSetArticle.getArticleData(), new ArrayList<>(), TypeActivity.this);
                rvDetailData.setAdapter(ilba_typeAdapter);
                rvDetailData.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        globalModel().getSetImages().observe(this, getSetImages ->
        {
            try {
                ILBA_TypeAdapter ilba_typeAdapter = new ILBA_TypeAdapter(this, type, new ArrayList<>(), getSetImages.getBreeds(), TypeActivity.this);
                rvDetailData.setAdapter(ilba_typeAdapter);
                rvDetailData.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    private GlobalModel globalModel() {

        if (globalModel == null) {
            globalModel = ViewModelProviders.of(this).get(GlobalModel.class);
        }
        return globalModel;
    }

    private TypePresenter typePresenter() {

        if (typePresenter == null) {
            typePresenter = new TypePresenter(this, globalModel());
        }
        return typePresenter;
    }

    @Override
    public void clickObject(String type, String id, Breed breed) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("type", type);
        if (type.equals("Article")) {
            intent.putExtra("id", id);
        } else {
            intent.putExtra("Breed", breed);
        }
        startActivity(intent);
    }
}