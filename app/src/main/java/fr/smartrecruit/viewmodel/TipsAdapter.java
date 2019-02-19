package fr.smartrecruit.viewmodel;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;



import java.util.List;

import fr.smartrecruit.R;
import fr.smartrecruit.data.FragmentConstants;
import fr.smartrecruit.data.Tips;
import fr.smartrecruit.view.activities.OfferDetailActivity;
import fr.smartrecruit.view.activities.TipsDetailActivity;

public class TipsAdapter extends RecyclerView.Adapter<TipsAdapter.TipsViewHolder> {

    private List<Tips> tips;
    private Context context;

    public TipsAdapter(List<Tips> tips, Context context){
        this.tips = tips;
        this.context = context;
    }

    @NonNull
    @Override
    public TipsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View item = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.fragment_tips_row, parent, false);
        return new TipsViewHolder(item);
    }

   @Override
    public void onBindViewHolder(@NonNull TipsViewHolder holder, final int position) {
        holder.setView(tips.get(position));
       holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intentDetail = new Intent(context, TipsDetailActivity.class);

              intentDetail.putExtra("tips", tips.get(position));
              intentDetail.putExtra("fragment", FragmentConstants.Fragment_Tips);
              context.startActivity(intentDetail);
           }
       });

    }

    @Override
    public int getItemCount() {
        return tips.size();
    }

    public static class TipsViewHolder extends RecyclerView.ViewHolder{


        private TextView conseil;
        private TextView type;


        public TipsViewHolder(View itemView) {
            super(itemView);
            findViews(itemView);
        }

        public void findViews(View view){

            conseil = view.findViewById(R.id.tips_conseil);
            type = view.findViewById(R.id.tips_type);

        }

        public void setView(Tips tips){

            conseil.setText(tips.getConseil());
            type.setText(tips.getType());

        }
    }
}
