package com.centennial.wecare;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.BindView;
import butterknife.BindViews;

public class PatientDetailsActivity extends AppCompatActivity {

    @BindView(R.id.tv_Id)
    TextView tvId;
    @BindView(R.id.et_firstname)
    EditText etFirstname;
    @BindView(R.id.et_lastname)
    EditText etLastname;
    @BindView(R.id.et_doctor_name)
    EditText etDoctorname;
    @BindView(R.id.et_department)
    EditText etDepartment;
    @BindView(R.id.et_mobile)
    EditText etMobile;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_dob)
    EditText etDOB;
    @BindView(R.id.sp_bloodgroup)
    Spinner spBloodgroup;
    @BindView(R.id.rg_sex)
    RadioGroup rgSex;
    @BindView(R.id.rb_male)
    RadioButton rbMale;
    @BindView(R.id.rb_female)
    RadioButton rbFemale;

    @BindViews({ R.id.et_firstname, R.id.et_lastname, R.id.et_doctor_name, R.id.et_department, R.id.et_mobile, R.id.et_address, R.id.et_email, R.id.et_dob})
    List<EditText> etAll;

    int Id = 0;

    private RequestQueue mQueue;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_details);
        ButterKnife.bind(this);

        mQueue = Volley.newRequestQueue(this);

        Id = getIntent().getIntExtra("id",0 );

        if (Id > 0){
            tvId.setText(tvId.getText().toString()+": "+Id);
            jSonParse();
            setEditable(false);
        }
    }



    public void setEditable(Boolean value){
        ButterKnife.apply(etAll, ENABLED, value);
        ButterKnife.apply(rbFemale, ENABLED, value);
        ButterKnife.apply(rbMale, ENABLED, value);
        ButterKnife.apply(spBloodgroup, ENABLED, value);
        invalidateOptionsMenu();
    }

    public void setData(Patient patient){
        try {
            etFirstname.setText(patient.getFirstname());
            etLastname.setText(patient.lastname);
            etDoctorname.setText(patient.doctorname);
            etMobile.setText(patient.mobile);
            etAddress.setText(patient.address);
            etDepartment.setText(patient.department);
            etEmail.setText(patient.email);

        } catch (Exception ex){
            Log.e("PatientDetailsActivity", "Error while setting Data: "+ex.getLocalizedMessage());
            ex.printStackTrace();
        }
    }

    private void jSonParse(){
        String url = "http://finalpatient.herokuapp.com/patients/"+Id;
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {

                    @Override

                    public void onResponse( JSONArray response ) {
                        try {

                            Log.e("JsonParse","Request sent:"+response.toString());

                            if(!response.toString().isEmpty()) {
                                JSONObject obj = (JSONObject) response.get(0);
                                Patient patient = new Gson().fromJson(obj.toString(), Patient.class);
                                setData(patient);
                            }

                        }
                        catch (JSONException e){
                            Snackbar.make(etAddress,"Error !!",Snackbar.LENGTH_SHORT).show();
                            Log.e("PatientDetailsActivity", "Error in Json: "+e.getLocalizedMessage());
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.edit:
                setEditable(true);
                return true;

            case R.id.save:
                setEditable(false);
                return  true;

            default:
                return false;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.edit_menu, menu);

        MenuItem edit = menu.findItem(R.id.edit);
        MenuItem save = menu.findItem(R.id.save);
        save.setVisible(etFirstname.isEnabled());
        edit.setVisible(!save.isVisible());
        return super.onCreateOptionsMenu(menu);
    }

    static final ButterKnife.Setter<View, Boolean> ENABLED = new ButterKnife.Setter<View, Boolean>() {
        @Override public void set(View view, Boolean value, int index) {
            view.setEnabled(value);
        }
    };
}
