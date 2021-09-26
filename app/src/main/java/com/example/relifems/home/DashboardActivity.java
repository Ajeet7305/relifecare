package com.example.relifems.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.relifems.R;
import com.example.relifems.utils.StaticMethod;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {

    private Button Article, Images, LogOUT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        bindViews();

    }

    public void bindViews() {

        Article = findViewById(R.id.bt_Article);
        Images = findViewById(R.id.bt_Images);
        LogOUT = findViewById(R.id.bt_Logout);

        Article.setOnClickListener(this);
        Images.setOnClickListener(this);
        LogOUT.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.bt_Article:
                Intent intent = new Intent(this, TypeActivity.class);
                intent.putExtra("type", "Article");
                startActivity(intent);
                break;

            case R.id.bt_Images:
                intent = new Intent(this, TypeActivity.class);
                intent.putExtra("type", "Images");
                startActivity(intent);
                break;

            case R.id.bt_Logout:
                StaticMethod.showToastLong(this, getString(R.string.logout_successful));
                finish();
                break;
        }

    }
}