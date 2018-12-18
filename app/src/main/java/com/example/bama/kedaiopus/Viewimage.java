package com.example.bama.kedaiopus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import uk.co.senab.photoview.PhotoViewAttacher;

public class Viewimage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewimage);
        String url_img = getIntent().getExtras().getString("image") ;

        ImageView imageView = (ImageView)findViewById(R.id.lihat) ;


        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);


        // set image using Glide
        Glide.with(this).load(url_img).apply(requestOptions).into(imageView);
        //menerapkan tool bar sesuai id toolbar | ToolBarAtas adalah variabel buatan sndiri
        Toolbar ToolBarAtas2 = (Toolbar)findViewById(R.id.toolbar_lihat);
        setSupportActionBar(ToolBarAtas2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        PhotoViewAttacher pAttacher = new PhotoViewAttacher(imageView);
        pAttacher.setZoomable(true);
        pAttacher.update();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                //Write your logic here
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }





}
