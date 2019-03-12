package fr.smartrecruit.controller.recruiter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import fr.smartrecruit.R;
import fr.smartrecruit.data.JobOffer;

public class MyOffersAdapter  extends RecyclerView.Adapter<MyOffersAdapter.MyOffersViewHolder> {

    private List<JobOffer> myOffers;
    private Context context;

    public MyOffersAdapter(List<JobOffer> offers, Context context){
        this.myOffers = offers;
        this.context = context;
    }

    @NonNull
    @Override
    public MyOffersAdapter.MyOffersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View item = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.fragment_myoffer_rows, parent, false);
        return new MyOffersAdapter.MyOffersViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull MyOffersAdapter.MyOffersViewHolder holder, final int position) {
        holder.setView(context, myOffers.get(position));
    }

    @Override
    public int getItemCount() {
        return myOffers.size();
    }

    public class MyOffersViewHolder extends RecyclerView.ViewHolder {

        private TextView company;
        private TextView position;
        private TextView location;
        private TextView description;
        private TextView postedDate;

        public MyOffersViewHolder(View itemView) {
            super(itemView);
            findViews(itemView);
        }

        public void findViews(View view){
            company = view.findViewById(R.id.myOffer_company);
            position = view.findViewById(R.id.myOffer_position);
            location = view.findViewById(R.id.myOffer_location);
            description = view.findViewById(R.id.myOffer_description);
            postedDate = view.findViewById(R.id.myOffer_posted);
        }

        public void setView(Context context, JobOffer myOffer){
                /*Picasso.get()
                        .load(offer.getImg())
                        .fit()
                        .centerCrop()
                        .into(image);*/
            position.setText(myOffer.getPosition());
            company.setText(myOffer.getCompany());
            location.setText(myOffer.getLocation());
            description.setText(myOffer.getDescription());
            postedDate.setText(myOffer.getDatePosted());
        }
    }
}
