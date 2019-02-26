package fr.smartrecruit.view.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import fr.smartrecruit.R;
import fr.smartrecruit.controller.OffersDetailController;
import fr.smartrecruit.data.FragmentConstants;
import fr.smartrecruit.data.JobOffer;
import fr.smartrecruit.databinding.ActivityOfferDetailBinding;

public class OfferDetailActivity extends AppCompatActivity{

    private JobOffer offer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /** To refactor to match MVVM pattern */
        offer = (JobOffer) getIntent().getSerializableExtra("offer");
        ActivityOfferDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_offer_detail);
        binding.setOffer(offer);

        Button apply = findViewById(R.id.offer_detail_apply);
        switch (getIntent().getIntExtra("fragment", 0)){
            case FragmentConstants.Fragment_Offers:
                apply.setVisibility(View.VISIBLE);
                break;
            case FragmentConstants.Fragment_Applications:
                apply.setVisibility(View.GONE);
                break;
            default:
                break;

        }

        ImageView image = findViewById(R.id.offer_detail_image);

        /*Picasso.get()
                .load(offer.getImg())
                .fit()
                .centerCrop()
                .into(image);*/

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public void apply(View view){
        new OffersDetailController(this).addAppliedOffer(offer);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
