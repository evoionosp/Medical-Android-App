package com.centennial.wecare;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    static TextView navUser, navPhone;
    static ImageView navImage;
    static View navHeader;
    NavigationView navigationView;
    Toolbar toolbar;
    Fragment fragment;
    Context context;
    Intent intent;
    public static View v = navHeader;
    Snackbar snackbar;
    Activity activity = this;
    ;
    private Handler mHandler;
    public static ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Please wait...");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);

        setSupportActionBar(toolbar);
        mHandler = new Handler();
        HomeFragment fragment = new HomeFragment();
        android.support.v4.app.FragmentTransaction fragmenttransaction = getSupportFragmentManager().beginTransaction();
        fragmenttransaction.replace(R.id.fragment_container, fragment).commit();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerview = navigationView.getHeaderView(0);

        navUser = (TextView) headerview.findViewById(R.id.navUser);
        navPhone = (TextView) headerview.findViewById(R.id.navPhone);
        navImage = (ImageView) headerview.findViewById(R.id.navImage);
        navHeader = (View) headerview.findViewById(R.id.nav_header);
        navImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // onClickLogin(v);
            }
        });

        if (true) {  //No User Logged In
            navUser.setText("Login / Register");
            navPhone.setText("Mobile No.");
            navImage.setImageResource(R.drawable.mydp);
        } else {
                navUser.setText("Name");
                navPhone.setText("id");

        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Exit");
            builder.setMessage("Are you sure you want to Exit ?");

            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            if (Build.VERSION.SDK_INT >= 21) {
                                finishAndRemoveTask();
                            } else {
                                finish();
                                System.exit(0);
                            }
                        }

                    }
            );
            builder.setNegativeButton("NO", new DialogInterface.OnClickListener()

                    {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }

            );
            AlertDialog alert = builder.create();
            alert.show();
        }
    }

    public void onClickLogin(final View v) {
        new Thread() {
            @Override
            public void run() {

             /*   if (ParseAnonymousUtils.isLinked(ParseUser.getCurrentUser())) {
                    if (!InitClass.showNoInternetConnectionAlert(v, activity)) {
                        LoginFragment fragment = new LoginFragment();
                        android.support.v4.app.FragmentTransaction fragmenttransaction = getSupportFragmentManager().beginTransaction();
                        fragmenttransaction.replace(R.id.fragment_container, fragment).commit();
                    }

                } else {
                    ParseUser currentUser = ParseUser.getCurrentUser();
                    if (currentUser != null) {
                        Intent intent = new Intent(MainActivity.this, Profile_User_Activity.class);
                        startActivity(intent);
                        supportFinishAfterTransition();
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

                    } else {
                        if (!InitClass.showNoInternetConnectionAlert(v, activity)) {
                            LoginFragment fragment = new LoginFragment();
                            android.support.v4.app.FragmentTransaction fragmenttransaction = getSupportFragmentManager().beginTransaction();
                            fragmenttransaction.replace(R.id.fragment_container, fragment).commit();
                        }
                    }

                } */
            }
        }.start();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        final int id = item.getItemId();
        setTitle(item.getTitle());
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (id == R.id.nav_online) {
                    HomeFragment fragment = new HomeFragment();
                    android.support.v4.app.FragmentTransaction fragmenttransaction = getSupportFragmentManager().beginTransaction();
                    fragmenttransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
                    fragmenttransaction.replace(R.id.fragment_container, fragment).commit();


                } else if (id == R.id.patient_list) {
                    PatientListFragment fragment = new PatientListFragment();
                    android.support.v4.app.FragmentTransaction fragmenttransaction = getSupportFragmentManager().beginTransaction();
                    fragmenttransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
                    fragmenttransaction.replace(R.id.fragment_container, fragment).commit();


                } /*else if (id == R.id.nav_problem) {
                    ProblemFragment fragment = new ProblemFragment();
                    android.support.v4.app.FragmentTransaction fragmenttransaction = getSupportFragmentManager().beginTransaction();
                    fragmenttransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
                    fragmenttransaction.replace(R.id.fragment_container, fragment).commit();
                    if (!InitClass.showNoInternetConnectionAlert(navHeader, activity)) {
                    }

                } else if (id == R.id.nav_news) {
                    NewsFragment fragment = new NewsFragment();
                    android.support.v4.app.FragmentTransaction fragmenttransaction = getSupportFragmentManager().beginTransaction();
                    fragmenttransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
                    fragmenttransaction.replace(R.id.fragment_container, fragment).commit();
                    if (!InitClass.showNoInternetConnectionAlert(navHeader, activity)) {
                    }

                } else if (id == R.id.nav_feedback) {
                    FeedbackFragment fragment = new FeedbackFragment();
                    android.support.v4.app.FragmentTransaction fragmenttransaction = getSupportFragmentManager().beginTransaction();
                    fragmenttransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
                    fragmenttransaction.replace(R.id.fragment_container, fragment).commit();
                    if (!InitClass.showNoInternetConnectionAlert(navHeader, activity)) {
                    }
                } else if (id == R.id.nav_about) {
                    AboutFragment fragment = new AboutFragment();
                    android.support.v4.app.FragmentTransaction fragmenttransaction = getSupportFragmentManager().beginTransaction();
                    fragmenttransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
                    fragmenttransaction.replace(R.id.fragment_container, fragment).commit();
                } else if (id == R.id.nav_samajdetail) {
                    samajdetail fragment = new samajdetail();
                    android.support.v4.app.FragmentTransaction fragmenttransaction = getSupportFragmentManager().beginTransaction();
                    fragmenttransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
                    fragmenttransaction.replace(R.id.fragment_container, fragment).commit();
                } */
            }
        }, 250);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.logout:
               InitClass.logout(navHeader, activity);
            default:
                return false;
        }
    }


}


