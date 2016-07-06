package com.example.aishwarya.spidertask3;


import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

public class MovieList extends AppCompatActivity {
   static ArrayList<Movies> listItem;
    MovieAdapter adp;
    GoogleApiClient client;
    Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        back=(Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MovieList.this,MainActivity.class);
                startActivity(i);
            }
        });
        listItem = new ArrayList<>();
        String Name = getIntent().getStringExtra("NAME");
        String Year = getIntent().getStringExtra("YEAR");
        String Plot="short";
        String r="json";

        try {
            new JSONAsyncTask().execute("http://www.omdbapi.com/?t="+ URLEncoder.encode(Name, "UTF-8")+"&y="+URLEncoder.encode(Year,"UTF-8")+"&plot="+URLEncoder.encode(Plot,"UTF-8")+"&r="+URLEncoder.encode(r,"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        GridView lv = (GridView) findViewById(R.id.list);
        adp = new MovieAdapter(getApplicationContext(), R.layout.row, listItem);
        lv.setAdapter(adp);
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }



    @Override
    public void onStart() {
        super.onStart();
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.aishwarya.spidertask3/http/host/path")
        );
        AppIndex.AppIndexApi.start(client,viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
                Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.aishwarya.spidertask3/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    class JSONAsyncTask extends AsyncTask<String, Void, Boolean> {
        ProgressDialog pd;

        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(MovieList.this);
            pd.setMessage(" please wait");
            pd.setTitle("Connecting...");
            pd.show();
            pd.setCancelable(false);
        }

        @Override
        protected Boolean doInBackground(String... urls) {
// TODO Auto-generated method stub
            try {

                HttpGet httppost = new HttpGet(urls[0]);
                HttpClient httpclient = new DefaultHttpClient();
                HttpResponse response = httpclient.execute(httppost);

                int status = response.getStatusLine().getStatusCode();
                if (status == 200) {
                    HttpEntity entity = response.getEntity();
                    String data = EntityUtils.toString(entity);
                    JSONObject object = new JSONObject(data);
                    Movies movies = new Movies();
                    movies.setTitle(object.getString("Title"));
                    movies.setGenre(object.getString("Genre"));
                    movies.setPoster(object.getString("Poster"));
                    listItem.add(movies);
                    return true;
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return false;
        }


        @Override
        protected void onPostExecute(Boolean result) {
// TODO Auto-generated method stub
            pd.cancel();
            adp.notifyDataSetChanged();
            if (result == false)
                Toast.makeText(getApplicationContext(), "Unable to fetch data from server", Toast.LENGTH_LONG).show();
        }
    }
}


