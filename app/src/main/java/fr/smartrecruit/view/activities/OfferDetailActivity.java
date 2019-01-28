package fr.smartrecruit.view.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import fr.smartrecruit.R;
import fr.smartrecruit.data.JobOffer;
import fr.smartrecruit.databinding.ActivityOfferDetailBinding;

public class OfferDetailActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_offer_detail);

        JobOffer offer = (JobOffer) getIntent().getSerializableExtra("offer");
        ActivityOfferDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_offer_detail);
        binding.setOffer(offer);

        ImageView image = findViewById(R.id.offer_detail_image);
        Picasso.get()
                .load(offer.getImg())
                .fit()
                .centerCrop()
                .into(image);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //new OffersDetailViewModel(this).setView(offer);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
