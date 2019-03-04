package fr.smartrecruit.controller.candidat;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
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

public class ApplicationsAdapter extends RecyclerView.Adapter<ApplicationsAdapter.ApplicationsViewHolder> {
    private List<JobOffer> appliedOffers;
    private Context context;

    private JobOffer mRecentlyDeletedItem;
    private int mRecentlyDeletedItemPosition;

    public ApplicationsAdapter(List<JobOffer> appliedOffers, Context context){
        this.appliedOffers = appliedOffers;
        this.context = context;
    }

    @NonNull
    @Override
    public ApplicationsAdapter.ApplicationsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View item = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.fragment_applications_row, parent, false);
        return new ApplicationsAdapter.ApplicationsViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ApplicationsViewHolder holder, final int position) {
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

    public class ApplicationsViewHolder  extends RecyclerView.ViewHolder {
        private TextView company;
        private TextView position;
        private TextView location;
        private TextView postedDate;
        private TextView status;

        public ApplicationsViewHolder(@NonNull View itemView) {
            super(itemView);
            findViews(itemView);
        }
        public void findViews(View view){
            company = view.findViewById(R.id.application_company);
            position = view.findViewById(R.id.application_position);
            location = view.findViewById(R.id.application_location);
            postedDate = view.findViewById(R.id.application_posted);
            status = view.findViewById(R.id.application_status);
        }

        public void setView(JobOffer offer){
            position.setText(offer.getPosition());
            company.setText(offer.getCompany());
            location.setText(offer.getLocation());
            postedDate.setText(offer.getDatePosted());
            status.setText(offer.getStatus().getMessage());
            status.setBackgroundResource(R.drawable.rounded_corner);
            GradientDrawable drawable = (GradientDrawable) status.getBackground();
            drawable.setColor(Color.parseColor(offer.getStatus().getColor()));
        }
    }

    public void removeItem(int position){
        mRecentlyDeletedItem = appliedOffers.get(position);
        mRecentlyDeletedItemPosition = position;
        appliedOffers.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }
}
