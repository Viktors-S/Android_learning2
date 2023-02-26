package com.main.simplejsonlistwuthcustomui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class BlogActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog);

        Bundle extras = getIntent().getExtras();

        TextView title = findViewById(R.id.tv_blog_body);//title
        ImageView image = findViewById(R.id.iv_image_blog);
        TextView userId = findViewById(R.id.tv_userId_blog);
        TextView id = findViewById(R.id.tv_id_blog);
        TextView body = findViewById(R.id.tv_body_blog);

        ImageButton back = findViewById(R.id.ib_arrow_back);
        Button show_full_blog = findViewById(R.id.btn_show_full_blog);
        ImageButton minimize_blog = findViewById(R.id.ib_minimize_blog);


        if(!extras.isEmpty()){
            title.setText((CharSequence) extras.get("title"));
            image.setImageResource(Integer.parseInt((String) extras.get("iv_image")));
            userId.setText("USER ID: "+(CharSequence) extras.get("userId"));
            id.setText("ID: "+(CharSequence) extras.get("id"));
            body.setText((CharSequence) extras.get("body").toString().substring(0,extras.get("body").toString().length()/2)+"....");

        }else{
            Toast.makeText(this, "BROKEN BLOG", Toast.LENGTH_SHORT).show();
        }


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        show_full_blog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                body.setText((CharSequence) extras.get("body"));
                minimize_blog.setVisibility(View.VISIBLE);
                show_full_blog.setVisibility(View.GONE);
            }
        });

        minimize_blog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                body.setText((CharSequence) extras.get("body").toString().substring(0,extras.get("body").toString().length()/2)+"....");
                show_full_blog.setVisibility(View.VISIBLE);
                minimize_blog.setVisibility(View.GONE);
            }
        });

    }
}
