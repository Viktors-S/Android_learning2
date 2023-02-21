package com.main.simplejsonlistwuthcustomui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ListAdapter;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends ListActivity {

    String url ="https://jsonplaceholder.typicode.com/posts";

    JSONArray blogposts = null;

    private ProgressDialog progressDialog;

    ArrayList<HashMap<String,String>> blogPostList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        blogPostList = new ArrayList<>();
        ListView lv = getListView();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }

    private class GetPostList extends AsyncTask<Void,Void,Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("downloading data, please wait!");
            progressDialog.setCancelable(false);
            progressDialog.show();

        }

        @Override
        protected Void doInBackground(Void... voids) {

            ServiceHandler sh = new ServiceHandler(url);
            String jsonString = sh.makeServiceCall();
            if(jsonString!=null){
                try {
                    blogposts = new JSONArray(jsonString);

                    for(int i= 0; i<blogposts.length();i++){
                        JSONObject b = blogposts.getJSONObject(i);
                        String title = b.getString(GV.TITLE);

                        HashMap<String,String> oneBlogPost = new HashMap<>();
                        oneBlogPost.put(GV.TITLE,title);
                        oneBlogPost.put(GV.BODY,b.getString(GV.BODY));

                        blogPostList.add(oneBlogPost);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);

            if(progressDialog.isShowing()){
                progressDialog.dismiss();
            }
        }


    }
}