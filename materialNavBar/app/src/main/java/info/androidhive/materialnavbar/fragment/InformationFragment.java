package info.androidhive.materialnavbar.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import info.androidhive.materialnavbar.Activity.MainActivity;
import info.androidhive.materialnavbar.CardItem;
import info.androidhive.materialnavbar.R;
import info.androidhive.materialnavbar.ViewAdapters.RVAdapter;

/**
 * Created by bart on 10-6-2015
 * 2015 - 06
 * Superclass for all Information Fragments
 * Class handles the inflation of the RecyclerView and the XML files
 */

public class InformationFragment extends Fragment {
    private RecyclerView recyclerView;
    private RVAdapter mRecyclerViewAdapter;
    private View containerView;
    private static List<CardItem> CardEntry;
    private Map<Integer, String> mFragmentTags;
    public static boolean refresh = false;



    //haalt list leeg
    public void refreshFragment(){
            // zet fragment op null
            Fragment frg = null;
            frg = getFragmentManager().findFragmentById(2131296339);
            final FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.detach(frg);
            ft.attach(frg);
            ft.commit();
    }

    // recycler list
    public List<CardItem> getCardData(int type) {
        CardEntry = new ArrayList<>();
        // tijdelijk knop voor testing
        int reporticon = R.drawable.reporttemp;
        switch (type) {
            case 0:
                // Vull kaartjes met volgende items :
                CardEntry.add(new CardItem("Today", "string a", R.drawable.ic_facts,reporticon));
                //workaround
                //zet case type om naar cardcounter > inflater > refresh
                MainActivity.cardcounter = 0;
                if (!refresh){
                refreshFragment();
                    refresh = true;
                }
                break;
            case 1:                         // V title  Vcontent    V img
                CardEntry.add(new CardItem("Facts", "string a", R.drawable.ic_birthdays,reporticon));
                //workaround
                MainActivity.cardcounter = 1;
                if (!refresh){
                    refreshFragment();
                    refresh = true;
                }
                break;
            case 2:
                CardEntry.add(new CardItem("History", "string a", R.drawable.ic_history,reporticon));
                //workaround
                MainActivity.cardcounter = 2;
                if (!refresh){
                    refreshFragment();
                    refresh = true;
                }
                break;
            case 3:
                CardEntry.add(new CardItem("Birthday", "string a", R.drawable.ic_lifehacks,reporticon));
            //workaround
            MainActivity.cardcounter = 3;
            if (!refresh){
                refreshFragment();
                refresh = true;
            }
            break;
            case 4:
                CardEntry.add(new CardItem("Lifehacks", "string a", R.drawable.ic_facts,reporticon));
                //workaround
                MainActivity.cardcounter = 4;
                if (!refresh){
                    refreshFragment();
                    refresh = true;
                }
                break;
            case 5:
                CardEntry.add(new CardItem("Quotes", "string a", R.drawable.ic_facts,reporticon));
                //workaround
                MainActivity.cardcounter = 5;
                if (!refresh){
                    refreshFragment();
                    refresh = true;
                }
                break;
            case 6:
                CardEntry.add(new CardItem("Favorites", "string a", R.drawable.ic_facts,reporticon));
                //workaround
                MainActivity.cardcounter = 6;
                if (!refresh){
                    refreshFragment();
                    refresh = true;
                }
                break;
        }
        return CardEntry;
    }


    // end recycler


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
            // Inflate the layout for this fragment * iets aangepast, return onderaan voor recycler drawer
            View layout = inflater.inflate(R.layout.fragment_content, container, false);
            // pak het id van de recycler uit xml
            recyclerView = (RecyclerView) layout.findViewById(R.id.RecyclerCardList);
            // haal de data  op uit de return/switch hier boven
            //
            mRecyclerViewAdapter = new RVAdapter(getCardData(MainActivity.cardcounter));
            //
            recyclerView.setAdapter(mRecyclerViewAdapter);
            // zet hem als dit op deze activity
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
              return layout;
    }

}
