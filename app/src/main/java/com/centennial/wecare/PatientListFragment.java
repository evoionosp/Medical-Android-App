package com.centennial.wecare;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

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

import java.util.ArrayList;
import java.util.List;


public class PatientListFragment extends Fragment {

    View rootView;
    RecyclerView recyclerView;
    PatientAdapter patientAdapter;
    List<Patient> patientList;
    private RequestQueue mQueue;

    public PatientListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        rootView =  inflater.inflate(R.layout.fragment_patient_list, container, false);
        recyclerView = rootView.findViewById(R.id.recycler_view);

        patientList = new ArrayList<>();

        mQueue = Volley.newRequestQueue(getActivity());

        jSonParse();
        patientAdapter = new PatientAdapter(getActivity(), patientList);
        recyclerView.setAdapter(patientAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Log.e("ListSize",patientList.size()+" is Size");

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Log.e("MainActivity", "RecyclerView Pos: " + position);
                        Intent intent = new Intent(getActivity(), PatientDetailsActivity.class);
                        intent.putExtra("id", patientList.get(position).getId());
                        startActivity(intent);
                        getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                    }
                })
        );



        return rootView;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search, menu);
        final SearchView search = (SearchView) menu.findItem(R.id.search).getActionView();
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //search(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //search(newText);
                return true;
            }
        });
    }

    private void jSonParse(){
        String url = "http://finalpatient.herokuapp.com/patients";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {

                    @Override

                    public void onResponse( JSONArray response ) {
                        try {

                            Log.e("JsonParse","Request sent:"+response.toString());
                            for (int i = 0;i<response.length();i++ ){
                                JSONObject patientObj = (JSONObject) response.get(i);
                                Patient patient = new Gson().fromJson(patientObj.toString(), Patient.class);
                                patientList.add(patient);
                            }

                            recyclerView.setAdapter(patientAdapter);

                        }
                        catch (JSONException e){
                            Log.e("PatientListFragment", "Error in Json: "+e.getLocalizedMessage());
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
