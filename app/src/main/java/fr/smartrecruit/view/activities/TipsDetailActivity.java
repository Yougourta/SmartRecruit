package fr.smartrecruit.view.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import fr.smartrecruit.R;
import fr.smartrecruit.data.FragmentConstants;
import fr.smartrecruit.data.Tips;
import fr.smartrecruit.databinding.ActivityTipsDetailBinding;
import fr.smartrecruit.viewmodel.OffersDetailViewModel;
import fr.smartrecruit.viewmodel.TipsDetailViewModel;

public class TipsDetailActivity extends AppCompatActivity{

    private Tips tips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /** To refactor to match MVVM pattern */
        tips = (Tips) getIntent().getSerializableExtra("tips");
        ActivityTipsDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_tips_detail);
        binding.setTips(tips);




        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
