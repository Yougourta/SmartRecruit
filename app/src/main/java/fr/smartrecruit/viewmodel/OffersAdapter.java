package fr.smartrecruit.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import fr.smartrecruit.R;
import fr.smartrecruit.data.JobOffer;
import fr.smartrecruit.view.OffersViewHolder;
import fr.smartrecruit.view.activities.OfferDetailActivity;

/**
 * Created by Yougourta on 22/11/2018.
 */

public class OffersAdapter extends RecyclerView.Adapter<OffersViewHolder> {

    private List<JobOffer> offers;
    private Context context;

    public OffersAdapter(List<JobOffer> offers, Context context){
        this.offers = offers;
        this.context = context;
    }

    @NonNull
    @Override
    public OffersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View item = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.offers_row, parent, false);
        return new OffersViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull OffersViewHolder holder, final int position) {
        holder.setView(offers.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentDetail = new Intent(context, OfferDetailActivity.class);
                intentDetail.putExtra("offer", offers.get(position));
                context.startActivity(intentDetail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return offers.size();
    }
}
