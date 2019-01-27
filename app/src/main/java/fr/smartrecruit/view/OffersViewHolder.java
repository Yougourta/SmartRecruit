package fr.smartrecruit.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import fr.smartrecruit.R;
import fr.smartrecruit.data.JobOffer;

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

    private void findViews(View view){
        image = view.findViewById(R.id.offer_image);
        company = view.findViewById(R.id.offer_company);
        position = view.findViewById(R.id.offer_position);
        location = view.findViewById(R.id.offer_location);
        description = view.findViewById(R.id.offer_description);
        postedDate = view.findViewById(R.id.offer_posted);
    }

    public void setView(JobOffer offer){
        Picasso.get()
                .load(offer.getImg())
                .fit()
                .centerCrop()
                .into(image);
        position.setText(offer.getType());
        company.setText(offer.getCompany());
        location.setText(offer.getLocation());
        description.setText(offer.getDescription());
        postedDate.setText(offer.getDatePosted());
    }
}
