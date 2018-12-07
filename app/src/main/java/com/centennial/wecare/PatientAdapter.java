package com.centennial.wecare;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.MyViewHolder> {

    private Context mContext;
    private List<Patient> patientList;

    public PatientAdapter(Context mContext, List<Patient> patientList) {
        this.mContext = mContext;
        this.patientList = patientList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.patient_card_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Patient patient = patientList.get(position);
        holder.title.setText(patient.getFirstname()+" "+ patient.getLastname());
        holder.subtitle.setText("ID: "+patient.getId());
    }

    @Override
    public int getItemCount() {
        return patientList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, subtitle;

        public MyViewHolder
                (View view) {
            super(view);
            title = view.findViewById(R.id.tv_main);
            subtitle = view.findViewById(R.id.tv_other);
        }
    }
}

