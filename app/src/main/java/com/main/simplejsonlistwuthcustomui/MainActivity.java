package com.main.simplejsonlistwuthcustomui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ListAdapter;

import android.app.ListActivity;
import android.os.Bundle;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends ListActivity {

    String url ="https://jsonplaceholder.typicode.com/posts";

    JSONArray blogposts = null;

    ArrayList<HashMap<String,String>> blogPostList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        blogPostList = new ArrayList<>();
    }
}