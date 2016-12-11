package br.com.thiengo.futebolblog.domain;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import br.com.thiengo.futebolblog.R;


@EViewGroup(R.layout.article_item)
public class ArticleItenView extends CardView {
    @ViewById(R.id.iv_thumb_article)
    ImageView ivThumbArticle;
    @ViewById(R.id.tv_title)
    TextView tvTitle;
    @ViewById(R.id.tv_summary)
    TextView tvSummary;
    @ViewById(R.id.tv_views_count)
    TextView tvViewsCount;
    @ViewById(R.id.tv_comments_count)
    TextView tvCommentsCount;
    @ViewById(R.id.tv_time_reading)
    TextView tvTimeReading;

    public ArticleItenView( Context context ){
        super(context);
    }

    public void bind( Article a ){
        tvTitle.setText( a.getTitle() );
        tvSummary.setText( a.getSummary() );
        tvViewsCount.setText( String.valueOf( a.getNumViews() )  );
        tvCommentsCount.setText( String.valueOf( a.getNumComments() ) );
        tvTimeReading.setText( a.getTimeToRead() );

        Picasso
            .with(tvTitle.getContext())
            .load( a.getImageUrl() )
            .into(ivThumbArticle);
    }
}
