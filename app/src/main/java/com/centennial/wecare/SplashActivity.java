package com.centennial.wecare;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SplashActivity extends AppCompatActivity {

    int SPLASH_TIME_OUT = 3000;

    private RequestQueue mQueue;

    @BindView(R.id.frameview)
    FrameLayout frameSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        final String username = InitClass.prefs.getString("username", null);
        final String password = InitClass.prefs.getString("password", null);

        mQueue = Volley.newRequestQueue(this);

        if(InitClass.checkInternetConnection(frameSplash,this)){

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    // This method will be executed once the timer is over
                    // Start your app main activity
                    jSonParse(username,password);
                }
            }, SPLASH_TIME_OUT);

        }

    }

    private void jSonParse(String username, String password){
        String url = "http://finalpatient.herokuapp.com/users/"+username;
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {

                    @Override

                    public void onResponse( JSONArray response ) {
                        try {

                            Log.e("JsonParse","Request sent:"+response.toString());

                            if(!response.toString().isEmpty()) {
                                JSONObject user = (JSONObject) response.get(0);
                                String pass = user.getString("password");

                                if (pass.equals(password)){
                                    startActivity(new Intent(SplashActivity.this, MainActivity.class).putExtra("hello","hello"));
                                }else {
                                    startActivity(new Intent(SplashActivity.this, LoginActivity.class).putExtra("hello", "hello"));
                                }
                                finish();
                            }else {
                                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                                finish();
                            }




//                            for (int i = 0;i<response.length();i++ ){
//                                JSONObject employee = (JSONObject) response.get(i);
//
//                                String firstName = employee.getString("username");
//
//                                if(firstName.equals(username)) {
//                                    if(employee.getString("password").equals(password)) {
//                                        InitClass.saveLogin(username, password);
//                                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
//                                        finish();
//                                    }
//                                }
//                            }

                        }
                        catch (JSONException e){
                            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                            finish();
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse( VolleyError error ) {
                error.printStackTrace();
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                finish();
            }
        });
        mQueue.add(request);
    }

}
