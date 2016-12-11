package br.com.thiengo.futebolblog.domain;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import br.com.thiengo.futebolblog.R;

/**
 * Created by viniciusthiengo on 10/12/16.
 */

public class ArticlesAdapter extends BaseAdapter {
    private ArrayList<Article> articles;
    private LayoutInflater inflater;

    public ArticlesAdapter(Context context, ArrayList<Article> articles){
        inflater = LayoutInflater.from(context);
        this.articles = articles;
    }

    @Override
    public int getCount() {
        return articles.size();
    }

    @Override
    public Object getItem(int i) {
        return articles.get(i);
    }

    @Override
    public long getItemId(int i) {
        return articles.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Article a = articles.get(i);
        ViewHolder holder;

        if( view == null ){
            view = inflater.inflate(R.layout.article_item, null);
            holder = new ViewHolder();
            view.setTag( holder );
            holder.setFromLayout( view );
        }
        else{
            holder = (ViewHolder) view.getTag();
        }

        holder.tvTitle.setText( a.getTitle() );
        holder.tvSummary.setText( a.getSummary() );
        holder.tvViewsCount.setText( String.valueOf( a.getNumViews() )  );
        holder.tvCommentsCount.setText( String.valueOf( a.getNumComments() ) );
        holder.tvTimeReading.setText( a.getTimeToRead() );

        Picasso
            .with(inflater.getContext())
            .load( a.getImageUrl() )
            .into(holder.ivThumbArticle);

        return view;
    }

    private static class ViewHolder{
        ImageView ivThumbArticle;
        TextView tvTitle;
        TextView tvSummary;
        TextView tvViewsCount;
        TextView tvCommentsCount;
        TextView tvTimeReading;

        void setFromLayout( View view ){
            ivThumbArticle = (ImageView) view.findViewById(R.id.iv_thumb_article);
            tvTitle = (TextView) view.findViewById(R.id.tv_title);
            tvSummary = (TextView) view.findViewById(R.id.tv_summary);
            tvViewsCount = (TextView) view.findViewById(R.id.tv_views_count);
            tvCommentsCount = (TextView) view.findViewById(R.id.tv_comments_count);
            tvTimeReading = (TextView) view.findViewById(R.id.tv_time_reading);
        }
    }
}
