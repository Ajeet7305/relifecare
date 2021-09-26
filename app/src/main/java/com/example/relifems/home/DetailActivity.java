package com.example.relifems.home;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.example.relifems.R;
import com.example.relifems.gettersetter.ArticleData;
import com.example.relifems.gettersetter.Breed;
import com.example.relifems.presenter.TypePresenter;
import com.example.relifems.utils.StaticMethod;
import com.example.relifems.utils.StaticVariable;
import com.example.relifems.viewmodel.GlobalModel;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {

    private TypePresenter typePresenter;
    private GlobalModel globalModel;

    private ProgressDialog progressDialog;
    public static final int progressType = 0;
    private String type, id;
    private Breed breed;

    private ImageView ivImage;
    private TextView tvName, tvDownload, tvDescription;
    private Button btLink;
    private ArticleData articleData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        type = getIntent().getStringExtra("type");
        id = getIntent().getStringExtra("id");
        breed = getIntent().getParcelableExtra("Breed");

        String url;
        if (type != null && type.equals("Article")) {
            url = StaticVariable.ARTICLE;
            typePresenter().getDetailArticle(url, id);
        }

        bindViews();

    }

    public void bindViews() {

        ivImage = findViewById(R.id.iv_Image);
        tvDownload = findViewById(R.id.tv_Download);
        tvName = findViewById(R.id.tv_Name);
        tvDescription = findViewById(R.id.tv_Description);
        btLink = findViewById(R.id.bt_Test);

        btLink.setOnClickListener(this);

        viewModelObservers();
    }

    @Override
    public void onClick(View v) {

        String url;
        if (type.equals("Article")) {
            url = articleData.getUrl();
        } else {
            url = breed.getUrl();
        }
        switch (v.getId()) {
            case R.id.bt_Test:
                Intent browser = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browser);
                break;

            case R.id.tv_Download:
                //new DownloadFromURL().execute(url);
                break;
        }

    }

    public void viewModelObservers() {

        globalModel().getArticleData().observe(this, articleData ->
        {
            try {
                if (articleData != null) {
                    this.articleData = articleData;
                    Glide.with(this).load(articleData.getImageUrl()).into(ivImage);
                    tvName.setText("Name: " + articleData.getTitle());
                    tvDescription.setText("Description: " + articleData.getNewsSite() + "\nDate: " + StaticMethod.releaseDate(articleData.getPublishedAt()));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        if (breed != null) {
            Glide.with(this).load(breed.getUrl()).into(ivImage);
            tvName.setText("BreedName: " + breed.getBreeds().get(0).getName());
            tvDescription.setText("Description: " + breed.getBreeds().get(0).getLifeSpan() + " " + breed.getBreeds().get(0).getTemperament());
        }

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

    class DownloadFromURL extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showDialog(progressType);
        }

        @Override
        protected String doInBackground(String... fileUrl) {
            int count;
            try {
                URL url = new URL(fileUrl[0]);
                URLConnection urlConnection = url.openConnection();
                urlConnection.connect();
                // show progress bar 0-100%
                int fileLength = urlConnection.getContentLength();
                InputStream inputStream = new BufferedInputStream(url.openStream(), 8192);
                OutputStream outputStream = new FileOutputStream("/sdcard/downloadedfile.jpg");

                byte data[] = new byte[1024];
                long total = 0;
                while ((count = inputStream.read(data)) != -1) {
                    total += count;
                    publishProgress("" + (int) ((total * 100) / fileLength));
                    outputStream.write(data, 0, count);
                }
                // flushing output
                outputStream.flush();
                // closing streams
                outputStream.close();
                inputStream.close();

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }
            return null;
        }

        // progress bar Updating

        protected void onProgressUpdate(String... progress) {
            // progress percentage
            progressDialog.setProgress(Integer.parseInt(progress[0]));
        }

        @Override
        protected void onPostExecute(String file_url) {
            dismissDialog(progressType);
            String imagePath = Environment.getExternalStorageDirectory().toString() + "/downloadedfile.jpg";
            ivImage.setImageDrawable(Drawable.createFromPath(imagePath));
        }
    }

    //progress dialog
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case progressType: // we set this to 0
                progressDialog = new ProgressDialog(this);
                progressDialog.setMessage("File is Downloading. Please wait...");
                progressDialog.setIndeterminate(false);
                progressDialog.setMax(100);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.setCancelable(true);
                progressDialog.show();
                return progressDialog;
            default:
                return null;
        }
    }

}