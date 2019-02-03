package fr.smartrecruit.viewmodel;

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

public class ApplicationsAdapter extends RecyclerView.Adapter<ApplicationsAdapter.OffersViewHolder> {


    private List<JobOffer> appliedOffers;
    private Context context;

    public ApplicationsAdapter(List<JobOffer> appliedOffers, Context context){
        this.appliedOffers = appliedOffers;
        this.context = context;
    }

    @NonNull
    @Override
    public ApplicationsAdapter.OffersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View item = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.fragment_applications_row, parent, false);
        return new ApplicationsAdapter.OffersViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull OffersViewHolder holder, final int position) {
        holder.setView(appliedOffers.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentDetail = new Intent(context, OfferDetailActivity.class);
                intentDetail.putExtra("offer", appliedOffers.get(position));
                intentDetail.putExtra("fragment", FragmentConstants.Fragment_Applications);
                context.startActivity(intentDetail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return appliedOffers.size();
    }

    public class OffersViewHolder  extends RecyclerView.ViewHolder {
        private TextView company;
        private TextView position;
        private TextView location;
        private TextView postedDate;

        public OffersViewHolder(@NonNull View itemView) {
            super(itemView);
            findViews(itemView);
        }
        public void findViews(View view){
            company = view.findViewById(R.id.application_company);
            position = view.findViewById(R.id.application_position);
            location = view.findViewById(R.id.application_location);
            postedDate = view.findViewById(R.id.application_posted);
        }

        public void setView(JobOffer offer){
            position.setText(offer.getPosition());
            company.setText(offer.getCompany());
            location.setText(offer.getLocation());
            postedDate.setText(offer.getDatePosted());
        }
    }
}
