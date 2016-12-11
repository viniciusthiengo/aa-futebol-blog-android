package br.com.thiengo.futebolblog;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.NonConfigurationInstance;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import br.com.thiengo.futebolblog.domain.Article;
import br.com.thiengo.futebolblog.domain.ArticlesAdapter;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @ViewById(R.id.lv_articles)
    protected ListView listView;

    @InstanceState
    @NonConfigurationInstance
    public ArrayList<Article> articles = new ArrayList<>();

    @ViewById(R.id.toolbar)
    protected Toolbar toolbar;

    @ViewById(R.id.drawer_layout)
    protected DrawerLayout drawer;

    @ViewById(R.id.nav_view)
    protected NavigationView navigationView;

    @ViewById(R.id.pb_load)
    protected ProgressBar progressBar;

    @Bean
    protected ArticlesAdapter articlesAdapter;


    @AfterViews
    protected void viewsInitialized(){
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        retrieveData();
        listView.setAdapter( articlesAdapter );
    }

    @ItemClick(R.id.lv_articles)
    void itenClickedListener(Article article) {
        ContentActivity_.intent(this).article( article ).start();
    }

    private void retrieveData(){
        if( articles.size() == 0 ){
            progressBarLoad(true);
            runInBackground();
        }
    }

    private void progressBarLoad( boolean activated ){
        progressBar.setVisibility( activated ? View.VISIBLE : View.GONE );
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private String serverRequest() throws IOException {
        URL url = new URL( Article.URL );
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        InputStream in = new BufferedInputStream(urlConnection.getInputStream());
        BufferedReader r = new BufferedReader(new InputStreamReader(in));

        StringBuilder jsonString = new StringBuilder();
        String line;
        while ((line = r.readLine()) != null) {
            jsonString.append(line).append('\n');
        }

        urlConnection.disconnect();
        return jsonString.toString();
    }

    @Background
    protected void runInBackground(){
        try {
            String answer = serverRequest();

            JSONArray jsonArray = new JSONArray( answer );
            for( int i = 0, tam = jsonArray.length(); i < tam; i++ ){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Article a = new Article();
                a.setFromJson(jsonObject);

                articles.add( a );
            }
            runInUIThread();
        }
        catch(Exception e){}
    }

    @UiThread
    protected void runInUIThread(){
        progressBarLoad(false);
        articlesAdapter.notifyDataSetChanged();
    }
}
