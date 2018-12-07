package com.centennial.wecare;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {


    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;

    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        if(getIntent().getStringExtra("username") != null){
            etUsername.setText(getIntent().getStringExtra("username"));
        }

        mQueue = Volley.newRequestQueue(this);

    }

    private boolean isValidForm() {
        boolean valid = true;


        if (etUsername.getText().toString().isEmpty() || etUsername.getText().toString().length() == 0) {
            etUsername.setError("Enter Username");
            valid = false;
        }
        if (etUsername.getText().toString().isEmpty() || etPassword.getText().toString().length() == 0) {
            etPassword.setError("Enter Password");
            valid = false;
        }

        //TODO: Perform validations

        return valid;

    }

    @OnClick(R.id.btn_login)
    public void login(View view) {

        if(isValidForm()){
            Log.e("test","called");
            jSonParse(etUsername.getText().toString(), etPassword.getText().toString());
        }
    }

    @OnClick(R.id.btn_register)
    public void register(View view) {
        startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
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

                                if (pass.equals(password)) {
                                    InitClass.saveLogin(username, password);
                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                    finish();
                                } else {
                                    Snackbar.make(etUsername,"Username or Password incorrect !",Snackbar.LENGTH_SHORT).show();
                                }
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
                            Snackbar.make(etUsername,"Username or Password incorrect !",Snackbar.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse( VolleyError error ) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }
}

