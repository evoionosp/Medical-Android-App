package com.centennial.wecare;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.support.multidex.MultiDex;
import android.view.View;

public class InitClass extends Application {

    static Object connObj;
    static String TAG = "InitClass";
    static String MY_PREFS_NAME = "we_care";
    public static SharedPreferences prefs;
    ;

    @Override
    public void onCreate() {
        super.onCreate();
        connObj = getSystemService(Context.CONNECTIVITY_SERVICE);
        prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);

    }

    protected static boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;
        ConnectivityManager cm = (ConnectivityManager) connObj;
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }




    protected static boolean checkInternetConnection(View view, final Activity activity) {//(final Context context, final Activity activity) {
        if (!haveNetworkConnection()) {
            Snackbar snackbar = Snackbar
                    .make(view, "Can't Connect Right Now", Snackbar.LENGTH_LONG)
                    .setAction("RETRY", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //showNoInternetConnectionAlert(view);
                            new Thread(){
                                @Override
                                public void run() {
                                    activity.startActivity(new Intent(activity, SplashActivity.class));

                                }
                            } .start();
                            activity.finish();
                        }
                    });
            snackbar.show();
            return false;
        }
        return true;
    }
    public static void logout(View v,Activity activity) {

        SharedPreferences.Editor prefEditor = prefs.edit();
        prefEditor.putString("username", null);
        prefEditor.putString("password", null);
        prefEditor.apply();
        activity.startActivity(new Intent(activity, LoginActivity.class));
        //Snackbar.make(v, "Successfully Loged Out !", Snackbar.LENGTH_LONG).show();


    }

    public static void saveLogin(String username, String passeword) {

        SharedPreferences.Editor prefEditor = prefs.edit();
        prefEditor.putString("username", username);
        prefEditor.putString("password", passeword);
        prefEditor.apply();



    }



    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}