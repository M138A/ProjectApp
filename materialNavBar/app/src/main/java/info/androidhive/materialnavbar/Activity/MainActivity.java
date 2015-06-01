package info.androidhive.materialnavbar.Activity;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
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
import info.androidhive.materialnavbar.MenuList;
import info.androidhive.materialnavbar.R;
import info.androidhive.materialnavbar.ViewAdapters.ListViewMenuAdapter;

public class MainActivity extends AppCompatActivity {
    private JSON jsonObject = null;
    private ArrayList<Fact> facts = null;
    private ListViewMenuAdapter adapter = new ListViewMenuAdapter();


    @Override
    //Teset push
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        // Setting toolbar as the ActionBar with setSupportActionBar() call
        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager()
                        .findFragmentById(R.id.drawer_fragment);
        drawerFragment.setUp(R.id.drawer_fragment, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);

        StrictMode.ThreadPolicy old = StrictMode.getThreadPolicy();
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder(old)
                .permitAll()
                .build());
        try {

            testJSON();
        } catch (Exception e) {
            e.printStackTrace();
        }

        ListView codeLearnLessons = (ListView) findViewById(R.id.listViewId);
        codeLearnLessons.setAdapter(adapter);
        onClickMenuItem(codeLearnLessons);
    }



    public void onClickMenuItem(ListView codeLearnLessons) {
        codeLearnLessons.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position,
                                    long id) {
                String b = String.valueOf(adapter.getItem(position));
                System.out.println(b);
            }
        });
    }


    private void testJSON() throws JSONException, ExecutionException, InterruptedException {
        jsonObject = new JSON(getBaseContext(), findViewById(R.id.progressBar));
        facts = jsonObject.getFactAllList();
        fillLayout();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

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
                facts = jsonObject.getFactsList();
                Log.d("facts", facts.get(0).getName());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}