package info.androidhive.materialnavbar.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
    private static List<CardItem>  KaartItems;
    private Map<Integer, String> mFragmentTags;
    public static boolean refresh = false;



    //haalt list leeg
    public void refreshlist(){
            Fragment frg = null;
            frg = getFragmentManager().findFragmentById(2131296339);
            final FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.detach(frg);
            ft.attach(frg);
            ft.commit();

        List<Fragment> allFragments = getFragmentManager().getFragments();
        for(int z =0; z< allFragments.size();z++){
            System.out.println("tags");
            System.out.println(allFragments.get(z).getTag());
            System.out.println("ids");
            System.out.println(allFragments.get(z).getId());
            System.out.println("hashstuff");
            System.out.println(allFragments.get(z).toString());

        }
        System.out.println("zzzzzzzzzzz");
        System.out.println(MainActivity.cardcounter);
    }

    // recycler list
    public List<CardItem> haalData(int type) {
        KaartItems = new ArrayList<>();
        switch (type) {
            case 0:
                KaartItems.add(new CardItem("A", "string a", R.drawable.ic_facts));
                KaartItems.add(new CardItem("A", "string a", R.drawable.ic_facts));

                MainActivity.cardcounter = 0;
                if (!refresh){
                refreshlist();
                    refresh = true;
                }

                break;
            case 1:
                KaartItems.add(new CardItem("B", "string a", R.drawable.ic_birthdays));
                KaartItems.add(new CardItem("B", "string a", R.drawable.ic_birthdays));

                MainActivity.cardcounter = 1;
                if (!refresh){
                    refreshlist();
                    refresh = true;

                }
                break;
            case 2:
                KaartItems.add(new CardItem("C", "string a", R.drawable.ic_history));
                KaartItems.add(new CardItem("C", "string a", R.drawable.ic_history));

                MainActivity.cardcounter = 2;
                break;
            case 3:
                KaartItems.add(new CardItem("D", "string a", R.drawable.ic_lifehacks));
                KaartItems.add(new CardItem("D", "string a", R.drawable.ic_lifehacks));

                break;
            case 4:
                KaartItems.add(new CardItem("E", "string a", R.drawable.ic_facts));
                break;
            case 5:
                KaartItems.add(new CardItem("F", "string a", R.drawable.ic_facts));
                break;
            case 6:
                KaartItems.add(new CardItem("G", "string a", R.drawable.ic_facts));
                break;
        }
        return KaartItems;
    }


    // end recycler


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
            // Inflate the layout for this fragment * iets aangepast, return onderaan voor recycler drawer
            View layout = inflater.inflate(R.layout.fragment_test, container, false);
            // pak het id van de recycler uit xml
            recyclerView = (RecyclerView) layout.findViewById(R.id.RecyclerCardList);
            // haal de data  op uit de return/switch hier boven
            //
            mRecyclerViewAdapter = new RVAdapter(haalData(MainActivity.cardcounter));
            //
            recyclerView.setAdapter(mRecyclerViewAdapter);
            // zet hem als dit op deze activity
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return layout;
    }

    public void setText(String category) {
        TextView v = (TextView) getActivity().findViewById(R.id.fragmentinformation);
        v.setText(category);
    }
}
