package fr.smartrecruit.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import fr.smartrecruit.R;
import fr.smartrecruit.data.JobOffer;

public class OffersViewHolder extends RecyclerView.ViewHolder{

    private ImageView image;
    private TextView company;
    private TextView type;
    private TextView location;
    private TextView description;

    public OffersViewHolder(View itemView) {
        super(itemView);
        findViews(itemView);
    }

    private void findViews(View view){
        image = view.findViewById(R.id.img);
        company = view.findViewById(R.id.company);
        type = view.findViewById(R.id.type);
        location = view.findViewById(R.id.location);
        description = view.findViewById(R.id.description);
    }

    public void setView(JobOffer offer){
        image.setImageResource(offer.getImg());
        type.setText(offer.getType());
        company.setText(offer.getCompany());
        location.setText(offer.getLocation());
        description.setText(offer.getDescription());
    }
}
