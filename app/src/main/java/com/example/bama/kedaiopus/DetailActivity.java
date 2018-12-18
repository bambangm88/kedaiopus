package com.example.bama.kedaiopus;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.bama.kedaiopus.app.AppController;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class DetailActivity extends AppCompatActivity {






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mkn);

        // hide the default actionbar
        getSupportActionBar();

        // Recieve data
        String nama = getIntent().getExtras().getString("nama_mkn");
        String deskripsi = getIntent().getExtras().getString("deskripsi_mkn");
        String rating = getIntent().getExtras().getString("rating") ;
        String harga = getIntent().getExtras().getString("harga_mkn");
        String url_img = getIntent().getExtras().getString("url_mkn") ;
        String kategori = getIntent().getExtras().getString("kategori") ;


        // ini views

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar_id);
        collapsingToolbarLayout.setTitleEnabled(true);

        Toolbar ToolBarAtas2 = (Toolbar)findViewById(R.id.my_toolbar);
        setSupportActionBar(ToolBarAtas2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView tv_nama = findViewById(R.id.aa_nama);
        TextView tv_harga = findViewById(R.id.aa_harga);
        TextView tv_categorie = findViewById(R.id.aa_categorie) ;
        TextView tv_description = findViewById(R.id.aa_description);
        TextView tv_rating  = findViewById(R.id.aa_rating) ;
        ImageView img = findViewById(R.id.aa_thumbnail);

        // setting values to each view


        double hrga_dbl = Double.parseDouble(harga) ;

        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        tv_harga.setText(formatRupiah.format((double)hrga_dbl));





        tv_nama.setText(nama);
        tv_categorie.setText(kategori);
        tv_description.setText(deskripsi);
        tv_rating.setText(rating);
       // tv_harga.setText(harga);

        collapsingToolbarLayout.setTitle(nama);


        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);


        // set image using Glide
        Glide.with(this).load(url_img).apply(requestOptions).into(img);


   }

    public void lihatGambar(View v){

        String url_img = getIntent().getExtras().getString("url_mkn") ;

        Intent intent = new Intent(DetailActivity.this, Viewimage.class);
        intent.putExtra("image",url_img) ;
        startActivity(intent);

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