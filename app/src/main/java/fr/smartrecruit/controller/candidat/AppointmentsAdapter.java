package fr.smartrecruit.controller.candidat;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import fr.smartrecruit.R;
import fr.smartrecruit.data.Appointment;

public class AppointmentsAdapter extends RecyclerView.Adapter<AppointmentsAdapter.AppointmentsViewHolder>{
    private List<Appointment> appointments;

    public AppointmentsAdapter(List<Appointment> appointments){
        this.appointments = appointments;
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
        private TextView day;
        private TextView hour;

        public AppointmentsViewHolder(@NonNull View itemView) {
            super(itemView);
            findViews(itemView);
        }
        public void findViews(View view){
            company = view.findViewById(R.id.app_sched_appointments_company);
            position = view.findViewById(R.id.app_sched_appointments_position);
            location = view.findViewById(R.id.app_sched_appointments_location);
            day = view.findViewById(R.id.app_sched_appointments_day);
            hour = view.findViewById(R.id.app_sched_appointments_hour);
        }

        public void setView(Appointment appointment){
            position.setText(appointment.getPosition());
            company.setText(appointment.getCompany());
            location.setText(appointment.getLocation());
            try {
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                Date date = format.parse(appointment.getDay());
                Log.d("appointment", appointment.getDay());
                SimpleDateFormat sdf2 = new SimpleDateFormat("EE dd MMMM yyyy");
                String stringDate2 = sdf2.format(date);
                Log.d("appointment", stringDate2);
                day.setText(stringDate2);
            }
            catch(Exception e) {
                e.printStackTrace();
            }
            hour.setText(appointment.getHour());
        }
    }
}
