package fr.smartrecruit.controller.recruiter;

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
import fr.smartrecruit.data.RecAppointment;

public class MyScheduledAppointmentsAdapter extends RecyclerView.Adapter<MyScheduledAppointmentsAdapter.MyScheduledAppointmentsViewHolder>{

    private List<RecAppointment> appointments;

    public MyScheduledAppointmentsAdapter(List<RecAppointment> appointments){
        this.appointments = appointments;
    }

    @NonNull
    @Override
    public MyScheduledAppointmentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View item = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.fragment_my_scheduled_appointments_row, parent, false);
        return new MyScheduledAppointmentsViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull MyScheduledAppointmentsViewHolder holder, int i) {
        holder.setView(appointments.get(i));
    }

    @Override
    public int getItemCount() {
        return appointments.size();
    }


    public class MyScheduledAppointmentsViewHolder extends RecyclerView.ViewHolder{

        private TextView position;
        private TextView applicant;
        private TextView day;
        private TextView hour;

        public MyScheduledAppointmentsViewHolder(View itemView) {
            super(itemView);
            findViews(itemView);
        }

        public void findViews(View view){
            position = view.findViewById(R.id.rec_sched_appointments_position);
            applicant = view.findViewById(R.id.rec_sched_appointments_applicant_id);
            day = view.findViewById(R.id.rec_sched_day);
            hour = view.findViewById(R.id.rec_sched_hour);
        }

        public void setView(RecAppointment appointment){
            position.setText(appointment.getPosition());
            applicant.setText("Applicant ID: "+appointment.getApplicant());
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
