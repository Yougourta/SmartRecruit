package fr.smartrecruit.controller.recruiter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import fr.smartrecruit.R;
import fr.smartrecruit.data.RecAppointment;

public class MyAppointmentsAdapter extends RecyclerView.Adapter<MyAppointmentsAdapter.MyAppointmentsViewHolder>{

    private List<RecAppointment> appointments;

    private RecAppointment mRecentlyDeletedItem;
    private int mRecentlyDeletedItemPosition;

    public MyAppointmentsAdapter(List<RecAppointment> appointments){
        this.appointments = appointments;
    }

    @NonNull
    @Override
    public MyAppointmentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View item = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.fragment_my_appointments_row, parent, false);
        return new MyAppointmentsAdapter.MyAppointmentsViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAppointmentsViewHolder holder, int position) {
        holder.setView(appointments.get(position));
    }

    @Override
    public int getItemCount() {
        return appointments.size();
    }

    public class MyAppointmentsViewHolder extends RecyclerView.ViewHolder{

        private TextView position;
        private TextView location;
        private TextView applicant;
        private TextView offer;

        public MyAppointmentsViewHolder(View itemView) {
            super(itemView);
            findViews(itemView);
        }

        public void findViews(View view){
            position = view.findViewById(R.id.recAppointments_position);
            location = view.findViewById(R.id.recAppointments_location);
            applicant = view.findViewById(R.id.recAppointments_applicant_id);
            offer = view.findViewById(R.id.recAppointments_offer_id);

        }

        public void setView(RecAppointment appointment){
            position.setText(appointment.getPosition());
            location.setText(appointment.getLocation());
            applicant.setText("Applicant ID: "+appointment.getApplicant());
            offer.setText("Offer ID: "+appointment.getOffer());
        }
    }

    public void removeItem(int position){
        mRecentlyDeletedItem = appointments.get(position);
        mRecentlyDeletedItemPosition = position;
        appointments.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }
}
