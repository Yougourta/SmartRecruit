package fr.smartrecruit.viewmodel;

import android.content.Context;
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
    public void onBindViewHolder(@NonNull OffersViewHolder holder, int position) {
        holder.setView(offers.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Hello darkness my old friend", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return offers.size();
    }
}
