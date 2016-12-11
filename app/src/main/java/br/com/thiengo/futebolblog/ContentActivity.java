package br.com.thiengo.futebolblog;

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

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import br.com.thiengo.futebolblog.domain.Article;

@EActivity(R.layout.activity_content)
public class ContentActivity extends AppCompatActivity {

    @ViewById
    TextView tvTitle;
    @ViewById
    TextView tvViewsCount;
    @ViewById
    TextView tvCommentsCount;
    @ViewById
    TextView tvTimeReading;
    @ViewById
    TextView tvContent;
    @ViewById
    ImageView ivHeader;
    @ViewById(R.id.toolbar_layout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @ViewById
    Toolbar toolbar;
    @ViewById
    FloatingActionButton fab;
    @Extra
    Article article;

    @AfterViews
    protected void init(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Compartilhar conteúdo", Snackbar.LENGTH_LONG)
                        .setAction("Compartilhar", null).show();
            }
        });

        if( article == null ){
            return;
        }

        collapsingToolbarLayout.setTitle( article.getTitle() );

        Picasso
            .with(this)
            .load( article.getImageUrl() )
            .into(ivHeader);

        tvTitle.setText( article.getTitle() );
        tvViewsCount.setText( String.valueOf( article.getNumViews() ) );
        tvCommentsCount.setText( String.valueOf( article.getNumComments() ) );
        tvTimeReading.setText( String.valueOf( article.getTimeToRead() ) );
        tvContent.setText( String.valueOf(Html.fromHtml(article.getContent()) ) );
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
