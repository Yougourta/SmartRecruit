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

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder> {

    private List<JobOffer> offers;
    private Context context;

    private JobOffer mRecentlyDeletedItem;
    private int mRecentlyDeletedItemPosition;

    public FavoritesAdapter(List<JobOffer> offers, Context context){
            this.offers = offers;
            this.context = context;
    }

    @NonNull
    @Override
    public FavoritesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
            View item = LayoutInflater
            .from(parent.getContext())
            .inflate(R.layout.fragment_favorites_row, parent, false);
            return new FavoritesViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritesViewHolder holder, final int position) {
            holder.setView(context, offers.get(position));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        Intent intentDetail = new Intent(context, OfferDetailActivity.class);
                        intentDetail.putExtra("offer", offers.get(position));
                        intentDetail.putExtra("fragment", FragmentConstants.Fragment_Favorites);
                        context.startActivity(intentDetail);
                }
            });
    }

    @Override
    public int getItemCount() {
            return offers.size();
    }

    public static class FavoritesViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;
        private TextView company;
        private TextView position;
        private TextView location;
        private TextView description;
        private TextView postedDate;

        public FavoritesViewHolder(View itemView) {
            super(itemView);
            findViews(itemView);
        }

        public void findViews(View view){
            image = view.findViewById(R.id.favoriteOffer_image);
            company = view.findViewById(R.id.favoriteOffer_company);
            position = view.findViewById(R.id.favoriteOffer_position);
            location = view.findViewById(R.id.favoriteOffer_location);
            description = view.findViewById(R.id.favoriteOffer_description);
            postedDate = view.findViewById(R.id.favoriteOffer_posted);
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