package com.example.bama.kedaiopus.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.bama.kedaiopus.R;
import com.example.bama.kedaiopus.adapter.RecyclerViewAdapter;
import com.example.bama.kedaiopus.app.AppController;
import com.example.bama.kedaiopus.model.Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    private List<Model> lstMakan = new ArrayList<>();
    private RecyclerView myrv;

    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mManager;

    public static final String TAG_ID = "id";
    public static final String TAG_USERNAME = "username";


    private static final int TIME_INTERVAL = 2000;


    public static final String TAG_NAMA = "nama";
    public static final String TAG_HARGA = "harga";

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    String tag_json_obj = "json_obj_req";


    Context mycontext = getActivity();

    ProgressDialog pd;


    public HomeFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        myrv = view.findViewById(R.id.rv);
        mManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        myrv.setLayoutManager(mManager);
        mAdapter = new RecyclerViewAdapter(getActivity(), lstMakan);
        myrv.setAdapter(mAdapter);
        myrv.setNestedScrollingEnabled(false);
        jsoncall() ;
        return view;
    }






    public void jsoncall() {
        pd = new ProgressDialog(getActivity() );
        pd.setMessage("Silahkan Tunggu . .  ");
        pd.setCancelable(false);
        pd.show();


        final String URL_JSON = "http://opus.bambangm.com/viewData.php";
        JsonArrayRequest reqData = new JsonArrayRequest(URL_JSON,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        pd.cancel();
                        Log.d("volley", "response : " + response.toString());
                        for (int i = 0; i < response.length(); i++) {

                            //Toast.makeText(getApplicationContext(),String.valueOf(i),Toast.LENGTH_SHORT).show();

                            try {

                                JSONObject data = response.getJSONObject(i);
                                Model model= new Model();

                                model.setNama_mkn(data.getString("nama_mkn"));
                                model.setRating(data.getString("rating"));
                                model.setDeskripsi_mkn(data.getString("deskripsi_mkn"));
                                model.setUrl_mkn(data.getString("url_mkn"));
                                model.setHarga_mkn(data.getString("harga_mkn"));
                                model.setKategori(data.getString("kategori"));
                                //Toast.makeText(MainActivity.this,anime.toString(),Toast.LENGTH_SHORT).show();
                                lstMakan.add(model);
                            } catch (JSONException e) {
                                e.printStackTrace();
                                pd.cancel();

                            }
                        }
                        mAdapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("volley", "error : " + error.getMessage());
                        Snackbar snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content),  error.getMessage(), Snackbar.LENGTH_SHORT);
                        View snackbarView = snackbar.getView();
                        snackbarView.setBackgroundColor(ContextCompat.getColor(mycontext, R.color.Gagal));
                        snackbar.show();
                        pd.cancel();
                    }
                });

        AppController.getInstance().addToRequestQueue(reqData);
    }












}