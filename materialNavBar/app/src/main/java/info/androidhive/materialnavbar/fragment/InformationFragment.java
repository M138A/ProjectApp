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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    public static boolean refresh = false;
    private static List<CardItem> CardEntry = new ArrayList<>();
    private RecyclerView recyclerView;
    private RVAdapter mRecyclerViewAdapter;
    private View containerView;
    private Map<Integer, String> mFragmentTags;
    private ArrayList<Fact> facts = null;
    private JSON jsonObject = null;
    private int currentType = 0;
    private int reporticon = R.drawable.ic_report;
    private int favicon = R.drawable.ic_favorites;
    public String categoryName;
    private FavoriteManager favMan = null;


    public int getCurrentType() {
        return currentType;
    }

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

    private void loadFavorites(FavoriteManager favoriteManager) {
        ArrayList<Fact> favorites = favoriteManager.getFavorites();
        CardEntry.removeAll(CardEntry);
        if (facts != null) {
            for (Fact fact : favorites) {
                String name = fact.getName();
                String description = fact.getDescription();
                // V title  Vcontent    V img
                CardEntry.add(new CardItem(name, description, R.drawable.ic_facts, reporticon, favicon, categoryName));
            }
        }
    }

    private void loadFactsToFragment(int i, String u) {
        if (facts != null) {
            CardEntry.removeAll(CardEntry);
            for (Fact fact : facts) {

                //TextView x = new TextView(findViewById(R.id.RelaListCard).getContext());
                String name = fact.getName();
                String description = fact.getDescription();
                // V title  Vcontent    V img
                if(favMan.isFavorite(fact)) {

                    CardEntry.add(new CardItem(name, description, i, reporticon, R.drawable.ic_fav_black, u));
                }
                else{
                    CardEntry.add(new CardItem(name, description, i, reporticon, R.drawable.ic_favorites, u));
                }
            }
        } else {
            Log.e("ERROR:", "FACTSLIST IS EMPTY");
        }
    }

    private void fillCardList(String type, int image, String catHeader) {
        try {
            jsonObject = new JSON(getActivity().getBaseContext(), getActivity().findViewById(R.id.progressBar), type);
            facts = jsonObject.getFactAllList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        loadFactsToFragment(image, catHeader);
    }

    private void loadFactsToFragmentWithoutRemoving() {
        if (facts != null) {

            for (Fact fact : facts) {

                //TextView x = new TextView(findViewById(R.id.RelaListCard).getContext());
                String name = fact.getName();
                String description = fact.getDescription();
                // V title  Vcontent    V img
                if(favMan.isFavorite(fact)) {
                    CardEntry.add(new CardItem(name, description, R.drawable.ic_facts, reporticon, R.drawable.ic_fav_black, categoryName));
                }
                else
                {
                    CardEntry.add(new CardItem(name, description, R.drawable.ic_facts, reporticon, R.drawable.ic_favorites, categoryName));
                }
            }
        } else {
            Log.e("ERROR:", "FACTSLIST IS EMPTY");
        }
    }

    private void generateGeneral() {
        CardEntry.removeAll(CardEntry);
        String[] Categories = new String[]{"history", "lifehacks", "quote", "facts", "birthday"};
        for (int i = 0; i < Categories.length; i++) {
            try {
                jsonObject = new JSON(getActivity().getBaseContext(), getActivity().findViewById(R.id.progressBar), Categories[i]);
                facts = jsonObject.getGeneralFact();

            } catch (Exception e) {
                e.printStackTrace();
            }
            loadFactsToFragmentWithoutRemoving();

        }


    }

    // recycler list
    public List<CardItem> getCardData(int type, FavoriteManager f) {
        favMan = f;
        int[] images = {R.drawable.ic_facts,R.drawable.ic_history,R.drawable.ic_birthdays,R.drawable.ic_lifehacks,R.drawable.ic_quotes};
        String[] catHeaders = {"FACT", "TODAY IN HISTORY", "CELEBRITY BIRTHDAY", "LIFEHACK", "QUOTE"};
        currentType = type;
        // tijdelijk knop voor testing

        switch (type) {
            case 0:
                generateGeneral();
                //workaround
                //zet case type om naar cardcounter > inflater > refresh
                MainActivity.cardcounter = 0;
                if (!refresh) {
                    refreshFragment();
                    refresh = true;
                }
                break;

            case 1:
                fillCardList("facts", images[0], catHeaders[0]);
                // CardEntry.add(new CardItem("Facts", "string a", R.drawable.ic_birthdays, reporticon));
                //workaround
                MainActivity.cardcounter = 1;
                if (!refresh) {
                    refreshFragment();
                    refresh = true;
                }
                break;

            case 2:
                fillCardList("history", images[1], catHeaders[1]);
                //CardEntry.add(new CardItem("History", "string a", R.drawable.ic_history, reporticon));
                //workaround
                MainActivity.cardcounter = 2;
                if (!refresh) {
                    refreshFragment();
                    refresh = true;
                }
                break;

            case 3:
                fillCardList("birthday", images[2], catHeaders[2]);
                //CardEntry.add(new CardItem("Birthday", "string a", R.drawable.ic_lifehacks, reporticon));
                //workaround
                MainActivity.cardcounter = 3;
                if (!refresh) {
                    refreshFragment();
                    refresh = true;
                }
                break;

            case 4:
                fillCardList("lifehacks", images[3], catHeaders[3]);
                //CardEntry.add(new CardItem("Lifehacks", "string a", R.drawable.ic_facts, reporticon));
                //workaround
                MainActivity.cardcounter = 4;
                if (!refresh) {
                    refreshFragment();
                    refresh = true;
                }
                break;

            case 5:
                fillCardList("quote", images[4], catHeaders[4]);
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