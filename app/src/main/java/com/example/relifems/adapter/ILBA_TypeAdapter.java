package com.example.relifems.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.relifems.R;
import com.example.relifems.gettersetter.ArticleData;
import com.example.relifems.gettersetter.Breed;
import com.example.relifems.utils.StaticMethod;

import java.util.ArrayList;
import java.util.List;

public class ILBA_TypeAdapter extends RecyclerView.Adapter<ILBA_TypeAdapter.ViewHolder> implements View.OnClickListener {

    private Context context;
    private String type;
    private ArrayList<ArticleData> articleList;
    private ArrayList<Breed> getSetBreeds;
    private OnItemClickI onItemClickI;
    private static final int article = 0, images = 1;
    private int selectPos = -1;

    public ILBA_TypeAdapter(Context context, String displayType, List<ArticleData> articleList, List<Breed> breedsList, OnItemClickI onItemClickI) {
        this.context = context;
        this.type = displayType;
        this.articleList = new ArrayList<>(articleList);
        this.getSetBreeds = new ArrayList<>(breedsList);
        this.onItemClickI = onItemClickI;
    }

    public interface OnItemClickI {
        void clickObject(String type, String id, Breed breed);
    }

    @NonNull
    @Override
    public ILBA_TypeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        switch (viewType) {
            case article:
                return new ViewHolder(inflater.inflate(R.layout.list_article, parent, false), article);
            default:
                return new ViewHolder(inflater.inflate(R.layout.list_article, parent, false), article);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ILBA_TypeAdapter.ViewHolder holder, int position) {

        int viewType = holder.getItemViewType();

        switch (viewType) {
            case article:
                if (articleList != null && articleList.size() > 0) {
                    holder.tv_NewsHead.setText("NewsTitle: " + articleList.get(position).getNewsSite());
                    holder.tv_Title.setText("NewsTitle: " + articleList.get(position).getTitle());
                    holder.tv_PublishedDate.setText("NewsTitle: " + StaticMethod.releaseDate(articleList.get(position).getPublishedAt()));
                    holder.btn_Details.setOnClickListener(this);
                    holder.btn_Details.setTag(position);
                }
                break;
            default:
                try {
                    if (getSetBreeds != null && getSetBreeds.size() > 0 && getSetBreeds.get(position).getBreeds() != null && getSetBreeds.get(position).getBreeds().get(0) != null) {
                        holder.tv_NewsHead.setText("Name: " + getSetBreeds.get(position).getBreeds().get(0).getName());
                        holder.tv_Title.setText("Temperament: " + getSetBreeds.get(position).getBreeds().get(0).getTemperament());
                        holder.tv_PublishedDate.setText("Life Span: " + getSetBreeds.get(position).getBreeds().get(0).getLifeSpan());
                        holder.btn_Details.setOnClickListener(this);
                        holder.btn_Details.setTag(position);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    public int getItemCount() {
        if (type.equals("Article"))
            return articleList.size();
        else
            return getSetBreeds.size() - 1;
    }

    @Override
    public int getItemViewType(int position) {
        switch (type) {
            case "Article":
                return article;
            default:
                return images;
        }
    }

    @Override
    public void onClick(View v) {

        int pos = (int) v.getTag();

        switch (v.getId()) {
            case R.id.btn_Details:
                if (type.equals("Article")) {
                    onItemClickI.clickObject(type, articleList.get(pos).getId(), null);
                } else {
                    onItemClickI.clickObject(type, null, getSetBreeds.get(pos));
                }
                break;
        }

    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_NewsHead, tv_Title, tv_PublishedDate;
        private Button btn_Details;

        ViewHolder(View view, int viewType) {
            super(view);

            switch (viewType) {
                case article:
                    tv_NewsHead = view.findViewById(R.id.tv_NewsHead);
                    tv_Title = view.findViewById(R.id.tv_Title);
                    tv_PublishedDate = view.findViewById(R.id.tv_PublishedDate);
                    btn_Details = view.findViewById(R.id.btn_Details);
                    break;
                case images:
                    break;
            }

        }

    }
}