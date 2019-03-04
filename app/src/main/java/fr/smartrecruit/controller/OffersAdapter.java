package fr.smartrecruit.controller;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import fr.smartrecruit.R;
import fr.smartrecruit.data.FragmentConstants;
import fr.smartrecruit.data.JobOffer;
import fr.smartrecruit.view.activities.OfferDetailActivity;

/**
 * Created by Yougourta on 22/11/2018.
 */

public class OffersAdapter extends RecyclerView.Adapter<OffersAdapter.OffersViewHolder> {

    private List<JobOffer> offers;
    private Context context;

    private JobOffer mRecentlyDeletedItem;
    private int mRecentlyDeletedItemPosition;

    public OffersAdapter(List<JobOffer> offers, Context context){
        this.offers = offers;
        this.context = context;
    }

    @NonNull
    @Override
    public OffersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View item = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.fragment_offers_row, parent, false);
        return new OffersViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull OffersViewHolder holder, final int position) {
        holder.setView(context, offers.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentDetail = new Intent(context, OfferDetailActivity.class);
                intentDetail.putExtra("offer", offers.get(position));
                intentDetail.putExtra("fragment", FragmentConstants.Fragment_Offers);
                context.startActivity(intentDetail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return offers.size();
    }

    public class OffersViewHolder extends RecyclerView.ViewHolder{

        private ImageView image;
        private TextView company;
        private TextView position;
        private TextView location;
        private TextView description;
        private TextView postedDate;

        public OffersViewHolder(View itemView) {
            super(itemView);
            findViews(itemView);
        }

        public void findViews(View view){
            image = view.findViewById(R.id.offer_image);
            company = view.findViewById(R.id.offer_company);
            position = view.findViewById(R.id.offer_position);
            location = view.findViewById(R.id.offer_location);
            description = view.findViewById(R.id.offer_description);
            postedDate = view.findViewById(R.id.offer_posted);
        }

        public void setView(Context context, JobOffer offer){
            /*Picasso.get()
                    .load(offer.getImg())
                    .fit()
                    .centerCrop()
                    .into(image);*/
            position.setText(offer.getPosition());
            company.setText(offer.getCompany());
            location.setText(offer.getLocation());
            description.setText(offer.getDescription());
            postedDate.setText(offer.getDatePosted());
        }
    }

    public void removeItem(int position){
        mRecentlyDeletedItem = offers.get(position);
        mRecentlyDeletedItemPosition = position;
        offers.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }
}
