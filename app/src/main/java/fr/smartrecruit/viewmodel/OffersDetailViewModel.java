package fr.smartrecruit.viewmodel;

import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import fr.smartrecruit.R;
import fr.smartrecruit.data.JobOffer;
import fr.smartrecruit.view.activities.OfferDetailActivity;

public class OffersDetailViewModel{

    private ImageView image;
    private TextView company;
    private TextView position;
    private TextView location;
    private TextView description;
    private TextView postedDate;

    public OffersDetailViewModel(OfferDetailActivity view){
        findViews(view);
    }

    private void findViews(OfferDetailActivity view){
        image = view.findViewById(R.id.offer_detail_image);
        company = view.findViewById(R.id.offer_detail_company);
        position = view.findViewById(R.id.offer_detail_position);
        location = view.findViewById(R.id.offer_detail_location);
        description = view.findViewById(R.id.offer_detail_description);
        postedDate = view.findViewById(R.id.offer_detail_posted);
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
