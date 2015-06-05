package info.androidhive.materialnavbar.Activity;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import info.androidhive.materialnavbar.Fact;
import info.androidhive.materialnavbar.JSON;
import info.androidhive.materialnavbar.CardItem;
import info.androidhive.materialnavbar.R;
import info.androidhive.materialnavbar.ViewAdapters.ListViewMenuAdapter;
import info.androidhive.materialnavbar.ViewAdapters.RVAdapter;

public class MainActivity extends AppCompatActivity {
    private JSON jsonObject = null;
    private ArrayList<Fact> facts = null;
    private ListViewMenuAdapter adapter = new ListViewMenuAdapter();
    private List<CardItem> cardItems;
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Initialize the toolbar
        initToolbar();
        //Initialize the Frontpage with JSON formatted information
        initFrontPageInformation();
        ///Set categories for the navigation drawer in a listview
        setListMenuItems();
        //recycl.
        RecyclerPart();

    }


    // slide menu items
    private void setListMenuItems() {
        // zet de list carditem naar die van het slide menu
        ListView codeLearnLessons = (ListView) findViewById(R.id.listViewId);
        codeLearnLessons.setAdapter(adapter);
        codeLearnLessons.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position,
                                    long id) {
                // test menu click items
                String b = String.valueOf(adapter.getItem(position));
                System.out.println(b);

                //todo
                /** FragmentManager fragmentManager = getFragmentManager();
                 ContentFragment contentFragment = new ContentFragment();
                 FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                 // work here to change Activity fragments (add, remove, etc.).  Example here of adding.
                 fragmentTransaction.add(R.layout.fragment_test, contentFragment);
                 fragmentTransaction.commit();**/
            }
        });
    }

    private void initFrontPageInformation() {
        StrictMode.ThreadPolicy old = StrictMode.getThreadPolicy();
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder(old)
                .permitAll()
                .build());
        try {
            testJSON();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Set the Toolbar as Actionbar
     */
    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        // Setting toolbar as the ActionBar with setSupportActionBar() call
        setSupportActionBar(toolbar);

        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager()
                        .findFragmentById(R.id.drawer_fragment);
        drawerFragment.setUp(R.id.drawer_fragment, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);
    }


    private void testJSON() throws JSONException, ExecutionException, InterruptedException {
        jsonObject = new JSON(getBaseContext(), findViewById(R.id.progressBar),"facts");
        facts = jsonObject.getFactAllList();
      //  fillLayout();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar carditem clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

/* Old imports
   private void fillLayout() {

        LinearLayout ll = (LinearLayout) findViewById(R.id.linearFactLayout);
        if (facts != null) {
            for (Fact fact : facts) {
                TextView x = new TextView(findViewById(R.id.linearFactLayout).getContext());
                String name = fact.getName();
                String description = fact.getDescription();
                x.setText(name + "\n" + description + "\n");
                ll.addView(x);
                Log.i("Fact", name);
            }
        } else {
            Log.e("ERROR", "Factslist is empty");
            try {
                facts = jsonObject.getFactsList("facts");
                Log.d("facts", facts.get(0).getName());
            } catch (JSONException e) {
                e.printStackTrace();
            }
      */

    // Recycler cards part

    //
    private void RecyclerPart(){
        // referenties recycler
        rv=(RecyclerView)findViewById(R.id.rv);
        // refereerd naar de Linear Layout waar hij in staat.
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);
        // pakt de data om hem te vullen, zie onder
        initializeData();
        initializeAdapter();

    }

    // nieuwe list/data/recyclercard methode

    private void initializeData() {
        cardItems = new ArrayList<>();
        // array voor card items
        // Mark Json methode
        if (facts != null) {
            for (Fact fact : facts) {
                //TextView x = new TextView(findViewById(R.id.RelaListCard).getContext());
                String name = fact.getName();
                String description = fact.getDescription();
                // x.setText(name + "\n" + description + "\n");
                //  ll.addView(x);
                //  Log.i("Fact", name);
                cardItems.add(new CardItem(name, description, R.drawable.ic_facts));

            }
        } else {
            Log.e("ERROR", "Factslist is empty");
            try {
                facts = jsonObject.getFactsList("facts");
                Log.d("facts", facts.get(0).getName());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

            // wat standaard items / template
            cardItems.add(new CardItem("John Smith", "23 years old", R.drawable.ic_birthdays));
            cardItems.add(new CardItem("Today in 1942", "Stuff War Stuff War Stuff", R.drawable.ic_history));
            cardItems.add(new CardItem("Lifehack #2423", "Iets slims wat tijd bespaart.", R.drawable.ic_lifehacks));
            cardItems.add(new CardItem("Lifehack #1342", "Iets slims wat geld bespaart.", R.drawable.ic_lifehacks));
            cardItems.add(new CardItem("Mike Smith", "27 years old", R.drawable.ic_birthdays));
            cardItems.add(new CardItem("Today in 42BC", "Fire was first invented.", R.drawable.ic_history));
            cardItems.add(new CardItem("Quote #253", "Hmm Hmmm Hmmm", R.drawable.ic_quotes));


    }

    private void initializeAdapter(){
        RVAdapter adapter = new RVAdapter(cardItems);
        rv.setAdapter(adapter);
    }
}