package info.androidhive.materialnavbar;

import android.content.Context;
import android.util.Log;
import android.view.View;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


/**
 * Created by mark on 21-5-15.
 */
public class JSON {
    private String jsonString = "";
    private JSONArray json = null;
    private StringBuilder builder = new StringBuilder();

    public ArrayList<Fact> getFactAllList() {return factAllList;}

    public ArrayList<Fact> factAllList = null;

    public void setJsonString(String jsonString) {
        this.jsonString = jsonString;
    }




    public JSON(Context con, View vw) throws JSONException, ExecutionException, InterruptedException {

            new FactsLoader(con,vw,this).execute().get();

        json = getQuoteArray(jsonString);
        factAllList = getFactsList();
    }

    public JSONArray getFacts() {

        return json;
    }

    public ArrayList<Fact> getFactsList() throws JSONException {
        int arrayLength = json.length();
        ArrayList<Fact> allFacts = new ArrayList<Fact>(arrayLength);
        for (int i = 0; i < arrayLength; i++) {
            JSONObject y = json.getJSONObject(i);
            Fact JSONextractedFact = null;
            JSONextractedFact = new Fact(y.get("author").toString(), y.get("content").toString());
            allFacts.add(JSONextractedFact);
        }

        return allFacts;
    }

    public String requestJSON()
    {
        HttpResponse response = null;
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("http://www.bartfokker.nl/factorial/");
        String respJSON = null;
        try {
            // Add your data
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
            nameValuePairs.add(new BasicNameValuePair("table", "quote"));
            nameValuePairs.add(new BasicNameValuePair("datum", "0004-01-01"));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            // Execute HTTP Post Request
            response  = httpclient.execute(httppost);

            respJSON = inputStreamToString(response.getEntity().getContent()).toString();

        } catch (ClientProtocolException e) {

        } catch (IOException e) {

          if(respJSON == null)
        {
            throw new NullPointerException("jsonString is null");
        }      }

        return respJSON;
    }

    private JSONArray convertStringToJSONarray(String JSON) throws JSONException {
        JSONObject JSONObject = new JSONObject(JSON);
        JSONArray JSONArray = JSONObject.getJSONArray("records");
        return JSONArray;

    }
    private JSONArray getQuoteArray(String JSON) throws JSONException {
        JSONArray x = new JSONArray(JSON);
        Log.d("JSONARRAY",x.toString());
        return x;
    }
    private StringBuilder inputStreamToString(InputStream is) throws IOException {
        String line = "";
        StringBuilder total = new StringBuilder();
        // Wrap a BufferedReader around the InputStream
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        // Read response until the end
        while ((line = rd.readLine()) != null) {
            total.append(line);
        }
        // Return full string
        return total;
    }


    /**
     * OLD METHOD, SHOULD NOT BE USED
     * Replaced by requestJSON()
     * @param url String containing the URL
     * @return JSONArray
     */
    private JSONArray getJSONFromUrl(String url) {
        JSONArray jarray = null;
        HttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        try {
            HttpResponse response = client.execute(httpGet);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode == 200) {
                HttpEntity entity = response.getEntity();
                InputStream content = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(content));
                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }
            } else {
                Log.e("==>", "Failed to download file");
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Parse String to JSON object
        try {
            String builder2 = builder.toString();
            // JSONObject myObject = new JSONObject(builder2);
            json = convertStringToJSONarray(builder2);
            //Log.d("JSON",json.toString());
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }
        // return JSON Object
        return jarray;

    }
}
