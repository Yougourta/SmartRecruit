/*package fr.smartrecruit.preview;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import fr.smartrecruit.R;
import fr.smartrecruit.data.FragmentConstants;
import fr.smartrecruit.data.JobOffer;
import fr.smartrecruit.view.activities.OfferDetailActivity;

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.NotificationsViewHolder>{

    private List<PreviewAppointment> appointments;
    private Context context;

    private PreviewAppointment mRecentlyDeletedItem;
    private int mRecentlyDeletedItemPosition;

    public NotificationsAdapter(List<PreviewAppointment> appointments, Context context){
        this.appointments = appointments;
        this.context = context;
    }

    @NonNull
    @Override
    public NotificationsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View item = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.fragment_notifications_row, parent, false);
        return new NotificationsAdapter.NotificationsViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationsViewHolder holder, int position) {
        holder.setView(context, appointments.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentDetail = new Intent(context, OfferDetailActivity.class);
                intentDetail.putExtra("offer", appointments.get(position));
                intentDetail.putExtra("fragment", FragmentConstants.Fragment_Offers);
                context.startActivity(intentDetail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return appointments.size();
    }

    public class NotificationsViewHolder extends RecyclerView.ViewHolder{

        private TextView company;

        public NotificationsViewHolder(View itemView) {
            super(itemView);
            findViews(itemView);
        }

        public void findViews(View view){
            company = view.findViewById(R.id.offer_company);
        }

        public void setView(Context context, PreviewAppointment appointment){
            company.setText(appointment.getCompany());
        }
    }
}*/
