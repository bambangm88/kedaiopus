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

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    int success;
    private Context mContext ;
    private List<Model> mData;
    RequestOptions option;
    private   AlertDialog.Builder dialog;
    LayoutInflater inflater;
    View dialogView;
    ProgressDialog pd;



    ProgressDialog pDialog;


    public RecyclerViewAdapter(Context mContext, List<Model> mData) {
        this.mContext = mContext;
        this.mData = mData;

        // Request option for Glide
        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {




        final View view;
         inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.mkn_row_item, parent, false);
        final MyViewHolder viewHolder = new MyViewHolder(view);
        pd = new ProgressDialog(mContext);




        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(mContext, DetailActivity.class);
                // sending data process
                i.putExtra("kategori", mData.get(viewHolder.getAdapterPosition()).getKategori());
                i.putExtra("nama_mkn", mData.get(viewHolder.getAdapterPosition()).getNama_mkn());
                i.putExtra("harga_mkn", mData.get(viewHolder.getAdapterPosition()).getHarga_mkn());
                i.putExtra("rating", mData.get(viewHolder.getAdapterPosition()).getRating());
                i.putExtra("deskripsi_mkn", mData.get(viewHolder.getAdapterPosition()).getDeskripsi_mkn());
                i.putExtra("url_mkn", mData.get(viewHolder.getAdapterPosition()).getUrl_mkn());


                mContext.startActivity(i);

            }
        });

        viewHolder.btnCart.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {



                String s = MainActivity.getMyVarible();


                final String nama_pesan =  s ;
                final String harga = mData.get(viewHolder.getAdapterPosition()).getHarga_mkn() ;
                final String nama_makanan = mData.get(viewHolder.getAdapterPosition()).getNama_mkn() ;
                final String url_imageSend = mData.get(viewHolder.getAdapterPosition()).getUrl_mkn() ;
                final String url = "http://opus.bambangm.com/insertPesanan.php" ;

                pd.setMessage("Memasukan Ke Keranjang . .");
                pd.setCancelable(false);
                pd.show();


                RequestQueue queue = Volley.newRequestQueue(mContext);

                StringRequest sendData = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                    public void onResponse(String response) {

                        pd.cancel();
                                try {
                                    JSONObject jObj = new JSONObject(response);
                                    success = jObj.getInt("success");
                                    if (success == 1) {

                                        dialog = new AlertDialog.Builder(new ContextThemeWrapper(mContext, R.style.AlertDialogCustom));
                                        final AlertDialog dialogInstance=dialog.create();
                                        LayoutInflater   inflater2 =  LayoutInflater.from(mContext);
                                        dialogView = inflater2.inflate(R.layout.form_custom_dialog, null);
                                        dialog.setView(dialogView);
                                        dialog.setCancelable(false);

                                        final AlertDialog  alertDialog=dialog.create();
                                        TextView nama = (TextView)dialogView.findViewById(R.id.nama_makanan) ;
                                        TextView hrga_mkn = (TextView)dialogView.findViewById(R.id.harga_makanan) ;
                                        ImageView img = (ImageView) dialogView.findViewById(R.id.thumbnailCustomDialog);
                                        ImageButton btnImg = (ImageButton)dialogView.findViewById(R.id.ceklis) ;
                                        Button bt1 = (Button)dialogView.findViewById(R.id.buttonBayar);
                                       // Button bt2 = (Button)dialogView.findViewById(R.id.buttonPesanLagi);

                                        dialog.setNegativeButton("Tambah Pesanan Lagi", new DialogInterface.OnClickListener() {

                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                            }
                                        });

                                        dialog.show();

                                        //parsing harga
                                        String Phrg = mData.get(viewHolder.getAdapterPosition()).getHarga_mkn();
                                        double Phrga_dbl = Double.parseDouble(Phrg) ;
                                        Locale localeID = new Locale("in", "ID");
                                        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
                                        hrga_mkn.setText(formatRupiah.format((double)Phrga_dbl));


                                        nama.setText(mData.get(viewHolder.getAdapterPosition()).getNama_mkn()) ;

                                        String  url_img = mData.get(viewHolder.getAdapterPosition()).getUrl_mkn() ;
                                        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

                                        Glide.with(mContext).load(url_img).apply(requestOptions).into(img);

                                        Snackbar snackbar = Snackbar.make(v,"Data Berhasil Masuk Keranjang", Snackbar.LENGTH_SHORT);
                                        View snackbarView = snackbar.getView();
                                        snackbarView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.toolbar));
                                        snackbar.show();
                                    } else {
                                        Snackbar snackbar = Snackbar.make(v, jObj.getString("message"), Snackbar.LENGTH_SHORT);
                                        View snackbarView = snackbar.getView();
                                        snackbarView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.Gagal));
                                        snackbar.show();
//
//                                       // Toast.makeText(mContext, jObj.getString("message"), Toast.LENGTH_LONG).show();
                                    }
                                } catch (JSONException e) {

                                    // JSON error
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                         Toast.makeText(mContext, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {

                    @Override
                    protected Map<String, String> getParams(){
                        Map<String,String> params = new HashMap<String, String>();
                        //mengirim value melalui parameter ke database
                        params.put("nama_pesan", nama_pesan);
                        params.put("nama_makanan", nama_makanan);
                        params.put("harga", harga);
                        params.put("url_img", url_imageSend);
                        return params;
                    }




                };



          queue.add(sendData);



            }

        });

        return viewHolder ;
    }










    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {





       String hrg = mData.get(position).getHarga_mkn();
        double hrga_dbl = Double.parseDouble(hrg) ;

        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        holder.tv_harga.setText(formatRupiah.format((double)hrga_dbl));

        holder.tv_nama.setText(mData.get(position).getNama_mkn());
        holder.tv_rating.setText(mData.get(position).getRating());
       // holder.tv_harga.setText(mData.get(position).getHarga_mkn());
        holder.tv_category.setText(mData.get(position).getKategori());

        // Load Image from the internet and set it into Imageview using Glide

        Glide.with(mContext).load(mData.get(position).getUrl_mkn()).apply(option).into(holder.img_thumbnail);



    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_nama ;
        TextView tv_rating ;
        TextView tv_harga ;
        TextView tv_category;
        ImageView img_thumbnail;
        LinearLayout view_container;
        CardView cardView ;
        ImageButton btnCart ;






        public MyViewHolder(View itemView) {
            super(itemView);

            view_container = itemView.findViewById(R.id.container);
            tv_nama = itemView.findViewById(R.id.title_mkn);
            tv_category = itemView.findViewById(R.id.kategori);
            tv_rating = itemView.findViewById(R.id.rating);
            tv_harga= itemView.findViewById(R.id.harga);
            img_thumbnail = itemView.findViewById(R.id.thumbnail);
            btnCart = itemView.findViewById(R.id.cart) ;
            cardView= (CardView) itemView.findViewById(R.id.cvKronologi);

        }
    }

}