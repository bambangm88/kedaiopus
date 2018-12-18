package com.example.bama.kedaiopus.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.bama.kedaiopus.MainActivity;
import com.example.bama.kedaiopus.R;
import com.example.bama.kedaiopus.adapter.RecyclerAdapterKeranjang;
import com.example.bama.kedaiopus.adapter.RecyclerViewAdapter;
import com.example.bama.kedaiopus.app.AppController;
import com.example.bama.kedaiopus.model.Model;
import com.example.bama.kedaiopus.model.Model_Krj;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KeranjangFragment extends Fragment {

    private List<Model_Krj> lstKrj = new ArrayList<>();
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

    private SwipeRefreshLayout mSwipeRefreshLayout;









    public KeranjangFragment() {
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
        View view = inflater.inflate(R.layout.fragment_keranjang, container, false);

        myrv = view.findViewById(R.id.rv_krj);
        mManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        myrv.setLayoutManager(mManager);
        mAdapter = new RecyclerAdapterKeranjang(getActivity(), lstKrj);
        myrv.setAdapter(mAdapter);
        myrv.setNestedScrollingEnabled(false);
        jsonCall() ;


        return view;





    }



//panggil data
    public void jsonCall() {
        pd = new ProgressDialog(getActivity() );
        pd.setMessage("Mengambil Keranjang . . ");
        pd.setCancelable(false);
        pd.show();


        final String URL_JSON = "http://opus.bambangm.com/Tampil_Keranjang.php";
        StringRequest  reqData = new StringRequest(Request.Method.POST, URL_JSON,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        pd.cancel();
                    Log.e("Response: ", response.toString());
                            try {
                                JSONObject jObj = new JSONObject(response);
                                int success = jObj.getInt("value");
                                if (success == 1) {
                                    mAdapter.notifyDataSetChanged();

                                    String getObject = jObj.getString("results");
                                    JSONArray jsonArray = new JSONArray(getObject);

                                    for (int i = 0; i < response.length(); i++) {
                                        JSONObject data = jsonArray.getJSONObject(i);

                                        Model_Krj model = new Model_Krj();

                                        model.setNama_pesan(data.getString("nama_pesan"));
                                        model.setNama_makanan(data.getString("nama_makanan"));
                                        model.setHarga(data.getString("harga"));
                                        model.setUrl_img(data.getString("url_img"));
                                        //Toast.makeText(MainActivity.this,anime.toString(),Toast.LENGTH_SHORT).show();
                                        lstKrj.add(model);

                                    }
                                }else {
                                  Toast.makeText(mycontext, jObj.getString(TAG_MESSAGE), Toast.LENGTH_SHORT).show();
                                    pd.cancel();
//                                    Snackbar snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content), jObj.getString("message"), Snackbar.LENGTH_SHORT);
//                                    View snackbarView = snackbar.getView();
//                                    snackbarView.setBackgroundColor(ContextCompat.getColor(mycontext, R.color.Gagal));
//                                    snackbar.show();
                                }
                            } catch (JSONException e) {
                                pd.cancel();
                                e.printStackTrace();
                            }

                        mAdapter.notifyDataSetChanged();
                        pd.cancel();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("volley", "error : " + error.getMessage());
                        Snackbar snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content), error.getMessage(), Snackbar.LENGTH_SHORT);
                        View snackbarView = snackbar.getView();
                        snackbarView.setBackgroundColor(ContextCompat.getColor(mycontext, R.color.Gagal));
                        snackbar.show();
                        pd.cancel();

                    }}
                ){

                    @Override
                    protected Map<String, String> getParams(){

                        String s = MainActivity.getMyVarible();
                        final String nama_pesan =  s ;

                        Map<String,String> params = new HashMap<String, String>();
                        //mengirim value melalui parameter ke database
                        params.put("nama_pesan", nama_pesan);
                        return params;
                    }


        };





        AppController.getInstance().addToRequestQueue(reqData);
    }
















}
