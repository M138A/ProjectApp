package info.androidhive.materialnavbar.Activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import info.androidhive.materialnavbar.AndroidDate;
import info.androidhive.materialnavbar.CardItem;
import info.androidhive.materialnavbar.Fact;
import info.androidhive.materialnavbar.FavoriteManager;
import info.androidhive.materialnavbar.R;
import info.androidhive.materialnavbar.ReportDialog;
import info.androidhive.materialnavbar.ViewAdapters.ListViewMenuAdapter;
import info.androidhive.materialnavbar.ViewAdapters.RVAdapter;
import info.androidhive.materialnavbar.fragment.InformationFragment;

public class MainActivity extends AppCompatActivity {

    //ez toast
    Context context = this;
    CharSequence text = "Clicked";
    int duration = Toast.LENGTH_LONG;

    /* als je iets wilt ''toasten'' :
       Toast toast = Toast.makeText(context, text, duration);
        toast.show();
     */
    //
    private ListViewMenuAdapter adapter = new ListViewMenuAdapter();
    private List<CardItem> cardItems;
    private DrawerLayout mDrawerLayout;
    private InformationFragment informationFragment = new InformationFragment();
    public static int cardcounter = 0;
    private FavoriteManager favoriteManager = null;

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
    private void initializeFavorites()
    {
        favoriteManager = new FavoriteManager(getBaseContext());
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
        initializeFavorites();
        ListView codeLearnLessons = (ListView) findViewById(R.id.listViewId);
        codeLearnLessons.setAdapter(adapter);
        codeLearnLessons.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position,
                                    long id) {
                long menulist = a.getAdapter().getItemId(position);
                switch ((int) menulist) {
                    case 0:
                        InformationFragment.refresh = false;
                        informationFragment.getCardData(0,favoriteManager);
                        adapter.notifyDataSetChanged();
                        setTitle("Today");// tijdelijk om te checken of kaarten refreshen
                        break;
                    case 1:
                        InformationFragment.refresh = false;
                        informationFragment.getCardData(1,favoriteManager);
                        adapter.notifyDataSetChanged();
                        setTitle("Facts");// tijdelijk om te checken of kaarten refreshen
                        break;
                    case 2:
                        InformationFragment.refresh = false;
                        informationFragment.getCardData(2,favoriteManager);
                        adapter.notifyDataSetChanged();
                        break;
                    case 3:
                        InformationFragment.refresh = false;
                        informationFragment.getCardData(3,favoriteManager);
                        adapter.notifyDataSetChanged();
                        break;
                    case 4:
                        InformationFragment.refresh = false;
                        informationFragment.getCardData(4, favoriteManager);
                        adapter.notifyDataSetChanged();
                        break;
                    case 5:
                        InformationFragment.refresh = false;
                        informationFragment.getCardData(5,favoriteManager);
                        adapter.notifyDataSetChanged();
                        break;
                    case 6:
                        InformationFragment.refresh = false;
                        informationFragment.getCardData(6,favoriteManager);
                        adapter.notifyDataSetChanged();
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

    // report button
    public void ReportAction(View view) {
        View view1 = (View) view.findViewById(R.id.ReportBut).getParent(); //Get the Parent(CardView) from the clicked button
        final TextView t1 = (TextView) view1.findViewById(R.id.person_age);
        final TextView t2 = (TextView) view1.findViewById(R.id.person_name);

        // Alert dialog/PopUp
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Report Article"); // title
        alert.setMessage("Comment :"); // content
        // nieuwe text area
        final EditText input = new EditText(this);
        // zet text area in alert
        alert.setView(input);
        // send button
        /**
         * Create onclicklistener for the pop up
         * Send starts new Thread which sends the comment including the whole fact to PHP script
         */
        alert.setPositiveButton("Send", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                final String value = input.getText().toString();
                final AndroidDate date = new AndroidDate();
                final ReportDialog report = new ReportDialog();
                new Thread(new Runnable() {
                    public void run() {
                        report.reportFact(String.valueOf(t1.getText()),
                                String.valueOf(date.getDayNumber() + "-" + date.getFullmonthNumber()),
                                String.valueOf(t2.getText()), value);
                    }
                }).start();
                Toast toast = Toast.makeText(context, "Thanks for your Feedback!", duration);
                toast.show();
            }
        });
        // cancel button
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
            }
        }); // show allert
        alert.show();
    }
    public void ManageFavorites(View view)
    {
        View favButton = (View) view.findViewById(R.id.favButton).getParent();

        String Title = ((TextView) favButton.findViewById(R.id.person_name)).getText().toString();
        String description = ((TextView) favButton.findViewById(R.id.person_age)).getText().toString();
        favoriteManager.addFact(new Fact(Title, description));


    }
}

