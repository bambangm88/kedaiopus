package com.example.bama.kedaiopus;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bama.kedaiopus.adapter.RecyclerViewAdapter;
import com.example.bama.kedaiopus.fragment.AkunFragment;
import com.example.bama.kedaiopus.fragment.HomeFragment;
import com.example.bama.kedaiopus.fragment.KeranjangFragment;
import com.example.bama.kedaiopus.fragment.TransaksiFragment;
import com.example.bama.kedaiopus.model.Model;
import com.example.bama.kedaiopus.pesan.PesanActivity;
import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity  {



    TextView txt_id, txt_username;
    String id;



    private List<Model> lstAnime = new ArrayList<>();

    public static String myVarible ;

    public static final String TAG_ID = "id";
    public static final String TAG_USERNAME = "username";


    private static final int TIME_INTERVAL = 2000;
    private long mBackPressed;

    public static final String TAG_NAMA = "nama";
    public static final String TAG_HARGA = "harga";

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    String tag_json_obj = "json_obj_req";

    Toolbar toolbar ;
    Fragment fragment ;

    SharedPreferences sharedpreferences;



   // String myVariable = getIntent().getStringExtra(TAG_USERNAME);


    private TextView mTextMessage,mTextMessage1,mTextMessage2,mTextMessage3;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_activity);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        myVarible = getIntent().getStringExtra(TAG_USERNAME);
        Toast.makeText(getBaseContext(), TAG_USERNAME, Toast.LENGTH_SHORT).show();





        mTextMessage2 = (TextView) findViewById(R.id.message2);
        mTextMessage3 = (TextView) findViewById(R.id.message3);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        loadFragment(new HomeFragment());



    }

//supaya bisa di akses oleh class lain
    public static String getMyVarible (){
        return myVarible;
    }




    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = new HomeFragment();
                    loadFragment(fragment) ;
                    toolbar.setTitle("Kedai Opus");
                    return true;
                case R.id.navigation_dashboard:
                    fragment = new KeranjangFragment();
                    loadFragment(fragment) ;
                    toolbar.setTitle("Keranjang");
                    return true;
                case R.id.navigation_transaksi:
                    fragment = new TransaksiFragment();
                    loadFragment(fragment) ;
                    toolbar.setTitle("Transaksi");
                    return true;
                case R.id.navigation_notifications:
                    fragment = new AkunFragment();
                    loadFragment(fragment) ;
                    toolbar.setTitle("Akun");
                    return true;
            }
            return false;
        }
    };


    @Override
    public void onBackPressed() {


        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
            super.onBackPressed(); return;
        } else {
            Toast.makeText(getBaseContext(), "Tekan Back Sekali Lagi Untuk Keluar", Toast.LENGTH_SHORT).show();
        } mBackPressed = System.currentTimeMillis();



    }







    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav_activity, menu);

        MenuItem searchItem = menu.findItem(R.id.search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // lakukan query disini
                 searchView.clearFocus();

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });



        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.logOut) {
            showAlertLogOut();
            return true;
        }
        if (id == R.id.pesan) {
            Intent i = new Intent(MainActivity.this, PesanActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }





//
    //perintah keluar ------------
    private void showAlertLogOut() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setMessage("Keluar Akun Anda ? ?")
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();

                        sharedpreferences = getSharedPreferences(Login.my_shared_preferences, Context.MODE_PRIVATE);


                        // TODO Auto-generated method stub
                        // update login session ke FALSE dan mengosongkan nilai id dan username
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putBoolean(Login.session_status, false);
                        editor.putString(TAG_ID, null);
                        editor.putString(TAG_USERNAME, null);
                        editor.commit();

                        Intent intent = new Intent(MainActivity.this, Login.class);
                        finish();
                        startActivity(intent);
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
//----------------









}
