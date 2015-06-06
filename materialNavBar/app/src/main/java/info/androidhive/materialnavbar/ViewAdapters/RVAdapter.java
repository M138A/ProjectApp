package info.androidhive.materialnavbar.ViewAdapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import info.androidhive.materialnavbar.Animations;
import info.androidhive.materialnavbar.CardItem;
import info.androidhive.materialnavbar.R;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder> {

    // Recycler View Adapter
    public static class PersonViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView personName;
        TextView personAge;
        ImageView personPhoto;

        PersonViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            personName = (TextView)itemView.findViewById(R.id.person_name);
            personAge = (TextView)itemView.findViewById(R.id.person_age);
            personPhoto = (ImageView)itemView.findViewById(R.id.person_photo);
        }
    }

    List<CardItem> cardItems;

    public RVAdapter(List<CardItem> cardItems){
        this.cardItems = cardItems;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.carditem, viewGroup, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }


    // de ''oncreate''
    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {
        // vult het object/element met
        personViewHolder.personName.setText(cardItems.get(i).name);
        personViewHolder.personAge.setText(cardItems.get(i).age);
        personViewHolder.personPhoto.setImageResource(cardItems.get(i).photoId);
        //zet animation op dit object/element
        Animations.animateScatter(personViewHolder, true);


    }

    @Override
    public int getItemCount() {
        return cardItems.size();
    }

}