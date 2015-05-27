package info.androidhive.materialnavbar;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

/**
 * Created by MarkGame on 26-5-2015.
 */
public class FactsLoader extends AsyncTask<Integer, Integer, Integer> {
    private Context x = null;
    private View y = null;
    private ProgressBar mProgress;
    private JSON json = null;
    public FactsLoader(Context c, View v, JSON j)
    {
        this.x = c;
        this.y = v;
        this.json = j;
        mProgress = (ProgressBar) y;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        y.setVisibility(ProgressBar.VISIBLE);
    }

    @Override
    protected Integer doInBackground(Integer... integers) {
       json.setJsonString(json.requestJSON());

        return 1;
    }

    @Override
    protected void onPostExecute(Integer s) {
        super.onPostExecute(s);

        mProgress.setVisibility(View.INVISIBLE);

    }
}

