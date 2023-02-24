package com.main.simplejsonlistwuthcustomui;

import android.app.Application;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import androidx.recyclerview.widget.ListAdapter;


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
                Toast.makeText(getApplicationContext(), blogPostList.get(i).get("userId").toString(), Toast.LENGTH_SHORT).show();

            }
        });

        new GetPostList().execute();

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
            String[] icons = {String.valueOf(R.drawable.baseline_hive_24),String.valueOf(R.drawable.baseline_spa_24),
                    String.valueOf(R.drawable.baseline_skateboarding_24),String.valueOf(R.drawable.baseline_subway_24),
                    String.valueOf(R.drawable.baseline_workspace_premium_24),String.valueOf(R.drawable.baseline_whatshot_24),
                    String.valueOf(R.drawable.baseline_water_24),String.valueOf(R.drawable.baseline_visibility_24),
                    String.valueOf(R.drawable.baseline_verified_24),String.valueOf(R.drawable.baseline_update_24)};
            if(jsonString!=null){
                try {
                    blogposts = new JSONArray(jsonString);

                    for(int i= 0; i<blogposts.length();i++){
                        JSONObject b = blogposts.getJSONObject(i);
                        String title = b.getString(GV.TITLE);

                        HashMap<String,String> oneBlogPost = new HashMap<>();
                        oneBlogPost.put(GV.TITLE,title);
                        oneBlogPost.put(GV.BODY,b.getString(GV.BODY));
                        oneBlogPost.put(GV.ID,b.getString(GV.ID));
                        oneBlogPost.put(GV.USER_ID,b.getString(GV.USER_ID));
                        int userToImg = Integer.parseInt(b.getString(GV.USER_ID))-1;
                        oneBlogPost.put(GV.IMAGE, icons[userToImg]);

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

            SimpleAdapter listAdapter = new SimpleAdapter(
                    MainActivity.this,
                    blogPostList,
                    R.layout.item_singe_post,
                    new String[]{GV.TITLE,GV.BODY,GV.IMAGE,GV.ID,GV.USER_ID,},
                    new int[]{R.id.tv_title,R.id.tv_body,R.id.iv_image});

            setListAdapter(listAdapter);

        }


    }
}