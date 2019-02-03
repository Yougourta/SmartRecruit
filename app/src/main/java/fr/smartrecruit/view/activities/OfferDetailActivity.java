package fr.smartrecruit.view.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import fr.smartrecruit.R;
import fr.smartrecruit.data.JobOffer;
import fr.smartrecruit.databinding.ActivityOfferDetailBinding;
import fr.smartrecruit.viewmodel.OffersDetailViewModel;

public class OfferDetailActivity extends AppCompatActivity{

    private JobOffer offer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /** To refactor to match MVVM pattern */
        offer = (JobOffer) getIntent().getSerializableExtra("offer");
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
    }

    public void apply(View view){
        new OffersDetailViewModel().addAppliedOffer(offer);
        Toast.makeText(this, "You applied to: "+offer.getPosition(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
