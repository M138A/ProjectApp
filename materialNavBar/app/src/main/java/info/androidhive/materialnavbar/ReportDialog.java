package info.androidhive.materialnavbar;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bart on 13-6-2015
 * 2015 - 06
 */
public class ReportDialog extends Activity {
    //ez toast
    Context context = this;
    CharSequence text = "Can't connect to e-mail!";
    int duration = Toast.LENGTH_SHORT;

    public void reportFact(String content, String datum, String category, String report) {

        try {
            HttpResponse response = null;
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://www.bartfokker.nl/factorial/email.php");

            List<NameValuePair> nameValuePairs;
            nameValuePairs = new ArrayList<NameValuePair>(4);
            nameValuePairs.add(new BasicNameValuePair("content", content));
            nameValuePairs.add(new BasicNameValuePair("datum", datum));
            nameValuePairs.add(new BasicNameValuePair("category", category));
            nameValuePairs.add(new BasicNameValuePair("report", report));

            // Execute HTTP Post Request
            response = httpclient.execute(httppost);

        }catch(Exception e){
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }


        //todo Get information from Textfield
        //todo create POST request and send to URL
    }




}
