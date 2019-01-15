package fr.smartrecruit.viewmodel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import fr.smartrecruit.R;
import fr.smartrecruit.data.JobOffer;

/**
 * Created by Yougourta on 22/11/2018.
 */

public class OffersAdapter extends BaseAdapter {

    private List<JobOffer> offers;
    private Context context;

    public OffersAdapter(Context context, List<JobOffer> offers){
        this.offers = offers;
        this.context = context;
    }

    @Override
    public int getCount() {
        return offers.size();
    }

    @Override
    public Object getItem(int position) {
        return offers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.offers_row, parent, false);
        ImageView img = view.findViewById(R.id.img);
        TextView type = view.findViewById(R.id.type);
        TextView company = view.findViewById(R.id.company);
        TextView location = view.findViewById(R.id.location);
        TextView description = view.findViewById(R.id.description);

        Button candidater = view.findViewById(R.id.candidat);
        Button favoris = view.findViewById(R.id.favoris);

        candidater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Ola pls", Toast.LENGTH_SHORT).show();
            }
        });

        favoris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Ola too pls", Toast.LENGTH_SHORT).show();
            }
        });

        img.setImageResource(offers.get(position).getImg());
        type.setText(offers.get(position).getType());
        company.setText(offers.get(position).getCompany());
        location.setText(offers.get(position).getLocation());
        description.setText(offers.get(position).getDescription());

        return view;
    }
}
