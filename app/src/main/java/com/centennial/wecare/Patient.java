package com.centennial.wecare;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Patient {

    @SerializedName("idpatients")
    int id;
    @SerializedName("firstName")
    String firstname;
    @SerializedName("lastName")
    String lastname;
    @SerializedName("dateBirthDay")
    String birthdate;
    @Expose(serialize = false)   //False
    Boolean sex;
    @Expose(serialize = false)   //False
    int bloodgroup;
    @SerializedName("phoneNumber")
    String mobile;
    @Expose(serialize = false)  //False
    String email;
    @SerializedName("address")
    String address;
    @Expose(serialize = false)   //False
    String department;
    @SerializedName("doctorName")
    String doctorname;

    public Patient() {
    }

    public Patient(int id) {
        this.id = id;
    }


    public Patient(int id, String firstname, String lastname, String birthdate, Boolean sex, int bloodgroup, String mobile, String email, String address, String department, String doctorname) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.sex = sex;
        this.bloodgroup = bloodgroup;
        this.mobile = mobile;
        this.email = email;
        this.address = address;
        this.department = department;
        this.doctorname = doctorname;
    }

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public int getBloodgroup() {
        return bloodgroup;
    }

    public void setBloodgroup(int bloodgroup) {
        this.bloodgroup = bloodgroup;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDoctorname() {
        return doctorname;
    }

    public void setDoctorname(String doctorname) {
        this.doctorname = doctorname;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
