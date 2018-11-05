package com.sawapps.baymaxhealthcare;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.sawapps.baymaxhealthcare.Adapters.DoctorsRecyclerViewAdapter;
import com.sawapps.baymaxhealthcare.Network.Remote.ApiUtils;
import com.sawapps.baymaxhealthcare.Network.Responses.DoctorSearchResponse.DoctorSearchResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemSelectedListener {

    RecyclerView doctorsRv;
    NavigationView navigationView;

    View selectLocation, search;

    AppCompatSpinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setTitle("Health Inspector");
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.getMenu().getItem(0).setChecked(true);
        doctorsRv = (RecyclerView) findViewById(R.id.doctorsRv);
        doctorsRv.setLayoutManager(new LinearLayoutManager(this));

        selectLocation = findViewById(R.id.select_location);
        search = findViewById(R.id.search);

        selectLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
//
//                startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST);
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDoctors();
            }
        });
        categories.add("All");
        categories.add("pediatrician");
        categories.add("internist");
        categories.add("cardiologist");
        categories.add("general-surgeon");
        categories.add("general-dentist");
        categories.add("gastroenterologist");
        categories.add("anesthesiologist");
        categories.add("obstetrics-gynecologist");

        setupSpinner();
        getDoctors();
    }

    ArrayAdapter<String> dataAdapter;

    List<String> categories = new ArrayList<>();

    private void setupSpinner() {
        spinner = findViewById(R.id.spinner);

        spinner.setVisibility(View.VISIBLE);
        spinner.setOnItemSelectedListener(HomeActivity.this);


        dataAdapter = new ArrayAdapter<>(HomeActivity.this, android.R.layout.simple_spinner_item, categories);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);
    }


    public void getDoctors() {

        String query = "https://api.betterdoctor.com/2016-03-01/doctors?location=37.773%2C-122.413%2C100&user_location=37.773%2C-122.413&skip=0&limit=10&user_key=76a2878a9e8d28dcd556ba0c53461174";

        if (currentCategory != null) {
            if (!currentCategory.equals("All")) {
                query += "&specialty_uid=" + currentCategory;
            }
        }
        Log.v("query", query);
        ApiUtils.getService()
                .searchDoctors(query)
                .enqueue(new Callback<DoctorSearchResponse>() {
                    @Override
                    public void onResponse(Call<DoctorSearchResponse> call, Response<DoctorSearchResponse> response) {
                        try {

                            if (response.body().data != null) {
                                doctorsRv.setAdapter(new DoctorsRecyclerViewAdapter(response.body().data));
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<DoctorSearchResponse> call, Throwable t) {
                        t.printStackTrace();

                    }
                });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.signout) {
            if (FirebaseAuth.getInstance() != null && FirebaseAuth.getInstance().getCurrentUser() != null) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(this, LoginActivity.class));
                finish();
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            if (navigationView != null)
                navigationView.getMenu().getItem(0).setChecked(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_3:

                startActivity(new Intent(this, BMIActivity.class));
                break;
            case R.id.nav_2:

                startActivity(new Intent(this, MyDietActivity.class));
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    String currentCategory = "All";

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


        currentCategory = categories.get(position);
        getDoctors();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
