package com.centennial.wecare;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by patel on 10/26/2017.
 */

public class RegistrationActivity extends AppCompatActivity {

    String TAG = "RegistrationActivity";

    Calendar myCalendar;
    Date date;

    @BindView(R.id.et_firstname)
    EditText etFirstname;
    @BindView(R.id.et_lastname)
    EditText Lastname;
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_conf_password)
    EditText etConfPassword;
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_dob)
    EditText etDOB;
    @BindView(R.id.sp_bloodgroup)
    Spinner spBloodgroup;
    @BindView(R.id.sp_role)
    Spinner spRole;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);
       

        myCalendar = Calendar.getInstance();


//        Date date = DatePickerDialog.OnDateSetListener(new View.) { view, year, monthOfYear, dayOfMonth ->
//            // TODO Auto-generated method stub
//                myCalendar?.set(Calendar.YEAR, year)
//            myCalendar?.set(Calendar.MONTH, monthOfYear)
//            myCalendar?.set(Calendar.DAY_OF_MONTH, dayOfMonth)
//            updateDateLabel()
//        }
        new DatePickerDialog.OnDateSetListener() {
            // onDateSet method
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                date = myCalendar.getTime();
                updateDateLabel();
            }
        }; // DatePickerDialog.OnDateSetListener


        etDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  DatePickerDialog(this, )
               // DatePickerDialog(this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

    }


    public boolean isValidForm(){
            boolean valid = true;

           /* if (etFirstname.getText().toString().isEmpty() && etFirstname.getText().toString().length() > 0) {
                if (!etFirstname.getText().toString().matches("[A-Z][a-zA-Z]*").toRegex()) {
                    etFirstname.error = "Invalid Firstname";
                    valid = false
                }
            } else {
                etFirstname!!.error = "Enter Firstname"
                valid = false
            }

            if (etLastname.getText().toString().isEmpty() && etLastname.getText().toString().length() > 0) {
                if (!etLastname!!.text.toString().matches("[A-Z][a-zA-Z]*".toRegex())) {
                    etLastname!!.error = "Invalid Lastname"
                    valid = false
                }
            } else {
                etLastname!!.error = "Enter Surname"
                valid = false
            }

            if (etUsername.getText().toString().isEmpty() && etUsername.getText().toString().length() > 0) {
                if (!etUsername!!.text.toString().matches("[a-zA-Z0-9]+([_-]?[a-zA-Z0-9])*".toRegex())) {
                    etUsername!!.error = "Invalid Username"
                    valid = false
                }
            } else {
                etUsername!!.error = "Enter Username"
                valid = false
            }

            if (etPassword.getText().toString().isEmpty() && etPassword.getText().toString().length() > 0) {
                if (etPassword.getText().toString().length() < 8) {
                    etPassword!!.error = "Minimum 8 characters required"
                    valid = false
                }
            } else {
                etPassword!!.error = "Enter Password"
                valid = false
            }

            if (etConfPassword.getText().toString().isEmpty() && etConfPassword.getText().toString().length() > 0) {
                if(etPassword!!.text.toString().equals(etConfPassword!!.text.toString())){
                    etConfPassword!!.error = "Password doesn't match"
                    etPassword!!.error = "Password doesn't match"
                    valid = false
                }
            } else {
                etPassword!!.error = "Enter Password"
                valid = false
            }

            if (etDOB!!.text == null || etDOB.getText().toString().length() < 7) {
                etDOB!!.error = "Invalid Date"
                valid = false
            } */
            //TODO: Perform validations
            return valid ;
        }

    void updateDateLabel() {

        String myFormat = "dd/MM/yyyy";//In which you need put here
        //SimpleDateFormat sdf = SimpleDateFormat(myFormat, Locale.US);


       // etDOB.setText(sdf.format(myCalendar.getTime()));
    }



    public boolean onCreateOptionsMenu(Menu menu){
       // MenuInflater(this).inflate(R.menu.done_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        switch (id) {

            case R.id.done:
               // isValidForm
                doRegistration();
                return true;
            default:
            return false;
        }
    }

    private boolean doRegistration() {
        //TODO: registration process
        return false;
    }
}