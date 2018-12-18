package com.example.bama.kedaiopus.adapter;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.bama.kedaiopus.DetailActivity;

import com.example.bama.kedaiopus.MainActivity;
import com.example.bama.kedaiopus.R;

import com.example.bama.kedaiopus.model.Model;
import com.example.bama.kedaiopus.model.Model_Krj;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


/**
 * Created by Aws on 11/03/2018.
 */

public class RecyclerAdapterKeranjang extends RecyclerView.Adapter<RecyclerAdapterKeranjang.MyViewHolder> {
    int success;
    private Context mContext_krj ;
    private List<Model_Krj> mData_krj;
    RequestOptions option;
    private   AlertDialog.Builder dialog;
    LayoutInflater inflater;
    View dialogView;
    ProgressDialog pd;
    ProgressDialog pDialog;


    public RecyclerAdapterKeranjang(Context mContext_krj, List<Model_Krj> mData_krj) {
        this.mContext_krj = mContext_krj;
        this.mData_krj = mData_krj;

        // Request option for Glide
        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        final View view;
        inflater = LayoutInflater.from(mContext_krj);
        view = inflater.inflate(R.layout.keranjang_row_item, parent, false);
        final MyViewHolder viewHolder = new MyViewHolder(view);
        pd = new ProgressDialog(mContext_krj);

        return viewHolder ;
    }







    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {



        String hrg = mData_krj.get(position).getHarga();
        double hrga_dbl = Double.parseDouble(hrg) ;

        //parsing harga
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        holder.tv_harga.setText(formatRupiah.format((double)hrga_dbl));

        holder.tv_nama.setText(mData_krj.get(position).getNama_makanan());

        // Load Image from the internet and set it into Imageview using Glide
        Glide.with(mContext_krj).load(mData_krj.get(position).getUrl_img()).apply(option).into(holder.img_thumbnail);



    }

    @Override
    public int getItemCount() {
        return mData_krj.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_nama ;
        TextView tv_rating ;
        TextView tv_harga ;
        TextView tv_nama_pesan;
        ImageView img_thumbnail;
        CardView cardView ;
        ImageButton btnDelete;






        public MyViewHolder(View itemView) {
            super(itemView);


            tv_nama = itemView.findViewById(R.id.title_mkn_krj);
            tv_harga= itemView.findViewById(R.id.harga_krj);
            img_thumbnail = itemView.findViewById(R.id.thumbnail_krj);
            btnDelete= itemView.findViewById(R.id.delete_krj) ;
            cardView= (CardView) itemView.findViewById(R.id.cvKeranjang_row);

        }
    }

}