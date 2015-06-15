package info.androidhive.materialnavbar.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import info.androidhive.materialnavbar.Activity.MainActivity;
import info.androidhive.materialnavbar.CardItem;
import info.androidhive.materialnavbar.Fact;
import info.androidhive.materialnavbar.FavoriteManager;
import info.androidhive.materialnavbar.JSON;
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
    private static List<CardItem> CardEntry = new ArrayList<>();
    private Map<Integer, String> mFragmentTags;
    public static boolean refresh = false;
    private ArrayList<Fact> facts = null;
    private JSON jsonObject = null;
    private int reporticon = R.drawable.ic_sim_alert_black_18dp;

    public int getCurrentType() {
        return currentType;
    }

    private int currentType = 0;
    //haalt list leeg
    public void refreshFragment() {
        // zet fragment op null
        Fragment frg = null;
        frg = getFragmentManager().findFragmentById(2131296339);
        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(frg);
        ft.attach(frg);
        ft.commit();
    }
    private void loadFavorites(FavoriteManager favoriteManager)
    {
       ArrayList<Fact> favorites = favoriteManager.getFavorites();
        CardEntry.removeAll(CardEntry);
        if (facts != null) {
            for (Fact fact : favorites) {
                String name = fact.getName();
                String description = fact.getDescription();
                // V title  Vcontent    V img
                CardEntry.add(new CardItem(name, description, R.drawable.ic_facts, reporticon));
            }
        }
    }
    private void fillCardList(String type)
    {
        try {
            jsonObject = new JSON(getActivity().getBaseContext(), getActivity().findViewById(R.id.progressBar), type);
            facts = jsonObject.getFactAllList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (facts != null) {
            CardEntry.removeAll(CardEntry);
            for (Fact fact : facts) {

                //TextView x = new TextView(findViewById(R.id.RelaListCard).getContext());
                String name = fact.getName();
                String description = fact.getDescription();
                // V title  Vcontent    V img
                CardEntry.add(new CardItem(name, description, R.drawable.ic_facts, reporticon));
            }
        }
        else {
            Log.e("ERROR:", "FACTSLIST IS EMPTY");
        }
    }
    // recycler list
    public List<CardItem> getCardData(int type, FavoriteManager f) {

        currentType = type;
        // tijdelijk knop voor testing

        switch (type) {
            case 0:
                fillCardList("history");
                //workaround
                //zet case type om naar cardcounter > inflater > refresh
                MainActivity.cardcounter = 0;
                if (!refresh) {
                    refreshFragment();
                    refresh = true;
                }
                break;

            case 1:
                fillCardList("facts");
                // CardEntry.add(new CardItem("Facts", "string a", R.drawable.ic_birthdays, reporticon));
                //workaround
                MainActivity.cardcounter = 1;
                if (!refresh) {
                    refreshFragment();
                    refresh = true;
                }
                break;

            case 2:
                fillCardList("history");
                //CardEntry.add(new CardItem("History", "string a", R.drawable.ic_history, reporticon));
                //workaround
                MainActivity.cardcounter = 2;
                if (!refresh) {
                    refreshFragment();
                    refresh = true;
                }
                break;

            case 3:
                fillCardList("birthday");
                //CardEntry.add(new CardItem("Birthday", "string a", R.drawable.ic_lifehacks, reporticon));
                //workaround
                MainActivity.cardcounter = 3;
                if (!refresh) {
                    refreshFragment();
                    refresh = true;
                }
                break;

            case 4:
                fillCardList("lifehacks");
                //CardEntry.add(new CardItem("Lifehacks", "string a", R.drawable.ic_facts, reporticon));
                //workaround
                MainActivity.cardcounter = 4;
                if (!refresh) {
                    refreshFragment();
                    refresh = true;
                }
                break;

            case 5:
                fillCardList("quote");
                //CardEntry.add(new CardItem("Quotes", "string a", R.drawable.ic_facts, reporticon));
                //workaround
                MainActivity.cardcounter = 5;
                if (!refresh) {
                    refreshFragment();
                    refresh = true;
                }
                break;

            case 6:
                loadFavorites(f);
                //workaround
                MainActivity.cardcounter = 6;
                if (!refresh) {
                    refreshFragment();
                    refresh = true;
                }
                break;
        }
        return CardEntry;
    }


    public List<CardItem> getCardEntry() {
        return CardEntry;
    }
    // end recycler


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment * iets aangepast, return onderaan voor recycler drawer
        View layout = inflater.inflate(R.layout.fragment_content, container, false);
        // pak het id van de recycler uit xml
        recyclerView = (RecyclerView) layout.findViewById(R.id.RecyclerCardList);
        // haal de data  op uit de return/switch hier boven
        //
        mRecyclerViewAdapter = new RVAdapter(getCardData(MainActivity.cardcounter, new FavoriteManager(layout.getContext())));
        //
        recyclerView.setAdapter(mRecyclerViewAdapter);
        // zet hem als dit op deze activity
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return layout;
    }

}