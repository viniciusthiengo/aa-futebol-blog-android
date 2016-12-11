package br.com.thiengo.futebolblog;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.com.thiengo.futebolblog.domain.Article;

public class ContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Compartilhar conteúdo", Snackbar.LENGTH_LONG)
                        .setAction("Compartilhar", null).show();
            }
        });

        init();
    }

    private void init(){
        if( getIntent() == null
                && getIntent().getParcelableExtra(Article.KEY) == null ){
            return;
        }

        Article article = getIntent().getParcelableExtra(Article.KEY);

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        collapsingToolbarLayout.setTitle( article.getTitle() );

        ImageView imageView = (ImageView) findViewById(R.id.iv_header);
        Picasso
                .with(this)
                .load( article.getImageUrl() )
                .into(imageView);

        TextView tv = (TextView) findViewById(R.id.tv_title);
        tv.setText( article.getTitle() );

        tv = (TextView) findViewById(R.id.tv_views_count);
        tv.setText( String.valueOf( article.getNumViews() ) );

        tv = (TextView) findViewById(R.id.tv_comments_count);
        tv.setText( String.valueOf( article.getNumComments() ) );

        tv = (TextView) findViewById(R.id.tv_time_reading);
        tv.setText( String.valueOf( article.getTimeToRead() ) );

        tv = (TextView) findViewById(R.id.tv_content);
        tv.setText( String.valueOf(Html.fromHtml(article.getContent()) ) );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
