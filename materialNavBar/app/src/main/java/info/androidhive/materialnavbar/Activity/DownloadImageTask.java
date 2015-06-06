package info.androidhive.materialnavbar.Activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * TODO waarom mag deze niet private ?
 *
 * image async thread do in background test
 *
 * er is een lib beschikbaar voor * https://github.com/koush/UrlImageViewHelper *
 *
 * maar beter zonder
 *
 *  TODO http://stackoverflow.com/questions/5776851/load-image-from-url
 *
 *               >>Doet op het moment nog niets!<<
 *
 */
public class DownloadImageTask extends AsyncTask<String, Void, Void> {
    ImageView bmImage;

    public DownloadImageTask(ImageView bmImage) {
        this.bmImage = bmImage;
    }


    @Override
    protected Void doInBackground(String... strings) {
        URL url = null;
        try {
            url = new URL("https://pbs.twimg.com/profile_images/522909800191901697/FHCGSQg0.png");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}