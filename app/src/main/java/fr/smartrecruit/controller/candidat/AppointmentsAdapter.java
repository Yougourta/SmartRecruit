package fr.smartrecruit.controller.candidat;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fr.smartrecruit.R;
import fr.smartrecruit.data.Appointment;

public class AppointmentsAdapter extends RecyclerView.Adapter<AppointmentsAdapter.AppointmentsViewHolder>{
    private List<Appointment> appointments;

    public AppointmentsAdapter(List<Appointment> appointments){
        //this.appointments = appointments;
        this.appointments = new ArrayList();
    }

    @NonNull
    @Override
    public AppointmentsAdapter.AppointmentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View item = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.fragment_appointments_row, parent, false);
        return new AppointmentsAdapter.AppointmentsViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentsAdapter.AppointmentsViewHolder holder, final int position) {
        holder.setView(appointments.get(position));
    }

    @Override
    public int getItemCount() {
        return appointments.size();
    }

    public class AppointmentsViewHolder  extends RecyclerView.ViewHolder {
        private TextView company;
        private TextView position;
        private TextView location;
        private TextView date;

        public AppointmentsViewHolder(@NonNull View itemView) {
            super(itemView);
            findViews(itemView);
        }
        public void findViews(View view){
            company = view.findViewById(R.id.appointment_company);
            position = view.findViewById(R.id.appointment_position);
            location = view.findViewById(R.id.appointment_location);
            date = view.findViewById(R.id.appointment_date);
        }

        public void setView(Appointment appointment){
            position.setText(appointment.getJobOffer().getPosition());
            company.setText(appointment.getJobOffer().getCompany());
            location.setText(appointment.getJobOffer().getLocation());
            date.setText(appointment.getDateHour());
        }
    }
}
