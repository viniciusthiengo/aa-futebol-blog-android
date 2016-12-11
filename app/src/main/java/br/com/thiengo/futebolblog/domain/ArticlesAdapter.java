package br.com.thiengo.futebolblog.domain;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.SystemService;

import java.util.ArrayList;

import br.com.thiengo.futebolblog.MainActivity;


@EBean
public class ArticlesAdapter extends BaseAdapter {
    private ArrayList<Article> articles;

    @SystemService
    protected LayoutInflater inflater;

    @AfterInject
    protected void afterInitialized(){
        articles = ((MainActivity) inflater.getContext()).articles;
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
        ArticleItenView articleItenView;

        if( view == null ){
            articleItenView = ArticleItenView_.build( inflater.getContext() );
        }
        else{
            articleItenView = (ArticleItenView) view;
        }

        articleItenView.bind( articles.get(i) );
        return articleItenView;
    }
}
