package info.androidhive.materialnavbar.Activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import info.androidhive.materialnavbar.CardItem;
import info.androidhive.materialnavbar.R;
import info.androidhive.materialnavbar.ViewAdapters.ListViewMenuAdapter;
import info.androidhive.materialnavbar.fragment.InformationFragment;

public class MainActivity extends AppCompatActivity {
    private ListViewMenuAdapter adapter = new ListViewMenuAdapter();
    private List<CardItem> cardItems;
    private DrawerLayout mDrawerLayout;
    private InformationFragment informationFragment = new InformationFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Create fragment and give it an argument specifying the article it should show
        //Initialize the toolbar
        setFragmentInfo(informationFragment);
        initToolbar();
        ///Set categories for the navigation drawer in a listview
        setListMenuItems();

    }

    private void setFragmentInfo(InformationFragment f) {
        // Begin the transaction
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        // Replace the contents of the container with the new fragment
        ft.replace(R.id.fragment_category, f);
        // or ft.add(R.id.your_placeholder, new FooFragment());
        // Complete the changes added above
        ft.commit();
    }


    private void setListMenuItems() {
        // zet de list carditem naar die van het slide menu
        ListView codeLearnLessons = (ListView) findViewById(R.id.listViewId);
        codeLearnLessons.setAdapter(adapter);
        codeLearnLessons.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position,
                                    long id) {
                long menulist = a.getAdapter().getItemId(position);
                switch ((int) menulist) {
                    case 0:
                        informationFragment.setText("Today");
                        break;
                    case 1:
                        informationFragment.setText("Facts");
                        break;
                    case 2:
                        informationFragment.setText("History");
                        break;
                    case 3:
                        informationFragment.setText("Birthdays");
                        break;
                    case 4:
                        informationFragment.setText("Lifehacks");
                        break;
                    case 5:
                        informationFragment.setText("Quotes");
                        break;
                    case 6:
                        informationFragment.setText("Favorites");
                        break;
                }
                closeDrawer();
            }
        });
    }

    private void closeDrawer() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.closeDrawers();
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
            /**
             * TODO settings
             */
        } else if (id == R.id.action_exit) {
            moveTaskToBack(true);
        }

        return super.onOptionsItemSelected(item);
    }

}

